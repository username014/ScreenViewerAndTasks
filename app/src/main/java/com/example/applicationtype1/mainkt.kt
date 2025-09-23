package com.example.applicationtype1

open class Payment(protected val amount: Double) {
    open fun process(){
        println("Processing payment of $amount")
    }
}

class CreditCardPayment(amount: Double) : Payment(amount){
    override fun process(){
        println("Paid $amount via credit card")
    }
}

class PayPalPayment(amount: Double) : Payment(amount){
    override fun process(){
        println("Paid $amount via PayPal")
    }
}

class BankTransferPayment(amount: Double) : Payment(amount){
    override fun process(){
        println("Paid $amount via bank transfer")
    }
}

fun makeTransaction(payment: Payment){
    payment.process()
}

fun main(){
    val payments = listOf(CreditCardPayment(123.5), PayPalPayment(572.3), BankTransferPayment(2546.2))
    for (payment in payments){
        makeTransaction(payment)
    }
}