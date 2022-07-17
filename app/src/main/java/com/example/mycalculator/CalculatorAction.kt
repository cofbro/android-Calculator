package com.example.mycalculator

sealed class CalculatorAction() {
    class Operation(val operation: CalculatorOperation): CalculatorAction()
    class Number(val number: String): CalculatorAction()
    object Equal: CalculatorAction()
    object Clear: CalculatorAction()
}