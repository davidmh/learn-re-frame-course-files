(ns app.nav.views.authenticated
  (:require [app.components.mui :refer [box tabs tab]]))

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
                      :href "#profile"}]]
      (for [{:keys [id name href]} nav-items]
        [tab {:component "a"
              :href href
              :key id
              :label name}]))]])
