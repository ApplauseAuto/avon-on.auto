package com.applause.auto.pageframework.views;

import java.lang.invoke.MethodHandles;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.DeviceChunkFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.framework.pageframework.util.logger.LogController;
import com.applause.auto.pageframework.chunks.AvonDialogChunk;

@AndroidImplementation(AndroidChangeLanguageView.class)
@IosImplementation(IosChangeLanguageView.class)
public abstract class ChangeLanguageView extends AbstractDeviceView {
  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "getCurrentlySelectedLanguage"));
  }

  @MobileElementLocator(android = "changeMarketMessage", iOS = "")
  protected Text getInfoDesc() {
    return new Text(getLocator(this, "getInfoMsg"));
  }

  @MobileElementLocator(
      android =
          "//*[@resource-id='com.avon.avonon.uat:id/textView']/following-sibling::*[@resource-id='com.avon.avonon.uat:id/localeName']",
      iOS =
          "//XCUIElementTypeStaticText[@name=\"infoLabel\"]/preceding-sibling::XCUIElementTypeStaticText")
  protected Text getCurrentlySelectedLanguage() {
    return new Text(getLocator(this, "getCurrentlySelectedLanguage"));
  }

  @MobileElementLocator(
      android =
          "//android.view.ViewGroup/android.widget.TextView[@resource-id='com.avon.avonon.uat:id/localeName' and position()=1]",
      iOS =
          "//XCUIElementTypeStaticText[@name=\"mainLabel\" and not (following-sibling::XCUIElementTypeStaticText)]")
  protected Text getCurrentlyUnselectedLanguage() {
    return new Text(getLocator(this, "getCurrentlyUnselectedLanguage"));
  }

  /**
   * Return string value from the 'Currently Selected Language' text element
   *
   * @return String
   */
  public String getCurrentlySelectedLanguageString() {
    return getCurrentlySelectedLanguage().getStringValue();
  }

  /**
   * Change language
   *
   * @return
   */
  public abstract void changeLanguage();

  /**
   * Change language
   *
   * @param language
   * @return
   */
  public abstract void changeLanguage(String language);
}

class AndroidChangeLanguageView extends ChangeLanguageView {

  @Override
  public void changeLanguage() {
    if (queryHelper.getMobileElementCount(
            "//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup")
        > 1) {
      getCurrentlyUnselectedLanguage().clickText();
    } else {
      getCurrentlySelectedLanguage().clickText();
    }
    DeviceChunkFactory.create(AvonDialogChunk.class).tapAcceptButton();
    syncHelper.waitForElementToAppear(getLocator(this, "getCurrentlySelectedLanguage"));
  }

  @Override
  public void changeLanguage(String language) {
    new Button("//android.widget.TextView[@text='" + language + "']").pressButton();
    DeviceChunkFactory.create(AvonDialogChunk.class).tapAcceptButton();
    syncHelper.waitForElementToAppear(getLocator(this, "getCurrentlySelectedLanguage"));
  }
}

class IosChangeLanguageView extends ChangeLanguageView {

  @Override
  public void changeLanguage() {
    getCurrentlyUnselectedLanguage().clickText();
    DeviceChunkFactory.create(AvonDialogChunk.class).tapAcceptButton();
    syncHelper.waitForElementToAppear(getLocator(this, "getCurrentlySelectedLanguage"));
  }

  @Override
  public void changeLanguage(String language) {}
}
