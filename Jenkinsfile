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
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Code Analysis') {
            steps {
                // Configure SonarQube Scanner (you must install it on your Jenkins server)
                withSonarQubeEnv('sonarqube13') {
                    // Run SonarQube analysis
                    sh 'mvn sonar:sonar'
                }
            }
        }
    }

    // post {
    //     success {
    //         // Archive and publish JaCoCo code coverage reports
    //         step([$class: 'JacocoPublisher', changeBuildStatus: true, execPattern: '**/target/jacoco.exec'])

    //         // Additional actions to perform on build success
    //     }
    //     failure {
    //         // Actions to perform on build failure
    //     }
    // }
}

