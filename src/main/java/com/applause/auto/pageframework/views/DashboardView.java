package com.applause.auto.pageframework.views;

import static com.applause.auto.pageframework.testdata.TestConstants.Timeouts.THREE_SECONDS_MILLI;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.DeviceChunkFactory;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.framework.pageframework.devicecontrols.Image;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.pageframework.chunks.MainPromoChunk;
import com.applause.auto.pageframework.chunks.ShortcutMenuChunk;
import com.applause.auto.pageframework.testdata.Market;
import com.applause.auto.pageframework.utils.LogHelper;

@AndroidImplementation(AndroidDashboardView.class)
@IosImplementation(IosDashboardView.class)
public abstract class DashboardView extends AbstractDeviceView {

  protected static final LogHelper LOG = LogHelper.getInstance();

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "campaignNameElement"));
  }

  /** @return true, if dashboard title is not displayed or populated */
  public boolean dashboardTitleIsDisplayed() {
    if (!syncHelper.isElementDisplayed(
        getLocator(this, "dashboardTitleElement"), THREE_SECONDS_MILLI)) {
      LOG.error("Dashboard title element is not displayed.");
      return false;
    }

    if (getDashboardTitle().isEmpty()) {
      LOG.error("Dashboard title is not populated.");
      return false;
    }

    return true;
  }

  /**
   * Return the string in the dashboard title element
   *
   * @return String
   */
  public String getDashboardTitle() {
    return dashboardTitleElement().getStringValue().trim();
  }

  /** @return name of user connected to account */
  public String getUserName() {
    if (Market.getMarketName().equals("Greece")) {
      int word = getDashboardTitle().split("\\s+").length;
      return getDashboardTitle().split("\\s+")[word - 1].trim();
    } else {
      return getDashboardTitle().split("\\,")[1].trim();
    }
  }

  /** @return Campaign name */
  public String getCampaignName() {
    return campaignNameElement().getStringValue().trim();
  }

  /** @return Number of days left text */
  public abstract String getNumberOfDaysLeftText();

  /** @return Deadline text */
  public String getDeadlineText() {
    return deadlineTextElement().getStringValue().trim();
  }

  /**
   * Opens main menu
   *
   * @return MainMenuView
   */
  public MainMenuView openMainMenu() {
    LOG.info("Tapping hamburger icon to open menu.");
    menuButton().tap();
    return DeviceViewFactory.create(MainMenuView.class);
  }

  /**
   * Opens Shortcut menu
   *
   * @return ShortcutMenuChunk
   */
  public ShortcutMenuChunk openShortcutMenu() {
    LOG.info("Tapping Shortcut menu icon.");
    shorcutIcon().tap();
    return DeviceChunkFactory.create(ShortcutMenuChunk.class);
  }

  /** @return true, if notifications icon is displayed */
  public boolean notificationsIconIsDisplayed() {
    return syncHelper.isElementDisplayed(getLocator(this, "notificationsIcon"));
  }

  /** @return true, if notifications counter is displayed */
  public boolean numberOfUnreadNotificationsIsDisplayed() {
    return syncHelper.isElementDisplayed(getLocator(this, "numberOfUnreadNotificationsText"));
  }

  /** @return true, if burger icon is displayed */
  public boolean burgerIconIsDisplayed() {
    return syncHelper.isElementDisplayed(getLocator(this, "menuButton"));
  }

  /**
   * Gets the main promo from the dashboard
   *
   * @return MainPromoChunk
   */
  public MainPromoChunk getMainPromo() {
    return DeviceChunkFactory.create(MainPromoChunk.class);
  }

  @MobileElementLocator(android = "//android.widget.ImageButton[1]", iOS = "hamburgerMenu")
  protected Button menuButton() {
    return new Button(getLocator(this, "menuButton"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/dashboardTitleTv.*\")",
      iOS = "welcomeLabel")
  protected Text dashboardTitleElement() {
    return new Text(getLocator(this, "dashboardTitleElement"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/dashboardCampaignTv.*\")",
      iOS = "campaignLabel")
  private Text campaignNameElement() {
    return new Text(getLocator(this, "campaignNameElement"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/dashboardOrderUntilTv.*\")",
      iOS = "orderUntilLabel")
  private Text deadlineTextElement() {
    return new Text(getLocator(this, "deadlineTextElement"));
  }

  @MobileElementLocator(
      android =
          "//*[@resource-id='com.avon.avonon.uat:id/action_notification']/android.widget.ImageView",
      iOS = "bell")
  protected Image notificationsIcon() {
    return new Image(getLocator(this, "notificationsIcon"));
  }

  @MobileElementLocator(android = "notBadgeTv")
  protected Text numberOfUnreadNotificationsText() {
    return new Text(getLocator(this, "numberOfUnreadNotificationsText"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/fab.*\")",
      iOS = "fabMenuOpen")
  protected Button shorcutIcon() {
    return new Button(getLocator(this, "shorcutIcon"));
  }
}

class AndroidDashboardView extends DashboardView {

  public String getNumberOfDaysLeftText() {
    return getDeadlineText().split("\\|")[1].trim();
  }
}

class IosDashboardView extends DashboardView {

  public String getNumberOfDaysLeftText() {
    return getDeadlineText().split("\\ı")[1].trim();
  }
}
