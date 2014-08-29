package monentrevue.dao.directory;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Based on a file extension list do a filter to accept just the extensions in the list.
 *
 * Date: 8/27/14
 * Time: 10:50 AM
 * @author jsanca
 */
public class ExtensionListFilenameFilter implements FilenameFilter {

    private Set<String> extensions = null;

    /**
     * Add the extension to the filter, precondition: it must be on lowercase
     * @param extensions String array of (use lower case)
     */
    public ExtensionListFilenameFilter(String... extensions) {

        this.extensions = new HashSet<String>(Arrays.asList(extensions));
    }

    /**
     * Tests if a specified file should be included in a file list.
     *
     * @param dir  the directory in which the file was found.
     * @param name the name of the file.
     * @return <code>true</code> if and only if the name should be
     *         included in the file list; <code>false</code> otherwise.
     */
    @Override
    public boolean accept(File dir, String name) {

        boolean accept = false;

        for (String ext : this.extensions) {

            accept |= name.toLowerCase().endsWith(ext);
        }

        return accept;
    }
} // E:O:F:ExtensionListFilenameFilter.
