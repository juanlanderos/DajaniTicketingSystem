pipeline {
    agent any
    stages {
        stage('compile') {
	   steps {
                echo 'compiling..'
		git 'https://github.com/juanlanderos/DajaniTicketingSystem'
		sh script: '/opt/maven/bin/mvn compile'
           }
    	}
    }
}
