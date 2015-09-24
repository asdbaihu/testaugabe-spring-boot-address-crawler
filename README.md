 ## Additional frameworks as well as specific build or deploy instructions should be described 
 
1. Spring Boot Application can be started by running mvn spring-boot:run im parent directory
or by building jar and runnig it with java -jar command

2. SQL scripts are under /regis24-test-persistence/src/test/resources/sql

3. Currently not implemented or partually implemented are one of the central classes ScheduledTask 
and some secundary classes like CSVServiceImpl, MailServiceImpl, ModelConvertUtil

4. Two Tests are covering some crucial functions of the application

5. UMLs are in the according dir under the root dir
