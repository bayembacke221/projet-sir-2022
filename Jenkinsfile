pipeline{
    agent any
    tools{
        maven '3.8.6'
    }
    stages{
        stage('Source') {
            steps{
                git branch: 'main', url: 'https://github.com/bayembacke221/projet-sir-2022.git'
            }
        }
        stage ('Build') {
            steps{
                bat 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install'
            }
        }
        stage ('SonarQube Analysis') {
            steps{
                bat 'mvn sonar:sonar'
            }
        }

    } // stages



}
