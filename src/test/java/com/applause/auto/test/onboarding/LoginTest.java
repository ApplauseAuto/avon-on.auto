package com.applause.auto.test.onboarding;

import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class LoginTest extends BaseTest {

  @Test(
      groups = {TestNGGroups.CATEGORY_ONBOARDING, TestNGGroups.ALL_PLATFORMS},
      description = "C1292898")
  public void testLogin() {
    LoginHelper.successfullyLogin();
  }
}
