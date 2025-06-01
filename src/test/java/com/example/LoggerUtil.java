package com.example;

import java.util.logging.*;

public class LoggerUtil {
  public static Logger getLogger(String name) {
    Logger logger = Logger.getLogger(name);
    logger.setUseParentHandlers(false); // Ngăn log in 2 lần
    if (logger.getHandlers().length == 0) {
      ConsoleHandler handler = new ConsoleHandler();
      handler.setFormatter(new SimpleFormatter());
      logger.addHandler(handler);
    }
    logger.setLevel(Level.INFO);
    return logger;
  }
}
