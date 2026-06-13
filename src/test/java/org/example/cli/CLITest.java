package org.example.cli;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CLITest {

    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testWithoutParams() {
        String[] mockArgs = {"--r", "R2"};

        CLI cli = new CLI(mockArgs);
        cli.run();

        String actualOutput = outContent.toString().trim();

        assertEquals("ReportProjectsCommand", actualOutput);
    }

    @Test
    void testWithOneParam() {
        String[] mockArgs = {"--r", "R2"};

        CLI cli = new CLI(mockArgs);
        cli.run();

        String actualOutput = outContent.toString().trim();

        assertEquals("ReportProjectsCommand", actualOutput);
    }

    @Test
    void testWithOneSetOfParams() {
        String[] mockArgs2 = {"--help", "--path", "/awda", "--r", "R3"};
        CLI cli2 = new CLI(mockArgs2);
        cli2.run();

        String actualOutput2 = outContent.toString().trim();
        assertEquals("ReportEmployeeProjectsCommand", actualOutput2);

    }

    @Test
    void testWithFewSetsOfParams() {
        String[] mockArgs = {"--r", "R2;","--help", "--path", "/awda", "--r", "R3;", "--r", "R2;" };

        CLI cli = new CLI(mockArgs);
        cli.run();

        String actualOutput = outContent.toString().trim();

        String expectedOutput = String.join(
                System.lineSeparator(),
                "ReportProjectsCommand",
                "ReportEmployeeProjectsCommand",
                "ReportProjectsCommand"
        );

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testofParamsSetsList() {
        String[] mockArgs = {"--r", "R2;","--help", "--path", "/awda", "--r", "R3;", "--r", "R2;",
                "--path", "pathtest", "--from", "testFrom", "--to", "testTo", "--r", "R1", "--out", "testOut"};

        CLI cli = new CLI(mockArgs);

        List<ParamsSet> paramsSets = cli.getParamsSets();

        for (ParamsSet paramsSet : paramsSets) {
            System.out.println(paramsSet.toString());
        }

        String actualOutput = outContent.toString().trim();
        String expectedOutput = String.join(
                System.lineSeparator(),
                "ParamsSet{path='Resources/reporter-dane/2012/01/Kowalski_Jan.xls', from='2010-01-01', to='2027-06-13', whichReport='R2', whichOutput='out1'}",
                "ParamsSet{path='/awda', from='2010-01-01', to='2027-06-13', whichReport='R3', whichOutput='out1'}",
                "ParamsSet{path='Resources/reporter-dane/2012/01/Kowalski_Jan.xls', from='2010-01-01', to='2027-06-13', whichReport='R2', whichOutput='out1'}",
                "ParamsSet{path='pathtest', from='testFrom', to='testTo', whichReport='R1', whichOutput='testOut'}"
        );

        assertEquals(expectedOutput, actualOutput);


    }

    //TODO more tests
}