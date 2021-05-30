#!/bin/bash

BASEDIR=$(realpath "$(dirname "$(readlink -f "$0")")")
cd "$BASEDIR"/../bin
rm -rf ./*

javac -d . ../src/com/dareit/*.java ../src/com/dareit/common/*.java ../src/com/dareit/mysql/*.java

exit 0
