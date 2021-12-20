(ns app.recipes.views.recipes-page
  (:require
    [app.components.page-nav :refer [page-nav]]
    [app.components.mui :refer [typography]]
    [re-frame.core :as rf]))

(defn recipes-page
  []
  (let [public @(rf/subscribe [:public])
        drafts @(rf/subscribe [:drafts])
        logged-in? true]
  [:<>
   [page-nav {:center "Recipes"}]
   (when (seq drafts)
      [typography {:variant :h4 :py 2 :font-weight 700} "Drafts"]
      #_[recipe-list drafts])
   (when logged-in?
     [typography {:variant :h4 :py 2 :font-weight 700} "Public"])
   #_[recipe-list public]]))
