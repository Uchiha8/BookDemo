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
                    bat 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
                }
            }
        }
        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'target/*.war', followSymlinks: false
            }
        }
        stage('Deploy') {
            steps {
             deploy adapters: [tomcat9(credentialsId: '87807d89-b33d-4830-a63c-f71d4be61f08', path: '', url: 'http://localhost:8181')], contextPath: 'BookDemo', war: '**/*war'
            }
        }
    }
}

