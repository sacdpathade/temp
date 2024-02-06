package com.sachin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

class Node {
    public int data;
    public Node left;
    public Node right;

    Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data + "";
    }

    
}

public class BinaryTreeUtil {
    static Node root = null;
    static Scanner scanner = new Scanner(System.in);

    public Node createTree() {
        System.out.println("Enter data");
        int data = scanner.nextInt();
        if (data == -1) {
            return null;
        }

        Node node = new Node(data);

        System.out.println("Enter left of " + data);
        node.left = createTree();

        System.out.println("Enter right of " + data);
        node.right = createTree();

        return node;
    }

    public void inorderTraversal(Node node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        System.out.print(node.data + ",");
        inorderTraversal(node.right);
    }

    public void preorderTraversal(Node node) {
        if (node == null) {
            return;
        }
        preorderTraversal(node.left);
        preorderTraversal(node.right);
        System.out.print(node.data + ",");
    }

    public void postorderTraversal(Node node) {
        if (node == null) {
            return;
        }
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.print(node.data + ",");
    }

    public void add(int value) {
        // If the root is null, the tree is empty
        if (root == null) {
            // Create a new node with the given value and assign it to the root
            root = new Node(value);
        } else {
            // Otherwise, call the recursive helper method to insert the new node
            addRecursive(root, value);
        }
    }

    // A helper method to insert a new node to the subtree rooted at the given node
    private void addRecursive(Node node, int value) {
        // If the value is less than the node's value, go to the left subtree
        if (value < node.data) {
            // If the left child is null, create a new node and assign it to the left child
            if (node.left == null) {
                node.left = new Node(value);
            } else {
                // Otherwise, recursively insert the new node to the left subtree
                addRecursive(node.left, value);
            }
            // If the value is greater than the node's value, go to the right subtree
        } else if (value > node.data) {
            // If the right child is null, create a new node and assign it to the right
            // child
            if (node.right == null) {
                node.right = new Node(value);
            } else {
                // Otherwise, recursively insert the new node to the right subtree
                addRecursive(node.right, value);
            }
            // If the value is equal to the node's value, do nothing (no duplicates allowed)
        } else {
            return;
        }
    }


    public int heightOfTree(Node node)
    {
        if(node == null) {
            return 0;
        }
        return (Math.max(heightOfTree(node.left), heightOfTree(node.right))) + 1;
    }

    public int sizeOfTree(Node node)
    {
        if(node == null) {
            return 0;
        }
        return sizeOfTree(node.left) + sizeOfTree(node.right) + 1;
    }

    public int maxInTree(Node node)
    {
        if(node == null) {
            return Integer.MIN_VALUE;
        }
        return Math.max(node.data, Math.max(maxInTree(node.left) ,maxInTree(node.right)));
    }

    public int minInTree(Node node)
    {
        if(node == null) {
            return Integer.MAX_VALUE;
        }
        return Math.min(node.data, Math.min(minInTree(node.left) ,minInTree(node.right)));
    }

