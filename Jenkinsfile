pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Build1') {
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
