/*****************************************************************************
 * Level.java                        Logging levels of the log tool wrappers *
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
 * Logging levels.
 * @author Julien Darthenay
 * @version 1.4
 * @since 1.1
 */
public enum Level {
  /**
   * No message at all.
   * @since 1.1
   */
  OFF(0, false, "OFF"),

  /**
   * Fatal error message. This should terminate application.
   * @since 1.1
   */
  FATAL(1, true, "FATAL"),

  /**
   * Error message. Something went wrong.
   * @since 1.1
   */
  ERROR(2, true, "ERROR"),

  /**
   * Warning message. There is some risk result is not reliable.
   * @since 1.1
   */
  WARN(3, true, "WARN"),

  /**
   * Information message.
   * @since 1.1
   */
  INFO(4, true, "INFO"),

  /**
   * Debug message. Useful if you want to see internal intermediary results.
   * @since 1.1
   */
  DEBUG(5, true, "DEBUG"),

  /**
   * Trace message. Useful if you want to seek everything that is executed in
   * your application.
   * @since 1.1
   */
  TRACE(6, true, "TRACE"),

  /**
   * All messages.
   * @since 1.1
   */
  ALL(7, false, "ALL");

  final int value;
  final boolean mayBeSent;
  final String log4j2Name;

  /**
   * Enum constructor.
   * @param value Value of level, the higher the more specific
   * @param mayBeSent True if messages of this level can be sent
   * @param log4j2Name Name of the equivalent Log4j 2 level
   * @since 1.1
   */
  Level(int value, boolean mayBeSent, String log4j2Name) {
    this.value = value;
    this.mayBeSent = mayBeSent;
    this.log4j2Name = log4j2Name;
  }

  /**
   * Retrieves level with given value.
   * @param value Value of the researched level
   * @return Level or null
   * @since 1.1
   */
  public static Level fromValue(int value) {
    for (Level level : Level.values()) {
      if (level.getValue() == value) {
        return level;
      }
    }

    return null;
  }

  /**
   * Retrieves level with given name.
   * @param s Name of the researched level
   * @return Level or null
   * @since 1.1
   */
  public static Level fromString(String s) {
    for (Level level : Level.values()) {
      if (level.name().equalsIgnoreCase(s)) {
        return level;
      }
    }

    return null;
  }

  /**
   * Retrieves level with given Log4j 2 name.
   * @param s Log4j 2 name of the researched level
   * @return level or null
   * @since 1.1
   */
  public static Level fromLog4j2Name(String s) {
    for (Level level : Level.values()) {
      if (level.getLog4j2Name().equalsIgnoreCase(s)) {
        return level;
      }
    }

    return null;
  }

  /**
   * Retrieves value of this level.
   * @return Level value
   * @since 1.1
   */
  public int getValue() {
    return value;
  }

  /**
   * Checks if given level can be used for logging a message.
   * @return True if given level can be used for logging a message
   * @since 1.1
   */
  public boolean isMayBeSent() {
    return mayBeSent;
  }

  /**
   * Retrieves name of this level.
   * @return Level name, lowercase
   * @since 1.1
   */
  public String toString() {
    return name().toLowerCase();
  }

  /**
   * Retrieves Log4j 2 name of this level.
   * @return Log4j 2 name of this level, lowercase
   * @since 1.1
   */
  public String getLog4j2Name() {
    return log4j2Name.toLowerCase();
  }

  /**
   * Checks if this level as a logger level enables a message of given level.
   * @param level message level
   * @return True If this level enables a message of given level
   * @throws NullPointerException If given level is null
   * @since 1.1
   */
  public boolean isEnabling(Level level) {
    if (level == null) {
      throw new NullPointerException();
    }

    return this.value >= level.value;
  }

}
