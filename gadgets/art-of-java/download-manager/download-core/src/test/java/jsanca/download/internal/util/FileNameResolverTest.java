package jsanca.download.internal.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileNameResolverTest {

    @Test
    @DisplayName("Should resolve file name from a standard URL path")
    void shouldResolveFileNameFromStandardUrl() {
        String url = "https://example.com/files/report.pdf";

        String result = FileNameResolver.resolveFileName(url);

        assertEquals("report.pdf", result);
    }

    @Test
    @DisplayName("Should return default file name when URL path ends with slash")
    void shouldReturnDefaultFileNameWhenPathEndsWithSlash() {
        String url = "https://example.com/files/";

        String result = FileNameResolver.resolveFileName(url);

        assertEquals(FileNameResolver.DEFAULT_FILE_NAME, result);
    }

    @Test
    @DisplayName("Should return default file name when URL has no path")
    void shouldReturnDefaultFileNameWhenUrlHasNoPath() {
        String url = "https://example.com";

        String result = FileNameResolver.resolveFileName(url);

        assertEquals(FileNameResolver.DEFAULT_FILE_NAME, result);
    }

    @Test
    @DisplayName("Should ignore query parameters when resolving file name")
    void shouldIgnoreQueryParametersWhenResolvingFileName() {
        String url = "https://example.com/download/archive.zip?token=12345";

        String result = FileNameResolver.resolveFileName(url);

        assertEquals("archive.zip", result);
    }

    @Test
    @DisplayName("Should ignore fragment when resolving file name")
    void shouldIgnoreFragmentWhenResolvingFileName() {
        String url = "https://example.com/docs/manual.txt#section-2";

        String result = FileNameResolver.resolveFileName(url);

        assertEquals("manual.txt", result);
    }

    @Test
    @DisplayName("Should return default file name for blank final path segment")
    void shouldReturnDefaultFileNameForBlankFinalPathSegment() {
        String url = "https://example.com/files/%20";

        String result = FileNameResolver.resolveFileName(url);

        assertEquals(FileNameResolver.DEFAULT_FILE_NAME, result);
    }

    @Test
    @DisplayName("Should throw NullPointerException when URL is null")
    void shouldThrowNullPointerExceptionWhenUrlIsNull() {
        assertThrows(NullPointerException.class, () -> FileNameResolver.resolveFileName(null));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when URL is invalid")
    void shouldThrowIllegalArgumentExceptionWhenUrlIsInvalid() {
        String url = "ht!tp://bad url";

        assertThrows(IllegalArgumentException.class, () -> FileNameResolver.resolveFileName(url));
    }
}
