# Helianthus — Project Summary

**Helianthus** is a Java-based middleware/abstraction layer that exposes SQL queries as HTTP endpoints, allowing frontend developers who only know client-side technologies and SQL to access relational database results in multiple formats (JSON, XML, HTML, binary).

- **Author:** jsanca
- **Created:** 2014
- **License:** Apache 2.0

---

## Architecture

Helianthus is a multi-module Maven project:

```
helianthus/
├── helianthus/                 # helianthus-core — core interfaces, beans, JDBC data access
├── helianthus-bean/            # helianthus-bean — shared data beans (TableResultBean)
├── helianthus-transformer/     # helianthus-transformer — output formatters (HTML, CSV)
├── helianthus-web/             # helianthus-web — servlet, workflow engine, marshallers, Spring DI
└── helianthus-web-deploy/      # helianthus-web-deploy — deployable WAR with sample configs
```

---

## How It Works

1. **Define queries in XML** (`queryConfig.xml`) — each query gets an operation ID (which maps to a URL path), typed parameters, and an optional data source name.

   ```xml
   <query id="/all-products" datasource="classicmodels">
       <sql>SELECT * FROM products</sql>
   </query>
   
   <query id="/get-product" datasource="classicmodels">
       <sql>SELECT * FROM products WHERE productCode = ?</sql>
       <param name="productCode" type="string" />
   </query>
   ```

2. **Access via URLs** — the format is determined by the file extension:
   - `http://localhost:8080/helianthus/all-products.json` → JSON
   - `http://localhost:8080/helianthus/all-products.html` → HTML table
   - `http://localhost:8080/helianthus/all-products.xml` → XML
   - `http://localhost:8080/helianthus/all-products.bin` → Java-serialized binary
   - `http://localhost:8080/helianthus/get-product.json?productCode=S10_1678` → parameterized query

3. **Request flow:**
   - `HelianthusServlet` receives the HTTP request
   - `PathHandler` parses the URL to extract the operation ID and format
   - `WorkFlowFactory` builds a workflow chain:
     1. `OperationRunnerWorkFlowStep` — looks up the query via `OperationMappingHelper`, binds parameters with type-safe `ColumnHandler`s, executes via JDBC `PreparedStatement`, and maps the `ResultSet` to a `TableResultBean`
     2. `FormatterWorkFlowStep` — selects the appropriate `MarshallFormatter` (JSON/XML/HTML/Binary) and writes the result to the HTTP response

---

## Key Components

| Component | Module | Role |
|-----------|--------|------|
| `HelianthusService` | core | Interface: `executeOperation(operationId, params)` |
| `OperationMappingHelper` | core | Registry mapping operation IDs → SQL queries, types, datasources |
| `GenericDataAccess` | core | Interface: `executeQuery(query, types, datasource, params)` |
| `DataBaseGenericDataAccessImpl` | core | JDBC implementation with `PreparedStatement` |
| `ConnectionProviderService` | core | Routes to the correct connection pool by datasource name |
| `DataSourcePoolConnectionProvider` | core | Apache DBCP connection pool |
| `TableMappingHandler` | core | Converts `ResultSet` → `TableResultBean` |
| `ColumnHandler` / `ColumnHandlerFactory` | core | Type-safe parameter binding (string, integer, long, float, double, bigdecimal, boolean, byte, date) |
| `HelianthusServlet` | web | Main HTTP entry point (GET/POST/PUT/DELETE) |
| `PathHandler` | web | Parses URL path into operation ID + format |
| `WorkFlowFactory` / `WorkFlowStep` | web | Pluggable workflow chain |
| `MarshallFormatFactory` | web | Registry of output formatters by name |
| `TableResultJSONMarshallFormatter` | web | XStream + Jettison → JSON with aliases |
| `TableResultXMLMarshallFormatter` | web | XStream + Dom4J → XML with aliases |
| `TableResultHTMLMarshallFormatter` | web | Uses `HTMLTableFormatter` to render HTML `<table>` |
| `BinaryMarshallFormater` | web | Java `ObjectOutputStream` serialization |
| `HTMLTableFormatter` | transformer | Converts beans to HTML tables via Commons BeanUtils |
| `ParseQueryConfigurationUtil` | web | Parses `queryConfig.xml` using Commons Digester |
| `SpringQueryConfigurationFactoryBean` | web | Spring `FactoryBean` that produces `OperationMappingHelper` from XML config |

---

## Technology Stack

| Technology | Version | Usage |
|------------|---------|-------|
| Java | 1.4+ (uses 1.7 features) | Primary language |
| Maven | — | Build and dependency management |
| Spring Framework | 4.0.1 | Dependency injection, ApplicationContext, FactoryBean |
| Apache Commons DBCP | 1.2.2 | Connection pooling |
| JDBC (`java.sql`) | — | Database access |
| XStream | 1.4.7 | JSON and XML serialization |
| Dom4J | 1.6.1 | XML driver for XStream |
| Jettison | 1.1 | Alternative JSON driver |
| Apache Commons BeanUtils | 1.9.1 | Reflection-based bean property access |
| Apache Commons Digester | 3.2 | XML-to-Java mapping (query config parsing) |
| Java Servlet API | 2.4 | Web layer |
| JSTL | 1.1.2 | JSP tag library |
| MySQL Connector | 5.1.30 | Database driver |
| Jetty (Maven plugin) | 6.1.10 | Embedded web server |
| JUnit | 3.8.1 / 4.11 | Testing |

---

## Configuration

The project uses Spring XML for wiring. The deploy module (`helianthus-web-deploy`) includes:

- `web.xml` — declares `HelianthusServlet` mapped to `/`
- `applicationContextCore.xml` — wires core beans, marshallers, and workflow
- `applicationContextExample.xml` — wires a MySQL connection pool for the `classicmodels` sample database
- `queryConfig.xml` — sample query definitions
- `mysqlsampledatabase.sql` — SQL dump for the `classicmodels` sample database

---

## Build & Run

```bash
# Build each module in order
cd helianthus        && mvn clean install
cd helianthus-transformer && mvn clean install
cd helianthus-web    && mvn clean install

# Set up MySQL and import sample data
mysql -u root -p < helianthus-web-deploy/src/main/resources/mysqlsampledatabase.sql

# Configure DB credentials in applicationContextExample.xml

# Run with Jetty
cd helianthus-web-deploy && mvn jetty:run
# Available at http://localhost:8080/helianthus/
```

---

## Current State & Observations

- **Functional prototype** — the core flow works: define SQL in XML → hit URL → get formatted data
- **Incomplete areas:**
  - `CSVTableFormatter` outputs HTML-like tags instead of actual CSV (appears unfinished)
  - Minimal test coverage (uses ancient JUnit 3.8.1)
  - No input validation beyond type checking
  - No authentication or authorization layer
  - Hardcoded format detection by file extension only
- **Architectural notes:**
  - The workflow engine is extensible — new steps can be added via Spring config
  - New output formats can be added by implementing `MarshallFormatter` and registering in `MarshallFormatFactory`
  - New database types can be supported by implementing `ConnectionProvider`
