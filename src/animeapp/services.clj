(ns animeapp.services
  (:require [animeapp.queries :as queries]))

(defn get-all-animes
  []
  (queries/get-all-animes))

(defn get-anime-by-id
  [anime_id]
  (queries/get-anime-by-id anime_id))

(defn delete-anime
  [anime_id]
  (queries/delete-anime anime_id))

(defn new-anime
  [title, jp-title, genero, descripcion]
  (queries/new-anime title, jp-title, genero, descripcion))

(defn update-anime-title
  [anime-title]
  (queries/update-anime-title anime-title))

(defn search-by-title
  [anime-title]
  (queries/search-by-title anime-title))
