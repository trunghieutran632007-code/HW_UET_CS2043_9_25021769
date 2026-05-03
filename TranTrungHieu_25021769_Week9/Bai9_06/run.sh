#!/usr/bin/env bash
#
# Script chay bai Bai9_06 - CI/CD Pipeline Optimization & Caching.
# Build bang Maven, sau do chay class Main.
#
set -e

ROOT_DIR=$(pwd)
echo "=== Bai9_06: building & running ==="
echo "Working dir: $ROOT_DIR"

# Build (compile + test + package)
mvn -B -q clean package

# Chay JAR vua build
java -jar "$ROOT_DIR/target/bai9-06-cache-demo.jar"
