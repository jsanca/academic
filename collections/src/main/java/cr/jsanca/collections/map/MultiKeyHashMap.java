package cr.jsanca.collections.map;

import java.util.*;

/**
 * Multi key hash map implementation
 *
 * @author jsanca
 */
public class MultiKeyHashMap<K, V> extends HashMap<K, V> implements MultiKeyMap<K, V> {

    private final Map<K, K> keyIndexMap = new HashMap<K, K>();


    @Override
    public MultiKeyMap addSynonyms(K key, V value, K... synonymKeys) {

        // add the key/value replicated entries
        this.put(key, value);

        // index the synonyms
        if (null != synonymKeys) {

            for (K synonymKey : synonymKeys) {

                this.keyIndexMap.put(synonymKey, key);
            }
        }

        return this;
    } // addSynonyms.

    private K getFirstOne(final LinkedHashSet<K> keySet) {

        return keySet.iterator().next();
    }

    @Override
    public V put(K key, V value) {

        // if it is a synonym, so use the main key
        if (this.keyIndexMap.containsKey(key)) {

            key = this.keyIndexMap.get(key);
        }

        return super.put(key, value);
    } // put.

    @Override
    public V get(Object key) {

        // if it is a synonym, so use the main key
        if (this.keyIndexMap.containsKey(key)) {

            key = this.keyIndexMap.get(key);
        }

        return super.get(key);
    }

    public static void main(String[] args) {

        Map<String, Integer> multiKeyMap =
                new MultiKeyHashMap<String, Integer>().addSynonyms("A", 0).addSynonyms("B", 0, "C", "D");

        // Case 1
        final Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("A", 50);
        map.put("B", 50);
        map.put("C", 50);
        map.put("D", 50);

        sum(map, multiKeyMap);

        System.out.println("Case 1: " + map);
        System.out.println("A = " + multiKeyMap.get("A"));
        System.out.println("B = " + multiKeyMap.get("B"));
        System.out.println("C = " + multiKeyMap.get("C"));
        System.out.println("multiKeyMap = " + multiKeyMap);

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
        System.out.println("multiKeyMap = " + multiKeyMap);


        // Case 3
        map.clear();

        map.put("A", 50);
        map.put("C", 50);

        System.out.println("Case 3: "  + map);
        System.out.println("A = " + multiKeyMap.get("A"));
        System.out.println("B = " + multiKeyMap.get("B"));
        System.out.println("C = " + multiKeyMap.get("C"));
        System.out.println("multiKeyMap = " + multiKeyMap);
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
