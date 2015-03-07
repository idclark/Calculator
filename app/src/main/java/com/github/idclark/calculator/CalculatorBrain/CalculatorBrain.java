package com.github.idclark.calculator.CalculatorBrain;

/**
 * Created by idclark on 3/7/15.
 *
 * 3 + 6 = 9
 * 3 and 6 are called the operand
 * the + is called the operator
 * 9 is the result of the operation
 *
 */
public class CalculatorBrain {

    private double mOperand;
    private double mWaitingOperand;
    private String mWaitingOperator;
    private double mCalculatorMemory;

    //define string representations of buttons
    public static final String ADD = "+";
    public static final String SUBTRACT = "-";
    public static final String DIVIDE = "/";
    public static final String MULTIPLY = "*";

    public static final String CLEARMEMORY = "AC";
    public static final String TOGGLESIGN = "+/-";
    public static final String PERCENT = "%";

    public CalculatorBrain() {
        mOperand = 0;
        mWaitingOperand = 0;
        mWaitingOperator = "";
        mCalculatorMemory = 0;
    }

    public void setOperand(double operand) {
        mOperand = operand;
    }

    public double getResult() {
        return mOperand;
    }

    public String toString() {
        return Double.toString(mOperand);
    }

    /*
      operator is one of + - / or *.
    */
    protected double performOperation(String operator) {
        switch (operator){
            case CLEARMEMORY:
                mCalculatorMemory = 0;
                mOperand = 0;
                mWaitingOperand = 0;
                mWaitingOperator = "";
                break;
            case TOGGLESIGN:
                mOperand = -mOperand;
                break;
            case PERCENT:
                mOperand = 0.1 * mOperand;
                break;
        }
        performWaitingOperation();
        mWaitingOperator = operator;
        mWaitingOperand = mOperand;

        return mOperand;
    }

    protected void performWaitingOperation() {
        switch (mWaitingOperator) {
            case ADD:
                mOperand = mWaitingOperand + mOperand;
                break;
            case SUBTRACT:
                mOperand = mWaitingOperand - mOperand;
                break;
            case MULTIPLY:
                mOperand = mWaitingOperand * mOperand;
                break;
            case DIVIDE:
                if (mOperand != 0) {
                    mOperand = mWaitingOperand / mOperand;
                }
        }

    }
}
