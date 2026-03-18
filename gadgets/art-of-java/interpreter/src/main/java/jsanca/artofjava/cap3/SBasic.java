package jsanca.artofjava.cap3;

import java.io.*;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.TreeMap;

public class SBasic {

    final int PROG_SIZE = 10000; // maximum program size
    // These are the token types.
    final int NONE = 0;
    final int DELIMITER = 1;
    final int VARIABLE = 2;
    final int NUMBER = 3;
    final int COMMAND = 4;
    final int QUOTEDSTR = 5;
    // These are the types of errors.
    final int SYNTAX = 0;
    final int UNBALPARENS = 1;
    final int NOEXP = 2;
    final int DIVBYZERO = 3;
    final int EQUALEXPECTED = 4;
    final int NOTVAR = 5;
    final int LABELTABLEFULL = 6;
    final int DUPLABEL = 7;
    final int UNDEFLABEL = 8;
    final int THENEXPECTED = 9;
    final int TOEXPECTED = 10;
    final int NEXTWITHOUTFOR = 11;
    final int RETURNWITHOUTGOSUB = 12;
    final int MISSINGQUOTE = 13;

    final int FILENOTFOUND = 14;
    final int FILEIOERROR = 15;
    final int INPUTIOERROR = 16;

    // Internal representation of the Small BASIC keywords.
    final int UNKNCOM = 0;

    final int PRINT = 1;
    final int INPUT = 2;
    final int IF = 3;
    final int THEN = 4;
    final int FOR = 5;
    final int NEXT = 6;
    final int TO = 7;
    final int GOTO = 8;
    final int GOSUB = 9;
    final int RETURN = 10;
    final int END = 11;
    final int EOL = 12;

    // This token indicates end-of-program.
    final String EOP = "\0";

    // Codes for double-operators, such as <=.
    static final char LE = 1;
    static final char GE = 2;
    static final char NE = 3;

    // Array for variables.
    private double vars[];

    // This class links keywords with their keyword tokens.
    record Keyword (String keyword, // string form
            int keywordTok // internal representation
    ) {}

    /* Table of keywords with their internal representation.
    All keywords must be entered lowercase. */
    Keyword kwTable[] = new Keyword[] {
            new Keyword("print", PRINT), // in this table.
            new Keyword("input", INPUT),
            new Keyword("if", IF),
            new Keyword("then", THEN),
            new Keyword("goto", GOTO),
            new Keyword("for", FOR),
            new Keyword("next", NEXT),
            new Keyword("to", TO),
            new Keyword("gosub", GOSUB),
            new Keyword("return", RETURN),
            new Keyword("end", END)};

    private char[] prog; // refers to program array
    private int programIndex; // current index into program

    private String token; // holds current token
    private int tokenType; // holds token's type

    private int keywordToken; // internal representation of a keyword

    // Support for FOR loops.
    class ForInfo {
        int var; // counter variable
        double target; // target value
        int loc; // index in source code to loop to
    }

    // Stack for FOR loops.
    private Stack<ForInfo> fStack;

    // Defines label table entries.
    record Label (String name, // label
            int loc // index of label's location in source file
    ) {}

    // A map for labels.
    private TreeMap<String, Integer> labelTable;

    // Stack for gosubs.
    private Stack<Integer> gStack;

    // Relational operators.
    static final char RELATIONAL_OPERATORS_ARRAY[] = { GE, NE, LE, '<', '>', '=', 0};

    /* Create a string containing the relational
        operators in order to make checking for
    them more convenient. */
    static final String RELATIONAL_OPERATORS_STRING = new String(RELATIONAL_OPERATORS_ARRAY);

    // Constructor for SBasic.
    public SBasic(final String progName) throws InterpreterException {

        final char tempbuf[] = new char[PROG_SIZE];
        // Load the program to execute.
        final int size = loadProgram(tempbuf, progName);

        if (size != -1) {
            // Create a properly sized array to hold the program.
            prog = new char[size];
            // Copy the program into program array.
            System.arraycopy(tempbuf, 0, prog, 0, size);
        }
    }

    // Load a program.
    private int loadProgram(final char[] programBuffer, final String fileName) throws InterpreterException {
        int size = 0;
        try (FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            size = bufferedReader.read(programBuffer, 0, PROG_SIZE);
        } catch (FileNotFoundException exc) {
            handleErr(FILENOTFOUND);
        } catch (IOException exc) {
            handleErr(FILEIOERROR);
        }
        // If file ends with an EOF mark, back up.
        if (programBuffer[size - 1] == (char) 26) {
            size--;
        }

        return size; // return size of program
    }

