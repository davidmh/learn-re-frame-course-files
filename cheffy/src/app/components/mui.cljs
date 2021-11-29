(ns app.components.mui
  (:require [reagent.core :as r]
            ["@mui/material/Button" :default MuiButton]))

(def button (r/adapt-react-class MuiButton))
