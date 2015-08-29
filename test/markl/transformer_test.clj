(ns markl.transformer-test
  (:require [markl.transformer :refer :all])
  (:use midje.sweet))

(facts "about `transform`"

       (fact "it transforms a header 1 element"
             (transform [:document [:header-1 "A Header"]]) => [:article [:h1 "A Header"]])

       (fact "it transforms a header 2 element"
             (transform [:document [:header-2 "A Header"]]) => [:article [:h2 "A Header"]])

       (fact "it transforms a header 3 element"
             (transform [:document [:header-3 "A Header"]]) => [:article [:h3 "A Header"]])

       (fact "it transforms a header 3 element"
             (transform [:document [:header-3 "A Header"]]) => [:article [:h3 "A Header"]])

       (fact "it transforms a header 4 element"
             (transform [:document [:header-4 "A Header"]]) => [:article [:h4 "A Header"]])

       (fact "it transforms a header 5 element"
             (transform [:document [:header-5 "A Header"]]) => [:article [:h5 "A Header"]])

       (fact "it transforms a header 6 element"
             (transform [:document [:header-6 "A Header"]]) => [:article [:h6 "A Header"]])

       (fact "it transforms a paragraph element"
             (transform [:document [:paragraph "A paragraph"]]) => [:article [:p "A paragraph"]])

       (fact "it transforms a image element"
             (transform [:document [:image "http://location/image.jpg"]]) => [:article [:img {:src "http://location/image.jpg"}]])

       (fact "it transforms a numbered list element"
             (transform [:document [:numbered-list "one" "two"]]) => [:article [:ol [:li "one"] [:li "two"]]])

       (fact "it transforms a bulleted list element"
             (transform [:document [:bulleted-list "one" "two"]]) => [:article [:ul [:li "one"] [:li "two"]]])

       (fact "it transforms a link element"
             (transform [:document [:link "Google" "http://www.google.com"]]) => [:article [:a {:src "http://www.google.com"} "Google"]])

       (fact "it transforms a bold element"
             (transform [:document [:bold "bold text"]]) => [:article [:strong "bold text"]])

       (fact "it transforms a italic element"
             (transform [:document [:italic "italic text"]]) => [:article [:em "italic text"]])

       (fact "nested elements can be transformed"
             (transform [:document [:header-1 "Header " [:link "Google" "http://www.google.com"]]]) => [:article [:h1 "Header " [:a {:src "http://www.google.com"} "Google"]]]
             (transform [:document [:numbered-list [:bold "one"] [:bold "two"]]]) => [:article [:ol [:li [:strong "one"]] [:li [:strong "two"]]]])

       (fact "it transforms an entire document"
             (transform [:document [:header-1 "An article"] [:paragraph "A paragraph about a " [:link "link" "http://www.google.com"] "."] [:paragraph "Another paragraph."] [:numbered-list "reason 1" "reason 2"]]) => [:article [:h1 "An article"] [:p "A paragraph about a " [:a {:src "http://www.google.com"} "link"] "."] [:p "Another paragraph."] [:ol [:li "reason 1"] [:li "reason 2"]]]))