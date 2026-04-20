#!/bin/bash
ROOT_DIR=$(pwd)
SRC_DIR="$ROOT_DIR/src"
BUILD_DIR="$ROOT_DIR/build"
LIB_DIR="$ROOT_DIR/lib"
# Cap nhat ten package hien tai
MAIN_CLASS="Bai8_09.src.Main"
mkdir -p "$BUILD_DIR"
if [ -d "$LIB_DIR" ] && [ "$(ls -A $LIB_DIR/*.jar 2>/dev/null)" ]; then
    # Gop tat ca file .jar thanh chuoi Classpath
    CLASSPATH=$(find "$LIB_DIR" -name "*.jar" | tr '\n' ':')

    echo "Bien dich voi thu vien..."
    javac -d "$BUILD_DIR" -cp "$CLASSPATH" "$SRC_DIR"/*.java

    # Kiem tra xem trong Classpath co chua chu "junit" khong
    if [[ "$CLASSPATH" == *"junit"* ]]; then
        echo "Phat hien thu vien JUnit. Dang chay Test..."
        # Tim duong dan chinh xac toi file jar cua JUnit de chay
        JUNIT_JAR=$(find "$LIB_DIR" -name "junit-platform-console-standalone*.jar" | head -n 1)
        java -jar "$JUNIT_JAR" --class-path "$BUILD_DIR" --scan-class-path
    else
        echo "Chay chuong trinh chinh (co thu vien khac)..."
        java -cp "$BUILD_DIR:$CLASSPATH" $MAIN_CLASS
    fi
else
    echo "Bien dich khong co thu vien..."
    javac -d "$BUILD_DIR" "$SRC_DIR"/*.java
    echo "Chay chuong trinh chinh..."
    java -cp "$BUILD_DIR" $MAIN_CLASS
fi