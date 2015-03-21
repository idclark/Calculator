package com.github.idclark.calculator;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new CalculatorFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class CalculatorFragment extends Fragment implements OnClickListener {

        private TextView mCalculatorDisplay;
        private Boolean userEnteringNumber = false;
        private CalculatorBrain mCalculatorBrain;
        private static final String DIGITS = "0123456789.";
        DecimalFormat df = new DecimalFormat("@############");

        public CalculatorFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

//            //Restore variables on screen orientation change
//            mCalculatorBrain.setOperand(savedInstanceState.getDouble("OPERAND"));
//            mCalculatorBrain.setMemory(savedInstanceState.getDouble("MEMORY"));
//            mCalculatorDisplay.setText(df.format(mCalculatorBrain.getResult()));

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            mCalculatorBrain = new CalculatorBrain();

            mCalculatorDisplay = (TextView) rootView.findViewById(R.id.textView);

            rootView.findViewById(R.id.AC).setOnClickListener(this);
            rootView.findViewById(R.id.plusminus).setOnClickListener(this);
            rootView.findViewById(R.id.percent).setOnClickListener(this);
            rootView.findViewById(R.id.nine).setOnClickListener(this);
            rootView.findViewById(R.id.eight).setOnClickListener(this);
            rootView.findViewById(R.id.seven).setOnClickListener(this);
            rootView.findViewById(R.id.six).setOnClickListener(this);
            rootView.findViewById(R.id.five).setOnClickListener(this);
            rootView.findViewById(R.id.four).setOnClickListener(this);
            rootView.findViewById(R.id.three).setOnClickListener(this);
            rootView.findViewById(R.id.two).setOnClickListener(this);
            rootView.findViewById(R.id.one).setOnClickListener(this);
            rootView.findViewById(R.id.zero).setOnClickListener(this);
            rootView.findViewById(R.id.div).setOnClickListener(this);
            rootView.findViewById(R.id.mult).setOnClickListener(this);
            rootView.findViewById(R.id.plus).setOnClickListener(this);
            rootView.findViewById(R.id.minus).setOnClickListener(this);
            rootView.findViewById(R.id.dec).setOnClickListener(this);
            rootView.findViewById(R.id.equal).setOnClickListener(this);

            if (rootView.findViewById(R.id.MR) != null) {
                rootView.findViewById(R.id.MR).setOnClickListener(this);
            }
            if (rootView.findViewById(R.id.Mplus) != null) {
                rootView.findViewById(R.id.Mplus).setOnClickListener(this);
            }
            if (rootView.findViewById(R.id.Mminus) != null) {
                rootView.findViewById(R.id.Mminus).setOnClickListener(this);
            }
            if (rootView.findViewById(R.id.squared) != null) {
                rootView.findViewById(R.id.squared).setOnClickListener(this);
            }
            if (rootView.findViewById(R.id.cubed) != null) {
                rootView.findViewById(R.id.cubed).setOnClickListener(this);
            }
            if (rootView.findViewById(R.id.etox) != null) {
                rootView.findViewById(R.id.etox).setOnClickListener(this);
            }
            if (rootView.findViewById(R.id.tentox) != null) {
                rootView.findViewById(R.id.tentox).setOnClickListener(this);
            }
            if (rootView.findViewById(R.id.etox) != null) {
                rootView.findViewById(R.id.etox).setOnClickListener(this);
            }
            if (rootView.findViewById(R.id.oneoverx) != null) {
                rootView.findViewById(R.id.oneoverx).setOnClickListener(this);
            }
            if (rootView.findViewById(R.id.sqrt) != null) {
                rootView.findViewById(R.id.sqrt).setOnClickListener(this);
            }
            if (rootView.findViewById(R.id.sin) != null) {
                rootView.findViewById(R.id.sin).setOnClickListener(this);
            }
            if (rootView.findViewById(R.id.cos) != null) {
                rootView.findViewById(R.id.cos).setOnClickListener(this);
            }
            if (rootView.findViewById(R.id.tan) != null) {
                rootView.findViewById(R.id.tan).setOnClickListener(this);
            }
            if (rootView.findViewById(R.id.naturallog) != null) {
                rootView.findViewById(R.id.naturallog).setOnClickListener(this);
            }
            if (rootView.findViewById(R.id.pi) != null) {
                rootView.findViewById(R.id.pi).setOnClickListener(this);
            }
            if (rootView.findViewById(R.id.random) != null) {
                rootView.findViewById(R.id.random).setOnClickListener(this);
            }

            return rootView;
        }

        @Override
        public void onClick(View v) {
            String buttonPressed = ((Button )v).getText().toString();

            if (DIGITS.contains(buttonPressed)) {

                // digit was pressed
                if (userEnteringNumber) {
                    if (buttonPressed.equals(".") && mCalculatorDisplay.getText().toString().contains(".")) {
                        // ERROR PREVENTION
                        // Eliminate entering multiple decimals
                    } else {
                        mCalculatorDisplay.append(buttonPressed);
                    }

                } else {
                    if (buttonPressed.equals(".")) {
                        // ERROR PREVENTION
                        // This will avoid error if only the decimal is hit before an operator, by placing a leading zero
                        // before the decimal
                        mCalculatorDisplay.setText(0 + buttonPressed);
                    } else {
                        mCalculatorDisplay.setText(buttonPressed);
                    }
                    userEnteringNumber = true;
                }

            } else {
                if (userEnteringNumber) {
                    mCalculatorBrain.setOperand(Double.parseDouble(mCalculatorDisplay.getText().toString()));
                    userEnteringNumber = false;
                }
                mCalculatorBrain.performOperation(buttonPressed);
                mCalculatorDisplay.setText(df.format(mCalculatorBrain.getResult()));
            }

        }

        @Override
        public void onSaveInstanceState(Bundle oldState) {
            super.onSaveInstanceState(oldState);
            oldState.putDouble("OPERAND", mCalculatorBrain.getResult());
            oldState.putDouble("MEMORY", mCalculatorBrain.getMemory());

        }

    }
}
