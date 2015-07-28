package cr.jsanca.collections.map;

import java.io.Serializable;
import java.util.Map;

/**
 * Implements a multi key map which is a map with a key and synonyms for that key
 * @author jsanca
 */
public interface MultiKeyMap<K,V> extends Map<K,V> {

    /**
     * Add a Value with Synonyms keys.
     * @param key K some of the keys
     * @param value V initial value
     * @param synonymKeys K array of synonyms
     */
    public MultiKeyMap addSynonyms(K key, V value, K... synonymKeys);

} // E:O:F:MultiKeyMap.
