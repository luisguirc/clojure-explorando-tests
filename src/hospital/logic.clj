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

; exemplo para extrair com ex-data
;(defn chega-em
;  [hospital departamento pessoa]
;  (if (cabe-na-fila? hospital departamento)
;    (update hospital departamento conj pessoa)
;    (throw (ex-info "Não cabe ninguém neste departamento." {:paciente pessoa :tipo :impossivel-adicionar-pessoa}))))

(defn- tenta-colocar-na-fila
    [hospital departamento pessoa]
    (if (cabe-na-fila? hospital departamento)
      (update hospital departamento conj pessoa)))


(defn chega-em
  [hospital departamento pessoa]
  (if-let [novo-hospital (tenta-colocar-na-fila hospital departamento pessoa)]
    {:hospital novo-hospital :resultado :sucesso}
    {:hospital hospital :resultado :impossivel-adicionar-pessoa}))

; antes de fazer swap chega-em, vai ter que tratar seu resultado
; nao da pra fugir disso se o resultado é usado em átomos e para tratar erros.
;(defn chega-em!
;  [hospital departamento pessoa]
;  (:hospital (chega-em hospital departamento pessoa)))