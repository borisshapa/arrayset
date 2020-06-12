package ru.ifmo.rain.shaposhnikov.arrayset;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/**
 * {@link List} that can be expanded in <var>O(1)</var>, while not allocating additional memory.
 * Implements {@link RandomAccess} interface.
 *
 * @param <E> type of data stored
 */
class ReversibleList<E> extends AbstractList<E> implements RandomAccess {

    private final List<E> data;
    private final boolean reversed;

    /**
     * Constructs a new reversible list containing the elements in the specified {@link List}
     *
     * @param list on the basis of which to build a list
     */
    public ReversibleList(List<E> list) {
        if (list instanceof ReversibleList) {
            ReversibleList<E> reverableList = (ReversibleList<E>) list;
            this.data = reverableList.data;
            this.reversed = !reverableList.reversed;
        } else {
            this.data = list;
            this.reversed = true;
        }
    }

    @Override
    public E get(int i) {
        return data.get(reversed ? data.size() - i - 1 : i);
    }

    @Override
    public int size() {
        return data.size();
    }
}
