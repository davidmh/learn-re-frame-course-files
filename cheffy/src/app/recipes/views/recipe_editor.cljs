(ns app.recipes.views.recipe-editor 
  (:require
    [reagent.core :as r]
    [re-frame.core :as rf]
    [clojure.string :as str]
    [app.components.mui :refer [typography grid text-field button]]
    [app.components.modal :refer [modal]]))

(defn recipe-editor []
  (let [initial-values {:name "" :prep-time ""}
        values (r/atom initial-values)
        open-modal (fn [{:keys [modal-name recipe]}]
                     (rf/dispatch [:open-modal modal-name])
                     (reset! values recipe))
        save (fn [evt]
               (.preventDefault evt)
               (rf/dispatch [:upsert-recipe {:name (str/trim (:name @values))
                                             :prep-time (js/parseInt (:prep-time @values) 10)}])
               (reset! values initial-values))]
    (fn []
      (let [{:keys [name prep-time]} @(rf/subscribe [:recipe])]
        [:<>
         [typography {:variant :h2
                      :py 2
                      :class "editable"
                      :font-weight 700
                      :on-click #(open-modal {:modal-name :recipe-editor
                                              :recipe {:name name
                                                       :prep-time prep-time}})}
          name]
         [modal {:modal-name :recipe-editor
                 :header "Recipe"
                 :container-props {:as :form :on-submit save}
                 :body [grid {:container true}
                        [grid {:item true :xs 7 :pr 2}
                           [text-field {:label "Name"
                                        :type :text
                                        :value (:name @values)
                                        :full-width true
                                        :on-change #(swap! values assoc :name (.. % -target -value))
                                        }]]
                        [grid {:item true :xs 5 :pr 2}
                           [text-field {:label "Cooking time (min)"
                                        :type :number
                                        :value (:prep-time @values)
                                        :full-width true
                                        :on-change #(swap! values assoc :prep-time (.. % -target -value))
                                        }]]]
                 :footer [:<>
                          (when name
                            [button {:type :button
                                     :color "error"
                                     :on-click #(when (js/confirm "Are you sure?")
                                                  (rf/dispatch [:delete-recipe]))
                                     :sx {:mr 2}}
                             "Delete"])
                          [button {:type :button
                                   :on-click #(rf/dispatch [:close-modal])
                                   :variant :outlined} "Cancel"]
                          [button {:type :submit
                                   :variant :contained
                                   :sx {:ml 2}} "Save"]]}]
         ]))))
