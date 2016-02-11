/*****************************************************************************
 * LogWrapper.java                           console output log tool wrapper *
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

class LogConsoleWrapper extends LogWrapper {
  private static final String FORMAT_FATAL = "FATAL %s - %s";
  private static final String FORMAT_ERROR = "ERROR %s - %s";
  private static final String FORMAT_WARN = "WARN  %s - %s";
  private static final String FORMAT_INFO = "INFO  %s - %s";
  private static final String FORMAT_DEBUG = "DEBUG %s - %s";
  private static final String FORMAT_TRACE = "TRACE %s - %s";

  private final String className;

  /**
   * Constructor should only be used by Log4j2Wrapper.getLogger().
   */
  LogConsoleWrapper(Class<?> clazz) {
    super();

    className = clazz.getName();
  }

  @Override
  public void fatal(String message) {
    System.out.println(String.format(FORMAT_FATAL, className, message));
  }

  @Override
  public void error(String message) {
    System.out.println(String.format(FORMAT_ERROR, className, message));
  }

  @Override
  public void warn(String message) {
    System.out.println(String.format(FORMAT_WARN, className, message));
  }

  @Override
  public void info(String message) {
    System.out.println(String.format(FORMAT_INFO, className, message));
  }

  @Override
  public void debug(String message) {
    System.out.println(String.format(FORMAT_DEBUG, className, message));
  }

  @Override
  public void trace(String message) {
    System.out.println(String.format(FORMAT_TRACE, className, message));
  }

}
