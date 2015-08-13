(ns markl.parser
  (:require [instaparse.core :as insta]))

(def parser (insta/parser
              "
              document = (line-level-elements | block-level-elements | <line-break>)*

              (* Block Level Elements *)

              <block-level-elements> = header-1 | header-2 | header-3 | header-4 | header-5 | header-6 | paragraph | image

              header-1 = <start-tag> <header1-start> (line-level-elements | characters)* <end-tag>
              <header1-start> = #'- ?'

              header-2 = <start-tag> <header2-start> (line-level-elements | characters)* <end-tag>
              <header2-start> = #'-- ?'

              header-3 = <start-tag> <header3-start> (line-level-elements | characters)* <end-tag>
              <header3-start> = #'--- ?'

              header-4 = <start-tag> <header4-start> (line-level-elements | characters)* <end-tag>
              <header4-start> = #'---- ?'

              header-5 = <start-tag> <header5-start> (line-level-elements | characters)* <end-tag>
              <header5-start> = #'----- ?'

              header-6 = <start-tag> <header6-start> (line-level-elements | characters)* <end-tag>
              <header6-start> = #'------ ?'

              paragraph = <start-tag> <paragraph-start> (line-level-elements | characters)* <end-tag>
              <paragraph-start> = #'% ?'

              image = <start-tag> <image-start> characters <end-tag>
              <image-start> = #'\\^ ?'

              (* Line Level Elements *)

              <line-level-elements> = link | bold | italic

              link = <start-tag> <anchor-start> <start-tag> characters <end-tag> <line-break>? <start-tag> characters <end-tag> <end-tag>
              <anchor-start> = #'@ ?'

              bold = <start-tag> <bold-start> characters <end-tag>
              <bold-start> = #'! ?'

              italic = <start-tag> <italic-start> characters <end-tag>
              <italic-start> = #'\\$ ?'

              (* Shared *)

              <start-tag> = #'\\['
              <end-tag> = #'\\]'
              <character> = #'[^\\[\\]]*'
              characters = (character / escaped-open-bracket / escaped-close-bracket)*
              <line-break> = #'\\s'
              escaped-open-bracket = #'\\[\\['
              escaped-close-bracket = #'\\]\\]'
              "))

(defn run-parser
  [s]
  (->> s
       parser
       (insta/transform
         {:characters str
          :escaped-open-bracket second
          :escaped-close-bracket second})))