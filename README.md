# TV Series Management System
![java_badge](https://img.shields.io/badge/-Java-lightgrey?style=for-the-badge&logo=appveyor)
![maven_badge](https://img.shields.io/badge/-Maven-yellow?style=for-the-badge&logo=appveyor)
![spring](https://img.shields.io/badge/-Spring-green?style=for-the-badge&logo=appveyor)

## About Project

This project is developed to display programming practices including OOP, SOLID, design patterns, testing and logging.

## Project Requirements

This project aims to create a TV series management system that retrieves information from an external API and allows users to manage and explore
TV series data. The application will read a configuration file containing a list of TV series,
perform lookups against the http://www.tvmaze.com/api, and store the results in a local database.
The choice of database engine and framework is left to the candidate's discretion.

### General Requirements

- The application should read a configuration file with a minimum of 60 TV series entries (refer to the appendix for a suggested file format).
- Each TV series in the list should be queried against the http://www.tvmaze.com/api.
- The retrieved data should be stored in a local database.
- The application should store all episodes for each TV series.
- The system should generate reports for the user and provide TV series recommendations based on user preferences.
- The candidate can decide which filters to include for viewing selected TV series, such as the network the series airs on or the series' rating.

### Reports

The application should provide the following reports, which can be exported as semicolon-separated txt files:

1. Next Week: This report should group TV series episodes by day of the week.  
   Example:
```
SHOW_NAME;MONDAY;TUESDAY;WEDNESDAY;THURSDAY;FRIDAY;SATURDAY;SUNDAY
LOST;;S01E03;;;;;
GoT;S07E04;;;;;;
FARGO;;;;S03E07;;;
TABOO;;;;;;S01E08;;
```
2. Top 10: This report should list the top 10 TV series based on ratings.  
Example:
```
Better Call Saul;
One-Punch Man;
MINDHUNTER;
Yellowstone;
Dark Matter;
Janet King;
True Detective;
Blue Bloods;
Stan Lee's Lucky Man;
Killjoys
```

3. Top network: This report should list networks and their associated TV series based on average ratings.  
Example:

```
AVERAGE_RATING;NETWORK;TOP_RATED_SHOW;TOP_RATING;SHOW_COUNT
7.5;HBO;Game of Thrones;9.0;5
7.0;CBS;XXXX;8.0;4
```

4. Summary: This report should list all registered TV series.  
Example:
```
SHOW_NAME;NETWORK;GENRES;EPISODE_COUNT;RELEASED_EPISODE_COUNT
```

5. Best episode: This report should list the highest-rated episode for each TV series.  
Example:
```
SHOW_NAME;NETWORK;GENRES;SEASON_NUMBER;EPISODE_NUMBER;EPISODE_NAME;RATING
```

6. Recommended show: This report should provide TV series recommendations.  
Example:
```
SHOW_NAME;RATING;GENRES;SUMMARY;IMDB_LINK
```

### Error Handling

The TVmaze API documentation states the following guidelines for error handling:
- API calls are rate-limited to 20 calls every 10 seconds per IP address. If the rate limit is exceeded, an HTTP 429 error might be returned. It is recommended to retry the request after a small pause instead of treating it as a permanent failure.
- Setting a unique HTTP User Agent for the client application is strongly recommended to help identify the application in case of problems.

It is expected that error handling regarding these guidelines is implemented in the project.

### GUI

Implementing a web-based GUI is considered a significant advantage. The GUI should provide the following functionalities:
- Displaying and retrieving reports.
- Providing TV series recommendations based on user preferences.
- Summarizing TV series data.
- Adding and removing TV series from the local database.


### Built With

* IntelliJ IDEA
* Spring 3.1.0
* Maven

### Dependencies

* Spring Web
* Webflux
* MongoDB NOSQL Database
* Flapdoodle Embed Database Server
* Lombok
* JUNIT Testing
* Spring OpenAPI (SwaggerUI)

***
## Getting Started and Program Overview


### Installation

Clone the repository below.
<https://github.com:Primo-hash/tv-series-admin.git>

Open the pom.xml file as a project with your preferred IDE (I used Intellij IDEA Community).

### API Request examples

The API runs on port 8080 by default, and serves a Swagger UI interface which is a GUI that allows simulation of all the mapped API endpoint requests in this TV Series API.
You can access this by navigating to <http://localhost:8080/swagger-ui/index.html> while the project is running.

#### Configuration
As mentioned in the project description, the project should read a file with a mnimum of 60 show names.  
configuration file path:  ```src/main/resources/tvmaze_config.txt```
You can also change the name of the file, but also have to change the tvmaze.config.file property in application.property to the same name.

### Report Endpoints

#### Next Week Schedule GET
>http://localhost:8080/nextweek
Download link:
>http://localhost:8080/nextweek.txt 

#### Top 10 Shows GET
>http://localhost:8080/top10shows
> Download link:
>http://localhost:8080/top10shows.txt

#### Top Network GET
>http://localhost:8080/topnetwork
> Download link:
>http://localhost:8080/topnetwork.txt

#### Summary GET
>http://localhost:8080/summary
> Download link:
>http://localhost:8080/summary.txt

#### Best Episode Per Show GET
>http://localhost:8080/bestepisodes
> Download link:
>http://localhost:8080/bestepisodes.txt

#### Best Episode Per Show POST
>http://localhost:8080/bestepisodes
> Download link:
>http://localhost:8080/bestepisodes.txt
Example Request Body:
```
{
    "network": "MTV",
    "limit": 5,
    "genres": [
        "Drama",
        "Crime",
        "Thriller"
    ],
    "runtimeGreaterThan": 30,
    "runtimeLessThan": 61,
    "ratingGreaterThan": 8,
    "premieredAfter": "2015-01-01",
    "premieredBefore": "2023-01-01",
    "language": "English",
    "runningShow": true,
    "keywords": [
        "Award"
    ]
}
```

The fields in the JSON example above do not represent filters, but rather boxes to check.
Shows are given a score for each box that is checked, and the shows with the highest score are recommended.  

>*Network*: Name of a network that exists in the tvmaze API.  
  
>*Limit*: Number for the maximum recommended shows to return.  
  
>*Genres*: Array of genres, each genre awards its own points.  
  
>*Runtime*: A number representing minutes the average episode of a show runs for.  
  
>*Rating*: A Number between 1 and 10 that represents the rating of a show.  

>*Premiered*: A String representing a date in the yyyy-MM-dd format, representing when a show premiered.  
  
>*Language*: Name of a language.  

>*Running show*: A Boolean representing if a show is currently still running.

>*Keywords*: Array of keywords found in show title and summary, each keyword awards its own points.

***

