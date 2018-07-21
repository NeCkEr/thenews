(ns repl
  (:require [figwheel-sidecar.system :as sys]
            [figwheel-sidecar.repl-api :refer [start-figwheel! stop-figwheel! cljs-repl]]))

(def fig-config
  {:figwheel-options {:server-port 3455
                      :css-dirs ["resources/public/css"]}
   :build-ids ["server" "client"]
   :all-builds
   [{:id           "client"
     :source-paths ["src/client" "src/isomorphic"]
     :figwheel     {:on-jsload "thenews.core-client/on-js-reload"}
     :compiler     {:main                 "thenews.core-client"
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "/js/compiled/out"
                    :source-map-timestamp true
                    :closure-defines      {'goog.DEBUG true}
                    :preloads             '[devtools.preload]
                    :closure-warnings {:non-standard-jsdoc :off}
                    :external-config      {:devtools/config {:features-to-install :all}}}}
    {:id "server"
      :figwheel true
      :source-paths ["src/server" "env/dev"]
      :compiler {:main "server.repl"
                 :asset-path "target/js/compiled/dev"
                 :output-to "target/js/compiled/server.js"
                 :output-dir "target/js/compiled/dev"
                 :target :nodejs
                 :npm-deps
                 {:express "^4.16.2"
                  :serve-static "^1.13.1"
                  :ws "3.2.0"}
                 :install-deps true
                 :optimizations :none
                 :source-map-timestamp true
                 :closure-defines {'goog.DEBUG true}
                 }}]})


(defn start!
  []
  (start-figwheel! fig-config))

(defn stop!
  []
  (stop-figwheel!))

(defn node-server-repl!
  []
  (cljs-repl "server"))

(defn b-repl!
  []
  (cljs-repl "client"))

(comment
  
  (start!)
  (node-server-repl!)
  (b-repl!)
  (stop!)

);
