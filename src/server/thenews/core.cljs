(ns thenews.core
  (:require [cljs.nodejs :as nodejs]))

(defonce http (nodejs/require "http"))
(defonce express (nodejs/require "express"))
(defonce serve-static (nodejs/require "serve-static"))

(defn create-app
  [dev?]
  (let [app (express)]
    (.get app "/health" (fn [req res] (.send res "ok")))
    (.use app (serve-static "resources/public"))
    app))

(defn start-app
  [prod? port]
  (let [app (if prod?
              (create-app (not prod?))
              ;; This is the secret sauce.
              ;; this allows you to change routes and have them hot loaded as you code.
              (fn [req res]
                ((create-app (not prod?)) req res)))]
    (doto (.createServer http app)
      (.listen port))
    (println (str "server running on port " port "! Yey!"))))
