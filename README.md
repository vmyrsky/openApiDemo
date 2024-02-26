# OpenApi - Demo
A spring boot example using OpenApi to generate the endpoint stubs


### List of contained modules
- ItemService
    * ItemService-API
       - Contains the OAD-files (OpenAPI Document) for generating the APIs to implement.
       - Start by building and publishing this package.
       - For more information refer to its [README-OPENAPI.md](itemService/itemService-API/README-OPENAPI.md)
    * ItemService-REST
       - Implements the APIs generated and provides the actual functionality.
       - A SpringBoot project to use as a micro service.
       - For more information refer to it's [README-REST.md](itemService/itemService-REST/README-REST.md)
- GadgetService (Not implemented)
    * GadgetService-API
      - Contains the OAD-files (OpenAPI Document) for generating the APIs to implement.
    * GadgetService-REST
      - A gadget may consist of multiple items to provide some functionality.
      - Implements the APIs generated.
      - Controls the gadgets, depends on ItemService.
