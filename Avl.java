package com.company;
import java.lang.*;
public class Avl {
    int key;
    Avl left;
    Avl right;
    int height;
    String s;
    Avl same;
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
        root.height = Math.max(H(root.right), H(root.left)) + 1;
        temp.height = Math.max(H(temp.right), H(temp.left)) + 1;
        return temp;
    }
    private Avl singleRotateR(Avl root){
        Avl temp = root.right;
        root.right = temp.left;
        temp.left = root;
        root.height = Math.max(H(root.right), H(root.left)) + 1;
        temp.height = Math.max(H(temp.right), H(temp.left)) + 1;
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
            Avl n = new Avl(key, s);
            while(temp.same != null){
                temp = temp.same;
            }
            temp.same = n;
        }
        root.height = Math.max(H(root.left), H(root.right)) + 1;
        return root;
    }
    public void inorder(Avl root){
        if(root == null)
                return;
        inorder(root.left);
        Avl temp = root;
        while(temp != null) {
            System.out.println(temp.key + " " + temp.s);
            temp = temp.same;
        }
        inorder(root.right);
    }
    private int findMin(Avl r){
        if(r == null)
            return 0;
        while(r.left != null)
            r = r.left;
        return r.key;
    }
    public Avl delete(Avl root, int key){
        if(root == null)
            return root;
        if(root.key > key)
            root.left = delete(root.left, key);
        else if(root.key < key)
            root.right = delete(root.right, key);
        if(root.key == key){
            if(root.left == null && root.right == null)
                return null;
            else if(root.left == null && root.right != null)
                return root.right;
            else if(root.left != null && root.right == null)
                return root.left;
            else{
                root.key = findMin(root.right);
                root.right = delete(root.right, root.key);
            }
        }
        if(root == null)
            return null;
        root.height = Math.max(H(root.left), H(root.right)) + 1;
        if(H(root.left) != H(root.right)) {
            if (root.left != null && H(root.left) > H(root.right)) {
                int t = H(root.left.left) - H(root.left.right);
                if (t > 0)
                    root = singleRotateL(root);
                else
                    root = doubleRotateL(root);
            } else if(root.right != null) {
                int p = H(root.right.left) - H(root.right.right);
                if (p > 0)
                    root = doubleRotateR(root);
                else
                    root = singleRotateR(root);
            }
        }
        return root;
    }
}
