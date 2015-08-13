(ns markl.parser-test
  (:require [markl.parser :refer :all])
  (:use midje.sweet))

(facts "about `run-parser`"
       (fact "it parses a header 1 element"
             (run-parser "[- A Header]") => [:document [:header-1 "A Header"]])

       (fact "it parses a header 2 element"
             (run-parser "[-- A Header]") => [:document [:header-2 "A Header"]])

       (fact "it parses a header 3 element"
             (run-parser "[--- A Header]") => [:document [:header-3 "A Header"]])

       (fact "it parses a header 4 element"
             (run-parser "[---- A Header]") => [:document [:header-4 "A Header"]])

       (fact "it parses a header 5 element"
             (run-parser "[----- A Header]") => [:document [:header-5 "A Header"]])

       (fact "it parses a header 6 element"
             (run-parser "[------ A Header]") => [:document [:header-6 "A Header"]])

       (fact "it parses a paragraph element"
             (run-parser "[% A paragraph]") => [:document [:paragraph "A paragraph"]])

       (fact "it parses a image element"
             (run-parser "[^ http://location/image.jpg]") => [:document [:image "http://location/image.jpg"]])

       (fact "it parsed a numbered list element"
             (run-parser "[# [one] [two]]") => [:document [:numbered-list "one" "two"]])

       (fact "it parsed a bulleted list element"
             (run-parser "[* [one] [two]]") => [:document [:bulleted-list "one" "two"]])

       (fact "it parses a link element"
             (run-parser "[@ [Google] [http://www.google.com]]") => [:document [:link "Google" "http://www.google.com"]])

       (fact "it parses a bold element"
             (run-parser "[! bold text]") => [:document [:bold "bold text"]])

       (fact "it parses a italic element"
             (run-parser "[$ italic text]") => [:document [:italic "italic text"]])

       (fact "inline elements can be contained in block elements"
             (run-parser "[- Header [@ [Google] [http://www.google.com]]]") => [:document [:header-1 "Header " [:link "Google" "http://www.google.com"]]])

       (fact "[] characters can be escaped"
             (run-parser "[% test [[]]]") => [:document [:paragraph "test []"]]))