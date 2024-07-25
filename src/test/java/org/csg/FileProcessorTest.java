package org.csg;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

public class FileProcessorTest {

    private static final String TEST_FILE_DIR = "test.txt";
    private static final String WORD_DELIMITER = "\\s+";

    @BeforeAll
    public static void setUp() throws IOException {
        // Create a test file
        try {
            FileWriter writer = new FileWriter(TEST_FILE_DIR);
            writer.write("word1 word2\nword3\nword4 word5");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    public static void tearDown() {
        // Delete the test file
        File file = new File(TEST_FILE_DIR);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testFileProcessorReadsWordsCorrectly() {
        FileProcessor fileProcessor = new FileProcessor(TEST_FILE_DIR, WORD_DELIMITER);

        assertEquals("word1", fileProcessor.getNextWord());
        assertEquals("word2", fileProcessor.getNextWord());
        assertEquals("word3", fileProcessor.getNextWord());
        assertEquals("word4", fileProcessor.getNextWord());
        assertEquals("word5", fileProcessor.getNextWord());
        assertNull(fileProcessor.getNextWord(), "End of file.");
    }

    @Test
    public void testFileProcessorHandlesFileNotFound() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new FileProcessor("nonexistentfile.txt", WORD_DELIMITER);
        });

        String expectedMessage = "File at: [nonexistentfile.txt] not found.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "Expected file not found message.");
    }

    @Test
    public void testFileProcessorHandlesEmptyFile() throws IOException {
        // Create an empty test file
        String emptyFileDir = "emptyTestFile.txt";
        FileWriter writer = new FileWriter(emptyFileDir);
        writer.close();

        FileProcessor fileProcessor = new FileProcessor(emptyFileDir, WORD_DELIMITER);
        assertNull(fileProcessor.getNextWord(), "Expected null for empty file.");

        // Clean up
        File file = new File(emptyFileDir);
        if (file.exists()) {
            file.delete();
        }
    }
}