[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/fN4hrKEh)
# eda_examen2_avl

## Integrantes
* Martell Villanueva, Gabriela Vanessa
* Taco Huaman, Ariel Dicson

## Resumen
1. Se han implementado las rotaciones necesarias para el algoritmo de balanceo del Árbol AVL, siguiendo los conceptos enseñados en la teoría. A continuación se presentan los 5 casos que se consideran dependiendo de los factores de balanceo (bf) de los nodos:
        1. Si el factor de balanceo (bf) del nodo padre es mayor que 1 y el del hijo es igual a 1, se realiza una rotación simple a la izquierda (RSL) en el nodo padre para restablecer el equilibrio.
        2. Si el factor de balanceo (bf) del nodo padre es menor que -1 y el del hijo es igual a -1, se realiza una rotación simple a la derecha (RSR) en el nodo padre para restaurar el equilibrio.
        3. Si el factor de balanceo (bf) del nodo padre es mayor que 1 y el del hijo es -1, se realiza una rotación simple a la derecha (RSR) en el hijo y luego una rotación simple a la izquierda (RSL) en el nodo padre para lograr el balanceo.
        4. Si el factor de balanceo (bf) del nodo padre es menor que -1 y el del hijo es 1, se realiza una rotación simple a la izquierda (RSL) en el hijo y luego una rotación simple a la derecha (RSR) en el nodo padre para restablecer el equilibrio.
        5. Si ninguno de los casos anteriores se cumple, se considera que el nodo padre está balanceado y no es necesario realizar rotaciones.
