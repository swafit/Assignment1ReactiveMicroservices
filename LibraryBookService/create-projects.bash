#!/usr/bin/env bash
spring init \
--boot-version=2.7.3.RELEASE \
--build=gradle \
--java-version=1.8 \
--packaging=jar \
--name=employee-service \
--package-name=com.assignment1.employee \
--groupId=com.assignment1.employee \
--dependencies=web \
--version=1.0.0-SNAPSHOT \
employee-service\