    // Execute the program.
    public void run() throws InterpreterException {

        // Initialize for new program run.
        vars = new double[26];
        fStack = new Stack<>();
        labelTable = new TreeMap<>();
        gStack = new Stack<>();
        programIndex = 0;
        scanLabels(); // find the labels in the program
        sbInterp(); // execute
    }

    // Find all labels.
    private void scanLabels() throws InterpreterException {
        int i;
        Object result;

        // See if the first token in the file is a label.
        getToken();

        if (tokenType == NUMBER) {

            labelTable.put(token, new Integer(programIndex));
        }

        findEOL();

        do {

            getToken();
            if (tokenType == NUMBER) { // must be a line number

                result = labelTable.put(token, new Integer(programIndex));
                if (result != null) {

                    handleErr(DUPLABEL);
                }
            }

            // If not on a blank line, find next line.
            if (keywordToken != EOL) {

                findEOL();
            }
        } while (!token.equals(EOP));

        programIndex = 0; // reset index to start of program
    }

    // Entry point for the Small BASIC interpreter.
    private void sbInterp() throws InterpreterException {
        // This is the interpreter's main loop.
        do {

            getToken();
            // Check for assignment statement.
            if (tokenType == VARIABLE) {

                putBack(); // return the var to the input stream
                assignment(); // handle assignment statement
            } else // is keyword
                switch (keywordToken) {
                    case PRINT  -> print();
                    case GOTO   -> execGoto();
                    case IF     -> execIf();
                    case FOR    -> execFor();
                    case NEXT   -> next();
                    case INPUT  -> input();
                    case GOSUB  -> gosub();
                    case RETURN -> greturn();
                    case END    -> { return; }
                }
        } while (!token.equals(EOP));
    }


    // Find the start of the next line.
    private void findEOL() {
        while (programIndex < prog.length && prog[programIndex] != '\n') {
            ++programIndex;
        }

        if (programIndex < prog.length) {
            programIndex++;
        }
    }

    // Assign a variable a value.
    private void assignment() throws InterpreterException {
        int variable;
        double value;
        char variableName;
        // Get the variable name.
        getToken();
        variableName = token.charAt(0);
        if (!Character.isLetter(variableName)) { // if the variable name does not start with a letter is an error
            handleErr(NOTVAR);
            return;
        }

        // Convert to index into variable table.
        variable = (int) Character.toUpperCase(variableName) - 'A';
        // Get the equal sign.
        getToken();
        if (!token.equals("=")) {
            handleErr(EQUALEXPECTED);
            return;
        }

        // Get the value to assign.
        value = evaluate();

        // Assign the value.
        vars[variable] = value;
    }


    // Execute a simple version of the PRINT statement.
    private void print() throws InterpreterException {

        double result;
        int len = 0;
        int spaces;
        String lastDelim = "";

        do {

            getToken(); // get next list item
            if (keywordToken == EOL || token.equals(EOP)) {
                break;
            }

            if (tokenType == QUOTEDSTR) { // is string
                System.out.print(token);
                len += token.length();
                getToken();
            } else { // is expression
                putBack();
                result = evaluate();
                getToken();
                System.out.print(result);
                // Add length of output to running total.
                Double t = new Double(result);
                len += t.toString().length(); // save length
            }

            lastDelim = token;
            // If comma, move to next tab stop.
            if (lastDelim.equals(",")) {

            // compute number of spaces to move to next tab
                spaces = 8 - (len % 8);
                len += spaces; // add in the tabbing position
                while (spaces != 0) {
                    System.out.print(" ");
                    spaces--;
                }
            } else if (token.equals(";")) {
                System.out.print(" ");
                len++;
            } else if (keywordToken != EOL && !token.equals(EOP)) {
                handleErr(SYNTAX);
            }
        } while (lastDelim.equals(";") || lastDelim.equals(","));

        if (keywordToken == EOL || token.equals(EOP)) {
            if (!lastDelim.equals(";") && !lastDelim.equals(",")) {
                System.out.println();
            }
        } else {
            handleErr(SYNTAX);
        }
    }

