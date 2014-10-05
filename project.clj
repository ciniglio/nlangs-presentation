(defproject nlangs "0.1.0-SNAPSHOT"
  :description "Webapp demo for n langs in n months"
  :url "http://github.com/ciniglio"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring "1.3.1"]
                 [ring/ring-json "0.3.1"]
                 [compojure "1.2.0"]
                 [org.postgresql/postgresql "9.3-1102-jdbc41"]
                 [yesql "0.4.0"]

                 [org.clojure/clojurescript "0.0-2356"]]
  :plugins [[lein-cljsbuild "1.0.3"]]
  :cljsbuild {
              :builds [{:source-paths ["src-cljs"] ; The path to the top-level ClojureScript source directory:
                                        ; The standard ClojureScript compiler options:
                                        ; (See the ClojureScript compiler documentation for details.)
                        :compiler {:output-to "resources/public/javascripts/main.js"  ; default: target/cljsbuild-main.js
                                   :optimizations :whitespace
                                   :pretty-print true}}]})
