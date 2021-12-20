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
  (fn [db [_ {:keys [handler]}]]
    (assoc-in db [:nav :active-page] handler)))

(reg-event-db
  :set-active-page
  (fn [db [_ active-page]]
    (assoc-in db [:nav :active-page] active-page)))
