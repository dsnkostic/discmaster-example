# Defect list

## ERROR

* NONE so far!!!

## Some conclusions

* `liquibase-hibernate5` maven plugin for liquibase seems to be bugged. It should be taken extra care when generating changesets as wrongly schema can be generated from the Hibernate JPA. Some of documented
  - https://github.com/liquibase/liquibase-hibernate/issues/205

 

## Warning

* Running `liquibase:diff` causes `SEQUENCE` auto generation strategy to create `autoIncrement` attribute in createTable changeset tag. This should be deleted as we do not want to use identity strategy!!
* Running `liquibase:diff` causes this warning in the output. So far it does not affect development:
>  Jun 10, 2021 11:57:01 AM org.hibernate.engine.jdbc.connections.internal.ConnectionProviderInitiator initiateService
  WARN: HHH000181: No appropriate connection provider encountered, assuming application will be supplying connections
  Jun 10, 2021 11:57:01 AM org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator initiateService
  WARN: HHH000342: Could not obtain connection to query metadata
  java.lang.UnsupportedOperationException: The application must supply JDBC connections
  at org.hibernate.engine.jdbc.connections.internal.UserSuppliedConnectionProviderImpl.getConnection(UserSuppliedConnectionProviderImpl.java:44)
  at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess.obtainConnection(JdbcEnvironmentInitiator.java:180)
  at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:68)
  at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:35)
  at org.hibernate.boot.registry.internal.StandardServiceRegistryImpl.initiateService(StandardServiceRegistryImpl.java:101)
  at org.hibernate.service.internal.AbstractServiceRegistryImpl.createService(AbstractServiceRegistryImpl.java:263)
  at org.hibernate.service.internal.AbstractServiceRegistryImpl.initializeService(AbstractServiceRegistryImpl.java:237)
  at org.hibernate.service.internal.AbstractServiceRegistryImpl.getService(AbstractServiceRegistryImpl.java:214)
  at org.hibernate.id.factory.internal.DefaultIdentifierGeneratorFactory.injectServices(DefaultIdentifierGeneratorFactory.java:152)
  at org.hibernate.service.internal.AbstractServiceRegistryImpl.injectDependencies(AbstractServiceRegistryImpl.java:286)
  at org.hibernate.service.internal.AbstractServiceRegistryImpl.initializeService(AbstractServiceRegistryImpl.java:243)
  at org.hibernate.service.internal.AbstractServiceRegistryImpl.getService(AbstractServiceRegistryImpl.java:214)
  at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.<init>(InFlightMetadataCollectorImpl.java:176)
  at org.hibernate.boot.model.process.spi.MetadataBuildingProcess.complete(MetadataBuildingProcess.java:127)
  at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.metadata(EntityManagerFactoryBuilderImpl.java:1224)
  at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:1255)
  at liquibase.ext.hibernate.database.HibernateEjb3Database.buildMetadataFromPath(HibernateEjb3Database.java:59)
  at liquibase.ext.hibernate.database.HibernateDatabase.buildMetadata(HibernateDatabase.java:143)
  at liquibase.ext.hibernate.database.HibernateDatabase.setConnection(HibernateDatabase.java:83)
  at liquibase.database.DatabaseFactory.findCorrectDatabaseImplementation(DatabaseFactory.java:121)
  at liquibase.database.DatabaseFactory.openDatabase(DatabaseFactory.java:141)
  at liquibase.integration.commandline.CommandLineUtils.createDatabaseObject(CommandLineUtils.java:96)
  at org.liquibase.maven.plugins.LiquibaseDatabaseDiff.performLiquibaseTask(LiquibaseDatabaseDiff.java:222)
  at org.liquibase.maven.plugins.AbstractLiquibaseMojo.lambda$null$0(AbstractLiquibaseMojo.java:447)
  at liquibase.Scope.lambda$child$0(Scope.java:160)
  at liquibase.Scope.child(Scope.java:169)
  at liquibase.Scope.child(Scope.java:159)
  at liquibase.Scope.child(Scope.java:138)
  at org.liquibase.maven.plugins.AbstractLiquibaseMojo.lambda$execute$1(AbstractLiquibaseMojo.java:373)
  at liquibase.Scope.lambda$child$0(Scope.java:160)
  at liquibase.Scope.child(Scope.java:169)
  at liquibase.Scope.child(Scope.java:159)
  at liquibase.Scope.child(Scope.java:138)
  at liquibase.Scope.child(Scope.java:222)
  at liquibase.Scope.child(Scope.java:226)
  at org.liquibase.maven.plugins.AbstractLiquibaseMojo.execute(AbstractLiquibaseMojo.java:307)
  at org.liquibase.maven.plugins.LiquibaseDatabaseDiff.execute(LiquibaseDatabaseDiff.java:187)
  at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo(DefaultBuildPluginManager.java:137)
  at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:210)
  at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:156)
  at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:148)
  at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject(LifecycleModuleBuilder.java:117)
  at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject(LifecycleModuleBuilder.java:81)
  at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build(SingleThreadedBuilder.java:56)
  at org.apache.maven.lifecycle.internal.LifecycleStarter.execute(LifecycleStarter.java:128)
  at org.apache.maven.DefaultMaven.doExecute(DefaultMaven.java:305)
  at org.apache.maven.DefaultMaven.doExecute(DefaultMaven.java:192)
  at org.apache.maven.DefaultMaven.execute(DefaultMaven.java:105)
  at org.apache.maven.cli.MavenCli.execute(MavenCli.java:957)
  at org.apache.maven.cli.MavenCli.doMain(MavenCli.java:289)
  at org.apache.maven.cli.MavenCli.main(MavenCli.java:193)
  at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
  at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
  at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
  at java.base/java.lang.reflect.Method.invoke(Method.java:566)
  at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced(Launcher.java:282)
  at org.codehaus.plexus.classworlds.launcher.Launcher.launch(Launcher.java:225)
  at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode(Launcher.java:406)
  at org.codehaus.plexus.classworlds.launcher.Launcher.main(Launcher.java:347)
  at org.codehaus.classworlds.Launcher.main(Launcher.java:47)
  

## Nice to have

* Removal of `changelog-diff.xml` after each `liquibase:diff` run in generated folder. Maybe creating specific maven goal for this?
* Removal of generated alterSequence changeSet with `hibernate_sequence` after every liquibase run. Maybe that maven goal above can help with this?