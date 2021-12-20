(ns app.nav.events
  (:require
   [app.router :as router]
   [re-frame.core :refer [reg-event-db reg-fx]]))

(reg-fx
  :navigate-to
  (fn [{:keys [path]}]
    (router/set-token! path)))

(reg-event-db
  :route-changed
  (fn [db [_ {:keys [handler route-params]}]]
    (-> db
        (assoc-in [:nav :active-page] handler)
        (assoc-in [:nav :active-recipe] (keyword (:recipe-id route-params))))))

(reg-event-db
  :set-active-page
  (fn [db [_ active-page]]
    (assoc-in db [:nav :active-page] active-page)))

(reg-event-db
  :close-modal
  (fn [db _]
    (assoc-in db [:nav :active-modal] nil)))

(reg-event-db
  :open-modal
  (fn [db [_ active-modal]]
    (assoc-in db [:nav :active-modal] active-modal)))
