package jsanca.artofjava.cap3;

import java.util.Map;

/**
 * A simple lexical analyzer (lexer) for a BASIC-like language.
 *
 * <p>This class scans a character stream and produces {@link BasicToken} objects.
 * Each token represents a meaningful unit such as keywords, variables, numbers,
 * delimiters, or quoted strings.</p>
 *
 * <p>The lexer maintains an internal cursor (index) and supports a single-token
 * pushback mechanism for basic lookahead.</p>
 *
 * <p>This implementation is intentionally simple and educational, inspired by
 * classic interpreter designs.</p>
 */
public class BasicLexer {

    static final char LE = 1;
    static final char GE = 2;
    static final char NE = 3;

    private static final String EOP = "\0";

    private final char[] source;
    private int index;

    private BasicToken pushedBackToken;

    private final Map<String, BasicKeyword> keywords = Map.ofEntries(
            Map.entry("print", BasicKeyword.PRINT),
            Map.entry("input", BasicKeyword.INPUT),
            Map.entry("if", BasicKeyword.IF),
            Map.entry("then", BasicKeyword.THEN),
            Map.entry("for", BasicKeyword.FOR),
            Map.entry("next", BasicKeyword.NEXT),
            Map.entry("to", BasicKeyword.TO),
            Map.entry("goto", BasicKeyword.GOTO),
            Map.entry("gosub", BasicKeyword.GOSUB),
            Map.entry("return", BasicKeyword.RETURN),
            Map.entry("end", BasicKeyword.END)
    );

    /**
     * Creates a new lexer for the given source input.
     *
     * @param source the input program as a character array
     */
    public BasicLexer(char[] source) {
        this.source = source;
        this.index = 0;
    }

    /**
     * Returns the current position in the source.
     *
     * @return current index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets the current position of the lexer.
     * Also clears any pushed-back token.
     *
     * @param index new position
     */
    public void setIndex(int index) {
        this.index = index;
        this.pushedBackToken = null;
    }

    /**
     * Pushes a token back so it will be returned on the next call to {@link #nextToken()}.
     *
     * @param token token to push back
     */
    public void putBack(BasicToken token) {
        this.pushedBackToken = token;
    }

    /**
     * Retrieves the next token from the input stream.
     *
     * <p>This method skips whitespace, detects token types, and delegates
     * to helper methods for parsing.</p>
     *
     * @return next token
     * @throws InterpreterException if a lexical error occurs
     */
    public BasicToken nextToken() throws InterpreterException {
        if (pushedBackToken != null) {
            BasicToken token = pushedBackToken;
            pushedBackToken = null;
            return token;
        }

        skipSpacesAndTabs();

        if (index >= source.length) {
            return BasicToken.eop();
        }

        if (source[index] == '\r') {
            if (index + 1 < source.length && source[index + 1] == '\n') {
                index += 2;
                return BasicToken.eol("\r\n");
            }
            index++;
            return BasicToken.eol("\r");
        }

        if (source[index] == '\n') {
            index++;
            return BasicToken.eol("\n");
        }

        char ch = source[index];

        if (ch == '<' || ch == '>') {
            return relationalToken();
        }

        if (isDelimiter(ch)) {
            index++;
            return new BasicToken(String.valueOf(ch), BasicTokenType.DELIMITER, BasicKeyword.UNKNOWN);
        }

        if (Character.isLetter(ch)) {
            return readIdentifierOrKeyword();
        }

        if (Character.isDigit(ch)) {
            return readNumber();
        }

        if (ch == '"') {
            return readQuotedString();
        }

        return BasicToken.eop();
    }

    /**
     * Skips spaces and tab characters.
     */
    private void skipSpacesAndTabs() {
        while (index < source.length && (source[index] == ' ' || source[index] == '\t')) {
            index++;
        }
    }

    /**
     * Parses relational operators such as <, >, <=, >=, and <>.
     *
     * @return token representing the relational operator
     * @throws InterpreterException if malformed
     */
    private BasicToken relationalToken() throws InterpreterException {
        char ch = source[index];

        if (index + 1 >= source.length) {
            throw new InterpreterException("Syntax Error");
        }

        if (ch == '<') {
            if (source[index + 1] == '>') {
                index += 2;
                return new BasicToken(String.valueOf(NE), BasicTokenType.DELIMITER, BasicKeyword.UNKNOWN);
            }
            if (source[index + 1] == '=') {
                index += 2;
                return new BasicToken(String.valueOf(LE), BasicTokenType.DELIMITER, BasicKeyword.UNKNOWN);
            }
            index++;
            return new BasicToken("<", BasicTokenType.DELIMITER, BasicKeyword.UNKNOWN);
        }

        if (source[index + 1] == '=') {
            index += 2;
            return new BasicToken(String.valueOf(GE), BasicTokenType.DELIMITER, BasicKeyword.UNKNOWN);
        }

        index++;
        return new BasicToken(">", BasicTokenType.DELIMITER, BasicKeyword.UNKNOWN);
    }

    /**
     * Reads an identifier or keyword.
     *
     * @return VARIABLE or COMMAND token
     */
    private BasicToken readIdentifierOrKeyword() {
        StringBuilder builder = new StringBuilder();

        while (index < source.length && !isDelimiter(source[index])) {
            builder.append(source[index]);
            index++;
        }

        String text = builder.toString();
        BasicKeyword keyword = keywords.getOrDefault(text.toLowerCase(), BasicKeyword.UNKNOWN);

        if (keyword == BasicKeyword.UNKNOWN) {
            return new BasicToken(text, BasicTokenType.VARIABLE, BasicKeyword.UNKNOWN);
        }

        return new BasicToken(text, BasicTokenType.COMMAND, keyword);
    }

    /**
     * Reads a numeric literal.
     *
     * @return NUMBER token
     */
    private BasicToken readNumber() {
        StringBuilder builder = new StringBuilder();

        while (index < source.length && !isDelimiter(source[index])) {
            builder.append(source[index]);
            index++;
        }

        return new BasicToken(builder.toString(), BasicTokenType.NUMBER, BasicKeyword.UNKNOWN);
    }

    /**
     * Reads a quoted string literal.
     *
     * @return QUOTED_STRING token
     * @throws InterpreterException if closing quote is missing
     */
    private BasicToken readQuotedString() throws InterpreterException {
        index++;
        StringBuilder builder = new StringBuilder();

        while (index < source.length && source[index] != '"' && source[index] != '\r' && source[index] != '\n') {
            builder.append(source[index]);
            index++;
        }

        if (index >= source.length || source[index] == '\r' || source[index] == '\n') {
            throw new InterpreterException("Closing quotes needed");
        }

        index++;
        return new BasicToken(builder.toString(), BasicTokenType.QUOTED_STRING, BasicKeyword.UNKNOWN);
    }

    /**
     * Checks whether a character is a delimiter.
     *
     * @param c character to check
     * @return true if delimiter
     */
    private boolean isDelimiter(char c) {
        return " \t\r\n,;<>+-/*%^=()".indexOf(c) != -1;
    }
}