# DareIT Challenge 1 - JDBC
#### with external MYSQL database

## prerequisites
assure that you know the mysql connection data:
* server
* user
* password
* database (must exist in mysql)
* table (does not have to exist in mysql)

## how to compile and run
* `cd ???/dareit???/bin`
* `javac -d . ../src/com/dareit/*.java ../src/com/dareit/persistence/*.java`
* `java -cp ../lib/mysql-connector-java-8.0.23.jar:. com.dareit.Main`