    // Execute a GOTO statement.
    private void execGoto() throws InterpreterException {

        Integer loc;
        getToken(); // get label to go to
        // Find the location of the label.
        loc = (Integer) labelTable.get(token);
        if (loc == null) {
            handleErr(UNDEFLABEL); // label not defined
        }
        else { // start program running at that loc
            programIndex = loc.intValue();
        }
    }

    // Execute an IF statement.
    private void execIf() throws InterpreterException {

        double result;
        result = evaluate(); // get value of expression
        /* If the result is true (non-zero),
        process target of IF. Otherwise move on
        to next line in the program. */
        if (result != 0.0) {
            getToken();
            if (keywordToken != THEN) {
                handleErr(THENEXPECTED);
                return;
            } // else, target statement will be executed
        } else {
            findEOL(); // find start of next line
        }
    }

    // Execute a FOR loop.
    private void execFor() throws InterpreterException {

        final ForInfo stckvar = new ForInfo();
        double value;
        char vname;
        getToken(); // read the control variable
        vname = token.charAt(0);
        if (!Character.isLetter(vname)) {
            handleErr(NOTVAR);
            return;
        }

        // Save index of control var.
        stckvar.var = Character.toUpperCase(vname) - 'A';
        getToken(); // read the equal sign
        if (token.charAt(0) != '=') {
            handleErr(EQUALEXPECTED);
            return;
        }

        value = evaluate(); // get initial value
        vars[stckvar.var] = value;
        getToken(); // read and discard the TO
        if (keywordToken != TO) handleErr(TOEXPECTED);
        stckvar.target = evaluate(); // get target value
        /* If loop can execute at least once, push info on stack. */
        if (value >= vars[stckvar.var]) {
            stckvar.loc = programIndex;
            fStack.push(stckvar);
        } else {// otherwise, skip loop code altogether
            while (keywordToken != NEXT) {
                getToken();
            }
        }
    }

    // Execute a NEXT statement.
    private void next() throws InterpreterException {

        ForInfo stckvar;
        try {
            // Retrieve info for this For loop.
            stckvar = (ForInfo) fStack.pop();
            vars[stckvar.var]++; // increment control var
            // If done, return.
            if (vars[stckvar.var] > stckvar.target) {
                return;
            }
            // Otherwise, restore the info.
            fStack.push(stckvar);
            programIndex = stckvar.loc; // loop
        } catch (EmptyStackException exc) {
            handleErr(NEXTWITHOUTFOR);
        }
    }


    // Execute a simple form of INPUT.
    private void input() throws InterpreterException {
        int var;
        double val = 0.0;
        String str;
        BufferedReader br = new
                BufferedReader(new InputStreamReader(System.in));
        getToken(); // see if prompt string is present
        if (tokenType == QUOTEDSTR) {
            // if so, print it and check for comma
            System.out.print(token);
            getToken();
            if (!token.equals(",")) {
                handleErr(SYNTAX);
            }
            getToken();
        } else {
            System.out.print("? "); // otherwise, prompt with ?
        }

        // get the input var
        var = Character.toUpperCase(token.charAt(0)) - 'A';
        try {
            str = br.readLine();
            val = Double.parseDouble(str); // read the value
        } catch (IOException exc) {
            handleErr(INPUTIOERROR);
        } catch (NumberFormatException exc) {
            /* You might want to handle this error
            differently than the other interpreter
            errors. */
            System.out.println("Invalid input.");
        }

        vars[var] = val; // store it
    }


    // Execute a GOSUB.
    private void gosub() throws InterpreterException {
        Integer loc;
        getToken();
        // Find the label to call.
        loc = (Integer) labelTable.get(token);
        if (loc == null) {
            handleErr(UNDEFLABEL); // label not defined
        } else {
            // Save place to return to.
            gStack.push(new Integer(programIndex));
            // Start program running at that loc.
            programIndex = loc.intValue();
        }
    }


    // Return from GOSUB.
    private void greturn() throws InterpreterException {
        Integer t;
        try {
            // Restore program index.
            t = (Integer) gStack.pop();
            programIndex = t.intValue();
        } catch (EmptyStackException exc) {
            handleErr(RETURNWITHOUTGOSUB);
        }
    }

    // **************** Expression Parser ****************
    // Parser entry point.
    private double evaluate() throws InterpreterException {
        double result = 0.0;
        getToken();
        if (token.equals(EOP)) {
            handleErr(NOEXP); // no expression present
        }
        // Parse and evaluate the expression.
        result = evalExp1();
        putBack();
        return result;
    }

