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
import com.applause.auto.pageframework.views.termsandconditions.SettingsTermsAndConditionsView;

@AndroidImplementation(AndroidSettingView.class)
@IosImplementation(IosSettingView.class)
public abstract class SettingsView extends AbstractDeviceView {

  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  /**
   * Opens Privacy Policy
   *
   * @return PrivacyPolicyView
   */
  public PrivacyPolicyView openPrivacyPolicy() {
    getPrivacyPolicy().tap();
    return DeviceViewFactory.create(PrivacyPolicyView.class);
  }

  /**
   * Opens Terms And Conditions
   *
   * @return SettingsTermsAndConditionsView
   */
  public SettingsTermsAndConditionsView openTermsAndConditions() {
    logger.info("Selecting Terms and Conditions");
    getTermsAndConditions().tap();
    return DeviceViewFactory.create(SettingsTermsAndConditionsView.class);
  }

  /**
   * Return the string in the Application Version element
   *
   * @return String
   */
  public String getAppVersionTextString() {
    return getAppVersionText().getStringValue();
  }

  /**
   * Close Settings
   *
   * @return DashboardView
   */
  public DashboardView closeSettings() {
    getCloseButton().tap();
    return DeviceViewFactory.create(DashboardView.class);
  }

  /**
   * Tap 'Create Pin Code' option
   *
   * @return PinView
   */
  public PinView openCreatePinOption() {
    logger.info("Tap 'Create Pin Code' option");
    getChangePinText().tap();
    return DeviceViewFactory.create(PinView.class);
  }

  /** Tap 'Language' option */
  public ChangeLanguageView openLanguageOption() {
    logger.info("Tap 'Language' option");
    getLanguageText().tap();
    return DeviceViewFactory.create(ChangeLanguageView.class);
  }

  /** Tap 'Market' option */
  public ChangeMarketView openMarketOption() {
    logger.info("Tap 'Market' option");
    getMarketText().tap();
    return DeviceViewFactory.create(ChangeMarketView.class);
  }

  /**
   * Verifies that 'Settings' title element exists
   *
   * @return boolean
   */
  public boolean settingTitleElementExists() {
    syncHelper.waitForElementToAppear(getLocator(this, "getCloseButton"));
    return queryHelper.doesElementExist(getLocator(this, "getSettingsTitleElement"));
  }

  /** Taps 'Login with touch/finger ID' switch. */
  public void tapLoginWithRecognitionId() {
    logger.info("Tapping 'Login with touch/finger ID' switch.");
    loginWithTouchFingerIdSwitch().tap();
  }

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "getCloseButton"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/closeIv.*\")",
      iOS = "closeReverse")
  protected Button getCloseButton() {
    return new Button(getLocator(this, "getCloseButton"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/settingsPolicy.*\")",
      iOS = "//*[@name=\"SettingsCell_index_6\"]/XCUIElementTypeStaticText")
  protected Button getPrivacyPolicy() {
    return new Button(getLocator(this, "getPrivacyPolicy"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/settingsTerms.*\")",
      iOS = "//*[@name=\"SettingsCell_index_5\"]/XCUIElementTypeStaticText")
  protected Button getTermsAndConditions() {
    return new Button(getLocator(this, "getTermsAndConditions"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/settingsAppVersionTv.*\")",
      iOS =
          "//XCUIElementTypeTable/following-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText")
  public Text getAppVersionText() {
    return new Text(getLocator(this, "getAppVersionText"));
  }

  @MobileElementLocator(
      android =
          "//*[@resource-id='com.avon.avonon.uat:id/settingsChangeLanguage']/android.widget.TextView[2]",
      iOS = "//XCUIElementTypeCell[@name=\"SettingsCell_index_3\"]/XCUIElementTypeStaticText[1]")
  protected Text getLanguageText() {
    return new Text(getLocator(this, "getLanguageText"));
  }

  @MobileElementLocator(
      android = "toolbarTitleTv",
      iOS = "//XCUIElementTypeCell[@name=\"SettingsCell_index_1\"]")
  protected Text getSettingsTitleElement() {
    return new Text(getLocator(this, "getSettingsTitleElement"));
  }

  @MobileElementLocator(
      android = "settingsChangePincodeTv",
      iOS = "//*[@name=\"SettingsCell_index_1\"]/XCUIElementTypeStaticText")
  protected Text getChangePinText() {
    return new Text(getLocator(this, "getChangePinText"));
  }

  @MobileElementLocator(
      android =
          "//*[@resource-id='com.avon.avonon.uat:id/settingsChangeMarket']/android.widget.TextView[2]",
      iOS = "//*[@name=\"SettingsCell_index_4\"]/XCUIElementTypeStaticText[1]")
  protected Text getMarketText() {
    return new Text(getLocator(this, "getMarketText"));
  }

  @MobileElementLocator(
      android =
          "//android.widget.Switch[contains(@resource-id, 'id/settingsUseFingerprintSwitch')]")
  protected Button loginWithTouchFingerIdSwitch() {
    return new Button(getLocator(this, "loginWithTouchFingerIdSwitch"));
  }
}

class AndroidSettingView extends SettingsView {}

class IosSettingView extends SettingsView {}
