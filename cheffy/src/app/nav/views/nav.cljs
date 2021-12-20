(ns app.nav.views.nav
  (:require [re-frame.core :as rf]
            [app.components.mui :refer [tabs tab box]]
            [app.helpers :refer [find-index]]
            [app.router :as router]))

(def authenticated [{:id :saved
                     :name "Saved"
                     :href :saved}
                    {:id :recipes
                     :name "Recipes"
                     :href :recipes}
                    {:id :inboxes
                     :name "Inbox"
                     :href :inboxes}
                    {:id :become-a-chef
                     :name "Chef"
                     :href :become-a-chef}
                    {:id :profile
                     :name "Profile"
                     :href :profile}])

(def public [
             {:id :recipes
              :name "Recipes"
              :href :recipes}
             {:id :become-a-chef
              :name "Chef"
              :href :become-a-chef}
             {:id :sign-up
              :name "Sign up"
              :href :sign-up}
             {:id :log-in
              :name "Log in"
              :href :log-in}])

(defn nav
  []
  (let [logged-in? @(rf/subscribe [:logged-in?])
        nav-items (if logged-in? authenticated public)
        active-page @(rf/subscribe [:active-page])
        selected-nav-index (or (find-index (fn [{:keys [id]}]
                                          (= id active-page)) nav-items)
                               0)
        select-tab #(rf/dispatch [:set-active-page %])]

    [box {:sx {:border-bottom 1
               :border-color "divider"
               :display "flex"
               :justify-content "flex-end"}}
     [tabs {:aria-label "menu" :value selected-nav-index}
      (for [{:keys [id name href]} nav-items]
        [tab {:component "a"
              :href (router/path-for href)
              :key id
              :label name
              :on-click #(select-tab id)}])]]))
