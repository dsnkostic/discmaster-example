# discmaster-example
A demo sandbox project where I will try to adapt as much Java technologies as I can

## Objective

This will be a `simple` java application that contains the reviews of the video games. Upon that, I will try to build and integrate as technologies that are interesting to me. Primarily, focus will be on the microservices and dockerizing whole thing. Unit and integration testing is a must. I will modify plans as I see fit. No deadlines, just relax, be fun and keep it cool. :)

## Implementation roadmap

Check separate file roadmap.md in the repository

## Development remarks

### Use docker for postgresql dependency

Inside src/main/resources/docker/docker-compose.yml, there is a composer file that you can run with `docker-compose up`. This will bring postresql which will be used when application is run in the local environment. Tests use embedded H2 so no configuration is necessary there

### Liquibase database evolution

Make sure you run this command to evolve schema based on JPA entity models `mvn liquibase:diff`. Result changelog you will find in the `generated/main/resources/changelog-diff.xml` file. Inspect the file and copy needed changeset to the `src/main/java/resource/changelog/changelog-master.xml`. Be aware that there is almost always generated alterSequence changeSet with `hibernate_sequence`. You can ignore this one. 

