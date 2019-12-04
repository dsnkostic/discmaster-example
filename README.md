# discmaster-example
A demo sandbox project where I will try to adapt as much Java technologies as I can

## Objective

This will be a `simple` java application that contains the reviews of the video games. Upon that, I will try to build and integrate as technologies that are interesting to me. Primarily, focus will be on the microservices and dockerizing whole thing. Unit and integration testing is a must. I will modify plans as I see fit. No deadlines, just relax, be fun and keep it cool. :)

## Implementation roadmap

Each point should be considered as a separate milestone (and tag in git). Version should be defined as: `Major Version`.`Minor Version`.`Fix version`. Steps will probably be updated as I go forward.

### Phase One - Monolith begins

* [ ] Create basic video game review platform. 
  * Have a couple of games (10 games), along with the descriptions, pictures, tags, ratings, and recommendations. Just reading of data for now. No updates, inserts and removals. 
  * Frontend should be AngularJS and should be as simple as possible
  * Backend should be in the spring boot with the JPA (Hibernate in the back). For interface, use JSON REST API.
  * Use Docker to host postgre database for now.
* [ ] Use [Liquibase](https://www.liquibase.org/) to play with the database migrations

### Phase Two - Microservice adventure

* [ ] Break thing into three microservices. Document everything with OpenAPI/Swagger. Try to avoid `Distributed monolith antipattern`:
  * One for storing the game reviews
  * One for storing the ratings
  * One for storing the recommendations
  * One for AngularJS frontend (Backend for Frontend pattern [BFF](https://samnewman.io/patterns/architectural/bff/)
* [ ] Implement Circuit breakers to defend frontend. Investigate [Hystrix](https://github.com/Netflix/Hystrix)
* [ ] Use a [nginx reverse proxy] (https://www.nginx.com/resources/wiki/). Not to much as it seems that Zuul offers better performance
* [ ] Use a service discovery. Evaluate and implement [Eureka](https://github.com/Netflix/eureka)
  * What about [Consul](https://www.consul.io/discovery.html)
* [ ] Use a reverse proxy and API gateway. Evaluate [Zuul](https://github.com/Netflix/eureka). Some [thoughts](https://engineering.opsgenie.com/comparing-api-gateway-performances-nginx-vs-zuul-vs-spring-cloud-gateway-vs-linkerd-b2cc59c65369). Not sure if these should be separated in two points. 
* [ ] Make redundant microservice nodes and cycle among them (round robin)

### Phase Three - Publish Subscribe pattern

* [ ] Implement [Selenium](https://selenium.dev/) for functional testing.
* [ ] Update the basic application to allow Create, Update and Delete of the game reviews.
* [ ] Implement pub-sub pattern so that other microservices are notified when something got changed.
* [ ] Implement [CQRS](https://microservices.io/patterns/data/cqrs.html) pattern.

### Phase Four - Security protocols

* [ ] Use Https protocol
* [ ] Implement Spring security with the OAuth authentification
  * Different microservice will be used to authenticate users
  * Use token based authentification
  * Implement Roles of USER and ADMIN
  * Update frontend to support logging of the admin
  * There should be some work on updating the user management screen.
* [ ] Add support for the refresh tokens

### Phase Five - Childern of the Microservices

* [ ] Implement new microservice for tagging and search. Use [ElasticSearch](https://www.elastic.co/)
* [ ] Implement Comment system in the new microservice. See how it integrates with the search.
* [ ] Discuss and think about using a separate microservice to store images.
* [ ] Migrate image placement to this new microservice.

### Phase Six - GraphQL extravaganza 
* [ ] Explore how to implement [GraphQL](http://graphql.github.io/)

## Food for thought
* -Should I integrate CI/CD cycle to test deployment on almost prod-like envrironment and which one should I use. This [discussion](https://hackernoon.com/should-i-use-heroku-or-aws-3bfcd4706a36) should be helpful. At the end, this could be the Phase Two of the project.- It would bring unnecessary complexity so I will not do it. I am going to tag the repo, and later, I can simulate countinous deployment at least.
* Caching is not considered anywhere, so that also must be integrated somewhere

## Usefull links:
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Cloud](https://spring.io/projects/spring-cloud)
* [Microservices](https://microservices.io/)
