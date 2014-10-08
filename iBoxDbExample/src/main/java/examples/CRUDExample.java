package examples;

import iBoxDB.LocalServer.Box;
import iBoxDB.LocalServer.CommitResult;

import java.io.File;
import java.text.MessageFormat;

/**
 * Based on https://coding.net/u/iboxdb/p/java-servlet3-package/git/blob/master/src/main/java/iBoxDB/samples/path/RecordCRUD.java
 */
public class CRUDExample {

    public static void main (String [ ] args) {

        String path = "./db";
        String tmpPath = "./tmpdb";
        File filePath = new File(path);

        if (!filePath.exists()) {

            filePath.mkdirs();
        }

        File fileTempPath = new File(path);

        if (!fileTempPath.exists()) {

            fileTempPath.mkdirs();
        }

        MyDB.init(tmpPath, path);

        long id;
        CRUDExample crudExample = new CRUDExample();
        Record r  = crudExample.insert(null);
        Record r1  = crudExample.insert(null);
        Record r2  = crudExample.insert(null);
        id = r.ID;
        Record ra = crudExample.key(id);

        System.out.println
                (MessageFormat.format("r id: {0} | ra id: {1}", r.ID, ra.ID));
        System.out.println
                (MessageFormat.format("r id: {0} | ra id: {1}", r.Name, ra.Name));
        System.out.println
                (MessageFormat.format("r id: {0} | ra id: {1}", r.RegTime, ra.RegTime));

        System.out.println("Updating the name");

        crudExample.update(r.ID, "A new Name");

        ra = crudExample.key(id);

        System.out.println
                (MessageFormat.format("r id: {0} | ra id: {1}", r.ID, ra.ID));
        System.out.println
                (MessageFormat.format("r id: {0} | ra id: {1}", r.Name, ra.Name));
        System.out.println
                (MessageFormat.format("r id: {0} | ra id: {1}", r.RegTime, ra.RegTime));

        Iterable<Record> list = crudExample.all();

        System.out.println("Printing the list");

        for(Record record : list) {

            System.out.println
                    (MessageFormat.format("r id: {0}", record.ID));
            System.out.println
                    (MessageFormat.format("r id: {0}", record.Name));
            System.out.println
                    (MessageFormat.format("r id: {0}", record.RegTime));

            crudExample.delete(record.ID);
        }

        MyDB.close();
    }

    public Record insert(String username) {

        long id = MyDB.getInstance().newId(1);
        Record r = new Record();
        r.ID = id;
        if (username == null) {
            username = "Name-" + r.ID;
        }
        r.Name = username;
        r.RegTime = System.currentTimeMillis();
        MyDB.getInstance().insert("Record", r);
        return r;
    }

    public Record update(
            long id,
            String username) {

        try (Box box = MyDB.getInstance().cube()) {
            Record r = box.bind("Record", id).select(Record.class);
            if (r != null) {
                r.Name = username;
                if (box.bind("Record", id).update(r)) {
                    if (box.commit() == CommitResult.OK) {
                        return r;
                    }
                }
            }
        }
        return null;
    }

    public Record key(long id) {

        return MyDB.getInstance().selectKey(Record.class, "Record", id);
    }

    public Iterable<Record> last( long id) {
        // Case-sensitive SQL
        return MyDB.getInstance().select(Record.class, "from Record where ID<? limit 0,20", id);
    }

    public Iterable<Record> all() {
        // Case-sensitive SQL
        return MyDB.getInstance().select(Record.class, "from Record");
    }

    public boolean delete (long id) {

        boolean deleted = false;
        try (Box box = MyDB.getInstance().cube()) {

            deleted = box.bind("Record", id).delete();
        }

        return deleted;
    }

}
