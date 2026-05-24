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
                echo 'Source code is already checked out by Jenkins...'
            }
        }

        stage('Gradle Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean build'
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
