package com.sachin;



public class SortingAlgos {

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    public static void printArray(int[] a) {
        for(int i=0; i<a.length; i++) {
            System.out.print( a[i] + " ");
        }
        System.out.println();
    }

    /**
     * https://www.youtube.com/watch?v=bBQkErahU9c&list=PLUcsbZa0qzu3yNzzAxgvSgRobdUUJvz7p&index=17
     * @param a
     */
    public void bubbleSort(int[] a) {
        int n = a.length;
        for(int i=0; i < n; i++) {
            boolean swapDone = false;
            for(int j=0;  j < n - i - 1; j++) {
                if(a[j] > a[j+1]) {
                    swapDone = true;
                    swap(a, j, j+1);
                }
            }
            if(!swapDone) {
                break;
            }
        }
        printArray(a);
    }

    /**
     * https://www.youtube.com/watch?v=wWhAhp6PIuQ&list=PLUcsbZa0qzu3yNzzAxgvSgRobdUUJvz7p&index=18
     * @param a
     */
    public void insertionSort(int[] a) {
        int n = a.length;
        for(int i=1; i < n; i++) {
            int temp = a[i];
            int j = i - 1;
            while(j>=0 && a[j] > temp) {
                a[j+1] = a[j];
                j--;
            }
            a[j+1] = temp;
        }
        printArray(a);
    }

    /**
     * https://www.youtube.com/watch?v=B-nqY6IYqVw&list=PLUcsbZa0qzu3yNzzAxgvSgRobdUUJvz7p&index=19
     * @param a
     */
    public void selectionSort(int[] a) {
        int n = a.length;
        for(int i=0; i < n; i++) {
            int min = i;
            for(int j=i+1; j<n; j++) {
                if(a[j] < a[min]) {
                    min = j;
                }
            }
            if(min != i) {
                swap(a, i, min);
            }
        }
        printArray(a);
    }

    int partition(int[] a, int l, int h) {
        int pivot = a[l];
        int i = l;
        int j = h;
        while(i < j) {
            while(a[i] <= pivot) i++;
            while(a[j] > pivot) j--;
            if( i < j) {
                swap(a, i, j);
            }
            swap(a, l, j);
        }
        return j;
    }

    public void quickSort(int[] a, int l, int h) {
        if(l < h) {
            int pivot = partition(a, l, h);
            quickSort(a, l, pivot - 1);
            quickSort(a, pivot + 1, h);
        }
    }

    /**
     * https://www.youtube.com/watch?v=UA_Rmjfj4bw&list=PLUcsbZa0qzu3yNzzAxgvSgRobdUUJvz7p&index=20
     * @param a
     */
    public void quickSort(int[] a) {        
        quickSort(a, 0, a.length - 1);
        printArray(a);
    }

    public void merge(int[] a, int l, int mid, int r) {
        int i=l;
        int j=mid+1;
        int k=l;

        int[] b= new int[a.length];
        while(i<=mid && j<=r) {
            if(a[i] < a[j]) {
                b[k] = a[i];
                i++;
            } else {
                b[k] = a[j];
                j++;
            }
            k++;
        }

        if(i > mid) {
            while(j <= r) {
                b[k]=a[j];
                k++;
                j++;
            }
        } else {
            while(i <= mid) {
                b[k]=a[i];
                k++;
                i++;
            }
        }
        for(k=l; k<=r; k++) {
            a[k] = b[k];
        }
    }

    public void mergeSort(int[] a, int l, int r) {
        if(l<r) {
            int mid = (l+r) / 2;
            mergeSort(a, l, mid);
            // mergeSort(a, mid+1, r);
            merge(a, l, mid, r);
        }
    }

    /**
     * https://www.youtube.com/watch?v=aDX3MFL0tYs&list=PLUcsbZa0qzu3yNzzAxgvSgRobdUUJvz7p&index=21
     * @param a
     */
    public void mergeSort(int[] a) {
        quickSort(a, 0, a.length - 1);
        printArray(a);
    }

    public static void main(String[] args) {
        int[] a = {5,2,6,1,4,7,3};

        SortingAlgos algos = new SortingAlgos();

        algos.bubbleSort(a.clone());

        algos.insertionSort(a.clone());

        algos.selectionSort(a.clone());

        // algos.quickSort(a.clone());

        // algos.mergeSort(a.clone());


    }    
}
