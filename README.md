# Anime Catalog Clojure

**Anime Catalog Clojure** is a full-stack (that can be quoted :-) ) project built with Clojure. It provides a RESTful API to manage and display anime information using a MariaDB database and a simple web frontend that consumes this API. This project is an excellent example for beginners to learn about backend development with Clojure, handling database connections, and creating basic web interfaces.

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
  - [Clone the Repository](#clone-the-repository)
  - [Configure the Database](#configure-the-database)
- [Running the Project](#running-the-project)
  - [Starting the Backend](#starting-the-backend)
  - [Running the Frontend](#running-the-frontend)
- [API Endpoints](#api-endpoints)
- [Project Structure](#project-structure)
- [Example Requests](#example-requests)
  - [Using curl](#using-curl)
- [License](#license)
- [Contributing](#contributing)
- [Contact](#contact)

## Features

- RESTful API built using Compojure and Ring.
- Database connectivity using next.jdbc to interact with a MariaDB database.
- Basic CRUD operations for managing anime records.
- A simple web frontend (HTML/JavaScript) to display the titles in catalog of animes.
- Configured with Clojure CLI tools using a `deps.edn` file.

## Requirements

- Java (JDK 8 or higher)
- Clojure (see [Clojure Getting Started](https://clojure.org/guides/getting_started))
- MariaDB (or any compatible database; adjust connection settings in the code)
- Optionally, a tool for serving static files (e.g., Python's `http.server`)

## Installation

### Clone the Repository

```bash
git clone https://github.com/yourusername/anime-catalog-clojure.git
cd anime-catalog-clojure
```

### Configure the Database

Edit the file `src/animeapp/dbconn.clj` to match your database credentials.

Create a MariaDB database named (for example) `mylist` and a table named `anime`. Here is a simple SQL example (adjust as needed):

```sql
CREATE DATABASE mylist;
USE mylist;
CREATE TABLE anime (
  anime_id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  jp_title VARCHAR(255),
  genero VARCHAR(100),
  descripcion TEXT
);
```

## Running the Project

### Starting the Backend

The backend is configured with a run alias in the `deps.edn` file.

Run the API server:

```bash
clojure -M:run-m
```

The server will start on port `3000`. You can access the API at:

```
http://localhost:3000/anime
```

### Running the Frontend

The frontend files are located in `src/animeapp/web`.

**Open the Frontend:**

- Simply open `src/animeapp/web/index.html` in your web browser.

## API Endpoints

Here are the primary API endpoints provided by the backend:

- **GET /anime**  
  Retrieves all anime records.

- **GET /anime/search/:title**  
  Searches for anime records by title (or Japanese title).

- **POST /anime**  
  Creates a new anime record. Expects a JSON body with keys: `title`, `jp_title`, `genero`, and `descripcion`.

- **PUT /anime/:id**  
  Updates an anime record by its ID. Expects a JSON body with keys like `title` and `jp_title`.

- **DELETE /anime/:id**  
  Deletes an anime record by its ID.

## Project Structure

```
anime-catalog-clojure/
├── deps.edn                  ; Dependency configuration and aliases
├── src/
│   └── animeapp/
│       ├── core.clj         ; API routes, web server setup, JSON response helper, and CORS middleware
│       ├── dbconn.clj       ; Database connection configuration using next.jdbc
│       ├── queries.clj      ; CRUD SQL queries
│       └── services.clj     ; Service layer wrapping the query functions
│       └── web/             ; Frontend folder
│           ├── index.html   ; Basic HTML page consuming the API
│           └── some.js      ; JavaScript code to fetch and display anime data
└── README.md                ; This file
```

## Example Requests

### Using curl

- **Get all animes:**

  ```bash
  curl http://localhost:3000/anime
  ```

- **Search anime by title:**

  ```bash
  curl http://localhost:3000/anime/search/One
  ```

- **Create a new anime:**

  ```bash
  curl -X POST http://localhost:3000/anime \
       -H "Content-Type: application/json" \
       -d '{"title": "Naruto", "jp_title": "ナルト", "genero": "Shonen", "descripcion": "A popular anime series."}'
  ```

- **Update an anime (replace `:id` with the actual anime ID):**

  ```bash
  curl -X PUT http://localhost:3000/anime/1 \
       -H "Content-Type: application/json" \
       -d '{"title": "Naruto Shippuden", "jp_title": "ナルト 疾風伝"}'
  ```

- **Delete an anime (replace `:id` with the actual anime ID):**

  ```bash
  curl -X DELETE http://localhost:3000/anime/1
  ```

## License

This project is licensed under the terms of the **Eclipse Public License v2.0**. See the LICENSE file for details.

## Contributing

Contributions are welcome! Feel free to fork the repository and submit pull requests. For any issues or questions, please open an issue on GitHub.

## Contact

For questions or suggestions, please contact:

*Isaac Narváez*
Email: [isaac.rkt@proton.me](mailto:isaac.rkt@proton.me)
