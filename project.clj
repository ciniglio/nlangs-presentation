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
                 [yesql "0.4.0"]])
