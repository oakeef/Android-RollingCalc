package com.example.evan.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9,
            buttonSub, buttonAdd, buttonDivide, buttonMultiply, buttonDecimal, buttonEquals, buttonBack,
            buttonClear, buttonPosNeg;

    TextView textView;

    Operator nextOperator = Operator.NONE;
    int rollingTotal = 0;
    int incomingNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //define all the GUI elements
        button0 =(Button) findViewById(R.id.btn0);
        button1 =(Button) findViewById(R.id.btn1);
        button2 =(Button) findViewById(R.id.btn2);
        button3 =(Button) findViewById(R.id.btn3);
        button4 =(Button) findViewById(R.id.btn4);
        button5 =(Button) findViewById(R.id.btn5);
        button6 =(Button) findViewById(R.id.btn6);
        button7 =(Button) findViewById(R.id.btn7);
        button8 =(Button) findViewById(R.id.btn8);
        button9 =(Button) findViewById(R.id.btn9);
        buttonSub =(Button) findViewById(R.id.btnSubtract);
        buttonAdd =(Button) findViewById(R.id.btnAdd);
        buttonDivide =(Button) findViewById(R.id.btnDivide);
        buttonMultiply =(Button) findViewById(R.id.btnMultiply);
        buttonDecimal =(Button) findViewById(R.id.btnDecimal);
        buttonEquals =(Button) findViewById(R.id.btnEquals);
        buttonPosNeg = (Button) findViewById(R.id.btnPosNeg);
        buttonClear = (Button) findViewById(R.id.btnClear);
        buttonBack = (Button) findViewById(R.id.btnBack);
        textView = (TextView) findViewById(R.id.txtViewAnswer);

        //Number buttons go here
        button3.setOnClickListener(new NumberBtnClick(3));
        button4.setOnClickListener(new NumberBtnClick(4));
        button1.setOnClickListener(new NumberBtnClick(1));
        button5.setOnClickListener(new NumberBtnClick(5));
        button7.setOnClickListener(new NumberBtnClick(7));
        button8.setOnClickListener(new NumberBtnClick(8));
        button9.setOnClickListener(new NumberBtnClick(9));
        button0.setOnClickListener(new NumberBtnClick(0));
        button2.setOnClickListener(new NumberBtnClick(2));
        button6.setOnClickListener(new NumberBtnClick(6));

        //Operator Buttons go here
        buttonAdd.setOnClickListener(new OperatorBtnClick(Operator.PLUS));
        buttonSub.setOnClickListener(new OperatorBtnClick(Operator.MINUS));
        buttonMultiply.setOnClickListener(new OperatorBtnClick(Operator.MULTIPLY));
        buttonDivide.setOnClickListener(new OperatorBtnClick(Operator.DIVIDE));



    }//end OnCreate

    //Functions go here
    public void clearScreen(View v){
        rollingTotal = 0;
        incomingNumber =0;
        nextOperator = Operator.NONE;
        textView.setText("");
    }

    //function updates the display with whatever has changed behind the scenes
    public void updateDisplay() {
        textView.setText(Integer.toString(rollingTotal));
        textView.append(Character.toString(operatorToChar(nextOperator)));
    }

    //function takes the operator and turns it into a char to add to textView
    public char operatorToChar(Operator o) {
        if(o == Operator.PLUS) {
            return '+';
        }else if(o == Operator.MINUS){
            return '-';
        }else if(o == Operator.MULTIPLY){
            return 'x';
        }else if(o == Operator.DIVIDE){
            return '/';
        }
        return '\0';
    }

    //used an enum for the operators since they never change
    enum Operator { PLUS, MINUS, MULTIPLY, DIVIDE, NONE};

    //got help with this one from a friend. They taught me how the OnClickListener class works
    //and how I can implement it. I used one for NUmber buttons and one for operator buttons.
    class NumberBtnClick implements View.OnClickListener {
        int btnValue;

        NumberBtnClick(int value) {
            this.btnValue = value;
        }

        public void onClick(View v) {
            textView.append(Integer.toString(btnValue));
        }
    }

    class OperatorBtnClick implements View.OnClickListener {
        Operator operator;

        OperatorBtnClick(Operator operator) {
            this.operator = operator;
        }

        public int findIncomingNumber(Operator o){
            String operation="";
            if(o == Operator.PLUS) {
                operation ="+";
            }else if(o == Operator.MINUS){
                operation = "-";
            }else if(o == Operator.MULTIPLY){
                operation = "x";
            }else if(o == Operator.DIVIDE){
                operation = "/";
            }
                String newNumber = textView.getText().toString();
                newNumber = newNumber.substring(newNumber.lastIndexOf(operation) + 1);
                return Integer.parseInt(newNumber);

        };

        public void onClick(View v) {

            if(nextOperator == Operator.PLUS) {

                    rollingTotal += findIncomingNumber(Operator.PLUS);

                }else if(nextOperator == Operator.MINUS){

                    rollingTotal -=  findIncomingNumber(Operator.MINUS);

                }else if(nextOperator == Operator.MULTIPLY) {

                    rollingTotal *= findIncomingNumber(Operator.MULTIPLY);

                }else if(nextOperator == Operator.DIVIDE) {

                    rollingTotal /= findIncomingNumber(Operator.DIVIDE);

                }else if(nextOperator == Operator.NONE) {

                    incomingNumber = Integer.parseInt(textView.getText().toString());
                    rollingTotal = incomingNumber;
                }//end else

            nextOperator = this.operator;
            updateDisplay();
        }
    }



}//end MainActivity
