package com.applause.auto.pageframework.utils;

import org.openqa.selenium.support.ui.ExpectedCondition;

import com.applause.auto.framework.pageframework.devicecontrols.BaseDeviceControl;

/** The type Extended expected conditions. */
public class ExtendedExpectedConditions {

  /**
   * Element digit value to be more than expected condition.
   *
   * @param baseDeviceControl the base device control
   * @param elementIntValue the element int value
   * @return the expected condition
   */
  public static ExpectedCondition<Boolean> elementDigitValueToBeMoreThan(
      BaseDeviceControl baseDeviceControl, int elementIntValue) {
    return (webDriver -> {
      try {
        return Integer.parseInt(
                baseDeviceControl.getMobileElement().getText().replaceAll("[^0-9]", ""))
            >= elementIntValue;
      } catch (NumberFormatException e) {
        return false;
      }
    });
  }
}
