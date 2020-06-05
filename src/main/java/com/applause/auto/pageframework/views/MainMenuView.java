package com.applause.auto.pageframework.views;

import java.lang.invoke.MethodHandles;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.DeviceChunkFactory;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.framework.pageframework.devicecontrols.ScrollView;
import com.applause.auto.framework.pageframework.util.logger.LogController;
import com.applause.auto.pageframework.chunks.ModelPopupChunk;
import com.applause.auto.pageframework.utils.Helper;
import com.applause.auto.pageframework.views.socialsharinghub.SocialSharingHubView;

@AndroidImplementation(AndroidMainMenuView.class)
@IosImplementation(IosMainMenuView.class)
public abstract class MainMenuView extends AbstractDeviceView {

  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "closeButton"));
  }

  /**
   * Opens Setting menu
   *
   * @return SettingView
   */
  public SettingsView openSettings() {
    Helper.scrollDownToElement(getLocator(this, "settingButton"), 3);
    settingButton().tap();
    return DeviceViewFactory.create(SettingsView.class);
  }

  /**
   * Opens Logging Out popup
   *
   * @return MainMenuView
   */
  public ModelPopupChunk clickOnLogout() {
    logger.info("Press 'Log Out' button");
    Helper.scrollDownToElement(getLocator(this, "logoutButton"), 3);
    logoutButton().tap();
    return DeviceChunkFactory.create(ModelPopupChunk.class);
  }

  /**
   * Open Place An Order
   *
   * @return PlaceAnOrderView
   */
  public PlaceAnOrderView tapPlaceAnOrder() {
    logger.info("Tapping 'Place an Order' button");
    Helper.scrollDownToElement(getLocator(this, "placeAnOrderButton"), 3);
    placeAnOrderButton().tap();
    return DeviceViewFactory.create(PlaceAnOrderView.class);
  }

  /**
   * Opens Help & Support menu
   *
   * @return HelpAndSupportView
   */
  public HelpAndSupportView openHelpAndSupport() {
    logger.info("Tapping 'Help & Support' button");
    Helper.scrollDownToElement(getLocator(this, "helpAndSupportButton"), 3);
    helpAndSupportButton().pressButton();
    return DeviceViewFactory.create(HelpAndSupportView.class);
  }

  /**
   * Opens Notifications menu
   *
   * @return NotificationsView
   */
  public NotificationsView openNotifications() {
    logger.info("Tapping 'Notifications' button");
    Helper.scrollDownToElement(getLocator(this, "notificationsButton"), 3);
    notificationsButton().pressButton();
    return DeviceViewFactory.create(NotificationsView.class);
  }

  /**
   * Opens 'Social Media Hub' menu
   *
   * @return SocialMediaHubView
   */
  public SocialSharingHubView openSocialMediaHub() {
    logger.info("Tapping 'Social Media Hub' button");
    Helper.scrollDownToElement(getLocator(this, "socialMediaHubButton"), 3);
    socialMediaHubButton().pressButton();
    return DeviceViewFactory.create(SocialSharingHubView.class);
  }

  /**
   * Opens 'Share Your Look' menu
   *
   * @return ShareYourLookView
   */
  public ShareYourLookView openShareYourLook() {
    logger.info("Tapping 'Share Your Look' button");
    Helper.scrollDownToElement(getLocator(this, "shareYourLookButton"), 3);
    shareYourLookButton().pressButton();
    return DeviceViewFactory.create(ShareYourLookView.class);
  }

  /**
   * Opens 'Learning Hub' menu
   *
   * @return LearningHubView
   */
  public LearningHubView openLearningHub() {
    logger.info("Tapping 'Learning Hub' button");
    Helper.scrollDownToElement(getLocator(this, "learningHubButton"), 3);
    learningHubButton().pressButton();
    return DeviceViewFactory.create(LearningHubView.class);
  }

  /**
   * Tap 'Close' button
   *
   * @return DashboardView
   */
  public DashboardView tapCloseButton() {
    logger.info("Tapping 'Close' button.");
    closeButton().tap();
    return DeviceViewFactory.create(DashboardView.class);
  }

  /**
   * Tap 'Home' button
   *
   * @return SettingView
   */
  public DashboardView tapHomeButton() {
    logger.info("Tapping 'Home' button.");
    homeButton().tap();
    return DeviceViewFactory.create(DashboardView.class);
  }

  /**
   * Verifies 'Burger Menu' exists
   *
   * @return boolean
   */
  public boolean burgerMenuIsDisplayed() {
    return syncHelper.isElementDisplayed(getLocator(this, "burgerMenuButton"));
  }

  /**
   * Verifies 'User Full Name' exists
   *
   * @return boolean
   */
  public boolean userFullNameIsDisplayed() {
    return syncHelper.isElementDisplayed(getLocator(this, "userFullNameText"))
        && !userFullNameText().getButtonString().isEmpty();
  }

  /**
   * Verifies 'Deadline for ordering products' exists
   *
   * @return boolean
   */
  public boolean deadlineOfOrderingProductsIsDisplayed() {
    return syncHelper.isElementDisplayed(getLocator(this, "deadlineOfOrderingText"))
        && !deadlineOfOrderingText().getButtonString().isEmpty();
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/drawerCloseBtn.*\")",
      iOS = "closeMenu")
  protected Button closeButton() {
    return new Button(getLocator(this, "closeButton"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/drawerDashboardLinear.*\")",
      iOS = "MenuOptionCell_home")
  protected Button homeButton() {
    return new Button(getLocator(this, "homeButton"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/drawerOrderLinear.*\")",
      iOS = "MenuOptionCell_placeOrder")
  protected Button placeAnOrderButton() {
    return new Button(getLocator(this, "placeAnOrderButton"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/drawerSettingsBtn.*\")",
      iOS = "MenuOptionCell_settings")
  protected Button settingButton() {
    return new Button(getLocator(this, "settingButton"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/helpSupportTv.*\")",
      iOS = "MenuOptionCell_helpSupport")
  protected Button helpAndSupportButton() {
    return new Button(getLocator(this, "helpAndSupportButton"));
  }

  @MobileElementLocator(
      android = "drawerSocialSharingLinear",
      iOS = "MenuOptionCell_socialMediaHub")
  protected Button socialMediaHubButton() {
    return new Button(getLocator(this, "socialMediaHubButton"));
  }

  @MobileElementLocator(android = "drawerNotificationsTv", iOS = "MenuOptionCell_notifications")
  protected Button notificationsButton() {
    return new Button(getLocator(this, "notificationsButton"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/drawerLogoutLinear.*\")",
      iOS = "MenuOptionCell_logout")
  protected Button logoutButton() {
    return new Button(getLocator(this, "logoutButton"));
  }

  @MobileElementLocator(
      android = "drawerTitleTv",
      iOS =
          "//XCUIElementTypeButton[@name='closeMenu']/following-sibling::XCUIElementTypeStaticText[position()=1]")
  protected Button userFullNameText() {
    return new Button(getLocator(this, "userFullNameText"));
  }

  @MobileElementLocator(
      android = "drawerDaysLeftTv",
      iOS =
          "//XCUIElementTypeButton[@name='closeMenu']/following-sibling::XCUIElementTypeStaticText[position()=3]")
  protected Button deadlineOfOrderingText() {
    return new Button(getLocator(this, "deadlineOfOrderingText"));
  }

  @MobileElementLocator(
      android = "mainDrawerContent",
      iOS = "//XCUIElementTypeButton[@name='closeMenu']/parent::XCUIElementTypeTable")
  protected ScrollView burgerMenuButton() {
    return new ScrollView(getLocator(this, "burgerMenuButton"));
  }

  @MobileElementLocator(android = "theLearningHub", iOS = "MenuOptionCell_learningHub")
  protected Button learningHubButton() {
    return new Button(getLocator(this, "learningHubButton"));
  }

  @MobileElementLocator(android = "userGeneratedContent", iOS = "MenuOptionCell_share")
  protected Button shareYourLookButton() {
    return new Button(getLocator(this, "shareYourLookButton"));
  }
}

class AndroidMainMenuView extends MainMenuView {}

class IosMainMenuView extends MainMenuView {}
