#!/bin/bash
set -e

echo "🔄 Rensar tidigare byggen..."
mvn clean

echo "📦 Paketerar projektet..."
mvn package

echo "🧪 Kör tester..."
mvn test

# Leta upp JAR-filen i ./target
JAR_FILE=$(find ./target -type f -name "*.jar" | head -n 1)

if [[ ! -f "$JAR_FILE" ]]; then
    echo "❌ Ingen JAR-fil hittades i ./target/"
    exit 1
fi

echo "✅ JAR-fil skapad: $JAR_FILE"


