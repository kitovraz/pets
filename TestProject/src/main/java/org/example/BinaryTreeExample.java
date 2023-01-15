package org.example;

import org.apache.commons.lang3.time.StopWatch;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class BinaryTreeExample {
    public static void main(String[] args) {
        TreeNode<Integer> six = new TreeNode<>(6, null, null);
        TreeNode<Integer> four = new TreeNode<>(4, null, null);
        TreeNode<Integer> five = new TreeNode<>(5, six, four);
        TreeNode<Integer> seven = new TreeNode<>(7, null, null);
        TreeNode<Integer> root2 = new TreeNode<>(2, seven, five);

        List<Integer> res = new ArrayList<>();
        long start = System.nanoTime();
        System.out.println(TreeNode.getValuesRecursion_searchDepth(root2, res));
        System.err.println(System.nanoTime() - start);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println(TreeNode.getValuesByStack_searchWidth(root2));
        stopWatch.stop();
        System.err.println(stopWatch.getTime(TimeUnit.NANOSECONDS));
    }
}

class TreeNode<T> {
    private T value;
    private TreeNode<T> left;
    private TreeNode<T> rihgt;

    public TreeNode(T value, TreeNode<T> left, TreeNode<T> rihgt) {
        this.value = value;
        this.left = left;
        this.rihgt = rihgt;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRihgt() {
        return rihgt;
    }

    public void setRihgt(TreeNode<T> rihgt) {
        this.rihgt = rihgt;
    }

    public static <T> List<T> getValuesRecursion_searchDepth(TreeNode<T> root, List<T> res) {
        TreeNode<T> current = root;

        if (current != null) {
            if (current.getValue() != null) {
                res.add(current.getValue());
            }
            getValuesRecursion_searchDepth(current.getLeft(), res);
            getValuesRecursion_searchDepth(current.getRihgt(), res);
        }

        return res;
    }

    public static <T> List<T> getValuesByStack_searchWidth(TreeNode<T> root) {
        Deque<TreeNode<T>> deque = new ArrayDeque<>();
        List<T> res = new ArrayList<>();
        TreeNode<T> current = root;

        deque.add(current);

        while (!deque.isEmpty()) {
            TreeNode<T> node = deque.poll();
            if (node != null){
                res.add(node.getValue());
            }

            if (node.getLeft() != null) {
                deque.push(node.getLeft());
            }
            if (node.getRihgt() != null) {
                deque.push(node.getRihgt());
            }
        }

        return res;
    }
}
