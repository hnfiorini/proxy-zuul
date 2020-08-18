# Proxy - Mercado Libre

Implementation of a proxy for Mercado Libre's apis.

## Design

![MELI](https://user-images.githubusercontent.com/39138029/90557261-958f3a80-e170-11ea-8acd-ac40653edcbb.png)

## Requirements - Getting Started

[Docker](https://www.docker.com/) installation required.
In the root project, run:

```bash
docker-compose up
```

## Usage / Test
With the project running on your local machine, execute

```python
curl 127.0.0.1:8080/categories/MLA97994

```

## Technologies used
* Spring Boot
* Zuul
* Maven
* JPA
