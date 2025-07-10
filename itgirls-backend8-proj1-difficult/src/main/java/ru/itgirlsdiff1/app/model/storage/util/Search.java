package ru.itgirlsdiff1.app.model.storage.util;

import java.util.List;
import java.util.function.BiFunction;

public class Search {

    public <Q, T> int searchBinary(List<T> list, int startIndx, int endIndx, Q query, BiFunction<Q, T, Integer> comparator) {
        while (endIndx >= startIndx) {
            int middleIndx = startIndx + (endIndx - startIndx) / 2;
            int result = comparator.apply(query, list.get(middleIndx));
            if (result == 0) {
                return middleIndx;
            } else if (result < 0) {
                endIndx = middleIndx - 1;
            } else {
                startIndx = middleIndx + 1;
            }
        }
        return -1;
    }
}
