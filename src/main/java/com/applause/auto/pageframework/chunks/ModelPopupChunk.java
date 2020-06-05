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
import com.applause.auto.pageframework.views.LoginView;
import com.applause.auto.pageframework.views.SelectMarketView;

@AndroidImplementation(AndroidModelPoupChunk.class)
@IosImplementation(IosModelPoupChunk.class)
public abstract class ModelPopupChunk extends AbstractDeviceChunk {

  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  public ModelPopupChunk() {
    super(EnvironmentUtil.getInstance().getIsMobileAndroid() ? "dialogTitle" : "");
  }

  @Override
  protected void waitUntilVisible() {}

  /**
   * Opens Welcome Login screen
   *
   * @return
   * @return LoginView
   */
  public abstract <T> T clickOnContinue();

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/buttonPositive.*\")",
      iOS = "button_0")
  protected Button getContinueButton() {
    return new Button(getLocator(this, "getContinueButton"));
  }
}

class AndroidModelPoupChunk extends ModelPopupChunk {
  public AndroidModelPoupChunk() {
    super();
  }

  @Override
  public LoginView clickOnContinue() {
    logger.info("Press 'Continue' button");
    getContinueButton().pressButton();
    return DeviceViewFactory.create(LoginView.class);
  }
}

class IosModelPoupChunk extends ModelPopupChunk {
  public IosModelPoupChunk() {
    super();
  }

  @Override
  public SelectMarketView clickOnContinue() {
    logger.info("Press 'Continue' button");
    getContinueButton().pressButton();
    return DeviceViewFactory.create(SelectMarketView.class);
  }
}
