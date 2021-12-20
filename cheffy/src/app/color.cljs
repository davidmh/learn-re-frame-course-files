(ns app.color
  (:require
    ["@mui/material/colors" :as mui-colors]))

(def ^:private colors (js->clj mui-colors))

(defn color [hue shade]
  (get-in colors [(clj->js hue) (str shade)]))
