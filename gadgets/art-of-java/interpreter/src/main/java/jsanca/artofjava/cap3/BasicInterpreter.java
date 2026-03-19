package jsanca.artofjava.cap3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * A simple interpreter for a BASIC-like language.
 *
 * <p>This class coordinates the execution of a BASIC program by combining
 * lexical analysis ({@link BasicLexer}), expression evaluation
 * ({@link ExpressionParser}), and runtime state management.</p>
 *
 * <p>The interpreter supports core BASIC constructs such as:</p>
 * <ul>
 *   <li>Variable assignment</li>
 *   <li>PRINT and INPUT</li>
 *   <li>Conditional execution (IF / THEN)</li>
 *   <li>Control flow (GOTO, GOSUB, RETURN)</li>
 *   <li>Loops (FOR / NEXT)</li>
 * </ul>
 *
 * <p>The runtime model is intentionally simple and uses:</p>
 * <ul>
 *   <li>A fixed array for variables (A-Z)</li>
 *   <li>A label table for line numbers</li>
 *   <li>Stacks for FOR loops and GOSUB calls</li>
 * </ul>
 *
 * <p>This implementation is educational and inspired by classic interpreter designs.</p>
 * @author jsanca & elo
 */
public class BasicInterpreter {

    private static final int PROG_SIZE = 10000;

    private final char[] program;
    private final BasicLexer lexer;
    private final ExpressionParser expressionParser;

    private final double[] variableValues = new double[26];
    private final Map<String, Integer> labelTableMap = new TreeMap<>();
    private final Deque<ForInfo> forStack = new ArrayDeque<>();
    private final Deque<Integer> gosubStack = new ArrayDeque<>();

    /**
     * Creates a new interpreter instance by loading a BASIC program from a file.
     *
     * @param programFile path to the BASIC program file
     * @throws InterpreterException if the program cannot be loaded
     */
    public BasicInterpreter(final String programFile) throws InterpreterException {

        this.program = loadProgram(programFile);
        this.lexer   = new BasicLexer(this.program);
        this.expressionParser = new ExpressionParser(this.lexer, this::findVar);
    }

    /**
     * Executes the loaded BASIC program.
     *
     * <p>This method resets runtime state, scans labels, and starts interpretation.</p>
     *
     * @throws InterpreterException if a runtime or syntax error occurs
     */
    public void run() throws InterpreterException {

        this.resetRuntimeState();
        this.scanLabels();
        this.lexer.rewindToProgramStart();
        this.interpret();
    }

    /**
     * Main interpreter loop.
     *
     * <p>Continuously reads tokens from the lexer and dispatches execution
     * based on token type or keyword.</p>
     *
     * @throws InterpreterException if a syntax or runtime error occurs
     */
    private void interpret() throws InterpreterException {

        while (true) {

            final  BasicToken token = this.lexer.nextToken();

            // if it is the end of the program, exit
            if (token.type() == BasicTokenType.EOP) {
                return;
            }

            if (token.type() == BasicTokenType.EOL) {
                continue;
            }

            if (token.type() == BasicTokenType.NUMBER) {
                continue;
            }

            if (token.type() == BasicTokenType.VARIABLE) {

                this.lexer.putBack(token);
                this.assignment();
                continue;
            }

            switch (token.keyword()) {
                case PRINT -> print();
                case GOTO -> execGoto();
                case IF -> execIf();
                case FOR -> execFor();
                case NEXT -> next();
                case INPUT -> input();
                case GOSUB -> gosub();
                case RETURN -> greturn();
                case END -> {
                    return;
                }
                default -> throw new InterpreterException("Syntax Error");
            }
        }
    }

    /**
     * Handles variable assignment statements.
     *
     * <p>Expected syntax: VARIABLE = expression</p>
     *
     * @throws InterpreterException if syntax is invalid
     */
    private void assignment() throws InterpreterException {

        final BasicToken variableToken = this.lexer.nextToken();
        final char variableName = variableToken.text().charAt(0);

        if (!Character.isLetter(variableName)) {
            throw new InterpreterException("Not a variable");
        }

        // Map variable name (A-Z) to array index (0-25).
        // Example: 'A' -> 0, 'B' -> 1, ..., 'Z' -> 25.
        // This allows O(1) access using a fixed-size array instead of a Map.
        final int variableIndex = Character.toUpperCase(variableName) - 'A';

        final BasicToken equals = this.lexer.nextToken();
        if (!equals.isText("=")) {
            throw new InterpreterException("Equal sign expected");
        }

        final double value = this.expressionParser.evaluate();
        this.variableValues[variableIndex] = value;
    }

