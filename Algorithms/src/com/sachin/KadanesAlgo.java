package com.sachin;

/**
 * Algorithm to find maximum sum of subarray in given array
 * e.g. [5, -4, -2, 3, 3, -1] Ans "6"
 * https://www.youtube.com/watch?v=HCL4_bOd3-4&list=PLUcsbZa0qzu3yNzzAxgvSgRobdUUJvz7p&index=13
 */
public class KadanesAlgo {
    
    int maxSumSubArray(int[] a) {
        int maxSum = 0;
        int curSum = 0;

        for(int index = 0; index < a.length; index++) {
            curSum = curSum + a[index];
            if(curSum > maxSum) {
                maxSum = curSum;
            }

            if(curSum < 0) {
                curSum = 0;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] myArray = {5, -4, -2, 3, 3, -1}; // creates an array of 5 integers with the given values

        int ans = (new KadanesAlgo()).maxSumSubArray(myArray);

        System.out.println(ans);
    }
}
