(ns nlangs.core
  (:require
    [dommy.utils :as utils]
    [dommy.core :as dommy]
    [ajax.core :refer [GET POST PUT]]
    ;[cljs.core.async :refer [chan <! >! timeout]]
    )
  ;; (:require-macros
  ;;  [cljs.core.async.macros :refer [go]])
  (:use-macros
    [dommy.macros :only [node sel sel1]]))

(enable-console-print!)

(prn "Hello there")

(defn checkbox-clicked [item e]
  (PUT "grocery-list" {:format :json
                       :params (update-in item
                                          [:bought] not)}))

(defn listen-to-checkboxes []
  (let [checkboxes (sel :input.checkbox)]
    (doall (map (fn [checkbox]
                  (dommy/listen!
                   checkbox :click
                   checkbox-clicked))
                checkboxes))))

(defn append-item-to-ul [ul item]
  (let [checkbox (node [:input {:class "checkbox"
                                :type "checkbox"
                                :checked (get item "bought")}])
        li (node [:li [:span checkbox (get item "name")]])]
    (dommy/listen! checkbox :click
                   (partial checkbox-clicked item))
    (dommy/append! ul li)))

(defn render-list [grocery-list]
  (let [ul (sel1 :ul.grocery-list)]
    (doall (map (partial append-item-to-ul ul) grocery-list)))
  )

(defn onload []
  (GET "/grocery-list" {:handler render-list}))

(set! (.-onload js/window) onload)
