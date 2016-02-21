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
            prcActivity.checkInputsValidity("ahsv","hsjkd","jsdc","skcsdc",true);
            assertEquals("ERROR: Length is missing",prcActivity.errorLengthMissing);
    }

    // test if the width argument is missing
    @Test
    public void testMissingWidth(){
        prcActivity.checkInputsValidity("asx","dxsx","jsdc","skcsdc",true);
        assertEquals("ERROR: Width is missing",prcActivity.errorWidthMissing);
    }

    // test if the depth argument is missing
    @Test
    public void testMissingDepth(){
        prcActivity.checkInputsValidity("ahsv","hsjkd","csdxsd","skcsdc",true);
        assertEquals("ERROR: Depth is missing",prcActivity.errorDepthMissing);
    }

    // test if the weight argument is missing
    @Test
    public void testMissingWeight(){
        prcActivity.checkInputsValidity("ahsv","hsjkd","jsdc","axsax",true);
        assertEquals("ERROR: Weight is missing",prcActivity.errorWeightMissing);
    }

    // test if the country argument is missing
    @Test
    public void testMissingCountry(){
        prcActivity.checkInputsValidity("ahsv","hsjkd","jsdc","axsax",true);
        assertEquals("ERROR: Country is missing",prcActivity.errorCountryMissing);
    }


    public void testLengthLow(){
        prcActivity.checkInputsValidity("170", "hsjkd", "jsdc", "axsax", true);
        assertEquals("ERROR: Length out of range low",prcActivity.errorLengthLow);
    }

    public void testLengthHighForStandard(){
        prcActivity.checkInputsValidity("230", "123", "212", "342", true);
        assertEquals("ERROR: Length out of range high for standard lettermail",prcActivity.errorLengthHighForStandard);
    }

    public void testLengthHighNonStandard(){
        prcActivity.checkInputsValidity("330", "232", "234", "233", true);
        assertEquals("ERROR: Width out of range high for standard lettermail", prcActivity.errorLengthHighForStandard);
        assertEquals("ERROR: Length out of range high for non standard lettermail",prcActivity.errorLengthHighForNonStandard);
    }



    public void testDepthLow(){
        prcActivity.checkInputsValidity("10", "100", "0.1", "233", true);
        assertEquals("ERROR: Depth out of range low",prcActivity.errorDepthLow);
    }

    public void testDepthHighForStandard(){
        prcActivity.checkInputsValidity("230", "300", "22", "342", true);
        assertEquals("ERROR: Depth out of range high for standard lettermail",prcActivity.errorDepthHighForStandard);
    }

    public void testDepthHighNonStandard(){
        prcActivity.checkInputsValidity("330", "400", "18", "233", true);
        assertEquals("ERROR: Depth out of range high for standard lettermail", prcActivity.errorDepthHighForStandard);
        assertEquals("ERROR: Depth out of range high for non standard lettermail",prcActivity.errorDepthHighForNonStandard);
    }



    //Test for weight
    public void testWeightLowForStandard(){
        prcActivity.checkInputsValidity("10", "100", "0.1", "10", true);
        assertEquals("ERROR: Weight out of range low for standard lettermail",prcActivity.errorWeightLowForStandard);
        assertEquals("ERROR: Weight out of range low for non standard lettermail",prcActivity.errorWeightLowForNonStandard);
    }


    public void testWeightLowForNonStandard(){
        prcActivity.checkInputsValidity("10", "100", "0.1", "1", true);
        assertEquals("ERROR: Weight out of range low for non standard lettermail",prcActivity.errorWeightLowForNonStandard);
    }


    public void testWeightHighForStandard(){
        prcActivity.checkInputsValidity("230", "300", "22", "40", true);
        assertEquals("ERROR: Weight out of range low for standard lettermail",prcActivity.errorWeightLowForStandard);
    }

    public void testWeightHighForNonStandard(){
        prcActivity.checkInputsValidity("330", "400", "18", "400", true);
        assertEquals("ERROR: Depth out of range high for standard lettermail", prcActivity.errorDepthHighForStandard);
        assertEquals("ERROR: Depth out of range high for non standard lettermail",prcActivity.errorDepthHighForNonStandard);
    }


    public void testInvalidInputAlpha(){
        prcActivity.checkInputsValidity("2", "1", "18", "400", true);
        assertEquals("ERROR: Input cannot be a letter of the alphabet", prcActivity.errorInvalidInputAlpha);
    }


    public void testInvalidInputNegative(){
        prcActivity.checkInputsValidity("2", "1", "18", "5", true);
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
        prcActivity.checkInputsValidity("170", "95", "0.19", "4", true);
        assertEquals("ERROR: At least one input is out of range", prcActivity.errorInvalidInputCombinationStandard);
    }

    public void testValidityCanadaStandardUpTo30Rate(){
        prcActivity.isCanadaChecked=true;
        prcActivity.isUnitedStatesChecked=false;
        prcActivity.isInternationalChecked=false;

        String actualResult = prcActivity.calculateRate(150,100,3,20);
        String expectedResult ="If \"Stamps in booklets/coils/panes\" : " + prcActivity.canadaStandardUpTo30.get("Stamps in booklets/coils/panes") + "\n" +
                "If \"Meter or Postal Indicia\" : " + prcActivity.canadaStandardUpTo30.get("Meter or Postal Indicia") + "\n" +
                "If \"Single Stamp(s)\" : " + prcActivity.canadaStandardUpTo30.get("Single Stamp(s)");
        assertEquals(expectedResult,actualResult);

    }

}
