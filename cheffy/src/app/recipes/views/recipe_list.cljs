(ns app.recipes.views.recipe-list 
  (:require
    [app.components.mui :refer [box]]
    [app.recipes.views.recipe-card :refer [recipe-card]]))

(defn recipe-list [items]
  [box {:class "cards"}
   (for [recipe items]
     ^{:key (:id recipe)}
     [recipe-card recipe])])
