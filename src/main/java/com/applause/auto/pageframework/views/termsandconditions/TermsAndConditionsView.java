package com.applause.auto.pageframework.views.termsandconditions;

import java.lang.invoke.MethodHandles;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.framework.pageframework.devicecontrols.Checkbox;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.framework.pageframework.util.TestHelper;
import com.applause.auto.framework.pageframework.util.logger.LogController;
import com.applause.auto.pageframework.testdata.TestConstants.Timeouts;
import com.applause.auto.pageframework.utils.Helper;
import com.applause.auto.pageframework.views.PinView;

@AndroidImplementation(AndroidTermsAndConditionsView.class)
@IosImplementation(IosTermsAndConditionsView.class)
public abstract class TermsAndConditionsView extends AbstractDeviceView {

  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "getTermsAndConditionsText"));
  }

  /**
   * Accept Terms and move to Pin view
   *
   * @return PinView
   */
  public PinView acceptTermsAndContinue() {
    long timeLimit = TestHelper.getCurrentGMT6Time() + Timeouts.FIFTY_SECONDS_MILLI;
    while (!queryHelper.doesElementExist(getLocator(this, "getAgreeCheckBox"))
        && timeLimit > TestHelper.getCurrentGMT6Time()) {
      Helper.scrollToBottom(3);
    }

    getAgreeCheckBox().checkCheckbox();
    getContinueButton().pressButton();
    return DeviceViewFactory.create(PinView.class);
  }

  @MobileElementLocator(
      android = "termsTextTv",
      iOS = "**/XCUIElementTypeWebView/XCUIElementTypeOther/XCUIElementTypeOther")
  protected Text getTermsAndConditionsText() {
    return new Text(getLocator(this, "getTermsAndConditionsText"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/termsCheckBox.*\")",
      iOS = "**/XCUIElementTypeButton[2]")
  protected Checkbox getAgreeCheckBox() {
    return new Checkbox(getLocator(this, "getAgreeCheckBox"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/termsContinueBtn.*\")",
      iOS = "**/XCUIElementTypeButton[3]")
  protected Button getContinueButton() {
    return new Button(getLocator(this, "getContinueButton"));
  }
}

class AndroidTermsAndConditionsView extends TermsAndConditionsView {}

class IosTermsAndConditionsView extends TermsAndConditionsView {}
