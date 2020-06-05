package com.applause.auto.pageframework.views;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.framework.pageframework.util.logger.LogController;

@AndroidImplementation(AndroidFAQView.class)
@IosImplementation(IosFAQView.class)
public abstract class FAQView extends AbstractDeviceView {

  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "getDescriptionElement"));
  }

  /**
   * Checking for empty category items
   *
   * @return boolean
   */
  public boolean hasListEmptyCategories() {
    logger.info("Checking for empty category items");
    boolean hasEmptyCategory = false;

    for (Text category : getCategoriesList()) {
      if (category.getStringValue().isEmpty()) {
        hasEmptyCategory = true;
        break;
      }
    }
    return hasEmptyCategory;
  }

  /**
   * Press 'My Account1' text element
   *
   * @return MyAccountView
   */
  public MyAccountView openMyAccount() {
    logger.info("Press close button");
    getMyAccountTextElement().clickText();
    return DeviceViewFactory.create(MyAccountView.class);
  }

  /**
   * Returns list of categories
   *
   * @return List<Text>
   */
  abstract List<Text> getCategoriesList();

  @MobileElementLocator(
      android = "faqItemDescriptionTv",
      iOS = "**/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]")
  protected Text getDescriptionElement() {
    return new Text(getLocator(this, "getDescriptionElement"));
  }

  @MobileElementLocator(
      android = "//android.view.ViewGroup[2]/android.widget.TextView",
      iOS = "**/XCUIElementTypeCell[$name=\"faqCell index 1\"$]/XCUIElementTypeStaticText")
  protected Text getMyAccountTextElement() {
    return new Text(getLocator(this, "getMyAccountTextElement"));
  }
}

class AndroidFAQView extends FAQView {
  public List<Text> getCategoriesList() {
    String locator = "//android.view.ViewGroup/android.widget.TextView";
    return IntStream.range(2, queryHelper.getMobileElementCount(locator) - 1)
        .mapToObj(
            value ->
                new Text(
                    String.format(
                        "//android.view.ViewGroup" + "[%s]" + "/android.widget.TextView", value)))
        .collect(Collectors.toList());
  }
}

class IosFAQView extends FAQView {

  @Override
  public List<Text> getCategoriesList() {
    String locator = "**/XCUIElementTypeCell[$name contains 'faqCell index'$]";
    return IntStream.range(0, queryHelper.getMobileElementCount(locator))
        .mapToObj(
            value ->
                new Text(
                    String.format(
                        "**/XCUIElementTypeCell[$name='faqCell index "
                            + "%s"
                            + "'$]/XCUIElementTypeStaticText",
                        value)))
        .collect(Collectors.toList());
  }
}
