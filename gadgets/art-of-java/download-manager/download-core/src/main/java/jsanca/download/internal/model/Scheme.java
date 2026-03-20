package jsanca.download.internal.model;

import java.util.Locale;

public enum Scheme {

    HTTP,
    HTTPS;

    public static Scheme from(final String scheme) {
        final Scheme resolvedScheme;
        try {

            resolvedScheme = Scheme.valueOf(scheme.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "No download strategy available for scheme: " + scheme, e);
        }

        return resolvedScheme;
    }
}
