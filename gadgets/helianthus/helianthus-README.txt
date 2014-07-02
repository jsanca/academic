---- Welcome to Helianthus project -------
*** What is it?
Helianthus born as an idea to supply an abstraction layer (middleware) for people that just know client side technologies such as Javascript and SQL as unique backend knowledge.
Helianthus provides a layer to get relational database results (as a table result) based on a SQL query, you can include one or more parameters, it is totally optional.
Helianthus also integrated to support for several formatters, for instance the extension .html will render a human readable format for people, but also include json, xml and Java Byte code (bin).
In the future we are expecting to have csv, excel and so on.
Also the Helianthus has the support to run custom workflows, by default it reads a SQL query (based on an id/path) executes the query over a specific data base and serialize in the second step the result to some format (this mechanism has to be refactoring in order to be more friendly with the programmers)
*** How build it?
Helianthus is Java based app and use maven as a mechanism to deploy the artifacts.

In order to get the webapp running you have to build:

1) go to helianthus/helianthus/ and run mvn clean install, these classes are the core.

2) go to helianthus/helianthus-transformer/ and run mvn clean install, these classes are the transformers in charge of take the table results and format to a specific output such as HTML and so on.

3) go to helianthus/helianthus-web/ and run mvn clean install, these classes enclosed the webapp (controller, workflow and so on).

4) finally go to helianthus/helianthus-web-deploy/ and run mvn jetty:run, this will start jetty at the port 8080.
So in order to see it working you will need:

How configure and test?

If you ran the previous steps, you probably will notice that the app does not work ok, however you already have the components ready, but you still needing to do some basic configuration to make it works, first let's see the system requeriments


1) Java
2) Maven
3) a Data Base (the examples uses MySQL)

If you have everything ready in your end, so you have to go to 

helianthus/helianthus-web-deploy/src/main/resources/mysqlsampledatabase.sql

Just go ahead and run it over mysql instance, it will create a database called "classicmodels" with all the structures and data populated.

Finally go to:

helianthus/helianthus-web-deploy/src/main/webapp/WEB-INF/applicationContextExample.xml

Open it (it is a spring configuration but don't be afraid you don't need to know spring to use helianthus) look for the definition for mysqlDataSource:

check if the datasource configuration is ok for you end (usually take care about these values "url", "username", and "password")

After that run again on helianthus/helianthus-web-deploy/

mvn jetty:run

If you are curios you will notice there is another configuration file called queryConfig.xml under:

helianthus/helianthus-web-deploy/src/main/resources/

it represents the entry point of the application, if you open it you will see a queries definitions with a name, optional parameters and actual SQL query.

So, what it means.

The name is the path associated to this particular query, for instance:

http://localhost:8080/helianthus-web-deploy/all-products.html

this will render the query result in a HTML format, but you can also run

http://localhost:8080/helianthus-web-deploy/all-products.json 

To get the same info as a json

Other options are .xml and .bin (for XML and Java Bin)

An example of parameter would be:

http://localhost:8080/helianthus-web-deploy/get-product.json?productCode=S10_1678

this is will replace the ? signal into the query, you have to say to the framework the expected type to avoid security issues.

This was a quick start, not more by now :)
Pura vida