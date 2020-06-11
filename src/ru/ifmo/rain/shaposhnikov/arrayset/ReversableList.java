package ru.ifmo.rain.shaposhnikov.arrayset;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

class ReversableList<E> extends AbstractList<E> implements RandomAccess {

    private final List<E> data;
    private boolean reversed;

    public ReversableList(List<E> list) {
        if (list instanceof ReversableList) {
            ReversableList<E> reverableList = (ReversableList<E>) list;
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
