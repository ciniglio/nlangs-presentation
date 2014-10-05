(ns nlangs.sql-api
  (:require [yesql.core :refer [defqueries]]))

(def db-spec {:classname "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname "//localhost:5432/nlangs"
              :user "alejandro"})

(defqueries "nlangs/sql/queries.psql")

(get-grocery-items db-spec)

(add-new-grocery-item<! db-spec "Cabbage")
