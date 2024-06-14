package telran.utils;

public interface SortedSet<T> extends Set<T> {
	T first();
	T last();
	T floor(T key);
	T ceilling(T key);
}