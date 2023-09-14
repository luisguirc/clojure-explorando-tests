(ns hospital.logic-test
  (:use clojure.pprint)
  (:require [clojure.test :refer :all]
            [hospital.logic :refer :all]
            [hospital.model :as h.model]
            [schema.core :as s]))

(s/set-fn-validation! true)

; códigos de versoes antigas nos outros commits antes desse.

(deftest transfere-test
  (testing "aceita pessoas se cabe"
    (let [hospital-original {:espera (conj h.model/fila-vazia "51" "5") :raio-x (conj h.model/fila-vazia "13")}]
      (= {:espera ["5"]                                       ; dá certo pq está comparando os valores dentro da
          :raio-x ["13" "51"]}                                  ; sequencia, e nao se é um vetor ou fila
         (transfere hospital-original :espera :raio-x))))

  (testing "recusa pessoas se não cabe"
    (let [hospital-cheio {:espera (conj h.model/fila-vazia "5") :raio-x (conj h.model/fila-vazia "1" "2" "53" "42" "13")}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (transfere hospital-cheio :espera :raio-x))))))