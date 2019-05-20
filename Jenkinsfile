pipeline {   
	agent {     
		label 'Slave_Induccion'   
	} 
   
	options { 
		buildDiscarder(logRotator(numToKeepStr: '3')) 
		disableConcurrentBuilds()   
	} 
   
   	tools {     
		jdk 'JDK8_Centos' 
		gradle 'Gradle4.5_Centos' 
	} 
   
	stages{     
		stage('Checkout') {       
			steps{         
				echo "------------>Checkout<------------"   
				checkout([$class: 'GitSCM', branches: [[name: '*/master']],
 				doGenerateSubmoduleConfigurations: false, extensions: [], gitTool:
 				'Git_Centos', submoduleCfg: [], userRemoteConfigs: [[credentialsId:
 				'GitHub_myanza', url:'https://github.com/myanza/repo_ceiba_estacionamiento']]])    
			}     
		}       
		
		stage('Unit Tests') {       
			steps{         
				echo "------------>Test unitarios<------------" 
				sh 'gradle --b ./build.gradle clean test'
			}     
		}
		
		stage('Integration Tests') {
			steps {         
				echo "------------>Test de Integracion<------------"       
			}     
		} 
     
		stage('Static Code Analysis') {       
			steps{         
				echo '------------>Analisis de codigo estatico<------------'         
				withSonarQubeEnv('Sonar') { 
					sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties" 
				}       
			}     
		}
		
		stage('Build') {       
			steps {         
				echo "------------>Build<------------"
				//Construir sin tarea test que se ejecut� previamente   
				sh 'gradle --b ./build.gradle build -x test' 				
			}     
		}    
	}
	
	post {     
		always {       
			echo 'This will always run'     
		}     
		
		success {       
			echo 'This will run only if successful' 
			junit '**/jacoco/test-results/test/*.xml'			
		}     
		
		failure {
 			echo 'This will run only if failed'
 			mail (to: 'nancy.yanza@ceiba.com.co',subject:"Failed Pipeline:${currentBuild.fullDisplayName}",body:"Algo paso con ${env.BUILD_URL}")
  
 		}    
		
		unstable {       
			echo 'This will run only if the run was marked as unstable'     
		}     
		
		changed {       
			echo 'This will run only if the state of the Pipeline has changed'       
			echo 'For example, if the Pipeline was previously failing but is now successful'     
		}   
	} 
	
} 