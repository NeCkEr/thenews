(ns ^:figwheel-no-load server.repl
  (:require [cljs.nodejs :as nodejs]
            [figwheel.client :as fw]
            [thenews.core :as core]))

(nodejs/enable-util-print!)
(fw/start {:websocket-url (str "ws://localhost:3455" "/figwheel-ws")})
(println "here?")

(set! *main-cli-fn*
      (fn []
        (core/start-app false 3000)))
