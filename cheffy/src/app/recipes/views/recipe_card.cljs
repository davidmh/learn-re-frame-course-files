(ns app.recipes.views.recipe-card
  (:require
    [app.components.mui :refer [box typography]]
    [app.router :as router]
    [app.components.icons :as icons]))

(defn recipe-card [{:keys [id name saved-count prep-time img]}]
  [box {:as "a"
        :href (router/path-for :recipes)
        :class "recipe-card"}
   [box {:class "img-card"
         :min-height 280
         :alt name
         :sx {:background-image (str "url(" (or img "/img/placeholder.jpg") ")")
              :background-size :cover}}]
   [box {:p 2}
    [typography {:variant :h6
                 :font-weight 700}
     name]]
   [box {:pl 2 :pb 2 :display :flex}
    [box {:display :flex
          :align-items :center
          :pr 4}
     [icons/favorite]
     [typography {:pl 1} saved-count]]
    [box {:display :flex
          :align-items :center
          :pr 4}
     [icons/access-time]
     [typography {:pl 1} prep-time " min"]]]])
