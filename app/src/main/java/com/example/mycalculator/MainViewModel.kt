package com.example.mycalculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var lastIsSymbol = false
    var isFirst = true
    var num1: StringBuilder = StringBuilder()
    private var num2: String = ""
        set(value) {
            field = value
            if (value.isNotEmpty()){
                getResult()
            }
        }
    var details = MutableLiveData("")
    var result = MutableLiveData("")
    var operation: CalculatorOperation = CalculatorOperation.None

    fun onAction(action: CalculatorAction){
        when(action) {
            is CalculatorAction.Operation -> saveOperation(action.operation)
            is CalculatorAction.Number -> buildNumber(action.number)
            is CalculatorAction.Clear -> clearAll()
            is CalculatorAction.Equal -> getResult()
        }
    }

    private fun getResult() {
        val v1 = num1.toString().toDouble()
        val v2 = num2.toDouble()
        if (num1.isNotEmpty() && num2.isNotEmpty()) {
            val values = when(operation) {
                is CalculatorOperation.ADD -> v1.plus(v2)
                is CalculatorOperation.Divide -> v1.div(v2)
                is CalculatorOperation.Multiply -> v1.times(v2)
                is CalculatorOperation.Minus -> v1.minus(v2)
                else -> 0.0
            }
            result.postValue(values.toString())
        }
    }

    private fun clearAll(){
        details.postValue("")
        result.postValue("")
        operation= CalculatorOperation.None
        isFirst = true
        lastIsSymbol = false
        num2 = ""
        num1.clear()
    }

    private fun buildNumber(number: String) {
        details.postValue(details.value!!.plus(number))
        if (lastIsSymbol) {
            lastIsSymbol = false
        }
        if (isFirst){
            num1.append(number)
        }else {
            num2 = num2.plus(number)
        }
    }

    private fun saveOperation(operation: CalculatorOperation) {
        if (isFirst){
            isFirst = false
        }
        if (!lastIsSymbol) {
            details.postValue(details.value.plus(operation.symbol))
            lastIsSymbol = true
        } else {
            details.postValue(details.value!!.replaceLast(operation.symbol))
        }
        this.operation = operation
        if (result.value != ""){
            num1.clear()
            num1.append(result.value)
            num2=""
        }
    }
}