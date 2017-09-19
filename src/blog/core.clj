(ns blog.core
  (:gen-class)
  (:require [ring.adapter.undertow :refer [run-undertow]]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [selmer.parser :as template]))

(defroutes app-routes
  (GET "/" []
    (template/render-file "main.html" {:title "Blah test"}))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defn -main
  [& args]
  (template/cache-off!)
  (template/set-resource-path! (clojure.java.io/resource "templates"))
  (run-undertow app {:port 3000}))
