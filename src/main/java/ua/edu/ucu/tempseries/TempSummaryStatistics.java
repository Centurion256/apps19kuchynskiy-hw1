package ua.edu.ucu.tempseries;

public class TempSummaryStatistics 
{
    private double minimal, maximal, average, standardDeviation;

    TempSummaryStatistics(double minimal, double maximal,
                          double average, double standardDeviation)
    {
        this.minimal = minimal;
        this.maximal = maximal;
        this.average = average;
        this.standardDeviation = standardDeviation;  
    }

    public double getMinimal() 
    {
        return minimal;
    }
    
    public double getMaximal() 
    {
        return maximal;
    }
    
    public double getAverage() 
    {
        return average;
    }
    
    public double getStandardDeviation() 
    {

        return standardDeviation;
    }
    @Override
    public int hashCode() 
    {
        final int PRIME = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(average);
        result = PRIME * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(maximal);
        result = PRIME * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(minimal);
        result = PRIME * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(standardDeviation);
        result = PRIME * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        TempSummaryStatistics other = (TempSummaryStatistics) obj;
        if (Double.doubleToLongBits(average) != Double.doubleToLongBits(other.average))
        {
            return false;
        }
        if (Double.doubleToLongBits(maximal) != Double.doubleToLongBits(other.maximal))
        {
            return false;
        }
        if (Double.doubleToLongBits(minimal) != Double.doubleToLongBits(other.minimal))
        {
            return false;
        }
        if (Double.doubleToLongBits(standardDeviation) != Double.doubleToLongBits(other.standardDeviation))
        {
            return false;
        }
        return true;
    }


}