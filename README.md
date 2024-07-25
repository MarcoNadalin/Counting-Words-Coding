## Table of Contents

---

1. [Introduction](#introduction)
2. [Design Choices](#design-choices)
3. [How To Run](#how-to-run)
   - [JAR executable](#how-to-run-1)
   - [IDE](#how-to-run-2)
4. [Unit Tests](#tests)
5. [Areas For Improvement](#improvement)

---

## Word Analyser Program <a name="introduction"></a>
This program is designed to analyse words given a plaintext source file.

This is achieved by combining Filters and WordAnalysisTask objects.

The Filter class defines a filter which can be applied to a word. 
An example of a filter would be "If a word starts with M or m".

A WordAnalysisTask determines what to do with a word once it has been filtered.
Examples of this can be:
1. Counting how many words pass a given Filter
2. Listing how many words pass a given Filter

The FileProcessor is what reads each word from the source file.
The WordAnalyser simply stores a customisable list of  WordAnalysisTasks, 
where each word read in from the source file is passed to every WordAnalysisTask
within the list.

## Design Choices <a name="design-choices"></a>
1. The FileProcessor was designed to read only one word at a time from the source file 
to prevent performance issues that may arise from storing large files into a single string.
2. The program uses a combination of Filter and WordAnalysisTask interfaces in order to maintain
modularity and extensibility. 
3. The WordAnalyser task holds a list of WordAnalysisTasks so that the source file only has to be read once.

   For example, with 2 WordAnalysisTasks, each word is processed twice but only read once.
4. Maven was used as a build automation tool to replicate common production environments.
5. Java 17 was used for it's LTS support.
6. JUnit was used for unit tests.

## How To Run <a name="how-to-run"></a>
When running this application, two arguments must be provided. 
1. Location of the source text file .
2. The delimiter used to determine what separates one word from another in the source file.

This application can be run in 2 ways:

### 1. Running the executable .jar file <a name="how-to-run-1"></a>
1. Open up a command terminal and `cd` to the directory of the executable .jar. The .jar is located in the following
directory

    `\CSG_Challenge\out\artifacts\WordAnalyser_jar`
2. Use the following command to execute the .jar file:

    `java -jar CSG_Challenge.jar ./sample_text.txt \s+`

### 2. Importing the project into your preferred IDE <a name="how-to-run-2"></a>
If importing this project into an IDE, ensure that the run configuration has the two required command arguments.

## Unit Tests <a name="tests"></a>
![Unit tests](/screenshots/tests_passing.png?raw=true)

## Areas for Improvement <a name="improvement"></a>
1. Command line arguments can be processed more thoroughly to ensure more detailed errors are thrown
to the user when mis-used.
2. Command line arguments can be processed more safely to ensure errors are properly caught.
3. WordAnalysisTask objects should NOT have a `printResults` method. This should be separated into its own class
responsible for printing the results of a WordAnalysisTask.
4. Instead of printing the results to standard output, the results could be saved to an output file instead. 

