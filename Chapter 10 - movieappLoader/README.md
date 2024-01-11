# MovieApp Data Loader
A simple batch application written in Java 21; intended to be used with the book "Code with Java 21" (BPB Publishing). This batch job reads the [movies_metadata.csv](https://www.kaggle.com/datasets/rounakbanik/the-movies-dataset?select=movies_metadata.csv) file, and writes the processed data to an Apache Cassandra database.

As this `movies_metadata.csv` file does contain some line break characters which cause the ingest process to halt on error, it is recommended to use the [movies_metadata.csv](data/movies_metadata.csv) file located in the `data/` directory of this repository. This file is smaller, and has had the line break errors fixed.

## Database

This application requires a running [Astra DB](https://astra.datastax.com) cloud database. The "free tier" of Astra DB should provide more than enough resources to run the application. If the Astra database is "hibernated," it will need to be "resumed" before running the applicaiton.

_Note: As of this writing, only databases created on the Google Cloud Platform (GCP) provider qualify for Astra DB's free tier._

In the book "Code with Java 21," the keyspace is named "movieapp." Really, the keyspace can be named anything, but its name needs to be reflected in the **ASTRA_DB_KEYSPACE** environment variable.

Whatever the name of the keyspace, it should have the following Cassandra tables created within:

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

A token from an appropriately-scoped role is required. As the main [MovieApp](https://github.com/aploetz/MovieApp) application uses Spring Data underneath, the application role should be "Database Administrator." This is because Spring Data needs the ability to create tables, even if instructed not to do so.

This application also requires the Secure Connect Bundle (SCB) to be downloaded from the Astra dashboard.

## Environment:

This application requires the following environment variables to be set:

 - **ASTRA_DB_KEYSPACE** - The database keyspace containing the tables required by the Weather Application.
 - **ASTRA_DB_APP_TOKEN** - The token obtained from Astra DB.
 - **ASTRA_DB_SECURE_BUNDLE_PATH** - The local file location of the Astra secure connect bundle ZIP file.

In a Mac/Linux environment, the environment varaibles can be set from a terminal like this (example):

```
export ASTRA_DB_KEYSPACE=movieapp
export ASTRA_DB_APP_TOKEN=AstraCS:BxinhBlahBlahBlahFHqKZw:d6532818082NotARealTokenc18d40
export ASTRA_DB_SECURE_BUNDLE_PATH=/home/aploetz/local/secure-connect-bpbmovies.zip
```

In a Windows environment, the environment variables can be set from the commmand line like this:

```
set ASTRA_DB_KEYSPACE=movieapp
set ASTRA_DB_APP_TOKEN=AstraCS:BxinhBlahBlahBlahFHqKZw:d6532818082NotARealTokenc18d40
set ASTRA_DB_SECURE_BUNDLE_PATH=d:\local\secure-connect-bpbmovies.zip
```

## To build:

### Build Requirements

 - Java 21 (JDK)
 - Maven

### Build command

    mvn clean install

### Running the build

For its purposes with the "Code with Java 21" book, this application was meant to be run from within an IDE. To run it from the command line, add the [Maven Assembly Plugin](https://maven.apache.org/plugins/maven-assembly-plugin/) to the [pom.xml](pom.xml) file (shown below), configure it to point to the `MovieDataLoader.java` file, and rerun `mvn clean install`. This should create file named `movieapp-0.0.1-SNAPSHOT-jar-with-dependencies.jar` file in the target directory, which can be run with the following command:

#### Command line config for the pom.xml

Add this code inside of the `<project>` tag level:

```xml
  <build>
    <plugins>
      <plugin>
          <artifactId>maven-assembly-plugin</artifactId>
          <executions>
            <execution>
              <phase>package</phase>
              <goals>
                <goal>single</goal>
              </goals>
            </execution>
          </executions>
          <configuration>
            <archive>
              <manifest>
                <addClasspath>true</addClasspath>
                <mainClass>com.codewithjava21.movieapp.batchloader.MovieDataLoader</mainClass>
              </manifest>
            </archive>
            <descriptorRefs>
               <descriptorRef>jar-with-dependencies</descriptorRef>
            </descriptorRefs>
          </configuration>
      </plugin> 
    </plugins>
  </build>
```

#### Running

```
java -jar target/movieapp-0.0.1-SNAPSHOT-jar-with-dependencies.jar

...
The Empire Strikes Back
Return of the Jedi
Back to the Future
Aliens
Alien
1005 movies written
```
