(ns nlangs.core-async
  (:require
    [dommy.utils :as utils]
    [dommy.core :as dommy]
    [ajax.core :refer [GET POST PUT]]
    [cljs.core.async :refer [chan <! >! timeout alts! put!]])
  (:require-macros
   [cljs.core.async.macros :refer [go go-loop]])
  (:use-macros
    [dommy.macros :only [node sel sel1]]))

(enable-console-print!)

(declare get-all-items)

(defn mark-bought [item node event]
  (let [checked (.-checked node)]
    (PUT "/grocery-list" {:params (assoc item "bought" checked)
                          :format :json
                          :handler get-all-items})))

(defn add-item-to-list [list item]
  (let [checkbox (node [:input {:type "checkbox"
                                :checked (item "bought")}])
        _ (dommy/listen! checkbox :click
                         (partial mark-bought item checkbox))
        n (node [:li [:span checkbox (item "name")]])]
    (dommy/append! list n)))

(defn get-all-items []
  (let [render-items (fn [items]
                       (let [glist (sel1 :.grocery-list)]
                         (dommy/clear! glist)
                         (doall (map
                                 (partial add-item-to-list glist)
                                 items))))]
    (GET "/grocery-list" {:handler render-items})))

(defn listen-to-form []
  (let [form (sel1 :form#new-item)
        add-new-item (fn [form event]
                       (let [input (sel1 form :input)
                             name (.-value input)]
                         (POST "/grocery-list" {:params {:name name :bought false}
                                                :format :json})
                         (set! (.-value input) "")
                         (get-all-items)
                         (.preventDefault event)
                         (.stopPropagation event)))]
    (dommy/listen! form :submit (partial add-new-item form))))

(defn start-clearer []
  (let [text-field (sel1 [:form#new-item :input])
        c (chan)]
    (go-loop [t (timeout 5000)]
      (let [[v ch] (alts! [c t])]
        (if (identical? ch t)
          (set! (.-value text-field) "")
          (recur (timeout 5000)))))
    (dommy/listen! text-field :input #(put! c %))))

(defn onload []
  (listen-to-form)
  (start-clearer)
  (get-all-items))

(set! (.-onload js/window) onload)
