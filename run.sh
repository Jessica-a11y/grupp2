l#!/bin/bash
set -e

echo "Cleaning previous builds..."
mvn clean

echo "Packaging the project..."
mvn package

echo "Running tests..."
mvn test

# Find the JAR file in ./target
JAR_FILE=$(find ./target -type f -name "*.jar" | head -n 1)

if [[ ! -f "$JAR_FILE" ]]; then
    echo "No JAR file found in ./target/"
    exit 1
fi

echo "JAR file created: $JAR_FILE"

echo "Now running the code..."
mvn exec:java -Dexec.mainClass="se.yrgo.client.Client"



