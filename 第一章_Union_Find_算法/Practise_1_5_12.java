package 第一章_Union_Find_算法;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class Practise_1_5_12 {
	static class CompressedQuickUnion {
		private int[] id;
		CompressedQuickUnion(int N) {
			id = new int[N];
			for (int i = 0; i < N; i++) 
				id[i] = i;
		}
		boolean connected(int p, int q) { return find(p) == find(q); }
		int find(int p) {
			int root = p;
			// 上溯查找至树根
			while (root != id[root])
				root = id[root];
			
			// 从当前结点开始,如果父结点不为树根
			while (id[p] != root) {
				// 保留父结点
				int parent = id[p];
				// 让儿子把树根作为父节点
				id[p] = root;
				// 往上挪
				p = parent;
			}
			return root;
		}
		void union(int p, int q) {
			int pRoot = find(p);
			int qRoot = find(q);
			if (pRoot == qRoot) {
				StdOut.printf("%d %d 已连通\n",p, q);
				return;
			}
			id[pRoot] = qRoot;
			StdOut.printf("连接 %d %d\n", p, q);
			StdOut.println(this);
		}
		int maxTreeDepth() {
			int depth = 0;
			for (int i = 0; i < id.length; i++) {
				int tmp = 0;
				int p = i;
				while (p != id[p]) {
					tmp++;
					p = id[p];
				}
				if (tmp > depth) depth = tmp;
			}
			return depth;
		}
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("--------------------------\n");
			sb.append("索引:\t");
			int i = 0;
			while (i < id.length) sb.append(" " + i++);
			sb.append("\n\t"); i = 0;
			while (i < id.length) sb.append(" " + id[i++]);
			sb.append("\n\t最大树高 " + maxTreeDepth()); 
			sb.append("\n--------------------------\n");
			return sb.toString();
		}
	}
	public static void main(String[] args) {
		int N = 40;
		CompressedQuickUnion cqu = new CompressedQuickUnion(N);
		Text_Generator gen = new Text_RandomPairGenerator(N);
		ArrayList<Object> container = new ArrayList<Object>();
		for (int i = 0; i < N; i++) {
			int[] pair = gen.nextPair();
			cqu.union(pair[0], pair[1]);
			container.add(pair);
		}
		StdOut.println();
		StdOut.printf("以下序列产生的树高为 : %d\n", cqu.maxTreeDepth());
		for (Object a : container) {
			int[] arr = (int[])a;
			StdOut.printf("%-2d %2d\n",arr[0], arr[1]);
		}
	}
	// output
	/*
	 * 	连接 26 5
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 5 27 28 29 30 31 32 33 34 35 36 37 38 39
			最大树高 1
		--------------------------
		
		连接 24 21
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 21 25 5 27 28 29 30 31 32 33 34 35 36 37 38 39
			最大树高 1
		--------------------------
		
		连接 23 2
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 2 21 25 5 27 28 29 30 31 32 33 34 35 36 37 38 39
			最大树高 1
		--------------------------
		
		连接 15 22
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 22 16 17 18 19 20 21 22 2 21 25 5 27 28 29 30 31 32 33 34 35 36 37 38 39
			最大树高 1
		--------------------------
		
		连接 12 7
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 2 3 4 5 6 7 8 9 10 11 7 13 14 22 16 17 18 19 20 21 22 2 21 25 5 27 28 29 30 31 32 33 34 35 36 37 38 39
			最大树高 1
		--------------------------
		
		连接 18 35
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 2 3 4 5 6 7 8 9 10 11 7 13 14 22 16 17 35 19 20 21 22 2 21 25 5 27 28 29 30 31 32 33 34 35 36 37 38 39
			最大树高 1
		--------------------------
		
		连接 23 37
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 4 5 6 7 8 9 10 11 7 13 14 22 16 17 35 19 20 21 22 2 21 25 5 27 28 29 30 31 32 33 34 35 36 37 38 39
			最大树高 2
		--------------------------
		
		18 35 已连通
		连接 31 27
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 4 5 6 7 8 9 10 11 7 13 14 22 16 17 35 19 20 21 22 2 21 25 5 27 28 29 30 27 32 33 34 35 36 37 38 39
			最大树高 2
		--------------------------
		
		连接 25 32
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 4 5 6 7 8 9 10 11 7 13 14 22 16 17 35 19 20 21 22 2 21 32 5 27 28 29 30 27 32 33 34 35 36 37 38 39
			最大树高 2
		--------------------------
		
		连接 23 35
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 4 5 6 7 8 9 10 11 7 13 14 22 16 17 35 19 20 21 22 37 21 32 5 27 28 29 30 27 32 33 34 35 36 35 38 39
			最大树高 2
		--------------------------
		
		连接 35 21
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 4 5 6 7 8 9 10 11 7 13 14 22 16 17 35 19 20 21 22 37 21 32 5 27 28 29 30 27 32 33 34 21 36 35 38 39
			最大树高 3
		--------------------------
		
		连接 31 9
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 4 5 6 7 8 9 10 11 7 13 14 22 16 17 35 19 20 21 22 37 21 32 5 9 28 29 30 27 32 33 34 21 36 35 38 39
			最大树高 3
		--------------------------
		
		连接 17 31
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 4 5 6 7 8 9 10 11 7 13 14 22 16 9 35 19 20 21 22 37 21 32 5 9 28 29 30 9 32 33 34 21 36 35 38 39
			最大树高 3
		--------------------------
		
		连接 25 6
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 4 5 6 7 8 9 10 11 7 13 14 22 16 9 35 19 20 21 22 37 21 32 5 9 28 29 30 9 6 33 34 21 36 35 38 39
			最大树高 3
		--------------------------
		
		连接 6 18
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 4 5 21 7 8 9 10 11 7 13 14 22 16 9 21 19 20 21 22 37 21 32 5 9 28 29 30 9 6 33 34 21 36 35 38 39
			最大树高 3
		--------------------------
		
		连接 24 17
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 4 5 21 7 8 9 10 11 7 13 14 22 16 9 21 19 20 9 22 37 21 32 5 9 28 29 30 9 6 33 34 21 36 35 38 39
			最大树高 4
		--------------------------
		
		连接 26 10
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 4 10 21 7 8 9 10 11 7 13 14 22 16 9 21 19 20 9 22 37 21 32 5 9 28 29 30 9 6 33 34 21 36 35 38 39
			最大树高 4
		--------------------------
		
		连接 13 32
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 4 10 9 7 8 9 10 11 7 9 14 22 16 9 21 19 20 9 22 37 21 32 5 9 28 29 30 9 9 33 34 21 36 35 38 39
			最大树高 4
		--------------------------
		
		32 23 已连通
		连接 20 13
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 4 10 9 7 8 9 10 11 7 9 14 22 16 9 21 19 9 9 22 9 21 32 5 9 28 29 30 9 9 33 34 9 36 9 38 39
			最大树高 2
		--------------------------
		
		连接 12 30
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 4 10 9 30 8 9 10 11 7 9 14 22 16 9 21 19 9 9 22 9 21 32 5 9 28 29 30 9 9 33 34 9 36 9 38 39
			最大树高 2
		--------------------------
		
		连接 10 32
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 4 10 9 30 8 9 9 11 7 9 14 22 16 9 21 19 9 9 22 9 21 32 5 9 28 29 30 9 9 33 34 9 36 9 38 39
			最大树高 3
		--------------------------
		
		连接 25 29
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 4 10 9 30 8 29 9 11 7 9 14 22 16 9 21 19 9 9 22 9 21 9 5 9 28 29 30 9 9 33 34 9 36 9 38 39
			最大树高 4
		--------------------------
		
		连接 14 32
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 4 10 9 30 8 29 9 11 7 9 29 22 16 9 21 19 9 9 22 9 21 9 5 9 28 29 30 9 29 33 34 9 36 9 38 39
			最大树高 4
		--------------------------
		
		连接 4 34
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 34 10 9 30 8 29 9 11 7 9 29 22 16 9 21 19 9 9 22 9 21 9 5 9 28 29 30 9 29 33 34 9 36 9 38 39
			最大树高 4
		--------------------------
		
		26 31 已连通
		6 18 已连通
		连接 28 25
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 34 29 29 30 8 29 29 11 7 9 29 22 16 9 29 19 9 29 22 9 21 29 29 9 29 29 30 29 29 33 34 9 36 9 38 39
			最大树高 3
		--------------------------
		
		连接 17 30
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 34 29 29 30 8 29 29 11 7 9 29 22 16 29 29 19 9 29 22 9 21 29 29 9 29 30 30 29 29 33 34 9 36 9 38 39
			最大树高 4
		--------------------------
		
		连接 30 8
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 34 29 29 30 8 29 29 11 7 9 29 22 16 29 29 19 9 29 22 9 21 29 29 9 29 30 8 29 29 33 34 9 36 9 38 39
			最大树高 5
		--------------------------
		
		连接 39 12
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 34 29 29 8 8 29 29 11 8 9 29 22 16 29 29 19 9 29 22 9 21 29 29 9 29 30 8 29 29 33 34 9 36 9 38 8
			最大树高 5
		--------------------------
		
		39 32 已连通
		6 12 已连通
		连接 23 34
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 34 29 8 8 34 8 29 11 8 9 29 22 16 29 29 19 9 29 22 8 21 29 29 9 29 8 8 29 8 33 34 9 36 9 38 8
			最大树高 4
		--------------------------
		
		连接 32 15
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 34 29 8 8 34 8 29 11 8 9 29 22 16 29 29 19 9 29 22 8 21 29 29 9 29 8 8 29 34 33 22 9 36 9 38 8
			最大树高 5
		--------------------------
		
		39 31 已连通
		22 15 已连通
		连接 19 38
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 34 29 8 8 22 8 29 11 8 9 29 22 16 29 29 38 9 29 22 8 21 29 29 9 29 22 8 22 34 33 22 9 36 9 38 22
			最大树高 4
		--------------------------
		
		连接 37 33
		--------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
			 0 1 37 3 34 29 8 8 22 22 29 11 8 9 29 22 16 29 29 38 9 29 33 8 21 29 29 9 29 22 8 22 34 33 22 9 36 22 38 22
			最大树高 4
		--------------------------
		
		
		以下序列产生的树高为 : 4
		26  5
		24 21
		23  2
		15 22
		12  7
		18 35
		23 37
		18 35
		31 27
		25 32
		23 35
		35 21
		31  9
		17 31
		25  6
		6  18
		24 17
		26 10
		13 32
		32 23
		20 13
		12 30
		10 32
		25 29
		14 32
		4  34
		26 31
		6  18
		28 25
		17 30
		30  8
		39 12
		39 32
		6  12
		23 34
		32 15
		39 31
		22 15
		19 38
		37 33
	 */
}
