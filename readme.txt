===============================================================================
=  readme.txt       Log tool wrapper files description and short user manual  =
=  Author               Julien Darthenay julien<dot>darthenay<at>free<dot>fr  =
=  Developing tools      Oracle Java SDK 8, Notepad++, MS Windows Seven Home  =
=  Copyright 2016 Julien Darthenay                                            =
===============================================================================

===============================================================================
=  The MIT License (MIT)                                                      =
=                                                                             =
=  Copyright (c) 2016 Julien Darthenay <julien.darthenay@free.fr>             =
=                                                                             =
=  Permission is hereby granted, free of charge, to any person obtaining a    =
=  copy of this software and associated documentation files (the              =
=  "Software"), to  deal in the Software without restriction, including       =
=  without limitation the rights to use, copy, modify, merge, publish,        =
=  distribute, sublicense, and/or sell copies of the Software, and to permit  =
=  persons to whom the Software is furnished to do                            =
=  so, subject to the following conditions:                                   =
=                                                                             =
=  The above copyright notice and this permission notice shall be included    =
=  in all copies or substantial portions of the Software.                     =
=                                                                             =
=  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS    =
=  OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF                 =
=  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN  =
=  NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,   =
=  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR      =
=  OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE  =
=  USE OR OTHER DEALINGS IN THE SOFTWARE.                                     =
===============================================================================

The archive file containing this file is available at
<http://julien.darthenay.free.fr> or
<https://github.com/JDarthenay/log-wrapper>.

LogWrapper - version 1.4
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
  MITLICENSE.txt
  lisezmoi.txt
  readme.txt
  README.md

Logger.java - generic parent class for all wrappers.

LogManager.java - determines log wrapper to be used and build loggers.

Log4j2Wrapper.java - wrapper for Apache Log4j 2.

LogConsoleWrapper.java - wrapper for console logging.

Level.java - logging levels.

MITLICENSE.txt - MIT license. It applies to all these files.

lisezmoi.txt - LogWrapper's description in French.

readme.txt - LogWrapper's description in English.

README.md - short usage note.

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
February 27, 2016 - version 1.2
  New logging methods for Throwable objects.
March 5, 2016 - version 1.3
  Simplification of logging methods to reduce necessary code for each wrapper.
  Add one argument to logging methods for Throwable objects in order to better
  match logging tools conventions.
June 5, 2016 - version 1.4
  Javadoc typos.
  Minor code updates.
  Adding README.md file.
  Switching to MIT license.
