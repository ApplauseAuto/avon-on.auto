package com.applause.auto.pageframework.chunks;

import java.lang.invoke.MethodHandles;

import com.applause.auto.framework.pageframework.device.AbstractDeviceChunk;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.framework.pageframework.util.environment.EnvironmentUtil;
import com.applause.auto.framework.pageframework.util.logger.LogController;

@AndroidImplementation(AndroidAvonDialogChunk.class)
@IosImplementation(IosAvonDialogChunk.class)
public abstract class AvonDialogChunk extends AbstractDeviceChunk {

  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  public AvonDialogChunk() {
    super(EnvironmentUtil.getInstance().getIsMobileAndroid() ? "dialogTitle" : "");
  }

  @Override
  protected void waitUntilVisible() {}

  /** Taps 'Accept' button */
  public void tapAcceptButton() {
    logger.info("Tapping 'Accept' button");
    getAcceptButton().pressButton();
  }

  /** Taps 'Cancel' button */
  public void tapCancelButton() {
    logger.info("Tapping 'Cancel' button");
    getCancelButton().pressButton();
  }

  /** Taps 'OK' button */
  public void tapOkButton() {
    logger.info("Tapping 'OK' button.");
    okButton().tap();
  }

  @MobileElementLocator(android = "dialogMessage", iOS = "")
  protected Text getDescText() {
    return new Text(getLocator(this, "getDescText"));
  }

  @MobileElementLocator(android = "buttonPositive", iOS = "**/XCUIElementTypeButton[2]")
  protected Button getAcceptButton() {
    return new Button(getLocator(this, "getAcceptButton"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/buttonNegative.*\")",
      iOS = "button_1")
  protected Button getCancelButton() {
    return new Button(getLocator(this, "getCancelButton"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/buttonPositive.*\")",
      iOS = "button_0")
  protected Button okButton() {
    return new Button(getLocator(this, "okButton"));
  }
}

class AndroidAvonDialogChunk extends AvonDialogChunk {

  public AndroidAvonDialogChunk() {
    super();
  }
}

class IosAvonDialogChunk extends AvonDialogChunk {

  public IosAvonDialogChunk() {
    super();
  }
}
