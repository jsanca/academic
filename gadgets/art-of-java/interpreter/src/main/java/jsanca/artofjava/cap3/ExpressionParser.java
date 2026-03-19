package jsanca.artofjava.cap3;

/**
 * Recursive descent expression parser for a BASIC-like language.
 *
 * <p>This parser evaluates arithmetic and relational expressions directly
 * from tokens provided by {@link BasicLexer}. It respects operator precedence
 * and associativity using a layered method structure.</p>
 *
 * <p>Supported features:</p>
 * <ul>
 *   <li>Arithmetic operations: +, -, *, /, %, ^</li>
 *   <li>Relational operations: <, >, <=, >=, =, <></li>
 *   <li>Unary operators: +, -</li>
 *   <li>Parentheses</li>
 *   <li>Variables and numeric literals</li>
 * </ul>
 * @author jsanca & elo
 */
public class ExpressionParser {

    private static final String REL_OPS = new String(new char[]{
            BasicLexer.GE, BasicLexer.NE, BasicLexer.LE, '<', '>', '=', 0
    });

    private final BasicLexer lexer;
    private final VariableResolver variableResolver;

    public ExpressionParser(BasicLexer lexer, VariableResolver variableResolver) {
        this.lexer = lexer;
        this.variableResolver = variableResolver;
    }

    /**
     * Evaluates a full expression starting at the current lexer position.
     *
     * @return evaluated numeric result
     * @throws InterpreterException if syntax is invalid
     */
    public double evaluate() throws InterpreterException {
        BasicToken token = lexer.nextToken();
        if (token.type() == BasicTokenType.EOP) {
            throw new InterpreterException("No Expression Present");
        }

        lexer.putBack(token);
        return parseRelational();
    }

    /**
     * Parses relational expressions (<, >, <=, >=, =, <>).
     * This is the top-level entry for expression parsing.
     */
    private double parseRelational() throws InterpreterException {
        double result = parseAddSub();
        BasicToken token = lexer.nextToken();

        if (token.type() == BasicTokenType.EOP || token.type() == BasicTokenType.EOL) {
            lexer.putBack(token);
            return result;
        }

        char op = token.text().charAt(0);
        if (isRelationalOperator(op)) {
            double left = result;
            double right = parseRelational();

            return switch (op) {
                case '<' -> left < right ? 1.0 : 0.0;
                case BasicLexer.LE -> left <= right ? 1.0 : 0.0;
                case '>' -> left > right ? 1.0 : 0.0;
                case BasicLexer.GE -> left >= right ? 1.0 : 0.0;
                case '=' -> left == right ? 1.0 : 0.0;
                case BasicLexer.NE -> left != right ? 1.0 : 0.0;
                default -> throw new InterpreterException("Syntax Error");
            };
        }

        lexer.putBack(token);
        return result;
    }

    /**
     * Parses addition and subtraction expressions (+, -).
     */
    private double parseAddSub() throws InterpreterException {
        double result = parseMulDivMod();

        while (true) {
            BasicToken token = lexer.nextToken();

            if (!token.isText("+") && !token.isText("-")) {
                lexer.putBack(token);
                return result;
            }

            double partial = parseMulDivMod();
            result = token.isText("+") ? result + partial : result - partial;
        }
    }

    /**
     * Parses multiplication, division, and modulo (*, /, %).
     */
    private double parseMulDivMod() throws InterpreterException {
        double result = parseExponent();

        while (true) {
            BasicToken token = lexer.nextToken();

            if (!token.isText("*") && !token.isText("/") && !token.isText("%")) {
                lexer.putBack(token);
                return result;
            }

            double partial = parseExponent();

            if ((token.isText("/") || token.isText("%")) && partial == 0.0) {
                throw new InterpreterException("Division by Zero");
            }

            result = switch (token.text()) {
                case "*" -> result * partial;
                case "/" -> result / partial;
                case "%" -> result % partial;
                default -> result;
            };
        }
    }

    /**
     * Parses exponentiation (^) with right associativity.
     */
    private double parseExponent() throws InterpreterException {
        double result = parseUnary();
        BasicToken token = lexer.nextToken();

        if (!token.isText("^")) {
            lexer.putBack(token);
            return result;
        }

        double partial = parseExponent();
        if (partial == 0.0) {
            return 1.0;
        }

        double base = result;
        for (int i = (int) partial - 1; i > 0; i--) {
            result *= base;
        }

        return result;
    }

    /**
     * Parses unary operators (+, -).
     */
    private double parseUnary() throws InterpreterException {
        BasicToken token = lexer.nextToken();
        String unary = "";

        if (token.type() == BasicTokenType.DELIMITER && (token.isText("+") || token.isText("-"))) {
            unary = token.text();
        } else {
            lexer.putBack(token);
        }

        double result = parsePrimary();
        return "-".equals(unary) ? -result : result;
    }

    /**
     * Parses primary expressions such as parentheses.
     */
    private double parsePrimary() throws InterpreterException {
        BasicToken token = lexer.nextToken();

        if (token.isText("(")) {
            double result = parseRelational();
            BasicToken closing = lexer.nextToken();

            if (!closing.isText(")")) {
                throw new InterpreterException("Unbalanced Parentheses");
            }

            return result;
        }

        lexer.putBack(token);
        return parseAtom();
    }

    /**
     * Parses atomic values: numbers and variables.
     */
    private double parseAtom() throws InterpreterException {
        BasicToken token = lexer.nextToken();

        return switch (token.type()) {
            case NUMBER -> parseNumber(token.text());
            case VARIABLE -> variableResolver.resolve(token.text());
            default -> throw new InterpreterException("Syntax Error");
        };
    }

    private double parseNumber(String text) throws InterpreterException {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException ex) {
            throw new InterpreterException("Syntax Error");
        }
    }

    private boolean isRelationalOperator(char c) {
        return REL_OPS.indexOf(c) != -1;
    }
}