package jsanca.artofjava.cap3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BasicLexerTest {

    @Test
    void shouldReturnEndOfProgramForEmptyInput() throws InterpreterException {
        BasicToken token = lexerOf("").nextToken();

        assertToken(token, "\0", BasicTokenType.EOP, BasicKeyword.UNKNOWN);
    }

    @Test
    void shouldSkipLeadingSpacesAndTabs() throws InterpreterException {
        BasicToken token = lexerOf("   \t\tPRINT").nextToken();

        assertToken(token, "PRINT", BasicTokenType.COMMAND, BasicKeyword.PRINT);
    }

    @Test
    void shouldReadKeywordIgnoringCase() throws InterpreterException {
        BasicToken token = lexerOf("pRiNt").nextToken();

        assertToken(token, "pRiNt", BasicTokenType.COMMAND, BasicKeyword.PRINT);
    }

    @Test
    void shouldReadVariableWhenIdentifierIsNotKeyword() throws InterpreterException {
        BasicToken token = lexerOf("total").nextToken();

        assertToken(token, "total", BasicTokenType.VARIABLE, BasicKeyword.UNKNOWN);
    }

    @Test
    void shouldReadNumberToken() throws InterpreterException {
        BasicToken token = lexerOf("12345").nextToken();

        assertToken(token, "12345", BasicTokenType.NUMBER, BasicKeyword.UNKNOWN);
    }

    @Test
    void shouldReadQuotedStringToken() throws InterpreterException {
        BasicToken token = lexerOf("\"Hello BASIC\"").nextToken();

        assertToken(token, "Hello BASIC", BasicTokenType.QUOTED_STRING, BasicKeyword.UNKNOWN);
    }

    @Test
    void shouldReadSimpleDelimiter() throws InterpreterException {
        BasicToken token = lexerOf("+").nextToken();

        assertToken(token, "+", BasicTokenType.DELIMITER, BasicKeyword.UNKNOWN);
    }

    @Test
    void shouldReadLessThanDelimiter() throws InterpreterException {
        BasicToken token = lexerOf("< ").nextToken();

        assertToken(token, "<", BasicTokenType.DELIMITER, BasicKeyword.UNKNOWN);
    }

    @Test
    void shouldReadGreaterThanDelimiter() throws InterpreterException {
        BasicToken token = lexerOf("> ").nextToken();

        assertToken(token, ">", BasicTokenType.DELIMITER, BasicKeyword.UNKNOWN);
    }

    @Test
    void shouldReadLessThanOrEqualDelimiter() throws InterpreterException {
        BasicToken token = lexerOf("<=").nextToken();

        assertToken(token, String.valueOf(BasicLexer.LE), BasicTokenType.DELIMITER, BasicKeyword.UNKNOWN);
    }

    @Test
    void shouldReadGreaterThanOrEqualDelimiter() throws InterpreterException {
        BasicToken token = lexerOf(">=").nextToken();

        assertToken(token, String.valueOf(BasicLexer.GE), BasicTokenType.DELIMITER, BasicKeyword.UNKNOWN);
    }

    @Test
    void shouldReadNotEqualDelimiter() throws InterpreterException {
        BasicToken token = lexerOf("<>").nextToken();

        assertToken(token, String.valueOf(BasicLexer.NE), BasicTokenType.DELIMITER, BasicKeyword.UNKNOWN);
    }

    @Test
    void shouldReturnEolForLineFeed() throws InterpreterException {
        BasicToken token = lexerOf("\n").nextToken();

        assertToken(token, "\n", BasicTokenType.EOL, BasicKeyword.UNKNOWN);
    }

    @Test
    void shouldReturnEolForCarriageReturn() throws InterpreterException {
        BasicToken token = lexerOf("\r").nextToken();

        assertToken(token, "\r", BasicTokenType.EOL, BasicKeyword.UNKNOWN);
    }

    @Test
    void shouldReturnEolForCarriageReturnLineFeed() throws InterpreterException {
        BasicToken token = lexerOf("\r\n").nextToken();

        assertToken(token, "\r\n", BasicTokenType.EOL, BasicKeyword.UNKNOWN);
    }

    @Test
    void shouldPushBackToken() throws InterpreterException {
        BasicLexer lexer = lexerOf("PRINT");
        BasicToken token = lexer.nextToken();

        lexer.putBack(token);

        BasicToken pushedBack = lexer.nextToken();
        assertEquals(token, pushedBack);
    }

    @Test
    void shouldSetIndexToReadFromAnotherPosition() throws InterpreterException {
        BasicLexer lexer = lexerOf("10 PRINT");

        lexer.setIndex(3);

        BasicToken token = lexer.nextToken();
        assertToken(token, "PRINT", BasicTokenType.COMMAND, BasicKeyword.PRINT);
    }

    @Test
    void shouldRewindToProgramStart() throws InterpreterException {
        BasicLexer lexer = lexerOf("PRINT");
        lexer.nextToken();

        lexer.rewindToProgramStart();

        BasicToken token = lexer.nextToken();
        assertToken(token, "PRINT", BasicTokenType.COMMAND, BasicKeyword.PRINT);
        assertEquals(5, lexer.getIndex());
    }

    @Test
    void shouldClearPushedBackTokenWhenIndexChanges() throws InterpreterException {
        BasicLexer lexer = lexerOf("PRINT INPUT");
        BasicToken printToken = lexer.nextToken();
        lexer.putBack(printToken);

        lexer.setIndex(6);

        BasicToken token = lexer.nextToken();
        assertToken(token, "INPUT", BasicTokenType.COMMAND, BasicKeyword.INPUT);
    }

    @Test
    void shouldReadMultipleTokensSequentially() throws InterpreterException {
        BasicLexer lexer = lexerOf("PRINT A + 10");

        assertToken(lexer.nextToken(), "PRINT", BasicTokenType.COMMAND, BasicKeyword.PRINT);
        assertToken(lexer.nextToken(), "A", BasicTokenType.VARIABLE, BasicKeyword.UNKNOWN);
        assertToken(lexer.nextToken(), "+", BasicTokenType.DELIMITER, BasicKeyword.UNKNOWN);
        assertToken(lexer.nextToken(), "10", BasicTokenType.NUMBER, BasicKeyword.UNKNOWN);
        assertToken(lexer.nextToken(), "\0", BasicTokenType.EOP, BasicKeyword.UNKNOWN);
    }

    @Test
    void shouldThrowWhenQuotedStringIsNotClosed() {
        InterpreterException exception = assertThrows(
                InterpreterException.class,
                () -> lexerOf("\"Hello").nextToken()
        );

        assertEquals("Closing quotes needed", exception.getMessage());
    }

    @Test
    void shouldThrowWhenQuotedStringHitsNewLineBeforeClosing() {
        InterpreterException exception = assertThrows(
                InterpreterException.class,
                () -> lexerOf("\"Hello\nWorld").nextToken()
        );

        assertEquals("Closing quotes needed", exception.getMessage());
    }

    @Test
    void shouldTreatUnknownCharacterAsEndOfProgram() throws InterpreterException {
        BasicToken token = lexerOf("@").nextToken();

        assertToken(token, "\0", BasicTokenType.EOP, BasicKeyword.UNKNOWN);
    }

    @Test
    void shouldTrackCurrentIndexAfterReadingToken() throws InterpreterException {
        BasicLexer lexer = lexerOf("PRINT A");

        lexer.nextToken();

        assertEquals(5, lexer.getIndex());
    }

    private static BasicLexer lexerOf(String source) {
        return new BasicLexer(source.toCharArray());
    }

    private static void assertToken(
            BasicToken token,
            String expectedText,
            BasicTokenType expectedType,
            BasicKeyword expectedKeyword
    ) {
        assertEquals(expectedText, token.text());
        assertEquals(expectedType, token.type());
        assertEquals(expectedKeyword, token.keyword());
    }
}
