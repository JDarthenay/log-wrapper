# LogWrapper

This Java library is a lighter equivalent of
[Apache Commonq Logging](http://commons.apache.org/proper/commons-logging/).
It supports very few features and only two logging systems:
* console logger (with a single logging level for all loggers)
* [Appache Log4j 2](http://logging.apache.org/log4j/2.x/)

Java reflection is used to try to load Log4j 2.

## License

The MIT License (MIT)
Copyright (c) 2016 Julien Darthenay <julien.darthenay@free.fr>

Permission is hereby granted, free of charge, to any person obtaining a
copy of this software and associated documentation files (the
"Software"), to  deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to permit
persons to whom the Software is furnished to do
so, subject to the following conditions:

The above copyright notice and this permission notice shall be included
in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
USE OR OTHER DEALINGS IN THE SOFTWARE.

## How to use

### Adding the library to your project

Use with Java 8 SDK. If you use a previous Java version, you will mainly need
to create a
[Supplier](https://docs.oracle.com/javase/8/docs/api/java/util/function/Supplier.html)
interface to replace the core Java 8 one.

You can either build a jar or add the java source files to your project as you
prefer.

### Use in your code

You should import at least
`fr.juliendarthenay.tools.logging.wrapper.LogManager` and
`fr.juliendarthenay.tools.logging.wrapper.Logger`.
Common practice is to creating a
`private static final Logger LOGGER = LogManager.getLogger(MyClass.class);`
member variable in each class and logging messages with commands such as
`Logger.fatal("This prints a fatal level message.");`.

### Configuration with Java system properties

If you want a behaviour different from default one you will need to set some
JVM system properties. This can be achieved by using Java's `-D` command line
option.

`LogWrapper.ignoreLog4j2` default value is `false`. If set to `true` the
program won't even try to load Log4j 2.

`LogWrapper.silentlySearchLoggingTools` default value is `false`. If set to
`false` a message is print in console if Log4j 2 cannot be loaded.

`LogWrapper.consoleLogger.level` default value is `all`. Valid values are
`off`, `fatal`, `error`, `warn`, `info`, `debug`, `trace` or `all`. It is the
the message level all console loggers will use.

To configure Log4j 2's behaviour please refer to its own documentation.