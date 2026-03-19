package jsanca.artofjava.cap3;

/**
 * Token categories produced by the BASIC lexer.
 *
 * <p>These values describe the role of the current lexeme in the source stream.
 * They are intentionally small and generic because the interpreter only needs a
 * lightweight classification to dispatch statements and parse expressions.
 *
 * <p>Special cases:
 * <ul>
 *     <li>{@link #EOL}: end of line. This marks the boundary between BASIC statements.</li>
 *     <li>{@link #EOP}: end of program. This marks that the lexer has consumed all input.</li>
 * </ul>
 * @author jsanca & elo
 */
public enum BasicTokenType {

    /**
     * No meaningful token type has been assigned yet.
     */
    NONE,

    /**
     * Punctuation or operator token such as {@code +}, {@code -}, {@code =},
     * {@code (}, {@code )}, {@code <}, or {@code >}.
     */
    DELIMITER,

    /**
     * Variable identifier token such as {@code X}.
     */
    VARIABLE,

    /**
     * Numeric literal token such as {@code 10} or {@code 3.14}.
     */
    NUMBER,

    /**
     * Reserved BASIC keyword such as {@code PRINT}, {@code IF}, or {@code FOR}.
     */
    COMMAND,

    /**
     * Text enclosed in double quotes, for example {@code "HELLO"}.
     */
    QUOTED_STRING,

    /**
     * End of line.
     *
     * <p>Used by the interpreter to detect when one BASIC statement finishes and
     * the next line begins.
     */
    EOL,

    /**
     * End of program.
     *
     * <p>Used by the lexer and interpreter to signal that there is no more input
     * to read from the source buffer.
     */
    EOP
}