    /**
     * Executes the PRINT statement.
     *
     * <p>Supports printing expressions and quoted strings, with BASIC-style
     * formatting using ',' and ';' delimiters.</p>
     *
     * @throws InterpreterException if syntax is invalid
     */
    private void print() throws InterpreterException {

        String lastDelimiter = "";
        int len = 0;

        while (true) {

            final BasicToken token = this.lexer.nextToken();

            if (isEndOfLineOrEndOfProgram(token)) {
                if (!lastDelimiter.equals(";") && !lastDelimiter.equals(",")) {
                    System.out.println();
                }
                return;
            }

            if (token.type() == BasicTokenType.QUOTED_STRING) {

                System.out.print(token.text());
                len += token.text().length();
            } else {

                this.lexer.putBack(token);
                final double result = expressionParser.evaluate();
                System.out.print(result);
                len += Double.toString(result).length();
            }

            final BasicToken delimiter = this.lexer.nextToken();
            lastDelimiter = delimiter.text();

            if (delimiter.isText(",")) {

                int spaces = 8 - (len % 8);
                len += spaces;
                while (spaces-- > 0) {
                    System.out.print(" ");
                }
            } else if (delimiter.isText(";")) {
                System.out.print(" ");
                len++;
            } else if (isEndOfLineOrEndOfProgram(delimiter)) {
                if (!lastDelimiter.equals(";") && !lastDelimiter.equals(",")) {
                    System.out.println();
                }
                return;
            } else {
                throw new InterpreterException("Syntax Error");
            }
        }
    }

    /**
     * Executes a GOTO statement.
     *
     * <p>Transfers control to a labeled line.</p>
     *
     * @throws InterpreterException if the label is undefined
     */
    private void execGoto() throws InterpreterException {
        final BasicToken label = this.lexer.nextToken();
        final Integer location = this.labelTableMap.get(label.text());

        if (location == null) {
            throw new InterpreterException("Undefined label");
        }

        this.lexer.setIndex(location);
    }

    /**
     * Executes an IF statement.
     *
     * <p>If the condition evaluates to non-zero, execution continues.
     * Otherwise, the rest of the line is skipped.</p>
     *
     * @throws InterpreterException if syntax is invalid
     */
    private void execIf() throws InterpreterException {

        final double result = this.expressionParser.evaluate();

        if (result != 0.0) {

            final BasicToken thenToken = this.lexer.nextToken();
            if (!thenToken.isKeyword(BasicKeyword.THEN)) {
                throw new InterpreterException("THEN expected");
            }
        } else {
            skipToNextLine();
        }
    }

    /**
     * Executes a FOR loop initialization.
     *
     * <p>Initializes the loop variable and pushes loop metadata onto the stack.</p>
     *
     * @throws InterpreterException if syntax is invalid
     */
    private void execFor() throws InterpreterException {

        final BasicToken variableToken = this.lexer.nextToken();
        final char variableName = variableToken.text().charAt(0);

        if (!Character.isLetter(variableName)) {
            throw new InterpreterException("Not a variable");
        }

        final int variableIndex = Character.toUpperCase(variableName) - 'A';

        final BasicToken equals = this.lexer.nextToken();
        if (!equals.isText("=")) {
            throw new InterpreterException("Equal sign expected");
        }

        final double startValue = this.expressionParser.evaluate();
        this.variableValues[variableIndex] = startValue;

        final BasicToken toToken = this.lexer.nextToken();
        if (!toToken.isKeyword(BasicKeyword.TO)) {
            throw new InterpreterException("TO expected");
        }

        final double targetValue = this.expressionParser.evaluate();

        final ForInfo info = new ForInfo(variableIndex, targetValue, this.lexer.getIndex());

        if (startValue <= targetValue) {

            this.forStack.push(info);
        } else {
            skipUntilNext();
        }
    }

    /**
     * Executes a NEXT statement.
     *
     * <p>Advances the loop variable and either repeats the loop or exits it.</p>
     *
     * @throws InterpreterException if no matching FOR exists
     */
    private void next() throws InterpreterException {
        ForInfo info = forStack.pollFirst();
        if (info == null) {
            throw new InterpreterException("NEXT without FOR");
        }

        variableValues[info.varIndex()]++;

        if (variableValues[info.varIndex()] > info.target()) {
            return;
        }

        forStack.push(info);
        lexer.setIndex(info.loopStartIndex());
    }

    /**
     * Executes a GOSUB statement.
     *
     * <p>Pushes the current execution position onto a stack and jumps to a label.</p>
     *
     * @throws InterpreterException if the label is undefined
     */
    private void gosub() throws InterpreterException {
        BasicToken label = lexer.nextToken();
        Integer location = labelTableMap.get(label.text());

        if (location == null) {
            throw new InterpreterException("Undefined label");
        }

        gosubStack.push(lexer.getIndex());
        lexer.setIndex(location);
    }

