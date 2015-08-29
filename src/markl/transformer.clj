(ns markl.transformer
  (:require [clojure.zip :as zip]))

(defn- edit-node [node]
  (cond (= node :header-1) :h1
        (= node :header-2) :h2
        (= node :header-3) :h3
        (= node :header-4) :h4
        (= node :header-5) :h5
        (= node :header-6) :h6
        (= node :paragraph) :p
        (= node :document) :article
        (and (vector? node) (= (first node) :image)) [:img {:src (last node)}]
        (and (vector? node) (= (first node) :numbered-list)) (reduce (fn [memo node] (conj memo [:li node])) [:ol] (rest node))
        (and (vector? node) (= (first node) :bulleted-list)) (reduce (fn [memo node] (conj memo [:li node])) [:ul] (rest node))
        (and (vector? node) (= (first node) :link)) [:a {:src (get node 2)} (get node 1)]
        (= node :bold) :strong
        (= node :italic) :em
        :else node))

(defn transform
  [markl]
  (loop [loc (-> markl (zip/vector-zip) (zip/next))]
    (do
      (if
        (zip/end? loc)
        (zip/root loc)
        (recur (zip/next (zip/edit loc edit-node)))))))