(ns markl.parser-test
  (:require [markl.parser :refer :all])
  (:use midje.sweet))

(facts "about `run-parser`"

       (fact "it parses a header 1 element"
             (parse "[- A Header]") => [:document [:header-1 "A Header"]])

       (fact "it parses a header 2 element"
             (parse "[-- A Header]") => [:document [:header-2 "A Header"]])

       (fact "it parses a header 3 element"
             (parse "[--- A Header]") => [:document [:header-3 "A Header"]])

       (fact "it parses a header 4 element"
             (parse "[---- A Header]") => [:document [:header-4 "A Header"]])

       (fact "it parses a header 5 element"
             (parse "[----- A Header]") => [:document [:header-5 "A Header"]])

       (fact "it parses a header 6 element"
             (parse "[------ A Header]") => [:document [:header-6 "A Header"]])

       (fact "it parses a paragraph element"
             (parse "[% A paragraph]") => [:document [:paragraph "A paragraph"]])

       (fact "it parses a image element"
             (parse "[^ http://location/image.jpg]") => [:document [:image "http://location/image.jpg"]])

       (fact "it parsed a numbered list element"
             (parse "[# [one] [two]]") => [:document [:numbered-list "one" "two"]])

       (fact "it parsed a bulleted list element"
             (parse "[* [one] [two]]") => [:document [:bulleted-list "one" "two"]])

       (fact "it parses a link element"
             (parse "[@ [Google] [http://www.google.com]]") => [:document [:link "Google" "http://www.google.com"]])

       (fact "it parses a bold element"
             (parse "[! bold text]") => [:document [:bold "bold text"]])

       (fact "it parses a italic element"
             (parse "[$ italic text]") => [:document [:italic "italic text"]])

       (fact "inline elements can be contained in block elements"
             (parse "[- Header [@ [Google] [http://www.google.com]]]") => [:document [:header-1 "Header " [:link "Google" "http://www.google.com"]]]
             (parse "[# [! one] [! two]]") => [:document [:numbered-list [:bold "one"] [:bold "two"]]])

       (fact "[] characters can be escaped"
             (parse "[% test [[]]]") => [:document [:paragraph "test []"]])

       (fact "elements can be combined to generate full articles"
             (parse "
[- An article]

[% A paragraph about a [@ [link] [http://www.google.com]].]

[% Another paragraph.]

[# [reason 1]
    [reason 2]]") => [:document [:header-1 "An article"] [:paragraph "A paragraph about a " [:link "link" "http://www.google.com"] "."] [:paragraph "Another paragraph."] [:numbered-list "reason 1" "reason 2"]]))