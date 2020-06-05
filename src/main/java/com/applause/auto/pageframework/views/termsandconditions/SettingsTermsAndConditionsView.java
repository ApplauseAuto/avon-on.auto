package com.applause.auto.pageframework.views.termsandconditions;

import java.lang.invoke.MethodHandles;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.framework.pageframework.util.logger.LogController;

@AndroidImplementation(AndroidSettingsTermsAndConditionsView.class)
@IosImplementation(IosSettingsTermsAndConditionsView.class)
public abstract class SettingsTermsAndConditionsView extends AbstractDeviceView {

  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "getTermsAndConditionsText"));
  }

  /**
   * Return the string in the Terms And Conditions
   *
   * @return String
   */
  public String getTermsAndConditionsTextString() {
    logger.info("Getting Text for Terms and Conditions");
    return getTermsAndConditionsText().getStringValue();
  }

  @MobileElementLocator(
      android = "//android.view.View",
      iOS = "(//XCUIElementTypeWebView//XCUIElementTypeOther[1]/XCUIElementTypeStaticText)[1]")
  protected Text getTermsAndConditionsText() {
    return new Text(getLocator(this, "getTermsAndConditionsText"));
  }
}

class AndroidSettingsTermsAndConditionsView extends SettingsTermsAndConditionsView {}

class IosSettingsTermsAndConditionsView extends SettingsTermsAndConditionsView {}
