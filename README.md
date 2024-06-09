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
