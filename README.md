# configurable-discounts

Part of a shopping platform that calculates the price of products based on configurable discount policies.

## How to run

<b>First you need to build the jar.</b> <br>
In order to do that just simply run in project root next command:
```shell
./gradlew clean build
```
<i>Note:</i> This command will also run integration and unit tests. <br>
<b>Once this command finished you need to start docker compose.</b> Be sure that you're local docker is up and running. <br>
Run this simple command:
```shell
    docker compose up
```
Then wait until DB will be up and running, after that application will start and apply liquibase configuration on the started DB.<br>
<br>
<i>Note:</i> Or you can just open `docker-compose.yml` and start all services.<br>

## How to test
Once you run the application you can simply call rest endpoints. E.g: `http://localhost:8080/api/v1/products/b0fa9f81-4f43-4d67-b84c-6a8ecf80e7cf`

<i>Note:</i> Request above will return 404 response because DB does not have any data in it. Everything is tested by unit and integration tests. <br>
See `src/test` folder for unit tests. <br>
See `src/integrationTest` folder for integration tests.<br>