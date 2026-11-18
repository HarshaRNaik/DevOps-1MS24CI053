pipeline {
    agent any

    tools {
        jdk 'JDK-21'
        maven 'Maven-3.9.11'
    }

    triggers {
        pollSCM('H/5 * * * *')
    }

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Test') {
            steps {
                sh 'mvn -B test'
            }
            post {
                always {
                    junit testResults: 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn -B -DskipTests package'
                script {
                    def commit = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
                    sh "cp target/quote-api.jar target/quote-api-${commit}.jar"
                }
            }
        }

        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'target/quote-api*.jar', fingerprint: true
            }
        }
    }
}
