package org.example;

import lombok.Data;

public class LinkedListExample {
    public static void main(String[] args) {
        Node node3 = new Node(3, null);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);

        node1.print();

        Node node4 = new Node(4, null);

        node1.add(node4);

        node1.print();

        Node reverseRootNode = node1.reverse();

        reverseRootNode.print();
        node1.print();
    }

    @Data
    public static class Node {
        private int value;
        private Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(Node node) {
            this.value = node.value;
            this.next = node.next;
        }

        public void print() {
            Node head = this;

            while (head != null) {
                System.out.print(head.value + " ");
                head = head.next;
            }
            System.out.println();
        }

        public void add(Node node) {
            Node head = this;

            while (true) {
                if (head.next == null) {
                    head.next = node;
                    break;
                }
                head = head.next;
            }
        }

        public Node reverse() {
            Node head = this;
            Node current = null;
            Node previous = null;

            while (head != null) {
                current = new Node(head);

                current.next = previous;

                previous = current;

                head = head.next;
            }

            return current;
        }
    }
}