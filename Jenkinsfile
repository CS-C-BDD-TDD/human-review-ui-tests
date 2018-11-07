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
    env:
    - name: MY_POD_IP
      valueFrom:
        fieldRef:
          fieldPath: status.podIP
  - name: jenkins-slave-zap
    image: docker-registry.default.svc:5000/labs-ci-cd/jenkins-slave-zap
    tty: true
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
        sh '/usr/local/bin/generate_container_user'
        sh 'rm -rf workdir'
        sh 'git clone https://github.com/CS-C-BDD-TDD/human-review-ui-tests.git workdir'
        dir('workdir') {
          // sh 'git checkout origin/DHS-106_-_Support_running_acceptance_tests_in_pipeline -b DHS-106_-_Support_running_acceptance_tests_in_pipeline'
          sh """/opt/rh/rh-maven33/root/usr/bin/mvn -Dhr.restapi.url=http://human-review-backend-labs-test.apps.domino.rht-labs.com/api/v1 \
                                                    -Dhr.website.url=http://vue-app-labs-test.apps.domino.rht-labs.com/ \
                                                    -Dtest=RunCukesTest \
                                                    -Dwebdriver.remote.driver=chrome \
                                                    -Dwebdriver.remote.os=LINUX \
                                                    -Dwebdriver.remote.url=http://zalenium-zalenium.apps.domino.rht-labs.com/wd/hub \
                                                    test"""
        }
      }
    }
  }
}