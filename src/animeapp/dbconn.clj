(ns animeapp.dbconn
  (:require [next.jdbc :as jdbc]))

(def db-spec
  {:dbtype "mariadb"
   :dbname "mylist"
   :host "localhost"
   :port 3306
   :user "root"
   :password "12345"})

(def ds (jdbc/get-datasource db-spec))
