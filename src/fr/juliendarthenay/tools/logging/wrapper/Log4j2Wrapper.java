/*****************************************************************************
 * Log4j2Wrapper.java                        Apache Log4j 2 log tool wrapper *
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.EnumMap;
import java.util.Map;

/**
 * Wrapper for Log4j 2.
 * @author Julien Darthenay
 * @version 1.4
 * @since 1.0
 */
class Log4j2Wrapper extends Logger {
  private static final String LOG4J2_LOGGER_QUALIFIED_NAME =
                                             "org.apache.logging.log4j.Logger";
  private static final String LOG4J2_LEVEL_QUALIFIED_NAME =
                                              "org.apache.logging.log4j.Level";

  private static final String METHOD_GET_LEVEL_NAME = "getLevel";

  private static final Map<Level, Object> MAP_LOG4J_2_LEVELS =
                                       new EnumMap<Level, Object>(Level.class);
  private static final Map<Level, Method> MAP_LOG4J_2_METHODS =
                                       new EnumMap<Level, Method>(Level.class);
  private static final Method METHOD_GET_LEVEL;

  /**
   * Loads classes, methods and constants for Log4j 2.
   * @since 1.1
   */
  static {
    if (LogManager.LOG4J2_FOUND) {
      ClassLoader classLoader = Log4j2Wrapper.class.getClassLoader();

      Class<?> classLogger;
      try {
        classLogger = classLoader.loadClass(LOG4J2_LOGGER_QUALIFIED_NAME);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
        classLogger = null;
      }

      Method methodGetLevel = null;
      if (classLogger != null) {
        try {
          methodGetLevel = classLogger.getMethod(METHOD_GET_LEVEL_NAME);
        } catch (NoSuchMethodException e) {
          e.printStackTrace();
        }

        for (Level level : Level.values()) {
          if (level.isMayBeSent()) {
            Method method;

            try {
              method = classLogger.getMethod(level.getLog4j2Name(),
                                             String.class);
            } catch (NoSuchMethodException e) {
              e.printStackTrace();
              method = null;
            }

            MAP_LOG4J_2_METHODS.put(level, method);
          }
        }
      } else {
        for (Level level : Level.values()) {
          if (level.isMayBeSent()) {
            MAP_LOG4J_2_METHODS.put(level, null);
          }
        }
      }

      Class<?> classLevel;
      try {
        classLevel = classLoader.loadClass(LOG4J2_LEVEL_QUALIFIED_NAME);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
        classLevel = null;
      }

      if (classLevel != null) {
        for (Level level : Level.values()) {
          Field fieldLog4j2Level;
          try {
            fieldLog4j2Level =
                      classLevel.getField(level.getLog4j2Name().toUpperCase());
          } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fieldLog4j2Level = null;
          }

          if (fieldLog4j2Level !=  null) {
            try {
              MAP_LOG4J_2_LEVELS.put(level, fieldLog4j2Level.get(null));
            } catch (IllegalAccessException e) {
              e.printStackTrace();
              MAP_LOG4J_2_LEVELS.put(level, null);
            }
          } else {
            MAP_LOG4J_2_LEVELS.put(level, null);
          }
        }
      } else {
        for (Level level : Level.values()) {
          MAP_LOG4J_2_LEVELS.put(level, null);
        }
      }

      METHOD_GET_LEVEL = methodGetLevel;
    } else {
      METHOD_GET_LEVEL = null;
    }
  }

  private final Object logger;
  private final String name;

  /**
   * Constructor should only be used by LogManager.getLogger().
   * @param tag Logger name
   * @since 1.0
   */
  Log4j2Wrapper(String tag) {
    super();

    Object logger;

    try {
      logger = LogManager.LOG4J2_METHOD_GET_LOGGER.invoke(null, tag);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      logger = null;
    } catch (InvocationTargetException e) {
      e.printStackTrace();
      logger = null;
    }

    this.logger = logger;
    this.name = tag;
  }

  @Override
  public void fatal(String message) {
    try {
      MAP_LOG4J_2_METHODS.get(Level.FATAL).invoke(logger, message);
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
      MAP_LOG4J_2_METHODS.get(Level.ERROR).invoke(logger, message);
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
      MAP_LOG4J_2_METHODS.get(Level.WARN).invoke(logger, message);
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
      MAP_LOG4J_2_METHODS.get(Level.INFO).invoke(logger, message);
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
      MAP_LOG4J_2_METHODS.get(Level.DEBUG).invoke(logger, message);
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
      MAP_LOG4J_2_METHODS.get(Level.TRACE).invoke(logger, message);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      System.out.println(message);
    } catch (InvocationTargetException e) {
      e.printStackTrace();
      System.out.println(message);
    }
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Level getLevel() {
    Object log4j2level;
    try {
      log4j2level = METHOD_GET_LEVEL.invoke(logger);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      log4j2level = null;
    } catch (InvocationTargetException e) {
      e.printStackTrace();
      log4j2level = null;
    }

    if (log4j2level != null) {
      for (Level level : Level.values()) {
        if (log4j2level.equals(MAP_LOG4J_2_LEVELS.get(level))) {
          return level;
        }
      }
    }

    return null;
  }

}
