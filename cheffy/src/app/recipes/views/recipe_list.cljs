(ns app.recipes.views.recipe-list 
  (:require
    [app.components.mui :refer [box]]))

(defn recipe-list [items]
  [box {:class "cards"}
   (for [recipe items]
     ^{:key (:id recipe)}
     (:name recipe))])
