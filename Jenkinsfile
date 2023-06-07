pipeline {
    agent any
    stages {
        stage ('Build Backend') {
            steps {
                bat 'mvn clean package -DskipTests=true'
            }
        }
        stage ('Unit Tests') {
            steps {
                bat 'mvn test'
            }
        }
        /*stage ('Sonar Analysis') {
            enviroment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
                bat "${scannerHome}/bin/sonar-scanner -e"
            }
        }*/
        stage ('Deploy Frontend') {
            steps {
                dir('frontend') {
                    git credentialsId: 'github_login', url: 'https://github.com/linconviana/jenkins-tasks-frontend']
                    bat 'mvn clean package'
                    deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks', war: 'target/tasks.war'
                }
            }
        }
    }
}