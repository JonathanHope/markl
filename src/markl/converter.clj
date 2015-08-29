(ns markl.converter
  (:require [markl.parser :as p]
            [markl.transformer :as t]
            [hiccup.core :as c]))

(defn markl->hiccup
  [s]
  (-> s
      (p/parse)
      (t/transform)))

(defn markl->html
  [s]
  (-> s
      (markl->hiccup)
      (c/html)))