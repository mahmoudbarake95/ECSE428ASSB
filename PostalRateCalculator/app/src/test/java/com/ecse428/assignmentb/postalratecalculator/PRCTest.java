package com.ecse428.assignmentb.postalratecalculator;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Mahmoud on 16-02-19.
 */
public class PRCTest extends TestCase{


    PRCActivity prcActivity;
    //EditText e = new EditText();

    @Before
    public void setUp() {
        prcActivity = new PRCActivity();
    }

    // test if the length argument is missing
    @Test
    public void testMissingLength(){
            prcActivity.checkInputsValidity("","23","21","22",true);
            assertEquals("ERROR: Length is missing",prcActivity.errorLengthMissing);
    }

    // test if the width argument is missing
    @Test
    public void testMissingWidth(){
        prcActivity.checkInputsValidity("12","","23","456",true);
        assertEquals("ERROR: Width is missing",prcActivity.errorWidthMissing);
    }

    // test if the depth argument is missing
    @Test
    public void testMissingDepth(){
        prcActivity.checkInputsValidity("20","30","","40",true);
        assertEquals("ERROR: Depth is missing",prcActivity.errorDepthMissing);
    }

    // test if the weight argument is missing
    @Test
    public void testMissingWeight(){
        prcActivity.checkInputsValidity("20","21","23","",true);
        assertEquals("ERROR: Weight is missing",prcActivity.errorWeightMissing);
    }

    // test if the country argument is missing
    @Test
    public void testMissingCountry(){
        prcActivity.checkInputsValidity("20","21","22","23", false);
        assertEquals("ERROR: Country is missing",prcActivity.errorCountryMissing);
    }


    public void testLengthLow(){
        prcActivity.checkInputsValidity("130", "21", "22", "3", true);
        assertEquals("ERROR: Length out of range low",prcActivity.errorLengthLow);
    }

    public void testLengthHighForStandard(){
        prcActivity.checkInputsValidity("246", "123", "212", "342", true);
        assertEquals("ERROR: Length out of range high for standard lettermail",prcActivity.errorLengthHighForStandard);
    }

    public void testLengthHighNonStandard(){
        prcActivity.checkInputsValidity("400", "232", "234", "233", true);
        assertEquals("ERROR: Length out of range high for standard lettermail", prcActivity.errorLengthHighForStandard);
    }



    public void testWidthLow(){
        prcActivity.checkInputsValidity("130", "21", "22", "3", true);
        assertEquals("ERROR: Width out of range low",prcActivity.errorWidthLow);
    }

    public void testWidthHighForStandard(){
        prcActivity.checkInputsValidity("246", "173", "212", "342", true);
        assertEquals("ERROR: Width out of range high for standard lettermail",prcActivity.errorWidthHighForStandard);
    }

    public void testWidthHighNonStandard(){
        prcActivity.checkInputsValidity("400", "320", "234", "320", true);
        assertEquals("ERROR: Width out of range high for standard lettermail", prcActivity.errorWidthHighForStandard);
    }




    public void testDepthLow(){
        prcActivity.checkInputsValidity("10", "100", "0.23", "233", true);
        assertEquals("ERROR: Depth out of range low",prcActivity.errorDepthLow);
    }

    public void testDepthHighForStandard(){
        prcActivity.checkInputsValidity("230", "300", "22", "342", true);
        assertEquals("ERROR: Depth out of range high for standard lettermail",prcActivity.errorDepthHighForStandard);
    }

    public void testDepthHighNonStandard(){
        prcActivity.checkInputsValidity("150", "400", "25", "50", true);
        // assertEquals("ERROR: Depth out of range high for standard lettermail", prcActivity.errorDepthHighForStandard);
        assertEquals("ERROR: Depth out of range high for non standard lettermail",prcActivity.errorDepthHighForNonStandard);
    }

    //Test for weight
    public void testWeightLowForStandard(){
        prcActivity.checkInputsValidity("10", "100", "0.1", "1", true);
        assertEquals("ERROR: Weight out of range low for standard lettermail",prcActivity.errorWeightLowForStandard);
        assertEquals("ERROR: Weight out of range low for non standard lettermail",prcActivity.errorWeightLowForNonStandard);
    }


    public void testWeightLowForNonStandard(){
        prcActivity.checkInputsValidity("10", "100", "0.1", "2.5", true);
        assertEquals("ERROR: Weight out of range low for non standard lettermail",prcActivity.errorWeightLowForNonStandard);
    }


