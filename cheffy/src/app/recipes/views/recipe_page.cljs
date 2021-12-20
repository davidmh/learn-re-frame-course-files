(ns app.recipes.views.recipe-page 
  (:require
    [app.components.page-nav :refer [page-nav]]
    [app.components.mui :refer [box grid]]
    [re-frame.core :as rf]))

(defn recipe-page []
  (let [{:keys [name]} @(rf/subscribe [:recipe])]
  [box {}
   [page-nav {:center name}]
   [grid {:container true}
    [grid {:item true :xs 12 :sm 6}
     [box {:pb 2} "recipe-info"]
     [box {:pb 2} "recipe-image"]
     [box {:pb 2} "recipe-ingredients"]]
    [grid {:item true :xs 12 :sm 6}
     [box {:pb 2} "recipe-steps"]]]]))
