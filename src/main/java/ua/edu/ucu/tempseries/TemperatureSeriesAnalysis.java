package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

@FunctionalInterface
interface FilterFunction
{
    public abstract boolean isEligible(double val);
}

public class TemperatureSeriesAnalysis {
    
    final int ABSOLUTE_MINIMUM = -273;
    
    private int capacity;
    private double[] tempValues;
    private int amount;
    

    private void ifNotEmpty() throws IllegalArgumentException
    {
        if (this.amount == 0)
        {
            throw new IllegalArgumentException();
        }
    }

    private boolean isValid(double[] temperatureSeries)
    {
        for(double value : temperatureSeries)
        {
            if (value < ABSOLUTE_MINIMUM)
            {
                return false;
            }
        }
        return true;
    }

    public TemperatureSeriesAnalysis() 
    {
        this.capacity = 1;
        this.tempValues = new double[capacity];
        this.amount = 0;
    }


    public TemperatureSeriesAnalysis(double[] temperatureSeries) throws InputMismatchException
    {
        if (!isValid(temperatureSeries))
        {
            throw new InputMismatchException();
        }
        this.capacity = temperatureSeries.length;
        this.tempValues = new double[this.capacity];
        this.amount = 0;
        for (double temp : temperatureSeries)
        {
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
        ifNotEmpty();
        double sum = getSum();
        return sum / amount;
    }
    private double expectedValue()
    {
        ifNotEmpty();
        return getSum() / (double)this.amount;
    }
    private double variance()
    {
        double exp = expectedValue();
        double sum = 0;
        for (double temp : this.tempValues)
        {
            sum += Math.pow(temp - exp, 2);
        }
        return sum / (double)this.amount;

    }
    public double deviation() 
    {
        return Math.sqrt(variance());
    }


    public double min() 
    {
        ifNotEmpty();
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
        ifNotEmpty();
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

    public double findTempClosestToZero()
    {
        return findTempClosestToValue(0);
    }
    

    public double findTempClosestToValue(double tempValue) throws IllegalArgumentException
    {    
        ifNotEmpty();
        double minTemp = Integer.MAX_VALUE;
        double minDiff = Integer.MAX_VALUE;
        double currentDiff;
        for (double temp: tempValues)
        {
            currentDiff = Math.abs(Math.abs(temp) - tempValue);
            if (currentDiff < minDiff)
            {
                minTemp = temp;
                minDiff = currentDiff;
            }
            else if (currentDiff == minDiff)
            {
                minTemp = (temp > 0) ? temp : minTemp;
            }
        }
        return minTemp;
    }
    private double[] filter(double[] values, FilterFunction condition, double conditinalValue)
    {
        double[] filteredTemps = new double[this.capacity];
        int index = 0;
        for (double value : values)
        {
            if (condition.isEligible(value))
            {
                filteredTemps[index] = value;
                index++;
            }
        }
        return filteredTemps;
    }


    public double[] findTempsLessThen(final double tempValue) 
    {
        return filter(this.tempValues, n -> n < tempValue, tempValue);
    }

    public double[] findTempsGreaterThen(final double tempValue) 
    {
        return filter(this.tempValues, n -> n >= tempValue, tempValue);
    }

    public TempSummaryStatistics summaryStatistics() 
    {
        return new TempSummaryStatistics(min(), max(), average(), deviation());
    }

    private void reserve()
    {
        this.capacity = this.capacity*2;
        double[] newTempValues = new double[this.capacity];
        System.arraycopy(this.tempValues, 0, newTempValues, 0, this.amount);
        this.tempValues = newTempValues;
    }

    private int addTemp(double temp)
    {
        if (this.amount >= this.capacity)
        {
            reserve();
        }
        amount++;
        this.tempValues[amount] = temp;
        return 0;
    }

    public int addTemps(double... temps) throws InputMismatchException 
    {
        for (double temp : temps)
        {
            if (temp < ABSOLUTE_MINIMUM)
            {
                throw new InputMismatchException();
            }
        }
        for (double temp : temps)
        {
            addTemp(temp);
        }
        return amount;
    }
}
