/*****************************************************************************
 * LogWrapper.java                    Generic class of the log tool wrappers *
 * Auteur               Julien Darthenay julien<dot>darthenay<at>free<dot>fr *
 * Developing tools      Oracle Java SDK 8, Notepad++, MS Windows Seven Home *
 * Copyright 2016 Julien Darthenay                                           *
 *****************************************************************************/

/*****************************************************************************
 * This file is part of LogWrapper                                           *
 *                                                                           *
 * LogWrapper is free software: you can redistribute it and/or modify it     *
 * under the terms of the GNU Lesser General Public License as published by  *
 * the Free Software Foundation, either version 3 of the License, or (at     *
 * your option) any later version.                                           *
 *                                                                           *
 * LogWrapper is distributed in the hope that it will be useful, but WITHOUT *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public       *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Lesser General Public License  *
 * along with LogWrapper. If not, see <http://www.gnu.org/licenses/>.        *
 *****************************************************************************/

package fr.juliendarthenay.tools.logging.wrapper;

import java.lang.reflect.Method;

/**
 * Automatically select a log tool for logging.
 * If Log4j2 is available, use it.
 * If no other log tool is available, print logs on the console.
 */
public abstract class LogWrapper extends Object {
  private static final String LOG_MANAGER_QUALIFIED_NAME = "org.apache.logging.log4j.LogManager";
  private static final String METHOD_GET_LOGGER = "getLogger";

  private static boolean log4j2Trouve = false;
  private static Method methodGetLogger = null;

  /**
   * Select the log wrapper to use for logging.
   * If Log4j2 is available, use Log4j2 wrapper.
   * If no other log tool is available, use System.out wrapper.
   */
  static {
    ClassLoader classLoader = LogWrapper.class.getClassLoader();

    try {
      Class<?> classLogManager = classLoader.loadClass(LOG_MANAGER_QUALIFIED_NAME);
      methodGetLogger = classLogManager.getMethod(METHOD_GET_LOGGER, Class.class);

      log4j2Trouve = true;
    } catch (ClassNotFoundException e) {
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
  }

  /**
   * Constructor. Not intended to be used except by subclasses constructors.
   */
  LogWrapper() {
    super();
  }

  /**
   * Give the getLogger method of the log manager to be used.
   * @return getLogger method
   */
  static Method getMethodGetLogger() {
    return methodGetLogger;
  }

  /**
   * Create a log wrapper for the given class.
   * @return log wrapper
   */
  public static LogWrapper getLogger(Class<?> clazz) {
    if (log4j2Trouve) {
      return new Log4j2Wrapper(clazz);
    } else {
      return new LogConsoleWrapper(clazz);
    }
  }

  /**
   * Print a fatal level message.
   * @param message message to log
   */
  abstract public void fatal(String message);

  /**
   * Print an error level message.
   * @param message message to log
   */
  abstract public void error(String message);

  /**
   * Print a warn level message.
   * @param message message to log
   */
  abstract public void warn(String message);

  /**
   * Print an info level message.
   * @param message message to log
   */
  abstract public void info(String message);

  /**
   * Print a debug level message.
   * @param message message to log
   */
  abstract public void debug(String message);

  /**
   * Print a trace level message.
   * @param message message to log
   */
  abstract public void trace(String message);

}
