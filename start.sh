#!/bin/sh
export pjodd_http_port="8081"
export pjodd_data_path="/tmp/pjodd/node-service"
export pjodd_mozilla_location_api_key="test"
export MAVEN_OPTS="-Xmx1g"
mvn exec:java