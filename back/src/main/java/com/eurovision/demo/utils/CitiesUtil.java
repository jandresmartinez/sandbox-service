package com.eurovision.demo.utils;


import com.eurovision.demo.domain.Cities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public  class CitiesUtil {

    private CitiesUtil() {
    }

    public static String getLongestSequence(List<Cities> cities){
        int[] cityIdArr = new int [cities.size()];

        AtomicInteger i= new AtomicInteger();
        cities.stream().forEach((c)->{
            cityIdArr[i.get()]= Math.toIntExact(c.getId());
            i.getAndIncrement();
        });
        return getLongestSequence(cityIdArr);
    }

    public static String getLongestSequence(int[] arr){
        List<List<Integer>> integerList = new ArrayList<>();

        /**
         * Creating init Object
         */
        for (int i = 0; i < arr.length; i++) {
            integerList.add(new ArrayList<>());
        }

        integerList.get(0).add(arr[0]);

        for (int i = 1; i < arr.length; i++)
        {

            for (int j = 0; j < i; j++)
            {

                if (arr[j] < arr[i] && integerList.get(j).size() > integerList.get(i).size()) {
                    integerList.set(i, new ArrayList<>(integerList.get(j)));
                }
            }


            integerList.get(i).add(arr[i]);
        }

        int j = 0;
        for (int i = 0; i < arr.length; i++)
        {
            if (integerList.get(j).size() < integerList.get(i).size()) {
                j = i;
            }
        }


        return integerList.get(j).stream().map(n -> String.valueOf(n)).collect(Collectors.joining( " "));

    }





}
