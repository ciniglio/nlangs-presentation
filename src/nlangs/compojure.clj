(ns nlangs.compojure
  (:require [compojure.core :refer :all]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.json :refer [wrap-json-response]]
            [nlangs.api :as api]))

(defroutes app
  (GET "/" [] "<h1>Hello World</h1>")
  (GET "/grocery-list" [] {:body (api/grocery-list)}))

(def server
  (run-jetty (wrap-json-response app)
             {:port 3000 :join? false}))

(defn stop [] (.stop server))

;; make the request
(stop)
