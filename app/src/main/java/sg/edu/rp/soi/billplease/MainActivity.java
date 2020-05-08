package sg.edu.rp.soi.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    //Declare TextViews
    TextView amountTitle;
    TextView noOfPeopleTitle;
    TextView additionalChargesTitle;
    TextView resultTotalAmountTitle;
    TextView resultTotalAmount;
    TextView resultPerPersonTitle;
    TextView resultPerPerson;

    //Declare Buttons
    ToggleButton gstButton;
    ToggleButton serviceChargeButton;
    Button calculateButton;
    Button resetButton;

    //Declare EditTexts
    EditText inputBill;
    EditText inputPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Link fields to UI components
        amountTitle            = findViewById(R.id.amountTitle);
        noOfPeopleTitle        = findViewById(R.id.noOfPeopleTitle);
        additionalChargesTitle = findViewById(R.id.additionalChargesTitle);
        resultTotalAmountTitle = findViewById(R.id.resultTotalAmountTitle);
        resultTotalAmount      = findViewById(R.id.resultTotalAmount);
        resultPerPersonTitle   = findViewById(R.id.resultPerPersonTitle);
        resultPerPerson        = findViewById(R.id.resultPerPerson);

        gstButton              = findViewById(R.id.gstButton);
        serviceChargeButton    = findViewById(R.id.serviceChargeButton);
        calculateButton        = findViewById(R.id.calculateButton);
        resetButton            = findViewById(R.id.resetButton);

        inputBill              = findViewById(R.id.inputBill);
        inputPeople            = findViewById(R.id.inputPeople);

        calculateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(inputBill.getText().length() == 0 || inputPeople.getText().length() == 0)
                {
                    String error = "Please input both values";
                    inputBill.setText(error);
                    inputPeople.setText(error);
                }
                else
                {
                    //Convert to int/double
                    int peopleAmount = Integer.parseInt(inputPeople.getText().toString());
                    double billAmount1 = Integer.parseInt(inputBill.getText().toString());

                    //Calculation
                    double costPerPerson1 = billAmount1 / peopleAmount;
                    String costPerPerson = String.format("$%.2f", costPerPerson1);

                    double costPerPersonGST1 = (billAmount1 / peopleAmount) * 1.07;
                    String costPerPersonGST = String.format("$%.2f", costPerPersonGST1);

                    double costPerPersonSC1  = (billAmount1 / peopleAmount) * 1.10;
                    String costPerPersonSC = String.format("$%.2f", costPerPersonSC1);

                    double costPerPersonAll1 = (billAmount1 / peopleAmount) * 1.17;
                    String costPerPersonAll = String.format("$%.2f", costPerPersonAll1);

                    //Format to 2 dec
                    String billAmount = String.format("$%.2f", billAmount1);

                    double billAmountGST1 = billAmount1 * 1.07;
                    String billAmountGST = String.format("$%.2f", billAmountGST1);

                    double billAmountSC1 = billAmount1 * 1.10;
                    String billAmountSC = String.format("$%.2f", billAmountSC1);

                    double billAmountAll1 = billAmount1 * 1.17;
                    String billAmountAll = String.format("$%.2f", billAmountAll1);

                    if(!gstButton.isChecked() && !serviceChargeButton.isChecked()) {
                        //Display
                        resultTotalAmount.setText(billAmount);
                        resultPerPerson.setText(costPerPerson);

                    }
                    else if(!gstButton.isChecked() && serviceChargeButton.isChecked()){
                        //Display
                        resultTotalAmount.setText(billAmountSC);
                        resultPerPerson.setText(costPerPersonSC);
                    }
                    else if(gstButton.isChecked() && !serviceChargeButton.isChecked()){
                        //Display
                        resultTotalAmount.setText(billAmountGST);
                        resultPerPerson.setText(costPerPersonGST);
                    }
                    else if(gstButton.isChecked()&& serviceChargeButton.isChecked()){
                        //Display
                        resultTotalAmount.setText(billAmountAll);
                        resultPerPerson.setText(costPerPersonAll);
                    }
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                resultTotalAmount.setText("");
                resultPerPerson.setText("");
                inputPeople.setText("");
                inputBill.setText("");
                gstButton.setChecked(false);
                serviceChargeButton.setChecked(false);
            }
        });
    }
}
