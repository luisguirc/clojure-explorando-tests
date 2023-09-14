(ns hospital.logic)

; Test Driven Development
; Test Driven Design

; menos explicito, e qualquer um que der nulo vai causar devolver nulo
(defn cabe-na-fila?
  [hospital departamento]
  (some-> hospital
      departamento
      count
      (< 5)))

;funciona para o caso de nao existir o departamento
;(defn cabe-na-fila?
;  [hospital departamento]
;  (when-let [fila (get hospital departamento)]
;    (-> fila
;        count
;        (< 5))))