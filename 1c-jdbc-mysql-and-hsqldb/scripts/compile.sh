#!/bin/bash

BASEDIR=$(realpath "$(dirname "$(readlink -f "$0")")")
cd "$BASEDIR"/../bin
rm -rf ./*

javac -d . -cp ../lib/dareit.jar:../../1a-jdbc-mysql/lib/mysql-connector-java-8.0.23.jar:../../1b-jdbc-hsqldb/lib/hsqldb-2.5.1.jar ../src/com/dareit/*.java

exit 0