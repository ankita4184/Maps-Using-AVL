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
			key = hash(key);
			s = in.nextLine();
			av = r.add(av, key , s);
		}

	}

	private static int hash(int k) {
		Random r = new Random();
		int a = r.nextInt(1000);
		int b = r.nextInt(2000);
		int m = ((a * k) + b) % 997;
		return m;
	}
}
