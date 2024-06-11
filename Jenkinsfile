pipeline {
    agent any

    // this section configures Jenkins options
    options {

        // only keep 10 logs for no more than 10 days  
        // Es werden nur 10 Logs für höchstens 10 Tage beibehalten.
        buildDiscarder(logRotator(daysToKeepStr: '10', numToKeepStr: '10'))

        // add timestamps to the log
        timestamps()
    }

    // the pipeline section we all know and love: stages! :D
    stages {
        stage('Requirements') {
            steps {
                echo 'Installing requirements...'
            }
        }
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Report') {
            steps {
                echo 'Reporting....'
            }
        }
    }

    // the post section is a special collection of stages
    // that are run after all other stages have completed
    // Post-Abschnitt wird ausgeführt , nachdem alle anderen Stages abgeschlossen sind.
    post {

        // the always stage will always be run
        //  always wird immer ausgeführt.
        always {
            // a "steps{...}" section is not needed.
            echo "This step will run after all other steps have finished.  It will always run, even in the status of the build is not SUCCESS"
        }
       failure {
              echo 'failure ...'
       }
       success {
              echo ' yoohooo'
       }
    }
}

/////////////////////////////////////condition///////////////////////////////////////////////////
// pipeline {
//     agent any
//     stages {
//         stage('install') {
//             when {
//                 expression {
//                     env.BRANCH_NAME == 'dev' || env.BRANCH_NAME == 'master'
//                 }
//             }
//             steps {
//                 echo 'Installing requirements...'
//             }
//         }
//         stage('Build') {
//             steps {
//                 echo 'Building..'
//             }
//         }
//     }    
// }

///////////////////////////////////environment///////////////////////////////////////////////////
// pipeline {
//     agent any
//     environment {
//         TEST_SERVER = 'www.url.com'
//         BOOLEAN_VAR  = 'true' //boolean value as string
//     }
//     stages {
//         stage('install') {
//             when {
//             expression {
//                 env.BOOLEAN_VAR.toBoolean()
//                 }
//             }
//             steps {
//                 echo 'Installing requirements...'
//             }
//         }
//         stage('Build') {
//             steps {
//                 echo 'Building..'
//             }
//         }
//     }   
// }
////////////////////////////////credentials//////////////////////////////////////////////////////
// pipeline {
//     agent any
//     environment {
//         TEST_SERVER  = credentials('test-server')
//         TEST_SERVER_URL = 'www.url.com'
//     }
//     stages {
//         stage('install') {
//             steps {
//                 echo 'Installing requirements...'
//             }
//         }
//         stage('deploy') {
//             steps {
//                 echo 'deploying the app ...'
//                 // Jenkins always adds _USR and _PSW endings to the names of the variables.
//                 //sh "docker login '${TEST_SERVER_URL}' --password '${TEST_SERVER_PSW}' --username '${TEST_SERVER_USR}'"
//             }
//         }
       
//     }
// }
/////////////////////////////TOOLS//////////////////////////////////////////////////////
// pipeline {
//     agent any
//     tools {
//         nodejs "node-22.2"
//     }
//     stages {
//         stage('install') {
//             steps {
//                 sh 'npm -v'
//             }
//         }
//     }
// }
