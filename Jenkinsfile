pipeline {
  agent {
    kubernetes {
      label 'sidecars1'
      defaultContainer 'jenkins-slave-mvn'
      idleMinutes 60
      activeDeadlineSeconds 3600
      yaml """
apiVersion: v1
kind: Pod
metadata:
  labels:
    pod-template: jenkins-slave-sidecars
spec:
  containers:
  - name: jenkins-slave-mvn
    image: docker-registry.default.svc:5000/labs-ci-cd/jenkins-slave-mvn
    tty: true
    command:
    - cat
    env:
    - name: MY_POD_IP
      valueFrom:
        fieldRef:
          fieldPath: status.podIP
  - name: jenkins-slave-zap
    image: docker-registry.default.svc:5000/labs-ci-cd/jenkins-slave-zap
    tty: true
    command:
    - cat
    env:
    - name: MY_POD_IP
      valueFrom:
        fieldRef:
          fieldPath: status.podIP
"""
    }
  }
  environment {
    POD_IP = ''
    ZAP_BASELINE_SCAN_OUTPUT = ''
  }
  stages {
    stage('Get Pod IP') {
      steps {
        container('jenkins-slave-mvn') {
          script {
            env.POD_IP = sh(returnStdout: true, script: 'echo "${MY_POD_IP}"').trim()
          }
        }
      }
    }
    stage('Run Acceptance Tests') {
      steps {
      	script {
          sh '/usr/local/bin/generate_container_user'
          def retVal = sh(returnStatus: true, script: 'opt/rh/rh-maven33/root/usr/bin/mvn \
                    -Dhr.restapi.url=http://human-review-backend-labs-test.apps.domino.rht-labs.com/api/v1 \
                    -Dhr.website.url=http://vue-app-labs-test.apps.domino.rht-labs.com/ \
                    -Dhr.regular.username=User1 \
                    -Dhr.regular.password=Pass1 \
                    -Dtest=RunCukesTest \
                    -Dwebdriver.timeouts.implicitlywait=5000 \
                    -Dcukes.config.file=config.properties \
                    -Dwebdriver.remote.driver=chrome \
                    -Dwebdriver.remote.url=http://zalenium:zalenium1234@zalenium-zalenium.apps.domino.rht-labs.com/wd/hub \
                    clean test')
          sh """/opt/rh/rh-maven33/root/usr/bin/mvn -DskipTests verify"""
          publishHTML(target: [
                      	reportDir             : 'target/site/serenity',
                        reportFiles           : 'index.html',
                        reportName            : 'AA Test Report',
                        keepAll               : true,
                        alwaysLinkToLastBuild : true,
                        allowMissing          : true
          ])
          
      	}

      }
    }
    stage('Run ZAProxy Baseline Spider Scan') {
      steps {
        container('jenkins-slave-zap') {
          script {
            sh 'mkdir -p zaproxy_reports'
            sh 'touch zaproxy_reports/console_output.txt'
            def retVal = sh(returnStatus: true, script: 'zap-baseline.py -m 1 -r zaproxy_reports/zaproxy-baseline-report.html -t http://vue-app-labs-test.apps.domino.rht-labs.com/ > zaproxy_reports/console_output.txt')
            if (retVal != 0) {
              def output = readFile('zaproxy_reports/console_output.txt').trim()
              emailext body: "${output}", subject: 'Failed ZAP Scan', to: 'snayak@bcmcgroup.com; ncho@bcmcgroup.com'
              error 'ZAProxy Scan Failure'
            }

            emailext attachmentsPattern: '**/*-report.html', body: "Please see attached report", subject: 'ZAP Baseline Scan Report', to: 'snayak@bcmcgroup.com; ncho@bcmcgroup.com'
          }
        }
      }
    }
  }
}