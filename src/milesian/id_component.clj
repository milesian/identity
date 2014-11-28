(ns milesian.id-component
  (:require [milesian.utils :as utils]
            [com.stuartsierra.component :as component ]))


(defn ^{:bigbang/phase :before-start} add-meta-key [c* system]
  (->> (utils/get-component-key c* system)
       (vary-meta c* assoc :bigbang/key)))