    // Process relational operators.
    private double evalExp1() throws InterpreterException {

        double l_temp, r_temp, result;
        char op;
        result = evalExp2();
        // If at end of program, return.
        if (token.equals(EOP)) {
            return result;
        }

        op = token.charAt(0);
        if (isRelop(op)) {
            l_temp = result;
            getToken();
            r_temp = evalExp1();
            result = switch (op) {
                case '<' -> (l_temp <  r_temp) ? 1.0 : 0.0;
                case LE  -> (l_temp <= r_temp) ? 1.0 : 0.0;
                case '>' -> (l_temp >  r_temp) ? 1.0 : 0.0;
                case GE  -> (l_temp >= r_temp) ? 1.0 : 0.0;
                case '=' -> (l_temp == r_temp) ? 1.0 : 0.0;
                case NE  -> (l_temp != r_temp) ? 1.0 : 0.0;
                default  -> throw new IllegalArgumentException("Unsupported operator: " + op);
            };
        }
        return result;
    }


    // Add or subtract two terms.
    private double evalExp2() throws InterpreterException {
        char op;
        double result;
        double partialResult;
        result = evalExp3();
        while ((op = token.charAt(0)) == '+' || op == '-') {
            getToken();
            partialResult = evalExp3();
            result = switch (op) {
                case '-' -> result - partialResult;
                case '+' -> result + partialResult;
                default  -> result; // Mantiene el valor si no es + o -
            };
        }
        return result;
    }

    // Multiply or divide two factors.
    private double evalExp3() throws InterpreterException {
        char op;
        double result;
        double partialResult;
        result = evalExp4();
        while ((op = token.charAt(0)) == '*' ||
                op == '/' || op == '%') {
            getToken();
            partialResult = evalExp4();
            result = switch (op) {
                case '*' -> result * partialResult;

                case '/', '%' -> {
                    if (partialResult == 0.0) {
                        handleErr(DIVBYZERO);
                    }
                    // Usamos yield para retornar el valor del bloque
                    yield (op == '/') ? (result / partialResult) : (result % partialResult);
                }

                default -> result;
            };
        }
        return result;
    }


    // Process an exponent.
    private double evalExp4() throws InterpreterException {
        double result;
        double partialResult;
        double ex;
        int t;
        result = evalExp5();
        if (token.equals("^")) {
            getToken();
            partialResult = evalExp4();
            ex = result;
            if (partialResult == 0.0) {
                result = 1.0;
            } else {
                for (t = (int) partialResult - 1; t > 0; t--) {
                    result = result * ex;
                }
            }
        }
        return result;
    }


    // Evaluate a unary + or -.
    private double evalExp5() throws InterpreterException {
        double result;
        String op;
        op = "";
        if ((tokenType == DELIMITER) &&
                token.equals("+") || token.equals("-")) {
            op = token;
            getToken();
        }
        result = evalExp6();
        if (op.equals("-")) {
            result = -result;
        }
        return result;
    }


    // Process a parenthesized expression.
    private double evalExp6() throws InterpreterException {
        double result;
        if (token.equals("(")) {
            getToken();
            result = evalExp2();
            if (!token.equals(")")) {
                handleErr(UNBALPARENS);
            }

            getToken();
        } else result = atom();

        return result;
    }


    // Get the value of a number or variable.
    private double atom() throws InterpreterException {
        double result = 0.0;
        switch (tokenType) {
            case NUMBER:
                try {
                    result = Double.parseDouble(token);
                } catch (NumberFormatException exc) {
                    handleErr(SYNTAX);
                }
                getToken();
                break;
            case VARIABLE:
                result = findVar(token);
                getToken();
                break;
            default:
                handleErr(SYNTAX);
                break;
        }
        return result;

    }

    // Return the value of a variable.
    private double findVar(String vname)
            throws InterpreterException {
        if (!Character.isLetter(vname.charAt(0))) {
            handleErr(SYNTAX);
            return 0.0;
        }
        return vars[Character.toUpperCase(vname.charAt(0)) - 'A'];
    }

    // Return a token to the input stream.
    private void putBack() {

        if (token == EOP) {
            return;
        }
        for (int i = 0; i < token.length(); i++) {
            programIndex--;
        }
    }


