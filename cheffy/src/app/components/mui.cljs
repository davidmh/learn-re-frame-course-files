(ns app.components.mui
  (:require [reagent.core :as r]
            ["@mui/material/Box" :default MuiBox]
            ["@mui/material/Button" :default MuiButton]
            ["@mui/material/Container" :default MuiContainer]
            ["@mui/material/Grid" :default MuiGrid]
            ["@mui/material/FormGroup" :default MuiFormGroup]
            ["@mui/material/Paper" :default MuiPaper]
            ["@mui/material/TextField" :default MuiTextField]
            ["@mui/material/Tab" :default MuiTab]
            ["@mui/material/Tabs" :default MuiTabs]
            ["@mui/material/Typography" :default MuiTypography]
            ))

(def button (r/adapt-react-class MuiButton))
(def box (r/adapt-react-class MuiBox))
(def container (r/adapt-react-class MuiContainer))
(def form-group (r/adapt-react-class MuiFormGroup))
(def paper (r/adapt-react-class MuiPaper))
(def grid (r/adapt-react-class MuiGrid))
(def tab (r/adapt-react-class MuiTab))
(def tabs (r/adapt-react-class MuiTabs))
(def text-field (r/adapt-react-class MuiTextField))
(def typography (r/adapt-react-class MuiTypography))
