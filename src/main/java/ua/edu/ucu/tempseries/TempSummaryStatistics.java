package ua.edu.ucu.tempseries;

public class TempSummaryStatistics 
{
    private double minimal, maximal, average, standard_deviation;

    TempSummaryStatistics(double minimal, double maximal,
                          double average, double standard_deviation)
    {
        this.minimal = minimal;
        this.maximal = maximal;
        this.average = average;
        this.standard_deviation = standard_deviation;  
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
    
    public double getStandard_deviation() 
    {

        return standard_deviation;
    }
    @Override
    public int hashCode() 
    {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(average);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(maximal);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(minimal);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(standard_deviation);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
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
        if (Double.doubleToLongBits(standard_deviation) != Double.doubleToLongBits(other.standard_deviation))
        {
            return false;
        }
        return true;
    }


}