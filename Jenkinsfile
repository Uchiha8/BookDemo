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
        stage('Sonar Analysis') {
            environment {
                scannerHome = tool "sonar-scanner"
            }

            steps {
                withSonarQubeEnv("sonarqube13") {
                    bat 'mvn clean verify sonar:sonar -Dsonar.projectKey=book-demo -Dsonar.projectName='book demo' -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_3282e21e90fbfb3488b48c9b7ac1e42a9ad15a24'
                }
            }
        }
        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'target/*.war', followSymlinks: false
            }
        }
        // stage('Deploy') {
        //     steps {
        //         deploy adapters: [tomcat10(credentialsId: 'tomcat.admin', path: '', url: 'http://localhost:8181/')], contextPath: 'book demo', onFailure: false, war: '**/*.war'
        //     }
        // }
    }

    post {
        always {
            step([$class: 'JacocoPublisher', changeBuildStatus: true, execPattern: '**/target/jacoco.exec'])
        }
    }
}