    // Handle an error.
    private void handleErr(int error)
            throws InterpreterException {
        String[] err = {
                "Syntax Error",
                "Unbalanced Parentheses",
                "No Expression Present",
                "Division by Zero",
                "Equal sign expected",
                "Not a variable",
                "Label table full",
                "Duplicate label",
                "Undefined label",
                "THEN expected",
                "TO expected",
                "NEXT without FOR",
                "RETURN without GOSUB",
                "Closing quotes needed",
                "File not found",
                "I/O error while loading file",
                "I/O error on INPUT statement"
        };
        throw new InterpreterException(err[error]);
    }


    // Obtain the next token.
    private void getToken() throws InterpreterException {
        char ch;
        tokenType = NONE;
        token = "";
        keywordToken = UNKNCOM;
        // Check for end of program.
        if (programIndex == prog.length) {
            token = EOP;
            return;
        }
        // Skip over white space.
        while (programIndex < prog.length &&
                isSpaceOrTab(prog[programIndex])) {
            programIndex++;
        }

        // Trailing whitespace ends program.
        if (programIndex == prog.length) {
            token = EOP;
            tokenType = DELIMITER;
            return;
        }
        if (prog[programIndex] == '\r') { // handle crlf
            programIndex += 2;
            keywordToken = EOL;
            token = "\r\n";
            return;
        }
        // Check for relational operator.
        ch = prog[programIndex];
        if (ch == '<' || ch == '>') {
            if (programIndex + 1 == prog.length) handleErr(SYNTAX);
            switch (ch) {
                case '<':
                    if (prog[programIndex + 1] == '>') {
                        programIndex += 2;
                        ;
                        token = String.valueOf(NE);
                    } else if (prog[programIndex + 1] == '=') {
                        programIndex += 2;
                        token = String.valueOf(LE);
                    } else {
                        programIndex++;
                        token = "<";
                    }
                    break;
                case '>':
                    if (prog[programIndex + 1] == '=') {
                        programIndex += 2;
                        ;
                        token = String.valueOf(GE);
                    } else {
                        programIndex++;
                        token = ">";
                    }
                    break;
            }
            tokenType = DELIMITER;
            return;
        }

        if (isDelim(prog[programIndex])) {
            // Is an operator.
            token += prog[programIndex];
            programIndex++;
            tokenType = DELIMITER;
        } else if (Character.isLetter(prog[programIndex])) {
            // Is a variable or keyword.
            while (!isDelim(prog[programIndex])) {
                token += prog[programIndex];
                programIndex++;
                if (programIndex >= prog.length) break;
            }
            keywordToken = lookUp(token);
            if (keywordToken == UNKNCOM) tokenType = VARIABLE;
            else tokenType = COMMAND;
        } else if (Character.isDigit(prog[programIndex])) {
            // Is a number.
            while (!isDelim(prog[programIndex])) {
                token += prog[programIndex];
                programIndex++;
                if (programIndex >= prog.length) break;
            }
            tokenType = NUMBER;
        } else if (prog[programIndex] == '"') {
            // Is a quoted string.
            programIndex++;
            ch = prog[programIndex];
            while (ch != '"' && ch != '\r') {
                token += ch;
                programIndex++;
                ch = prog[programIndex];
            }
            if (ch == '\r') handleErr(MISSINGQUOTE);
            programIndex++;
            tokenType = QUOTEDSTR;
        } else { // unknown character terminates program
            token = EOP;
            return;
        }
    }


    // Return true if c is a delimiter.
    private boolean isDelim(char c) {
        return " \r,;<>+-/*%^=()".indexOf(c) != -1;
    }

    // Return true if c is a space or a tab.
    boolean isSpaceOrTab(char c) {
        return c == ' ' || c == '\t';
    }

    // Return true if c is a relational operator.
    boolean isRelop(char c) {
        return  RELATIONAL_OPERATORS_STRING.indexOf(c) != -1; // todo; this could be a set
    }

    /* Look up a token's internal representation in the
    token table. */
    private int lookUp(String s) {
        int i;
        // Convert to lowercase.
        s = s.toLowerCase();
        // See if token is in table.
        for (i = 0; i < kwTable.length; i++) {
            if (kwTable[i].keyword.equals(s)) {
                return kwTable[i].keywordTok;
            }
        }
        return UNKNCOM; // unknown keyword
    }

} // E:O:F:SBasic