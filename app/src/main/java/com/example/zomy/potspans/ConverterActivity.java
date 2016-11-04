package com.example.zomy.potspans;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.zomy.potspans.utils.Utilities;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConverterActivity extends AppCompatActivity {

    @BindView(R.id.from)
    Spinner fromSpinner;
    @BindView(R.id.to)
    Spinner toSpinner;
    @BindView(R.id.amount)
    EditText amountEditText;
    @BindView(R.id.result)
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        ButterKnife.bind(ConverterActivity.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.units, R.layout.spinner_bg);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.convert_button)
    public void convert(){
        String amountString = amountEditText.getText().toString();
        try {
            double result = 0;
            double amount = Double.parseDouble(amountString);
            Log.d("hazem", "convert: " + fromSpinner.getSelectedItemPosition());
            Log.d("hazem", "convert: " + toSpinner.getSelectedItemPosition());
            switch (fromSpinner.getSelectedItemPosition()){
                case 0:
                    switch (toSpinner.getSelectedItemPosition()){
                        case 0:
                            result = amount * Utilities.TE_SPOON_TO_TE_SPOON;
                            break;
                        case 1:
                            result = amount * Utilities.TE_SPOON_TO_D_SPOON;
                            break;
                        case 2:
                            result = amount * Utilities.TE_SPOON_TO_TA_SPOON;
                            Log.d("hazem", "convert: " + amount + " * " + Utilities.TE_SPOON_TO_TA_SPOON  + " = " + result);
                            break;
                        case 3:
                            result = amount * Utilities.TE_SPOON_TO_CUP;
                            break;
                        case 4:
                            result = amount * Utilities.TE_SPOON_TO_M_LITER;
                            break;
                        case 5:
                            result = amount * Utilities.TE_SPOON_TO_D_LITER;
                            break;
                        case 6:
                            result = amount * Utilities.TE_SPOON_TO_LITER;
                            break;
                        case 7:
                            result = amount * Utilities.TE_SPOON_TO_GRAM;
                            break;
                        case 8:
                            result = amount * Utilities.TE_SPOON_TO_K_GRAM;
                            break;
                    }
                    break;
                case 1:
                    switch (toSpinner.getSelectedItemPosition()){
                        case 0:
                            result = amount * Utilities.D_SPOON_TO_TE_SPOON;
                            break;
                        case 1:
                            result = amount * Utilities.D_SPOON_TO_D_SPOON;
                            break;
                        case 2:
                            result = amount * Utilities.D_SPOON_TO_TA_SPOON;
                            break;
                        case 3:
                            result = amount * Utilities.D_SPOON_TO_CUP;
                            break;
                        case 4:
                            result = amount * Utilities.D_SPOON_TO_M_LITER;
                            break;
                        case 5:
                            result = amount * Utilities.D_SPOON_TO_D_LITER;
                            break;
                        case 6:
                            result = amount * Utilities.D_SPOON_TO_LITER;
                            break;
                        case 7:
                            result = amount * Utilities.D_SPOON_TO_GRAM;
                            break;
                        case 8:
                            result = amount * Utilities.D_SPOON_TO_K_GRAM;
                            break;
                    }
                    break;
                case 2:
                    switch (toSpinner.getSelectedItemPosition()){
                        case 0:
                            result = amount * Utilities.TA_SPOON_TO_TE_SPOON;
                            break;
                        case 1:
                            result = amount * Utilities.TA_SPOON_TO_D_SPOON;
                            break;
                        case 2:
                            result = amount * Utilities.TA_SPOON_TO_TA_SPOON;
                            break;
                        case 3:
                            result = amount * Utilities.TA_SPOON_TO_CUP;
                            break;
                        case 4:
                            result = amount * Utilities.TA_SPOON_TO_M_LITER;
                            break;
                        case 5:
                            result = amount * Utilities.TA_SPOON_TO_D_LITER;
                            break;
                        case 6:
                            result = amount * Utilities.TA_SPOON_TO_LITER;
                            break;
                        case 7:
                            result = amount * Utilities.TA_SPOON_TO_GRAM;
                            break;
                        case 8:
                            result = amount * Utilities.TA_SPOON_TO_K_GRAM;
                            break;
                    }
                    break;
                case 3:
                    switch (toSpinner.getSelectedItemPosition()){
                        case 0:
                            result = amount * Utilities.CUP_TO_TE_SPOON;
                            break;
                        case 1:
                            result = amount * Utilities.CUP_TO_D_SPOON;
                            break;
                        case 2:
                            result = amount * Utilities.CUP_TO_TA_SPOON;
                            break;
                        case 3:
                            result = amount * Utilities.CUP_TO_CUP;
                            break;
                        case 4:
                            result = amount * Utilities.CUP_TO_M_LITER;
                            break;
                        case 5:
                            result = amount * Utilities.CUP_TO_D_LITER;
                            break;
                        case 6:
                            result = amount * Utilities.CUP_TO_LITER;
                            break;
                        case 7:
                            result = amount * Utilities.CUP_TO_GRAM;
                            break;
                        case 8:
                            result = amount * Utilities.CUP_TO_K_GRAM;
                            break;
                    }
                    break;
                case 4:
                    switch (toSpinner.getSelectedItemPosition()){
                        case 0:
                            result = amount * Utilities.M_LITER_TO_TE_SPOON;
                            break;
                        case 1:
                            result = amount * Utilities.M_LITER_TO_D_SPOON;
                            break;
                        case 2:
                            result = amount * Utilities.M_LITER_TO_TA_SPOON;
                            break;
                        case 3:
                            result = amount * Utilities.M_LITER_TO_CUP;
                            break;
                        case 4:
                            result = amount * Utilities.M_LITER_TO_M_LITER;
                            break;
                        case 5:
                            result = amount * Utilities.M_LITER_TO_D_LITER;
                            break;
                        case 6:
                            result = amount * Utilities.M_LITER_TO_LITER;
                            break;
                        case 7:
                            result = amount * Utilities.M_LITER_TO_GRAM;
                            break;
                        case 8:
                            result = amount * Utilities.M_LITER_TO_K_GRAM;
                            break;
                    }
                    break;
                case 5:
                    switch (toSpinner.getSelectedItemPosition()){
                        case 0:
                            result = amount * Utilities.D_LITER_TO_TE_SPOON;
                            break;
                        case 1:
                            result = amount * Utilities.D_LITER_TO_D_SPOON;
                            break;
                        case 2:
                            result = amount * Utilities.D_LITER_TO_TA_SPOON;
                            break;
                        case 3:
                            result = amount * Utilities.D_LITER_TO_CUP;
                            break;
                        case 4:
                            result = amount * Utilities.D_LITER_TO_M_LITER;
                            break;
                        case 5:
                            result = amount * Utilities.D_LITER_TO_D_LITER;
                            break;
                        case 6:
                            result = amount * Utilities.D_LITER_TO_LITER;
                            break;
                        case 7:
                            result = amount * Utilities.D_LITER_TO_GRAM;
                            break;
                        case 8:
                            result = amount * Utilities.D_LITER_TO_K_GRAM;
                            break;
                    }
                    break;
                case 6:
                    switch (toSpinner.getSelectedItemPosition()){
                        case 0:
                            result = amount * Utilities.LITER_TO_TE_SPOON;
                            break;
                        case 1:
                            result = amount * Utilities.LITER_TO_D_SPOON;
                            break;
                        case 2:
                            result = amount * Utilities.LITER_TO_TA_SPOON;
                            break;
                        case 3:
                            result = amount * Utilities.LITER_TO_CUP;
                            break;
                        case 4:
                            result = amount * Utilities.LITER_TO_M_LITER;
                            break;
                        case 5:
                            result = amount * Utilities.LITER_TO_D_LITER;
                            break;
                        case 6:
                            result = amount * Utilities.LITER_TO_LITER;
                            break;
                        case 7:
                            result = amount * Utilities.LITER_TO_GRAM;
                            break;
                        case 8:
                            result = amount * Utilities.LITER_TO_K_GRAM;
                            break;
                    }
                    break;
                case 7:
                    switch (toSpinner.getSelectedItemPosition()){
                        case 0:
                            result = amount * Utilities.GRAM_TO_TE_SPOON;
                            break;
                        case 1:
                            result = amount * Utilities.GRAM_TO_D_SPOON;
                            break;
                        case 2:
                            result = amount * Utilities.GRAM_TO_TA_SPOON;
                            break;
                        case 3:
                            result = amount * Utilities.GRAM_TO_CUP;
                            break;
                        case 4:
                            result = amount * Utilities.GRAM_TO_M_LITER;
                            break;
                        case 5:
                            result = amount * Utilities.GRAM_TO_D_LITER;
                            break;
                        case 6:
                            result = amount * Utilities.GRAM_TO_LITER;
                            break;
                        case 7:
                            result = amount * Utilities.GRAM_TO_GRAM;
                            break;
                        case 8:
                            result = amount * Utilities.GRAM_TO_K_GRAM;
                            break;
                    }
                    break;
                case 8:
                    switch (toSpinner.getSelectedItemPosition()){
                        case 0:
                            result = amount * Utilities.K_GRAM_TO_TE_SPOON;
                            break;
                        case 1:
                            result = amount * Utilities.K_GRAM_TO_D_SPOON;
                            break;
                        case 2:
                            result = amount * Utilities.K_GRAM_TO_TA_SPOON;
                            break;
                        case 3:
                            result = amount * Utilities.K_GRAM_TO_CUP;
                            break;
                        case 4:
                            result = amount * Utilities.K_GRAM_TO_M_LITER;
                            break;
                        case 5:
                            result = amount * Utilities.K_GRAM_TO_D_LITER;
                            break;
                        case 6:
                            result = amount * Utilities.K_GRAM_TO_LITER;
                            break;
                        case 7:
                            result = amount * Utilities.K_GRAM_TO_GRAM;
                            break;
                        case 8:
                            result = amount * Utilities.K_GRAM_TO_K_GRAM;
                            break;
                    }
                    break;
            }
            String resultString = Math.round(result * 100) / 100f + " " + toSpinner.getSelectedItem().toString();
            resultTextView.setText(resultString);
        }catch (Exception e){
            amountEditText.setError(getString(R.string.error));
        }
    }
}
