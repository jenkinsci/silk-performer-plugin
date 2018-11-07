package com.borland.jenkins.SilkPerformerJenkins;

import java.io.Serializable;
import java.util.List;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import com.borland.jenkins.SilkPerformerJenkins.data.SPMeasure;
import com.borland.jenkins.SilkPerformerJenkins.util.SilkPerformerListBuilder;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import hudson.util.FormValidation;
import hudson.util.ListBoxModel;

public class SuccessCriteria extends AbstractDescribableImpl<SuccessCriteria> implements Serializable
{
  private static final long serialVersionUID = -2120283467496530254L;

  private final String userType;
  private final String measureCategory;
  private final int measureCategoryInt;
  private final String measureType;
  private final int measureTypeInt;
  private final String measureName;
  private final String valueType;
  private final String operatorType;
  private final String chosenValue;

  @DataBoundConstructor
  public SuccessCriteria(String userType, String measureCategory, String measureType, String measureName, String valueType, String operatorType, String chosenValue)
  {
    this.userType = userType;
    this.measureCategory = measureCategory;
    this.measureCategoryInt = SilkPerformerListBuilder.getMeasureCategoryId(measureCategory);
    this.measureType = measureType;
    this.measureTypeInt = SilkPerformerListBuilder.getMeasureTypeId(measureType);
    this.measureName = measureName;
    this.valueType = valueType;
    this.operatorType = operatorType;
    this.chosenValue = chosenValue;
  }

  public SuccessCriteria(String userType, int measureCategoryInt, int measureTypeInt, String measureName, String valueType, String operatorType, String chosenValue)
  {
    this.userType = userType;
    this.measureCategory = SilkPerformerListBuilder.getMeasureCategoryName(measureCategoryInt);
    this.measureCategoryInt = measureCategoryInt;
    this.measureType = SilkPerformerListBuilder.getMeasureTypeName(measureTypeInt);
    this.measureTypeInt = measureTypeInt;
    this.measureName = measureName;
    this.valueType = valueType;
    this.operatorType = operatorType;
    this.chosenValue = chosenValue;
  }

  public String getUserType()
  {
    return userType;
  }

  public String getMeasureCategory()
  {
    return measureCategory;
  }

  public int getMeasureCategoryInt()
  {
    return measureCategoryInt;
  }

  public String getMeasureType()
  {
    return measureType;
  }

  public int getMeasureTypeInt()
  {
    return measureTypeInt;
  }

  public String getMeasureName()
  {
    return measureName;
  }

  public String getValueType()
  {
    return valueType;
  }

  public String getOperatorType()
  {
    return operatorType;
  }

  public String getChosenValue()
  {
    return chosenValue;
  }

  public boolean isSelectedMeasure(SPMeasure m)
  {
    return checkMeasureCategory(m.getMeasureClass()) && getMeasureTypeInt() == m.getMeasureType();
  }

  private boolean checkMeasureCategory(int iMeasureCategory)
  {
    /*
     * Summary Report(10) is divided into: Summary General(12), Summary
     * Internet(13), Summary Web(14) ...
     */
    return (iMeasureCategory == getMeasureCategoryInt() || ((iMeasureCategory == 10) && (11 <= getMeasureCategoryInt() && getMeasureCategoryInt() <= 17)));
  }

  public String toStringLog()
  {
    StringBuilder sb = new StringBuilder("Success Criterion : ");
    sb.append(getMeasureName());
    sb.append(", ").append(getMeasureCategory()).append("(").append(getMeasureCategoryInt()).append(")");
    sb.append(", ").append(getMeasureType()).append("(").append(getMeasureTypeInt()).append(")");
    return sb.toString();
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("\n\tUser Type: ").append(userType);
    sb.append("\n\tMesaure Category: ").append(getMeasureCategory());
    sb.append("\n\tExpression: ").append(getMeasureType()).append("(").append(valueType).append(")").append(" ").append(operatorType).append(" chosen value:").append(chosenValue)
        .append("##");
    return sb.toString();
  }

  @Extension
  public static class DescriptorImpl extends Descriptor<SuccessCriteria>
  {
    private final List<String> measureCategories;

    public DescriptorImpl()
    {
      super(SuccessCriteria.class);
      measureCategories = SilkPerformerBuilder.DESCRIPTOR.getMeasureCategoryList();
    }

    @Override
    public String getDisplayName()
    {
      return "Success Criterion";
    }

    public ListBoxModel doFillMeasureCategoryItems()
    {
      ListBoxModel items = new ListBoxModel();
      for (String measureCategory : measureCategories)
      {
        items.add(measureCategory, measureCategory);
      }
      return items;
    }

    public ListBoxModel doFillMeasureTypeItems(@QueryParameter String measureCategory)
    {
      if (measureCategory.isEmpty()) {
        measureCategory = "Controller";
      }
      ListBoxModel items = new ListBoxModel();
      for (String measureType : SilkPerformerBuilder.DESCRIPTOR.getMeasureTypes(measureCategory))
      {
        items.add(measureType, measureType);
      }
      return items;
    }

    public ListBoxModel doFillOperatorTypeItems()
    {
      ListBoxModel items = new ListBoxModel();
      items.add("<", "<");
      items.add(">", ">");
      items.add("<=", "<=");
      items.add(">=", ">=");
      items.add("=", "=");
      return items;
    }

    public ListBoxModel doFillValueTypeItems()
    {
      ListBoxModel items = new ListBoxModel();
      items.add("Minimum Value", "Minimum Value");
      items.add("Maximum Value", "Maximum Value");
      items.add("Average Value", "Average Value");
      items.add("Count All", "Count All");
      return items;
    }
    
       
      public FormValidation doCheckChosenValue(@QueryParameter String chosenValue) {
        try {
          Double.parseDouble(chosenValue);
          }
        catch (Exception e) {
          return FormValidation.error("Value not of type Double");
        }
        return FormValidation.ok();
      }
  }

}
