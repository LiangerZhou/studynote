> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://www.baeldung.com/logback

**1. Overview**
---------------

[Logback](https://logback.qos.ch/) is one of the most widely used logging frameworks in the Java Community. It’s a [replacement for its predecessor, Log4j.](https://logback.qos.ch/reasonsToSwitch.html) Logback offers a faster implementation than Log4j, provides more options for configuration, and more flexibility in archiving old log files.

This introduction will introduce Logback’s architecture and show you how you can use it make your applications better.

**2. Logback Architecture**
---------------------------

Three classes comprise the Logback architecture; _Logger_, _Appender_, and _Layout_.

A logger is a context for log messages. This is the class that applications interact with to create log messages.

Appenders place log messages in their final destinations. A Logger can have more than one Appender. We generally think of Appenders as being attached to text files, but Logback is much more potent than that.

Layout prepares messages for outputting. Logback supports the creation of custom classes for formatting messages, as well as robust configuration options for the existing ones.

**3. Setup**
------------

### **3.1. Maven Dependency**

Logback uses the Simple Logging Facade for Java (SLF4J) as its native interface. Before we can start logging messages, we need to add Logback and Slf4j to our _pom.xml_:

```
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-core</artifactId>
    <version>1.2.3</version>
</dependency>

<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.8.0-beta2</version>
    <scope>test</scope>
</dependency>


```

Maven Central has the [latest version of the Logback Core](https://search.maven.org/classic/#search%7Cgav%7C1%7Cg%3A%22ch.qos.logback%22%20AND%20a%3A%22logback-core%22) and the [most recent version of _slf4j-api_](https://search.maven.org/classic/#search%7Cgav%7C1%7Cg%3A%22org.slf4j%22%20AND%20a%3A%22slf4j-api%22).

### **3.2. Classpath**

Logback also requires [_logback-classic.jar _](https://search.maven.org/classic/#search%7Cga%7C1%7Clogback-classic)on the classpath for runtime.

We’ll add this to _pom.xml_ as a test dependency:

```
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.3</version>
</dependency>


```

4. Basic Example and Configuration
----------------------------------

Let’s start with a quick example of using Logback in an application.

First, we need a configuration file. We’ll create a text file named _logback.xml_ and put it somewhere in our classpath:

```
<configuration>
  <appender >
    <encoder>
      <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
    </encoder>
  </appender>

  <root level="debug">
    <appender-ref ref="STDOUT" />
  </root>
</configuration>

```

Next, we need a simple class with a _main_ method:

```
public class Example {

    private static final Logger logger 
      = LoggerFactory.getLogger(Example.class);

    public static void main(String[] args) {
        logger.info("Example log from {}", Example.class.getSimpleName());
    }
}

```

This class creates a _Logger_ and calls _info()_ to generate a log message.

When we run _Example_ we see our message logged to the console:

```
20:34:22.136 [main] INFO Example - Example log from Example

```

It’s easy to see why Logback is so popular; we’re up in running in minutes.

This configuration and code give us a few hints as to how this works.

1.  We have an _appender _named _STDOUT_ that references a class name _ConsoleAppender._
2.  There is a pattern that describes the format of our log message.
3.  Our code creates a _Logger_ and we passed our message to it via an _info()_ method_._

Now that we understand the basics, let’s have a closer look.

**5. _Logger_ Contexts**
------------------------

### **5.1. Creating a Context**

To log a message to Logback, we initialized a _Logger_ from SLF4J or Logback:

```
private static final Logger logger 
  = LoggerFactory.getLogger(Example.class);


```

And then used it:

```
logger.info("Example log from {}", Example.class.getSimpleName());


```

This is our logging context. When we created it, we passed _LoggerFactory_ our class. This gives the _Logger_ a name (there is also an overload that accepts a _String)._

Logging contexts exist in a hierarchy that closely resembles the Java object hierarchy:

1.  **A logger is an ancestor when its name, followed by a dot, prefixes a descendant logger’s name**
2.  **A logger is a parent when there are no ancestors between it and a child**

For example, the _Example_ class below is in the _com.baeldung.logback_ package. There’s another class named _ExampleAppender_ in the _com.baeldung.logback.appenders_ package.

_ExampleAppender’s Logger_ is a child of _Example’s Logger._

**All loggers are descendants of the predefined root logger.**

A _Logger_ has a _Level,_ which can be set either via configuration or with _Logger.setLevel()._ Setting the level in code overrides configuration files.

The possible levels are, in order of precedence: _TRACE, DEBUG, INFO, WARN_ and _ERROR._Each level has a corresponding method that we use to log a message at that level.

**If a Logger isn’t explicitly assigned a level, it inherits the level of its closest ancestor.** The root logger defaults to _DEBUG._ We’ll see how to override this below.

### **5.2. Using a Context**

Let’s create an example program that demonstrates using a context within logging hierarchies:

```
ch.qos.logback.classic.Logger parentLogger = 
  (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.baeldung.logback");

parentLogger.setLevel(Level.INFO);

Logger childlogger = 
  (ch.qos.logback.classic.Logger)LoggerFactory.getLogger("com.baeldung.logback.tests");

parentLogger.warn("This message is logged because WARN > INFO.");
parentLogger.debug("This message is not logged because DEBUG < INFO.");
childlogger.info("INFO == INFO");
childlogger.debug("DEBUG < INFO");


```

When we run this, we see these messages:

```
20:31:29.586 [main] WARN com.baeldung.logback - This message is logged because WARN > INFO.
20:31:29.594 [main] INFO com.baeldung.logback.tests - INFO == INFO

```

We start by retrieving a _Logger_ named _com.baeldung.logback_ and cast it to a _ch.qos.logback.classic.Logger._

A Logback context is needed to set the level in the next statement; note that the SLF4J’s abstract logger does not implement _setLevel()._

We set the level of our context to _INFO_;we then create another logger named _com.baeldung.logback.tests._

We log two messages with each context to demonstrate the hierarchy. Logback logs the _WARN,_ and _INFO_ messages and filters the _DEBUG_messages.

Now, let’s use the root logger:

```
ch.qos.logback.classic.Logger logger = 
  (ch.qos.logback.classic.Logger)LoggerFactory.getLogger("com.baeldung.logback");
logger.debug("Hi there!");

Logger rootLogger = 
  (ch.qos.logback.classic.Logger)LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
logger.debug("This message is logged because DEBUG == DEBUG.");

rootLogger.setLevel(Level.ERROR);

logger.warn("This message is not logged because WARN < ERROR.");
logger.error("This is logged.");


```

We see these messages when we execute this snippet:

```
20:44:44.241 [main] DEBUG com.baeldung.logback - Hi there!
20:44:44.243 [main] DEBUG com.baeldung.logback - This message is logged because DEBUG == DEBUG.
20:44:44.243 [main] ERROR com.baeldung.logback - This is logged.


```

To conclude, we started with a _Logger_ context and printed a _DEBUG_ message.

We then retrieved the root logger using its statically defined name and set its level to _ERROR._

And finally, we demonstrated that Logback actually does filter any statement less than an error.

### **5.3. Parameterized Messages**

Unlike the messages in the sample snippets above, most useful log messages required appending _Strings._ This entails allocating memory, serializing objects, concatenating _Strings,_ and potentially cleaning up the garbage later.

Consider the following message:

```
log.debug("Current count is " + count);


```

We incur the cost of building the message whether the Logger logs the message or not.

Logback offers an alternative with its parameterized messages:

```
log.debug("Current count is {}", count);


```

**The braces {} will accept any _Object_ and uses its _toString()_ method to build a message only after verifying that the log message is required.**

Let’s try some different parameters:

```
String message = "This is a String";
Integer zero = 0;

try {
    logger.debug("Logging message: {}", message);
    logger.debug("Going to divide {} by {}", 42, zero);
    int result = 42 / zero;
} catch (Exception e) {
    logger.error("Error dividing {} by {} ", 42, zero, e);
}


```

This snippet yields:

```
21:32:10.311 [main] DEBUG com.baeldung.logback.LogbackTests - Logging message: This is a String
21:32:10.316 [main] DEBUG com.baeldung.logback.LogbackTests - Going to divide 42 by 0
21:32:10.316 [main] ERROR com.baeldung.logback.LogbackTests - Error dividing 42 by 0
java.lang.ArithmeticException: / by zero
  at com.baeldung.logback.LogbackTests.givenParameters_ValuesLogged(LogbackTests.java:64)
...


```

We see how a _String,_ an _int,_ and an _Integer_ can be passed in as parameters.

Also, when an _Exception_ is passed as the last argument to a logging method, Logback will print the stack trace for us.

**6. Detailed Configuration**
-----------------------------

In the previous examples, we were using the 11-line configuration file to we created in [section 4](#example) to print log messages to the console. This is Logback’s default behavior; if it cannot find a configuration file, it creates a _ConsoleAppender _and associates it with the root logger.

### **6.1. Locating Configuration Information**

A configuration file can be placed in the classpath and named either _logback.xml_ or _logback-test.xml._

Here’s how Logback will attempt to find configuration data:

1.  Search for files named _logback-test.xml__, logback.groovy,_or _logback.xml_ in the classpath, in that order.
2.  If the library does not find those files, it will attempt to use Java’s _[ServiceLoader](https://docs.oracle.com/javase/6/docs/api/java/util/ServiceLoader.html)_ to locate an implementor of the _com.qos.logback.classic.spi.Configurator._
3.  Configure itself to log output directly to the console

Note: the current version of Logback does not support Groovy configuration due to there not being a version of Groovy compatible with Java 9.

### 6.2. Basic Configuration

Let’s take a closer look at our [example configuration.](#example)

The entire file is in _<configuration>_tags.

**We see a tag that declares an _Appender_ of type _ConsoleAppender_, and names it _STDOUT_. Nested within that tag is an encoder. It has a pattern with what looks like _sprintf-style_ escape codes:**

```
<appender >
    <encoder>
        <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
    </encoder>
</appender>

```

Last, we see a _root_ tag. This tag sets the root logger to _DEBUG_ mode and associates its output with the _Appender_ named _STDOUT_:

```
<root level="debug">
    <appender-ref ref="STDOUT" />
</root>

```

### **6.3. Troubleshooting Configuration**

Logback configuration files can get complicated, so there are several built-in mechanisms for troubleshooting.

To see debug information as Logback processes the configuration, you can turn on debug logging:

```
<configuration debug="true">
  ...
</configuration>

```

Logback will print status information to the console as it processes the configuration:

```
23:54:23,040 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Found resource [logback-test.xml] 
  at [file:/Users/egoebelbecker/ideaProjects/logback-guide/out/test/resources/logback-test.xml]
23:54:23,230 |-INFO in ch.qos.logback.core.joran.action.AppenderAction - About to instantiate appender 
  of type [ch.qos.logback.core.ConsoleAppender]
23:54:23,236 |-INFO in ch.qos.logback.core.joran.action.AppenderAction - Naming appender as [STDOUT]
23:54:23,247 |-INFO in ch.qos.logback.core.joran.action.NestedComplexPropertyIA - Assuming default type 
  [ch.qos.logback.classic.encoder.PatternLayoutEncoder] for [encoder] property
23:54:23,308 |-INFO in ch.qos.logback.classic.joran.action.RootLoggerAction - Setting level of ROOT logger to DEBUG
23:54:23,309 |-INFO in ch.qos.logback.core.joran.action.AppenderRefAction - Attaching appender named [STDOUT] to Logger[ROOT]
23:54:23,310 |-INFO in ch.qos.logback.classic.joran.action.ConfigurationAction - End of configuration.
23:54:23,313 |-INFO in ch.qos.logback.classic.joran.JoranConfigurator@5afa04c - Registering current configuration 
  as safe fallback point

```

If warnings or errors are encountered while parsing the configuration file, Logback writes status messages to the console.

There is a second mechanism for printing status information:

```
<configuration>
    <statusListener class="ch.qos.logback.core.status.OnConsoleStatusListener" />  
    ...
</configuration>

```

**The _StatusListener_ intercepts status messages and prints them during configuration and while the program is running**.

The output from all configuration files is printed, making it useful for locating “rogue” configuration files on the classpath.

### **6.4. Reloading Configuration Automatically**

Reloading logging configuration while an application is running is a powerful troubleshooting tool. Logback makes this possible with the _scan_ parameter:

```
<configuration scan="true">
  ...
</configuration>

```

The default behavior is to scan the configuration file for changes every 60 seconds. Modify this interval by adding _scanPeriod_:

```
<configuration scan="true" scanPeriod="15 seconds">
  ...
</configuration>

```

We can specify values in milliseconds, seconds, minutes, or hours.

### 6.5. Modifying _Loggers_

In our sample file above we set the level of the root logger and associated it with the console _Appender_**. **

We can set the level for any logger:

```
<configuration>
    <appender >
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    <logger  /> 
    <logger  /> 
    <root level="debug">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>

```

Let’s add this to our classpath and run this code:

```
Logger foobar = 
  (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.baeldung.foobar");
Logger logger = 
  (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.baeldung.logback");
Logger testslogger = 
  (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.baeldung.logback.tests");

foobar.debug("This is logged from foobar");
logger.debug("This is not logged from logger");
logger.info("This is logged from logger");
testslogger.info("This is not logged from tests");
testslogger.warn("This is logged from tests");


```

We see this output:

```
00:29:51.787 [main] DEBUG com.baeldung.foobar - This is logged from foobar
00:29:51.789 [main] INFO com.baeldung.logback - This is logged from logger
00:29:51.789 [main] WARN com.baeldung.logback.tests - This is logged from tests


```

By not setting the level of our Loggers programmatically, the configuration sets them; _**com.baeldung.foobar**_ **inherits**_** DEBUG**_ **from the root logger.**

Loggersalso inherit the _appender-ref_ from the root logger. As we’ll see below, we can override this.

### **6.6. Variable Substitution**

Logback configuration files support variables. We define variables inside the configuration script or externally. A variable can be specified at any point in a configuration script in place of a value.

For example, here is the configuration for a _FileAppender_:

```
<property  />
<appender >
    <file>${LOG_DIR}/tests.log</file>
    <append>true</append>
    <encoder>
        <pattern>%-4relative [%thread] %-5level %logger{35} - %msg%n</pattern>
    </encoder>
</appender>

```

At the top of the configuration, we declared a _property_named _LOG_DIR._Then we used it as part of the path to the file inside the _appender_ definition.

Properties are declared in a _<property>_ tag in configuration scripts. But they are also available from outside sources, such as system properties. We could omit the _property_ declaration in this example and set the value of _LOG_DIR_ on the command line:

```
$ java -DLOG_DIR=/var/log/application com.baeldung.logback.LogbackTests

```

We specify the value of the property with _${propertyname}._ Logback implements variables as text replacement. Variable substitution can occur at any point in a configuration file where a value can be specified.

**7. _Appenders_**
------------------

_Loggers_ pass _LoggingEvents_ to _Appenders._ _Appenders_ do the actual work of logging. We usually think of logging as something that goes to a file or the console, but Logback is capable of much more. _Logback-core_ provides several useful appenders.

### **7.1. _ConsoleAppender_**

We’ve seen _ConsoleAppender_in action already. Despite its name, _ConsoleAppender_ appends messages to _System.out_or _System.err._

It uses an _OutputStreamWriter_ to buffer the I/O, so directing it to _System.err_ does not result in unbuffered writing.

### **7.2. _FileAppender_**

_FileAppender_appends messages to a file. It supports a broad range of configuration parameters. Let’s add a file appender to our basic configuration:

```
<configuration debug="true">
    <appender >
        <!-- encoders are assigned the type
             ch.qos.logback.classic.encoder.PatternLayoutEncoder by default -->
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <appender >
        <file>tests.log</file>
        <append>true</append>
        <encoder>
            <pattern>%-4relative [%thread] %-5level %logger{35} - %msg%n</pattern>
        </encoder>
    </appender>
    
    <logger  /> 
    <logger > 
        <appender-ref ref="FILE" /> 
    </logger> 

    <root level="debug">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>

```

The _FileAppender_ is configured with a file name via _<file>._ The _<append>_tag instructs the _Appender_to append to an existing file rather than truncating it. If we run the test several times, we see that the logging output is appended to the same file.

If we re-run our test from above, messages from _com.baeldung.logback.tests_ go to both the console and to a file named tests.log. **The descendant logger inherits the root logger’s association with the _ConsoleAppender_ with its association with _FileAppender._ Appenders are cumulative.**

We can override this behavior:

```
<logger  > 
    <appender-ref ref="FILE" /> 
</logger> 

<root level="debug">
    <appender-ref ref="STDOUT" />
</root>


```

Setting _additivity_ to _false_disables the default behavior. _Tests_ will not log to the console, and neither will any of its descendants.

### **7.3. _RollingFileAppender_**

Oftentimes, appending log messages to the same file is not the behavior we need. We want files to “roll” based on time, log file size, or a combination of both.

For this, we have _RollingFileAppender:_

```
<property  />
<appender >
    <file>${LOG_FILE}.log</file>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <!-- daily rollover -->
        <fileNamePattern>${LOG_FILE}.%d{yyyy-MM-dd}.gz</fileNamePattern>

        <!-- keep 30 days' worth of history capped at 3GB total size -->
        <maxHistory>30</maxHistory>
        <totalSizeCap>3GB</totalSizeCap>
    </rollingPolicy>
    <encoder>
        <pattern>%-4relative [%thread] %-5level %logger{35} - %msg%n</pattern>
    </encoder>
</appender> 


```

A _RollingFileAppender_has a _RollingPolicy._In this sample configuration, we see a _TimeBasedRollingPolicy._

Similar to the _FileAppender,_ we configured this appender with a file name. We declared a property and used it for this because we’ll be reusing the file name below.

We define a _fileNamePattern_ inside the _RollingPolicy._This pattern defines not just the name of files, but also how often to roll them. _TimeBasedRollingPolicy_examines the pattern and rolls at the most finely defined period.

For example:

```
<property  />
<property  />
<appender >
    <file>${LOG_DIR}/${LOG_FILE}.log</file> 
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <fileNamePattern>${LOG_DIR}/%d{yyyy/MM}/${LOG_FILE}.gz</fileNamePattern>
        <totalSizeCap>3GB</totalSizeCap>
    </rollingPolicy>

```

The active log file is _/var/logs/application/LogFile._This file rolls over at the beginning of each month into _/Current Year/Current Month/LogFile.gz_and _RollingFileAppender_ createsa new active file.

When the total size of archived files reaches 3GB, _RollingFileAppender_deletes archives on a first-in-first-out basis.

There are codes for a week, hour, minute, second, and even millisecond. Logback has a reference [here](https://logback.qos.ch/manual/appenders.html#TimeBasedRollingPolicy).

_RollingFileAppender_also has built-in support for compressing files. It compresses our rolled files because named our them _LogFile.gz._

_TimeBasedPolicy_isn’t our only option for rolling files. Logback also offers _SizeAndTimeBasedRollingPolicy,_which will roll based on current log file size as well as time. It also offers a _FixedWindowRollingPolicy_which rolls log file names each time the logger is started.

We can also write our own _[RollingPolicy](https://logback.qos.ch/manual/appenders.html#onRollingPolicies)._

### **7.4. Custom Appenders**

We can create custom appenders by extending one of Logback’s base appender classes. We have a tutorial for creating custom appenders [here](https://www.baeldung.com/custom-logback-appender).

**8. _Layouts_**
----------------

_Layouts_ format log messages. Like the rest of Logback, _Layouts_are extensible and we can [create our own.](https://logback.qos.ch/manual/layouts.html#writingYourOwnLayout) However, the default _PatternLayout_ offers what most applications need and then some.

We’ve used _PatternLayout_ in all of our examples so far:

```
<encoder>
    <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
</encoder>


```

This configuration script contains the configuration for _PatternLayoutEncoder._We pass an _Encoder_ to our _Appender,_and this encoder uses the _PatternLayout_ to format the messages.

The text in the _<pattern>_ tag defines how log messages are formatting. **_PatternLayout_ implements a large variety of conversion words and format modifiers for creating patterns.**

Let’s break this one down. _PatternLayout_ recognizes conversion words with a %, so the conversions in our pattern generate:

*   _%d{HH:mm:ss.SSS}_ – a timestamp with hours, minutes, seconds and milliseconds
*   _[%thread]_ – the thread name generating the log message, surrounded by square brackets
*   _%-5level_ – the level of the logging event, padded to 5 characters
*   _%logger{36}_ – the name of the logger, truncated to 35 characters
*   _%msg%n_ – the log messages followed by the platform dependent line separator character

So we see messages similar to this:

```
21:32:10.311 [main] DEBUG com.baeldung.logback.LogbackTests - Logging message: This is a String

```

An exhaustive list of conversion words and format modifiers can be found [here](https://logback.qos.ch/manual/layouts.html#conversionWord).

9. Conclusion
-------------

In this extensive guide, we covered the fundamentals of using Logback in an application.

We looked at the three major components in Logback’s architecture: loggers, appenders, and layout. Logback’s has powerful configuration scripts, which we used to manipulate components for filtering and formatting messages. We also looked at the two most commonly used file appenders to create, roll over, organize, and compress log files.

As usual, code snippets can be found [over on GitHub](https://github.com/eugenp/tutorials/tree/master/logging-modules/logback).

### **I just announced the new _Learn Spring_ course, focused on the fundamentals of Spring 5 and Spring Boot 2:**

**[>> CHECK OUT THE COURSE](https://www.baeldung.com/ls-course-end)**