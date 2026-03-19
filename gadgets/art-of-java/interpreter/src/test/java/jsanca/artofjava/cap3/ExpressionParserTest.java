package jsanca.artofjava.cap3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpressionParserTest {

    @Test
    void shouldEvaluateSingleNumber() throws InterpreterException {
        assertEvaluatesTo("42", 42.0);
    }

    @Test
    void shouldEvaluateAdditionAndSubtraction() throws InterpreterException {
        assertEvaluatesTo("10 + 5 - 3", 12.0);
    }

    @Test
    void shouldRespectOperatorPrecedence() throws InterpreterException {
        assertEvaluatesTo("2 + 3 * 4", 14.0);
    }

    @Test
    void shouldRespectParentheses() throws InterpreterException {
        assertEvaluatesTo("(2 + 3) * 4", 20.0);
    }

    @Test
    void shouldEvaluateUnaryMinus() throws InterpreterException {
        assertEvaluatesTo("-5 + 2", -3.0);
    }

    @Test
    void shouldEvaluateUnaryPlus() throws InterpreterException {
        assertEvaluatesTo("+5", 5.0);
    }

    @Test
    void shouldResolveVariables() throws InterpreterException {
        assertEvaluatesTo("A + B * 2", 8.0, variableName -> switch (variableName) {
            case "A" -> 2.0;
            case "B" -> 3.0;
            default -> throw new InterpreterException("Unknown variable: " + variableName);
        });
    }

    @Test
    void shouldEvaluateExponentiation() throws InterpreterException {
        assertEvaluatesTo("2 ^ 3", 8.0);
    }

    @Test
    void shouldEvaluateExponentiationAsRightAssociative() throws InterpreterException {
        assertEvaluatesTo("2 ^ 3 ^ 2", 512.0);
    }

    @Test
    void shouldEvaluateModulo() throws InterpreterException {
        assertEvaluatesTo("10 % 3", 1.0);
    }

    @Test
    void shouldEvaluateRelationalLessThan() throws InterpreterException {
        assertEvaluatesTo("2 < 3", 1.0);
    }

    @Test
    void shouldEvaluateRelationalGreaterThan() throws InterpreterException {
        assertEvaluatesTo("5 > 7", 0.0);
    }

    @Test
    void shouldEvaluateRelationalLessThanOrEqual() throws InterpreterException {
        assertEvaluatesTo("3 <= 3", 1.0);
    }

    @Test
    void shouldEvaluateRelationalGreaterThanOrEqual() throws InterpreterException {
        assertEvaluatesTo("4 >= 9", 0.0);
    }

    @Test
    void shouldEvaluateRelationalEquality() throws InterpreterException {
        assertEvaluatesTo("8 = 8", 1.0);
    }

    @Test
    void shouldEvaluateRelationalNotEqual() throws InterpreterException {
        assertEvaluatesTo("8 <> 3", 1.0);
    }

    @Test
    void shouldAllowWhitespaceAroundExpression() throws InterpreterException {
        assertEvaluatesTo("   7 + 8   ", 15.0);
    }

    @Test
    void shouldThrowWhenExpressionIsEmpty() {
        InterpreterException exception = assertThrows(
                InterpreterException.class,
                () -> evaluate("")
        );

        assertEquals("No Expression Present", exception.getMessage());
    }

    @Test
    void shouldThrowWhenParenthesesAreUnbalanced() {
        InterpreterException exception = assertThrows(
                InterpreterException.class,
                () -> evaluate("(2 + 3")
        );

        assertEquals("Unbalanced Parentheses", exception.getMessage());
    }

    @Test
    void shouldThrowWhenDivisionByZeroOccurs() {
        InterpreterException exception = assertThrows(
                InterpreterException.class,
                () -> evaluate("10 / 0")
        );

        assertEquals("Division by Zero", exception.getMessage());
    }

    @Test
    void shouldThrowWhenModuloByZeroOccurs() {
        InterpreterException exception = assertThrows(
                InterpreterException.class,
                () -> evaluate("10 % 0")
        );

        assertEquals("Division by Zero", exception.getMessage());
    }

    @Test
    void shouldThrowWhenExpressionStartsWithInvalidToken() {
        InterpreterException exception = assertThrows(
                InterpreterException.class,
                () -> evaluate(")")
        );

        assertEquals("Syntax Error", exception.getMessage());
    }

    @Test
    void shouldThrowWhenVariableCannotBeResolved() {
        InterpreterException exception = assertThrows(
                InterpreterException.class,
                () -> evaluate("Z + 1", variableName -> {
                    throw new InterpreterException("Unknown variable: " + variableName);
                })
        );

        assertEquals("Unknown variable: Z", exception.getMessage());
    }

    private static void assertEvaluatesTo(String expression, double expected) throws InterpreterException {
        assertEvaluatesTo(expression, expected, variableName -> {
            throw new InterpreterException("Unknown variable: " + variableName);
        });
    }

    private static void assertEvaluatesTo(
            String expression,
            double expected,
            VariableResolver variableResolver
    ) throws InterpreterException {
        assertEquals(expected, evaluate(expression, variableResolver), 0.000001);
    }

    private static double evaluate(String expression) throws InterpreterException {
        return evaluate(expression, variableName -> {
            throw new InterpreterException("Unknown variable: " + variableName);
        });
    }

    private static double evaluate(String expression, VariableResolver variableResolver) throws InterpreterException {
        BasicLexer lexer = new BasicLexer(expression.toCharArray());
        ExpressionParser parser = new ExpressionParser(lexer, variableResolver);
        return parser.evaluate();
    }
}
