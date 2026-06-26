# Academic

Personal repository containing Java projects, proofs-of-concept, and experiments across various technologies and frameworks.

## Projects

### Core Java & Fundamentals
- **annotations/** - Custom Java annotations
- **collections/** - Java collections framework experiments
- **design_patterns_java/** - Design patterns implementation in Java
- **java8/** - Java 8 features and examples

### AOP (Aspect-Oriented Programming)
- **aspectj-poc/** - Compile-time AOP with AspectJ (custom @Transactional, @Cacheable aspects)
- **guice-aop-poc/** - Runtime AOP using Google Guice IoC container

### Web Frameworks
- **javalin/** - Lightweight web framework
- **spring_tutorial/** - Multiple Spring framework examples (11 sub-projects covering AOP, bean lifecycle, events, aware interfaces, property placeholder, etc.)
- **mvc_workshop/** - MVC workshop with Play Framework and Spring MVC
- **websocketpingpongclient/** - WebSocket client implementation

### Databases & Persistence
- **iBoxDbExample/** - iBoxDB embedded NoSQL database
- **helianthus/** - Middleware layer exposing SQL queries as HTTP endpoints (JSON/XML/HTML/binary)

### Mobile Development
- **mobile/ionic/** - Ionic 3 + Angular 4 hybrid mobile app

### Utilities & Gadgets
- **gadgets/** - Miscellaneous projects:
  - `scheduler/` - Task scheduler
  - `mon-entrevue/` - Job interview platform
  - `threadlocal/` - ThreadLocal examples
  - `download-manager/` - Download manager
  - And more...

### Other Experiments
- **mapping/** - Object mapping
- **skillMatrix/** - Skill matrix tracking application
- **syncttest/** - Sync test project
- **teamshowcase/** - Team showcase application
- **worldcountmapreduce/** - Word count MapReduce example
- **art-of-java/** - BASIC interpreter (recursive descent parser)

## Cold Start Solutions

### CRaC
- **CRaC/** - Coordinated Restore at Checkpoint example (forthcoming)

CRaC (Coordinated Restore at Checkpoint) is an OpenJDK technology that enables JVM checkpointing and restoration, significantly reducing cold start times for Java applications. Useful for serverless and microservice architectures.

## Build Systems

- **Maven** - Most projects (pom.xml)
- **Gradle** - aspectj-poc, guice-aop-poc, java8
- **SBT** - mvc_workshop/jhelloworld (Play Framework)

## Technologies

Spring, AspectJ, Guice, Javalin, Jetty, Servlet API, Apache Commons, XStream, Dom4J, iBoxDB, Ionic/Angular
