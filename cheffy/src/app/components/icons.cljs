(ns app.components.icons
  (:require
   ["@mui/icons-material/AccessTime" :default AccessTime]
   ["@mui/icons-material/ChevronLeft" :default ChevronLeft]
   ["@mui/icons-material/Favorite" :default Favorite]
   [reagent.core :as r]))

(def access-time (r/adapt-react-class AccessTime))
(def chevron-left (r/adapt-react-class ChevronLeft))
(def favorite (r/adapt-react-class Favorite))
