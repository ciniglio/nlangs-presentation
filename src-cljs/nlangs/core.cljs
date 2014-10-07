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

(defn onload []
  )

(set! (.-onload js/window) onload)
