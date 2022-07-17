package com.example.mycalculator

sealed class CalculatorOperation(val symbol: String){
    object ADD: CalculatorOperation("+")
    object Minus: CalculatorOperation("-")
    object Multiply: CalculatorOperation("x")
    object Divide: CalculatorOperation("÷")
    object None: CalculatorOperation("")
}
