package ua.edu.ucu.tempseries;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    
    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }
    
    @Test
    public void testMin() {

        double[] temperatureSeries = {20.0, 10.0, 2.0, 3.0, 78.0, 2.0001};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double result = 2.0;

        assertEquals(result, seriesAnalysis.min(), 0.00001);
    }

    @Test
    public void testMinWithOneElement() {

        double[] temperatureSeries = {0.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double result = 0.0;

        assertEquals(result, seriesAnalysis.min(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinEmpty() {

        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.min();
    }
    @Test
    public void testMax() {

        double[] temperatureSeries = {20.0, 10.0, 2.0, 3.0, 78.0, 2.0001};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double result = 78.0;

        assertEquals(result, seriesAnalysis.max(), 0.00001);
    }

    @Test
    public void testMaxWithOneElement() {

        double[] temperatureSeries = {0.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double result = 0.0;

        assertEquals(result, seriesAnalysis.max(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxEmpty() {

        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.max();
    }
    @Test
    public void testValueClosestToZero () {

        double[] temperatureSeries = {20.0, -10.0, -2.0, 3.0, 78.0, 2.0001};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double result = -2.0;

        assertEquals(result, seriesAnalysis.findTempClosestToZero(), 0.0001);
    }

    @Test
    public void testValueClosestToZeroTwoEqualValuesWithDifferentSigns () {

        double[] temperatureSeries = {20.0, -10.0, -2.0, 3.0, 78.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double result = 2.0;

        assertEquals(result, seriesAnalysis.findTempClosestToZero(), 0.0001);
    }

    @Test
    public void testValueClosestToZeroEqualValues () {

        double[] temperatureSeries = {3.14, 3.14, 3.14, 3.14, 3.14};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double result = 3.14;

        assertEquals(result, seriesAnalysis.findTempClosestToZero(), 0.0001);
    }

    @Test
    public void testValueClosestToZeroEqualValuesDifferentSigns () {

        double[] temperatureSeries = {3.14, -3.14, 3.14, -3.14, -3.14};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double result = 3.14;

        assertEquals(result, seriesAnalysis.findTempClosestToZero(), 0.0001);
    }

    @Test
    public void testValueClosestToZeroEqualValuesDifferentSignsCloseValues () {

        double[] temperatureSeries = {3.14, -3.14, 3.14, -3.14, -3.1399999};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double result = -3.1399999;

        assertEquals(result, seriesAnalysis.findTempClosestToZero(), 0.0001);
    }

    @Test
    public void testValueClosestToOtherValue () {

        double[] temperatureSeries = {20.0, -10.0, -2.0, 3.0, 78.0, 2.0001};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double result = 20.0;

        assertEquals(result, seriesAnalysis.findTempClosestToValue(30), 0.0001);
    }
    @Test
    public void testValueClosestToOtherValueTwoEqualValuesWithDifferentSigns () {

        double[] temperatureSeries = {20.0, 40.0, -2.0, 3.0, 78.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double result = 40.0;

        assertEquals(result, seriesAnalysis.findTempClosestToValue(30.0), 0.0001);
    }

    @Test
    public void testValueClosestToOtherValueAllEqual () {

        double[] temperatureSeries = {3.14, 3.14, 3.14, 3.14, 3.14, 3.14};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double result = 3.14;

        assertEquals(result, seriesAnalysis.findTempClosestToValue(Math.PI), 0.0001);
    }
}
