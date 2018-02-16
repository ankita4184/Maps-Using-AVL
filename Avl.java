package com.company;
import java.io.*;
import java.lang.*;
import java.util.*;
public class Avl {
    int key;
    Avl left;
    Avl right;
    int height;
    Avl same;
    String s;
    public Avl(){
    }
    public Avl(int key, String s){
        this.key = key;
        this.left = null;
        this.right = null;
        this.s = new String(s);
        this.height = 1;
        this.same = null;
    }
    private int H(Avl a){
        if(a == null)
            return 0;
        return a.height;
    }
    private Avl singleRotateL(Avl root){
        Avl temp = root.left;
        root.left = temp.right;
        temp.right = root;
        root.height = Math.max((root.right == null) ? 0 : root.right.height, root.left == null ? 0 : root.left.height) + 1;
        temp.height = Math.max((temp.right == null) ? 0 : temp.right.height, temp.left == null ? 0 : temp.left.height) + 1;
        return temp;
    }
    private Avl singleRotateR(Avl root){
        Avl temp = root.right;
        root.right = temp.left;
        temp.left = root;
        root.height = Math.max((root.right == null) ? 0 : root.right.height, root.left == null ? 0 : root.left.height) + 1;
        temp.height = Math.max((temp.right == null) ? 0 : temp.right.height, temp.left == null ? 0 : temp.left.height) + 1;
        return temp;
    }
    private Avl doubleRotateL(Avl root){
        root.left = singleRotateR(root.left);
        return singleRotateL(root);
    }
    private Avl doubleRotateR(Avl root){
        root.right = singleRotateL(root.right);
        return singleRotateR(root);
    }
    public Avl add(Avl root, int key, String s){
        if(root == null){
            Avl t = new Avl(key, s);
            return t;
        }
        else if(root.key > key){
            root.left = add(root.left, key, s);
            if((H(root.left) - H(root.right)) == 2){
                if((root.left).key > key)
                    root = singleRotateL(root);
                else
                    root = doubleRotateL(root);
            }
        }
        else if(root.key < key){
            root.right = add(root.right, key, s);
            if((H(root.right) - H(root.left)) == 2){
                if((root.right).key < key)
                    root = singleRotateR(root);
                else
                    root = doubleRotateR(root);
            }
        }
        else if(root.key == key){
            Avl temp = root;
            while(temp.same != null){
                temp = temp.same;
            }
            temp.same = new Avl(key, s);
        }
        root.height = Math.max(H(root.left), H(root.right)) + 1;
        return root;
    }
}