    /**
     * Executes a RETURN statement.
     *
     * <p>Restores execution to the last GOSUB call.</p>
     *
     * @throws InterpreterException if there is no matching GOSUB
     */
    private void greturn() throws InterpreterException {
        Integer returnLocation = gosubStack.pollFirst();
        if (returnLocation == null) {
            throw new InterpreterException("RETURN without GOSUB");
        }

        lexer.setIndex(returnLocation);
    }

    /**
     * Executes an INPUT statement.
     *
     * <p>Reads a numeric value from standard input and assigns it to a variable.</p>
     *
     * @throws InterpreterException if input fails or syntax is invalid
     */
    private void input() throws InterpreterException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        BasicToken token = lexer.nextToken();
        if (token.type() == BasicTokenType.QUOTED_STRING) {
            System.out.print(token.text());
            BasicToken comma = lexer.nextToken();
            if (!comma.isText(",")) {
                throw new InterpreterException("Syntax Error");
            }
            token = lexer.nextToken();
        } else {
            System.out.print("? ");
        }

        int var = Character.toUpperCase(token.text().charAt(0)) - 'A';

        try {
            String input = reader.readLine();
            variableValues[var] = Double.parseDouble(input);
        } catch (IOException ex) {
            throw new InterpreterException("I/O error on INPUT statement");
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input.");
        }
    }

    /**
     * Scans the program and builds the label table.
     *
     * <p>Labels are numeric tokens at the start of lines.</p>
     *
     * @throws InterpreterException if duplicate labels are found
     */
    private void scanLabels() throws InterpreterException {

        this.lexer.setIndex(0);

        while (true) {

            final int tokenStart   = this.lexer.getIndex();
            final BasicToken token = this.lexer.nextToken();

            if (token.type() == BasicTokenType.EOP) { // end of program
                return;
            }

            if (token.type() == BasicTokenType.NUMBER) {

                final Integer previous = this.labelTableMap.put(token.text(), tokenStart);
                if (previous != null) {
                    throw new InterpreterException("Duplicate label");
                }
            }

            skipToNextLine();
        }
    }

    /**
     * Advances the lexer to the next line.
     *
     * @throws InterpreterException if a lexer error occurs
     */
    private void skipToNextLine() throws InterpreterException {
        while (true) {

            final BasicToken token = this.lexer.nextToken();
            if (this.isEndOfLineOrEndOfProgram(token)) {
                return;
            }
        }
    }

    private boolean isEndOfLineOrEndOfProgram(final BasicToken token) {
        return token.type() == BasicTokenType.EOL || token.type() == BasicTokenType.EOP;
    }

    /**
     * Skips tokens until a NEXT statement or end-of-program is found.
     *
     * <p>Used to bypass FOR loops that should not execute.</p>
     *
     * @throws InterpreterException if a lexer error occurs
     */
    private void skipUntilNext() throws InterpreterException {
        while (true) {
            BasicToken token = lexer.nextToken();
            if (token.type() == BasicTokenType.EOP) {
                return;
            }
            if (token.isKeyword(BasicKeyword.NEXT)) {
                return;
            }
        }
    }

    /**
     * Resolves the value of a variable.
     *
     * @param variableName variable identifier
     * @return current value of the variable
     * @throws InterpreterException if the variable name is invalid
     */
    private double findVar(String variableName) throws InterpreterException {
        if (!Character.isLetter(variableName.charAt(0))) {
            throw new InterpreterException("Syntax Error");
        }
        return variableValues[Character.toUpperCase(variableName.charAt(0)) - 'A'];
    }

    /**
     * Resets the runtime state of the interpreter.
     *
     * <p>Clears variables, labels, and control stacks.</p>
     */
    private void resetRuntimeState() {

        Arrays.fill(this.variableValues, 0.0);
        this.labelTableMap.clear();
        this.forStack.clear();
        this.gosubStack.clear();
    }

    /**
     * Loads a BASIC program from a file into memory.
     *
     * @param fileName file path
     * @return program as a character array
     * @throws InterpreterException if the file cannot be read
     */
    private char[] loadProgram(String fileName) throws InterpreterException {
        char[] buffer = new char[PROG_SIZE];
        int size;

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            size = bufferedReader.read(buffer, 0, PROG_SIZE);
        } catch (FileNotFoundException ex) {
            throw new InterpreterException("File not found");
        } catch (IOException ex) {
            throw new InterpreterException("I/O error while loading file");
        }

        if (size > 0 && buffer[size - 1] == (char) 26) {
            size--;
        }

        char[] result = new char[size];
        System.arraycopy(buffer, 0, result, 0, size);
        return result;
    }

    /**
     * Holds metadata for a FOR loop.
     *
     * @param varIndex index of the loop variable
     * @param target loop termination value
     * @param loopStartIndex position to return to for the next iteration
     */
    private record ForInfo(int varIndex, double target, int loopStartIndex) {
    }
}