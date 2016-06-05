/*****************************************************************************
 * Logger.java                        Generic class of the log tool wrappers *
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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.function.Supplier;

/**
 * Generic class for log wrappers.
 * @author Julien Darthenay
 * @version 1.4
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
   * @param level Log level to which log
   * @param message Message to log
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
   * Prints a message at given level, computes it only if necessary.
   * @param level Log level to which log
   * @param msgSupplier Supplier for computing message
   * @since 1.1
   */
  public void log(Level level, Supplier<?> msgSupplier) {
    if (isEnabled(level)) {
      log(level, msgSupplier.get().toString());
    }
  }

  /**
   * Prints a stack trace message at given level.
   * @param level Log level to which log
   * @param message Message to log
   * @param t Throwable you want to log the stack trace
   * @since 1.2
   */
  public void log(Level level, String message, Throwable t) {
    if (isEnabled(level)) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      pw.println(message);
      t.printStackTrace(pw);
      log(level, sw.toString());
    }
  }

  /**
   * Prints a fatal level message.
   * @param message Message to log
   * @since 1.0
   */
  abstract public void fatal(String message);

  /**
   * Prints a fatal level message, computes it only if necessary.
   * @param msgSupplier Supplier for computing message
   * @since 1.1
   */
  public void fatal(Supplier<?> msgSupplier) {
    log(Level.FATAL, msgSupplier);
  }

  /**
   * Prints a stack trace message at fatal level.
   * @param message Message to log
   * @param t Throwable you want to log the stack trace
   * @since 1.2
   */
  public void fatal(String message, Throwable t) {
    log(Level.FATAL, message, t);
  }

  /**
   * Prints an error level message.
   * @param message Message to log
   * @since 1.0
   */
  abstract public void error(String message);

  /**
   * Prints an error level message, computes it only if necessary.
   * @param msgSupplier Supplier for computing message
   * @since 1.1
   */
  public void error(Supplier<?> msgSupplier) {
    log(Level.ERROR, msgSupplier);
  }

  /**
   * Prints a stack trace message at error level.
   * @param message Message to log
   * @param t Throwable you want to log the stack trace
   * @since 1.2
   */
  public void error(String message, Throwable t) {
    log(Level.ERROR, message, t);
  }

  /**
   * Prints a warn level message.
   * @param message Message to log
   * @since 1.0
   */
  abstract public void warn(String message);

  /**
   * Prints a warn level message, computes it only if necessary.
   * @param msgSupplier Supplier for computing message
   * @since 1.1
   */
  public void warn(Supplier<?> msgSupplier) {
    log(Level.WARN, msgSupplier);
  }

  /**
   * Prints a stack trace message at warn level.
   * @param message Message to log
   * @param t Throwable you want to log the stack trace
   * @since 1.2
   */
  public void warn(String message, Throwable t) {
    log(Level.WARN, message, t);
  }

  /**
   * Prints an info level message.
   * @param message Message to log
   * @since 1.0
   */
  abstract public void info(String message);

  /**
   * Prints an info level message, computes it only if necessary.
   * @param msgSupplier Supplier for computing message
   * @since 1.1
   */
  public void info(Supplier<?> msgSupplier) {
    log(Level.INFO, msgSupplier);
  }

  /**
   * Prints a stack trace message at info level.
   * @param message Message to log
   * @param t Throwable you want to log the stack trace
   * @since 1.2
   */
  public void info(String message, Throwable t) {
    log(Level.INFO, message, t);
  }

  /**
   * Prints a debug level message.
   * @param message Message to log
   * @since 1.0
   */
  abstract public void debug(String message);

  /**
   * Prints a debug level message, computes it only if necessary.
   * @param msgSupplier Supplier for computing message
   * @since 1.1
   */
  public void debug(Supplier<?> msgSupplier) {
    log(Level.DEBUG, msgSupplier);
  }

  /**
   * Prints a stack trace message at debug level.
   * @param message Message to log
   * @param t Throwable you want to log the stack trace
   * @since 1.2
   */
  public void debug(String message, Throwable t) {
    log(Level.DEBUG, message, t);
  }

  /**
   * Prints a trace level message.
   * @param message Message to log
   * @since 1.0
   */
  abstract public void trace(String message);

  /**
   * Prints a trace level message, computes it only if necessary.
   * @param msgSupplier Supplier for computing message
   * @since 1.1
   */
  public void trace(Supplier<?> msgSupplier) {
    log(Level.TRACE, msgSupplier);
  }

  /**
   * Prints a stack trace message at trace level.
   * @param message Message to log
   * @param t Throwable you want to log the stack trace
   * @since 1.2
   */
  public void trace(String message, Throwable t) {
    log(Level.TRACE, message, t);
  }

  /**
   * Retrieves the name of this logger.
   * @return Logger name
   * @since 1.1
   */
  abstract public String getName();

  /**
   * Retrieves the level of this logger.
   * @return Logger level
   * @since 1.1
   */
  abstract public Level getLevel();

  /**
   * Checks if logger level enables a message of given level.
   * @param level Message level
   * @return True if logger level enables a message of given level
   * @since 1.1
   */
  public boolean isEnabled(Level level) {
    return getLevel().isEnabling(level);
  }

  /**
   * Checks if logger level enables a message of level fatal.
   * @return True if logger level enables a message of level fatal
   * @since 1.1
   */
  public boolean isFatalEnabled() {
    return isEnabled(Level.FATAL);
  }

  /**
   * Checks if logger level enables a message of level error.
   * @return True if logger level enables a message of level error
   * @since 1.1
   */
  public boolean isErrorEnabled() {
    return isEnabled(Level.ERROR);
  }

  /**
   * Checks if logger level enables a message of level warn.
   * @return True if logger level enables a message of level warn
   * @since 1.1
   */
  public boolean isWarnEnabled() {
    return isEnabled(Level.WARN);
  }

  /**
   * Checks if logger level enables a message of level info.
   * @return True if logger level enables a message of level info
   * @since 1.1
   */
  public boolean isInfoEnabled() {
    return isEnabled(Level.INFO);
  }

  /**
   * Checks if logger level enables a message of level debug.
   * @return True if logger level enables a message of level debug
   * @since 1.1
   */
  public boolean isDebugEnabled() {
    return isEnabled(Level.DEBUG);
  }

  /**
   * Checks if logger level enables a message of level trace.
   * @return True if logger level enables a message of level trace
   * @since 1.1
   */
  public boolean isTraceEnabled() {
    return isEnabled(Level.TRACE);
  }

}
