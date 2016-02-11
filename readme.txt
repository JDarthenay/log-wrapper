===============================================================================
=  lisezmoi.txt                           Log tool wrapper files description  =
=  Auteur               Julien Darthenay julien<dot>darthenay<at>free<dot>fr  =
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
<http://julien.darthenay.free.fr>.

LogWrapper - version 1.0
Uses reflection to determine if logging must be done with Log4j 2 or just sent
to console.

LogWrapper-src.7z - archive including all source file for the library.
Includes:
  LogWrapper.java
  LogConsoleWrapper.java
  Log4j2Wrapper.java
  COPYING.txt
  COPYING-LESSER.txt
  lisezmoi.txt
  readme.txt

LogWrapper.java - generic parent class for all wrappers.

Log4j2Wrapper.java - wrapper for Apache Log4j 2.

LogConsoleWrapper.java - wrapper console logging.

COPYING.txt - GPL licence version 3.

COPYING-LESSER.txt - Lesser GPL licence version 3. It applies to all these
files.

lisezmoi.txt - LogWrapper's description in French.

readme.txt - LogWrapper's description in English.

Version History
February 7, 2016 - version 1.0
  Creation.
