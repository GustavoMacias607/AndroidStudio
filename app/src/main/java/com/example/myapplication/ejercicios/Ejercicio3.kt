package com.example.myapplication.ejercicios

/*
Programa con el uso de metodos
Gustavo Macias
 */


fun main(){

    saluda();
    println("dame un numero: ")
    val a = readLine()!!.toInt();
    println("dame otro numero: ")
    val b = readLine()!!.toInt()
    sumarSuma(a,b)
    println("El resultado es ${sumarSuma3(a,b)}")
    val res = sumarSuma3(a,b);
    println("El numero es ${isPar(res)}")
}




/*
fun isPar(num:Int): String = if (num%2 == 0) "par" else "impar"
*/
fun isPar(num:Int): String{
   return if (num%2 == 0){
         "par"
    }else{
        "impar"
    }
}

fun sumarSuma3(a:Int,b: Int) =a + b


fun sumarSuma2(a:Int, b: Int): Int{
    val resultado = a + b
    return resultado;
}

fun sumarSuma(a:Int, b:Int){
    println("La suma de $a mas $b es ${a+b}")
}
fun saluda(){
    println("Desde el metodo saluda")
}