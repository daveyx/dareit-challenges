# DareIT Challenge 1a - JDBC with external MYSQL database

## prerequisites
assure that you know the mysql connection data:
* server
* user
* password
* database (must exist in mysql)
* table (does not have to exist in mysql)

## how to compile and run with script
* `cd ???/scripts`
* `./compile.sh`
* `./run.sh`

## how to compile and run without script
* `cd ???/bin`
* `javac -d . ../src/com/dareit/*.java ../src/com/dareit/common/*.java ../src/com/dareit/mysql/*.java`
* `java -cp ../lib/mysql-connector-java-8.0.23.jar:. com.dareit.Main`
