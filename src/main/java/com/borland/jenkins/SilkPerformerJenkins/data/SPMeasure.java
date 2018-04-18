package com.borland.jenkins.SilkPerformerJenkins.data;

public class SPMeasure
{
  private String name;
  private String displayType;
  private Double min;
  private Double max;
  private Double avg;
  private int count;
  private int countMeasured;
  private Double sum;
  private String unit;
  private int measureClass;
  private int measureType;

  public SPMeasure(String name, int measureClass, int measureType)
  {
    this.name = name;
    this.measureClass = measureClass;
    this.measureType = measureType;
  }

  public int getCountMeasured()
  {
    return countMeasured;
  }

  public void setCountMeasured(int countMeasured)
  {
    this.countMeasured = countMeasured;
  }

  public Double getSum()
  {
    return sum;
  }

  public void setSum(Double sum)
  {
    this.sum = sum;
  }

  public void setDisplayType(String displayType)
  {
    this.displayType = displayType;
  }

  public void setMin(Double min)
  {
    this.min = min;
  }

  public void setMax(Double max)
  {
    this.max = max;
  }

  public void setAvg(Double avg)
  {
    this.avg = avg;
  }

  public void setCount(int count)
  {
    this.count = count;
  }

  public void setUnit(String unit)
  {
    this.unit = unit;
  }

  public String getName()
  {
    return name;
  }

  public String getDisplayType()
  {
    return displayType;
  }

  public Double getMin()
  {
    return min;
  }

  public Double getMax()
  {
    return max;
  }

  public Double getAvg()
  {
    return avg;
  }

  public String getUnit()
  {
    return unit;
  }

  public int getMeasureClass()
  {
    return measureClass;
  }

  public int getMeasureType()
  {
    return measureType;
  }

  public int getCount()
  {
    return count;
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder("Measure Name: ");
    sb.append(name).append("\n\t\t\t Display Name: ").append(displayType).append("\n\t\t\t Measure Class: ").append(measureClass).append("\n\t\t\t Measure Type: ")
        .append(measureType).append("\n\t\t\t Count Measured: ").append(countMeasured).append("\n\t\t\t Sum of Measures: ").append(sum).append("\n\t\t\t Minimum Value: ")
        .append(min).append("\n\t\t\t Count Value: ").append(count).append("\n\t\t\t Maximum Value: ").append(max).append("\n\t\t\t Average Value: ").append(avg)
        .append("\n\t\t\t Unit of the values: ").append(unit);
    return sb.toString();
  }
}
