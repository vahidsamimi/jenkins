properties(
    [buildDiscarder(
      logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '30', numToKeepStr: '10')
    ),
    [$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false],
    parameters([
        choiceParam(name: "TYPE", choices: "snapshot\nreleases", description: "Deployment type"),
        choiceParam(name: "ENVIRONMENT", choices: "development\nproduction\nlocal", description: "AWS Region within which to deploy Infrastructure"),
        string(name: "PIPELINE_BRANCH", defaultValue: "master", description: "")
    ]),

    pipelineTriggers([])
  ])

switch(params.ENVIRONMENT) {
    case "dev":
        ACCOUNT = "aaaaaaaaa"
        break
    case "prod":
        ACCOUNT = "bbbbbbbbb"
        break
    default:
        ACCOUNT = "ccccccccc"
        break
}

def sendSlackMessage(color, message) {
    // slackSend baseUrl: 'https://hooks.slack.com/services/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',
    //           color: color,
    //           message: "${message}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})"
}

node {
    timestamps {
        ansiColor('xterm') {
            dir("${env.WORKSPACE}") {
                deleteDir()
            }
            try {
                stage ('Checkout source') {
                    dir('pipeline') {
                        git([url: "https://github.com/tyoffeawscloud/spec",
                             branch: params.PIPELINE_BRANCH ?: 'master',
                             credentialsId: 'aws_github'])
                    }
                }
            } catch (e) {
                error "Cannot checkout repo..."
            }

            try {
                dir("${env.WORKSPACE}/pipeline/spec-master/docs") {
                    stage("run build sh") {
                        envFileContent = "API_CONSUMER_KEY=xxxxxx\nAPI_CONSUMER_SECRET=xxxx\nAPI_TOKEN=xxxxx\nAPI_TOKEN_SECRET=xxxx\nAPI_ENDPOINT=https://dev.xxxxx"
                        ["development", "production"].each {
                            writeFile file: "${env.WORKSPACE}/pipeline/spec-master/docs/.env.${it}", text: envFileContent
                        }

                        sendSlackMessage('#FFFF00', "STARTED")
                        output = sh "bash build.sh ${params.ENVIRONMENT} build"
                    }

                    if (output.contains("exited with code 1")) {
                        currentBuild.result = "FAILURE"
                        error "Build has failed..."
                    }

                    stage("Publish report") {
                        publishHTML([allowMissing: false,
                                     alwaysLinkToLastBuild: false,
                                     keepAll: false,
                                     reportDir: 'output',
                                     reportFiles: 'report.html',
                                     reportName: 'HTML Report',
                                     reportTitles: 'Report'])

                    }
                }
            } catch (e2) {
                    error "Build has failed..."
            } finally {
                if (currentBuild.result == "FAILURE" || currentBuild.currentResult == "FAILURE" ) {
                    sendSlackMessage('#FF0000', "FAILURE")
                } else if (currentBuild.result == "SUCCESS" || currentBuild.currentResult == "SUCCESS" ) {
                    sendSlackMessage('#00FF00', "SUCCESS")
                }
            }
        }
    }
}
