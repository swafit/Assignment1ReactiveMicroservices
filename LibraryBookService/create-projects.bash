#!/usr/bin/env bash
spring init \
--boot-version=2.6.3.RELEASE \
--build=gradle \
--java-version=1.8 \
--packaging=jar \
--name=book-service \
--package-name=com.assignment1.book \
--groupId=com.assignment1.book \
--dependencies=web \
--version=1.0.0-SNAPSHOT \
book-service\

spring init \
--boot-version=2.6.3.RELEASE \
--build=gradle \
--java-version=1.8 \
--packaging=jar \
--name=api-gateway \
--package-name=com.assignment1.apigateway \
--groupId=com.assignment1.apigateway \
--dependencies=web \
--version=1.0.0-SNAPSHOT \
api-gateway

