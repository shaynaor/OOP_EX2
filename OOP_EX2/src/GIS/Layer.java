package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
/**
 * this class represents a layer.
 * layer is an array list of elements. 
 * usually a layer will be made from one gps data file.
 * @author A Beast
 *
 */
public class Layer implements GIS_layer {
    private ArrayList<GIS_element> layer;
    
	
	public Layer() {
		this.layer = new ArrayList<GIS_element>();
	}
    
	public boolean add(GIS_element arg0) {
		return this.layer.add(arg0);
	}

	public boolean addAll(Collection<? extends GIS_element> arg0) {
		return this.layer.addAll(arg0);
	}

	public void clear() {
		this.layer.clear();
	}

	public boolean contains(Object arg0) {
		return this.layer.contains(arg0);
	}

	public boolean containsAll(Collection<?> arg0) {
		return this.layer.containsAll(arg0);
	}

	public boolean isEmpty() {
		return this.layer.isEmpty();
	}

	public Iterator<GIS_element> iterator() {
		return this.layer.iterator();
	}

	public boolean remove(Object arg0) {
		return this.layer.remove(arg0);
	}

	public boolean removeAll(Collection<?> arg0) {
		return this.layer.removeAll(arg0);
	}

	public boolean retainAll(Collection<?> arg0) {
		return this.layer.retainAll(arg0);
	}

	public int size() {
		return this.layer.size();
	}

	public Object[] toArray() {
		return this.layer.toArray();
	}

	public <T> T[] toArray(T[] arg0) {
		return this.layer.toArray(arg0);
	}

	public Meta_data get_Meta_data() {
		return this.layer.get(0).getData();
	}


}
