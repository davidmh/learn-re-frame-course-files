(ns app.recipes.views.recipe-steps
  (:require
   [app.components.icons :as icons]
   [app.components.modal :refer [modal]]
   [app.components.mui :refer [box button grid icon-button paper text-field typography]]
   [clojure.string :as str]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn recipe-steps []
  (let [initial-values {:id nil
                        :desc ""}
        values (r/atom initial-values)
        open-modal (fn [{:keys [modal-name step]}]
                     (rf/dispatch [:open-modal modal-name])
                     (reset! values step))
        save (fn [evt]
               (.preventDefault evt)
               (let [{:keys [id desc]} @values]
                 (rf/dispatch [:upsert-step {:id (or id (keyword (str "step-" (random-uuid))))
                                             :desc (str/trim desc)}])
                 (reset! values initial-values)))]
    (fn []
      (let [author? @(rf/subscribe [:author?])
            steps @(rf/subscribe [:steps])]
        [paper {:elevation 1
                :sx {:p 2}}
         [box {:align-items :center
               :gap 1
               :display :flex}
          [typography {:variant :h5} "Steps"]
          [icon-button {:on-click #(open-modal {:modal-name :step-editor
                                                :step initial-values})}
           [icons/add]]]
         (for [{:keys [id desc] :as step} steps]
           ^{:key id}
           [box {:on-click (when author? #(open-modal {:modal-name :step-editor
                                                       :step step}))
                 :class (when author? "editable")}
            [typography desc]])
         (when author?
           [modal {:modal-name :step-editor
                   :header "Step"
                   :container-props {:as :form :on-submit save}
                   :body [grid {:container true :min-width 400}
                          [grid {:item true :xs 12}
                           [text-field {:label "Description"
                                        :type :text
                                        :value (:desc @values)
                                        :multiline true
                                        :min-rows 4
                                        :full-width true
                                        :on-change #(swap! values assoc :desc (.. % -target -value))}]]]
                   :footer [:<>
                            (when-let [step-id (:id @values)]
                              [button {:type :button
                                       :color "error"
                                       :on-click #(when (js/confirm "Are you sure?")
                                                    (rf/dispatch [:delete-step step-id]))
                                       :sx {:mr 2}}
                               "Delete"])
                            [button {:type :button
                                     :on-click #(rf/dispatch [:close-modal])
                                     :variant :outlined} "Cancel"]
                            [button {:type :submit
                                     :variant :contained
                                     :sx {:ml 2}} "Save"]]}])]))))
