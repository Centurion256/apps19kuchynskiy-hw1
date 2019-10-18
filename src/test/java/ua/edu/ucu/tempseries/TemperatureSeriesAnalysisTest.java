package ua.edu.ucu.tempseries;

import static org.junit.Assert.assertArrayEquals;
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

    @Test
    public void testValueFilterGreater () {

        double[] temperatureSeries = {20.0, -10.0, -2.0, 3.0, 78.0, 2.0001};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double pivot = 3.0;
        double[] filtered = {20.0, 3.0, 78.0};

        
        assertArrayEquals(filtered,  seriesAnalysis.findTempsGreaterThen(pivot), 0.0001);
    }
    @Test
    public void testValueFilterGreaterEmpty () {

        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double pivot = 3.0;
        double[] filtered = {};

        
        assertArrayEquals(filtered,  seriesAnalysis.findTempsGreaterThen(pivot), 0.0001);
    }
    @Test
    public void testValueFilterGreaterAllFiltered () {

        double[] temperatureSeries = {20.0, -10.0, -2.0, 3.0, 78.0, 2.0001};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double pivot = 100.0;
        double[] filtered = {};

        
        assertArrayEquals(filtered,  seriesAnalysis.findTempsGreaterThen(pivot), 0.0001);
    }
    @Test
    public void testValueFilterLesser () {

        double[] temperatureSeries = {20.0, -10.0, -2.0, 3.0, 78.0, 2.0001};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double pivot = 3.0;
        double[] filtered = {-10.0, -2.0, 2.0001};

        
        assertArrayEquals(filtered,  seriesAnalysis.findTempsLessThen(pivot), 0.0001);
    }
    @Test
    public void testValueFilterLesserEmpty () {

        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double pivot = 3.0;
        double[] filtered = {};

        
        assertArrayEquals(filtered,  seriesAnalysis.findTempsLessThen(pivot), 0.0001);
    }
    @Test
    public void testValueFilterLesserAllFiltered () {

        double[] temperatureSeries = {20.0, -10.0, -2.0, 3.0, 78.0, 2.0001};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double pivot = -100.0;
        double[] filtered = {};

        
        assertArrayEquals(filtered,  seriesAnalysis.findTempsLessThen(pivot), 0.0001);
    }

    @Test
    public void testAddTemps () {

        double[] temperatureSeries = {20.0, -10.0, -2.0, 3.0, 78.0, 2.0001};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] extended = {20.0, -10.0, -2.0, 3.0, 78.0, 2.0001, 99.9, 2.22};

        
        seriesAnalysis.addTemps(99.9, 2.22);
        assertArrayEquals(extended,  seriesAnalysis.getTempValues(), 0.0001);
    }

    @Test
    public void testAddTempsToEmpty () {

        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] extended = {99.9, 2.22};

        
        seriesAnalysis.addTemps(99.9, 2.22);
        assertArrayEquals(extended,  seriesAnalysis.getTempValues(), 0.0001);
    }
    @Test
    public void testGetTempValues () {

        double[] temperatureSeries = {20.0, -10.0, -2.0, 3.0, 78.0, 2.0001};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        assertArrayEquals(temperatureSeries, seriesAnalysis.getTempValues(), 0.0001);
    }

    @Test
    public void testGetTempValue () {

        double[] temperatureSeries = {20.0, -10.0, -2.0, 3.0, 78.0, 2.0001};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double value = 3.0;

        assertEquals(value, seriesAnalysis.getTempValue(3), 0.0001);
    }
    @Test
    public void testDeviation() {

        double[] temperatureSeries = {2.0, 3.0, -5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double deviation = 4.35;

        assertEquals(deviation, seriesAnalysis.deviation(), 0.01);
    }
}
