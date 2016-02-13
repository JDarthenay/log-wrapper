===============================================================================
=  readme.txt       Log tool wrapper files description and short user manual  =
=  Author               Julien Darthenay julien<dot>darthenay<at>free<dot>fr  =
=  Developing tools      Oracle Java SDK 8, Notepad++, MS Windows Seven Home  =
=  Copyright 2016 Julien Darthenay                                            =
===============================================================================

===============================================================================
=  This file is part of LogWrapper                                            =
=                                                                             =
=  LogWrapper is free software: you can redistribute it and/or modify it      =
=  under the terms of the GNU Lesser General Public License as published by   =
=  the Free Software Foundation, either version 3 of the License, or (at      =
=  your option) any later version.                                            =
=                                                                             =
=  LogWrapper is distributed in the hope that it will be useful, but WITHOUT  =
=  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or      =
=  FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public        =
=  License for more details.                                                  =
=                                                                             =
=  You should have received a copy of the GNU Lesser General Public License   =
=  along with LogWrapper. If not, see <http://www.gnu.org/licenses/>.         =
===============================================================================

The archive file containing this file is available at
<http://julien.darthenay.free.fr> or
<https://github.com/JDarthenay/log-wrapper>.

LogWrapper - version 1.1
Uses reflection to determine if logging must be done with Log4j 2 or just sent
to console.

Some properties can modify LogWrapper's behaviour.
LogWrapper.silentlySearchLoggingTools set to true will prevent showing errors
due to a missing logging tool.
LogWrapper.ignoreLog4j2 set to true will prevent from using Log4j 2 wrapper.
LogWrapper.consoleLogger.level may be set to "off", "fatal", "error",
"warn", "info", "trace", "debug" or "all" to choose trace level of console
logger. This level is by default set to "all".
If you use a compiler for Java 1.7 or sooner, you need to create a "Supplier"
interface to replace "java.util.function.Supplier".

LogWrapper-src.7z - archive including all source file for the library.
Includes:
  Logger.java
  LogManager.java
  LogConsoleWrapper.java
  Log4j2Wrapper.java
  Level.java
  COPYING.txt
  COPYING-LESSER.txt
  lisezmoi.txt
  readme.txt

Logger.java - generic parent class for all wrappers.

LogManager.java - determines log wrapper to be used and build loggers.

Log4j2Wrapper.java - wrapper for Apache Log4j 2.

LogConsoleWrapper.java - wrapper for console logging.

Level.java - logging levels.

COPYING.txt - GPL licence version 3.

COPYING-LESSER.txt - Lesser GPL licence version 3. It applies to all these
files.

lisezmoi.txt - LogWrapper's description in French.

readme.txt - LogWrapper's description in English.

Version History
February 7, 2016 - version 1.0
  Creation.
February 13, 2016 - version 1.1
  Logger class renamed as "Logger".
  "LogManager" class creation.
  "getLogger()" method moved to "LogManager" class.
  "Level" class creation.
  New property: "LogWrapper.silentlySearchLoggingTool".
  New property: "LogWrapper.ignoreLog4j2".
  New property: "LogWrapper.consoleLogger.level".
  New methods regarding to logging levels.
  New logging methods.
  Synchronization added in "getLogger()" method.
