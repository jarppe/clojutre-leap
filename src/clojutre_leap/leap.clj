(ns clojutre-leap.leap
  (:import [com.leapmotion.leap Controller Hand Finger Vector]))

(set! *warn-on-reflection* true)

(defn tip [^Finger finger]
  (-> finger .tipPosition))

(defn xyz [^Vector v]
  [(.getX v) (.getY v) (.getZ v)])

(defn hand->fingers [^Hand hand]
  (when hand
    (->> hand .fingers (map tip) (map xyz))))

(defn connect ^Controller []
  (Controller.))

(defonce ^Controller connection (connect))

(defn fingers []
  (-> connection .frame .hands .leftmost hand->fingers))
