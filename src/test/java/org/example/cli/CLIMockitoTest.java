package org.example.cli;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

class CLIMockitoTest {

    @Test
    void testWithoutParams() {
        String[] mockArgs = {};

        try (
                MockedStatic<ReportEmployeesCommand> employeesCommand =
                        mockStatic(ReportEmployeesCommand.class);
                MockedStatic<ReportProjectsCommand> projectsCommand =
                        mockStatic(ReportProjectsCommand.class);
                MockedStatic<ReportEmployeeProjectsCommand> employeeProjectsCommand =
                        mockStatic(ReportEmployeeProjectsCommand.class);
                MockedStatic<ReportTaskRankingCommand> taskRankingCommand =
                        mockStatic(ReportTaskRankingCommand.class)
        ) {
            CLI cli = new CLI(mockArgs);

            assertEquals(1, cli.getParamsSets().size());

            ParamsSet params = cli.getParamsSets().get(0);

            cli.run();

            employeesCommand.verify(
                    () -> ReportEmployeesCommand.execute(params)
            );

            projectsCommand.verifyNoInteractions();
            employeeProjectsCommand.verifyNoInteractions();
            taskRankingCommand.verifyNoInteractions();
        }
    }

    @Test
    void testWithIncompleteParam() {
        String[] mockArgs = {"--r"};

        try (
                MockedStatic<ReportEmployeesCommand> employeesCommand =
                        mockStatic(ReportEmployeesCommand.class);
                MockedStatic<ReportProjectsCommand> projectsCommand =
                        mockStatic(ReportProjectsCommand.class);
                MockedStatic<ReportEmployeeProjectsCommand> employeeProjectsCommand =
                        mockStatic(ReportEmployeeProjectsCommand.class);
                MockedStatic<ReportTaskRankingCommand> taskRankingCommand =
                        mockStatic(ReportTaskRankingCommand.class)
        ) {
            CLI cli = new CLI(mockArgs);

            assertEquals(1, cli.getParamsSets().size());

            ParamsSet defaultParams = cli.getParamsSets().get(0);

            cli.run();

            employeesCommand.verify(
                    () -> ReportEmployeesCommand.execute(defaultParams)
            );

            projectsCommand.verifyNoInteractions();
            employeeProjectsCommand.verifyNoInteractions();
            taskRankingCommand.verifyNoInteractions();
        }
    }

    @Test
    void testWithOneParam() {
        String[] mockArgs = {"--r", "R2"};

        try (
                MockedStatic<ReportProjectsCommand> projectsCommand =
                        mockStatic(ReportProjectsCommand.class);
                MockedStatic<ReportEmployeesCommand> employeesCommand =
                        mockStatic(ReportEmployeesCommand.class);
                MockedStatic<ReportEmployeeProjectsCommand> employeeProjectsCommand =
                        mockStatic(ReportEmployeeProjectsCommand.class);
                MockedStatic<ReportTaskRankingCommand> taskRankingCommand =
                        mockStatic(ReportTaskRankingCommand.class)
        ) {
            CLI cli = new CLI(mockArgs);

            assertEquals(1, cli.getParamsSets().size());

            ParamsSet params = cli.getParamsSets().get(0);

            cli.run();

            projectsCommand.verify(
                    () -> ReportProjectsCommand.execute(params)
            );

            employeesCommand.verifyNoInteractions();
            employeeProjectsCommand.verifyNoInteractions();
            taskRankingCommand.verifyNoInteractions();
        }
    }

    @Test
    void testWithOneSetOfParams() {
        String[] mockArgs = {
                "--help",
                "--path", "/awda",
                "--r", "R3"
        };

        try (
                MockedStatic<ReportEmployeeProjectsCommand> employeeProjectsCommand =
                        mockStatic(ReportEmployeeProjectsCommand.class);
                MockedStatic<ReportEmployeesCommand> employeesCommand =
                        mockStatic(ReportEmployeesCommand.class);
                MockedStatic<ReportProjectsCommand> projectsCommand =
                        mockStatic(ReportProjectsCommand.class);
                MockedStatic<ReportTaskRankingCommand> taskRankingCommand =
                        mockStatic(ReportTaskRankingCommand.class)
        ) {
            CLI cli = new CLI(mockArgs);

            assertEquals(1, cli.getParamsSets().size());

            ParamsSet params = cli.getParamsSets().get(0);

            cli.run();

            employeeProjectsCommand.verify(
                    () -> ReportEmployeeProjectsCommand.execute(params)
            );

            employeesCommand.verifyNoInteractions();
            projectsCommand.verifyNoInteractions();
            taskRankingCommand.verifyNoInteractions();
        }
    }

