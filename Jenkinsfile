pipeline {
    agent any
     stages {
        stage('Build') {
            steps {
                echo 'Building....'
                sh 'mvn clean'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'mvn compile'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                sh 'mvn test'
                
            }
        }
    }
}
