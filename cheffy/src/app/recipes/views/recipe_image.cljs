(ns app.recipes.views.recipe-image
  (:require
   [app.components.modal :refer [modal]]
   [app.components.mui :refer [box button text-field]]
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
         [modal {:modal-name :image-editor
                 :header "Image"
                 :container-props {:as :form :on-submit save}
                 :body [text-field {:label :URL
                                    :type :url
                                    :required true
                                    :auto-focus true
                                    :value (:img @values)
                                    :on-change #(swap! values assoc :email (.. % -target -value))
                                    :sx {:width 400}}]
                 :footer [:<>
                          [button {:type :button
                                   :on-click #(rf/dispatch [:close-modal])
                                   :variant :outlined} "Cancel"]
                          [button {:type :submit
                                   :variant :contained
                                   :sx {:ml 2}} "Save"]]}])])))
