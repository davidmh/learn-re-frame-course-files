(ns app.recipes.views.recipe-info 
  (:require
   [app.components.mui :refer [paper typography box]]
   [app.components.icons :as icons]
   [app.color :refer [color]]
   [re-frame.core :as rf]))

(defn recipe-info []
  (let [{:keys [id cook saved-count prep-time]} @(rf/subscribe [:recipe])
        {:keys [saved]} @(rf/subscribe [:user])
        logged-in? @(rf/subscribe [:logged-in?])
        saved? (contains? saved id)
        author? @(rf/subscribe [:author?])
        can-save? (and logged-in? (not author?) (not saved?))]
    [paper {:sx {:p 2}}
     [typography {:variant :h5} cook]
     [box {:display :flex}
      [box {:display :flex
            :align-items :center
            :pr 4}
       (if saved?
         [icons/favorite {:sx {:color (color :red 500)}}]
         [box {:as (if can-save? "a" "span")
               :href "#"
               :on-click (when can-save? #(rf/dispatch [:save-recipe id]))}
          [icons/favorite-border]])
       [typography {:pl 1} saved-count]]
      [box {:display :flex
            :align-items :center
            :pr 4}
       [icons/access-time]
       [typography {:pl 1} prep-time " min"]]]]))
