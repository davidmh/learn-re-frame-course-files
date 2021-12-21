(ns app.recipes.views.recipe-ingredients
  (:require
   [app.components.icons :as icons]
   [app.components.modal :refer [modal]]
   [app.components.mui :refer [box grid icon-button paper text-field
                               typography button]]
   [re-frame.core :as rf]
   [reagent.core :as r]
   [clojure.string :as str]
   [app.color :refer [color]]))

(defn recipe-ingredients []
  (let [initial-values {:id nil
                        :amount ""
                        :measure ""
                        :name ""}
        values (r/atom initial-values)
        open-modal (fn [{:keys [modal-name ingredient]}]
                     (rf/dispatch [:open-modal modal-name])
                     (reset! values ingredient))
        save (fn [evt]
               (.preventDefault evt)
               (let [{:keys [id amount measure name]} @values]
                 (rf/dispatch [:upsert-ingredient {:id (or id (keyword (str "ingredient-" (random-uuid))))
                                                   :name (str/trim name)
                                                   :amount (js/parseInt amount 10)
                                                   :measure (str/trim measure)}])
                 (reset! values initial-values)))]
    (fn []
      (let [author? @(rf/subscribe [:author?])
            ingredients @(rf/subscribe [:ingredients])]
        [paper {:elevation 1
                :sx {:p 2}}
         [box {:align-items :center
               :gap 1
               :display :flex
               :py 2}
          [typography {:variant :h5} "Ingredients"]
          [icon-button {:on-click #(open-modal {:modal-name :ingredient-editor
                                                :ingredient initial-values})}
           [icons/add]]]
         (for [{:keys [id amount measure name] :as ingredient} ingredients]
           ^{:key id}
           [grid {:container true
                  :on-click (when author? #(open-modal {:modal-name :ingredient-editor
                                                        :ingredient ingredient}))
                  :class (when author? "editable")}
            [grid {:item true :xs 3} [typography amount " " measure]]
            [grid {:item true :xs 9} [typography name]]])
         (when author?
           [modal {:modal-name :ingredient-editor
                   :header "Ingredient"
                   :container-props {:as :form :on-submit save}
                   :body [grid {:container true}
                          [grid {:item true :xs 6 :pr 2}
                           [text-field {:label "Amount"
                                        :type :number
                                        :value (:amount @values)
                                        :full-width true
                                        :on-change #(swap! values assoc :amount (.. % -target -value))
                                        }]]
                          [grid {:item true :xs 6}
                           [text-field {:label "Measure"
                                        :type :text
                                        :value (:measure @values)
                                        :full-width true
                                        :on-change #(swap! values assoc :measure (.. % -target -value))
                                        }]]
                          [grid {:item true
                                 :mt 2
                                 :xs 12}
                           [text-field {:label "Name"
                                        :type :text
                                        :value (:name @values)
                                        :full-width true
                                        :on-change #(swap! values assoc :name (.. % -target -value))}]]]
                   :footer [:<>
                            (when-let [ingredient-id (:id @values)]
                              [button {:type :button
                                       :color "error"
                                       :on-click #(when (js/confirm "Are you sure?")
                                                    (rf/dispatch [:delete-ingredient ingredient-id]))
                                       :sx {:mr 2}}
                               "Delete"])
                            [button {:type :button
                                     :on-click #(rf/dispatch [:close-modal])
                                     :variant :outlined} "Cancel"]
                            [button {:type :submit
                                     :variant :contained
                                     :sx {:ml 2}} "Save"]]}])]))))
