# OpenApi - Demo
A spring boot example using OpenApi to generate the endpoint stubs


### List of contained modules
- partService
    * partService-API
       - Contains the OAD-files (OpenAPI Document) for generating the APIs to implement.
       - Start by building and publishing this package.
       - For more information refer to its [README-OPENAPI.md](partService/partService-API/README-OPENAPI.md)
    * partService-REST
       - Implements the APIs generated and provides the actual functionality.
       - A SpringBoot project to use as a micro service.
       - For more information refer to it's [README-REST.md](partService/partService-REST/README-REST.md)
- GadgetService (Not implemented)
    * GadgetService-API
      - Contains the OAD-files (OpenAPI Document) for generating the APIs to implement.
    * GadgetService-REST
      - A gadget may consist of multiple parts to provide some functionality.
      - Implements the APIs generated.
      - Controls the gadgets, depends on partService.
