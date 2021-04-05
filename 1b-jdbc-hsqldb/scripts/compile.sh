#!/bin/bash

cd ../bin

javac -d . -cp ../lib/hsqldb-2.5.1.jar:. ../src/com/dareit/*.java ../src/com/dareit/common/*.java ../src/com/dareit/hsqldb/*.java

exit 0