    public void testWeightHighForStandard(){
        prcActivity.checkInputsValidity("230", "300", "22", "4440", true);
        assertEquals("ERROR: Weight out of range high for standard lettermail",prcActivity.errorWeightHighForStandard);
    }

    public void testWeightHighForNonStandard(){
        prcActivity.checkInputsValidity("330", "400", "18", "4000", true);
        // assertEquals("ERROR: Depth out of range high for standard lettermail", prcActivity.errorDepthHighForStandard);
        assertEquals("ERROR: Weight out of range high for non standard lettermail",prcActivity.errorWeightHighForNonStandard);
    }


    public void testInvalidInputAlpha(){
        prcActivity.checkInputsValidity("abc", "1", "18", "400", true);
        assertEquals("ERROR: Input cannot be a letter of the alphabet", prcActivity.errorInvalidInputAlpha);
    }


    public void testInvalidInputNegative(){
        prcActivity.checkInputsValidity("-2", "1", "18", "5", true);
        assertEquals("ERROR: Input cannot be negative", prcActivity.errorInvalidInputNegative);
    }

    public void testInvalidInputNonAlphaNumeric(){
        prcActivity.checkInputsValidity("$#", "45", "34", "45", true);
        assertEquals("ERROR: Input is a symbol", prcActivity.errorInvalidInputNonAlphaNumeric);
    }

    public void testInvalidCombinationNonStandard(){
        prcActivity.checkInputsValidity("15000", "95", "0.19", "4", true);
        assertEquals("ERROR: At least one input is out of range", prcActivity.errorInvalidInputCombinationNonStandard);
    }

    public void testInvalidCombinationStandard(){
        prcActivity.checkInputsValidity("170", "95", "0.17", "4", true);
        assertEquals("ERROR: At least one input is out of range", prcActivity.errorInvalidInputCombinationStandard);
    }

 public void testValidityCanadaStandardUpTo30Rate(){
        prcActivity.isCanadaChecked=true;
        prcActivity.isUnitedStatesChecked=false;
        prcActivity.isInternationalChecked=false;

        String actualResult = prcActivity.calculateRate(150,100,3,5);
        String expectedResult ="If \"Stamps in booklets/coils/panes\" : " + prcActivity.canadaStandardUpTo30.get("Stamps in booklets/coils/panes") + "\n" +
                "If \"Meter or Postal Indicia\" : " + prcActivity.canadaStandardUpTo30.get("Meter or Postal Indicia") + "\n" +
                "If \"Single Stamp(s)\" : " + prcActivity.canadaStandardUpTo30.get("Single Stamp(s)");
        assertEquals(expectedResult,actualResult);
    }

    public void testValidityCanadaStandard30To50Rate(){
        prcActivity.isCanadaChecked=true;
        prcActivity.isUnitedStatesChecked=false;
        prcActivity.isInternationalChecked=false;

        String actualResult = prcActivity.calculateRate(150,100,3,40);
        String expectedResult = "If \"Stamps in booklets/coils/panes\" : " + prcActivity.canadaStandard30To50.get("Stamps in booklets/coils/panes") + "\n" +
                "If \"Meter or Postal Indicia\" : " + prcActivity.canadaStandard30To50.get("Meter or Postal Indicia") + "\n" +
                "If \"Single Stamp(s)\" : " + prcActivity.canadaStandard30To50.get("Single Stamp(s)");
        assertEquals(expectedResult,actualResult);
    }


    public void testValidityCanadaNonStandardUpTo100Rate(){
        prcActivity.isCanadaChecked=true;
        prcActivity.isUnitedStatesChecked=false;
        prcActivity.isInternationalChecked=false;

        prcActivity.declarePrices();
        String actualResult = prcActivity.calculateRate(150,100,3,90);
        String expectedResult = "If \"Stamp(s)\" : " + prcActivity.canadaNonStandardUpTo100.get("Stamp(s)") + "\n" +
                "If \"Meter or Postal Indicia\" : " + prcActivity.canadaNonStandardUpTo100.get("Meter or Postal Indicia");

        assertEquals(expectedResult,actualResult);
    }

