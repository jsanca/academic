
package jsanca.artofjava.cap3;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BasicInterpreterTest {

    @Test
    void shouldRunHelloWorldProgram() throws Exception {
        String program = """
                10 PRINT \"Hello, World!\"
                20 END
                """;

        String output = runProgram(program);

        assertEquals("Hello, World!\n", output);
    }

    @Test
    void shouldRunAssignmentAndArithmeticProgram() throws Exception {
        String program = """
                10 A = 5
                20 B = A * 2 + 3
                30 PRINT B
                40 END
                """;

        String output = runProgram(program);

        assertEquals("13.0\n", output);
    }

    @Test
    void shouldRunForLoopAccumulationProgram() throws Exception {
        String program = """
                10 S = 0
                20 FOR I = 1 TO 3
                30 S = S + I
                40 NEXT
                50 PRINT S
                60 END
                """;

        String output = runProgram(program);

        assertEquals("6.0\n", output);
    }

    @Test
    void shouldRunIfThenProgramWhenConditionIsTrue() throws Exception {
        String program = """
                10 A = 7
                20 IF A > 5 THEN PRINT \"BIG\"
                30 END
                """;

        String output = runProgram(program);

        assertEquals("BIG\n", output);
    }

    @Test
    void shouldSkipIfThenBodyWhenConditionIsFalse() throws Exception {
        String program = """
                10 A = 2
                20 IF A > 5 THEN PRINT \"BIG\"
                30 PRINT \"SMALL\"
                40 END
                """;

        String output = runProgram(program);

        assertEquals("SMALL\n", output);
    }

    @Test
    void shouldRunGotoProgram() throws Exception {
        String program = """
                10 GOTO 30
                20 PRINT \"SHOULD NOT APPEAR\"
                30 PRINT \"JUMPED\"
                40 END
                """;

        String output = runProgram(program);

        assertEquals("JUMPED\n", output);
    }

    @Test
    void shouldRunGosubAndReturnProgram() throws Exception {
        String program = """
                10 GOSUB 100
                20 PRINT \"DONE\"
                30 END
                100 PRINT \"SUBROUTINE\"
                110 RETURN
                """;

        String output = runProgram(program);

        assertEquals("SUBROUTINE\nDONE\n", output);
    }

    @Test
    void shouldRunInputProgramWithPrompt() throws Exception {
        String program = """
                10 INPUT \"Enter number:\", A
                20 PRINT A + 1
                30 END
                """;

        String output = runProgram(program, "41\n");

        assertEquals("Enter number:42.0\n", output);
    }

    @Test
    void shouldRunInputProgramWithDefaultPrompt() throws Exception {
        String program = """
                10 INPUT A
                20 PRINT A * 2
                30 END
                """;

        String output = runProgram(program, "3\n");

        assertEquals("? 6.0\n", output);
    }

    @Test
    void shouldPrintUsingSemicolonAndCommaFormatting() throws Exception {
        String program = """
                10 PRINT \"A\";\"B\",1
                20 END
                """;

        String output = runProgram(program);

        assertEquals("A B     1.0\n", output);
    }

    @Test
    void shouldThrowWhenProgramFileDoesNotExist() {
        InterpreterException exception = assertThrows(
                InterpreterException.class,
                () -> new BasicInterpreter("/definitely/missing/file.bas")
        );

        assertEquals("File not found", exception.getMessage());
    }

    @Test
    void shouldThrowWhenProgramContainsDuplicateLabels() {
        String program = """
                10 PRINT \"ONE\"
                10 PRINT \"TWO\"
                20 END
                """;

        InterpreterException exception = assertThrows(
                InterpreterException.class,
                () -> runProgram(program)
        );

        assertEquals("Duplicate label", exception.getMessage());
    }

    @Test
    void shouldThrowWhenGotoLabelIsUndefined() {
        String program = """
                10 GOTO 999
                20 END
                """;

        InterpreterException exception = assertThrows(
                InterpreterException.class,
                () -> runProgram(program)
        );

        assertEquals("Undefined label", exception.getMessage());
    }

    @Test
    void shouldThrowWhenReturnHasNoMatchingGosub() {
        String program = """
                10 RETURN
                20 END
                """;

        InterpreterException exception = assertThrows(
                InterpreterException.class,
                () -> runProgram(program)
        );

        assertEquals("RETURN without GOSUB", exception.getMessage());
    }

    @Test
    void shouldThrowWhenNextHasNoMatchingFor() {
        String program = """
                10 NEXT
                20 END
                """;

        InterpreterException exception = assertThrows(
                InterpreterException.class,
                () -> runProgram(program)
        );

        assertEquals("NEXT without FOR", exception.getMessage());
    }

    @Test
    void shouldThrowWhenIfIsMissingThen() {
        String program = """
                10 IF 1 PRINT \"BAD\"
                20 END
                """;

        InterpreterException exception = assertThrows(
                InterpreterException.class,
                () -> runProgram(program)
        );

        assertEquals("THEN expected", exception.getMessage());
    }

    private static String runProgram(String program) throws Exception {
        return runProgram(program, "");
    }

    private static String runProgram(String program, String input) throws Exception {
        Path tempFile = Files.createTempFile("basic-interpreter-test-", ".bas");
        Files.writeString(tempFile, program, StandardCharsets.UTF_8);

        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream capture = new PrintStream(outputStream, true, StandardCharsets.UTF_8);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));

        try {
            System.setOut(capture);
            System.setIn(inputStream);

            BasicInterpreter interpreter = new BasicInterpreter(tempFile.toString());
            interpreter.run();

            capture.flush();
            return outputStream.toString(StandardCharsets.UTF_8);
        } finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
            Files.deleteIfExists(tempFile);
        }
    }
}
