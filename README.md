# Java Fundamentals -- Labs 01-10

## Lab 01

## Lab 02

## Lab 03 -- Maps, File I/O, and Testing Setup

- Added a method to the basiclibrary that analizes weather data. The method will find the highest and lowest temperature days and then increment one degree at a time from low to high and log any temperatures that were not found.
- Added another method to the basiclibrary that tallied votes from a list of names. The name with the most votes will be returned.
- Linter App checks JavaScript files for correct syntax. Currently the linter only checks for semicolons at the appropriate lines. The app can take cli arguments.
- Unit tests are included with the two new library methods and the linter app.

## Lab 04 -- Bitmap

The bitmap transformer app can take an inputfile from the command line and apply a transformation. Command Line app can be run from the `bitmap-transformer` directory. The app can be run with Gradle and requires 3 command line arguments.

To Use:
1. Place your .bmp file to be transformed in the `bitmap-transformer/app/src/main/resources` directory. The existing file "baldy-8bit.bmp" will be used in this example.
2. Decide on your transform. Transform arguments include:
  - "copy" to duplicate with no transform.
  - "grayscale" to remove color data.
  - "invert" to invert the image to resemble a negative.
  - "randomize" to give each pixel a random color.
  - "mirror-h" to mirror the image horizontally.
  - "mirror-v" to mirror the image vertically.
3. Apply the transform to the output file you specify (.bmp) with `./gradlew run --args "baldy-8bit.bmp out.bmp grayscale"`.
4. The transformed image can be found in the same resources folder that the input file was placed.

## Lab 05 -- Composition and Inheritance, Part 1

The inheritance library contains a restaurant package and review package that can be used together to create Restaurant objects and Review objects. 

- The star review system in place has a range of 0 to 5 stars. 
- There is also a price score that is in the range of 0 to 3 (inexpensive to expensive).

The Restaurant class has a list of its RestaurantReview objects. If a Restaurant object's star review score is requested the average score is calculated based on what is in the list of RestaurantReview objects.

The RestaurantReview object is a subclass of Review and requires a Restaurant object for instantiation. When the RestaurantReview object is created it is added to the Restaurant object's list of reviews and if the RestaurantReview object is updated (star score) it will be reflected in the Restaurant object's list and the Restaurant object's total rating.


Testing:

- The classes in the restaurant and review packages have corresponding tests files and individual tests for the methods of the classes.
- run `./gradlew test` from the `inheritance` directory.
