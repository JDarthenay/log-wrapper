/*****************************************************************************
 * LogManager.java                   piloting class of the log tool wrappers *
 * Author               Julien Darthenay julien<dot>darthenay<at>free<dot>fr *
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Automatically selects a tool for logging.
 * If Log4j2 is available, uses it.
 * If no other log tool is available, uses console logging.
 * @author Julien Darthenay
 * @version 1.2
 * @since 1.1
 */
public abstract class LogManager extends Object {
  private static final String PROPERTY_SILENTLY_SEARCH_LOGGING_TOOLS
                                     = "LogWrapper.silentlySearchLoggingTools";
  private static final String PROPERTY_IGNORE_LOG4J2
                                                   = "LogWrapper.ignoreLog4j2";

  private static final String LOG4J2_LOG_MANAGER_QUALIFIED_NAME
                                       = "org.apache.logging.log4j.LogManager";
  private static final String LOG4J2_METHOD_GET_LOGGER = "getLogger";

  private static final boolean SILENTLY_SEARCH_LOGGING_TOOLS =
                    Boolean.getBoolean(PROPERTY_SILENTLY_SEARCH_LOGGING_TOOLS);
  private static final boolean IGNORE_LOG4J2 =
                                    Boolean.getBoolean(PROPERTY_IGNORE_LOG4J2);

  private static final Map<String, Logger> MAP_LOGGERS =
                    Collections.synchronizedMap(new HashMap<String, Logger>());

  /**
   * Checks if Log4j 2 was found.
   * @since 1.0
   */
  static final boolean LOG4J2_FOUND;

  /**
   * Log4j 2 method to get loggers.
   * @since 1.0
   */
  static final Method METHOD_GET_LOGGER;

  /**
   * Selects the log wrapper to use for logging.
   * If Log4j2 is available, uses Log4j2 wrapper.
   * If no other log tool is available, uses console logging wrapper.
   * @since 1.0
   */
  static {
    ClassLoader classLoader = LogManager.class.getClassLoader();
    Method methodGetLogger = null;
    boolean log4j2Found = false;

    if (!IGNORE_LOG4J2) {
      try {
        Class<?> classLogManager =
                      classLoader.loadClass(LOG4J2_LOG_MANAGER_QUALIFIED_NAME);
        methodGetLogger =
             classLogManager.getMethod(LOG4J2_METHOD_GET_LOGGER, String.class);

        log4j2Found = true;
      } catch (ClassNotFoundException e) {
        if (!SILENTLY_SEARCH_LOGGING_TOOLS) {
          e.printStackTrace();
        }
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      }
    }

    METHOD_GET_LOGGER = methodGetLogger;
    LOG4J2_FOUND = log4j2Found;
  }

  /**
   * Constructor is not intended to be used.
   * @since 1.1
   */
  private LogManager() {
    super();
  }

  /**
   * Creates a log wrapper for the given class.
   * @param clazz class whose name will be used as logger name
   * @return log wrapper
   * @since 1.0
   */
  public static Logger getLogger(Class<?> clazz) {
    return getLogger(clazz.getName());
  }

  /**
   * Creates a log wrapper for the given tag.
   * @param tag name of the logger
   * @return log wrapper
   * @since 1.1
   */
  public static Logger getLogger(String tag) {
    synchronized(MAP_LOGGERS) {
      if (MAP_LOGGERS.containsKey(tag)) {
        return MAP_LOGGERS.get(tag);
      } else {
        Logger logger;

        if (LOG4J2_FOUND) {
          logger = new Log4j2Wrapper(tag);
        } else {
          logger = new LogConsoleWrapper(tag);
        }

        MAP_LOGGERS.put(tag, logger);
        return logger;
      }
    }
  }

}
