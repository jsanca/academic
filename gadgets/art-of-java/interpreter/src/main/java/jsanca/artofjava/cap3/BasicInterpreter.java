package jsanca.artofjava.cap3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;

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
 */
public class BasicInterpreter {

    private static final int PROG_SIZE = 10000;

    private final char[] program;
    private final BasicLexer lexer;
    private final ExpressionParser expressionParser;

    private final double[] vars = new double[26];
    private final Map<String, Integer> labelTable = new TreeMap<>();
    private final Deque<ForInfo> forStack = new ArrayDeque<>();
    private final Deque<Integer> gosubStack = new ArrayDeque<>();

    /**
     * Creates a new interpreter instance by loading a BASIC program from a file.
     *
     * @param programFile path to the BASIC program file
     * @throws InterpreterException if the program cannot be loaded
     */
    public BasicInterpreter(String programFile) throws InterpreterException {
        this.program = loadProgram(programFile);
        this.lexer = new BasicLexer(program);
        this.expressionParser = new ExpressionParser(lexer, this::findVar);
    }

    /**
     * Executes the loaded BASIC program.
     *
     * <p>This method resets runtime state, scans labels, and starts interpretation.</p>
     *
     * @throws InterpreterException if a runtime or syntax error occurs
     */
    public void run() throws InterpreterException {
        resetRuntimeState();
        scanLabels();
        lexer.setIndex(0);
        interpret();
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
            BasicToken token = lexer.nextToken();

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
                lexer.putBack(token);
                assignment();
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
        BasicToken variableToken = lexer.nextToken();
        char variableName = variableToken.text().charAt(0);

        if (!Character.isLetter(variableName)) {
            throw new InterpreterException("Not a variable");
        }

        int variableIndex = Character.toUpperCase(variableName) - 'A';

        BasicToken equals = lexer.nextToken();
        if (!equals.isText("=")) {
            throw new InterpreterException("Equal sign expected");
        }

        double value = expressionParser.evaluate();
        vars[variableIndex] = value;
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
            BasicToken token = lexer.nextToken();

            if (token.type() == BasicTokenType.EOL || token.type() == BasicTokenType.EOP) {
                if (!lastDelimiter.equals(";") && !lastDelimiter.equals(",")) {
                    System.out.println();
                }
                return;
            }

            if (token.type() == BasicTokenType.QUOTED_STRING) {
                System.out.print(token.text());
                len += token.text().length();
            } else {
                lexer.putBack(token);
                double result = expressionParser.evaluate();
                System.out.print(result);
                len += Double.toString(result).length();
            }

            BasicToken delimiter = lexer.nextToken();
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
            } else if (delimiter.type() == BasicTokenType.EOL || delimiter.type() == BasicTokenType.EOP) {
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
        BasicToken label = lexer.nextToken();
        Integer location = labelTable.get(label.text());

        if (location == null) {
            throw new InterpreterException("Undefined label");
        }

        lexer.setIndex(location);
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
        double result = expressionParser.evaluate();

        if (result != 0.0) {
            BasicToken thenToken = lexer.nextToken();
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
        BasicToken variableToken = lexer.nextToken();
        char variableName = variableToken.text().charAt(0);

        if (!Character.isLetter(variableName)) {
            throw new InterpreterException("Not a variable");
        }

        int variableIndex = Character.toUpperCase(variableName) - 'A';

        BasicToken equals = lexer.nextToken();
        if (!equals.isText("=")) {
            throw new InterpreterException("Equal sign expected");
        }

        double startValue = expressionParser.evaluate();
        vars[variableIndex] = startValue;

        BasicToken toToken = lexer.nextToken();
        if (!toToken.isKeyword(BasicKeyword.TO)) {
            throw new InterpreterException("TO expected");
        }

        double targetValue = expressionParser.evaluate();

        ForInfo info = new ForInfo(variableIndex, targetValue, lexer.getIndex());

        if (startValue <= targetValue) {
            forStack.push(info);
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

        vars[info.varIndex()]++;

        if (vars[info.varIndex()] > info.target()) {
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
        Integer location = labelTable.get(label.text());

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
            vars[var] = Double.parseDouble(input);
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
        lexer.setIndex(0);

        while (true) {
            int tokenStart = lexer.getIndex();
            BasicToken token = lexer.nextToken();

            if (token.type() == BasicTokenType.EOP) {
                return;
            }

            if (token.type() == BasicTokenType.NUMBER) {
                Integer previous = labelTable.put(token.text(), tokenStart);
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
            BasicToken token = lexer.nextToken();
            if (token.type() == BasicTokenType.EOL || token.type() == BasicTokenType.EOP) {
                return;
            }
        }
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
        return vars[Character.toUpperCase(variableName.charAt(0)) - 'A'];
    }

    /**
     * Resets the runtime state of the interpreter.
     *
     * <p>Clears variables, labels, and control stacks.</p>
     */
    private void resetRuntimeState() {
        for (int i = 0; i < vars.length; i++) {
            vars[i] = 0.0;
        }
        labelTable.clear();
        forStack.clear();
        gosubStack.clear();
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