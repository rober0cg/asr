ASR
Analizador
 Sintactico
  Recursivo

Intento de disponer de un evaluador en tiempo de ejecuci�n de expresiones matem�ticas
Y a su vez de profundizar en las teor�as de la progranmaci�n orientada a objetos.

La base del ASR:
  expr = term [ + expr ] 
  term = fact [ * term ]
  fact = cons | var | func(expr) | expr

Los clases implementadas:
  Expresion
  Terminio
  Factor
  Factor-Constante
  Factor-Variable
  Factor-Funciion(Expresion)
  Factor-(Expresion)

Pasado el c�digo por sonar: 0% duada tecnica

TODO
funciones con varios argumentos


