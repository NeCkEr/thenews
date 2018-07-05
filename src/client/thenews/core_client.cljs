(ns thenews.core-client
  (:require [rum.core :as rum]
            [antizer.rum :as ant]))

(defn content-area []
  (ant/layout-content {:class "content-area"}
    (ant/row {:gutter 12}
     (ant/col {:span 12}
      [:div "here?"])
     )))

(defn side-menu 
  []
  (ant/menu {:mode "inline" :theme :dark :style {:height "100%"}}
    (ant/menu-item {:key "1"
                    :disabled true} "Menu without icons")
    (ant/menu-item {:key "2"}  "Menu Item")
    (ant/menu-sub-menu {:key "sub1"
                        :title "Sub Menu"}
      (ant/menu-item {:key "sub1.1"} "Item 1")
      (ant/menu-item {:key "sub1.2"} "Item 2"))
    (ant/menu-item {:key "3"
                    :disabled true} "Menu with icons")
    (ant/menu-item {:key "4"} [:span {:key "bb"} (ant/icon {:type "home"}) "Menu Item"])
    (ant/menu-sub-menu {:key "sub2"
                        :title [:span (ant/icon {:type "setting"}) "Sub Menu"]}  
      (ant/menu-item {:key "sub2.1"} 
       [:span {:key "sub2.1.1"} (ant/icon {:type "user"}) "Item 1"])
      (ant/menu-item {:key "sub2.2"} 
       [:span {:key "sub2.2.1"} (ant/icon {:type "notification"}) "Item 2"]))))

(defn render-layout []
    (ant/layout
      (ant/affix
        (ant/layout-header {:class "banner"}
          (ant/row
            (ant/col {:span 12} [:h2.banner-header {:key "layout"} "Antizer Rum Example"])
            (ant/col {:span 1 :offset 11} 
              [:a {:href "https://github.com/priornix/antizer" :key "link"} 
                (ant/icon {:class "banner-logo" :type "github"})]))))
      (ant/layout
        (ant/layout-sider (side-menu))
        (ant/layout {:style {:width "60%"}} 
          #_(content-area)))))


(defn on-js-load
  [])

(defn ^:exports init
  []
  (rum/mount (render-layout)
    (js/document.getElementById "app")))