    public void levelOrderTraversal(Node node) {

        if(root == null) {
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        while(!q.isEmpty()) {
            Node cn = q.poll();
            System.out.print(cn.data + ",");
            if(cn.left != null) {
                q.add(cn.left);
            }
            if(cn.right != null) {
                q.add(cn.right);
            }
        }
    }

    

    public void leftViewUtil(Node node, List<Integer> list, int level) {
        // Base case: if the node is null, return
        if (node == null) {
            return;
        }

        // If the level is greater than the maxLevel, print the node and update the maxLevel
        // if (level > maxLevel) {
        if(list.size() < level) {
            list.add(node.data);
            // System.out.print(node.data + " ");
            // maxLevel = level;
        }

        // Recursively print the left view of the left subtree
        leftViewUtil(node.left, list, level + 1);

        // Recursively print the left view of the right subtree
        leftViewUtil(node.right, list, level + 1);
    }

    // int maxLevel = 0;
    public void leftView(Node node) {
        // maxLevel = 0;
        List<Integer> list = new ArrayList<>();
        leftViewUtil(node, list, 1);
        System.out.println(list);
    }

    // A method to print the right view of the tree using queue
    public void printRightView(Node node) {
        // If the root is null, return
        if (node == null) {
            return;
        }

        // Create a queue and add the root to it
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        // While the queue is not empty
        while (!queue.isEmpty()) {
            // Get the size of the current level
            int n = queue.size();

            // Iterate through the nodes of the current level
            while (n-- > 0) {
                // Remove the front node from the queue
                Node x = queue.poll();

                // If this is the last node of the current level, print it
                if (n == 0) {
                    System.out.print(x.data + " ");
                }

                // Add the left and right child of the node to the queue, if any
                if (x.left != null) {
                    queue.add(x.left);
                }
                if (x.right != null) {
                    queue.add(x.right);
                }
            }
        }
    }

    static class Pair {
        Node node;
        int distance;

        public Pair(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        public Node getKey() {
            return node;
        }

        public int getValue() {
            return distance;
        }
    }

    // A method to print the top view of the tree using map and queue
    public void printTopView(Node rootNode) {
        // If the root is null, return
        if (rootNode == null) {
            return;
        }

        // Create a map to store the horizontal distance and the node value of each node
        Map<Integer, Integer> map = new TreeMap<>();

        // Create a queue to store the node and its horizontal distance
        Queue<Pair> queue = new LinkedList<>();

        // Add the root and its horizontal distance (0) to the queue
        queue.add(new Pair(rootNode, 0));

        // While the queue is not empty
        while (!queue.isEmpty()) {
            // Remove the front element of the queue
            Pair p = queue.poll();

            // Get the node and its horizontal distance
            Node node = p.getKey();
            int hd = p.getValue();

            // If the map does not contain the horizontal distance, add it with the node value
            if (!map.containsKey(hd)) {
                map.put(hd, node.data);
            }

            // Add the left child and its horizontal distance (hd - 1) to the queue, if any
            if (node.left != null) {
                queue.add(new Pair(node.left, hd - 1));
            }

            // Add the right child and its horizontal distance (hd + 1) to the queue, if any
            if (node.right != null) {
                queue.add(new Pair(node.right, hd + 1));
            }
        }

        // Print the values in the map
        for (int value : map.values()) {
            System.out.print(value + " ");
        }
    }

    Node head = null;
    Node prev = null;
    public void convertToDll(Node node) {
        if(node == null)
            return;
        convertToDll(node.left);
        if(prev == null) {
            head = node;
        } else {
            node.left = prev;
            prev.right = node;
        }
        prev = node;
        convertToDll(node.right);
    }

    public static void printDll(Node head) {
        Node node = head;
        while(node != null) {
            System.out.print(node.data + " ");
            node = node.right;
        }
    }

    public int diameter(Node node) {
        if(node == null) return 0;
        int d1 = diameter(node.left);
        int d2 = diameter(node.right);
        int cur = heightOfTree(node.left) + heightOfTree(node.right) + 1;
        return Math.max(cur, Math.max(d1, d2));
    }

    public Node lca(Node node, int n1, int n2) {
        if(node == null) {
            return null;
        }

        if(node.data == n1 || node.data == n2) {
            return node;
        }

        Node left = lca(node.left, n1, n2);
        Node right = lca(node.right, n1, n2);

        if(left == null) {
            return right;
        }
        if(right == null) {
            return left;
        }

        return node;
    }





    public static void main(String[] args) {
        BinaryTreeUtil binaryTreeUtil = new BinaryTreeUtil();
        // root = binaryTreeUtil.createTree();

        //      10
        //     / \
        //    5   15
        //   / \   \
        //  4   8   20
        //     /   /  \ 
        //    6   16    22
        //         \   /
        //         18 21

        binaryTreeUtil.add(10);
        binaryTreeUtil.add(5);
        binaryTreeUtil.add(4);
        binaryTreeUtil.add(8);
        binaryTreeUtil.add(6);
        binaryTreeUtil.add(15);
        binaryTreeUtil.add(20);
        binaryTreeUtil.add(16);
        binaryTreeUtil.add(18);
        binaryTreeUtil.add(22);
        binaryTreeUtil.add(21);


        System.out.println();
        System.out.println("Inorder traversal");
        binaryTreeUtil.inorderTraversal(root);

        System.out.println();
        System.out.println("Preorder traversal");
        binaryTreeUtil.preorderTraversal(root);

        System.out.println();
        System.out.println("Postorder traversal");
        binaryTreeUtil.postorderTraversal(root);

        System.out.println();
        System.out.println("Level order traversal");
        binaryTreeUtil.levelOrderTraversal(root);

        System.out.println();
        System.out.println("Height of tree : " + binaryTreeUtil.heightOfTree(root));

        System.out.println("Size of tree : " + binaryTreeUtil.sizeOfTree(root));

        System.out.println("Max in tree : " + binaryTreeUtil.maxInTree(root));

        System.out.println("Min in tree : " + binaryTreeUtil.minInTree(root));

        System.out.println();
        System.out.println("Left view of tree");
        binaryTreeUtil.leftView(root);

        System.out.println();
        System.out.println("Right view of tree");
        binaryTreeUtil.printRightView(root);

        System.out.println();
        System.out.println("Top view of tree");
        binaryTreeUtil.printTopView(root);

        // System.out.println();
        // System.out.println("Convert to DLL");
        // binaryTreeUtil.convertToDll(root);
        // printDll(binaryTreeUtil.head);

        System.out.println();
        System.out.println("Diameter of tree : " + binaryTreeUtil.diameter(root));

        System.out.println("LCA of 18, 22 : " + binaryTreeUtil.lca(root, 18, 22));
    }
}
