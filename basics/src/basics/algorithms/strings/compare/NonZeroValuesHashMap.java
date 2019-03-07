package basics.algorithms.strings.compare;

import java.util.HashMap;

/**
 * The NonZeroValuesMap class does not accept a zero value for any key.
 * 
 * @author Carlos de la Fuente Antequera
 *
 * @param <K>
 *            extends <code>Character</code> as type of key for this map
 * @param <V>
 *            extends <code>Integer</code> as type of value for this map
 */
@SuppressWarnings("serial")
public final class NonZeroValuesHashMap<K extends Character, V extends Integer> extends HashMap<K, V> {

	@Override
	public V put(K key, V value) {
		if ((get(key).intValue() + value) == 0) {
			return remove(key);
		}
		return super.put(key, value);
	}

	public Integer get(K key) {
		return (super.containsKey(key)) ? super.get(key).intValue() : 0;
	}
}
