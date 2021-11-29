(ns app.core
  (:require [reagent.dom :as rdom]
            [re-frame.core :as rf]
            [app.db]
            [app.components.mui :refer [button]]
            [app.theme :refer [theme]]
            ["@mui/material/styles" :refer [ThemeProvider createTheme]]))

(defn app
  []
  [:> ThemeProvider {:theme (createTheme (clj->js theme))}
   [button {:variant "contained"} "Cheffy"] ])

(defn ^:dev/after-load start
  []
  (rf/dispatch-sync [:initialize-db])
  (rdom/render [app]
    (.getElementById js/document "app")))

(defn ^:export init
  []
  (start))
