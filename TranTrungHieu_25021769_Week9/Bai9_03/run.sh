#!/usr/bin/env bash
# run.sh - Build & run Fibonacci solution voi Maven.
# Day la mot du an Maven (Bai 3 - CI/CD), nen yeu cau co Maven 3.9+ va JDK 25.

set -e

if ! command -v mvn >/dev/null 2>&1; then
    echo "Error: Maven (mvn) khong tim thay tren PATH." >&2
    echo "Bai 3 yeu cau Maven 3.9+. Vui long cai dat Maven roi chay lai ./run.sh" >&2
    exit 1
fi

ROOT_DIR=$(pwd)

echo ">>> mvn -DskipTests package"
mvn -q -DskipTests package

JAR_FILE=$(ls "$ROOT_DIR"/target/fibonacci-*.jar 2>/dev/null | head -n 1)
if [ -z "$JAR_FILE" ] || [ ! -f "$JAR_FILE" ]; then
    echo "Error: Khong tim thay file JAR sau khi build." >&2
    exit 1
fi

echo ">>> java -jar $JAR_FILE"
java -jar "$JAR_FILE"
