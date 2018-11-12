import groovy.json.JsonSlurper

pipeline {
  options {
    buildDiscarder(logRotator(numToKeepStr: '50'))
    timeout(time: 20, unit: 'MINUTES')
  }
  agent {
    label 'jenkins-slave-mvn'
  }
  stages {
    stage('Run Acceptance Tests') {
      steps {
        script {
          // Initialize container so that the random UID is defined in /etc/passwd
          sh '/usr/local/bin/generate_container_user'

          def localIP = sh(returnStdout: true, script: 'echo ${MY_POD_IP}').trim()

          // Wait to ensure that ZAProxy is up and available before starting tests
          // If ZAProxy is not up in 50 seconds, fail the build
          retry(10) {
            sleep 5
            sh 'curl -v http://localhost:8080/JSON/core/view/mode'
          }

          // Execute the maven command to run Selenium/Serenity tests using CI settings for
          // Jenkins/OpenShift environment
          def retVal = sh(returnStatus: true, script: "export ZAPROXY_HOST=${localIP}; ./run_tests.sh")

          // Capture the ZAProxy HTML report - MUST use OTHER prefix
          sh "curl -v -o /tmp/reports/zap-passive-report.html http://localhost:8080/OTHER/core/other/htmlreport"

          // Capture the ZAProxy JSON report - MUST use OTHER prefix

          def jsonReport = sh(returnStdout: true, script: 'curl http://localhost:8080/OTHER/core/other/jsonreport')

          // Shut down ZAProxy Daemon
          sh 'curl -v http://localhost:8080/OTHER/action/shutdown'

          // Parse JSON report data and fail build if appropriate
          def jsonData = new JsonSlurper().parseText(jsonReport)
          def highCriticalRisks = jsonData.site.each { site ->
            site.alerts.each { alert ->
              def alertValue = alert.riskcode as Integer
              if (alertValue >= 3) {
                error 'High/Critical Risks Detected By Zed Attack Proxy'
              }
            }
          }
          if (retVal != 0) {
            error 'Selenium Tests Failed'
          }
        }
      }
    }
  }
  post {
    failure {
      // Copy reports from shared volume to workspace so that Jenkins plugins can acces them
      sh 'cp -a /tmp/reports ./'
      sh 'ls reports/'
      emailext attachmentsPattern: 'reports/*-report.*', body: "Please see attached report", subject: 'Failed ZAP Scan', to: 'snayak@bcmcgroup.com; deven.phillips@redhat.com; ncho@bcmcgroup.com; john.johnson@hq.dhs.gov'
      publishHTML(target: [
          reportDir             : 'reports',
          reportFiles           : 'zap-passive-report.html',
          reportName            : 'ZAProxy Passive Report',
          keepAll               : true,
          alwaysLinkToLastBuild : true,
          allowMissing          : true
      ])
    }
    success {
      // Copy reports from shared volume to workspace so that Jenkins plugins can acces them
      sh 'cp -a /tmp/reports ./'
      sh 'ls reports/'
      emailext attachmentsPattern: 'reports/*-report.*', body: "Please see attached report", subject: 'ZAP Scan Results', to: 'snayak@bcmcgroup.com; deven.phillips@redhat.com; ncho@bcmcgroup.com; john.johnson@hq.dhs.gov'
      publishHTML(target: [
          reportDir             : 'reports',
          reportFiles           : 'zap-passive-report.html',
          reportName            : 'ZAProxy Passive Report',
          keepAll               : true,
          alwaysLinkToLastBuild : true,
          allowMissing          : true
      ])
    }
  }
}