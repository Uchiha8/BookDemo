pipeline {
    agent any
    tools {
    maven '3.9.5'
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
                bat 'mvn clean test'  // Run tests to generate coverage data
                bat 'mvn jacoco:report'
            }
        }
       stage('Scan') {
            steps {
                withSonarQubeEnv(installationName: 'sonarqube13'){
                    // bat 'mvn org.sonar-source.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
                    bat 'mvn clean verify sonar:sonar'
                }
            }
        }
         stage('Archive Artifacts') {
            steps {
                 archiveArtifacts artifacts: '**/*.war', followSymlinks: false
             }
         }
        stage('Deploy') {
            steps {
             deploy adapters: [tomcat9(credentialsId: '87807d89-b33d-4830-a63c-f71d4be61f08', path: '', url: 'http://localhost:8181')], contextPath: 'BookDemo', war: '**/*war'
            }
        }
    }
}

