#!/usr/bin/env bash
mvn \
    -Dhr.restapi.url=${REST_API_URL} \
    -Dhr.website.url=${FRONTEND_URL} \
    -Dhr.regular.username=${REGULAR_USERNAME} \
    -Dhr.regular.password=${REGULAR_PASSWORD} \
    -Dtest=RunCukesTest \
    -Dwebdriver.timeouts.implicitlywait=15000 \
    -Dcukes.config.file=config.properties \
    -Dchrome.switches=\"--proxy-server=http:$1:8080\" \
    -Dwebdriver.remote.driver=chrome \
    -Dwebdriver.remote.url=http://selenium-hub:4444/wd/hub \
    clean test


    # -Dchrome.switches=\"--proxy-server=$1:8080\" \