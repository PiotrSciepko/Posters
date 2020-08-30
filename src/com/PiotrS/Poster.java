package com.PiotrS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Poster {
    private int numberOfPosters;

    public int getNumberOfPosters() {
        return numberOfPosters;
    }

    public void setNumberOfPosters() {
        this.numberOfPosters++;
    }

    private void stripZeros(List<Integer> list) {
        while (!list.isEmpty() && list.get(0) == 0) {
            list.remove(0);
        }
        while (!list.isEmpty() && list.get(list.size() - 1) == 0) {
            list.remove(list.size() - 1);
        }
    }

    private List<Integer> findZeros(List<Integer> list) {
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

    private void reduceZeros(List<Integer> list) {
        List<Integer> indexOfZeros = findZeros(list);
        int rem = 0;
        if (indexOfZeros.size() > 1) {
            for (int i = 0; i < indexOfZeros.size() - 1; i++) {
                if (indexOfZeros.get(i+1) - indexOfZeros.get(i) == 1) {
                    list.remove(indexOfZeros.get(i) - rem);
                    rem++;
                }
            }
        }
    }

    private void addPoster(List<Integer> list) {
        int min = Collections.min(list);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) - min);
        }
        setNumberOfPosters();
    }

    private void arrangePosters(List<Integer> list) {
        while (list.size() != 0) {
            reduceZeros(list);
            int index = 0;
            for (int i : findZeros(list)) {
                addPoster(list.subList(index, i));
                index = i + 1;
            }
            stripZeros(list);
        }
    }

    public static void main(String[] args) {

        List<Integer> arr = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        int limit = sc.nextInt();
        for (int i = 0; i < limit; i++) {
            sc.nextInt();
            arr.add(sc.nextInt());
        }
        //example input data:
        //List<Integer> arr = new ArrayList<>(List.of(10, 9, 10, 10, 23, 21, 20, 23, 124, 23, 20, 23, 23, 24, 23, 120, 23, 15, 12, 14, 15, 14, 13, 12, 14, 14, 13, 14, 23, 24, 28, 27, 28, 29, 30, 29, 311, 29, 23, 18, 17, 16, 15, 15, 15, 17, 10, 11, 10, 9, 8, 8, 10, 18, 19, 17, 14, 20, 14, 21));
        //List<Integer> arr = new ArrayList<>(List.of(0,0,100, 3, 0, 0, 1, 0, 0, 0, 0, 0, 0, 9,0,0,0));
        //List<Integer> arr = new ArrayList<>(List.of(5,2,4,3,2,3,8));

        Poster p = new Poster();
        p.stripZeros(arr);
        long start = System.nanoTime();
        p.arrangePosters(arr);

        //extended runtime messages (comment out "p.arrangePosters(arr)" above):
        /*
        while (arr.size() != 0) {
            System.out.println("\noriginal " + arr);
            System.out.println("posters before: " + p.numberOfPosters);
            System.out.println("findZeros " + p.findZeros(arr));
            p.reduceZeros(arr);
            System.out.println("reduceZeros " + arr);
            System.out.println("findZeros " + p.findZeros(arr));
            int index = 0;
            for (int i : p.findZeros(arr)) {
                p.addPoster(arr.subList(index, i));
                System.out.println("subList_" + i + " " + arr.subList(index, i));
                index = i + 1;
            }
            p.stripZeros(arr);
            System.out.println(arr);
            System.out.println("posters after: " + p.numberOfPosters);
        }
        */

        long stop = System.nanoTime();
        System.out.println("\nNumber of posters: " + p.getNumberOfPosters());
        System.out.println("\nTime elapsed: " + (stop - start) / 1000000. + " ms\n");
    }
}
