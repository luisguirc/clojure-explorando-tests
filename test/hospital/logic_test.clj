(ns hospital.logic-test
  (:require [clojure.test :refer :all]
            [hospital.logic :refer :all]))

;boundary tests: exatamente na borda e nas redondezas
; -1, +1, <=, >=, >, <, etc.

(deftest cabe-na-fila?-test
  (testing "Que cabe numa fila vazia"
    (is (cabe-na-fila? {:espera {}} :espera)))

  (testing "Que não cabe na fila quando a fila está cheia"
    ; estes testes estão com elementos sequenciais no vetor para facilitar leitura
    ; mas isso pode ser uma desvantagem, dando brecha para algum erro de implementação
    (is (not (cabe-na-fila? {:espera [1 2 3 4 5]} :espera))))

  ; no mundo real não é sequencial. o ideal é simular uma situação mais próxima do real.
  (testing "Que não cabe na fila quando tem mais do que uma fila cheia"
    (is (not (cabe-na-fila? {:espera [1 2 3 4 5 6]} :espera))))

  (testing "Que cabe na fila quando tem gente mas não está cheia"
    (is (cabe-na-fila? {:espera [1 2 3 4]} :espera))
    (is (cabe-na-fila? {:espera [1 2]} :espera)))

  (testing "Que não cabe quando o departamento não existe"
    (is (not (cabe-na-fila? {:espera [1 2 3 4]} :raio-x)))))

(deftest chega-em-test
  ; implementação ruim, teste é igual ao código da função chega-em.
  ; (is (= (update {:espera [1 2 3 4]} :espera conj 5)
  ;        (chega-em {:espera [1 2 3 4]} :espera 5))))

  (testing "aceita pessoas enquanto cabem pessoas na fila"
    (is (= {:espera [1 2 3 4 5]}
           (chega-em {:espera [1 2 3 4]} :espera 5)))
    (is (= {:espera [1 2 5]}
           (chega-em {:espera [1 2]} :espera 5)))))