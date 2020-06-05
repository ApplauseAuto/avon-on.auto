package com.applause.auto.pageframework.views.socialsharinghub;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.DeviceChunkFactory;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.pageframework.chunks.IMBrochuresChunk;
import com.applause.auto.pageframework.chunks.MainPromoChunk;
import com.applause.auto.pageframework.testdata.TestConstants;
import com.applause.auto.pageframework.utils.Helper;
import com.applause.auto.pageframework.utils.LogHelper;
import com.applause.auto.pageframework.views.DashboardView;

@AndroidImplementation(AndroidSocialSharingHubView.class)
@IosImplementation(IosSocialSharingHubView.class)
public abstract class SocialSharingHubView extends AbstractDeviceView {

  protected static final LogHelper LOG = LogHelper.getInstance();

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "getCloseButton"));
  }

  /**
   * Checks the promo is displayed
   *
   * @return boolean
   */
  public abstract boolean mainPromoIsDisplayed();

  /**
   * Checks the main promo can be shared
   *
   * @return
   */
  public boolean mainPromoCanBeShared() {
    LOG.info("Check the promo can be shared");
    return getMainPromo().promoCanBeShared();
  }

  /**
   * Scrolls to 'IM Brochures' chunk
   *
   * @return IMBrochuresChunk
   */
  public abstract IMBrochuresChunk scrollToIMBrochuresChunk();

  /**
   * Tap 'Close' button
   *
   * @return DashboardView
   */
  public DashboardView closeSocialSharingHubView() {
    LOG.info("Tapping 'Close' button.");
    getCloseButton().pressButton();
    return DeviceViewFactory.create(DashboardView.class);
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/closeIv.*\")",
      iOS = "closeReverse")
  protected Button getCloseButton() {
    return new Button(getLocator(this, "getCloseButton"));
  }

  @MobileElementLocator(
      android =
          "//android.widget.TextView[@resource-id='com.avon.avonon.uat:id/titleTv' and @text='IM Brochures']",
      iOS = "//XCUIElementTypeCell[4]")
  protected Text getIMBrochuresText() {
    return new Text(getLocator(this, "getIMBrochuresText"));
  }

  protected MainPromoChunk getMainPromo() {
    return DeviceChunkFactory.create(MainPromoChunk.class);
  }
}

class AndroidSocialSharingHubView extends SocialSharingHubView {
  public IMBrochuresChunk scrollToIMBrochuresChunk() {
    int swipeCount = 0;
    while (!syncHelper.isElementDisplayed(
        getLocator(
            DeviceChunkFactory.create(IMBrochuresChunk.class), "getFirstOpenIMBrochureButton"))) {
      Helper.scrollDownByCoordinates(0.7, 0.3);
      if (TestConstants.Scroll.MAX_SCROLL_COUNT == ++swipeCount) {
        LOG.info("The 'IM Brochures' component isn't displayed on screen");
        break;
      }
    }

    Helper.scrollDownByCoordinates(0.6, 0.4);
    return DeviceChunkFactory.create(IMBrochuresChunk.class);
  }

  public boolean mainPromoIsDisplayed() {
    int swipeCount = 0;
    while (!syncHelper.isElementDisplayed(
        getLocator(DeviceChunkFactory.create(MainPromoChunk.class), "getPromoDescription"))) {
      Helper.scrollDownByCoordinates(0.6, 0.4);
      if (TestConstants.Scroll.MAX_SCROLL_COUNT == ++swipeCount) {
        LOG.info("The 'IM Brochures' component isn't displayed on screen");
        break;
      }
    }
    LOG.info("Check the title and description have text displayed");
    return getMainPromo().promoDescriptionIsDisplayed() && getMainPromo().promoTitleIsDisplayed();
  }
}

class IosSocialSharingHubView extends SocialSharingHubView {
  public IMBrochuresChunk scrollToIMBrochuresChunk() {
    int count = 0;
    while (!syncHelper.isElementDisplayed(getLocator(this, "getIMBrochuresText")) && count++ < 10) {
      Helper.smallScreenScrollDown(
          TestConstants.Scroll.ONE_TIME, TestConstants.Scroll.X_COORDINATE);
    }
    Helper.smallScreenScrollDown(TestConstants.Scroll.ONE_TIME, TestConstants.Scroll.X_COORDINATE);
    return DeviceChunkFactory.create(IMBrochuresChunk.class);
  }

  public boolean mainPromoIsDisplayed() {
    LOG.info("Check the title and description have text displayed");
    return getMainPromo().promoDescriptionIsDisplayed() && getMainPromo().promoTitleIsDisplayed();
  }
}
