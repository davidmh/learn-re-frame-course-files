(ns app.components.page-nav
  (:require [app.components.mui :refer [box button typography]]
            [app.components.icons :refer [chevron-left]]))

(defn page-nav
  [{:keys [left center right]}]
  [box {:sx {:py 5
             :display "flex"
             :justify-content "space-between"}}
   [box {} (when left
             [button {:as "a"
                      :aria-label "Back"
                      :href left}
              [chevron-left]])]
   [box {} [typography {:variant "h3"
                        :font-weight 700}
            center]]
   [box {} (when right
             right)]
   ])
