# jenkins
<details>

<summary>Intro</summary>

### You can add a header

| First Header  | Second Header |
| ------------- | ------------- |
| Content Cell  | Content Cell  |
| Content Cell  | Content Cell  |

You can add an image or a code block, too.

```
pipeline {
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
}
```

</details>
---------

