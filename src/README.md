## Word Analyser Program
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
where each word read in from the source file is parsed through every WordAnalysisTask
within the list.

## Design Choices
1. The FileProcessor was designed to read only one word at a time from the source file 
to prevent performance issues that may arise from storing large files into a single string.
2. The program uses a combination of Filter and WordAnalysisTask interfaces in order to maintain
modularity and extensibility. 
3. The WordAnalyser task holds a list of WordAnalysisTasks so that the source file only has to be read once.
Each word is processed twice, but only read once.

## How To Run
Program uses command line arguments.
Argument 1: Source file directory
Argument 2: word delimiter. 
Here is an example of what the command line arguments may look like:

`src/sample_text.txt \s+`