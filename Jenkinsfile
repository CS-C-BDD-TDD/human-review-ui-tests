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

          // Execute the maven command to run Selenium/Serenity tests using CI settings for
          // Jenkins/OpenShift environment
          def retVal = sh(returnStatus: true, script: "./run_tests.sh")
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