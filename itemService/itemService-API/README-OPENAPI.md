# Instructions for the API module.

### Rules of thumb
1. Do not edit generated code.
2. Do not copy the generated code to other places.
3. Use released versions (non SNAPSHOT) of the generated code packages as dependencies.
    - Exception is to refer to a SNAPSHOT only when implementing/debugging a new feature/bug with the API.
       * Having a SNAPSHOT reference should be only very momentary. (e.g. an API change is under review for acceptance before it is released, which it should be right after review)
4. When not working with the API project, close it in your IDE.
    - Closing the project in eclipse helps it to detect files correctly from the dependencies.
    - Also, basically the source files are not for anyone to see, closing project prevents you from looking them directly. View the OAD-file or Refer to the api docs if insight is needed.
       * If the api docs are insufficient, you should then improve the OAD-files.

### Creating the APIs with OpenAPI document (OAD)
1. Create the REST API description by creating an OAD-file using the [OpenAPI Specification](https://swagger.io/specification/).
    - Use JSON (instead of yaml) as generally it is simpler to maintain and format an existing OAD-file. (Opinion of the author)
    - To start with, do not go into too much detail in describing the model entities to correspond with the actual data model (if one already exists).
       * Purpose of these DTOs (data transfer objects) is to answer to the consumer needs / document API contract.
       * DTOs can be:
          - Aggregate data from several DOs (data objects).
          - Used as stub-entities for DOs but the actual DOs are to be defined separately in the code.
       * Though, avoid the  _DTO-antipattern_  which can be basically summarized with:
          - Do not create explicit mapping from data objects(s) to data transfer objects, instead automate this as far as possible to avoid mapping errors when either side of the entities change.
    - See the [example OAD-file](src/main/resources/OAD_2_API.json).
2. Generate all the APIs with the first build (or if any API change).
    - Run two separate profiles with specific goals to have generated APIs packaged correctly.
       * `mvn clean compile -P generate,package jar:jar install:install`
         - You can run this spell with all the poms that have underlying OpenAPI configurations.
           * On master pom (should cascade this to all modules)
           * On service "parent" pom (should cascade to API modules by the profiles)
           * On API pom (the actual stuff)
         - This will (basically) first compile the class files, then package them correctly and finally deploy them to local maven repository.
           * Compilation and generation the classes requires using SpringBoot.
           * Packaging a jar with SpringBoot makes the package unusable as dependency due to its internal folder structure.
           * Dropping out the SpringBoot aspects from the resulting package (which is a requirement for the implementing package) and packaging normally but specifically using the generated classes makes the jar then normally usable as a dependency.
    - Once the APIs are generated and deployed, you can execute other builds normally.
        * E.g. `mvn clean install`
          - This won't attempt to generate the APIs anymore as these modules are (to be) left out in the modules of the "parent" pom.
    - When you are ready to implement the generated APIs, add a dependency normally to the project/package doing the implementation.

### Study and edit the OAD-files and files generated based on them
1. Open an OAD file located at the  _main/resources_  directory.
2. Open some of the **generated** class from  _target/generated-sources/openapi_ .
3. View the contents of the generated class to familiarize how the OAD-file corresponds with the generated one.
    - Also refer to [OpenAPI Specification](https://swagger.io/specification/)
    - **Note:** Do not edit the file(s) since the changes are discarded when the files are generated again.
4. To implement actual logic to execute when an endpoint is called, create a new SpringBoot project which has a dependency to version built from this (API) project.
    - Always refer to published version of the generated API. Do not refer to SNAPSHOT-version unless you are simultaneously changing signature of the endpoint while implementing its service.
      * The SNAPSHOT version of the API should be released before the actual feature implementation is complete.
    - Continue reading from the [README-REST.md](../itemService-REST/README-REST.md)