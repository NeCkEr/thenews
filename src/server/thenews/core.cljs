(ns thenews.core
  (:require [cljs.nodejs :as nodejs]))

(defonce http (nodejs/require "http"))
(defonce express (nodejs/require "express"))
(defonce serve-static (nodejs/require "serve-static"))



(def new-object {:beep "boop"})

asdasd

asd

#_(defn test 
  [a b]
  (println a b))

#_(test "eu sou o a " "e eu sou o b")

(defn create-app
  [dev?]
  (let [app (express)]
    (.get app "/health" (fn [req res] (.send res (:beep new-object))))
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
