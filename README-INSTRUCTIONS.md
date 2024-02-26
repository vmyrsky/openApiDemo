# ProductService implementation rules

### Documentation
- All documentation is to be written in English.
    * Exception if there is localized content for users manuals, but even in that case there must be English version first.
- All code and scripts are to be written in English.
- Exceptions bubling to frontend may be set to use localization keys to use when displaying messages.
    * The default message must be in English.
    * In addition they may have localized text content based on the localization key.
- Each micro service shall have a **REAME.md** file at its root describing its functionality and usage.

### Structuring & implementation
#### 1. General rules
- All projects in a service module done with a certain language must use the same language version.
    * Chosen version number must be documented and centrally controlled where possible.
- All projects done with a certain language must use the same framework as originally selected for the first (main) project.
- All projects must have docker configuration to test the services locally and for publishing them.
- All projects must also be locally runnable **without** docker.
    * For developing them.
    * Locally running projects may use other dockerized services where applicable so that not all must be started independently.
- The docker-compose at the ItemService root must contain reference to all projects.
     * For compiling all at one go.
     * To deploy every or selected set of projects.

##### 2. All services produced using Java and Spring-boot are to be located under similar folder structure.
- All projects must use **Maven** for compiling and follow the common maven-project structure.
- Root/group-package is 'com.org.product'.
- These projects share a common master  _pom.xml_  that is located in the same folder as this readme-file (at master project root).
    * The master pom is only for building all projects at one go.
    * There is no requirement for micro services to refer on master pom as parent. (This would break micro service independency)
- All java projects are to use spring as a framework and must be runnable locally (with SpringBoot) to execute their unit & integration tests.

##### 3. All services produced using (for example) Python or NodeJS are respectively located in sub folders under the project root, prefixed with the language name, e.g. 'nodejs/yyy'.
- 