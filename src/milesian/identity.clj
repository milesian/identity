(ns milesian.identity
  (:require [milesian.utils :as utils]
            [com.stuartsierra.component :as component ]))

(defn ^{:bigbang/phase :before-start} add-meta-key [c* system]
  (->> (utils/get-component-key c* system)
       (vary-meta c* assoc :bigbang/key)))


(defn ^{:bigbang/phase :on-start} assoc-meta-who-to-deps
  "this fn lets you store into component dependencies the parent component key"
  [c* & _]

  (assert (not (nil? (:bigbang/key (meta c*))))
          "this fn needs your components meta tag with :api-component/key")
  (let [component-key (:bigbang/key (meta c*))]
    (reduce (fn [c [dep-key _]]
              (let [dep (get c dep-key)]
                (let [new-dep (vary-meta dep assoc :bigbang/who component-key)]
                  (assoc c dep-key new-dep))))
            c*
            (component/dependencies c*))))
