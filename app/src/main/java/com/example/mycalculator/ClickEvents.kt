package com.example.mycalculator

import android.view.View
import androidx.databinding.BindingAdapter

class ClickEvents {
    @BindingAdapter("clearBtnClicked")
    fun clearBtnClicked(view: View,viewModel: MainViewModel){
        viewModel.onAction(CalculatorAction.Clear)
    }
    @BindingAdapter("numberBtnClicked")
    fun numberBtnClicked(view: View, viewModel: MainViewModel){
        viewModel.onAction(CalculatorAction.Number(view.tag as String))
    }
    @BindingAdapter("operationBtnClicked")
    fun operationBtnClicked(view: View, viewModel: MainViewModel){
        val operation = when(view.tag){
            "1" -> CalculatorOperation.Divide
            "2" -> CalculatorOperation.Multiply
            "3" -> CalculatorOperation.Minus
            "4" -> CalculatorOperation.ADD
            else -> CalculatorOperation.None
        }
        viewModel.onAction(CalculatorAction.Operation(operation))
    }
}