(ns app.nav.views.authenticated
  (:require [app.components.mui :refer [box tabs tab]]
            [re-frame.core :as rf]
            [app.helpers :refer [find-index]]))

(defn authenticated
  []
  (let [active-nav @(rf/subscribe [:active-nav])
        nav-items [{:id :saved
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
        selected-nav-index (find-index (fn [{:keys [id]}]
                                         (= id active-nav)) nav-items)
        select-tab #(rf/dispatch [:set-active-nav %])]

    [box {:sx {:border-bottom 1
               :border-color "divider"
               :display "flex"
               :justify-content "flex-end"}}
     [tabs {:aria-label "menu" :value selected-nav-index}
      (for [{:keys [id name href]} nav-items]
        [tab {:component "a"
              :href href
              :key id
              :label name
              :on-click #(select-tab id)}])]]))
