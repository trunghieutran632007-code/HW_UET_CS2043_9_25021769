#!/bin/bash
ROOT_DIR=$(pwd)
SRC_DIR="$ROOT_DIR/src"
BUILD_DIR="$ROOT_DIR/build"
LIB_DIR="$ROOT_DIR/lib"

mkdir -p "$BUILD_DIR"

# Kiểm tra có thư viện không
if [ -d "$LIB_DIR" ] && [ "$(ls -A $LIB_DIR/*.jar 2>/dev/null)" ]; then
    CLASSPATH=$(find "$LIB_DIR" -name "*.jar" | tr '\n' ':')
    javac -d "$BUILD_DIR" -cp "$CLASSPATH" "$SRC_DIR"/*.java
    java -cp "$BUILD_DIR:$CLASSPATH" Bai3_04.src.Main
else
    javac -d "$BUILD_DIR" "$SRC_DIR"/*.java
    java -cp "$BUILD_DIR" Bai3_04.src.Main
fi
