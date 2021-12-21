(ns app.recipes.views.recipe-ingredients 
  (:require
   [app.components.mui :refer [grid paper typography box]]
   [re-frame.core :as rf]))

(defn recipe-ingredients []
  (let [ingredients @(rf/subscribe [:ingredients])]
    [paper {:elevation 1
            :sx {:p 2}}
     [box {:py 2 :display :flex}
      [typography {:variant :h5} "Ingredients"]
      [typography "PLUS"]]
     [grid {:container true}
      (for [{:keys [id amount measure name]} ingredients]
        ^{:key id}
        [:<>
         [grid {:xs 6} [typography amount " " measure]]
         [grid {:xs 6} [typography name]]])]]))
