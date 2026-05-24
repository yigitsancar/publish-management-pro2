pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'yigitsancar/publish-management-pro2'
        IMAGE_TAG = "build-${BUILD_NUMBER}"
        K8S_DEPLOYMENT = 'publish-management-app'
        K8S_CONTAINER = 'publish-management-app'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Kod GitHub reposundan alındı.'
            }
        }

        stage('Gradle Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean build'
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USERNAME',
                    passwordVariable: 'DOCKER_PASSWORD'
                )]) {
                    sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin'
                }
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE:$IMAGE_TAG .'
            }
        }

        stage('Docker Push') {
            steps {
                sh 'docker push $DOCKER_IMAGE:$IMAGE_TAG'
            }
        }

        stage('Kubernetes Deploy') {
            steps {
                sh 'kubectl set image deployment/$K8S_DEPLOYMENT $K8S_CONTAINER=$DOCKER_IMAGE:$IMAGE_TAG'
                sh 'kubectl rollout status deployment/$K8S_DEPLOYMENT'
            }
        }
    }
}
