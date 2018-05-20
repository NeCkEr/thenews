(ns repl
  (:require [figwheel-sidecar.system :as sys]
            [figwheel-sidecar.repl-api :refer [start-figwheel! stop-figwheel! cljs-repl]]))

(def fig-config
  {:figwheel-options {:server-port 3455
                      :css-dirs ["resources/public/css"]}
   :build-ids ["server"]
   :all-builds
   [{:id "server"
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

(comment

  (start!)
  (node-server-repl!)
  (stop!)

);
