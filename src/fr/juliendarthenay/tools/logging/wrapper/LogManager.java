/*****************************************************************************
 * LogManager.java                   piloting class of the log tool wrappers *
 * Author               Julien Darthenay julien<dot>darthenay<at>free<dot>fr *
 * Developing tools      Oracle Java SDK 8, Notepad++, MS Windows Seven Home *
 * Copyright 2016 Julien Darthenay                                           *
 *****************************************************************************/

/*****************************************************************************
 * The MIT License (MIT)                                                     *
 *                                                                           *
 * Copyright (c) 2016 Julien Darthenay <julien.darthenay@free.fr>            *
 *                                                                           *
 * Permission is hereby granted, free of charge, to any person obtaining a   *
 * copy of this software and associated documentation files (the             *
 * "Software"), to  deal in the Software without restriction, including      *
 * without limitation the rights to use, copy, modify, merge, publish,       *
 * distribute, sublicense, and/or sell copies of the Software, and to permit *
 * persons to whom the Software is furnished to do                           *
 * so, subject to the following conditions:                                  *
 *                                                                           *
 * The above copyright notice and this permission notice shall be included   *
 * in all copies or substantial portions of the Software.                    *
 *                                                                           *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS   *
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF                *
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN *
 * NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,  *
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR     *
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE *
 * USE OR OTHER DEALINGS IN THE SOFTWARE.                                    *
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
 * @version 1.4
 * @since 1.1
 */
public abstract class LogManager extends Object {
  private static final String PROPERTY_SILENTLY_SEARCH_LOGGING_TOOLS
                                     = "LogWrapper.silentlySearchLoggingTools";
  private static final String PROPERTY_IGNORE_LOG4J2
                                                   = "LogWrapper.ignoreLog4j2";

  private static final String LOG4J2_LOG_MANAGER_QUALIFIED_NAME
                                       = "org.apache.logging.log4j.LogManager";
  private static final String LOG4J2_METHOD_GET_LOGGER_NAME = "getLogger";

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
  static final Method LOG4J2_METHOD_GET_LOGGER;

  /**
   * Selects the log wrapper to use for logging.
   * If Log4j2 is available, uses Log4j2 wrapper.
   * If no other log tool is available, uses console logging wrapper.
   * @since 1.0
   */
  static {
    ClassLoader classLoader = LogManager.class.getClassLoader();
    Method log4j2MethodGetLogger = null;
    boolean log4j2Found = false;

    if (!IGNORE_LOG4J2) {
      try {
        Class<?> classLogManager =
                      classLoader.loadClass(LOG4J2_LOG_MANAGER_QUALIFIED_NAME);
        log4j2MethodGetLogger =
        classLogManager.getMethod(LOG4J2_METHOD_GET_LOGGER_NAME, String.class);

        log4j2Found = true;
      } catch (ClassNotFoundException e) {
        if (!SILENTLY_SEARCH_LOGGING_TOOLS) {
          e.printStackTrace();
        }
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      }
    }

    LOG4J2_METHOD_GET_LOGGER = log4j2MethodGetLogger;
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
   * @param clazz Class whose name will be used as logger name
   * @return Log wrapper
   * @since 1.0
   */
  public static Logger getLogger(Class<?> clazz) {
    return getLogger(clazz.getName());
  }

  /**
   * Creates a log wrapper for the given tag.
   * @param tag Name of the logger
   * @return Log wrapper
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
