(ns s-ui.core
  (:require [rum.core :as rum]))

(defn- attrs-contents [xs]
  (let [[attrs]  xs
        map?     (map? attrs)
        attrs    (if map? attrs {})
        contents (if map? (rest xs) xs)]
    [attrs contents]))

(defn n->grid-enum
  [n]
  (let [numbers [[1 "one"]
                 [2 "two"]
                 [3 "three"]
                 [4 "four"]
                 [5 "five"]
                 [6 "six"]
                 [7 "seven"]
                 [8 "eight"]
                 [9 "nine"]
                 [10 "ten"]
                 [11 "eleven" ]
                 [12 "twelve"]
                 [13 "thirteen"]
                 [14 "fourteen"]
                 [15 "fifteen"]
                 [16 "sixteen"]]]
    (->> numbers 
         (filter (fn [[v t]]
                   (= v n)))
         first
         second)))

(rum/defc Grid 
  [& xs]
  (let [[attrs contents] (attrs-contents xs)]
    [:div.ui.grid attrs
     [(sablono.interpreter/interpret contents)]]))

(rum/defc Grid-Row
  [& xs]
  (let [[attrs contents] (attrs-contents xs)]
    [:div.row attrs
     [(sablono.interpreter/interpret contents)]]))

(rum/defc Grid-Collumn
  ;; Semantic-UI implemented attrs 
  ;;   NAME          Default      type               Description
  ;;Responsive Width
  ;;   :computer                  number (1...16)    A column can specify a width for a computer.
  ;;   :tablet                    number (1...16)    A column can specify a width for a tablet.
  ;;   :mobile                    number (1...16)    A column can specify a width for a mobile device.
  ;;   :widescreen                number (1...16)    A column can specify a width for a wide screen device.
  ;;
  [& xs]
  (let [[attrs contents] (attrs-contents xs)
        s-attrs (:s-ui attrs)
        responsive-class (->> (select-keys s-attrs  [:computer :tablet :mobile :widescreen])
                              (map (fn [[device n]]
                                     [(n->grid-enum n) "wide" (name device)]))
                              (flatten)
                              (clojure.string/join " "))
        el-attrs (-> attrs
                     (dissoc :s-ui)
                     (assoc :class responsive-class))]
    [:div.column el-attrs
     [(sablono.interpreter/interpret contents)]]))
