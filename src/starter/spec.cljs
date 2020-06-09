(ns starter.spec
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.test.alpha :as st]))

(defn ranged-rand
  "Returns random int in range start <= rand < end"
  [start end]
  (+ start (long (rand (- end start)))))

(s/fdef ranged-rand
  :args (s/and (s/cat :start int? :end int?)
               #(< (:start %) (:end %)))
  :ret int?
  :fn (s/and #(>= (:ret %) (-> % :args :start))
             #(< (:ret %) (-> % :args :end))))

(defn ^:export init []
  (println (ranged-rand 10 0)))

(st/instrument)
