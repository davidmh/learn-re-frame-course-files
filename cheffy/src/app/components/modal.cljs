(ns app.components.modal
  (:require
   [app.components.mui :refer [box dialog dialog-title]]
   [re-frame.core :as rf]
   [app.color :refer [color]]))

(defn modal
  [{:keys [modal-name header container-props body footer]}]
  (let [active-modal @(rf/subscribe [:active-modal])]
    [dialog {:open (= active-modal modal-name)
             :on-close #(rf/dispatch [:close-modal])}
     [dialog-title header]
     [box (merge {} (or container-props {}))
      [box {:px 3 :pb 3} body]
      [box {:display :flex
            :justify-content :flex-end
            :background-color (color "grey" 100)
            :p 2}
       footer]]]))
