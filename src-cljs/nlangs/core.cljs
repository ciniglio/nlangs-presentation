(ns nlangs.core
  (:require
    [dommy.utils :as utils]
    [dommy.core :as dommy]
    [ajax.core :refer [GET POST PUT]])
  (:use-macros
    [dommy.macros :only [node sel sel1]]))

(enable-console-print!)

(defn mark-bought [item event]
  (.log js/console "You've clicked the: " event " on " item)
  (PUT "/grocery-list" {:params (update-in item ["bought"] not) :format :json}))

(defn add-item-to-list [list item]
  (let [checkbox (node [:input {:type "checkbox" :checked (item "bought")}])
        _ (dommy/listen! checkbox :click (partial mark-bought item))
        n (node [:li [:span checkbox (item "name")]])]
    (dommy/append! list n)))

(defn render-items [items]
  (let [glist (sel1 :.grocery-list)]
    (doall (map (partial add-item-to-list glist) items))))

(defn onload []
  (GET "/grocery-list" {:handler render-items}))

(set! (.-onload js/window) onload)
