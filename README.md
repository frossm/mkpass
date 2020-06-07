
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

## Feedback
If you have ideas or issues, please let me know.  There is quite a bit more error checking that could probably be added.

pwgen at fross dot org

## License
[The MIT License](https://opensource.org/licenses/MIT)  [https://opensource.org/licenses/MIT](https://opensource.org/licenses/MIT)

Copyright 2019 by Michael Fross

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
