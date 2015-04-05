package com.github.idclark.calculator;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.tagmanager.Container;
import com.google.android.gms.tagmanager.ContainerHolder;
import com.google.android.gms.tagmanager.TagManager;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class MainActivity extends ActionBarActivity {
    private static final String CONTAINER_ID = "GTM-KNWNN4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new CalculatorFragment())
                    .commit();
        }

        TagManager tagManager = TagManager.getInstance(this);

        tagManager.setVerboseLoggingEnabled(true);

        PendingResult<ContainerHolder> pending =
                tagManager.loadContainerPreferNonDefault(CONTAINER_ID,
                        R.raw.gtmknwnn4v1);

        // The onResult method will be called as soon as one of the following happens:
        //     1. a saved container is loaded
        //     2. if there is no saved container, a network container is loaded
        //     3. the request times out. The example below uses a constant to manage the timeout period.
        pending.setResultCallback(new ResultCallback<ContainerHolder>() {
            @Override
            public void onResult(ContainerHolder containerHolder) {
                ContainerHolderSingleton.setContainerHolder(containerHolder);
                Container container = containerHolder.getContainer();
                if (!containerHolder.getStatus().isSuccess()) {
                    Log.e("CuteAnimals", "failure loading container");
                    //displayErrorToUser(R.string.load_error);
                    return;
                }
                ContainerHolderSingleton.setContainerHolder(containerHolder);
               // ContainerLoadedCallback.registerCallbacksForContainer(container);
               // containerHolder.setContainerAvailableListener(new ContainerLoadedCallback());
              //  startMainActivity();
            }
        }, 2, TimeUnit.SECONDS);
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
        private CalculatorLogic mCalculatorLogic;
        private static final String DIGITS = "0123456789.";
        DecimalFormat df = new DecimalFormat("@##########");


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
            mCalculatorLogic = new CalculatorLogic();

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
                ((TextView) rootView.findViewById(R.id.squared)).setText(Html.fromHtml("X<sup>2</sup>"));
                rootView.findViewById(R.id.squared).setOnClickListener(this);
            }
            if (rootView.findViewById(R.id.cubed) != null) {
                ((TextView) rootView.findViewById(R.id.cubed)).setText(Html.fromHtml("X<sup>3</sup>"));
                rootView.findViewById(R.id.cubed).setOnClickListener(this);
            }
            if (rootView.findViewById(R.id.etox) != null) {
                ((TextView) rootView.findViewById(R.id.etox)).setText(Html.fromHtml("e<small>x</sup>"));
                rootView.findViewById(R.id.etox).setOnClickListener(this);
            }
            if (rootView.findViewById(R.id.tentox) != null) {
                ((TextView) rootView.findViewById(R.id.tentox)).setText(Html.fromHtml("10<sup>x</sup>"));
                rootView.findViewById(R.id.tentox).setOnClickListener(this);
            }
            if (rootView.findViewById(R.id.etox) != null) {
                ((TextView) rootView.findViewById(R.id.etox)).setText(Html.fromHtml("e<sup>x</sup>"));
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
                    mCalculatorLogic.setOperand(Double.parseDouble(mCalculatorDisplay.getText().toString()));
                    userEnteringNumber = false;
                }
                mCalculatorLogic.performOperation(buttonPressed);
                mCalculatorDisplay.setText(df.format(mCalculatorLogic.getResult()));
            }

        }

        @Override
        public void onSaveInstanceState(Bundle oldState) {
            super.onSaveInstanceState(oldState);
            oldState.putDouble("OPERAND", mCalculatorLogic.getResult());
            oldState.putDouble("MEMORY", mCalculatorLogic.getMemory());

        }

    }
}
