# milesian/identity

stuartsierra.component/update-system functions to attach clojure.core/meta data to your components



### Simple usage, using component/udpate-system

Supose that system-map have a key :c for any-component

```clojure
(ns your-ns
  (:require [milesian.identity :as identity]
            [stuartsierra.component :as component]))

(def system-map (new-system-map))

(def system (-> system-map
                (component/update-system identity/add-meta-key system-map)
                component/start))

(assert (= :c (-> system :c meta :bigbang/key)))
```


### Also you can use [milesian bigbang](https://github.com/milesian/bigbang) 
```clojure
(ns your-ns
  (:require [milesian.bigbang :as bigbang]
            [milesian.identity :as identity]))

(def system-map (new-system-map))

(def system (bigbang/expand system-map {:before-start [[identity/add-meta-key system-map]]
                                        :after-start []}))
(assert (= :c (-> system :c meta :bigbang/key)))
```

#### Releases and Dependency Information


```clojure
[milesian/identity "0.1.4"]
```

```clojure
:dependencies [[org.clojure/clojure "1.6.0"]
               [com.stuartsierra/component "0.2.2"]]
```



## License

Copyright © 2014 Juan Antonio Ruz 

Distributed under the [MIT License](http://opensource.org/licenses/MIT). This means that pieces of this library may be copied into other libraries if they don't wish to have this as an explicit dependency, as long as it is credited within the code.

