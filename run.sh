#!/usr/bin/bash

javac -cp mysql-connector-java-8.0.27.jar *.java
java -cp mysql-connector-java-8.0.27.jar: Banking
