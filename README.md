# The Earendil Api
CI service for Elrond Smart Contracts. 

### How to use
Add a GitHub webhook to your repository, containing repository permissions, with the url https://earendil-api.herokuapp.com/trigger .

### Used ports
* `8080` - configurable from the `docker-compose.yml` file.

### Database
This service uses a Postgres database. Its production variant is configured in heroku. It also comes with a `docker-compose.yml`
file that allows running the whole stack locally.

### Prerequisites
* A unix based system
* docker 
* openjdk 18
* erlang
* elixir

### Running
* `./compile-cli.sh` - compiles the CLI binary
* `./run.sh` - builds a `.jar` file and runs `docker compose up`

### Docker
These are the steps required to run the application locally using docker:
* Build the application `.jar` file by running: `./jar.sh`
* Run `docker compose up`

To deploy the app to heroku, run the following:
* `heroku container:login`
* `heroku container:push web --app earendil-api`
* `heroku container:release web --app earendil-api`

### Graphql
The service implements the following queries:
```graphql
type Query {
    repositories: [Repository]
}
```

It also implements the following mutations:
```graphql
type Mutation {
    addRepository(name: String, token: String): Repository
}
```

The used types are:
```graphql
type Repository {
    id: ID
    name: String
    runs: [Run]
}

type Run {
    id: ID
    creationDate: String
    status: Status
    initiator: String
}

enum Status {
    SUCCESS,
    FAILURE,
    PENDING
}
```

### Heroku
The service is deployed on heroku at the following url:
https://earendil-api.herokuapp.com/

### Routes
These are the currently mapped routes:
* `/` - POST - the graphql endpoint
* `/trigger` - POST - the webhook triggering route
* `/graphiql` - GET - simple interface that allows viewing the service

### Deployment pipeline
This service comes with a GitHub deployment pipeline that is being triggered on all pushes to master. 
Alternatively, it can also be triggered manually.

