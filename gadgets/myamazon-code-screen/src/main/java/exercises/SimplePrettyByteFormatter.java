package exercises;

import java.text.DecimalFormat;

/**
 * Easy implementation
 * <p/>
 * Date: 7/1/14
 * Time: 11:59 PM
 *
 * @author jsanca
 */
public class SimplePrettyByteFormatter implements PrettyByteFormatter {

    private static final int KILO_BYTE_MOD = 1000;
    private static final int MEGA_BYTE_MOD = 1000 * KILO_BYTE_MOD;
    private static final int GIGA_BYTE_MOD = 1000 * MEGA_BYTE_MOD;
    private static final long MAX_GIGAS = Integer.MAX_VALUE;

    private static final DecimalFormat DECIMAL_FORMATTER = new DecimalFormat("###.###");

    static {

        DECIMAL_FORMATTER.setMaximumIntegerDigits(3);
        DECIMAL_FORMATTER.setMaximumFractionDigits(3);
    }

    /**
     * Format the bytes to a human being readable String
     *
     * @param bytes Integer
     * @return String
     */
    @Override
    public String format(Integer bytes) {

        final StringBuilder builder =
                new StringBuilder();

        bytes = Math.abs(bytes);

        // pre: no more than 2 digits
        if (bytes == MAX_GIGAS) {

            builder.append("999G");
        } else {

            this.format(bytes, builder);
        }


        return builder.toString();
    } // format.

    protected void format(final Integer bytes,
                          final StringBuilder builder) {


        final double gigas =
                (double) bytes / GIGA_BYTE_MOD;

        if ((int)gigas > 0) {

            builder.append
                    (DECIMAL_FORMATTER.format(gigas)).append("G");
        } else {

            final double megas =
                    (double) bytes / MEGA_BYTE_MOD;

            if ((int)megas > 0) {

                builder.append
                        (DECIMAL_FORMATTER.format(megas)).append("M");
            } else {

                final double kilos =
                        (double) bytes / KILO_BYTE_MOD;

                if ((int)kilos > 0) {

                    builder.append
                            (DECIMAL_FORMATTER.format(kilos)).append("K");
                } else {

                    builder.append
                            (DECIMAL_FORMATTER.format(bytes)).append("B");
                }
            }
        }

    } // format.

} // E:O:F:SimplePrettyByteFormatter.
