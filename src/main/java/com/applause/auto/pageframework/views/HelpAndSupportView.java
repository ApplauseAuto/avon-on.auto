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

@AndroidImplementation(AndroidHelpAndSupportView.class)
@IosImplementation(IosHelpAndSupportView.class)
public abstract class HelpAndSupportView extends AbstractDeviceView {

  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "getFAQTextElement"));
  }

  /**
   * Close Help & Support
   *
   * @return DashboardView
   */
  public DashboardView closeHelpAndSupport() {
    logger.info("Press close button");
    getCloseButton().pressButton();
    return DeviceViewFactory.create(DashboardView.class);
  }

  /**
   * Click 'FAQ' text
   *
   * @return FAQView
   */
  public FAQView clickFAQText() {
    logger.info("Click 'FAQ' text");
    getFAQTextElement().clickText();
    return DeviceViewFactory.create(FAQView.class);
  }

  /**
   * Click 'Call Us' text
   *
   * @return NativeCallUsView
   */
  public NativeCallUsView clickCallUsText() {
    logger.info("Click 'Call Us' text");
    getCallUsTextElement().clickText();
    return DeviceViewFactory.create(NativeCallUsView.class);
  }

  /**
   * Click 'Email Avon' text
   *
   * @return FAQView
   */
  public NativeEmailView clickEmailText() {
    logger.info("Click 'Email Avon' text");
    getEmailAvonTextElement().clickText();
    logger.info("Select email client");
    selectEmailClient();
    return DeviceViewFactory.create(NativeEmailView.class);
  }

  /**
   * Return string value from the 'Frequently Asked Questions' text element
   *
   * @return String
   */
  public String getFAQTextElementString() {
    return getFAQTextElement().getStringValue();
  }

  /**
   * Return string from the 'Call Us' text element
   *
   * @return String
   */
  public String getCallUsTextElememtString() {
    return getCallUsTextElement().getStringValue();
  }

  /**
   * Return string value form the 'Email Avon' text element
   *
   * @return String
   */
  public String getEmailAvonTextElementString() {
    return getEmailAvonTextElement().getStringValue();
  }

  /**
   * Verifies whether 'FAQ' item exists
   *
   * @return boolean
   */
  public boolean isFAQItemDisplayed() {
    return queryHelper.doesElementExist(getLocator(this, "getFAQTextElement"));
  }

  /**
   * Verifies whether 'Call Us' item exists
   *
   * @return boolean
   */
  public boolean isCallUsItemDisplayed() {
    return queryHelper.doesElementExist(getLocator(this, "getCallUsTextElement"));
  }

  /**
   * Verifies whether 'Email Avon' item exists
   *
   * @return boolean
   */
  public boolean isEmailAvonItemDisplayed() {
    return queryHelper.doesElementExist(getLocator(this, "getEmailAvonTextElement"));
  }

  /**
   * Select default email client
   *
   * @return
   */
  public abstract void selectEmailClient();

  @MobileElementLocator(
      android = "helpFaqTv",
      iOS =
          "//XCUIElementTypeCell[@name=\"helpCell_index_0\"]/XCUIElementTypeStaticText[position()=2]")
  public Text getFAQTextElement() {
    return new Text(getLocator(this, "getFAQTextElement"));
  }

  @MobileElementLocator(
      android = "helpCallTv",
      iOS =
          "//XCUIElementTypeCell[@name=\"helpCell_index_1\"]/XCUIElementTypeStaticText[position()=2]")
  public Text getCallUsTextElement() {
    return new Text(getLocator(this, "getCallUsTextElement"));
  }

  @MobileElementLocator(
      android = "helpEmailTv",
      iOS =
          "//XCUIElementTypeCell[@name=\"helpCell_index_2\"]/XCUIElementTypeStaticText[position()=2]")
  public Text getEmailAvonTextElement() {
    return new Text(getLocator(this, "getEmailAvonTextElement"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/closeIv.*\")",
      iOS = "closeReverse")
  protected Button getCloseButton() {
    return new Button(getLocator(this, "getCloseButton"));
  }
}

class AndroidHelpAndSupportView extends HelpAndSupportView {

  @Override
  public void selectEmailClient() {
    syncHelper.suspend(1000);
    if (syncHelper.isElementDisplayed("android:id/sem_title_default")) {
      logger.info("Click 'Just Once' button");
      getJustOnceButton().pressButton();
    }
  }

  @MobileElementLocator(android = "android:id/button_once")
  protected Button getJustOnceButton() {
    return new Button(getLocator(this, "getJustOnceButton"));
  }

  @MobileElementLocator(android = "android:id/button_always")
  protected Button getAlwaysButton() {
    return new Button(getLocator(this, "getAlwaysButton"));
  }
}

class IosHelpAndSupportView extends HelpAndSupportView {

  @Override
  public void selectEmailClient() {}
}
