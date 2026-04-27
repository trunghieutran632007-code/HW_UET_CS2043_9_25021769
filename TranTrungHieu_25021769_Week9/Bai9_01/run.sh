#!/bin/bash
# run.sh - Bai01: Quan ly Dependency voi Maven (dung Maven Wrapper)
# Yeu cau moi truong: JDK 17+ (khoa hoc dung JDK 25)
# Khong yeu cau cai Maven san - wrapper se tu download lan dau chay.
set -e

ROOT_DIR=$(pwd)
cd "$ROOT_DIR"

# Uu tien dung Maven Wrapper di kem project.
# Neu vi ly do nao do khong co wrapper, fallback ve mvn cua he thong.
if [ -f "./mvnw" ]; then
    chmod +x ./mvnw
    MVN_CMD="./mvnw"
    echo "==> Using Maven Wrapper"
else
    MVN_CMD="mvn"
    echo "==> Using system Maven (wrapper not found)"
fi

echo "==> $MVN_CMD clean compile"
$MVN_CMD -B -q clean compile

echo "==> $MVN_CMD exec:java"
$MVN_CMD -B -q exec:java