pipeline 
{
    agent any
    
    tools{
    	maven 'maven 3.9.8'
        }

    stages 
    {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }  
        }
      
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa")
            }
        }
                
        stage('Regression Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/Rekapost/Playwright_Framework'
                    sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testRunners/testng_regression.xml"
                    
                }
            }
        }
                
        stage('Publish Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: true, 
                                  reportDir: 'report', 
                                  reportFiles: 'TestExecutionReport.html', 
                                  reportName: 'HTML Extent Report', 
                                  reportTitles: ''])
            }
        }  
    }
}