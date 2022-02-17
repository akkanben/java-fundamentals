# Linter App

This linter app is a does a simple check to see if semicolons are present in JavaScript files. The checks are not exhaustive and some proper JavaScript will be marked as an error i.e. 'use strict' etc.

These are the only current rules for the linter:
- The linter will not show an error if the line is empty.
- The linter will not show an error if the line ends with an opening curly brace `{`
- The linter will not show an error if the line ends with an closing curly brace `}`
- The linter will not show an error if the line contains `if` or `else`
- All other lines that do not end in a semicolon will cause an error.

If run directly the linter app will process the file `app/src/main/resources/gates.js`. The linter can also process any user file with a command line argument. 

**Command line argument instructions:**

1. Navigate to `app/src/main/java/liner/`.
2. Run with `java App.java path/to/your/file.js`

Alternatively the full path to your file can be provied as a command line argument within Intellij IDEA.
