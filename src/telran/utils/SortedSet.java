package telran.utils;

public interface SortedSet<T> extends Set<T> {
	T first();

	T last();
}