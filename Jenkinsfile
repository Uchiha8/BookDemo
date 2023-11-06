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
        stage('Test') {
            steps { bat 'mvn test' }
        }
        stage('Build') {
            steps { bat 'mvn clean install' }
        }
        stage('Code Coverage') {
            steps {
                bat "mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install -Pcoverage-per-test"
            }
        }
       stage('Scan') {
            steps {
                withSonarQubeEnv(installationName: 'sonarqube13'){
                    bat 'mvn clean sonar:sonar'
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

