# week30-java-ETL
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
This project is simple ETL in Java on how to insert data from CSV to Postgresql
	
## Technologies
Project is created with:
* Java version: 15.0.2
* Postgresql: 13.3

	
## Setup
To run this project
1. Buat docker Posgresql 
```
$ docker run -p 127.0.0.1:5432:5432 --name digitalskola_postgres -e POSTGRES_PASSWORD=digitalskola -it postgres
```

2. Masuk ke console docker dan buat database skola
```
$ docker exec -it digitalskola_postgres /bin/bash
# psql -U postgres
postgres=# CREATE DATABASE skola;
```
3. Buat table "ddk_tingkat_pendidikan" via skrip CreateDatabaseViaJava.java
4. Eksekusi pembacaan CSV dan proses insersi ke database via skrip csvETLDatabase.java
5. Done
