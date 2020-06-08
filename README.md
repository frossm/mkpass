

## pwgen

pwgen is a simple command line utility to generate secure random passwords.  You can easily redirect these to a clipboard or copy and paste when needed.
I use a password manager and generate random passwords everytime I need one.  And while most password managers include a generator, there are times I need one outside of that.
The code is written in java so should run on any OS that supports it.
Lastly, the idea and core code came from a Wikipedia article.  I just took this and expanded upon it.
[https://en.wikipedia.org/wiki/Random_password_generator](https://en.wikipedia.org/wiki/Random_password_generator)

## Defaults
By default, pwgen will create a 30 character password and will use special characters.

## Command line Options
|Option|Description  |
|--|--|
|-l|Length of generated password.  Default is 30|
|-p|Use a plain password without special characters|
|-? or -h| Show Usage information|
|-D|Debug mode.  Mostly used by me to show extra debugging information|

## Linux Note
Had an issue with my Ubuntu notebook where SecureRandom would hang.  See this [Oracle Java Link](https://docs.oracle.com/cd/E13209_01/wlcp/wlss30/configwlss/jvmrand.html).  The detail is below.

**Avoiding JVM Delays Caused by Random Number Generation**
The library used for random number generation in Sun's JVM relies on /dev/random by default for UNIX platforms. This can potentially block the WebLogic SIP Server process because on some operating systems /dev/random waits for a certain amount of "noise" to be generated on the host machine before returning a result. Although /dev/random is more secure, BEA recommends using /dev/urandom if the default JVM configuration delays WebLogic SIP Server startup.

To determine if your operating system exhibits this behavior, try displaying a portion of the file from a shell prompt:

    head -n 1 /dev/random

If the command returns immediately, you can use /dev/random as the default generator for SUN's JVM. If the command does not return immediately, use these steps to configure the JVM to use /dev/urandom:

Open the $JAVA_HOME/jre/lib/security/java.security file in a text editor.
Change the line:

    securerandom.source=file:/dev/random

to read:

    securerandom.source=file:/dev/urandom

Alternatively, you can add the following java flag:

    -Djava.security.egd=file:/dev/./urandom


## Feedback
If you have ideas or issues, please let me know.  There is quite a bit more error checking that could probably be added.

pwgen at fross dot org

## License
[The MIT License](https://opensource.org/licenses/MIT)  [https://opensource.org/licenses/MIT](https://opensource.org/licenses/MIT)

Copyright 2019 by Michael Fross

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
