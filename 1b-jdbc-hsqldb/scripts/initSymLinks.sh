#!/bin/bash

cd ../src
rm -rf "com/dareit/common"
ln -s "../../../../1a-jdbc-mysql/src/com/dareit/common" "com/dareit/common"

exit 0
