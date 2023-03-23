pipeline {
    agent any
    tools {
        nodejs "node-18.14.0"
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                bat " npm i" 
                bat " npm run cypress:run "
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
