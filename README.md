# Vaadin14 + MPR-v7 demo project

A stripped down version of the Vaadin's bookstore demo project to 

## Prerequisites

 - JDK8

## Project Structure

The project is following the standard [Maven project layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html).

## Workflow

- Compiling the entire project
  - run `mvn install`
- Running locally (dev mode)
  - run `mvn jetty:run`
  - open http://localhost:8080/
- Running locally (production mode)
  - run `mvn jetty:run -Pproduction`
  - open http://localhost:8080/
