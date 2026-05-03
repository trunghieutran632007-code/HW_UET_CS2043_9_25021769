#!/bin/bash
# Bai 5: Test Coverage & Quality Enforcement (JaCoCo)
# Chay: mvn verify => compile, test, jacoco:report, jacoco:check

cd "$(dirname "$0")"

echo "=== Building and running tests with JaCoCo coverage ==="
mvn verify --batch-mode --no-transfer-progress

if [ $? -eq 0 ]; then
    echo ""
    echo "=== BUILD SUCCESS ==="
    echo "Coverage report: target/site/jacoco/index.html"
else
    echo ""
    echo "=== BUILD FAILED ==="
    echo "Coverage may be below 80%. Check report: target/site/jacoco/index.html"
    exit 1
fi
