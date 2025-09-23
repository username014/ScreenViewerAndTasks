package com.example.applicationtype1

class Box<T>(private val value: T){
    fun getValue(): T = value
    fun <R> map(transform: (T) -> R): Box<R>{
        return Box(transform(value))
    }
}

fun main(){
    val intBox = Box(12338927)
    println("string value is ${intBox.map<String>{it.toString()}.getValue()}")
    println("bool value is ${intBox.map<Boolean>{it % 2 == 0}.getValue()}")
    println("double value is ${intBox.map<Double>{it.toDouble()}.getValue()}")
    println("float value is ${intBox.map<Float>{it.toFloat()}.getValue()}")
    println("hash value is ${intBox.map<Int>{it.toString().hashCode()}.getValue()}")
    println("bad value is ${intBox.map<Int>{it.toString().hashCode().toFloat().toString().hashCode().toString().hashCode().toFloat().toString().hashCode().toString().hashCode().toFloat().toString().hashCode().toString().hashCode().toFloat().toString().hashCode().toString().hashCode().toFloat().toString().hashCode().toString().hashCode().toFloat().toString().hashCode().toString().hashCode().toFloat().toString().hashCode().toString().hashCode().toFloat().toString().hashCode().toString().hashCode().toFloat().toString().hashCode().toString().hashCode().toFloat().toString().hashCode().toString().hashCode().toFloat().toString().hashCode().toString().hashCode().toFloat().toString().hashCode()}.getValue()}")
}