package com.applause.auto.pageframework.views;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.DeviceChunkFactory;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.pageframework.chunks.AvonDialogChunk;
import com.applause.auto.pageframework.utils.Helper;

@AndroidImplementation(AndroidChangeMarketView.class)
@IosImplementation(IosChangeMarketView.class)
public abstract class ChangeMarketView extends AbstractDeviceView {

  /** Tap first unselected language. */
  public void tapFirstUnselectedLnaguage() {
    getFirstUnselectedLanguageText().tap();
  }

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "getDescriptionText"));
  }

  /**
   * Change market
   *
   * @return
   */
  public abstract SelectMarketView selectMarket();

  /**
   * Change market
   *
   * @param market
   * @return
   */
  public abstract SelectMarketView selectMarket(String market);

  @MobileElementLocator(
      android = "changeMarketMessage",
      iOS = "**/XCUIElementTypeTable/**XCUIElementTypeStaticText[1]")
  protected Text getDescriptionText() {
    return new Text(getLocator(this, "getDescriptionText"));
  }

  @MobileElementLocator(
      android =
          "(//android.view.ViewGroup/*[@resource-id='com.avon.avonon.uat:id/localeName' and position()=1])[1]",
      iOS = "")
  protected Text getFirstUnselectedLanguageText() {
    return new Text(getLocator(this, "getDescriptionText"));
  }
}

class AndroidChangeMarketView extends ChangeMarketView {

  @Override
  public SelectMarketView selectMarket() {
    getFirstUnselectedLanguageText().clickText();
    DeviceChunkFactory.create(AvonDialogChunk.class).tapAcceptButton();
    return DeviceViewFactory.create(SelectMarketView.class);
  }

  @Override
  public SelectMarketView selectMarket(String market) {
    String locator = "//android.widget.TextView[@text='" + market + "']";
    Helper.androidScrollToText(
        market, "new UiSelector().resourceIdMatches(\".*id/marketChangeRv.*\")");
    new Text(locator).clickText();
    DeviceChunkFactory.create(AvonDialogChunk.class).tapAcceptButton();
    return DeviceViewFactory.create(SelectMarketView.class);
  }
}

class IosChangeMarketView extends ChangeMarketView {

  @Override
  public SelectMarketView selectMarket() {
    return DeviceViewFactory.create(SelectMarketView.class);
  }

  @Override
  public SelectMarketView selectMarket(String market) {
    String locator =
        "**/XCUIElementTypeTable/**XCUIElementTypeStaticText[$value='" + market + "'$]";
    Helper.scrollDownToElementCloseToMiddle(locator, 10);
    new Text(locator).clickText();
    DeviceChunkFactory.create(AvonDialogChunk.class).tapOkButton();
    return DeviceViewFactory.create(SelectMarketView.class);
  }
}
