(ns app.recipes.views.recipe-image 
  (:require
   [app.components.mui :refer [box dialog dialog-title text-field button]]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn recipe-image []
  (let [initial-values {:img ""}
        values (r/atom initial-values)
        save (fn [evt]
               (.preventDefault evt)
               (rf/dispatch [:upsert-image @values])
               (reset! values initial-values))
        open-modal (fn [{:keys [modal-name recipe]}]
                     (rf/dispatch [:open-modal modal-name])
                     (reset! values recipe))
        {:keys [img name]} @(rf/subscribe [:recipe])
        author? @(rf/subscribe [:author?])]
    (fn []
      (let [active-modal @(rf/subscribe [:active-modal])]
        [:<>
         [box {:class (when author? "editable")
               :on-click (when author? #(open-modal {:modal-name :image-editor
                                                     :recipe {:img img}}))
               :min-height 400
               :alt name
               :sx {:background-image (str "url(" (or img "/img/placeholder.jpg") ")")
                    :background-size :cover
                    :border-radius 2}}]
         (when author?
           [dialog {:open (= active-modal :image-editor)
                    :on-close #(rf/dispatch [:close-modal])}
            [dialog-title "Image"]
            [box {:px 3
                  :pb 3
                  :as :form
                  :on-submit save}
             [text-field {:id :img
                          :label :URL
                          :type :url
                          :required true
                          :auto-focus true
                          :value (:img @values)
                          :on-change #(swap! values assoc :email (.. % -target -value))
                          :sx {:width 400}}]
             [box {:display :flex
                   :justify-content :flex-end
                   :pt 2}
              [button {:type :button
                       :on-click #(rf/dispatch [:close-modal])
                       :variant :outlined} "Cancel"]
              [button {:type :submit
                       :variant :contained
                       :sx {:ml 2}} "Save"]]]])]))))
