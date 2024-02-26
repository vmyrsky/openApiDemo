# OpenApi - Demo
A spring boot example using OpenApi to generate the endpoint stubs


### List of contained demo micro services
- ItemService
    * ItemService-API
      - Contains the OAD-files (OpenAPI Document) for generating the APIs to implement.
    * ItemService-REST
      - An item is a small component part of a bigger entity, it implements a single function.
      - Implements the APIs generated.
      - Controls the items.
- GadgetService (Not implemented)
    * GadgetService-API
      - Contains the OAD-files (OpenAPI Document) for generating the APIs to implement.
    * GadgetService-REST
      - A gadget may consist of multiple items to provide some functionality.
      - Implements the APIs generated.
      - Controls the gadgets, depends on ItemService.
