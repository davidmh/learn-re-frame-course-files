(ns app.core
  (:require [reagent.dom :as rdom]
            [re-frame.core :as rf]

            [app.db]
            [app.router :as router]

            ;; -- nav --
            [app.nav.views.nav :refer [nav]]
            [app.nav.events]
            [app.nav.subs]

            ;; -- page contents --
            [app.auth.views.profile :refer [profile]]
            [app.auth.views.sign-up :refer [sign-up]]
            [app.auth.views.log-in :refer [log-in]]
            [app.auth.events]
            [app.auth.subs]

            [app.become-a-chef.views.become-a-chef :refer [become-a-chef]]
            [app.inbox.views.inboxes :refer [inboxes]]

            [app.recipes.views.recipes-page :refer [recipes-page]]
            [app.recipes.subs]

            [app.components.mui :refer [grid container]]
            [app.theme :refer [theme]]
            ["@mui/material/styles" :refer [ThemeProvider createTheme]]))

(defn pages
  [page-name]
  (case page-name
    :profile [profile]
    :sign-up [sign-up]
    :log-in [log-in]
    :become-a-chef [become-a-chef]
    :inbox [inboxes]
    :recipes [recipes-page]
    [recipes-page]))

(defn app
  []
  (let [active-page @(rf/subscribe [:active-page])]
    [:> ThemeProvider {:theme (createTheme (clj->js theme))}
     [container
      [grid {:item true :xs 12}
       [nav]]
      [grid {:item true :xs 12}
       [pages active-page]]]]))

(defn ^:dev/after-load start
  []
  (rdom/render [app]
    (.getElementById js/document "app")))

(defn ^:export init
  []
  (router/start!)
  (rf/dispatch-sync [:initialize-db])
  (start))
