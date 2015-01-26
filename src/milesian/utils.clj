(ns milesian.utils
  (:require [com.stuartsierra.component :as component]))


(defn- dissoc-deps [c*]
  (reduce (fn [c [k v]]
            (assoc c k nil)) c* (component/dependencies c*)))

(defn get-component-key [c* system]
  (get (clojure.set/map-invert (reduce (fn [c [k v]] (assoc c k (dissoc-deps v))) system system))
       (dissoc-deps c*)))

(defn iobject?
  "your value needs to implement IObj if you are going to add meta data"
  [x]
  (contains? (supers (class x)) clojure.lang.IObj))
