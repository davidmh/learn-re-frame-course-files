(ns app.nav.views.nav
  (:require [re-frame.core :as rf]
            [app.components.mui :refer [tabs tab box]]
            [app.helpers :refer [find-index]]))

(def authenticated [{:id :saved
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
                     :href "#profile"}])

(def public [
             {:id :recipes
              :name "Recipes"
              :href "#recipes"}
             {:id :chef
              :name "Chef"
              :href "#chef"}
             {:id :sign-up
              :name "Sign up"
              :href "#sign-up"}
             {:id :log-in
              :name "Log in"
              :href "#log-in"}])

(defn nav
  []
  (let [logged-in? @(rf/subscribe [:logged-in?])
        nav-items (if logged-in? authenticated public)
        active-nav @(rf/subscribe [:active-nav])
        selected-nav-index (or (find-index (fn [{:keys [id]}]
                                          (= id active-nav)) nav-items)
                               0)
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
