(defproject markl "0.1.0-SNAPSHOT"

  :description ""
  :url ""
  :license {:name "MIT"
            :url "http://opensource.org/licenses/MIT"}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [instaparse "1.4.1"]
                 [hiccup "1.0.5"]]

  :profiles {:dev {:dependencies [[midje "1.6.3"]]}})