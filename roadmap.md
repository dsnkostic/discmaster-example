# discmaster-example
A demo sandbox project where I will try to adapt as much Java technologies as I can

## Objective

This will be a `simple` java application that contains the reviews of the video games. Upon that, I will try to build and integrate as technologies that are interesting to me. Primarily, focus will be on the microservices and dockerizing whole thing. Unit and integration testing is a must. I will modify plans as I see fit. No deadlines, just relax, be fun and keep it cool. :)

## Implementation roadmap

Each point should be considered as a separate milestone (and tag in git). Version should be defined as: `Major Version`.`Minor Version`.`Fix version`. Steps will probably be updated as I go forward.

### Phase One - Monolith begins

* [ ] Create basic video game review platform.
  * [x] Use thymeleaf at the beginning
  * [x] Have a couple of games (10 games), along with the descriptions. Just reading of data for now. No updates, inserts and removals.
  * [x] Use Docker to host postgre database for now.
  * [x] Add bootstrap css
  * [x] Test controllers
  * [x] Use [Liquibase](https://www.liquibase.org/) to play with the database migrations
  * [x] Switch UUID generation and add unique short name that is used as url path.  
  * [ ] Add tags (genre and artistic feel should be here), pictures and recommendations.
    * [ ] Tags are used as arbitrary information about the games. It consists of key/value pairs, which can be repeatable.
    * [ ] Images should be stored on the service, but in db, it should contain only data and location
    * [ ] Recommendations should contain title, text, from which user and date of creation
  * [ ] Frontend should be VueJS and should be as simple as possible
  * [ ] Use test containers for testing  
  * [ ] Backend should be in the spring boot with the JPA (Hibernate in the back). For the interface, use JSON REST API.

### Phase Two - Microservice adventure

* [ ] Break thing into three microservices. Document everything with OpenAPI/Swagger. Try to avoid `Distributed monolith antipattern`:
  * One for storing the game reviews
  * One for storing the ratings
  * One for storing the recommendations
  * One for AngularJS frontend (Backend for Frontend pattern [BFF](https://samnewman.io/patterns/architectural/bff/) )
* [ ] Implement Circuit breakers to defend frontend. Investigate [Hystrix](https://github.com/Netflix/Hystrix)
* [ ] Use a [nginx reverse proxy](https://www.nginx.com/resources/wiki/). Not to much as it seems that Zuul offers better performance
* [ ] Use a service discovery. Evaluate and implement [Eureka](https://github.com/Netflix/eureka)
  * What about [Consul](https://www.consul.io/discovery.html)
* [ ] Use a reverse proxy and API gateway. Evaluate [Zuul](https://github.com/Netflix/eureka). Some [thoughts](https://engineering.opsgenie.com/comparing-api-gateway-performances-nginx-vs-zuul-vs-spring-cloud-gateway-vs-linkerd-b2cc59c65369). Not sure if these should be separated in two points. 
* [ ] Make redundant microservice nodes and cycle among them (round-robin)
* [ ] Use configuration server. Maybe not at the end, but surely at some point during this phase

### Phase Three - Publish Subscribe pattern

* [ ] Implement [Selenium](https://selenium.dev/) for functional testing.
* [ ] Update the basic application to allow these operations on the game reviews: create, update and delete.
* [ ] Implement [pub-sub](https://microservices.io/patterns/communication-style/messaging.html) pattern so that other microservices are notified when something got changed.
* [ ] Implement [CQRS](https://microservices.io/patterns/data/cqrs.html) pattern.

### Phase Four - Security protocols

* [ ] Use Https protocol
* [ ] Implement Spring security with the OAuth authentication
  * Different microservice will be used to authenticate users
  * Use token based authentication
  * Implement Roles of USER and ADMIN
  * Update frontend to support logging of the admin
  * There should be some work on updating the user management screen.
* [ ] Add support for the refresh tokens

### Phase Five - Children of the Microservices

* [ ] Implement new microservice for tagging and search. Use [ElasticSearch](https://www.elastic.co/)
* [ ] Implement Comment system in the new microservice. See how it integrates with the search.
* [ ] Discuss and think about using a separate microservice to store images.
* [ ] Migrate image placement to this new microservice.

### Phase Six - GraphQL extravaganza 
* [ ] Explore how to implement [GraphQL](http://graphql.github.io/)

## Food for thought
* ~~Should I integrate CI/CD cycle to test deployment on almost prod-like environment and which one should I use. This [discussion](https://hackernoon.com/should-i-use-heroku-or-aws-3bfcd4706a36) should be helpful. At the end, this could be the Phase Two of the project.~~- It would bring unnecessary complexity, so I will not do it. I am going to tag the repo, and later, I can simulate continuous deployment at least.
* Caching is not considered anywhere, so that also must be integrated somewhere. (Use [Redis](https://redis.io/) somehow)
* Pagination of data and possible usage of cursor based pagination. This should be added somewhere at some point
* Maybe think about the [MongoDB](https://www.mongodb.com/)
* How about restructuring the whole system using the [Apache Kafka](https://kafka.apache.org/). Is using the events, instead of states, to describe parts (or everything) of the system considerable improvement to the design?
* Consider using [Elastic Stack](https://www.elastic.co/elastic-stack) (former ELK stack) for integrated logging.
* Should I add Automatic testing, from end user perspective like [Selenium](https://www.selenium.dev/)
* What about translations?
* How about switching to Gradle?
* Think about using the Graph databases for the recommendation system
* How about using [gherkin and cucumber](https://cucumber.io/docs/gherkin/) for testing

## Usefull links:
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Framework](https://spring.io/projects/spring-framework)
* [Spring Testing](https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#testing)  
* [Spring Cloud](https://spring.io/projects/spring-cloud)
* [Microservices](https://microservices.io/)
* [The Twelve-Factor App Methodology](https://12factor.net/)