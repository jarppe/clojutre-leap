(defproject clojutre-leap "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [amalloy/ring-buffer "1.0"]
                 [rogerallen/leaplib "0.8.1"]
                 [rogerallen/leaplib-natives "0.8.1"]
                 [quil "1.6.0"]]
  :profiles {:dev {:dependencies [[midje "1.5.1"]]
                   :plugins [[lein-midje "3.1.1"]]}}
  :main clojutre-leap.main
  :min-lein-version "2.0.0")
