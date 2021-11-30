(ns app.components.icons
  (:require [reagent.core :as r]
            ["@mui/icons-material/ChevronLeft" :default ChevronLeft]))

(def chevron-left (r/adapt-react-class ChevronLeft))
