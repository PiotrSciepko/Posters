package com.PiotrS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Poster {

    int numberOfPosters;

    public void stripZeros(List<Integer> list) {
        while (list.get(0) == 0) {
            list.remove(0);
        }
        while (list.get(list.size() - 1) == 0) {
            list.remove(list.size() - 1);
        }
    }

    public List<Integer> findInnerZeros(List<Integer> list) {
        List<Integer> indexOfZeros = new ArrayList<>();
        if (list.contains(0)) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == 0) {
                    indexOfZeros.add(i);
                }
            }
        }
        indexOfZeros.add(list.size());
        return indexOfZeros;
    }

    public void reduceZeros(List<Integer> list) {
        stripZeros(list);
        List<Integer> indexOfZeros = findInnerZeros(list);
        int rem = 0;
        if (indexOfZeros.size() > 1) {
            for (int i = 0; i < indexOfZeros.size() - 1; i++) {
                if (indexOfZeros.get(i+1) - indexOfZeros.get(i) == 1) {
                    list.remove(indexOfZeros.get(i)-rem);
                    rem++;
                }
            }
        }
    }

    public void addPoster(List<Integer> list) {
        int min = Collections.min(list);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) - min);
        }
        numberOfPosters++;
    }

    public void arrangePosters(List<Integer> list) {

        while (Collections.frequency(list, 0) != list.size()) {
            reduceZeros(list);
            int index = 0;
            for (int i : findInnerZeros(list)) {
                addPoster(list.subList(index, i));
                index = i + 1;
            }
        }
    }

    public static void main(String[] args) {

        List<Integer> arr = new ArrayList<>(List.of(10, 9, 10, 10, 23, 21, 20, 23, 124, 23, 20, 23, 23, 24, 23, 120, 23, 15, 12, 14, 15, 14, 13, 12, 14, 14, 13, 14, 23, 24, 28, 27, 28, 29, 30, 29, 311, 29, 23, 18, 17, 16, 15, 15, 15, 17, 10, 11, 10, 9, 8, 8, 10, 18, 19, 17, 14, 20, 14, 21));
        //List<Integer> arr = new ArrayList<>(List.of(100, 3, 0, 0, 1, 0, 0, 0, 0, 0, 0, 9));
        //List<Integer> arr = new ArrayList<>(List.of(5,0,2,4,3,0,2,3,8));

        Poster p = new Poster();
        p.arrangePosters(arr);

        System.out.println("\nNumber of posters: " + p.numberOfPosters);
    }
}
