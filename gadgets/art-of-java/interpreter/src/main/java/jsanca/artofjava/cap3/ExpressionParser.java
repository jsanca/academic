package jsanca.artofjava.cap3;

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

    public double evaluate() throws InterpreterException {
        BasicToken token = lexer.nextToken();
        if (token.type() == BasicTokenType.EOP) {
            throw new InterpreterException("No Expression Present");
        }

        lexer.putBack(token);
        return evalExp1();
    }

    private double evalExp1() throws InterpreterException {
        double result = evalExp2();
        BasicToken token = lexer.nextToken();

        if (token.type() == BasicTokenType.EOP || token.type() == BasicTokenType.EOL) {
            lexer.putBack(token);
            return result;
        }

        char op = token.text().charAt(0);
        if (isRelationalOperator(op)) {
            double left = result;
            double right = evalExp1();

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

    private double evalExp2() throws InterpreterException {
        double result = evalExp3();

        while (true) {
            BasicToken token = lexer.nextToken();

            if (!token.isText("+") && !token.isText("-")) {
                lexer.putBack(token);
                return result;
            }

            double partial = evalExp3();
            result = token.isText("+") ? result + partial : result - partial;
        }
    }

    private double evalExp3() throws InterpreterException {
        double result = evalExp4();

        while (true) {
            BasicToken token = lexer.nextToken();

            if (!token.isText("*") && !token.isText("/") && !token.isText("%")) {
                lexer.putBack(token);
                return result;
            }

            double partial = evalExp4();

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

    private double evalExp4() throws InterpreterException {
        double result = evalExp5();
        BasicToken token = lexer.nextToken();

        if (!token.isText("^")) {
            lexer.putBack(token);
            return result;
        }

        double partial = evalExp4();
        if (partial == 0.0) {
            return 1.0;
        }

        double base = result;
        for (int i = (int) partial - 1; i > 0; i--) {
            result *= base;
        }

        return result;
    }

    private double evalExp5() throws InterpreterException {
        BasicToken token = lexer.nextToken();
        String unary = "";

        if (token.type() == BasicTokenType.DELIMITER && (token.isText("+") || token.isText("-"))) {
            unary = token.text();
        } else {
            lexer.putBack(token);
        }

        double result = evalExp6();
        return "-".equals(unary) ? -result : result;
    }

    private double evalExp6() throws InterpreterException {
        BasicToken token = lexer.nextToken();

        if (token.isText("(")) {
            double result = evalExp1();
            BasicToken closing = lexer.nextToken();

            if (!closing.isText(")")) {
                throw new InterpreterException("Unbalanced Parentheses");
            }

            return result;
        }

        lexer.putBack(token);
        return atom();
    }

    private double atom() throws InterpreterException {
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