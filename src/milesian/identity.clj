(ns milesian.identity
  (:require [milesian.utils :as utils]
            [com.stuartsierra.component :as component ]))

(defn ^{:bigbang/phase :before-start} add-meta-key [c* system]
  (if (utils/iobject? c*)
    (->> (utils/get-component-key c* system)
         (vary-meta c* assoc :bigbang/key))
    c*))


(defn ^{:bigbang/phase :on-start} assoc-meta-who-to-deps
  "this fn lets you store into component dependencies the parent component key"
  [c* & _]
  (if (utils/iobject? c*)
    (do
      (assert (not (nil? (:bigbang/key (meta c*))))
              "this fn needs your components meta tag with :api-component/key")
      (let [component-key (:bigbang/key (meta c*))]
        (reduce (fn [c [dep-key _]]
                  (let [dep (get c dep-key)]
                    (if (utils/iobject? dep)
                      (let [new-dep (vary-meta dep assoc :bigbang/who component-key)]
                        (assoc c dep-key new-dep))
                      c
                      )
                    )

                  )
                c*
                (component/dependencies c*))))
    c*))
