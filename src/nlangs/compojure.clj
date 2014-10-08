(ns nlangs.compojure
  (:require [compojure.core :refer :all]
            [compojure.route :refer [resources]]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.json :refer [wrap-json-response
                                          wrap-json-params]]
            [nlangs.atom-api :as api]))

(defroutes app
  (GET "/" [] "<h1>Hello World!!!</h1>")
  (GET "/grocery-list" [] {:body (api/grocery-list)})
  (PUT "/grocery-list" [name bought]
       (do (prn name)
         {:body (api/update-item-bought name bought)}))
  (resources "/"))

(def server
  (run-jetty (-> app wrap-json-response wrap-json-params)
   {:port 3000 :join? false}))

(defn stop [] (.stop server))

;; make the request
(stop)
