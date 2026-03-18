package jsanca.artofjava.cap3;

/**
 * Enumeration of all reserved BASIC keywords recognized by the interpreter.
 *
 * <p>This enum is used by the lexer to classify identifiers that match
 * language commands (e.g., PRINT, IF, FOR). If a token does not match any
 * known keyword, it is classified as {@link #UNKNOWN}.
 */
public enum BasicKeyword {

    /**
     * Represents an unknown or non-keyword token.
     *
     * <p>This replaces the legacy name "UNKNCOM" (unknown command), which was
     * less clear and prone to confusion. It is used when a token is not a
     * reserved BASIC keyword.
     */
    UNKNOWN,

    /** Print statement (e.g., PRINT "HELLO") */
    PRINT,

    /** Input statement (e.g., INPUT X) */
    INPUT,

    /** Conditional statement (e.g., IF X > 0 THEN ...) */
    IF,

    /** THEN keyword used in IF statements */
    THEN,

    /** FOR loop declaration */
    FOR,

    /** NEXT keyword for loop continuation */
    NEXT,

    /** TO keyword used in FOR loops */
    TO,

    /** Unconditional jump */
    GOTO,

    /** Subroutine call */
    GOSUB,

    /** Return from subroutine */
    RETURN,

    /** Program termination */
    END
}