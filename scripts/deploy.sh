#!/bin/env bash
mvn clean package -DskipTests &&
  target/blog-0.0.1-SNAPSHOT.war root@138.68.79.111:/opt/tomcat/webapps/blog.war
