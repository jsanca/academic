package cr.jsanca.collections.map;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Multi key hash map implementation
 *
 * @author jsanca
 */
public class MultiKeyHashMap<K, V> extends HashMap<K, V> implements MultiKeyMap<K, V> {

    private Map<K, List<K>> keyListMap = new HashMap<K, List<K>>();


    @Override
    public MultiKeyMap addSynonyms(K key, V value, K... synonymKeys) {

        // creates the list of keys
        final ArrayList<K> keyList = new ArrayList<K>(Arrays.asList(synonymKeys));
        keyList.add(key);

        // add the key/value replicated entries
        this.put(key, value);
        this.keyListMap.put(key, keyList);

        if (null != synonymKeys) {

            for (K synonymKey : synonymKeys) {

                this.put(synonymKey, value);
                this.keyListMap.put(synonymKey, keyList);
            }
        }

        return this;
    } // addSynonyms.

    @Override
    public V put(K key, V value) {

        final List<K> keyList = this.keyListMap.get(key);

        if (null != keyList) {

            for (K synonymKey : keyList) {

                super.put(synonymKey, value);
            }
        }

        // redundant but not a big deal
        return super.put(key, value);
    } // put.

    public static void main(String[] args) {

        Map<String, Integer> multiKeyMap =
                new MultiKeyHashMap<String, Integer>().addSynonyms("A", 0).addSynonyms("B", 0, "C");

        // Case 1
        final Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("A", 50);
        map.put("B", 50);
        map.put("C", 50);

        sum(map, multiKeyMap);

        System.out.println("Case 1: " + map);
        System.out.println("A = " + multiKeyMap.get("A"));
        System.out.println("B = " + multiKeyMap.get("B"));
        System.out.println("C = " + multiKeyMap.get("C"));

        // Case 2
        map.clear();
        map.put("A", 50);
        map.put("B", 50);

        multiKeyMap =
                new MultiKeyHashMap<String, Integer>().addSynonyms("A", 0).addSynonyms("B", 0, "C");
        sum(map, multiKeyMap);

        System.out.println("Case 2: " +  map);
        System.out.println("A = " + multiKeyMap.get("A"));
        System.out.println("B = " + multiKeyMap.get("B"));
        System.out.println("C = " + multiKeyMap.get("C"));

        // Case 3
        map.clear();

        map.put("A", 50);
        map.put("C", 50);

        System.out.println("Case 3: "  + map);
        System.out.println("A = " + multiKeyMap.get("A"));
        System.out.println("B = " + multiKeyMap.get("B"));
        System.out.println("C = " + multiKeyMap.get("C"));
    }

    private static void sum (Map<String, Integer> tableMap, Map<String, Integer> resultMap) {

        int result = 0;

        for (Map.Entry<String, Integer> entry: tableMap.entrySet()) {

            result = resultMap.get(entry.getKey());
            result += entry.getValue();
            resultMap.put(entry.getKey(), result);
        }
    }
} // E:O:F:MultiKeyHashMap.
