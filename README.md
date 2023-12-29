<p align="center"> <img width="80%" src ="https://github.com/frossm/mkpass/raw/master/graphics/ScreenShot.jpg"> </p> 

## INTRODUCTION

## mkpass

<img align="right" width="200" src="https://github.com/frossm/mkpass/raw/master/graphics/PostIt-512.png">mkpass is a simple command line utility to generate secure random passwords.  You can easily redirect these to a clipboard or copy and paste as needed.

I use a password manager and generate random passwords everytime I need one.  And while most password managers include a generator, there are times I need one outside of that.

The code is written in java so it should run on any OS that supports it.  Lastly, the idea and core code came from a Wikipedia article.  I just took this and expanded upon it.

[https://en.wikipedia.org/wiki/Random_password_generator](https://en.wikipedia.org/wiki/Random_password_generator)

## Defaults
By default, mkpass will create a 30 character password using the following numbers, letters, and symbols.  This can be modified via the command line options.
- `0 1 2 3 4 5 6 7 8 9`
- `a b c d e f g h i j`
- `k l m n o p q r s t`
- `u v w x y z A B C D`
- `E F G H I J K L M N`
- `O P Q R S T U V W X`
- `Y Z ! @ # $ % ^ & *`
- `~ - + _ =`

## Command line Options
|Option|Description|
|--|--|
|-l length|Set the length of the generated password.  The default is 30 characters|
|-p|Use a plain password without any special characters.  Therefore, the password would be only numbers and upper & lower case letters|
|-c chars|Use these characters instead of the default special symbols.  Useful if password only allows certain special characters|
|-n number|Generate <num> passwords|
|-s|Show the characters that will be used in the password generation|
|-? or -h| Show Usage information|
|-D|Debug mode.  Mostly used by the developer to show extra debugging information|

## Linux Note
I had an issue with my Ubuntu notebook where SecureRandom would hang.  Apparently this is a known issue where /dev/random blocks until it has enough entropy to produce a secure random number.  The solution is documented at the link below.

**Codes and Notes** explains the situation [here](https://www.codesandnotes.be/2018/09/18/strong-random-number-generation-hangs-on-linux-machines/). It boils down to installing "rng-tools" which will feed entropy into the system.

For ubuntu based systems: `sudo apt install rng-tools`

Additional information can be found on in the [Oracle docs.](https://docs.oracle.com/cd/E13209_01/wlcp/wlss30/configwlss/jvmrand.html)

## SNAP

[![mkpass](https://snapcraft.io//mkpass/badge.svg)](https://snapcraft.io/mkpass)

I would encourage anyone with a supported Linux platform to use snap.  See [Snapcraft Homepage](https://snapcraft.io) for more information. You can download, install, and keep the application up to date automatically by installi
ng the snap via :

`sudo snap install mkpass`  (Assuming snap is installed)

This will install the application into a sandbox where it is separate from other applications.  I do want to look at packaging it via Flatpak as well, but my understanding is that Maven is not well supported.  However, I need to do more investigation.

[![Get it from the Snap Store](https://snapcraft.io/static/images/badges/en/snap-store-black.svg)](https://snapcraft.io/mkpass)

## Feedback
If you have ideas or issues, please let me know.  There is quite a bit more error checking that could probably be added.

mkpass at fross dot org

## License
[The MIT License](https://opensource.org/licenses/MIT)

Copyright 2020-2024 by Michael Fross

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
