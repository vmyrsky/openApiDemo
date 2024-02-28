# Instructions for running a SpringBoot application
1. Build with maven (repackage)
2. Build with docker (default)
3. Run with docker (expose correct port)

## Maven:
  - **Running springboot application**
    - `mvn spring-boot:run`
  - **Build the application for docker (executable jar)**
    - `mvn clean package spring-boot:repackage`
    - See pom.xml =>  _properties.start-class_

## Docker:
  - **docker builder:**  _https://docs.docker.com/engine/reference/builder/_
  - **docker-compose:**  _https://docs.docker.com/compose/compose-file/compose-file-v3/_
  - **Build**
    - Dockerfile (default with only executable jar)
        - `docker build --tag <imageName> .`
    - Dockerfile_dev (with src contents)
        - `docker build -f Dockerfile_dev --tag <imageName>`
        - `docker build -f Dockerfile_dev --tag <imageName>:<tag>`
  - **Add tag** (refer to same build as :latest)
    - `docker tag <imageName>:latest <imageName>:<newTag>`
  - **Run** (**D**etach, expose **P**ort, **NAME** container)
    - `docker run -d -p 8080:8080 --name <containerName> <imageName>`
  - **View Logs**
    - `docker logs <containerName>`
  - **Stop**
    - `docker stop <containerName>`
  - **Remove old container**
    - `docker rm <containerName>`