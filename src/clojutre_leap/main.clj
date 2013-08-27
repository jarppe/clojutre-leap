(ns clojutre-leap.main
  (:require [quil.core :refer :all]
            [clojutre-leap.leap :as leap]))

(defn setup []
  (smooth)
  (frame-rate 10))

(def finger-colors [[128   0   0]
                    [  0 128   0]
                    [  0   0 128]
                    [  0 128 128]
                    [128 128   0]])

(defn draw []
  (let [w (width)
        h (height)]

    (background 200)
    (stroke-weight 3)

    (translate (/ w 2.0) (/ h 2.0))
    (scale (/ (min w h) 150.0))
    
    (doseq [[[x y z] color] (map vector (leap/fingers) finger-colors)]
      (apply fill color)
      (apply stroke (map (partial * 2) color))             
      (let [size (/ (- 300 y) 2)]
        (ellipse x z size size)))))

(defn -main [& args]
  (defsketch leap-testing
    :title "show fingers"
    :setup setup
    :draw draw
    :size [323 200]
    :target :perm-frame))
