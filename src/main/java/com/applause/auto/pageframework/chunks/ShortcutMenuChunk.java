package com.applause.auto.pageframework.chunks;

import java.lang.invoke.MethodHandles;

import com.applause.auto.framework.pageframework.device.AbstractDeviceChunk;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.framework.pageframework.util.environment.EnvironmentUtil;
import com.applause.auto.framework.pageframework.util.logger.LogController;
import com.applause.auto.pageframework.views.PlaceAnOrderView;
import com.applause.auto.pageframework.views.ShareYourLookView;
import com.applause.auto.pageframework.views.socialsharinghub.SocialSharingHubView;

@AndroidImplementation(AndroidShortcutMenuChunk.class)
@IosImplementation(IosShortcutMenuChunk.class)
public abstract class ShortcutMenuChunk extends AbstractDeviceChunk {

  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  public ShortcutMenuChunk() {
    super(EnvironmentUtil.getInstance().getIsMobileAndroid() ? "" : "");
  }

  @Override
  protected void waitUntilVisible() {}

  /**
   * Click on 'Social media hub' button
   *
   * @return SocialSharingHubView
   */
  public SocialSharingHubView tapSocialMediaHubButton() {
    logger.info("Tapping 'Social media hub' option");
    socialMediaHubButton().tap();
    return DeviceViewFactory.create(SocialSharingHubView.class);
  }

  /**
   * Click on 'Place an Order' button
   *
   * @return PlaceAnOrderView
   */
  public PlaceAnOrderView tapPlaceAnOrderButton() {
    logger.info("Tapping 'Add An order' option");
    placeAnOrderButton().tap();
    return DeviceViewFactory.create(PlaceAnOrderView.class);
  }

  /**
   * Click on 'Share your look' button
   *
   * @return ShareYourLookView
   */
  public ShareYourLookView tapShareYourLookButton() {
    logger.info("Tapping 'Share your look' option");
    shareYourLookButton().tap();
    return DeviceViewFactory.create(ShareYourLookView.class);
  }

  /**
   * Checks the Place an Order option is displayed
   *
   * @return boolean
   */
  public boolean placeAnOrderOptionIsDisplayed() {
    return syncHelper.isElementDisplayed(getLocator(this, "placeAnOrderButton"));
  }

  /**
   * Checks the Social Media hub option is displayed
   *
   * @return boolean
   */
  public boolean socialMediaHubOptionIsDisplayed() {
    return syncHelper.isElementDisplayed(getLocator(this, "socialMediaHubButton"));
  }

  /**
   * Checks the Share your look option is displayed
   *
   * @return boolean
   */
  public boolean shareYourLookOptionIsDisplayed() {
    return syncHelper.isElementDisplayed(getLocator(this, "shareYourLookButton"));
  }

  @MobileElementLocator(
      android = "(//android.widget.ImageButton[contains(@resource-id, 'id/mini_fab')])[1]",
      iOS = "placeAnOrder")
  private Button placeAnOrderButton() {
    return new Button(getLocator(this, "placeAnOrderButton"));
  }

  @MobileElementLocator(
      android = "(//android.widget.ImageButton[contains(@resource-id, 'id/mini_fab')])[2]",
      iOS = "shareIcon")
  private Button socialMediaHubButton() {
    return new Button(getLocator(this, "socialMediaHubButton"));
  }

  @MobileElementLocator(
      android = "(//android.widget.ImageButton[contains(@resource-id, 'id/mini_fab')])[3]",
      iOS = "camera")
  private Button shareYourLookButton() {
    return new Button(getLocator(this, "shareYourLookButton"));
  }
}

class AndroidShortcutMenuChunk extends ShortcutMenuChunk {
  public AndroidShortcutMenuChunk() {
    super();
  }
}

class IosShortcutMenuChunk extends ShortcutMenuChunk {

  public IosShortcutMenuChunk() {
    super();
  }
}
