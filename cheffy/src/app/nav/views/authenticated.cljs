(ns app.nav.views.authenticated
  (:require [app.components.mui :refer [box tabs tab]]
            [re-frame.core :as rf]))

(defn authenticated
  []
  [box {:sx {:border-bottom 1
             :border-color "divider"
             :display "flex"
             :justify-content "flex-end"}}
   [tabs {:aria-label "menu" :value 0}
    (let [nav-items [{:id :saved
                      :name "Saved"
                      :href "#saved"}
                     {:id :recipes
                      :name "Recipes"
                      :href "#recipes"}
                     {:id :inbox
                      :name "Inbox"
                      :href "#inbox"}
                     {:id :chef
                      :name "Chef"
                      :href "#chef"}
                     {:id :profile
                      :name "Profile"
                      :href "#profile"}]
          select-tab #(rf/dispatch [:set-active-nav %])]
      (for [{:keys [id name href]} nav-items]
        [tab {:component "a"
              :href href
              :key id
              :label name
              :on-click #(select-tab id)}]))]])
