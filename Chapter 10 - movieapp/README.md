# MovieApp
A simple movie application written in Java 21; intended to be used with the book "Code with Java 21" (BPB Publishing). This batch job reads movie data from its Apache Cassandra database, and shows it inside of its user interface.

This application requires that the `movies_metadata.csv` file (from Kaggle) has been loaded by the [MovieApp Batch Loader](https://github.com/aploetz/MovieAppLoader). This repository contains a modified version of this file, which has been shortened and had its line break errors fixed.

<img src="movieApp_CwJ21.png" width="200" align=right />

## Database

This application requires a running [Astra DB](https://astra.datastax.com) _vector_ database. A non-vector enabled database will not The "free tier" of Astra DB should provide more than enough resources to run the application. If the Astra database is "hibernated," it will need to be "resumed" before running the applicaiton.

_Note: As of this writing, only databases created on the Google Cloud Platform (GCP) provider qualify for Astra DB's free tier._

In the book "Code with Java 21," the keyspace is named "movieapp." Really, the keyspace can be named anything, but its name needs to be reflected in the **ASTRA_DB_KEYSPACE** environment variable.

Whatever the name of the keyspace, it should have the following Cassandra tables created within (and they should already contain data before running this application):

```sql
CREATE TABLE movies (
    movie_id INT PRIMARY KEY,
    imdb_id TEXT,
    original_language TEXT,
    genres MAP<INT,TEXT>,
    website TEXT,
    title TEXT,
    description TEXT,
    release_date DATE,
    year INT,
    budget BIGINT,
    revenue BIGINT,
    runtime float,
    movie_vector vector<float,7>
);

CREATE CUSTOM INDEX ON movieapp.movies (movie_vector) USING 'StorageAttachedIndex';

CREATE TABLE movies_by_title (
    title TEXT PRIMARY KEY,
    movie_id INT
);
```

A token from an appropriately-scoped role is required. As this application uses Spring Data underneath, the application role should be "Database Administrator." This is because Spring Data needs the ability to create tables, even if instructed not to do so.

The same token used with the MovieAppLoader should work here just fine.

## Environment:

This application requires the following environment variables to be set:

 - **ASTRA_DB_KEYSPACE** - The database keyspace containing the tables required by the Movie Application.
 - **ASTRA_DB_REGION** - The cloud region of the database.
 - **ASTRA_DB_ID** - The identifier of the database.
 - **ASTRA_DB_APP_TOKEN** - The token obtained from Astra DB.

In a Mac/Linux environment, the environment varaibles can be set from a terminal like this (example):

```
export ASTRA_DB_KEYSPACE=movieapp
export ASTRA_DB_REGION=us-east1
export ASTRA_DB_ID=f1111111-1111-4111-1111-111111111111
export ASTRA_DB_APP_TOKEN=AstraCS:BxinhBlahBlahBlahFHqKZw:d6532818082NotARealTokenc18d40
```

In a Windows environment, the environment variables can be set from the commmand line like this:

```
set ASTRA_DB_KEYSPACE=movieapp
set ASTRA_DB_REGION=us-east1
set ASTRA_DB_ID=f1111111-1111-4111-1111-111111111111
set ASTRA_DB_APP_TOKEN=AstraCS:BxinhBlahBlahBlahFHqKZw:d6532818082NotARealTokenc18d40
```

## To build:

### Build Requirements

 - Java 21 (JDK)
 - Maven

### Build command

    mvn clean install

### Running the build

    mvn spring-boot:run

## Usage:

Movies may be queried based on their Movie ID inside the CSV file, or by their _exact_ (case-insensitive) title. Images may also be added, but NO IMAGES ARE INCLUDED with the application, except for the [noImage.png](images/noImage.png) file.
