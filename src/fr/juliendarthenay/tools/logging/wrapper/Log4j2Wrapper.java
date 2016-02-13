/*****************************************************************************
 * Log4j2Wrapper.java                        Apache Log4j 2 log tool wrapper *
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.EnumMap;
import java.util.Map;

/**
 * Wrapper for Log4j 2.
 * @author Julien Darthenay
 * @version 1.1
 * @since 1.0
 */
class Log4j2Wrapper extends Logger {
  private static final String LOG4J2_LOGGER_QUALIFIED_NAME = "org.apache.logging.log4j.Logger";
  private static final String LOG4J2_LEVEL_QUALIFIED_NAME = "org.apache.logging.log4j.Level";

  private static final String METHOD_GET_LEVEL_NAME = "getLevel";

  private static final Map<Level, Object> MAP_LOG4J_2_LEVELS = new EnumMap<Level, Object>(Level.class);
  private static final Map<Level, Method> MAP_LOG4J_2_METHODS = new EnumMap<Level, Method>(Level.class);
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
              method = classLogger.getMethod(level.getLog4j2Name(), String.class);
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
            fieldLog4j2Level = classLevel.getField(level.getLog4j2Name().toUpperCase());
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
   * @param tag logger name
   * @since 1.0
   */
  Log4j2Wrapper(String tag) {
    super();

    Object logger;

    try {
      logger = LogManager.METHOD_GET_LOGGER.invoke(null, tag);
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
