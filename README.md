# Getting Started

### Build

Build project with maven

```shell
mvn clean install
```

### Docker

Run app with docker-compose

```shell
docker-compose up -d
```

or if you need only database (PostgresDB) run

```shell
docker run --name open_meteo_db -e POSTGRES_USER=user -e POSTGRES_PASSWORD=welcome1 -e POSTGRES_DB=open_meteo_db -p 5432:5432 -d postgres
```
