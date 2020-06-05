package com.applause.auto.pageframework.views;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.BaseDeviceControl;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.framework.pageframework.devicecontrols.TextBox;
import com.applause.auto.pageframework.testdata.TestConstants.Timeouts;
import com.applause.auto.pageframework.utils.LogHelper;

@AndroidImplementation(AndroidPinView.class)
@IosImplementation(IosPinView.class)
public abstract class PinView extends AbstractDeviceView {

  protected static final LogHelper LOG = LogHelper.getInstance();
  protected static int pinCount = 0;

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "pinContainer"));
  }

  /**
   * Enters pin and confirms
   *
   * @param pin
   * @return PinView
   */
  public abstract DashboardView enterAndConfirmPin(String pin);

  /**
   * Enters a pin, then confirms with a different pin
   *
   * @return PinView
   */
  public PinView enterUnmatchingPins() {
    enterPinAndConfirmation("0000", "1111");
    return this;
  }

  /** Enters a pin */
  public abstract <T> T enterPin(String pin);

  /**
   * Enters pin to re-login
   *
   * @param pin
   * @return DashboardView
   */
  public DashboardView enterPinToLogin(String pin) {
    enterPin(pin);
    return DeviceViewFactory.create(DashboardView.class);
  }

  /** Tap 'ok' button */
  public void tapOkButton() {
    LOG.info("Tapping 'ok' button.");
    okButton().tap();
  }

  /**
   * Gets the message returned after entering a different confirmation pin
   *
   * @return String
   */
  public String getPinConfirmationError() {
    return pinConfirmationErrorMessageElement().getStringValue().trim();
  }

  /**
   * Gets confirmation msg, after pin code was successfully set
   *
   * @return String
   */
  public String getPinConfirmationSuccess() {
    return pinSetMessageElement().getStringValue().trim();
  }

  /**
   * Taps 'Forgotten PIN code?' button
   *
   * @return Expected view
   */
  public abstract <T> T tapForgottenPinCode();

  protected void enterPinAndConfirmation(String pin, String confirmation) {
    LOG.info("Entering pin.");
    enterPin(pin);

    LOG.info("Entering confirmation pin.");
    enterPin(confirmation);
    pinCount = 0;
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*enterPinEditText.*\")",
      iOS =
          "//XCUIElementTypeNavigationBar[contains(@name, 'EnterPinView')] | (//XCUIElementTypeSecureTextField)[3]")
  protected BaseDeviceControl pinContainer() {
    return new TextBox(getLocator(this, "pinContainer"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/enterPintSubtitleTv.*\")",
      iOS = "(//XCUIElementTypeStaticText)[1]")
  protected Text pinMessageElement() {
    return new Text(getLocator(this, "pinMessageElement"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/enterPinDoneTv.*\")",
      iOS = "(//XCUIElementTypeStaticText)[2]")
  protected Text pinSetMessageElement() {
    return new Text(getLocator(this, "pinSetMessageElement"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/enterPinErrorTv.*\")",
      iOS = "(//XCUIElementTypeStaticText)[2]")
  protected Text pinConfirmationErrorMessageElement() {
    return new Text(getLocator(this, "pinConfirmationErrorMessageElement"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/enableFingerprintBtn.*\")",
      iOS = "button_0")
  protected Button okButton() {
    return new Button(getLocator(this, "okButton"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/enterPinForgotPasswordBtn.*\")",
      iOS =
          "**/XCUIElementTypeOther[$type =='XCUIElementTypeImage' and name=='avonOnLogoPositive'$]/XCUIElementTypeOther/XCUIElementTypeButton")
  protected Button forgottenPinCodeButton() {
    return new Button(getLocator(this, "forgottenPinCodeButton"));
  }
}

class AndroidPinView extends PinView {

  @Override
  public DashboardView enterAndConfirmPin(String pin) {
    enterPinAndConfirmation(pin, pin);
    syncHelper.waitForElementToAppear(pinSetMessageElement());

    if (syncHelper.isElementDisplayed(getLocator(this, "usePinCodeButton"))) {
      LOG.info("Tapping 'Use pin code' button.");
      usePinCodeButton().tap();
    } else {
      LOG.info("Tapping 'ok' button.");
      okButton().tap();
    }

    pinCount = 0;
    return DeviceViewFactory.create(DashboardView.class);
  }

  @Override
  public <T> T tapForgottenPinCode() {
    LOG.info("Tapping 'Forgotten PIN code?' button.");
    forgottenPinCodeButton().tap();
    return (T) DeviceViewFactory.create(LoginView.class);
  }

  @Override
  public <T> T enterPin(String pin) {
    LOG.info("Entering pin: %s", pin);
    pinContainer().tap();
    pinContainer().getMobileElement().sendKeys(pin);
    pinCount++;

    /** the only other option here is to look for confirm message wont work with markets */
    syncHelper.suspend(Timeouts.THREE_SECONDS_MILLI);

    if (pinCount == 4) {
      pinCount = 0;
      return (T) DeviceViewFactory.create(LoginView.class);
    }

    return (T) this;
  }

  @MobileElementLocator(android = "new UiSelector().resourceIdMatches(\".*id/usePinCodeBtn.*\")")
  protected Button usePinCodeButton() {
    return new Button(getLocator(this, "usePinCodeButton"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/enableFingerprintBtn.*\")")
  protected Button enableRecognitionButton() {
    return new Button(getLocator(this, "enableRecognitionButton"));
  }
}

class IosPinView extends PinView {

  @Override
  public DashboardView enterAndConfirmPin(String pin) {
    enterPinAndConfirmation(pin, pin);

    if (syncHelper.isElementDisplayed(
        getLocator(this, "usePinCodeButton"), Timeouts.THREE_SECONDS_MILLI)) {
      LOG.info("Tap on Use Pin code button");
      usePinCodeButton().pressButton();
    }
    if (syncHelper.isElementDisplayed(
        getLocator(this, "allowNotificationsButton"), Timeouts.THREE_SECONDS_MILLI)) {
      LOG.info("Allow notification");
      allowNotificationsButton().pressButton();
    }
    if (syncHelper.isElementDisplayed(
        getLocator(this, "showMeThePromptButton"), Timeouts.THREE_SECONDS_MILLI)) {

      LOG.info("Tap on Show me prompt button");
      showMeThePromptButton().pressButton();

      if (syncHelper.isElementDisplayed(
          getLocator(this, "allowNotificationsButton"), Timeouts.THREE_SECONDS_MILLI)) {
        LOG.info("Allow notification");
        allowNotificationsButton().pressButton();
      }
    }

    pinCount = 0;
    return DeviceViewFactory.create(DashboardView.class);
  }

  @Override
  public <T> T tapForgottenPinCode() {
    LOG.info("Tapping 'Forgotten PIN code?' button.");
    forgottenPinCodeButton().tap();
    return (T) DeviceViewFactory.create(SelectMarketView.class);
  }

  @Override
  public <T> T enterPin(String pin) {
    LOG.info("Entering pin: %s", pin);
    syncHelper.waitForElementToAppear(
        "**/XCUIElementTypeSecureTextField", Timeouts.THREE_SECONDS_MILLI);
    int count = 0;

    for (char digit : pin.toCharArray()) {
      pinTextBox(++count).enterText(Character.toString(digit));
    }

    pinCount++;

    /** the only other option here is to look for confirm message wont work with markets */
    syncHelper.suspend(Timeouts.THREE_SECONDS_MILLI);

    if (pinCount == 3) {
      pinCount = 0;
      return (T) this;
    }

    return (T) this;
  }

  // Occurs on iOS for enable Face ID on some devices
  @MobileElementLocator(iOS = "**/XCUIElementTypeStaticText[$name CONTAINS 'FaceID'$][-1]")
  private Button usePinCodeButton() {
    return new Button(getLocator(this, "usePinCodeButton"));
  }

  @MobileElementLocator(
      iOS =
          "**/XCUIElementTypeOther[$type =='XCUIElementTypeImage' and name=='avonOnLogoPositive'$]/XCUIElementTypeButton")
  private Button showMeThePromptButton() {
    return new Button(getLocator(this, "showMeThePromptButton"));
  }

  @MobileElementLocator(iOS = "Allow")
  private Button allowNotificationsButton() {
    return new Button(getLocator(this, "allowNotificationsButton"));
  }

  @MobileElementLocator(iOS = "**/XCUIElementTypeSecureTextField[%s]")
  private TextBox pinTextBox(int cellNumber) {
    return new TextBox(getLocator(this, "pinTextBox", cellNumber));
  }
}
