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

(def +version+ "0.0.1-SNAPSHOT")

(task-options!
 pom {:project 'untangled-starter
      :version +version+
      :description "ReactBootstrap wrapper and some syntactical sugar for Om Next."
      :url "https://github.com/currentoor/untangled-starter"
      :scm {:url "https://github.com/currentoor/untangled-starter"}
      :license {"The MIT License (MIT)" "https://opensource.org/licenses/MIT"}}
 push {:repo "deploy-clojars"})

;;; boot -P package push-snapshot
