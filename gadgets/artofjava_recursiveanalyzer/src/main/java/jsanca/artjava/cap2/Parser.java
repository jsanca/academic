package jsanca.artjava.cap2;


import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Parse to eval and run an expression.
 *
 * A valid expression can have SUM (+), REST (-), MULTI (*) and DIV(/)
 * also ca have MOD(%), EXP(^), and Parentheses
 *
 * * User: jsanca
 * Date: 4/16/13
 * Time: 12:29 AM
 *
 * @author jsanca
 */
public class Parser {

    private static final String EMPTY_STRING = "";


    // define the token types
    private enum TokenType {

        NONE, DELIMITER, VARIABLE, NUMBER, EOE
    }

    private String expression = null;
    private int expressionIndex = 0;
    private String token = null;
    private TokenType tokenType = null;

    private Set delimiters = new HashSet(Arrays.asList(new Character[]{'+', '-', '/', '*', '%', '^', '=', '(', ')'}));

    public static double eval (final String expression) {

        return new Parser(expression).getResult();
    } // eval

    /**
     * Constructor.
     *
     * @param expression String
     */
    public Parser(final String expression) {

        if (null == expression) {

            throw new NonExpressionDefinedException ("The expression can not be null");
        }
        this.expression = this.normalizeExpresion(expression);
    }

    private String normalizeExpresion(final String expr) {

        return expr.replaceAll("\\s*", EMPTY_STRING);
    }

    /**
     * Gets the results of the eval of the expression.
     *
     * @return double
     */
    public double getResult() {

        double result = 0;

        this.expressionIndex = 0;
        this.nextToken();

        if (this.tokenType == TokenType.EOE) {

            throw new NonExpressionDefinedException();
        }

        // eval the expresion
        result = this.evalExp2();

        if (this.tokenType != TokenType.EOE) {

            throw new SintaxErrorException();
        }

        return result;
    } // getResult.

    /**
     * Do a sum or rest
     *
     * @return double
     */
    private double evalExp2() {

        char operator;
        double result;
        double partialResult;

        // call by the multi operation
        result = this.evalExp3();

        while ( (this.tokenType != TokenType.EOE) &&
                ((operator = this.token.charAt(0)) == '+' || operator == '-')) {

            this.nextToken();
            partialResult = evalExp3(); // call by composite operation

            switch (operator) {

                case '-':

                    result = result - partialResult;
                    break;

                case '+':

                    result = result + partialResult;
                    break;

            }
        }

        return result;
    } // evalExp2.

    /**
     * Do a multi or div
     *
     * @return double
     */
    private double evalExp3() {

        char operator;
        double result;
        double partialResult;

        // calls the ^
        result = this.evalExp4();


        while ( (this.tokenType != TokenType.EOE) &&
                ((operator = this.token.charAt(0)) == '*' || operator == '/' || operator == '%')) {

            this.nextToken();
            partialResult = evalExp4();

            switch (operator) {

                case '*':

                    result = result * partialResult;
                    break;

                case '/':

                    if (partialResult == 0.0) {

                        throw new DividedByZeroException();
                    }

                    result = result / partialResult;
                    break;

                case '%':

                    if (partialResult == 0.0) {

                        throw new DividedByZeroException();
                    }

                    result = result % partialResult;
                    break;

            }
        }

        return result;
    } // evalExp3,

    /**
     * Evalua el exponente
     *
     * @return double
     */
    private double evalExp4() {

        double result;
        double partialResult;

        // calls the negative sign
        result = this.evalExp5();

        if ("^".equals(this.token)) {

            this.nextToken();
            partialResult = this.evalExp4();

            if (partialResult == 0.0) { // x^0.0 = 1.0

                result = 1.0; //  trivial case
            } else {

                result =
                        Math.pow(result, partialResult);
            }
        }

        return result;
    } // evalExp4.

    /**
     * Evals  positive or negative sign
     *
     * @return
     */
    private double evalExp5() {

        double result;
        String operator = EMPTY_STRING;

        if ((this.tokenType == TokenType.DELIMITER) &&
                "+".equals(this.token) || "-".equals(this.token)) {

            operator = this.token;
            this.nextToken();
        }

        // calls by the parentheses
        result = this.evalExp6();

        if ("-".equals(operator)) {

            result = -result;
        }

        return result;
    } // evalExp5.

    /**
     * Eval the parentheses
     * @return double
     */
    private double evalExp6() {

        double result;

        if ("(".equals(this.token)) {

            this.nextToken();
            result = this.evalExp2();

            if (!")".equals(this.token)) {

                throw new UnbalanceParenthesesException();
            }

            this.nextToken();

        } else {

            result = this.getAtomicValue();
        }

        return result;
    } // evalExp6.

    /**
     * Get just an atomic value
     *
     * @return double
     */
    private double getAtomicValue() {

        double result = 0.0;

        if (this.tokenType == TokenType.NUMBER) {

            result = Double.parseDouble(this.token);

            this.nextToken();
        } else {

            throw new SintaxErrorException();
        }

        return result;
    } // getAtomicValue.


    /**
     * get the next token in the stack
     */
    protected void nextToken() {

        this.tokenType = TokenType.NONE;
        this.token = "";

        if (!isTheEndOfExpresion()) {

            // if it is an operator
            if (this.isDelimiter(this.expression.charAt(this.expressionIndex))) {

                this.parseDelimiter();

            } else if (this.isVariable(this.expression.charAt(this.expressionIndex))) { // if it is a var

                this.parseVariable();

            } else if (this.isNumber(this.expression.charAt(this.expressionIndex))) { // number

                this.parseNumber();

            } else {

                this.tokenType = TokenType.EOE; // unknown char
            }
        } else {

            this.tokenType = TokenType.EOE;
        }

        System.out.println(MessageFormat.format("token {0} and type {1}", this.token, this.tokenType));

    } // nextToken.

    private void parseFactor() {

        while (!this.isTheEndOfExpresion() && !this.isDelimiter(this.expression.charAt(this.expressionIndex))) {

            this.token += this.expression.charAt(this.expressionIndex);
            this.expressionIndex++;
        }
    }

    protected void parseNumber() {

        this.parseFactor();

        this.tokenType = TokenType.NUMBER;
    }


    protected void parseVariable() {

        this.parseFactor();

        this.tokenType = TokenType.VARIABLE;
    }

    protected void parseDelimiter() {

        this.token +=
                this.expression.charAt(this.expressionIndex);

        this.expressionIndex++;
        this.tokenType = TokenType.DELIMITER;
    }

    private boolean isNumber(final char c) {

        return Character.isDigit(c);
    } // isNumber,

    private boolean isDelimiter(final char c) {

        return this.delimiters.contains(c);
    } // isDelimiter.

    private boolean isVariable(final char c) {

        return Character.isLetter(c);
    } // isVariable.

    private boolean isTheEndOfExpresion() {

        return (this.expressionIndex >= this.expression.length());
    } // isTheEndOfExpresion.


} // E:O:F:Parser.
