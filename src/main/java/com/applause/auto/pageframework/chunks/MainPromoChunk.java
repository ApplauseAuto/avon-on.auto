package com.applause.auto.pageframework.chunks;

import java.lang.invoke.MethodHandles;

import com.applause.auto.framework.pageframework.device.AbstractDeviceChunk;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.framework.pageframework.util.environment.EnvironmentUtil;
import com.applause.auto.framework.pageframework.util.logger.LogController;
import com.applause.auto.pageframework.testdata.TestConstants;
import com.applause.auto.pageframework.testdata.TestConstants.Timeouts;
import com.applause.auto.pageframework.utils.Helper;
import com.applause.auto.pageframework.views.socialsharinghub.SocialSharingHubView;

@AndroidImplementation(AndroidMainPromoChunk.class)
@IosImplementation(IosMainPromoChunk.class)
public abstract class MainPromoChunk extends AbstractDeviceChunk {

  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  public MainPromoChunk() {
    super(EnvironmentUtil.getInstance().getIsMobileAndroid() ? "higlightView" : "");
  }

  @Override
  protected void waitUntilVisible() {}

  /** Tap 'Share' button and see if sharing options appear */
  public abstract boolean promoCanBeShared();

  /**
   * Checks the title displays
   *
   * @return boolean
   */
  public boolean promoTitleIsDisplayed() {
    return syncHelper.isElementDisplayed(getLocator(this, "getPromoTitle"));
  }

  /**
   * Checks the description displays
   *
   * @return boolean
   */
  public boolean promoDescriptionIsDisplayed() {
    return syncHelper.isElementDisplayed(getLocator(this, "getPromoDescription"));
  }

  /**
   * Select to view the promo
   *
   * @return SocialSharingHubView
   */
  public abstract SocialSharingHubView viewPromo();

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/shareBtn.*\")",
      iOS = "shareButton")
  protected Button getShareButton() {
    return new Button(getLocator(this, "getShareButton"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/titleTv.*\")",
      iOS = "titleLabel")
  protected Text getPromoTitle() {
    return new Text(getLocator(this, "getPromoTitle"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/contentTv.*\")",
      iOS = "subheaderLabel")
  protected Text getPromoDescription() {
    return new Text(getLocator(this, "getPromoDescription"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/goToSocialHubBtn.*\")",
      iOS = "viewButton")
  protected Button getViewPromoButton() {
    return new Button(getLocator(this, "getViewPromoButton"));
  }
}

class AndroidMainPromoChunk extends MainPromoChunk {

  private static final String ANDROID_SHARE_PANEL =
      "//*[@resource-id='android:id/resolver_list'] | //*[@resource-id='android:id/resolver_page']";

  public AndroidMainPromoChunk() {
    super();
  }

  @Override
  public boolean promoCanBeShared() {
    logger.info("Tap share button button");
    Helper.scrollDownToElementCloseToMiddle(getLocator(this, "getShareButton"), 3);
    getShareButton().pressButton();

    logger.info("check sharing menu appears");
    syncHelper.suspend(Timeouts.TWO_SECONDS_MILLI);
    return getQueryHelper().doesElementExist(ANDROID_SHARE_PANEL);
  }

  @Override
  public SocialSharingHubView viewPromo() {
    Helper.scrollToBottom(TestConstants.Scroll.ONE_TIME);
    logger.info("Tap view promo button");
    getViewPromoButton().pressButton();
    return DeviceViewFactory.create(SocialSharingHubView.class);
  }
}

class IosMainPromoChunk extends MainPromoChunk {

  private static final String IOS_SHARE_PANEL = "ActivityListView";

  public IosMainPromoChunk() {
    super();
  }

  @Override
  public boolean promoCanBeShared() {
    logger.info("Tap share button button");
    Helper.scrollDownToElement(getLocator(this, "getShareButton"), 1);
    getShareButton().pressButton();

    logger.info("check sharing menu appears");
    syncHelper.suspend(Timeouts.TWO_SECONDS_MILLI);
    return getQueryHelper().doesElementExist(IOS_SHARE_PANEL);
  }

  @Override
  public SocialSharingHubView viewPromo() {
    logger.info("Tap view promo button");
    Helper.scrollDownToElement(getLocator(this, "getViewPromoButton"), 1);
    getViewPromoButton().pressButton();
    return DeviceViewFactory.create(SocialSharingHubView.class);
  }
}
