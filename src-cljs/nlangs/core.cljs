(ns nlangs.core
  (:require
    [dommy.utils :as utils]
    [dommy.core :as dommy]
    [ajax.core :refer [GET POST]])
  (:use-macros
    [dommy.macros :only [node sel sel1]]))

(enable-console-print!)

(print "Hello clojurescript!")

(defn add-item-to-list [list item]
  (prn "Item: " item)
  (let [n (node [:li (item "name")])]
    (dommy/append! list n)))

(defn render-items [items]
  (let [glist (sel1 :.grocery-list)]
    (doall (map (partial add-item-to-list glist) items))))

(defn onload []
  (GET "/grocery-list" {:handler render-items}))

(set! (.-onload js/window) onload)
