(ns nlangs.core)

;; As usual, function comes first, but inside the parens!

(prn "Hello, N-langs") ;; compare to prn("Hello, N-langs")

;; Conditionals

(if (> 3 2)
  :greater-than
  :not-greater-than)

(if nil
  :truthy
  :falsey)

(if []
  :truthy
  :falsey)

(if '()
  :truthy
  :falsey)

;; Functions

(fn [n] (* n n))

((fn [n] (* n n)) 5)

(def square (fn [n] (* n n)))

(def nums (range 5))












;; Higher order functions

(map square nums)

(filter odd? (map square nums))

;; Threading macro

(->> nums (map square) (filter odd?))

(macroexpand-1 '(->> nums (map square) (filter odd?)))























;; Hashmaps

{:a 1 :b 2}

(get {:a 1 :b 2} :b)

(get {:a 1 :b 2} :c :not-found!)

(:a {:a 1 :b 2})

(:c {:a 1 :b 2} :not-found!)

({:a 1 :b 2} :b)




















(def a-map {:a 1 :b 2})

(assoc a-map :c 3)

(get (assoc a-map :c 3) :c :not-found!)

a-map ;; Immutable!

(dissoc a-map :a)

a-map





(def b-map (atom {}))

(swap! b-map assoc :a 3)

@b-map

(swap! b-map assoc :b 4)

b-map
