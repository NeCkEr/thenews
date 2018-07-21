(ns thenews.core-client
  (:require [rum.core :as rum]
            [s-ui.core :as s-ui]))


(rum/defc label [text]
  [:div.ui
   
     [:div.ui.container
      [:div.ui.grid.stackable
       [:div.row
        [:div.column.sixteen.wide.computer
         [:div {on-click (fn [e]
                           (js/console.log "weee")
                           )}
          "click me sss "
          "weee"]



         [:div
           "Hacker News"]]]]]])

(defn render-layout []
  (label "wee"))

(defn on-js-reload
  []
  (rum/mount (render-layout)
    (js/document.getElementById "app")))

(defn ^:exports init
  []
  (rum/mount (render-layout)
    (js/document.getElementById "app")))
