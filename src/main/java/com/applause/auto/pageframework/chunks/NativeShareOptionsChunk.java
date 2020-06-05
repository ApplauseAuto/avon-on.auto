package com.applause.auto.pageframework.chunks;

import java.lang.invoke.MethodHandles;

import com.applause.auto.framework.pageframework.device.AbstractDeviceChunk;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.framework.pageframework.devicecontrols.ScrollView;
import com.applause.auto.framework.pageframework.util.environment.EnvironmentUtil;
import com.applause.auto.framework.pageframework.util.logger.LogController;
import com.applause.auto.pageframework.views.socialsharinghub.SocialSharingHubView;

@AndroidImplementation(AndroidNativeShareOptionsChunk.class)
@IosImplementation(IosNativeShareOptionsChunk.class)
public abstract class NativeShareOptionsChunk extends AbstractDeviceChunk {

  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  public NativeShareOptionsChunk() {
    super(EnvironmentUtil.getInstance().getIsMobileAndroid() ? "" : "");
  }

  /**
   * Verifies that 'Share options' view exists
   *
   * @return boolean
   */
  public boolean shareOptionsDisplayed() {
    return syncHelper.isElementDisplayed(getLocator(this, "getShareOptionsScrollView"));
  }

  /**
   * Tep 'Cancel' button
   *
   * @return SocialSharingHubView
   */
  public abstract SocialSharingHubView tapCloseButton();

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getShareOptionsScrollView());
  }

  @MobileElementLocator(
      android =
          "//*[@resource-id='android:id/resolver_list'] | //*[@resource-id='android:id/resolver_page']",
      iOS = "ActivityListView")
  protected ScrollView getShareOptionsScrollView() {
    return new ScrollView(getLocator(this, "getShareOptionsScrollView"));
  }

  @MobileElementLocator(
      android = "",
      iOS =
          "//XCUIElementTypeOther[@name=\"ActivityListView\"]/following-sibling::XCUIElementTypeOther/XCUIElementTypeButton")
  protected Button getCancelButton() {
    return new Button(getLocator(this, "getCancelButton"));
  }
}

class AndroidNativeShareOptionsChunk extends NativeShareOptionsChunk {

  public AndroidNativeShareOptionsChunk() {
    super();
  }

  @Override
  public SocialSharingHubView tapCloseButton() {
    logger.info("Tapping 'Back' button.");
    getDeviceControl().pressBackButton();
    return DeviceViewFactory.create(SocialSharingHubView.class);
  }
}

class IosNativeShareOptionsChunk extends NativeShareOptionsChunk {

  public IosNativeShareOptionsChunk() {
    super();
  }

  @Override
  public SocialSharingHubView tapCloseButton() {
    logger.info("Tapping 'Cancel' button.");
    getCancelButton().pressButton();
    return DeviceViewFactory.create(SocialSharingHubView.class);
  }
}
