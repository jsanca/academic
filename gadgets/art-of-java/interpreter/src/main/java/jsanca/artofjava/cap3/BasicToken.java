package jsanca.artofjava.cap3;

/**
 * Immutable token produced by the BASIC lexer.
 *
 * <p>A token is the smallest meaningful unit consumed by the parser and the
 * interpreter. It carries three pieces of information:
 * <ul>
 *     <li>{@code text}: the original lexeme or normalized symbol text</li>
 *     <li>{@code type}: the general token category</li>
 *     <li>{@code keyword}: the recognized BASIC keyword, when applicable</li>
 * </ul>
 *
 * <p>Examples:
 * <ul>
 *     <li>{@code PRINT} -> text="PRINT", type=COMMAND, keyword=PRINT</li>
 *     <li>{@code X} -> text="X", type=VARIABLE, keyword=UNKNOWN</li>
 *     <li>{@code 10} -> text="10", type=NUMBER, keyword=UNKNOWN</li>
 *     <li>{@code "HELLO"} -> text="HELLO", type=QUOTED_STRING, keyword=UNKNOWN</li>
 * </ul>
 *
 * <p>This record is intentionally small because it sits at the boundary between
 * lexing and interpretation. Keeping it simple makes the lexer easier to reason
 * about and keeps statement parsing readable.
 *
 * @param text token text as read or normalized by the lexer
 * @param type broad token classification
 * @param keyword recognized BASIC keyword, or {@link BasicKeyword#UNKNOWN} when
 *                the token is not a reserved keyword
 * @author jsanca & elo
 */
public record BasicToken(
        String text,
        BasicTokenType type,
        BasicKeyword keyword
) {

    /**
     * Creates a synthetic end-of-program token.
     *
     * <p>This token is returned by the lexer when the full source buffer has
     * already been consumed.
     *
     * @return end-of-program token
     */
    public static BasicToken eop() {
        return new BasicToken("\0", BasicTokenType.EOP, BasicKeyword.UNKNOWN);
    }

    /**
     * Creates a synthetic end-of-line token.
     *
     * <p>The lexer uses this token to mark statement boundaries in the BASIC
     * source code.
     *
     * @param text original line-break representation, such as {@code "\n"} or
     *             {@code "\r\n"}
     * @return end-of-line token
     */
    public static BasicToken eol(String text) {
        return new BasicToken(text, BasicTokenType.EOL, BasicKeyword.UNKNOWN);
    }

    /**
     * Checks whether this token text matches the expected literal text.
     *
     * <p>This is useful for delimiters and symbols such as {@code =},
     * {@code +}, {@code -}, or parentheses.
     *
     * @param expected expected token text
     * @return {@code true} when the token text matches exactly; otherwise {@code false}
     */
    public boolean isText(String expected) {
        return text.equals(expected);
    }

    /**
     * Checks whether this token represents the expected BASIC keyword.
     *
     * @param expected expected keyword
     * @return {@code true} when the token keyword matches; otherwise {@code false}
     */
    public boolean isKeyword(BasicKeyword expected) {
        return keyword == expected;
    }
}