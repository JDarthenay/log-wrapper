/*****************************************************************************
 * LogConsoleWrapper.java                    console output log tool wrapper *
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

/**
 * Wrapper for console logging.
 * @author Julien Darthenay
 * @version 1.1
 * @since 1.0
 */
class LogConsoleWrapper extends Logger {
  private static final String PROPERTY_CONSOLE_LOGGER_LEVEL = "LogWrapper.consoleLogger.level";
  private static final String PROPERTY_CONSOLE_LOGGER_LEVEL_DEFAULT = "all";

  private static final String FORMAT_FATAL = "FATAL %s - %s";
  private static final String FORMAT_ERROR = "ERROR %s - %s";
  private static final String FORMAT_WARN = "WARN  %s - %s";
  private static final String FORMAT_INFO = "INFO  %s - %s";
  private static final String FORMAT_DEBUG = "DEBUG %s - %s";
  private static final String FORMAT_TRACE = "TRACE %s - %s";

  private static final String CONSOLE_LOGGER_LEVEL = System.getProperty(PROPERTY_CONSOLE_LOGGER_LEVEL, PROPERTY_CONSOLE_LOGGER_LEVEL_DEFAULT);
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
