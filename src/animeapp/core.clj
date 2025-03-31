; Copyright (C) 2025 Isaac Narváez <isaac.rkt@proton.me>
;
; This program and the accompanying materials are made available under the
; terms of the Eclipse Public License v2.0, which is available at
; https://www.eclipse.org/legal/epl-2.0/

(ns animeapp.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :as jetty]
            [ring.util.response :as response]
            [cheshire.core :as json]
            [animeapp.queries :as queries]
            [ring.middleware.cors :refer [wrap-cors]]))

(defn json-response
  [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string data)})

(defroutes app-routes
  ;; Obtener todos los animes
  (GET "/anime" []
    (try
      (json-response (queries/get-all-animes))
      (catch Exception e
        (json-response {:error (.getMessage e)} 500))))
  
  ;; Buscar anime por título
  (GET "/anime/search/:title" [title]
    (try
      (json-response (queries/search-by-title title))
      (catch Exception e
        (json-response {:error (.getMessage e)} 500))))
  
  ;; Eliminar anime por ID
  (DELETE "/anime/:id" [id]
    (try
      (json-response (queries/delete-anime (Integer/parseInt id)))
      (catch Exception e
        (json-response {:error (.getMessage e)} 500))))
  
  ;; Agregar un nuevo anime
  (POST "/anime" {body :body}
    (try
      (let [{:keys [title jp_title genero descripcion]} (json/parse-string (slurp body) true)]
        (json-response (queries/new-anime title jp_title genero descripcion) 201))
      (catch Exception e
        (json-response {:error (.getMessage e)} 500))))
  
  ;; Actualizar un anime por ID
  (PUT "/anime/:id" {body :body id :id}
    (try
      (let [{:keys [title jp_title]} (json/parse-string (slurp body) true)]
        (json-response (queries/update-anime-title (Integer/parseInt id) title jp_title)))
      (catch Exception e
        (json-response {:error (.getMessage e)} 500))))
  
  ;; Ruta para 404 Not Found
  (route/not-found (json-response {:error "404 Not Found"} 404)))

(def app
  (wrap-cors app-routes
    :access-control-allow-origin [#"null" #"http://localhost:8000"]
    :access-control-allow-methods [:get :post :put :delete :options]))

(defn -main []
  (println "Servidor en http://localhost:3000...")
  (jetty/run-jetty app {:port 3000 :join? false}))
