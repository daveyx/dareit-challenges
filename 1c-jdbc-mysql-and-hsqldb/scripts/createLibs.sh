#!/bin/bash

BASEDIR=$(realpath "$(dirname "$(readlink -f "$0")")")
cd "$BASEDIR"

../../1a-jdbc-mysql/scripts/compile.sh
../../1b-jdbc-hsqldb/scripts/compile.sh

jar cf ../lib/dareit.jar -C ../../1a-jdbc-mysql/bin/ com
jar uf ../lib/dareit.jar -C ../../1b-jdbc-hsqldb/bin/ com
zip -d ../lib/dareit.jar com/dareit/Main.class
