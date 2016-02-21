package com.ecse428.assignmentb.postalratecalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;
//import android.view.View.OnClickListener


public class PRCActivity extends AppCompatActivity implements View.OnClickListener{




    RadioGroup countriesRadioGroup;
    RadioButton canadaRadioButton;
    RadioButton unitedStatesRadioButton;
    RadioButton internationalRadioButton;

    public int numArgs = 0;

    boolean isCanadaChecked = false;
    boolean isUnitedStatesChecked=false;
    boolean isInternationalChecked =false;

    public boolean isGetRateButtonClicked=false;
    String calculatedRate="";

    // the values in the arrays represents length,width,depth and weight respectively
    double [] standardMinSpecifications = {140,90,0.18,2};
    double [] standardMaxSpecifications = {254,156,5,50};

    double [] nonStandardMinSpecifications = {140,90,0.18,3};
    double [] nonStandardMaxSpecifications = {380,270,20,500};

    //For Canada
    //Standard prices
    HashMap<String, Double> canadaStandardUpTo30 = new HashMap<String, Double>();
    HashMap<String, Double> canadaStandard30To50 = new HashMap<String, Double>();
    //Non Standard prices
    HashMap<String, Double> canadaNonStandardUpTo100 = new HashMap<String, Double>();
    HashMap<String, Double> canadaNonStandard100To200 = new HashMap<String, Double>();
    HashMap<String, Double> canadaNonStandard200To300 = new HashMap<String, Double>();
    HashMap<String, Double> canadaNonStandard300To400 = new HashMap<String, Double>();
    HashMap<String, Double> canadaNonStandard400To500 = new HashMap<String, Double>();

    //For United States
    //Standard Prices
    HashMap<String, Double> usaStandardUpTo30 = new HashMap<String, Double>();
    HashMap<String, Double> usaStandard30To50 = new HashMap<String, Double>();
    //Non Standard Prices
    HashMap<String, Double> usaNonStandardUpTo100 = new HashMap<String, Double>();
    HashMap<String, Double> usaNonStandard100To200 = new HashMap<String, Double>();
    HashMap<String, Double> usaNonStandard200To500 = new HashMap<String, Double>();

    //For international
    //Standard Prices
    HashMap<String, Double> internationalStandardUpTo30 = new HashMap<String, Double>();
    HashMap<String, Double> internationalStandard30To50 = new HashMap<String, Double>();
    //Non Standard Prices
    HashMap<String, Double> internationalNonStandardUpTo100 = new HashMap<String, Double>();
    HashMap<String, Double> internationalNonStandard100To200 = new HashMap<String, Double>();
    HashMap<String, Double> internationalNonStandard200To500 = new HashMap<String, Double>();


    //String variables for holding errors
    String errorLengthMissing="";
    String errorWidthMissing="";
    String errorDepthMissing="";
    String errorWeightMissing="";
    String errorCountryMissing="";


    String errorLengthHighForStandard="";
    String errorLengthHighForNonStandard="";
    String errorWidthHighForStandard="";
    String errorWidthHighForNonStandard="";
    String errorDepthHighForStandard="";
    String errorDepthHighForNonStandard="";
    String errorWeightLowForStandard="";
    String errorWeightLowForNonStandard="";
    String errorWeightHighForStandard="";
    String errorWeightHighForNonStandard="";



    String errorLengthLow="";
    String errorWidthHigh="";
    String errorWidthLow="";
    String errorDepthHigh="";
    String errorDepthLow="";
    String errorWeightHigh="";
    String errorWeightLow="";
    String errorInvalidInputCombinationStandard="";
    String errorInvalidInputCombinationNonStandard="";
    String errorInvalidInputAlpha="";
    String errorInvalidInputNegative="";
    String errorInvalidInputNonAlphaNumeric="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Create Reference with the screen Widget Components
        final EditText lengthText = (EditText) findViewById(R.id.length_text);
        final EditText widthText = (EditText) findViewById(R.id.width_text);
        final EditText depthText = (EditText) findViewById(R.id.depth_text);
        final EditText weightText = (EditText) findViewById(R.id.weight_text);
        final EditText rateText = (EditText) findViewById(R.id.rate_text);
        Button getRateButton  = (Button) findViewById(R.id.getrate_button);


