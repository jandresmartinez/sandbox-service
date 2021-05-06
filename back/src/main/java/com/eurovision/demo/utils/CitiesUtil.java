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
        List<List<Integer>> LIS = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            LIS.add(new ArrayList<>());
        }

        // `LIS[0]` denotes the longest increasing subsequence ending at `arr[0]`
        LIS.get(0).add(arr[0]);

        // start from the second array element
        for (int i = 1; i < arr.length; i++)
        {
            // do for each element in subarray `arr[0…i-1]`
            for (int j = 0; j < i; j++)
            {
                // find the longest increasing subsequence that ends with `arr[j]`
                // where `arr[j]` is less than the current element `arr[i]`

                if (arr[j] < arr[i] && LIS.get(j).size() > LIS.get(i).size()) {
                    LIS.set(i, new ArrayList<>(LIS.get(j)));
                }
            }

            // include `arr[i]` in `LIS[i]`
            LIS.get(i).add(arr[i]);
        }

        int j = 0;
        for (int i = 0; i < arr.length; i++)
        {
            if (LIS.get(j).size() < LIS.get(i).size()) {
                j = i;
            }
        }


        return LIS.get(j).stream().map(n -> String.valueOf(n)).collect(Collectors.joining( " "));

    }



}
