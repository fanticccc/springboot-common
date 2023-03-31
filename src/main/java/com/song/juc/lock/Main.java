package com.song.juc.lock;

import java.util.HashMap;
import java.util.Scanner;

class Node {
    Node left;
    Node right;
}

class Info {
    int height = 0;
    int maxDistance = 0;
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String firstLine = sc.nextLine();
        String[] s = firstLine.split(" ");
        int n = Integer.valueOf(s[0]);
        int rootVal = Integer.valueOf(s[1]);
        HashMap<Integer, Node> map = new HashMap<>();
        Node root = new Node();
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            String[] str = line.split(" ");
            int faVal = Integer.valueOf(str[0]);
            int lchVal = Integer.valueOf(str[1]);
            int rchVal = Integer.valueOf(str[2]);
            Node curNode = map.get(faVal);
            if (lchVal != 0) {
                Node lch = new Node();
                curNode.left = lch;
                map.put(lchVal, lch);
            }
            if (rchVal != 0) {
                Node rch = new Node();
                curNode.right = rch;
                map.put(rchVal, rch);
            }
        }
        Info info = process(root);

    }

    public static Info process(Node N) {
        Info curInfo = new Info();
        if (N == null) {
            return curInfo;
        }
        Info leftInfo = process(N.left);
        Info rightInfo = process(N.right);
        int dis1 = Math.max(leftInfo.maxDistance, rightInfo.maxDistance);
        int dis2 = leftInfo.height + rightInfo.height + 1;
        curInfo.maxDistance = Math.max(dis1, dis2);
        curInfo.height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return curInfo;
    }
}
