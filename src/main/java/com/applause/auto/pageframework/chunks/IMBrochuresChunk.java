package com.applause.auto.pageframework.chunks;

import java.lang.invoke.MethodHandles;

import com.applause.auto.framework.pageframework.device.AbstractDeviceChunk;
import com.applause.auto.framework.pageframework.device.DeviceChunkFactory;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.framework.pageframework.util.environment.EnvironmentUtil;
import com.applause.auto.framework.pageframework.util.logger.LogController;
import com.applause.auto.pageframework.testdata.TestConstants;
import com.applause.auto.pageframework.utils.Helper;
import com.applause.auto.pageframework.webviews.IMBrochureWebView;

@AndroidImplementation(AndroidIMBrochuresChunk.class)
@IosImplementation(IOSIMBrochuresChunk.class)
public abstract class IMBrochuresChunk extends AbstractDeviceChunk {

  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  public IMBrochuresChunk() {
    super(EnvironmentUtil.getInstance().getIsMobileAndroid() ? "" : "");
  }

  /**
   * Return count of brochures in 'IM Brochures' section
   *
   * @return int
   */
  public int getTotalAmountOfBrochures() {
    return Integer.parseInt(brochuresIndicatorText().getStringValue().split("/")[1].trim());
  }

  /**
   * Return position of currently focused brochure in 'IM Brochures' section
   *
   * @return int
   */
  public int getInitialBrochureNumber() {
    return Integer.parseInt(brochuresIndicatorText().getStringValue().split("/")[0].trim());
  }

  /**
   * Tap first 'Open IM Brochure' button
   *
   * @return IMBrochureWebView
   */
  public IMBrochureWebView tapFirstOpenIMBrochureButton() {
    logger.info("Tapping 'Open IM Brochure' button.");
    Helper.scrollDownToElement(getLocator(this, "firstOpenIMBrochureButton"), 2);
    firstOpenIMBrochureButton().pressButton();
    return DeviceViewFactory.create(IMBrochureWebView.class);
  }

  /**
   * Tap first 'Share me' button
   *
   * @return NativeShareOptionsChunk
   */
  public NativeShareOptionsChunk tapFirstShareMeButton() {
    logger.info("Tapping first 'Share me' button.");
    Helper.scrollDownToElement(getLocator(this, "firstShareMeButton"), 1);
    firstShareMeButton().pressButton();
    return DeviceChunkFactory.create(NativeShareOptionsChunk.class);
  }

  /** Swipes all available brochures from left to right */
  public void swipeBrochures() {
    swipeBrochuresLeft();
    swipeBrochuresRight();
    logger.info("Swiping through 'IM Brochures' is complete");
  }

  private void swipeBrochuresLeft() {
    int currentBrochureNumber = getInitialBrochureNumber();
    int totalBrochureAmount = getTotalAmountOfBrochures();
    if (TestConstants.Scroll.INITIAL_BROCHURE_ICON_NUMBER == totalBrochureAmount) {
      logger.info("Only one 'IM Brochure' is present");
      return;
    }
    while (currentBrochureNumber < totalBrochureAmount) {
      if (env.getIsMobileAndroid()) {
        Helper.androidSwipeLeft();
      } else {
        Helper.swipeLeft();
      }
      currentBrochureNumber++;
    }
  }

  private void swipeBrochuresRight() {
    int currentBrochureNumber = getInitialBrochureNumber();
    int totalBrochureAmount = getTotalAmountOfBrochures();
    if (TestConstants.Scroll.INITIAL_BROCHURE_ICON_NUMBER == totalBrochureAmount) {
      logger.info("Only one 'IM Brochure' is present");
      return;
    }
    while (currentBrochureNumber > TestConstants.Scroll.INITIAL_BROCHURE_ICON_NUMBER) {
      if (env.getIsMobileAndroid()) {
        Helper.androidSwipeRight();
      } else {
        Helper.swipeRight();
      }
      currentBrochureNumber--;
    }
  }

  @Override
  protected void waitUntilVisible() {}

  @MobileElementLocator(
      android = "(//android.widget.Button[@resource-id='com.avon.avonon.uat:id/shareMeBtn'])[1]",
      iOS = "SocialHubBrochureAndLipCell_shareButton")
  protected Button firstShareMeButton() {
    return new Button(getLocator(this, "firstShareMeButton"));
  }

  @MobileElementLocator(android = "queueOrderTv", iOS = "//XCUIElementTypeCell[4]/*[last()]")
  protected Text brochuresIndicatorText() {
    return new Text(getLocator(this, "brochuresIndicatorText"));
  }

  @MobileElementLocator(
      android = "(//android.widget.Button[@resource-id='com.avon.avonon.uat:id/openBtn'])[1]",
      iOS = "//XCUIElementTypeButton[@name=\"SocialHubBrochureAndLipCell_openButton\"][1]")
  protected Button firstOpenIMBrochureButton() {
    return new Button(getLocator(this, "firstOpenIMBrochureButton"));
  }
}

class AndroidIMBrochuresChunk extends IMBrochuresChunk {}

class IOSIMBrochuresChunk extends IMBrochuresChunk {}
