(ns app.recipes.views.recipes-page
  (:require
   [app.components.mui :refer [typography]]
   [app.components.page-nav :refer [page-nav]]
   [app.recipes.views.recipe-list :refer [recipe-list]]
   [re-frame.core :as rf]))

(defn recipes-page
  []
  (let [public @(rf/subscribe [:public])
        drafts @(rf/subscribe [:drafts])
        logged-in? true]
  [:<>
   [page-nav {:center "Recipes"}]
   (when (seq drafts)
     [:<>
      [typography {:variant :h4 :py 2 :font-weight 700} "Drafts"]
      [recipe-list drafts]])
   (when logged-in?
     [typography {:variant :h4 :py 2 :font-weight 700} "Public"])
   [recipe-list public]]))
