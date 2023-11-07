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
              deploy adapters: [tomcat9(credentialsId: '50b85213-cbca-4fd5-bceb-6c0aa734ea8b', path: '', url: 'http://localhost:8181')], contextPath: 'Book demo', war: '**/*war'
            }
        }
    }
}

