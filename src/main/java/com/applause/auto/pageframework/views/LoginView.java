package com.applause.auto.pageframework.views;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.framework.pageframework.devicecontrols.TextBox;
import com.applause.auto.pageframework.testdata.TestConstants.Timeouts;
import com.applause.auto.pageframework.utils.LogHelper;
import com.applause.auto.pageframework.views.termsandconditions.TermsAndConditionsView;

import static com.applause.auto.pageframework.testdata.TestConstants.Timeouts.FIFTY_SECONDS_MILLI;
import static com.applause.auto.pageframework.testdata.TestConstants.Timeouts.THREE_SECONDS_MILLI;

@AndroidImplementation(AndroidLoginView.class)
@IosImplementation(IosLoginView.class)
public abstract class LoginView extends AbstractDeviceView {

  protected static final LogHelper LOG = LogHelper.getInstance();

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "getPasswordField"));
  }

  /**
   * Enters Account and password to login
   *
   * @param accountNumber
   * @param password
   * @return PinView
   */
  public PinView login(String accountNumber, String password) {
    enterLoginDetails(accountNumber, password);
    syncHelper.suspend(Timeouts.TWO_SECONDS_MILLI);

    if (syncHelper.isElementDisplayed(
        getLocator(this, "getTermsAndConditionsText"), THREE_SECONDS_MILLI)) {
      LOG.info("terms and conditions appeared");
      DeviceViewFactory.create(TermsAndConditionsView.class).acceptTermsAndContinue();
    }

    return DeviceViewFactory.create(PinView.class);
  }

  /**
   * Enters invalid login details
   *
   * @param accountNumber
   * @param password
   * @return IncorrectCredentialsView
   */
  public IncorrectCredentialsView attemptIncorrectLogin(String accountNumber, String password) {
    enterLoginDetails(accountNumber, password);
    return DeviceViewFactory.create(IncorrectCredentialsView.class);
  }

  /**
   * Verifies that 'Login' button element exists
   *
   * @return boolean
   */
  public boolean loginButtonIsDisplayed() {
    return queryHelper.doesElementExist(getLocator(this, "getLoginButton"));
  }

  /** Taps 'Forgotten login details?' button */
  public void tapForgottenLoginDetails() {
    LOG.info("Tapping 'Forgotten login details?' button.");
    forgottenLoginDetailsButton().tap();
    syncHelper.suspend(THREE_SECONDS_MILLI);
  }

  protected abstract void enterLoginDetails(String accountNumber, String password);

  @MobileElementLocator(android = "", iOS = "**/XCUIElementTypeStaticText[1]")
  protected Text getWelcomeHeader() {
    return new Text(getLocator(this, "getWelcomeHeader"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/loginAccountEt.*\")",
      iOS = "//XCUIElementTypeTextField")
  protected TextBox getAccountNumberField() {
    return new TextBox(getLocator(this, "getAccountNumberField"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/loginPasswordEt.*\")",
      iOS = "//XCUIElementTypeSecureTextField")
  protected TextBox getPasswordField() {
    return new TextBox(getLocator(this, "getPasswordField"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/loginBtn.*\")",
      iOS =
          "**/XCUIElementTypeOther[$type =='XCUIElementTypeImage'$]/XCUIElementTypeOther[-1]/XCUIElementTypeButton[1]")
  protected Button getLoginButton() {
    return new Button(getLocator(this, "getLoginButton"));
  }

  @MobileElementLocator(
      android = "termsTextTv",
      iOS = "**/XCUIElementTypeWebView/XCUIElementTypeOther/XCUIElementTypeOther")
  protected Button getTermsAndConditionsText() {
    return new Button(getLocator(this, "getTermsAndConditionsText"));
  }

  @MobileElementLocator(android = "pleaseScrollTv", iOS = "")
  protected Text getPleaseScrollText() {
    return new Text(getLocator(this, "getPleaseScrollText"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/loginForgotLoginBtn.*\")",
      iOS = "(//XCUIElementTypeButton)[last()]")
  private Button forgottenLoginDetailsButton() {
    return new Button(getLocator(this, "forgottenLoginDetailsButton"));
  }
}

class AndroidLoginView extends LoginView {

  @Override
  protected void enterLoginDetails(String accountNumber, String password) {
    LOG.info("Entering account number: %s", accountNumber);
    getAccountNumberField().enterText(accountNumber);

    LOG.info("Entering password: %s", password);
    getPasswordField().enterText(password);
    getDriver().hideKeyboard();

    LOG.info("Tapping login button.");
    getLoginButton().tap();
  }
}

class IosLoginView extends LoginView {

  @Override
  protected void enterLoginDetails(String accountNumber, String password) {
    LOG.info("Entering account number: %s", accountNumber);
    getSyncHelper().waitForElementToAppear(getLocator(this, "getAccountNumberField"));
    getAccountNumberField().tap();
    getAccountNumberField().enterText(accountNumber);

    LOG.info("Entering password: %s", password);
    getPasswordField().tap();
    getPasswordField().enterText(password);

    LOG.info("Tapping welcome header.");
    getWelcomeHeader().clickText(); // dismisses keyboard
    syncHelper.suspend(2000);

    LOG.info("Tapping login button");
    getLoginButton().tap();
  }
}
