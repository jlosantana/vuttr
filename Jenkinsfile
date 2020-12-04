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
                sh "docker build -t ${docker_image} ."
            }
        }
        
        stage('Stop Docker Container') {
            steps {
                script {
                    try {
                        sh 'docker rm -f ${docker_container}'
                    } catch (e) {}
                }
            }
        }
        
        stage('Run Docker Container') {
            steps {
                sh 'docker run --name ${docker_container} -d -p 3000:3000 ${docker_image}'
            }
        }
        
    }
}
