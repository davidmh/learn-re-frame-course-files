(ns app.auth.views.profile
  (:require
   [app.components.mui :refer [box button container paper text-field
                               typography]]
   [app.components.page-nav :refer [page-nav]]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn profile
  []
  (let [{:keys [first-name last-name]} @(rf/subscribe [:active-user-profile])
        initial-values {:first-name first-name :last-name last-name}
        values (r/atom initial-values)]
    (fn []
      [:<>
       [page-nav {:center :Profile
                  :right [button {:variant :outlined
                                  :on-click #(rf/dispatch [:log-out])} "Log out"]
                  }]
       [container {:max-width :sm}
        [paper {:elevation 1 :sx {:p 2}}
         [typography {:variant :h6} "Personal Info"]
         [:form {:on-click (fn [evt]
                             (.preventDefault evt)
                             (rf/dispatch [:update-profile @values]))}
          [box {:display :flex :flex-direction :column}
           [text-field {:label "First name"
                        :type :text
                        :value (:first-name @values)
                        :on-change #(swap! values assoc :first-name (.. % -target -value))
                        :required true
                        :full-width true
                        :sx {:my 1}}]
           [text-field {:label "Last name"
                        :type :text
                        :value (:last-name @values)
                        :on-change #(swap! values assoc :last-name (.. % -target -value))
                        :required true
                        :full-width true
                        :sx {:my 1}}]
           [button {:variant :contained
                    :type :submit
                    :sx {:align-self :end}} "Save"]]]]

        [paper {:elevation 1 :sx {:p 2 :mt 2}}
         [typography {:variant :h6} "Danger zone"]
         [button {:variant :contained
                  :color :error
                  :on-click #(when (js/confirm "This will delete your account")
                               (rf/dispatch [:delete-account]))}
          "Delete Account"]]]])))
