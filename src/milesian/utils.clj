(ns milesian.utils
  (:require [com.stuartsierra.component :as component]))


(defn- dissoc-deps [c*]
  (reduce (fn [c [k v]]
            (assoc c k nil)) c* (component/dependencies c*)))

(defn get-component-key [c* system]
  (get (clojure.set/map-invert (reduce (fn [c [k v]] (assoc c k (dissoc-deps v))) system system))
       (dissoc-deps c*)))