        countriesRadioGroup = (RadioGroup) findViewById(R.id.countries_radioGroup);
        canadaRadioButton = (RadioButton) findViewById(R.id.canada_radiobutton);
        unitedStatesRadioButton = (RadioButton) findViewById(R.id.unitedstates_radiobutton);
        internationalRadioButton = (RadioButton) findViewById(R.id.international_radiobutton);


//        canadaRadioButton.setOnClickListener(this);
//        unitedStatesRadioButton.setOnClickListener(this);
//        internationalRadioButton.setOnClickListener(this);




        //Create Button Selection Listener
        getRateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize prices
                declarePrices();

                int checkedRadioButtonId = countriesRadioGroup.getCheckedRadioButtonId();
                switch (checkedRadioButtonId) {
                    case R.id.canada_radiobutton:
                        if (canadaRadioButton.isChecked()) {
                            isCanadaChecked = true;
                            isUnitedStatesChecked = false;
                            isInternationalChecked = false;
                            rateText.setText("For Canada  ");
                            break;
                        }

                    case R.id.unitedstates_radiobutton:
                        if (unitedStatesRadioButton.isChecked()) {
                            isCanadaChecked = false;
                            isUnitedStatesChecked = true;
                            isInternationalChecked = false;
                            rateText.setText("For USA ");
                            break;
                        }

                    case R.id.international_radiobutton:
                        if (internationalRadioButton.isChecked()) {
                            isCanadaChecked = false;
                            isUnitedStatesChecked = false;
                            isInternationalChecked = true;
                            rateText.setText("For International ");
                            break;
                        }
                }

                boolean isACountrySelected = isCanadaChecked || isUnitedStatesChecked || isInternationalChecked;
                String lenthTextInstring = lengthText.getText().toString();
                String widthTextInstring = widthText.getText().toString();
                String depthTextInstring = depthText.getText().toString();
                String weightTextInstring = weightText.getText().toString();

                boolean validInputs=checkInputsValidity(lenthTextInstring,widthTextInstring,depthTextInstring,weightTextInstring,isACountrySelected);



