#!/usr/bin/env bash
set -e

echo "==> [1/2] Building with Maven (mvn clean package)..."
mvn -q clean package

echo ""
echo "==> [2/2] Running packaged JAR..."
java -jar target/bai9-08.jar
