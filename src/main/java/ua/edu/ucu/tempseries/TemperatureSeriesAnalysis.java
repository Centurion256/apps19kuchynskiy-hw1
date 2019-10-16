package ua.edu.ucu.tempseries;

@FunctionalInterface
public interface FilterFunction
{
    public abstact bool isEligible(int val, int comparor);
}

public class TemperatureSeriesAnalysis {
    
    final int ABSOLUTE_MINIMUM = -273;
    
    private int capacity;
    private double[] tempValues;
    private int amount;
    
    @Retention(RetentionPolict.RUNTIME)
    @Target(ElementType.METHOD)
    private @interface bool ifNotEmpty() throws IllegalArgumentException
    {
        if (this.amount == 0)
        {
            throw new IllegalArgumentException();
        }
    }

    private validate(double[] temperatureSeries)
    {
        for(double value : temperatureSeries)
        {
            if (value < ABSOLUTE_MINIMUM)
            {
                return 1;
            }
        }
        return 0;
    }

    public TemperatureSeriesAnalysis() 
    {
        this.capacity = 1;
        this.tempValues = new double[capacity];
        this.amount = 0;
    }


    public TemperatureSeriesAnalysis(double[] temperatureSeries) throws InputMismatchException
    {
        if (validate(temperatureSeries) == 1)
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
    @ifNotEmpty
    private double getSum() throws IllegalArgumentException
    {
        double sum = 0;
        for (double temp : tempValues)
        {
            sum += temp;
        }
        return sum;
    }
    @ifNotEmpty
    public double average() 
    {
        double sum = getSum();
        return sum / amount;
    }

    public double deviation() 
    {
        return 0;
    }

    @ifNotEmpty
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

    @ifNotEmpty
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

    public double findTempClosestToZero()
    {
        return findTempClosestToValue(0);
    }
    
    @ifNotEmpty
    public double findTempClosestToValue(double tempValue) throws IllegalArgumentException
    {    
        int minTemp = Integer.MAX_VALUE;
        for (double temp: tempValues)
        {
            if (if abs(abs(temp) - tempValue) < minTemp)
            {
                minTemp = temp;
            }
            else if (abs(abs(temp) - tempValue) == minTemp)
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
            if (condition.isEligible(value, conditinalValue))
            {
                filteredTemps[index] = value;
                index++;
            }
        }
        return filteredTemps;
    }


    public double[] findTempsLessThen(double tempValue) 
    {
        return filter(this.tempValues, n, m -> n < m, tempValue);
    }

    public double[] findTempsGreaterThen(double tempValue) 
    {
        return filter(this.tempValues, n, m -> n >= m, tempValue);
    }

    public TempSummaryStatistics summaryStatistics() 
    {
        return new TempSummaryStatistics(min(), max(), average(), deviation());
    }

    private int reserve()
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
