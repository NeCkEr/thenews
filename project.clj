(defproject thenews "app"
  :description "thenews"
  :url "thenews.greenstudiolabs.com"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.10.238"]
                 [org.clojure/core.match "0.3.0-alpha5"]
                 [org.clojure/core.async "0.3.442"]
                 [com.taoensso/timbre "4.10.0"]
                 [rum "0.11.2"]
                 [cljsjs/react-dom "16.2.0-3"]]
  :plugins [[lein-cljsbuild "1.1.7"]]
  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
  :target-path "target/%s"
  :profiles {:dev {:source-paths ["env/dev" "src"]
                     :repl-options {:init-ns repl
                                    :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                     :dependencies [[binaryage/devtools "0.9.9"]
                                    [figwheel-sidecar "0.5.13"]
                                    [com.cemerick/piggieback "0.2.2"]]
                     :plugins      [[lein-figwheel "0.5.13"]]}})
