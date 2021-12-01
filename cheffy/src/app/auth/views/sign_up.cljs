(ns app.auth.views.sign-up
  (:require
   [app.components.mui :refer [button container grid text-field]]
   [app.components.page-nav :refer [page-nav]]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn sign-up
  []
  (let [initial-values {:first-name ""
                        :last-name ""
                        :email ""
                        :password ""}
        values (r/atom initial-values)]
    (fn []
      [:<>
       [page-nav {:center "Sign up"}]
       [container {:max-width "sm"}
        [:form {:on-submit (fn [evt]
                             (.preventDefault evt)
                             (rf/dispatch [:sign-up @values]))}
         [grid {:container true}
          [grid {:item true :xs 12 :pb 2}
           [text-field {:label "First name"
                        :type "text"
                        :value (:first-name @values)
                        :on-change #(swap! values assoc :first-name (.. % -target -value))
                        :required true
                        :full-width true}]]
           [grid {:item true :xs 12 :pb 2}
            [text-field {:label "Last name"
                         :type "text"
                         :value (:last-name @values)
                         :on-change #(swap! values assoc :last-name (.. % -target -value))
                         :required true
                         :full-width true}]]
           [grid {:item true :xs 12 :pb 2}
            [text-field {:label "Email"
                         :type "email"
                         :value (:email @values)
                         :on-change #(swap! values assoc :email (.. % -target -value))
                         :required true
                         :full-width true}]]
          [grid {:item true :xs 12 :pb 2}
           [text-field {:label "Password"
                        :type "password"
                        :value (:password @values)
                        :on-change #(swap! values assoc :password (.. % -target -value))
                        :required true
                        :full-width true}]]
          [grid {:item true
                 :xs 12
                 :display "flex"
                 :justify-content "space-between"}
           [:a {:href "#log-in"
                :on-click #(rf/dispatch [:set-active-nav :log-in])}
            "Already have an account? Log in!"]
           [button {:type "submit"} "Sign up"]]]]]])))
