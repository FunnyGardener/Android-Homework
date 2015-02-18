package net.k6.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


public class CalculatorActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_activity);
        final EditText numberOne = (EditText) findViewById(R.id.numberOne);
        final EditText numberTwo = (EditText) findViewById(R.id.numberTwo);
        final TextView resultText = (TextView) findViewById(R.id.resultText);
        final RadioGroup actionRadioGroup = (RadioGroup) findViewById(R.id.actionRadioGroup);
        Button resultButton = (Button) findViewById(R.id.resultButton);
        if (savedInstanceState != null && savedInstanceState.containsKey("numberOne")) {
           numberOne.setText(savedInstanceState.getCharSequence("numberOne"));
        }
        if (savedInstanceState != null && savedInstanceState.containsKey("numberTwo")) {
            numberTwo.setText(savedInstanceState.getCharSequence("numberTwo"));
        }
        if (savedInstanceState != null && savedInstanceState.containsKey("resultText")) {
            resultText.setText(savedInstanceState.getCharSequence("resultText"));
        }
        if (savedInstanceState != null && savedInstanceState.containsKey("actionRadioGroup")) {
            actionRadioGroup.check(savedInstanceState.getInt("actionRadioGroup"));
        }
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOne.getText() != null || numberTwo.getText() != null || actionRadioGroup.getCheckedRadioButtonId() != 0) {
                    switch (actionRadioGroup.getCheckedRadioButtonId()) {
                        case R.id.sumButton:
                            resultText.setText(String.valueOf(Double.parseDouble(numberOne.getText().toString())
                                    + (Double.parseDouble(numberTwo.getText().toString()))));
                            break;
                        case R.id.subtractionButton:
                            resultText.setText(String.valueOf(Double.parseDouble(numberOne.getText().toString())
                                    - (Double.parseDouble(numberTwo.getText().toString()))));
                            break;
                        case R.id.multiplyButton:
                            resultText.setText(String.valueOf(Double.parseDouble(numberOne.getText().toString())
                                    * (Double.parseDouble(numberTwo.getText().toString()))));
                            break;
                        case R.id.divideButton:
                            if (Double.parseDouble(numberTwo.getText().toString()) != 0) {
                                resultText.setText(String.valueOf(Double.parseDouble(numberOne.getText().toString())
                                        / (Double.parseDouble(numberTwo.getText().toString()))));
                            }
                            break;
                    }
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        EditText numberOne = (EditText) findViewById(R.id.numberOne);
        EditText numberTwo = (EditText) findViewById(R.id.numberTwo);
        TextView resultText = (TextView) findViewById(R.id.resultText);
        RadioGroup actionRadioGroup = (RadioGroup) findViewById(R.id.actionRadioGroup);
        if (numberOne.getText() != null) {
            saveInstanceState.putCharSequence("numberOne", numberOne.getText());
        }
        if (numberTwo.getText() != null) {
            saveInstanceState.putCharSequence("numberTwo", numberTwo.getText());
        }
        if (resultText.getText() != null) {
            saveInstanceState.putCharSequence("resultText", resultText.getText());
        }
        if (actionRadioGroup.getCheckedRadioButtonId() != 0) {
            saveInstanceState.putInt("actionRadioGroup", actionRadioGroup.getCheckedRadioButtonId());
        }
    }
}
