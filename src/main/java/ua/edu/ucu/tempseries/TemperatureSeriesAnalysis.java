package ua.edu.ucu.tempseries;

public class TemperatureSeriesAnalysis {

    final int ABSOLUTE_MINIMUM = -273;

    private int capacity;
    private double[] tempValues;
    private int amount;

    public TemperatureSeriesAnalysis() 
    {
        this.capacity = 1;
        this.tempValues = new double[capacity];
        this.amount = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) throws InputMismatchException
    {
        //this();
        this.capacity = temperatureSeries.length;
        this.tempValues = new double[this.capacity];
        this.amount = 0;
        for (double temp : temperatureSeries)
        {
            if (temp < ABSOLUTE_MINIMUM)
            {
                throw new InputMismatchException();
            }
            this.tempValues[amount] = temp;
            amount++;
        }

    }

    private double getSum() throws IllegalArgumentException
    {
        double sum = 0;
        for (double temp : tempValues)
        {
            sum += temp;
        }
        return sum;
    }
    
    public double average() 
    {
        if (amount == 0)
        {
            throw new IllegalArgumentException;
        }
        double sum = getSum();
        return sum / amount;
    }

    public double deviation() {
        return 0;
    }

    public double min() 
    {
        double minVal = Integer.MAX_VALUE;
        for (double temp : tempValues)
        {
            if (temp < minVal)
            {
                minVal = temp;
            }
        }
        return minVal;
    }

    public double max() 
    {
        double maxVal = Integer.MIN_VALUE;
        for (double temp : tempValues)
        {
            if (temp > maxVal)
            {
                maxVal = temp;
            }
        }
        return maxVal;
    }

    public double findTempClosestToZero() {
        return 0;
    }

    public double findTempClosestToValue(double tempValue) {
        return 0;
    }

    public double[] findTempsLessThen(double tempValue) {
        return null;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        return null;
    }

    public TempSummaryStatistics summaryStatistics() {
        return null;
    }

    public int addTemps(double... temps) {
        return 0;
    }
}
