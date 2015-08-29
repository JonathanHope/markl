(ns markl.converter
  (:require [markl.parser :as p]
            [markl.transformer :as t]
            [hiccup.core :as c]))

(defn markl->html
  [s]
  (-> s
      (p/parse)
      (t/transform)
      (c/html)))