                isGetRateButtonClicked=true;
                displayPopUp(""+isGetRateButtonClicked);
                if(validInputs==true) {
                    double length = Double.parseDouble(lengthText.getText().toString().trim());
                    double width = Double.parseDouble(widthText.getText().toString().trim());
                    double depth = Double.parseDouble(depthText.getText().toString().trim());
                    double weight = Double.parseDouble(weightText.getText().toString().trim());
                    calculateRate(length, width, depth, weight);
                }

//                double rate = calculateRate(length, width, depth, weight);
//                rateText.setText("The rate is : " + rate);
            }
        });



    }


    boolean getIsGetRateButtonClicked(){
        return  isGetRateButtonClicked;
    }



    boolean isAlpha(String str){
        return str.matches("[a-zA-Z]+");
    }
    boolean isNumeric(String str){
        return str.matches("-?\\d+(\\.\\d+)?");
    }





    boolean checkInputsValidity(String lengthTestInString,String widthTextInString,String depthTextInString,String weightTextInString,boolean isACountrySelected){
        //check that all required inputs are provided
        boolean areInputsValid=true;
//        if(  (lengthText.getText().toString().isEmpty()) || (widthText.getText().toString().isEmpty()) ||
//                (depthText.getText().toString().isEmpty()) || (weightText.getText().toString().isEmpty())  ||
//                ( (isCanadaChecked==false) && (isUnitedStatesChecked==false) && (isInternationalChecked==false))   ){
//            errorFewArguments ="ERROR: Too few arguments \n At least one of the required arguments is not provided " +
//                    "\n   Please fill in all the inputs fields";
//            displayPopUp(errorFewArguments);
//            areInputsValid=false;
//        }






        //Tests for empty inputs
        if(lengthTestInString==""){
            errorLengthMissing = "ERROR: Length is missing";
        }

        if(widthTextInString==""){
            errorWidthMissing = "ERROR: Width is missing";
        }

        if(depthTextInString==""){
            errorDepthMissing = "ERROR: Depth is missing";
        }

        if(weightTextInString==""){
            errorWeightMissing = "ERROR: Weight is missing";
        }

        if(isACountrySelected==false){
            errorCountryMissing = "ERROR: Country is missing";
        }


        if(isAlpha(lengthTestInString) || isAlpha(widthTextInString) || isAlpha(depthTextInString) || isAlpha(weightTextInString)){
            errorInvalidInputAlpha = "ERROR: Input cannot be a letter of the alphabet";
        }

        double length =Double.parseDouble(lengthTestInString);
        double width =Double.parseDouble(widthTextInString);
        double depth =Double.parseDouble(depthTextInString);
        double weight =Double.parseDouble(weightTextInString);


        if((length > 380 || length < 140) || (width > 270 || width < 90) ||
                (depth < 0.18 || depth > 20) || (weight < 3 || weight > 500)){
            errorInvalidInputCombinationNonStandard = "ERROR: At least one input is out of range";
        }

        if((length > 245 || length < 140) || (width > 156 || width < 90) ||
                (depth < 0.18 || depth > 5) || (weight < 2 || weight > 50)){
            errorInvalidInputCombinationStandard = "ERROR: At least one input is out of range";
        }



        if(length < 0 || width < 0 || depth < 0 || weight < 0){
            errorInvalidInputNegative = "ERROR: Input cannot be negative";
        }

        if((!isAlpha(lengthTestInString) && !isNumeric(lengthTestInString)) ||
                (!isAlpha(widthTextInString) && !isNumeric(widthTextInString)) ||
                (!isAlpha(depthTextInString) && !isNumeric(depthTextInString)) ||
                (!isAlpha(weightTextInString) && !isNumeric(weightTextInString))){
            errorInvalidInputNonAlphaNumeric = "ERROR: Input is a symbol";
        }



        //Test for Ranges
        //Test for range low
        if(length < 140){
            errorLengthLow = "ERROR: Length out of range low";
        }
        //Test for range high
        if(length>245 && length<380){
            errorLengthHighForStandard = "ERROR: Length out of range high for standard lettermail";
        }
        else if (length>380){
            errorLengthHighForStandard = "ERROR: Length out of range high for standard lettermail";
            errorLengthHighForNonStandard = "ERROR: Length out of range high for non standard lettermail";
        }


        //Test for width low
        if(width < 90){
            errorWidthLow = "ERROR: Width out of range low";
        }
        //Test for width high
        if(width>156 && width<270){
            errorWidthHighForStandard = "ERROR: Width out of range high for standard lettermail";
        }
        else if (width>270){
            errorWidthHighForStandard = "ERROR: Width out of range high for standard lettermail";
            errorWidthHighForNonStandard = "ERROR: Width out of range high for non standard lettermail";
        }



        //Test for depth low
        if(depth < 0.18){
            errorDepthLow = "ERROR: Depth out of range low";
        }
        //Test for depth high
        if(depth>5 && depth<20){
            errorDepthHighForStandard = "ERROR: Depth out of range high for standard lettermail";
        }
        else if (depth>20){
            errorDepthHighForStandard = "ERROR: Depth out of range high for standard lettermail";
            errorDepthHighForNonStandard = "ERROR: Depth out of range high for non standard lettermail";
        }


        //Test for weight low
        if(weight < 2){
            errorWeightLowForStandard = "ERROR: Weight out of range low for standard lettermail";
            errorWeightLowForNonStandard = "ERROR: Weight out of range low for non standard lettermail";
        }

        if(weight>2 && weight < 3){
            errorWeightLowForNonStandard = "ERROR: Weight out of range low for non standard lettermail";
        }

        //Test for depth high
        if(weight>50 && weight<500){
            errorWeightLowForStandard = "ERROR: Weight out of range low for standard lettermail";
        }
        else if (weight>500){
            errorWeightLowForStandard = "ERROR: Weight out of range low for standard lettermail";
            errorWeightLowForNonStandard = "ERROR: Weight out of range low for non standard lettermail";
        }



        return true;

    }
    //String[] calculatedRate = new String[3];
    String calculateRate(double length,double width,double depth,double weight){

        boolean standardLength = length>=standardMinSpecifications[0] && length<=standardMaxSpecifications[0];
        boolean standardWidth = width>=standardMinSpecifications[1] && width<=standardMaxSpecifications[1];
        boolean standardDepth = depth>=standardMinSpecifications[2] && depth<=standardMaxSpecifications[2];
        boolean standardWeight = weight>=standardMinSpecifications[3] && weight<=standardMaxSpecifications[3];

        //check if lettermail is standard
        if(standardLength && standardWidth && standardDepth && standardWeight){
            //check which country is selected then calculate lettermail rate
            if(isCanadaChecked){
                if(weight<=30){
                    calculatedRate= "If \"Stamps in booklets/coils/panes\" : " + canadaStandardUpTo30.get("Stamps in booklets/coils/panes") + "\n" +
                            "If \"Meter or Postal Indicia\" : " + canadaStandardUpTo30.get("Meter or Postal Indicia") + "\n" +
                            "If \"Single Stamp(s)\" : " + canadaStandardUpTo30.get("Single Stamp(s)");
                    //displayPopUp(calculatedRate);
                }
                else if(weight>30 && weight <=50){
                    calculatedRate= "If \"Stamps in booklets/coils/panes\" : " + canadaStandard30To50.get("Stamps in booklets/coils/panes") + "\n" +
                            "If \"Meter or Postal Indicia\" : " + canadaStandard30To50.get("Meter or Postal Indicia") + "\n" +
                            "If \"Single Stamp(s)\" : " + canadaStandard30To50.get("Single Stamp(s)");
                    //displayPopUp(calculatedRate);
                }
            }

            else if(isUnitedStatesChecked){
                if(weight<=30){
                    calculatedRate= "If \"Stamp(s)\" : " + usaStandardUpTo30.get("Stamp(s)") + "\n" +
                            "If \"Meter or Postal Indicia\" : " + usaStandardUpTo30.get("Meter or Postal Indicia");
                    //displayPopUp(calculatedRate);
                }
                else if(weight>30 && weight <=50){
                    calculatedRate= "If \"Stamp(s)\" : " + usaStandard30To50.get("Stamp(s)") + "\n" +
                            "If \"Meter or Postal Indicia\" : " + usaStandard30To50.get("Meter or Postal Indicia");
                    //displayPopUp(calculatedRate);
                }


            }
            else if(isInternationalChecked){
                if(weight<=30){
                    calculatedRate= "If \"Stamp(s)\" : " + internationalStandardUpTo30.get("Stamp(s)") + "\n" +
                            "If \"Meter or Postal Indicia\" : " + internationalStandardUpTo30.get("Meter or Postal Indicia");
                    //displayPopUp(calculatedRate);
                }

                else if(weight>30 && weight <=50){
                    calculatedRate= "If \"Stamp(s)\" : " + internationalStandard30To50.get("Stamp(s)") + "\n" +
                            "If \"Meter or Postal Indicia\" : " + internationalStandard30To50.get("Meter or Postal Indicia");
                    //displayPopUp(calculatedRate);
                }
            }

        }


        boolean nonStandardLength = length>=nonStandardMinSpecifications[0] && length<=nonStandardMaxSpecifications[0];
        boolean nonStandardWidth = width>=nonStandardMinSpecifications[1] && width<=nonStandardMaxSpecifications[1];
        boolean nonStandardDepth = depth>=nonStandardMinSpecifications[2] && depth<=nonStandardMaxSpecifications[2];
        boolean nonStandardWeight = weight>=nonStandardMinSpecifications[3] && weight<=nonStandardMaxSpecifications[3];

        //check if lettermail is non standard
        if(nonStandardLength && nonStandardWidth && nonStandardDepth && nonStandardWeight){
            //check which country is selected then calculate lettermail rate
            if(isCanadaChecked){
                if(weight<=100){
                    calculatedRate= "If \"Stamp(s)\" : " + canadaNonStandardUpTo100.get("Stamp(s)") + "\n" +
                            "If \"Meter or Postal Indicia\" : " + canadaNonStandardUpTo100.get("Meter or Postal Indicia");
                    //displayPopUp(calculatedRate);
                }
                else if(weight>100 && weight <=200){
                    calculatedRate= "If \"Stamp(s)\" : " + canadaNonStandard100To200.get("Stamp(s)") + "\n" +
                            "If \"Meter or Postal Indicia\" : " + canadaNonStandard100To200.get("Meter or Postal Indicia");
                    //displayPopUp(calculatedRate);
                }
                else if(weight>200 && weight <=300){
                    calculatedRate= "If \"Stamp(s)\" : " + canadaNonStandard200To300.get("Stamp(s)") + "\n" +
                            "If \"Meter or Postal Indicia\" : " + canadaNonStandard200To300.get("Meter or Postal Indicia");
                    //displayPopUp(calculatedRate);
                }
                else if(weight>300 && weight <=400){
                    calculatedRate= "If \"Stamp(s)\" : " + canadaNonStandard300To400.get("Stamp(s)") + "\n" +
                            "If \"Meter or Postal Indicia\" : " + canadaNonStandard300To400.get("Meter or Postal Indicia");
                    //displayPopUp(calculatedRate);
                }
                else if(weight>400 && weight <=500){
                    calculatedRate= "If \"Stamp(s)\" : " + canadaNonStandard400To500.get("Stamp(s)") + "\n" +
                            "If \"Meter or Postal Indicia\" : " + canadaNonStandard400To500.get("Meter or Postal Indicia");
                    //displayPopUp(calculatedRate);
                }
            }

            else if (isUnitedStatesChecked){
                if(weight>0 && weight<=100){
                    calculatedRate= "If \"Stamp(s)\" : " + usaNonStandardUpTo100.get("Stamp(s)") + "\n" +
                            "If \"Meter or Postal Indicia\" : " + usaNonStandardUpTo100.get("Meter or Postal Indicia");
                    //displayPopUp(calculatedRate);
                }
                else if(weight>100 && weight<=200){
                    calculatedRate= "If \"Stamp(s)\" : " + usaNonStandard100To200.get("Stamp(s)") + "\n" +
                            "If \"Meter or Postal Indicia\" : " + usaNonStandard100To200.get("Meter or Postal Indicia");
                    //displayPopUp(calculatedRate);
                }
                else if(weight>200 && weight<=500){
                    calculatedRate= "If \"Stamp(s)\" : " + usaNonStandard200To500.get("Stamp(s)") + "\n" +
                            "If \"Meter or Postal Indicia\" : " + usaNonStandard200To500.get("Meter or Postal Indicia");
                    //displayPopUp(calculatedRate);
                }
            }

            else if (isInternationalChecked){
                if(weight>0 && weight<=100){
                    calculatedRate= "If \"Stamp(s)\" : " + internationalNonStandardUpTo100.get("Stamp(s)") + "\n" +
                            "If \"Meter or Postal Indicia\" : " + internationalNonStandardUpTo100.get("Meter or Postal Indicia");
                    //displayPopUp(calculatedRate);
                }
                else if(weight>100 && weight<=200){
                    calculatedRate= "If \"Stamp(s)\" : " + internationalNonStandard100To200.get("Stamp(s)") + "\n" +
                            "If \"Meter or Postal Indicia\" : " + internationalNonStandard100To200.get("Meter or Postal Indicia");
                    //displayPopUp(calculatedRate);
                }
                else if(weight>200 && weight<=500){
                    calculatedRate= "If \"Stamp(s)\" : " + internationalNonStandard200To500.get("Stamp(s)") + "\n" +
                            "If \"Meter or Postal Indicia\" : " + internationalNonStandard200To500.get("Meter or Postal Indicia");
                    //displayPopUp(calculatedRate);
                }
            }

        }
        return calculatedRate;
    }



    void displayPopUp(String message){
        AlertDialog alertDialog = new AlertDialog.Builder(
                PRCActivity.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Alert Dialog");

        // Setting Dialog Message
        alertDialog.setMessage(message);

//        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.tick);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });

        // Showing Alert Message
        alertDialog.show();

    }




    void declarePrices(){
        //For Canada
        //Declare Standard Prices
        canadaStandardUpTo30.put("Stamps in booklets/coils/panes",0.85);
        canadaStandardUpTo30.put("Meter or Postal Indicia",0.80);
        canadaStandardUpTo30.put("Single Stamp(s)",1.00);

        canadaStandard30To50.put("Stamps in booklets/coils/panes",1.20);
        canadaStandard30To50.put("Meter or Postal Indicia",1.19);
        canadaStandard30To50.put("Single Stamp(s)",1.20);

        //Declare Non Standard Prices
        canadaNonStandardUpTo100.put("Stamp(s)",1.80);
        canadaNonStandardUpTo100.put("Meter or Postal Indicia",1.71);

        canadaNonStandard100To200.put("Stamp(s)",2.95);
        canadaNonStandard100To200.put("Meter or Postal Indicia",2.77);

        canadaNonStandard200To300.put("Stamp(s)",4.10);
        canadaNonStandard200To300.put("Meter or Postal Indicia",3.89);

        canadaNonStandard300To400.put("Stamp(s)",4.70);
        canadaNonStandard300To400.put("Meter or Postal Indicia",4.42);

        canadaNonStandard400To500.put("Stamp(s)",5.05);
        canadaNonStandard400To500.put("Meter or Postal Indicia",4.74);



        //For United States
        //Declare Standard Prices
        usaStandardUpTo30.put("Stamp(s)",1.20);
        usaStandardUpTo30.put("Meter or Postal Indicia",1.19);

        usaStandard30To50.put("Stamp(s)",1.80);
        usaStandard30To50.put("Meter or Postal Indicia",1.72);

        //Declare Non Standard Prices
        usaNonStandardUpTo100.put("Stamp(s)",2.95);
        usaNonStandardUpTo100.put("Meter or Postal Indicia",2.68);

        usaNonStandard100To200.put("Stamp(s)",5.15);
        usaNonStandard100To200.put("Meter or Postal Indicia",4.85);

        usaNonStandard200To500.put("Stamp(s)",10.30);
        usaNonStandard200To500.put("Meter or Postal Indicia",9.69);


        //For International
        //Declare Standard Prices
        internationalStandardUpTo30.put("Stamp(s)",2.50);
        internationalStandardUpTo30.put("Meter or Postal Indicia",2.36);

        internationalStandard30To50.put("Stamp(s)",3.60);
        internationalStandard30To50.put("Meter or Postal Indicia",3.42);

        //Declare Non Standard Prices
        internationalNonStandardUpTo100.put("Stamp(s)",5.90);
        internationalNonStandardUpTo100.put("Meter or Postal Indicia",5.56);

        internationalNonStandard100To200.put("Stamp(s)",10.30);
        internationalNonStandard100To200.put("Meter or Postal Indicia",9.69);

        internationalNonStandard200To500.put("Stamp(s)",20.60);
        internationalNonStandard200To500.put("Meter or Postal Indicia",19.39);
    }




    @Override
    public void onClick(View v) {
    }


}
