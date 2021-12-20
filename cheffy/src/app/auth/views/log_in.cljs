(ns app.auth.views.log-in
  (:require
   [app.components.mui :refer [button container grid text-field]]
   [app.components.page-nav :refer [page-nav]]
   [app.router :as router]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn log-in
  []
  (let [initial-values {:email ""
                        :password ""}
        values (r/atom initial-values)]
    (fn []
      [:<>
       [page-nav {:center "Log in"}]
       [container {:max-width "sm"}
        [:form {:on-submit (fn [evt]
                             (.preventDefault evt)
                             (rf/dispatch [:log-in @values]))}
         [grid {:container true}
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
           [:a {:href (router/path-for :sign-up)
                :on-click #(rf/dispatch [:set-active-page :sign-up])}
            "New to Cheffy? Create an account!"]
           [button {:type "submit"} "Log in"]]]]]])))
