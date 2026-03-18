package jsanca.artofjava.cap3;

import java.util.Map;

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

    public BasicLexer(char[] source) {
        this.source = source;
        this.index = 0;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
        this.pushedBackToken = null;
    }

    public void putBack(BasicToken token) {
        this.pushedBackToken = token;
    }

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

    private void skipSpacesAndTabs() {
        while (index < source.length && (source[index] == ' ' || source[index] == '\t')) {
            index++;
        }
    }

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

    private BasicToken readNumber() {
        StringBuilder builder = new StringBuilder();

        while (index < source.length && !isDelimiter(source[index])) {
            builder.append(source[index]);
            index++;
        }

        return new BasicToken(builder.toString(), BasicTokenType.NUMBER, BasicKeyword.UNKNOWN);
    }

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

    private boolean isDelimiter(char c) {
        return " \t\r\n,;<>+-/*%^=()".indexOf(c) != -1;
    }
}