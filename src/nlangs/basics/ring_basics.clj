(ns nlangs.ring-basics
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [clojure.pprint :refer [pprint]]))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World"})

(def server
  (run-jetty handler {:port 3000 :join? false}))

(defn stop [] (.stop server))

(stop)



















;; What's in a request?

(def req (atom nil))

(defn inspector [request]
  (reset! req request) ;; Save the request to look at later
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World"})

(def server
  (run-jetty inspector {:port 3000 :join? false}))

(defn stop [] (.stop server))


;; make the request

(pprint @req)

(keys @req)

(:query-string @req)

(:uri @req)

(stop)







;; Basic routing

(defn router [request]
  (case (:uri request)
    "/" {:status 200
         :headers {"Content-Type" "text/html"}
         :body "Hello World"}
    "/clojure" {:status 200
                :headers {"Content-Type" "text/html"}
                :body "Clojure rocks!"}))

(def server
  (run-jetty router {:port 3000 :join? false}))

(defn stop [] (.stop server))

;; make the request

(stop)















;; Request and response transformations

(require 'clojure.string)

(defn uppercaser [response]
  (update-in response [:body] clojure.string/upper-case))

(defn router [request]
  (case (:uri request)
    "/" {:status 200
         :headers {"Content-Type" "text/html"}
         :body "Hello World"}
    "/clojure" {:status 200
                :headers {"Content-Type" "text/html"}
                :body "Clojure rocks!"}))

(router {:uri "/"})

(uppercaser (router {:uri "/"}))

((comp uppercaser router) {:uri "/"})

(def server
  (run-jetty (comp uppercaser router)
             {:port 3000 :join? false}))

(defn stop [] (.stop server))

;; make the request

(stop)



;; Middleware

(defn uppercaser-m [handler]
  (fn [request]
    (let [response (handler request)]
      (update-in response [:body] clojure.string/upper-case))))

(defn handler [request]
  {:status 200
   :body "Handled"})

(fn? (uppercaser-m handler))

((uppercaser-m handler) {})

(def server
  (run-jetty (uppercaser-m handler)
             {:port 3000 :join? false}))

(defn stop [] (.stop server))

;; make the request
(stop)











;; Middleware stacking

(defn excited-m [handler]
  (fn [request]
    (let [response (handler request)]
      (update-in response [:body] str "!!!"))))

(def server
  (run-jetty (excited-m (uppercaser-m handler))
             {:port 3000 :join? false}))

(defn stop [] (.stop server))

;; make the request
(stop)


;; Modify requests

(defn request-sizer [request]
  (let [path-length (dec (count (:uri request)))]
    (assoc request :path-length path-length)))

(defn request-modifier-m [handler]
  (fn [request]
    (let [parsed-request (request-sizer request)
          response (handler parsed-request)]
      response)))

(defn handler [request]
  (let [path-length (:path-length request 0)]
    {:status 200
     :body (str "You entered a path that is "
                path-length " characters long")}))

(def server
  (run-jetty (request-modifier-m handler)
             {:port 3000 :join? false}))

(defn stop [] (.stop server))

;; make the request
(stop)








;; Compojure

(require '[compojure.core :refer :all])

(defroutes app
  (GET "/" [] "<h1>Hello World</h1>"))

(def server
  (run-jetty app
             {:port 3000 :join? false}))

(defn stop [] (.stop server))

;; make the request
(stop)
