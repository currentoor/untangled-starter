(set-env!
 :source-paths   #{"src"}
 :dependencies '[[adzerk/bootlaces "0.1.13" :scope "test"]

                 [org.clojure/clojure "1.8.0" :scope "provided"]
                 [org.clojure/clojurescript "1.7.228" :scope "provided"]

                 [org.omcljs/om "1.0.0-alpha32" :scope "provided"]
                 [sablono "0.6.0"]
                 [camel-snake-kebab "0.3.2"]
                 [cljsjs/react-bootstrap "0.29.2-0"]])

(require
 '[adzerk.bootlaces :refer :all]
 '[clojure.java.io               :as io]
 '[boot.pod                      :as pod])

(def ^:private common-opts
  {:cache-analysis true
   :warnings {:single-segment-namespace false}
   :compiler-stats true
   :parallel-build true})

(def none-opts
  (->> {:optimizations :none
        :source-map true
        :source-map-timestamp true}
       (merge common-opts)))

(def simple-opts
  (->> {:optimizations :simple
        :closure-defines {:goog.DEBUG false}
        :elide-asserts true}
       (merge common-opts)))

(def +version+ "0.0.1")

(task-options!
 pom {:project 'rum-mdl
      :version +version+
      :description "ReactBootstrap component and some syntactical sugar."
      :url "https://github.com/currentoor/untangled-starter"
      :scm {:url "https://github.com/currentoor/untangled-starter"}
      :license {"The MIT License (MIT)" "https://opensource.org/licenses/MIT"}}
 push {:repo "deploy-clojars"})

;;; boot -P package push-snapshot
