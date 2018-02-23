package com.company;
import java.lang.*;
import java.util.*;
public class Main {

	public static void main(String[] args) {
		int key, n, i;
		String s = new String();
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		Avl r = new Avl();
		Avl av = null;
		for (i = 0; i < n; i++) {
			key = in.nextInt();
			s = in.nextLine();
			av = r.add(av, key , s);
		}
		r.inorder(av);
		System.out.println();
		key = in.nextInt();
		av = r.delete(av,key);
		r.inorder(av);
	}
}