    public void testValidityCanadaNonStandard100To200Rate(){
        prcActivity.isCanadaChecked=true;
        prcActivity.isUnitedStatesChecked=false;
        prcActivity.isInternationalChecked=false;

        prcActivity.declarePrices();
        String actualResult = prcActivity.calculateRate(150,100,3,150);
        String expectedResult = "If \"Stamp(s)\" : " + prcActivity.canadaNonStandard100To200.get("Stamp(s)") + "\n" +
                "If \"Meter or Postal Indicia\" : " + prcActivity.canadaNonStandard100To200.get("Meter or Postal Indicia");

        assertEquals(expectedResult,actualResult);
    }


    public void testValidityCanadaNonStandard200To300Rate(){
        prcActivity.isCanadaChecked=true;
        prcActivity.isUnitedStatesChecked=false;
        prcActivity.isInternationalChecked=false;

        prcActivity.declarePrices();
        String actualResult = prcActivity.calculateRate(150,100,3,250);
        String expectedResult =  "If \"Stamp(s)\" : " + prcActivity.canadaNonStandard200To300.get("Stamp(s)") + "\n" +
                "If \"Meter or Postal Indicia\" : " + prcActivity.canadaNonStandard200To300.get("Meter or Postal Indicia");

        assertEquals(expectedResult,actualResult);
    }

    public void testValidityCanadaNonStandard300To400Rate(){
        prcActivity.isCanadaChecked=true;
        prcActivity.isUnitedStatesChecked=false;
        prcActivity.isInternationalChecked=false;

        prcActivity.declarePrices();
        String actualResult = prcActivity.calculateRate(150,100,3,350);
        String expectedResult = "If \"Stamp(s)\" : " + prcActivity.canadaNonStandard300To400.get("Stamp(s)") + "\n" +
                "If \"Meter or Postal Indicia\" : " + prcActivity.canadaNonStandard300To400.get("Meter or Postal Indicia");

        assertEquals(expectedResult,actualResult);
    }


    public void testValidityCanadaNonStandard400To500Rate(){
        prcActivity.isCanadaChecked=true;
        prcActivity.isUnitedStatesChecked=false;
        prcActivity.isInternationalChecked=false;

        prcActivity.declarePrices();
        String actualResult = prcActivity.calculateRate(150,100,3,450);
        String expectedResult = "If \"Stamp(s)\" : " + prcActivity.canadaNonStandard400To500.get("Stamp(s)") + "\n" +
                "If \"Meter or Postal Indicia\" : " + prcActivity.canadaNonStandard400To500.get("Meter or Postal Indicia");

        assertEquals(expectedResult,actualResult);
    }


    public void testValidityUSAStandardUpTo30Rate(){
        prcActivity.isCanadaChecked=false;
        prcActivity.isUnitedStatesChecked=true;
        prcActivity.isInternationalChecked=false;

        prcActivity.declarePrices();
        String actualResult = prcActivity.calculateRate(150,100,3,20);
        String expectedResult = "If \"Stamp(s)\" : " + prcActivity.usaStandardUpTo30.get("Stamp(s)") + "\n" +
                "If \"Meter or Postal Indicia\" : " + prcActivity.usaStandardUpTo30.get("Meter or Postal Indicia");

        assertEquals(expectedResult,actualResult);
    }

    public void testValidityUSAStandard30To50Rate(){
        prcActivity.isCanadaChecked=false;
        prcActivity.isUnitedStatesChecked=true;
        prcActivity.isInternationalChecked=false;

        prcActivity.declarePrices();
        String actualResult = prcActivity.calculateRate(150,100,3,40);
        String expectedResult = "If \"Stamp(s)\" : " + prcActivity.usaStandard30To50.get("Stamp(s)") + "\n" +
                "If \"Meter or Postal Indicia\" : " + prcActivity.usaStandard30To50.get("Meter or Postal Indicia");

        assertEquals(expectedResult,actualResult);
    }

    public void testValidityUSANonStandardUpTo100Rate(){
        prcActivity.isCanadaChecked=false;
        prcActivity.isUnitedStatesChecked=true;
        prcActivity.isInternationalChecked=false;

        prcActivity.declarePrices();
        String actualResult = prcActivity.calculateRate(150,100,3,70);
        String expectedResult = "If \"Stamp(s)\" : " + prcActivity.usaNonStandardUpTo100.get("Stamp(s)") + "\n" +
                "If \"Meter or Postal Indicia\" : " + prcActivity.usaNonStandardUpTo100.get("Meter or Postal Indicia");
        assertEquals(expectedResult,actualResult);
    }

