(ns app.core
  (:require [reagent.dom :as rdom]))

(defn app
  []
  [:div "Cheffy"])

(defn ^:dev/after-load start
  []
  (rdom/render [app]
    (.getElementById js/document "app")))

(defn ^:export init
  []
  (start))
