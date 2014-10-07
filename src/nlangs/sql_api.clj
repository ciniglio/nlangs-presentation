(ns nlangs.sql-api
  (:require [yesql.core :refer [defqueries]]))

(def db-spec {:classname "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname "//localhost:5432/nlangs"
              :user "alejandro"})

(defqueries "nlangs/sql/queries.psql")

(defn grocery-list []
 (get-grocery-items db-spec))

(defn add-to-grocery-list [name]
  (add-new-grocery-item<! db-spec name false)
  (grocery-list))

(defn update-item-bought
  ([name] (update-item-bought name true))
  ([name bought]
     (update-grocery-item-bought! db-spec bought name)
     (grocery-list)))
