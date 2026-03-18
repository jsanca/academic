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

public class BasicInterpreter {

    private static final int PROG_SIZE = 10000;

    private final char[] program;
    private final BasicLexer lexer;
    private final ExpressionParser expressionParser;

    private final double[] vars = new double[26];
    private final Map<String, Integer> labelTable = new TreeMap<>();
    private final Deque<ForInfo> forStack = new ArrayDeque<>();
    private final Deque<Integer> gosubStack = new ArrayDeque<>();

    public BasicInterpreter(String programFile) throws InterpreterException {
        this.program = loadProgram(programFile);
        this.lexer = new BasicLexer(program);
        this.expressionParser = new ExpressionParser(lexer, this::findVar);
    }

    public void run() throws InterpreterException {
        resetRuntimeState();
        scanLabels();
        lexer.setIndex(0);
        interpret();
    }

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

    private void execGoto() throws InterpreterException {
        BasicToken label = lexer.nextToken();
        Integer location = labelTable.get(label.text());

        if (location == null) {
            throw new InterpreterException("Undefined label");
        }

        lexer.setIndex(location);
    }

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

    private void gosub() throws InterpreterException {
        BasicToken label = lexer.nextToken();
        Integer location = labelTable.get(label.text());

        if (location == null) {
            throw new InterpreterException("Undefined label");
        }

        gosubStack.push(lexer.getIndex());
        lexer.setIndex(location);
    }

    private void greturn() throws InterpreterException {
        Integer returnLocation = gosubStack.pollFirst();
        if (returnLocation == null) {
            throw new InterpreterException("RETURN without GOSUB");
        }

        lexer.setIndex(returnLocation);
    }

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

    private void skipToNextLine() throws InterpreterException {
        while (true) {
            BasicToken token = lexer.nextToken();
            if (token.type() == BasicTokenType.EOL || token.type() == BasicTokenType.EOP) {
                return;
            }
        }
    }

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

    private double findVar(String variableName) throws InterpreterException {
        if (!Character.isLetter(variableName.charAt(0))) {
            throw new InterpreterException("Syntax Error");
        }
        return vars[Character.toUpperCase(variableName.charAt(0)) - 'A'];
    }

    private void resetRuntimeState() {
        for (int i = 0; i < vars.length; i++) {
            vars[i] = 0.0;
        }
        labelTable.clear();
        forStack.clear();
        gosubStack.clear();
    }

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

    private record ForInfo(int varIndex, double target, int loopStartIndex) {
    }
}