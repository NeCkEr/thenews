(ns thenews.core-client
  (:require [rum.core :as rum]
            [s-ui.core :as s-ui]))


(rum/defc label [text]
  [:div.ui
     [:div.ui.container
      [:div.ui.grid.stackable
       [:div.row
        [:div.column.sixteen.wide.computer
         [:div
           "Hacker News"]]]]]])

(defn render-layout []
  (label "wee"))

(defn on-js-load
  [])

(defn ^:exports init
  []
  (rum/mount (render-layout)
    (js/document.getElementById "app")))
