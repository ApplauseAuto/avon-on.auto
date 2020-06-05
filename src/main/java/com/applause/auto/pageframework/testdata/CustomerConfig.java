package com.applause.auto.pageframework.testdata;

import java.lang.invoke.MethodHandles;

import com.applause.auto.framework.pageframework.util.environment.EnvironmentUtil;
import com.applause.auto.framework.pageframework.util.environment.s3.S3Utility;
import com.applause.auto.framework.pageframework.util.logger.LogController;

/**
 * This class sets up all relevant customer configuration for reporting to the Applause Automation
 * Dashboard.
 */
public class CustomerConfig {

  private static final LogController LOGGER = new LogController(MethodHandles.lookup().getClass());
  private static EnvironmentUtil env = EnvironmentUtil.getInstance();
  private static S3Utility s3;

  private static String productIdIOS = "16504";
  private static String productIdAndroid = "16505";
  private static String buildIdIOS = "108001";
  private static String buildIdAndroid = "108002";
  private static String idCompany = "4333";

  static {
    s3 = S3Utility.getInstance();

    env.setProductIOSId(productIdIOS);
    env.setProductAndroidId(productIdAndroid);

    env.setCustomerS3BucketName(idCompany);
    env.setCompanyId(idCompany);

    if (!s3.doesCustomerBucketExist()) {
      s3.createCustomerBucket();
    }

    if (env.isMobileIOSTest()) {
      env.setBuildId(buildIdIOS);
    } else {
      env.setBuildId(buildIdAndroid);
    }

    LOGGER.info("Customer configuration complete.");
  }
}
