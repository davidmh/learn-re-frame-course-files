(ns app.components.icons
  (:require
   ["@mui/icons-material/AccessTime" :default AccessTime]
   ["@mui/icons-material/Add" :default Add]
   ["@mui/icons-material/ChevronLeft" :default ChevronLeft]
   ["@mui/icons-material/Favorite" :default Favorite]
   ["@mui/icons-material/FavoriteBorder" :default FavoriteBorder]
   [reagent.core :as r]))

(def access-time (r/adapt-react-class AccessTime))
(def add (r/adapt-react-class Add))
(def chevron-left (r/adapt-react-class ChevronLeft))
(def favorite (r/adapt-react-class Favorite))
(def favorite-border (r/adapt-react-class FavoriteBorder))
