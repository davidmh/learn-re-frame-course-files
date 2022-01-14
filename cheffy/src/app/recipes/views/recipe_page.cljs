(ns app.recipes.views.recipe-page 
  (:require
    [app.components.page-nav :refer [page-nav]]
    [app.components.mui :refer [box grid]]
    [re-frame.core :as rf]
    [app.recipes.views.recipe-info :refer [recipe-info]]
    [app.recipes.views.recipe-image :refer [recipe-image]]
    [app.recipes.views.recipe-ingredients :refer [recipe-ingredients]]
    [app.recipes.views.recipe-steps :refer [recipe-steps]]
    [app.recipes.views.recipe-editor :refer [recipe-editor]]))

(defn recipe-page []
  (let [{:keys [name]} @(rf/subscribe [:recipe])
        author? @(rf/subscribe [:author?])]
  [box {}
   [page-nav {:center (if author?
                        [recipe-editor]
                        name)}]
   [grid {:container true :gap 2}
    [grid {:item true :xs 12 :sm 5}
     [box {:pb 2} [recipe-info]]
     [box {:pb 2} [recipe-image]]
     [box {:pb 2} [recipe-ingredients]]]
    [grid {:item true :xs 12 :sm 5}
     [box {:pb 2} [recipe-steps]]]]]))
