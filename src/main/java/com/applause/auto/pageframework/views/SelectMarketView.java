package com.applause.auto.pageframework.views;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.pageframework.testdata.Market;
import com.applause.auto.pageframework.utils.Helper;
import com.applause.auto.pageframework.utils.LogHelper;

@AndroidImplementation(AndroidSelectMarketView.class)
@IosImplementation(IosSelectMarketView.class)
public abstract class SelectMarketView extends AbstractDeviceView {

  protected static final LogHelper LOG = LogHelper.getInstance();

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "marketSelectButton"));
    WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 10);
    webDriverWait.until(
        ExpectedConditions.elementToBeClickable(marketTextElement().getMobileElement()));
  }

  /**
   * Clicks continue button to go to LoginView
   *
   * @return LoginView
   */
  public LoginView continueToLoginView() {
    LOG.info("Tapping continue.");
    continueButton().pressButton();
    return DeviceViewFactory.create(LoginView.class);
  }

  /**
   * sets the market and language;
   *
   * @param market
   * @param language
   * @return SelectMarketView
   */
  public SelectMarketView selectMarketAndLanguage(String market, String language) {
    selectMarket(market);
    selectLanguage(language);
    return DeviceViewFactory.create(SelectMarketView.class);
  }

  /**
   * Returns currently selected language value
   *
   * @return
   */
  public String getCurrentLanguage() {
    return languageSelectButton().getButtonString().trim();
  }

  /**
   * Returns currently selected market value
   *
   * @return
   */
  public String getCurrentMarket() {
    return marketTextElement().getStringValue().trim();
  }

  /** @return true, if market select dropdown is displayed */
  public boolean marketSelectDropdownIsDisplayed() {
    return syncHelper.isElementDisplayed(getLocator(this, "marketSelectButton"));
  }

  /**
   * Selects market
   *
   * @param market
   */
  public abstract void selectMarket(String market);

  /**
   * Selects a language
   *
   * @param language
   */
  public abstract void selectLanguage(String language);

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/marketSelectionDoneBtn.*\")",
      iOS =
          "//XCUIElementTypeScrollView[descendant::XCUIElementTypeImage[@name='avonOnLogoPositive']]//XCUIElementTypeButton[not(contains(@name, 'chevron'))]")
  protected Button continueButton() {
    return new Button(getLocator(this, "continueButton"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/marketSelectMarketBtn.*\")",
      iOS = "**/XCUIElementTypeButton[$name contains[c] 'chevronDown'$][1]")
  protected Button marketSelectButton() {
    return new Button(getLocator(this, "marketSelectButton"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/marketSelectLangBtn.*\")",
      iOS =
          "(//XCUIElementTypeButton[@name='chevronDown'])[2]/preceding-sibling::XCUIElementTypeTextField")
  protected Button languageSelectButton() {
    return new Button(getLocator(this, "languageSelectButton"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/marketSelectMarketBtn.*\")",
      iOS = "**/XCUIElementTypeTextField[1]")
  protected Text marketTextElement() {
    return new Text(getLocator(this, "marketTextElement"));
  }
}

class AndroidSelectMarketView extends SelectMarketView {

  @Override
  public void selectMarket(String market) {
    LOG.info("Set market: %s", market);
    marketSelectButton().pressButton();
    syncHelper.waitForElementToAppear("//android.widget.TextView[@text='Colombia']");
    Helper.androidScrollToText(
        Market.getMarketName(),
        "new UiSelector().resourceIdMatches(\".*id/select_dialog_listview.*\")");
    new Button("//android.widget.TextView[@text='" + market + "']").pressButton();
  }

  public void selectLanguage(String language) {
    LOG.info("Set language: %s", language);
    languageSelectButton().pressButton();
    syncHelper.waitForElementToAppear("//android.widget.TextView[@text='" + language + "']");
    new Button("//android.widget.TextView[@text='" + language + "']").pressButton();
  }
}

class IosSelectMarketView extends SelectMarketView {

  @Override
  public void selectMarket(String market) {
    LOG.info("Set market: %s", market);
    marketSelectButton().tap();
    Helper.setIosPickerWheelValue(market);
  }

  @Override
  public void selectLanguage(String language) {
    LOG.info("Set language: %s", language);
    languageSelectButton().tap();
    Helper.setIosPickerWheelValue(language);
  }
}
