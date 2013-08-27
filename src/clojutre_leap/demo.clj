(ns clojutre-leap.demo
  (:require [clojutre-leap.leap :as leap]
            [quil.core :refer :all])
  (:import [com.leapmotion.leap Controller Hand Finger Vector]))

;;
;; Leap stuff:
;;

(defn xyz [^Vector v]
  [(.getX v) (.getY v) (.getZ v)])

(defn tip [^Finger finger]
  (-> finger .tipPosition xyz))

(defn hand->fingers [^Hand hand]
  (when hand
    (->> hand .fingers (map tip))))

(defonce ^Controller connection (Controller.))

(defn fingers []
  (-> connection .frame .hands .leftmost hand->fingers))

;;
;; Quil stuff:
;;

(defn setup []
  (smooth)
  (frame-rate 10))

(def finger-colors [[128 0 0] [0 128 0] [0 0 128] [0 128 128] [128 128 0]])

(defn draw []
  (let [w (width)
        h (height)]
    (background 200)
    (stroke-weight 3)
    (translate (/ w 2.0) (/ h 2.0))
    (scale (/ (min w h) 150.0))
    (doseq [[[x y z] color] (map vector (fingers) finger-colors)]
      (apply fill color)
      (apply stroke (map (partial * 2) color))             
      (let [size (/ (- 300 y) 2)]
        (ellipse x z size size)))))

;;
;; Main:
;;

(defn -main [& args]
  (defsketch leap-testing
    :title "show fingers"
    :setup setup
    :draw draw
    :size [323 200]
    :target :perm-frame))
