package com.myapplicationdev.android.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    var lastNumaric: Boolean= false
    var stateError: Boolean = false
    var lastDot :Boolean=false


    fun onPercent(view: View)
    {
        val text = tvNum.text.toString()+"/100"
        val expression= ExpressionBuilder(text).build()
        try
        {
            val result= expression.evaluate()
            tvNum.text= result.toString()
            lastDot=true
        }catch (ex:Exception)
        {
            tvNum.text="Error"
            stateError=true
            lastNumaric=false
        }
    }
    fun onPlusMins(view: View)
    {
        val text = tvNum.text.toString()+"*-1"
        val expression= ExpressionBuilder(text).build()
        try
        {
            val result= expression.evaluate()
            tvNum.text= result.toString()
            lastDot=true
        }catch (ex:Exception)
        {
            tvNum.text="Error"
            stateError=true
            lastNumaric=false
        }
    }

    fun onDigit(view: View)
    {
        if(stateError)
        {
            tvNum.text=(view as Button).text
            stateError=false
        }else {
            tvNum.append((view as Button).text)
        }
        lastNumaric=true
    }
    fun onDecimalPoint(view: View)
    {
        if(lastNumaric && !stateError && !lastDot)
        {
            tvNum.append(".")
            lastNumaric=false
            lastDot=true
        }
    }

    fun onOperator (view: View)
    {
        if(lastNumaric && !stateError)
        {
            tvNum.append((view as Button).text)
            lastNumaric=false
            lastDot=false
        }
    }


    fun onClear(view: View)
    {
        this.tvNum.text= ""
        lastNumaric=false
        stateError=false
        lastDot=false
    }
    fun onEqual(view: View)
    {

        if(lastNumaric && !stateError)
        {
            val text = tvNum.text.toString()
            val expression= ExpressionBuilder(text).build()
            try
            {
                val result= expression.evaluate()
                val r1 = result.toString().split(".")
                if (r1[1].toDouble()>0){
                    tvNum.text= result.toString()
                } else{
                    tvNum.text= r1[0]
                }
                lastDot=true
            }catch (ex:Exception)
            {
                tvNum.text="Error"
                stateError=true
                lastNumaric=false
            }
        }

    }

//    fun btnOnClick(view: View){
//        var num1:Double
//        var num3 = 0.0
//        var oper = ""
//        val btnSelected = view as Button
//        when(btnSelected.id) {
//            btnAC.id -> tvNum.text = "0"
//            btnPlusMins.id ->
//                if (num3>0){
//                    tvNum.text = (num3*-1).toString()
//                } else if (num3>0){
//                    tvNum.text = (num3*-1).toString()
//                }
//            btnPercent.id -> tvNum.text = (num3/100).toString()
//            btnDivide.id -> oper = "/"
//            btn7.id -> num1 = 7
//            btn8.id -> num1 = 8
//            btn9.id -> num1 = 9
//            btnMutiply.id -> oper = "*"
//            btn4.id -> num1 = 4
//            btn5.id -> num1 = 5
//            btn6.id -> num1 = 6
//            btnMinus.id -> oper = "-"
//            btn1.id -> num1 = 1
//            btn2.id -> num1 = 2
//            btn3.id -> num1 = 3
//            btnPlus.id -> oper = "+"
//            btn0.id ->
//            btnDot.id -> oper = "."
//            btnEqual.id -> oper = "="
//        }
//        //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
//        tvNum.text = num1
//    }
}
