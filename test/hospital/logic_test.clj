(ns hospital.logic-test
  (:require [clojure.test :refer :all]
            [hospital.logic :refer :all]))

;boundary tests: exatamente na borda e nas redondezas
; -1, +1, <=, >=, >, <, etc.

(deftest cabe-na-fila?-test
  (testing "Que cabe numa fila vazia"
    (is (cabe-na-fila? {:espera {}} :espera)))

  (testing "Que não cabe na fila quando a fila está cheia"
    (is (not (cabe-na-fila? {:espera [1 2 3 4 5]} :espera))))

  (testing "Que não cabe na fila quando tem mais do que uma fila cheia"
    (is (not (cabe-na-fila? {:espera [1 2 3 4 5 6]} :espera))))

  (testing "Que cabe na fila quando tem gente mas não está cheia"
    (is (cabe-na-fila? {:espera [1 2 3 4]} :espera))
    (is (cabe-na-fila? {:espera [1 2]} :espera)))

  (testing "Que ... quando o departamento não existe"
    (is (not (cabe-na-fila? {:espera [1 2 3 4]} :raio-x)))))