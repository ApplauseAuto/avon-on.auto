package com.applause.auto.pageframework.webviews;

import java.lang.invoke.MethodHandles;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.DeviceChunkFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.framework.pageframework.util.logger.LogController;
import com.applause.auto.pageframework.chunks.IMBrochuresChunk;

@AndroidImplementation(AndroidIMBrochureWebView.class)
@IosImplementation(IosIMBrochureWebView.class)
public class IMBrochureWebView extends AbstractDeviceView {

  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  /**
   * Tap 'Close' button
   *
   * @return SocialSharingHubView
   */
  public IMBrochuresChunk closeBrochureWebView() {
    logger.info("Tapping 'Close' button.");
    getCloseButton().pressButton();
    return DeviceChunkFactory.create(IMBrochuresChunk.class);
  }

  @Override
  protected void waitUntilVisible() {}

  @MobileElementLocator(android = "//android.widget.ImageButton", iOS = "close")
  protected Button getCloseButton() {
    return new Button(getLocator(this, "getCloseButton"));
  }
}

class AndroidIMBrochureWebView extends IMBrochureWebView {}

class IosIMBrochureWebView extends IMBrochureWebView {}
