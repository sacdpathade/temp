package com.sachin;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

/**
 * Salesforce
 */
class Result {
    public static boolean debug = true;

    // static class Node {
    //     public Node left;
    //     public Node right;
    //     public boolean isFinalNode = false;
    // }
    // public static Node buildTree(List<List<Integer>> warehouse, int rowIndex, int colIndex) {
    //     if(warehouse.get(rowIndex).get(colIndex) == 0) {
    //         return null;
    //     }
    //     Node node = new Node();
    //     //If last node, then mark it as last one.
    //     if((warehouse.size()==rowIndex+1) && (warehouse.get(0).size()==colIndex+1)) {
    //         node.isFinalNode = true;
    //         return node;
    //     }
    //     if(warehouse.size() > rowIndex+1) {
    //         node.left = buildTree(warehouse, rowIndex + 1, colIndex);
    //     }
    //     if(warehouse.get(0).size() > colIndex+1) {
    //         node.right = buildTree(warehouse, rowIndex, colIndex + 1);
    //     }
    //     return node;
    // }
    // public static int countLeafNodes(Node node) {
    //     //If leaf and final node
    //     if(node.left == null && node.right == null && node.isFinalNode) {
    //         return 1;
    //     }
    //     int count = 0;
    //     if(node.left != null) {
    //         count += countLeafNodes(node.left);
    //     }
    //     if(node.right != null) {
    //         count += countLeafNodes(node.right);
    //     }
    //     return count;
    // }

    public static int countPaths(List<List<Integer>> warehouse, int rowIndex, int colIndex) {
        //If current node is 0, then no path available from current node.
        if(warehouse.get(rowIndex).get(colIndex) == 0) {
            return 0;
        }

        //If last node, then count it as valid path.
        if((warehouse.size()==rowIndex+1) && (warehouse.get(0).size()==colIndex+1)) {
            return 1;
        }

        int count = 0;

        //If more rows available, check valid path from below node.
        if((warehouse.size() > rowIndex+1)) {
            count += countPaths(warehouse, rowIndex+1, colIndex);
        }

        //If more rows available, check valid path from below node.
        if(warehouse.get(0).size() > (colIndex+1)) {
            count += countPaths(warehouse, rowIndex, colIndex + 1);
        }        
        return count;
        
    }
    
    public static int numPathsTest(List<List<Integer>> warehouse) {        
        return countPaths(warehouse, 0 , 0);
    }
}


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int warehouseRows = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> warehouse = new LinkedList<>();

        IntStream.range(0, warehouseRows).forEach(i -> {
            try {
                warehouse.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.numPathsTest(warehouse);

       System.out.println("Paths => " + result);
    }
}
