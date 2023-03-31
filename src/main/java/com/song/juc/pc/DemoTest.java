package com.song.juc.pc;

import java.util.Scanner;

public class DemoTest {

    private static Info solve(Node root) {
        if (root == null) {
            return new Info(null, 0, 0);
        }
        Info left = solve(root.left);
        Info right = solve(root.right);
        int deep = Math.max(left.deep, right.deep) + 1;
        int distance = left.deep + right.deep;
        Info ret = new Info(root, distance, deep);
        if (ret.distance < left.distance) {
            ret.distance = left.distance;
            ret.node = left.node;
        }
        if (ret.distance < right.distance) {
            ret.distance = right.distance;
            ret.node = right.node;
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            Node[] nodes = new Node[n + 1];
            for (int i = 0; i <= n; ++i) {
                nodes[i] = new Node(i);
            }
            Node root = nodes[in.nextInt()];
            for (int i = 0; i <= n; ++i) {
                int fa = in.nextInt();
                nodes[fa].left = nodes[in.nextInt()];
                nodes[fa].right = nodes[in.nextInt()];
            }
            Info ret = solve(root);
            System.out.println(ret.distance);
        }
    }
}

class Info {
    Node node;
    int distance;
    int deep;

    Info(Node node, int distance, int deep) {
        this.node = node;
        this.distance = distance;
        this.deep = deep;
    }

}

class Node {
    Node left;
    Node right;
    int val;

    Node(int val) {
        this.val = val;
    }
}