    @Test
    void testWithFewSetsOfParams() {
        String[] mockArgs = {
                "--r", "R2;",
                "--help",
                "--path", "/awda",
                "--r", "R3;",
                "--r", "R2;"
        };

        try (
                MockedStatic<ReportProjectsCommand> projectsCommand =
                        mockStatic(ReportProjectsCommand.class);
                MockedStatic<ReportEmployeeProjectsCommand> employeeProjectsCommand =
                        mockStatic(ReportEmployeeProjectsCommand.class);
                MockedStatic<ReportEmployeesCommand> employeesCommand =
                        mockStatic(ReportEmployeesCommand.class);
                MockedStatic<ReportTaskRankingCommand> taskRankingCommand =
                        mockStatic(ReportTaskRankingCommand.class)
        ) {
            CLI cli = new CLI(mockArgs);
            List<ParamsSet> paramsSets = cli.getParamsSets();

            assertEquals(3, paramsSets.size());

            cli.run();

            projectsCommand.verify(
                    () -> ReportProjectsCommand.execute(paramsSets.get(0))
            );

            employeeProjectsCommand.verify(
                    () -> ReportEmployeeProjectsCommand.execute(paramsSets.get(1))
            );

            projectsCommand.verify(
                    () -> ReportProjectsCommand.execute(paramsSets.get(2))
            );

            projectsCommand.verifyNoMoreInteractions();
            employeeProjectsCommand.verifyNoMoreInteractions();
            employeesCommand.verifyNoInteractions();
            taskRankingCommand.verifyNoInteractions();
        }
    }

//    @Test
//    void testParamsSetsList() {
//        String[] mockArgs = {
//                "--r", "R2;",
//                "--help",
//                "--path", "/awda",
//                "--r", "R3;",
//                "--r", "R2;",
//                "--path", "pathtest",
//                "--from", "testFrom",
//                "--to", "testTo",
//                "--r", "R1",
//                "--out", "testOut"
//        };
//
//        CLI cli = new CLI(mockArgs);
//        List<ParamsSet> paramsSets = cli.getParamsSets();
//
//        assertEquals(4, paramsSets.size());
//
//        String defaultTo = LocalDate.now()
//                .plusDays(365)
//                .toString();
//
//        assertParamsSet(
//                paramsSets.get(0),
//                "Resources/reporter-dane/2012/01/Kowalski_Jan.xls",
//                "2010-01-01",
//                defaultTo,
//                "R2",
//                "out1"
//        );
//
//        assertParamsSet(
//                paramsSets.get(1),
//                "/awda",
//                "2010-01-01",
//                defaultTo,
//                "R3",
//                "out1"
//        );
//
//        assertParamsSet(
//                paramsSets.get(2),
//                "Resources/reporter-dane/2012/01/Kowalski_Jan.xls",
//                "2010-01-01",
//                defaultTo,
//                "R2",
//                "out1"
//        );
//
//        assertParamsSet(
//                paramsSets.get(3),
//                "pathtest",
//                "testFrom",
//                "testTo",
//                "R1",
//                "testOut"
//        );
//    }

    private void assertParamsSet(
            ParamsSet actual,
            String expectedPath,
            String expectedFrom,
            String expectedTo,
            String expectedReport,
            String expectedOutput
    ) {
        assertAll(
                () -> assertEquals(expectedPath, actual.getPath()),
                () -> assertEquals(expectedFrom, actual.getFrom()),
                () -> assertEquals(expectedTo, actual.getTo()),
                () -> assertEquals(expectedReport, actual.getWhichReport()),
                () -> assertEquals(expectedOutput, actual.getWhichOutput())
        );
    }
}