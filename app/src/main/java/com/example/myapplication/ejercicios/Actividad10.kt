package com.example.myapplication.ejercicios

/**
 * Manejo de colecciones
 */
fun main(){
    val numeros = arrayOf(10,5,6,3,7)

    for(numero in numeros){
        println("El valor es $numero")
    }
    println(numeros.contentToString())

    val suma = numeros.sum()
    println("La suma del arreglo es $suma")

    val ordenados = numeros.sortedArray();
    println("El arreglo ordenado es: ${ordenados.contentToString()}")

    println("------------------------------------------------")

    val arregloVacio = Array(5) { _ -> 3}
    arregloVacio[2] = 10
    println(arregloVacio.contentToString())
    println("---------- Colecciones de lista ------------")
    val frutas = listOf("Manzana","Fresa","Sandia")
    println(frutas)
    for (fruta in frutas){
        println(fruta)
    }
    println("Filtro")
    frutas.filter { it.contains("n") }.forEach{
        println(it)
    }
    println("---------Lista Mutable----------------")
    val frutasMutable = mutableListOf("Sandia")
    frutasMutable.add("Naranja")
    println(frutasMutable)
    println("------ Coleccion set ---------") /*No se le pueden agregar valores iguales a la lista */
    val numerosSet = mutableSetOf(4,10,20,40)

    println(numerosSet)

    println("------ Coleccion maps-----------")
    val maps = mutableMapOf("001" to "Coca Cola", "002" to "Sabritas")

    if ("001" in maps) println("El valor es ${maps["001"]}")
    if ("Coca Cola" in maps.values) println("Si se encuentra")
    if (maps.containsValue("Coca Cola")) println("Si se encuentra")
    println(maps.values)
    println(maps.keys)

}
