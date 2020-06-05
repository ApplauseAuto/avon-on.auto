package com.applause.auto.pageframework.views;

import java.lang.invoke.MethodHandles;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.framework.pageframework.util.logger.LogController;

@AndroidImplementation(AndroidPrivacyPolicyView.class)
@IosImplementation(IosPrivacyPolicyView.class)
public abstract class PrivacyPolicyView extends AbstractDeviceView {

  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "getPrivacyPolicyText"));
  }

  /**
   * Return the string in the Privacy Policy
   *
   * @return String
   */
  public String getPrivacyPolicyTextString() {
    return getPrivacyPolicyText().getStringValue();
  }

  @MobileElementLocator(android = "//android.view.View[1]", iOS = "**/XCUIElementTypeStaticText[1]")
  public Text getPrivacyPolicyText() {
    return new Text(getLocator(this, "getPrivacyPolicyText"));
  }
}

class AndroidPrivacyPolicyView extends PrivacyPolicyView {}

class IosPrivacyPolicyView extends PrivacyPolicyView {}
