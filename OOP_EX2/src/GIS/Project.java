package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
/**
 * This class represent a project.
 * a project is an array list of layers.
 * usually a project will consistent of few gps data files.
 * @author Alex vaisman, shay naor
 *
 */
public class Project implements GIS_project {
	private ArrayList<GIS_layer> project;

	public Project() {
		this.project = new ArrayList<GIS_layer>();
	}

	public boolean add(GIS_layer e) {

		return this.project.add(e);
	}

	public boolean addAll(Collection<? extends GIS_layer> c) {

		return this.project.addAll(c);
	}

	public void clear() {
		this.project.clear();

	}

	public boolean contains(Object o) {
		return this.project.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return this.project.containsAll(c);
	}

	public boolean isEmpty() {
		return this.project.isEmpty();
	}

	public Iterator<GIS_layer> iterator() {
		return this.project.iterator();
	}

	public boolean remove(Object o) {
		return this.project.remove(o);
	}

	public boolean removeAll(Collection<?> c) {

		return this.project.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {

		return this.project.retainAll(c);
	}

	public int size() {
		return this.project.size();
	}

	public Object[] toArray() {
		return this.project.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return this.project.toArray(a);
	}

	public Meta_data get_Meta_data() {
		return this.project.get(0).get_Meta_data();
	}

}
