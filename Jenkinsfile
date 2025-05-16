pipeline {
    agent { label 'agent1' }

    tools {
        git 'Default'
    }

    environment {
        DOTENV_FILE = "/var/agent-jdk17/env/.env.develop"
    }

    stages {
        stage('Prepare Environment') {
            steps {
                script {
                    sh 'chmod +x ./gradlew'
                }
            }
        }
        stage('Checkstyle Main') {
            steps {
                script {
                    sh './gradlew checkstyleMain -P"dotenv.filename=$DOTENV_FILE"'
                }
            }
        }
        stage('Checkstyle Test') {
            steps {
                script {
                    sh './gradlew checkstyleTest -P"dotenv.filename=$DOTENV_FILE"'
                }
            }
        }
        stage('Compile') {
            steps {
                script {
                    sh './gradlew compileJava -P"dotenv.filename=$DOTENV_FILE"'
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    sh './gradlew test -P"dotenv.filename=$DOTENV_FILE"'
                }
            }
        }
        stage('JaCoCo Report') {
            steps {
                script {
                    sh './gradlew jacocoTestReport -P"dotenv.filename=$DOTENV_FILE"'
                }
            }
        }
        stage('JaCoCo Verification') {
            steps {
                script {
                    sh './gradlew jacocoTestCoverageVerification -P"dotenv.filename=$DOTENV_FILE"'
                }
            }
        }
        stage('Update DB') {
            steps {
                script {
                    sh './gradlew update -P"dotenv.filename=$DOTENV_FILE"'
                }
            }
        }
    }

    post {
        always {
            script {
                def buildInfo = "Build number: ${currentBuild.number}\n" +
                                "Build status: ${currentBuild.currentResult}\n" +
                                "Started at: ${new Date(currentBuild.startTimeInMillis)}\n" +
                                "Duration so far: ${currentBuild.durationString}"
                telegramSend(message: buildInfo)
            }
        }
    }
}