# JavaWhiz HATEOAS Basic

This project is a small Spring Boot example that demonstrates how to build a paginated REST API using Spring HATEOAS.

Instead of returning only raw order data, the API returns:

- A collection of `Order` resources
- Hypermedia links (`self`, `previous`, `next`) that help clients navigate pages

It is designed as a learning/demo project for understanding HATEOAS basics in Spring.

## What the app does

- Exposes one endpoint at `/`
- Accepts query parameters:
  - `start` (default: `1`) -> first order id in the page
  - `count` (default: `10`) -> number of orders to return
- Generates sample in-memory orders dynamically (`Order 1`, `Order 2`, etc.)
- Returns a HATEOAS `CollectionModel<Order>` with pagination links

## Tech stack

- Java (`toolchain` configured to Java 25 in `build.gradle`)
- Spring Boot
- Spring HATEOAS
- springdoc OpenAPI (Swagger UI)
- Lombok
- Gradle

## Run locally

```bash
./gradlew bootRun
```

App starts on the default port `8080` unless overridden.

## API usage

Example request:

```text
http://localhost:8080/?start=11&count=10
```

Example response:

```json
{
  "_embedded": {
    "orderList": [
      {
        "id": 11,
        "name": "Order 11",
        "description": "Description 11",
        "amount": 110
      },
      {
        "id": 12,
        "name": "Order 12",
        "description": "Description 12",
        "amount": 120
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/?start=11&count=10"
    },
    "previous": {
      "href": "http://localhost:8080/?start=1&count=10"
    },
    "next": {
      "href": "http://localhost:8080/?start=21&count=10"
    }
  }
}
```

## OpenAPI / Swagger UI

After starting the app, open:

```text
http://localhost:8080/swagger-ui.html
```

## Project structure (high level)

- `controller/OrderController.java` -> endpoint + HATEOAS links
- `service/OrderService.java` -> sample order generation
- `model/Order.java` -> order DTO/model
- `config/OpenApiConfig.java` -> API metadata configuration
