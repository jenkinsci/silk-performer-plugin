package com.borland.jenkins.SilkPerformerJenkins.data;

import java.io.Serializable;

public class SPMeasure implements Serializable
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String name;
  private String displayClass;
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
  private Double percentile50;
  private Double percentile90;
  private Double percentile95;
  private Double percentile99;

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

  public void setDisplayClass(String displayClass)
  {
    this.displayClass = displayClass;
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

  public String getDisplayClass()
  {
    return displayClass;
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
  public boolean equals(Object o)
  {
    if (o instanceof SPMeasure)
    {
      SPMeasure m = (SPMeasure) o;
      if (this.measureClass == m.getMeasureClass() && this.measureType == m.getMeasureType())
      {
        return true;
      }
    }
    return false;
  }

  @Override
  public int hashCode()
  {
    return measureClass * 13 + measureType * 31;
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder("Measure Name: ");
    sb.append(name).append("\n\t\t\t Display Name: ").append(displayType).append("\n\t\t\t Measure Class: ").append(measureClass).append("\n\t\t\t Measure Type: ")
        .append(measureType).append("\n\t\t\t Count Measured: ").append(countMeasured).append("\n\t\t\t Sum of Measures: ").append(sum).append("\n\t\t\t Minimum Value: ")
        .append(min).append("\n\t\t\t Count Value: ").append(count).append("\n\t\t\t Maximum Value: ").append(max).append("\n\t\t\t Average Value: ").append(avg)
        .append("\n\t\t\t Percentile 50:").append(percentile50).append("\n\t\t\t Percentile 90:").append(percentile90).append("\n\t\t\t Percentile 95:").append(percentile95)
        .append("\n\t\t\t Percentile 99:").append(percentile99).append("\n\t\t\t Unit of the values: ").append(unit);
    return sb.toString();
  }

  public String toPrint()
  {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("Measure Name: %s | Measure Class: %s(%d) | Measure Type: %s(%d)", name, displayClass, measureClass, displayType, measureType));
    return sb.toString();
  }

  public void setPercentile50(Double percentile50)
  {
    this.percentile50 = percentile50;
  }

  public void setPercentile90(Double percentile90)
  {
    this.percentile90 = percentile90;
  }

  public void setPercentile95(Double percentile95)
  {
    this.percentile95 = percentile95;
  }

  public void setPercentile99(Double percentile99)
  {
    this.percentile99 = percentile99;
  }

  public Double getPercentile50()
  {
    return percentile50;
  }

  public Double getPercentile90()
  {
    return percentile90;
  }

  public Double getPercentile95()
  {
    return percentile95;
  }

  public Double getPercentile99()
  {
    return percentile99;
  }

}
