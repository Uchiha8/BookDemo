pipeline {
    agent any
    tools {
    maven 'maven'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
                    steps { bat 'mvn clean install' }
                }
        stage('Test') {
            steps { bat 'mvn test' }
        }
        stage('Code Analysis') {
            steps {
                withSonarQubeEnv('sonarqube13') {
                    bat 'mvn sonar:sonar'
                }
            }
        }
    }

    post {
        always {
            step([$class: 'JacocoPublisher', changeBuildStatus: true, execPattern: '**/target/jacoco.exec'])
        }
    }
}

