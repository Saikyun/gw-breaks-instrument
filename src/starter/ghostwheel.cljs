(ns starter.ghostwheel
  (:require [ghostwheel.core :as g :refer [>defn => |]]))

(>defn ranged-rand
  [start end]
  [int? int? | #(< start end)
   => int? | #(>= % start) #(< % end)]
  (+ start (long (rand (- end start)))))

(defn ^:export init []
  (println (ranged-rand 10 0)))

(g/check)
