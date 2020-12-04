pipeline {
    agent any

    stages {
        
        stage('Build') {
            steps {
                sh "mvn -DskipTests clean package"
            }
        }
        
        stage('Tests') {
            steps {
                sh "mvn test"
            }
        }
        
        stage('Build Docker Image') {
            steps {
                sh "docker build -t jlosantana/vuttr-dev ."
            }
        }
        
        stage('Stop Docker Container') {
            steps {
                script {
                    try {
                        sh 'docker rm -f vuttr-dev'
                    } catch (e) {}
                }
            }
        }
        
        stage('Run Docker Container') {
            steps {
                sh 'docker run --name vuttr-dev -d -p 3000:3000 jlosantana/vuttr-dev'
            }
        }
        
    }
}
