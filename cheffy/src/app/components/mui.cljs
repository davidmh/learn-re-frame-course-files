(ns app.components.mui
  (:require [reagent.core :as r]
            ["@mui/material/Box" :default MuiBox]
            ["@mui/material/Button" :default MuiButton]
            ["@mui/material/Container" :default MuiContainer]
            ["@mui/material/Grid" :default MuiGrid]
            ["@mui/material/Tab" :default MuiTab]
            ["@mui/material/Tabs" :default MuiTabs]
            ))

(def button (r/adapt-react-class MuiButton))
(def box (r/adapt-react-class MuiBox))
(def container (r/adapt-react-class MuiContainer))
(def grid (r/adapt-react-class MuiGrid))
(def tab (r/adapt-react-class MuiTab))
(def tabs (r/adapt-react-class MuiTabs))
