/*****************************************************************************
 * Logger.java                        Generic class of the log tool wrappers *
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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.function.Supplier;

/**
 * Generic class for log wrappers.
 * @author Julien Darthenay
 * @version 1.2
 * @since 1.0
 */
public abstract class Logger extends Object {
  /**
   * Constructor is not intended to be used except by subclasses constructors.
   * @since 1.0
   */
  Logger() {
    super();
  }

  /**
   * Prints a log at given level.
   * @param level log level to which log
   * @param message message to log
   * @since 1.1
   */
  public void log(Level level, String message) {
    switch (level) {
    case FATAL:
      fatal(message);
      break;

    case ERROR:
      error(message);
      break;

    case WARN:
      warn(message);
      break;

    case INFO:
      info(message);
      break;

    case DEBUG:
      debug(message);
      break;

    case TRACE:
      trace(message);
      break;
    }
  }

  /**
   * Prints a message at given level, computes it only if necessarry.
   * @param level log level to which log
   * @param msgSupplier supplier for computing message
   * @since 1.1
   */
  public void log(Level level, Supplier<?> msgSupplier) {
    if (isEnabled(level)) {
      log(level, msgSupplier.get().toString());
    }
  }

  /**
   * Prints a stack trace message at given level.
   * @param level log level to which log
   * @param t throwable you want to log the stack trace
   * @since 1.2
   */
  public void log(Level level, Throwable t) {
    if (isEnabled(level)) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      t.printStackTrace(pw);
      log(level, sw.toString());
    }
  }

  /**
   * Prints a fatal level message.
   * @param message message to log
   * @since 1.0
   */
  abstract public void fatal(String message);

  /**
   * Prints a fatal level message, computes it only if necessarry.
   * @param msgSupplier supplier for computing message
   * @since 1.1
   */
  public void fatal(Supplier<?> msgSupplier) {
    if (isFatalEnabled()) {
      fatal(msgSupplier.get().toString());
    }
  }

  /**
   * Prints a stack trace message at fatal level.
   * @param t throwable you want to log the stack trace
   * @since 1.2
   */
  public void fatal(Throwable t) {
    if (isFatalEnabled()) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      t.printStackTrace(pw);
      fatal(sw.toString());
    }
  }

  /**
   * Prints an error level message.
   * @param message message to log
   * @since 1.0
   */
  abstract public void error(String message);

  /**
   * Prints an error level message, computes it only if necessarry.
   * @param msgSupplier supplier for computing message
   * @since 1.1
   */
  public void error(Supplier<?> msgSupplier) {
    if (isErrorEnabled()) {
      error(msgSupplier.get().toString());
    }
  }

  /**
   * Prints a stack trace message at error level.
   * @param t throwable you want to log the stack trace
   * @since 1.2
   */
  public void error(Throwable t) {
    if (isErrorEnabled()) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      t.printStackTrace(pw);
      error(sw.toString());
    }
  }

  /**
   * Prints a warn level message.
   * @param message message to log
   * @since 1.0
   */
  abstract public void warn(String message);

  /**
   * Prints a warn level message, computes it only if necessarry.
   * @param msgSupplier supplier for computing message
   * @since 1.1
   */
  public void warn(Supplier<?> msgSupplier) {
    if (isWarnEnabled()) {
      warn(msgSupplier.get().toString());
    }
  }

  /**
   * Prints a stack trace message at warn level.
   * @param t throwable you want to log the stack trace
   * @since 1.2
   */
  public void warn(Throwable t) {
    if (isWarnEnabled()) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      t.printStackTrace(pw);
      warn(sw.toString());
    }
  }

  /**
   * Prints an info level message.
   * @param message message to log
   * @since 1.0
   */
  abstract public void info(String message);

  /**
   * Prints an info level message, computes it only if necessarry.
   * @param msgSupplier supplier for computing message
   * @since 1.1
   */
  public void info(Supplier<?> msgSupplier) {
    if (isInfoEnabled()) {
      info(msgSupplier.get().toString());
    }
  }

  /**
   * Prints a stack trace message at info level.
   * @param t throwable you want to log the stack trace
   * @since 1.2
   */
  public void info(Throwable t) {
    if (isInfoEnabled()) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      t.printStackTrace(pw);
      info(sw.toString());
    }
  }

  /**
   * Prints a debug level message.
   * @param message message to log
   * @since 1.0
   */
  abstract public void debug(String message);

  /**
   * Prints a debug level message, computes it only if necessarry.
   * @param msgSupplier supplier for computing message
   * @since 1.1
   */
  public void debug(Supplier<?> msgSupplier) {
    if (isDebugEnabled()) {
      debug(msgSupplier.get().toString());
    }
  }

  /**
   * Prints a stack trace message at debug level.
   * @param t throwable you want to log the stack trace
   * @since 1.2
   */
  public void debug(Throwable t) {
    if (isDebugEnabled()) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      t.printStackTrace(pw);
      debug(sw.toString());
    }
  }

  /**
   * Prints a trace level message.
   * @param message message to log
   * @since 1.0
   */
  abstract public void trace(String message);

  /**
   * Prints a trace level message, computes it only if necessarry.
   * @param msgSupplier supplier for computing message
   * @since 1.1
   */
  public void trace(Supplier<?> msgSupplier) {
    if (isTraceEnabled()) {
      trace(msgSupplier.get().toString());
    }
  }

  /**
   * Prints a stack trace message at trace level.
   * @param t throwable you want to log the stack trace
   * @since 1.2
   */
  public void trace(Throwable t) {
    if (isTraceEnabled()) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      t.printStackTrace(pw);
      trace(sw.toString());
    }
  }

  /**
   * Retrieves the name of this logger.
   * @return logger name
   * @since 1.1
   */
  abstract public String getName();

  /**
   * Retrieves the level of this logger.
   * @return logger level
   * @since 1.1
   */
  abstract public Level getLevel();

  /**
   * Checks if logger level enables a message of given level.
   * @param level message level
   * @return true or false
   * @since 1.1
   */
  public boolean isEnabled(Level level) {
    return getLevel().isEnabling(level);
  }

  /**
   * Checks if logger level enables a message of level fatal.
   * @return true or false
   * @since 1.1
   */
  public boolean isFatalEnabled() {
    return getLevel().isEnabling(Level.FATAL);
  }

  /**
   * Checks if logger level enables a message of level error.
   * @return true or false
   * @since 1.1
   */
  public boolean isErrorEnabled() {
    return getLevel().isEnabling(Level.ERROR);
  }

  /**
   * Checks if logger level enables a message of level warn.
   * @return true or false
   * @since 1.1
   */
  public boolean isWarnEnabled() {
    return getLevel().isEnabling(Level.WARN);
  }

  /**
   * Checks if logger level enables a message of level info.
   * @return true or false
   * @since 1.1
   */
  public boolean isInfoEnabled() {
    return getLevel().isEnabling(Level.INFO);
  }

  /**
   * Checks if logger level enables a message of level debug.
   * @return true or false
   * @since 1.1
   */
  public boolean isDebugEnabled() {
    return getLevel().isEnabling(Level.DEBUG);
  }

  /**
   * Checks if logger level enables a message of level trace.
   * @return true or false
   * @since 1.1
   */
  public boolean isTraceEnabled() {
    return getLevel().isEnabling(Level.TRACE);
  }

}
