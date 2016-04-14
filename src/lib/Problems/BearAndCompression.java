/**
 * @Author Garvit Sharma <garvits45@gmail.com>
 * @Date April 14, 2016
 * @Org Paytm
 * @Problem http://codeforces.com/problemset/problem/653/B
 **/

import java.util.*;

public class BearAndCompression
{
	class TrieNode
	{
		TrieNode child[] = new TrieNode[6];
		char[] links = new char[36];
		int numLinks = 0;
		boolean isEnd;
	}

	private TrieNode root;

	public BearAndCompression() {
		root = new TrieNode();
	}

	public void insert(String key, String value) {
		TrieNode current = root;
		int idx;
		char ch;
		for(int iter=0; iter<key.length(); iter++) {
			ch = (char)(key.charAt(iter) - 'a');
			if (current.child[ch] == null) {
				current.child[ch] = new TrieNode();
			}
			current = current.child[ch];
		}
		current.isEnd = true;
		idx = current.numLinks;
		current.links[idx] = value.charAt(0);
		current.numLinks++;
	}

	public TrieNode search(String key) {
		TrieNode current = root;
		char ch;
		for(int iter=0; iter<key.length(); iter++) {
			ch = (char)(key.charAt(iter) - 'a');
			//System.out.println(key.charAt(iter));
			if (current.child[ch] == null) {
				return null;
			}
			current = current.child[ch];
		}
		if (current.isEnd) {
			return current;
		} else
		return null;
	}

	int getCount(int len, String key) {
		TrieNode node = search(key);
		int cnt=0;

		if (node == null) return 0;
		if (len == 2) return node.numLinks;

		for(int i=0; i<node.numLinks; i++) {
			cnt += getCount(len-1, new String(node.links[i]+""));
		}
		return cnt;
	}

	public static void main(String[] args) {
		BearAndCompression bac = new BearAndCompression();
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		int q = Integer.parseInt(sc.next());
		String key, value;

		for(int i=0; i<q; i++) {
			value = sc.next();
			key = sc.next();
			bac.insert(key, value);
		}

		System.out.println(bac.getCount(n, "a"));
	}
}