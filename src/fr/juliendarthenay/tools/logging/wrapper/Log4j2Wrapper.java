/*****************************************************************************
 * LogWrapper.java                           Apache Log4j 2 log tool wrapper *
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Log4j2Wrapper extends LogWrapper {
  private static final String METHOD_FATAL = "fatal";
  private static final String METHOD_ERROR = "error";
  private static final String METHOD_WARN = "warn";
  private static final String METHOD_INFO = "info";
  private static final String METHOD_DEBUG = "debug";
  private static final String METHOD_TRACE = "trace";

  private final Object logger;
  private final Method methodFatal;
  private final Method methodError;
  private final Method methodWarn;
  private final Method methodInfo;
  private final Method methodDebug;
  private final Method methodTrace;

  /**
   * Constructor should only be used by Log4j2Wrapper.getLogger().
   */
  Log4j2Wrapper(Class<?> clazz) {
    super();

    Object logger;

    try {
      logger = LogWrapper.getMethodGetLogger().invoke(null, clazz);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      logger = null;
    } catch (InvocationTargetException e) {
      e.printStackTrace();
      logger = null;
    }

    Method methodFatal;
    Method methodError;
    Method methodWarn;
    Method methodInfo;
    Method methodDebug;
    Method methodTrace;

    if (logger != null) {
      try {
        methodFatal = logger.getClass().getMethod(METHOD_FATAL, String.class);
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
        methodFatal = null;
      }

      try {
        methodError = logger.getClass().getMethod(METHOD_ERROR, String.class);
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
        methodError = null;
      }

      try {
        methodWarn = logger.getClass().getMethod(METHOD_WARN, String.class);
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
        methodWarn = null;
      }

      try {
        methodInfo = logger.getClass().getMethod(METHOD_INFO, String.class);
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
        methodInfo = null;
      }

      try {
        methodDebug = logger.getClass().getMethod(METHOD_DEBUG, String.class);
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
        methodDebug = null;
      }

      try {
        methodTrace = logger.getClass().getMethod(METHOD_TRACE, String.class);
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
        methodTrace = null;
      }
    } else {
      methodFatal = null;
      methodError = null;
      methodWarn = null;
      methodInfo = null;
      methodDebug = null;
      methodTrace = null;
    }

    this.logger = logger;
    this.methodFatal = methodFatal;
    this.methodError = methodError;
    this.methodWarn = methodWarn;
    this.methodInfo = methodInfo;
    this.methodDebug = methodDebug;
    this.methodTrace = methodTrace;
  }

  @Override
  public void fatal(String message) {
    try {
      methodFatal.invoke(logger, message);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      System.out.println(message);
    } catch (InvocationTargetException e) {
      e.printStackTrace();
      System.out.println(message);
    }
  }

  @Override
  public void error(String message) {
    try {
      methodError.invoke(logger, message);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      System.out.println(message);
    } catch (InvocationTargetException e) {
      e.printStackTrace();
      System.out.println(message);
    }
  }

  @Override
  public void warn(String message) {
    try {
      methodWarn.invoke(logger, message);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      System.out.println(message);
    } catch (InvocationTargetException e) {
      e.printStackTrace();
      System.out.println(message);
    }
  }

  @Override
  public void info(String message) {
    try {
      methodInfo.invoke(logger, message);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      System.out.println(message);
    } catch (InvocationTargetException e) {
      e.printStackTrace();
      System.out.println(message);
    }
  }

  @Override
  public void debug(String message) {
    try {
      methodDebug.invoke(logger, message);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      System.out.println(message);
    } catch (InvocationTargetException e) {
      e.printStackTrace();
      System.out.println(message);
    }
  }

  @Override
  public void trace(String message) {
    try {
      methodTrace.invoke(logger, message);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      System.out.println(message);
    } catch (InvocationTargetException e) {
      e.printStackTrace();
      System.out.println(message);
    }
  }

}
