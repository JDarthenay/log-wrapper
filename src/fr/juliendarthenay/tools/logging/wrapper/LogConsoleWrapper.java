/*****************************************************************************
 * LogConsoleWrapper.java                    console output log tool wrapper *
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

/**
 * Wrapper for console logging.
 * @author Julien Darthenay
 * @version 1.4
 * @since 1.0
 */
class LogConsoleWrapper extends Logger {
  private static final String PROPERTY_CONSOLE_LOGGER_LEVEL =
                                              "LogWrapper.consoleLogger.level";
  private static final String PROPERTY_CONSOLE_LOGGER_LEVEL_DEFAULT = "all";

  private static final String FORMAT_FATAL = "FATAL %s - %s";
  private static final String FORMAT_ERROR = "ERROR %s - %s";
  private static final String FORMAT_WARN = "WARN  %s - %s";
  private static final String FORMAT_INFO = "INFO  %s - %s";
  private static final String FORMAT_DEBUG = "DEBUG %s - %s";
  private static final String FORMAT_TRACE = "TRACE %s - %s";

  private static final String CONSOLE_LOGGER_LEVEL = System.getProperty(
    PROPERTY_CONSOLE_LOGGER_LEVEL,
    PROPERTY_CONSOLE_LOGGER_LEVEL_DEFAULT
  );
  private static final Level LEVEL;

  private final String name;

  /**
   * Computes console logger level from system properties.
   * @since 1.1
   */
  static {
    Level level = Level.fromString(CONSOLE_LOGGER_LEVEL);
    if (level == null) {
      LEVEL = Level.fromString(PROPERTY_CONSOLE_LOGGER_LEVEL_DEFAULT);
    } else {
      LEVEL = level;
    }
  }

  /**
   * Constructor should only be used by LogManager.getLogger().
   * @param tag logger name
   * @since 1.0
   */
  LogConsoleWrapper(String tag) {
    super();

    name = tag;
  }

  @Override
  public void fatal(String message) {
    if (isFatalEnabled()) {
      System.out.println(String.format(FORMAT_FATAL, name, message));
    }
  }

  @Override
  public void error(String message) {
    if (isErrorEnabled()) {
      System.out.println(String.format(FORMAT_ERROR, name, message));
    }
  }

  @Override
  public void warn(String message) {
    if (isWarnEnabled()) {
      System.out.println(String.format(FORMAT_WARN, name, message));
    }
  }

  @Override
  public void info(String message) {
    if (isInfoEnabled()) {
      System.out.println(String.format(FORMAT_INFO, name, message));
    }
  }

  @Override
  public void debug(String message) {
    if (isDebugEnabled()) {
      System.out.println(String.format(FORMAT_DEBUG, name, message));
    }
  }

  @Override
  public void trace(String message) {
    if (isTraceEnabled()) {
      System.out.println(String.format(FORMAT_TRACE, name, message));
    }
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Level getLevel() {
    return LEVEL;
  }

}
