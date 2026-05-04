#!/usr/bin/env bash
set -e

ROOT_DIR=$(pwd)
SRC_DIR="$ROOT_DIR/src"
LIB_DIR="$ROOT_DIR/lib"
BUILD_DIR="$ROOT_DIR/build"

mkdir -p "$BUILD_DIR"

# Copy logback.xml vao build/ de Logback tim thay tren classpath
cp "$SRC_DIR/logback.xml" "$BUILD_DIR/"

# Bien dich: classpath bao gom toan bo JAR trong lib/
javac -d "$BUILD_DIR" -cp "$LIB_DIR/*" "$SRC_DIR"/*.java

# Chay: classpath = build + lib (dung ':' tren Linux/Mac/Git Bash)
java -cp "$BUILD_DIR:$LIB_DIR/*" Main
