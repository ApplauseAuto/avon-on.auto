package com.applause.auto.pageframework.views;

import java.lang.invoke.MethodHandles;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.framework.pageframework.util.logger.LogController;

@AndroidImplementation(AndroidIncorrectCredentialsView.class)
@IosImplementation(IosIncorrectCredentialsView.class)
public abstract class IncorrectCredentialsView extends AbstractDeviceView {

  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "getTryAgainButton"));
  }

  /**
   * Clicks try again button to go to LoginView
   *
   * @return LoginView
   */
  public LoginView returnToLoginView() {
    logger.info("Tapping continue");
    getTryAgainButton().tap();
    return DeviceViewFactory.create(LoginView.class);
  }

  /**
   * Returns the text from the error message element
   *
   * @return String
   */
  public String getErrorMessageString() {
    return getErrorMessage().getStringValue();
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/messageBodyTv.*\")",
      iOS = "//XCUIElementTypeStaticText[2]")
  protected Text getErrorMessage() {
    return new Text(getLocator(this, "getErrorMessage"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/messageButton.*\")",
      iOS = "**/XCUIElementTypeButton")
  protected Button getTryAgainButton() {
    return new Button(getLocator(this, "getTryAgainButton"));
  }
}

class AndroidIncorrectCredentialsView extends IncorrectCredentialsView {}

class IosIncorrectCredentialsView extends IncorrectCredentialsView {}
