package examples;

import iBoxDB.LocalServer.*;
import iBoxDB.LocalServer.IO.BoxFileStreamConfig;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyDB {

    public static String mainPath;
    public static String defaultPath;

    public final static boolean isDemo = true;
    private static DB.AutoBox instance;
    private static DB db;

    public static DB.AutoBox getInstance() {
        return instance;
    }

    public static void init(String defPath, String path) {
        Logger.getLogger(MyDB.class.getName()).log(Level.INFO, String.format("DBPath=%s", path));

        // Setting the primary database storage path
        DB.root(path);
        mainPath = BoxFileStreamConfig.RootPath;

        db = new DB();
        db.getConfig().Config.CachePageCount = 1024 * (isDemo ? 1 : 512);

        // Create a table with the specified primary key
        db.getConfig().ensureTable("Record", Record.class, "ID");

        // Open the main database
        instance = db.open();

        // Set other database storage path
        DB.root(defPath);
        defaultPath = BoxFileStreamConfig.RootPath;
    }

    public static void close() {
        if (db != null) {
            db.close();
            Logger.getLogger(MyDB.class.getName()).log(Level.INFO, "DBClosed");
        }
        db = null;
        instance = null;
    }
}