# Instructions for the REST module

### Building
- Refer to [README-OPENAPI.md](../partService-API/README-OPENAPI.md) for instructions to build the API-module.
- You can build normally all other modules.
    * E.g. `mvn clean install`
 - Refer to [README-SPRING.md](README-SPRING.md) for running the project with SpringBoot.

### Implementing the REST-api
1. You need to have the API module generated and packaged first.
    - Refer to [README-OPENAPI.md](../partService-API/README-OPENAPI.md) for instructions to build the API-module.
2. Once the API-module is packaged and available in the local maven repository (unless it was already published somewhere), add it normally as a dependency to the  _pom.xml_ .

```
<dependency>
    <groupId>com.org.product</groupId>
	<artifactId>partService-API</artifactId>
    <version>${partService-API.version}</version>
</dependency>
```
3. Implement the APIs in REST controller(s).
    - The REST controllers should be in a separate module as the APIs generated.
       * Having both in the same module/project will result in compilation problems when implementation refers to code not generated yet.
       * Versioning should be individual for both, since the API version should remain unchanged when there are no changes in endpoint signatures.
         - The API may be used elsewhere (e.g. API Gateways or by external clients) and they should not need to be aware nor care about updates in the service logic.
    - Create a `@RestController` which implements all the endpoints of the generated interfaces.
       * See [this example](src/main/java/com/org/product/rest/PartServiceController.java).
    - **Note:** There is also an alternative way of using service injection with the generated APIs, but its chosen **not to use** this approach.
4. Implement any logic to be executed when an endpoint is called as you normally would.

### Testing
The REST module should have at least two kinds of tests. Plain unit tests (which may require spring context for injection, but uses mock services) and full integration tests which use the real services. These are identified with the post fixes 'Tes.javat' and 'IT.java'.<br/>
A third kind of test would be "a pure unit test" where no Spring context is present at all and the tests will focus only at functionality provided by a "leaf class". i.e. A class that has functionality based only parameters directly provided for it per method. (See this [example](src/test/java/com/org/product/service/ProductBuildingServicePureTest.java))<br/>
Executing the test have been separated into two phases.
- Running the normal (unit) tests (fail fast).
    * Execute maven command: `mvn clean install -U`
      - Where  _-U_  forces to update all snapshot dependencies.
- Running the integration tests (in addition to unit tests).
    * Execute: `mvn clean verify -Psurefire`
    * **Note:** Some integration test may require running other micro services they need to interact with.
      - Generally a micro service should be independent of others, but this may not be always possible. For this reason they need to share an API contract defined with the OpenAPI document.

### Reverse engineer the OAD-file from code
- It may useful sometimes to create an  _OAD-file_  from a fully implemented `@RestController`. Such occasions would be for example:
    * To get OpenAPI "item" description of a real entity used with an endpoint (request or response object).
    * To get OpenAPI signature description of a specific endpoint.
- TODO: [Implement this](https://medium.com/@georgeberar/springboot-generate-openapi-document-during-test-phase-a3a793a50dfe)