package com.example.applicationtype1

fun first(list: List<Int>) {
    for (elem in list) {
        if (elem % 2 == 0) {
            println(elem)
        }
    }
}

fun second(num: Int): Int{
    var result: Int = 1
    for (i in 1 .. num){
        result *= i
    }
    return result
}

class third(val width: Double, val height: Double){
    fun area(): Double{
        return width * height
    }
}

fun fourth(str: String): String{
    var strResult = ""
    for(i in str.length-1 downTo 0){
        strResult += str[i]
    }
    return strResult
}

fun main(){
    val lint = listOf(1, 3, 7, 15, 67, 102)
    first(lint)

    val num = 9
    println(second(num))

    val width = 12.2
    val height = 16.4
    val result1 = third(width, height).area()
    println("$result1")

    val strFirst = "kotlin"
    val result2 = fourth(strFirst)
    println("$result2")
}