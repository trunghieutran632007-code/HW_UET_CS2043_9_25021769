#!/usr/bin/env bash
# Bien dich va chay BankSystem.
# Quy trinh:
#  1. Tai SLF4J + Logback ve thu muc lib/ neu thieu.
#  2. javac -> build/.
#  3. Sao chep logback.xml vao classpath.
#  4. java -cp <build:lib/*> Main.

set -e

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"
SRC_DIR="$ROOT_DIR/src"
LIB_DIR="$ROOT_DIR/lib"
BUILD_DIR="$ROOT_DIR/build"

# Phan tach classpath: ':' tren Unix, ';' tren Windows (Git Bash)
SEP=":"
case "$(uname -s 2>/dev/null)" in
  MINGW*|MSYS*|CYGWIN*) SEP=";" ;;
esac

mkdir -p "$BUILD_DIR" "$LIB_DIR"

# Tai dependency neu thieu
download_jar() {
  local url="$1"
  local out="$2"
  if [ ! -f "$out" ]; then
    echo "[run.sh] Downloading $(basename "$out")..."
    if command -v curl >/dev/null 2>&1; then
      curl -fsSL -o "$out" "$url"
    elif command -v wget >/dev/null 2>&1; then
      wget -q -O "$out" "$url"
    else
      echo "[run.sh] ERROR: can dat curl hoac wget de tai dependency." >&2
      exit 1
    fi
  fi
}

REPO="https://repo1.maven.org/maven2"
download_jar "$REPO/org/slf4j/slf4j-api/2.0.13/slf4j-api-2.0.13.jar" \
  "$LIB_DIR/slf4j-api-2.0.13.jar"
download_jar "$REPO/ch/qos/logback/logback-core/1.5.6/logback-core-1.5.6.jar" \
  "$LIB_DIR/logback-core-1.5.6.jar"
download_jar "$REPO/ch/qos/logback/logback-classic/1.5.6/logback-classic-1.5.6.jar" \
  "$LIB_DIR/logback-classic-1.5.6.jar"

# Build classpath tu lib/
CP="$BUILD_DIR"
for jar in "$LIB_DIR"/*.jar; do
  [ -f "$jar" ] && CP="$CP$SEP$jar"
done

# Bien dich
javac -d "$BUILD_DIR" -cp "$CP" "$SRC_DIR"/*.java

# Sao chep logback.xml de Logback nhan duoc cau hinh
if [ -f "$SRC_DIR/logback.xml" ]; then
  cp "$SRC_DIR/logback.xml" "$BUILD_DIR/"
fi

# Chay
java -cp "$CP" Main
