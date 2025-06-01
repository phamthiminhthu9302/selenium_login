package com.example;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Logger;

public class ScreenshotUtil {
  static Logger logger = LoggerUtil.getLogger("ScreenshotUtil");

  public static String captureScreenshot(WebDriver driver, String name) {
    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    String destPath = "reports/screenshots/" + name + "_" + System.currentTimeMillis() + ".png";
    try {
      Files.createDirectories(new File("reports/screenshots/").toPath());
      Files.copy(srcFile.toPath(), new File(destPath).toPath());
      logger.info("Screenshot saved at: " + destPath);
    } catch (IOException e) {
      logger.warning("Failed to save screenshot: " + e.getMessage());
    }
    return destPath;
  }
}
