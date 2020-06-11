package ru.ifmo.rain.shaposhnikov.arrayset;

import java.util.*;

public class ArraySet<E> extends AbstractSet<E> implements NavigableSet<E> {

    private final List<E> data;
    private final Comparator<? super E> comparator;

    public ArraySet() {
        data = Collections.emptyList();
        comparator = null;
    }

    private ArraySet(List<E> list, Comparator<? super E> comparator) {
        this.data = list;
        this.comparator = getComparatorOrNull(comparator);
    }

    public ArraySet(Collection<? extends E> collection) {
        this(collection, null);
    }

    public ArraySet(Comparator<? super E> comparator) {
        this(Collections.emptyList(), comparator);
    }

    public ArraySet(Collection<? extends E> collection, Comparator<? super E> comparator) {
        TreeSet<E> tmpTreeSet = new TreeSet<>(comparator);
        tmpTreeSet.addAll(collection);
        data = getUnmodifiableList(tmpTreeSet);
        this.comparator = getComparatorOrNull(comparator);
    }

    private List<E> getUnmodifiableList(Collection<E> collection) {
        return List.copyOf(collection);
    }

    private Comparator<? super E> getComparatorOrNull(Comparator<? super E> comparator) {
        return (comparator == Comparator.naturalOrder()) ? null : comparator;
    }

    private int binarySearch(E element) {
        return Collections.binarySearch(data, element, comparator);
    }

    private int lowerBound(E element, boolean inclusive) {
        int index = binarySearch(element);
        return index < 0
                ? -(index + 1) - 1
                : (inclusive ? index : index - 1);
    }

    private int upperBound(E element, boolean inclusive) {
        int index = binarySearch(element);
        return index < 0
                ? -(index + 1)
                : (inclusive ? index : index + 1);
    }

    private boolean correctIndex(int index) {
        return 0 <= index && index < data.size();
    }

    private E getElement(int index) {
        return correctIndex(index) ? data.get(index) : null;
    }

    @Override
    public E lower(E e) {
        return getElement(lowerBound(e, false));
    }

    @Override
    public E floor(E e) {
        return getElement(lowerBound(e, true));
    }

    @Override
    public E ceiling(E e) {
        return getElement(upperBound(e, true));
    }

    @Override
    public E higher(E e) {
        return getElement(upperBound(e, false));
    }

    @Override
    public E pollFirst() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E pollLast() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        return data.iterator();
    }

    @Override
    public NavigableSet<E> descendingSet() {
        return new ArraySet<>(new ReversableList<>(data), Collections.reverseOrder(comparator));
    }

    @Override
    public Iterator<E> descendingIterator() {
        return descendingSet().iterator();
    }

    private NavigableSet<E> emptySet() {
        return isEmpty() ? this : new ArraySet<>(comparator);
    }

    @Override
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        if (fromElement == null || toElement == null) {
            throw new NullPointerException();
        }

        int l = upperBound(fromElement, fromInclusive);
        int r = lowerBound(toElement, toInclusive);
        if (l > r) {
            return emptySet();
        }
        return new ArraySet<>(data.subList(l, r + 1), comparator);
    }

    @Override
    public NavigableSet<E> headSet(E toElement, boolean toInclusive) {
        return data.isEmpty()
                ? this
                : subSet(first(), true, toElement, toInclusive);
    }

    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean fromInclusive) {
        return data.isEmpty()
                ? this
                : subSet(fromElement, fromInclusive, last(), true);
    }

    @Override
    public Comparator<? super E> comparator() {
        return comparator;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SortedSet<E> subSet(E fromElement, E toElement) {
        if (comparator == null
                ? ((Comparable<? super E>) fromElement).compareTo(toElement) > 0
                : comparator.compare(fromElement, toElement) > 0) {
            throw new IllegalArgumentException();
        }

        return subSet(fromElement, true, toElement, false);
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return headSet(toElement, false);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return tailSet(fromElement, true);
    }

    private E checkEmptyAndGet(int index) {
        if (data.isEmpty()) {
            throw new NoSuchElementException();
        }
        return data.get(index);
    }

    @Override
    public E first() {
        return checkEmptyAndGet(0);
    }

    @Override
    public E last() {
        return checkEmptyAndGet(data.size() - 1);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object object) {
        return binarySearch((E) Objects.requireNonNull(object)) >= 0;
    }
}
