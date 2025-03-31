(ns animeapp.queries
  (:require [next.jdbc :as jdbc]
            [animeapp.dbconn :refer [ds]]))

(defn get-all-animes
  []
  (try
    (jdbc/execute! ds ["SELECT * FROM anime"])
    (catch java.sql.SQLException e
      {:error (.getMessage e)})))

(defn get-anime-by-id
  [anime_id]
  (try
    (jdbc/execute-one! ds ["SELECT * FROM anime WHERE anime_id=?" anime_id])
    (catch java.sql.SQLException e
      {:error (.getMessage e)})))

(defn delete-anime
  [anime_id]
  (try
    (jdbc/execute-one! ds ["DELETE FROM anime WHERE anime_id=?" anime_id])
    (catch java.sql.SQLException e
      {:error (.getMessage e)})))

(defn new-anime
  [title, jp-title, genero, descripcion]
  (try
    (jdbc/execute-one! ds ["INSERT INTO anime (title, jp_title, genero, descripcion) values (?, ?, ?, ?)" title jp-title genero descripcion])
    (catch java.sql.SQLException e
      {:error (.getMessage e)})))

(defn update-anime-title
  [anime_id new-title new-jp-title]
  (try
    (jdbc/execute-one! ds ["UPDATE anime SET title=?, jp_title=? WHERE anime_id=?" new-title new-jp-title anime_id])
    (catch java.sql.SQLException e
      {:error (.getMessage e)})))

(defn search-by-title
  [title]
  (try
    (jdbc/execute! ds ["SELECT * FROM anime WHERE title LIKE ? OR jp_title LIKE ?" (str "%" title "%") (str "%" title "%")])
    (catch java.sql.SQLException e
      {:error (.getMessage e)})))
