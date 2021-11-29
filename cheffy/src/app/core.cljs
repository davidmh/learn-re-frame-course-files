(ns app.core
  (:require [reagent.dom :as rdom]
            [app.components.mui :refer [button]]
            [app.theme :refer [theme]]
            ["@mui/material/styles" :refer [ThemeProvider createTheme]]))

(defn app
  []
  [:> ThemeProvider {:theme (createTheme (clj->js theme))}
   [button {:variant "contained"} "Cheffy"] ])

(defn ^:dev/after-load start
  []
  (rdom/render [app]
    (.getElementById js/document "app")))

(defn ^:export init
  []
  (start))
