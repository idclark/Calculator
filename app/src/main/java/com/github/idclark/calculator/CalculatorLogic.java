package com.github.idclark.calculator;

/**
 * Created by idclark on 3/7/15.
 *
 * 3 + 6 = 9
 * 3 and 6 are called the operand
 * the + is called the operator
 * 9 is the result of the operation
 *
 */
public class CalculatorLogic {

    private double mOperand;
    private double mWaitingOperand;
    private String mWaitingOperator;
    private double mCalculatorMemory;
    private static final String UNDEFINED = "Undefined";

    //define string representations of buttons
    public static final String ADD = "+";
    public static final String SUBTRACT = "-";
    public static final String DIVIDE = "/";
    public static final String MULTIPLY = "X";

    public static final String CLEARMEMORY = "AC";
    public static final String TOGGLESIGN = "+/-";
    public static final String ADDMEMORY = "M+";
    public static final String SUBTRACTMEMORY = "M-";
    public static final String RECALMEMORY = "MR";
    public static final String SQUARED = "x2";
    public static final String CUBED = "x3";
    public static final String E_TO_X = "ex";
    public static final String TEN_TO_X = "10x";
    public static final String INVERSE = "1/x";
    public static final String SQRT  = "sqrt";
    public static final String SIN = "sin";
    public static final String COSIN = "cos";
    public static final String TANGENT = "tangent";
    public static final String PI = "pi";
    public static final String NAT_LOG = "ln";
    public static final String RANDOM_NUM = "rand";
    public static final String PERCENT = "%";

    public CalculatorLogic() {
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

    public void setMemory(double calculatorMemory) {
        mCalculatorMemory = calculatorMemory;
    }

    public double getMemory() {
        return mCalculatorMemory;
    }

    public String toString() {
        return Double.toString(mOperand);
    }

    /**
     *
     * @param operator one of the buttons that is not a digit
     * @return a double the result of the operation.
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

            case ADDMEMORY:
                mCalculatorMemory = mCalculatorMemory + mOperand;
                break;
            case SUBTRACTMEMORY:
                mCalculatorMemory = mCalculatorMemory + mOperand;
                break;

            case RECALMEMORY:
                mOperand = mCalculatorMemory;
                break;

            case SQUARED:
                mOperand = Math.pow(mOperand, 2);
                break;

            case CUBED:
                mOperand = Math.pow(mOperand, 3);
                break;

            case E_TO_X:
                mOperand = Math.exp(mOperand);
                break;

            case TEN_TO_X:
                mOperand = Math.pow(10, mOperand);
                break;

            case INVERSE:
                if (mOperand != 0) {
                    mOperand = 1 / mOperand;
                }
                break;

            case SQRT:
                mOperand = Math.sqrt(mOperand);
                break;

            case SIN:
                mOperand = Math.sin(mOperand);
                break;

            case COSIN:
                mOperand = Math.cos(mOperand);
                break;

            case TANGENT:
                mOperand = Math.tan(mOperand);
                break;

            case PI:
                mOperand = Math.PI;
                break;

            case NAT_LOG:
                mOperand = Math.log(mOperand);
                break;

            case RANDOM_NUM:
                mOperand = Math.random();
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