    public void testValidityUSANonStandard100To200Rate(){
        prcActivity.isCanadaChecked=false;
        prcActivity.isUnitedStatesChecked=true;
        prcActivity.isInternationalChecked=false;

        prcActivity.declarePrices();
        String actualResult = prcActivity.calculateRate(150,100,3,150);
        String expectedResult = "If \"Stamp(s)\" : " + prcActivity.usaNonStandard100To200.get("Stamp(s)") + "\n" +
                "If \"Meter or Postal Indicia\" : " + prcActivity.usaNonStandard100To200.get("Meter or Postal Indicia");
        assertEquals(expectedResult,actualResult);
    }

    public void testValidityUSANonStandard200To500Rate(){
        prcActivity.isCanadaChecked=false;
        prcActivity.isUnitedStatesChecked=true;
        prcActivity.isInternationalChecked=false;

        prcActivity.declarePrices();
        String actualResult = prcActivity.calculateRate(150,100,3,450);
        String expectedResult = "If \"Stamp(s)\" : " + prcActivity.usaNonStandard200To500.get("Stamp(s)") + "\n" +
                "If \"Meter or Postal Indicia\" : " + prcActivity.usaNonStandard200To500.get("Meter or Postal Indicia");
        assertEquals(expectedResult,actualResult);
    }

    public void testValidityInternationalStandardUpTo30Rate(){
        prcActivity.isCanadaChecked=false;
        prcActivity.isUnitedStatesChecked=false;
        prcActivity.isInternationalChecked=true;

        prcActivity.declarePrices();
        String actualResult = prcActivity.calculateRate(150,100,3,20);
        String expectedResult = "If \"Stamp(s)\" : " + prcActivity.internationalStandardUpTo30.get("Stamp(s)") + "\n" +
                "If \"Meter or Postal Indicia\" : " + prcActivity.internationalStandardUpTo30.get("Meter or Postal Indicia");

        assertEquals(expectedResult,actualResult);
    }

    public void testValidityInternationalStandard30To50Rate(){
        prcActivity.isCanadaChecked=false;
        prcActivity.isUnitedStatesChecked=false;
        prcActivity.isInternationalChecked=true;

        prcActivity.declarePrices();
        String actualResult = prcActivity.calculateRate(150,100,3,45);
        String expectedResult = "If \"Stamp(s)\" : " + prcActivity.internationalStandard30To50.get("Stamp(s)") + "\n" +
                "If \"Meter or Postal Indicia\" : " + prcActivity.internationalStandard30To50.get("Meter or Postal Indicia");

        assertEquals(expectedResult,actualResult);
    }

    public void testValidityInternationalNonStandardUpTo100Rate(){
        prcActivity.isCanadaChecked=false;
        prcActivity.isUnitedStatesChecked=false;
        prcActivity.isInternationalChecked=true;

        prcActivity.declarePrices();
        String actualResult = prcActivity.calculateRate(150,100,3,80);
        String expectedResult = "If \"Stamp(s)\" : " + prcActivity.internationalNonStandardUpTo100.get("Stamp(s)") + "\n" +
                "If \"Meter or Postal Indicia\" : " + prcActivity.internationalNonStandardUpTo100.get("Meter or Postal Indicia");

        assertEquals(expectedResult,actualResult);
    }

    public void testValidityInternationalNonStandard100To200Rate(){
        prcActivity.isCanadaChecked=false;
        prcActivity.isUnitedStatesChecked=false;
        prcActivity.isInternationalChecked=true;

        prcActivity.declarePrices();
        String actualResult = prcActivity.calculateRate(150,100,3,150);
        String expectedResult = "If \"Stamp(s)\" : " + prcActivity.internationalNonStandard100To200.get("Stamp(s)") + "\n" +
                "If \"Meter or Postal Indicia\" : " + prcActivity.internationalNonStandard100To200.get("Meter or Postal Indicia");

        assertEquals(expectedResult,actualResult);
    }

    public void testValidityInternationalNonStandard200To500Rate(){
        prcActivity.isCanadaChecked=false;
        prcActivity.isUnitedStatesChecked=false;
        prcActivity.isInternationalChecked=true;

        prcActivity.declarePrices();
        String actualResult = prcActivity.calculateRate(150,100,3,450);
        String expectedResult = "If \"Stamp(s)\" : " + prcActivity.internationalNonStandard200To500.get("Stamp(s)") + "\n" +
                "If \"Meter or Postal Indicia\" : " + prcActivity.internationalNonStandard200To500.get("Meter or Postal Indicia");

        assertEquals(expectedResult,actualResult);
    }

}
