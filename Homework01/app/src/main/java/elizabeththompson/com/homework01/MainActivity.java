package elizabeththompson.com.homework01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

/*
 * homework 01
 * MainActivity.java
 * elizabeth thompson
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int userWeight;
    private String userGender;
    private double totalBacLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // create and setup content view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add icon in action bar
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.beer_24px);

        // set onclick listeners for buttons
        findViewById(R.id.saveUserDataButton).setOnClickListener(this);
        findViewById(R.id.addDrinkButton).setOnClickListener(this);
        findViewById(R.id.resetButton).setOnClickListener(this);

        // set onchange listener for switch
        final Switch genderSwitch = (Switch) findViewById(R.id.genderSwitch);
        genderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (getString(R.string.label_gender_male).equals(genderSwitch.getText().toString())) {
                    genderSwitch.setText(R.string.label_gender_female);
                } else {
                    genderSwitch.setText(R.string.label_gender_male);
                }
            }
        });

        // set onchange listener for seekbar
        final SeekBar alcoholPercentageSeekBar = (SeekBar) findViewById(R.id.alcoholPercentageSeekBar);
        final TextView alcoholPercentageValueLabel = (TextView) findViewById(R.id.alcoholPercentageValueLabel);
        alcoholPercentageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // move progress to closest multiple of 5 when user selects
                if (b) alcoholPercentageSeekBar.setProgress((int) (Math.rint((double) i / 5) * 5));
                alcoholPercentageValueLabel.setText(alcoholPercentageSeekBar.getProgress() + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    @Override
    public void onClick(View v) {
        // retrieve all the necessary view components
        EditText weightEditText = (EditText) findViewById(R.id.weightEditText);
        Switch genderSwitch = (Switch) findViewById(R.id.genderSwitch);
        RadioGroup drinkSizeRadioGroup = (RadioGroup) findViewById(R.id.drinkSizeRadioGroup);
        RadioButton selectedDrinkSize = (RadioButton) findViewById(drinkSizeRadioGroup.getCheckedRadioButtonId());
        SeekBar alcoholPercentageSeekBar = (SeekBar) findViewById(R.id.alcoholPercentageSeekBar);
        TextView alcoholPercentageValueLabel = (TextView) findViewById(R.id.alcoholPercentageValueLabel);
        TextView bacLevelValueLabel = (TextView) findViewById(R.id.bacLevelValueLabel);
        ProgressBar bacLevelProgressBar = (ProgressBar) findViewById(R.id.bacLevelProgressBar);
        TextView statusValueLabel = (TextView) findViewById(R.id.statusValueLabel);

        switch (v.getId()) {
            case R.id.saveUserDataButton:
                if (!weightEditText.getText().toString().isEmpty()) {
                    // save the user input data
                    this.saveUserData(bacLevelValueLabel, weightEditText, genderSwitch);
                } else {
                    weightEditText.setError("Please enter weight");
                }
                break;

            case R.id.addDrinkButton:
                if (this.getUserWeight() != 0) {
                    // calculate the bac level
                    this.addDrink(selectedDrinkSize, alcoholPercentageSeekBar,
                            bacLevelValueLabel);
                } else {
                    weightEditText.setError("Please enter weight");
                }

                break;

            case R.id.resetButton:
                // reset the application values
                this.reset(weightEditText, genderSwitch, drinkSizeRadioGroup, alcoholPercentageSeekBar,
                        alcoholPercentageValueLabel, bacLevelValueLabel, bacLevelProgressBar, statusValueLabel);
                break;

            default:
                break;
        }
    }

    private void saveUserData(TextView bacLevelValueLabel, EditText weightEditText,
                                 Switch genderSwitch) {

        // retrieve the user input values
        int enteredWeight = Integer.parseInt(weightEditText.getText().toString());
        String selectedGender = genderSwitch.getText().toString();

        // check if the weight or gender inputs changed
        boolean inputChanged = false;

        if (this.getUserWeight() != 0 && enteredWeight != this.getUserWeight())
            inputChanged = true;

        if (this.getUserGender() != null && selectedGender != this.getUserGender())
            inputChanged = true;

        // if the input(s) changed, recalculate the bac level
        if (inputChanged)
            this.recalculateBacLevel(bacLevelValueLabel, enteredWeight, selectedGender);

        // set the fields to the user input values
        this.setUserWeight(enteredWeight);
        this.setUserGender(selectedGender);

        // display a success message
        this.displayMessage("Your information has been saved.");
    }

    private void addDrink(RadioButton drinkSize, SeekBar alcoholPercentage,
                                   TextView bacLevelValueLabel) {

        // retrieve all the variables for the bac level formula
        int o = Integer.parseInt(drinkSize.getText().toString().replaceAll("[^0-9]", ""));
        double p = (double) alcoholPercentage.getProgress() / 100;
        int w = this.getUserWeight();
        double g = calculateGenderBias(this.getUserGender());

        // calculate the bac level
        double bacLevel = calculateBacLevel(o, p, w, g);

        // add to the total bac level
        double newBac = this.getTotalBacLevel() + bacLevel;
        Log.d("debug", String.valueOf(newBac));

        // update the bac output section
        this.updateBacLevelLabel(bacLevelValueLabel, newBac);
    }

    private double calculateGenderBias(String gender) {
        if (getString(R.string.label_gender_male).equals(gender)) {
            return 0.68;
        } else {
            return 0.55;
        }
    }

    private double calculateBacLevel(int oz, double percentage, int weight, double genderBias) {
        return calculateBacLevel((percentage * oz), weight, genderBias);
    }

    private double calculateBacLevel(double alcoholContent, int weight, double genderBias) {
        return ((alcoholContent) * 6.24) / (weight * genderBias);
    }

    private void recalculateBacLevel(TextView bacLevelValueLabel, int newWeight, String newGender) {
        // retrieve all the variables for the bac level formula
        double currentBac = Double.parseDouble(bacLevelValueLabel.getText().toString());
        int currentWeight = this.getUserWeight();
        double currentGenderBias = calculateGenderBias(this.getUserGender());
        double newGenderBias = calculateGenderBias(newGender);

        // solve the bac formula for total alcoholContent given the current values
        double alcoholContent = (currentBac * currentWeight * currentGenderBias) / 6.24;

        // calculate the bac level
        double newBac = calculateBacLevel(alcoholContent, newWeight, newGenderBias);

        // update the bac output section
        this.updateBacLevelLabel(bacLevelValueLabel, newBac);
    }

    private void updateBacLevelLabel(TextView bacLevelValueLabel, double bacLevel) {
        // if over the limit, disable buttons and display message
        if (bacLevel >= 0.25) {
            // disable buttons
            findViewById(R.id.saveUserDataButton).setEnabled(false);
            findViewById(R.id.addDrinkButton).setEnabled(false);

            // display message to user
            this.displayMessage("No more drinks for you.");
        }

        // set the total bac level
        this.setTotalBacLevel(bacLevel);

        // update the UI textview for bac level
        bacLevelValueLabel.setText(String.valueOf(bacLevel).substring(0, 4));

        // update the UI progress bar
        this.updateProgressBar(bacLevel);

        // update the UI textview for status
        this.updateStatusLabel(bacLevel);
    }

    private void updateProgressBar(double bacLevel) {
        ProgressBar p = (ProgressBar) findViewById(R.id.bacLevelProgressBar);
        p.setProgress((int) (bacLevel * 100));
    }

    private void updateStatusLabel(double bacLevel) {
        int status = 0;
        int statusColor = 0;

        // determine the proper status text and color
        if (bacLevel <= 0.08) {
            status = R.string.label_status_value_safe;
            statusColor = R.color.colorSafeStatus;
        } else if (bacLevel < 0.20) {
            status = R.string.label_status_value_cautious;
            statusColor = R.color.colorCautiousStatus;
        } else {
            status = R.string.label_status_value_unsafe;
            statusColor = R.color.colorUnsafeStatus;
        }

        // set the determined status text and color
        TextView statusValueLabel = (TextView) findViewById(R.id.statusValueLabel);
        statusValueLabel.setText(status);
        statusValueLabel.setBackgroundResource(statusColor);
    }

    private void reset(EditText weightEditText, Switch genderSwitch, RadioGroup drinkSizeRadioGroup,
                       SeekBar alcoholPercentageSeekBar, TextView alcoholPercentageValueLabel,
                       TextView bacLevelValueLabel, ProgressBar bacLevelProgressBar, TextView statusValueLabel) {

        // reset user section
        this.setUserWeight(0);
        this.setUserGender(null);
        weightEditText.setText(null);
        genderSwitch.setChecked(false);
        genderSwitch.setText(R.string.label_gender_male);
        findViewById(R.id.saveUserDataButton).setEnabled(true);

        // reset drink section
        drinkSizeRadioGroup.check(R.id.drinkSizeRadio01);
        alcoholPercentageSeekBar.setProgress(5);
        alcoholPercentageValueLabel.setText(alcoholPercentageSeekBar.getProgress() + "%");
        findViewById(R.id.addDrinkButton).setEnabled(true);

        // reset bac output section
        this.setTotalBacLevel(0);
        bacLevelValueLabel.setText(R.string.label_bac_level_value);
        bacLevelProgressBar.setProgress(0);
        statusValueLabel.setText(R.string.label_status_value_safe);
        statusValueLabel.setBackgroundResource(R.color.colorSafeStatus);
    }

    private void displayMessage(String text) {
        // display a toast message to the user
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    public int getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(int userWeight) {
        this.userWeight = userWeight;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public double getTotalBacLevel() {
        return totalBacLevel;
    }

    public void setTotalBacLevel(double totalBacLevel) {
        this.totalBacLevel = totalBacLevel;
    }
}
