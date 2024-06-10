# jenkins
<details>

<summary>Intro</summary>

### Pipeline Syntax

| Scripted  | Declarative |
| ------------- | ------------- |
| First syntax  | recent addition |
| groovy engine  | easier to get started, but not that powerful  |
| advanced scripting, high flexibility  | pre-defined structure |
| ``` node {}```  | ``` pipeline {agent any stages {...}}```|

You can add an image or a code block, too.

```
pipeline {
    // where to execute 
    agent any
    // the pipeline section we all know and love: stages! :D
    stages {
        // CI: Focuses on integrating code changes and ensuring the build and test processes are automated.
        stage('install') {
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
        // CD: Ensure that the code is always in a deployable state and reduce the time to release features to users.
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
    post {
        always {
            echo 'Done'
        }
        success {
            // example: send msg to microsoft teams
        }
        failure {
            // example: send msg to microsoft teams
        }
    }
}
```

</details>
<details>

<summary>Define conditions</summary>

```
pipeline {
    agent any
    stages {
        stage('install') {
            when {
                expression {
                    BRANCH_NAME == 'dev' ||  BRANCH_NAME == 'master'
                }
            }
            steps {
                echo 'Installing requirements...'
            }
        }
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
       
}
```

</details>

<details>

<summary>Enviromental Variables</summary>

find all pre-defined vars in jenkinsurl + `/env-vars.html` 
```
pipeline {
    agent any
    stages {
        stage('install') {
            when {
                expression {
                    BRANCH_NAME == 'dev' ||  BRANCH_NAME == 'master'
                }
            }
            steps {
                echo 'Installing requirements...'
            }
        }
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
       
}
```
```
pipeline {
    agent any
    environment {
        TEST_SERVER = 'www.url.com'
        BOOLEAN_VAR  = 'true' //boolean value as string
    }
    stages {
        stage('install') {
            when {
            expression {
                !env.BOOLEAN_VAR.toBoolean()
                }
            }
            steps {
                echo 'Installing requirements...'
            }
        }
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
       
}
```
</details>
<details>

<summary>Credentials in Jenkinsfile</summary>
Variant 1 : using `withCredentials` in each seperate stage

```
pipeline {
    agent any
    stages {
        stage('install') {
            steps {
                echo 'Installing requirements...'
            }
        }
        stage('deploy') {
            steps {
                echo 'deploying the app ...'
                // usernamePassword is the type of credential that I created in jenkins server
                withCredentials([usernamePassword(credentialsId: test-server, usernameVariable: 'USER', passwordVariable: 'PASSWORD')]) {
                    def url = 'www.url.com'
                    echo "${USER}"
                    // sh "docker login  url -u USER  -p PASSWORD
                }
            }
        }
       
    }
}
```
variant 2:
using `environment` 

```
pipeline {
    agent any
    environment {
        TEST_SERVER  = credentials('test-server')
        TEST_SERVER_URL = 'www.url.com'
    }
    stages {
        stage('install') {
            steps {
                echo 'Installing requirements...'
            }
        }
        stage('deploy') {
            steps {
                echo 'deploying the app ...'
                // Jenkins always adds _USR and _PSW endings to the names of the variables.
                sh "docker login '${TEST_SERVER_URL}' --password '${TEST_SERVER_PSW}' --username '${TEST_SERVER_USR}'"
            }
        }
       
    }
}
```
</details>
<details>

<summary>Others</summary>
Command to check Jenkins version
`java -jar /usr/share/java/jenkins.war --version`

Link to how install Jenkins server on aws
`https://d1.awsstatic.com/Projects/P5505030/aws-project_Jenkins-build-server.pdf`


</details>
