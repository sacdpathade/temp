package com.sachin;

/**
 * Find Buy/Sell dates for maximum profit
 * 
 * e.g. [3,1,4,8,7,2,5] And : 7 
 */
public class StocksBuyAndSell {

    public int singleProfit(int[] a) {
        int minSoFar = a[0];
        int maxProfit = 0;
        for(int i =0; i < a.length; i++) {
            if(a[i] < minSoFar) {
                minSoFar = a[i];
            }
            int profit = a[i] - minSoFar;
            if(profit > maxProfit) {
                maxProfit = profit;
            }
        }

        return maxProfit;
    }


    public int multiProfit(int[] a) {
        int profit = 0;
        for(int i =1; i < a.length; i++) {
            if(a[i] > a[i-1]) {
                profit = profit + (a[i] - a[i-1]);
            }
        }

        return profit;
    }

    public static void main(String[] args) {
        int[] a = {5,2,6,1,4,7,3,6};

        StocksBuyAndSell sbs = new StocksBuyAndSell();

        System.out.println("Max Profit " + sbs.singleProfit(a));

        System.out.println("Multi trade Profit " + sbs.multiProfit(a));
    }
}
