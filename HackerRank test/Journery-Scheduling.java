import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;


public class C 
{
	 static InputStream is;
	 static PrintWriter out;
	 static String INPUT = "";


	 static void  solve()
	 {
	 	 int n = ni(), m = ni();
	 	 int[] from = new int[n-1];
	 	 int[] to = new int[n-1];
	 	 for(int i = 0; i < n-1; i++)
	 	 {
	 	 	from[i] = ni()-1;
	 	 	to[i] = ni()-1;
	 	 } 
	 	 int[][] g = packU(n, from, to);
	 	 int[][] pars = parents3(g, 0);
	 	 int[] ord = pars[1], par = pars[0];
	 	 int[] ds1 = new int[n];
	 	 int[] ds2 = new int[n];
	 	 Arrays.fill(ds1, -1);
	 	 Arrays.fill(ds2. -1);
	 	 for(int i = n-1; i >= 0;i--)
	 	 {
	 	 	int cur = ord[i];
	 	 	for(int e : g[cur])
	 	 	{
	 	 		if(ds1[cur] <= ds1[e] + 1)
	 	 		{
	 	 			ds2[cur] = ds1[cur];
	 	 			ds1[cur] = ds1[e] + 1;
	 	 		}
	 	 		else if(ds2[cur] <= ds1[e] + 1)
	 	 		{
	 	 			ds2[cur] = ds1[e] + 1;
	 	 		}
	 	 	}
	 	 }
	 	 for(int i = 1; i < n;i++)
	 	 {
	 	 	int cur = ord[i];
	 	 	int pa = par[cur];
	 	 	int x = -1;
	 	 	if(ds1[pa] == ds1[cur] + 1)
	 	 	{
	 	 		x = ds2[pa] + 1;
	 	 	}
	 	 	else
	 	 	{
	 	 		x = ds1[pa] + 1;
	 	 	}
	 	 	if(ds1[cur] <= x)
	 	 	{
	 	 		ds2[cur] = ds1[cur];
	 	 		ds1[cur] = x;
	 	 	}
	 	 	else if(ds2[cur] <= x)
	 	 	{
	 	 		ds2[cur] = x;
	 	 	}
	 	 }
	 	 int diam = 0;
	 	 for(int i = 0;i < n;i++)
	 	 {
	 	 	diam = Math.max(diam, ds1[i]);
	 	 }
	 	 tr(ds1);
	 	 tr(ds2);
	 	 for( int u = 0;u < m;u++)
	 	 {
	 	 	int v = ni() -1, k =ni();
	 	 	out.println(ds1[v] + (long)diam * ( k-1)); 
	 	 }
	 }
	 public static int[][] parents3(int[][] g, int root)
	 {
	 	int n = g.length;
	 	int[] par = new int[n];
	 	Arrays.fill(par, -1);
	 	int[] depth = new int[n];
	 	q[0] = root;
	 	for(int p = 0, r =1; p < r; p++)
	 	{
	 		int cur = q[p];
	 		for(int nex : g[cur] )
	 		{
	 			if(par[cur] != nex)
	 			{
	 				q[r++] = nex;
	 				par[nex] =cur;
	 				depth[nex] = depth[cur] + 1;
	 			}
	 		}
	 	}
	 	return new int[][] {par, q, depth};
	 }
	 static int[][] packU(int n, int[] from, int[] to)
	 {
	 	int[][] g =  new int[n][];
	 	int[] p = new int[n];
	 	for(int f : from)
	 		p[f]++;
	 	for(int t :  to)
	 		p[t]++;
	 	for(int i = 0; i < from.length; i++)
	 	{
	 		g[from[i]][--p[from[i]]] =  to[i];
	 		g[to[i]][--p[to[i]]] =  from[i];
	 	}
	 	return g;
	 }
	 public static void main(String[] args) throws Exception
	 {
	 	long S = System.currentTimeMillis();
	 	is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
	 	out = new PrintWriter(System.out);
	 	solve();
	 	out.flush();
	 	long G = System.currentTimeMillis();
	 	tr( G-S + "ms");
	 }
	 private static boolean eof()
	{
		if(lenbuf == -1)return true;
		int lptr = ptrbuf;
		while(lptr < lenbuf)if(!isSpaceChar(inbuf[lptr++]))return false;
		
		try {
			is.mark(1000);
			while(true){
				int b = is.read();
				if(b == -1){
					is.reset();
					return true;
				}else if(!isSpaceChar(b)){
					is.reset();
					return false;
				}
			}
		} catch (IOException e) {
			return true;
		}
	}
	
	private static byte[] inbuf = new byte[1024];
	static int lenbuf = 0, ptrbuf = 0;
	
	private static int readByte()
	{
		if(lenbuf == -1)throw new InputMismatchException();
		if(ptrbuf >= lenbuf){
			ptrbuf = 0;
			try { lenbuf = is.read(inbuf); } catch (IOException e) { throw new InputMismatchException(); }
			if(lenbuf <= 0)return -1;
		}
		return inbuf[ptrbuf++];
	}
	
	private static boolean isSpaceChar(int c) { return !(c >= 33 && c <= 126); }
	private static int skip() { int b; while((b = readByte()) != -1 && isSpaceChar(b)); return b; }
	
	private static double nd() { return Double.parseDouble(ns()); }
	private static char nc() { return (char)skip(); }
	
	private static String ns()
	{
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while(!(isSpaceChar(b))){ // when nextLine, (isSpaceChar(b) && b != ' ')
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}
	
	private static char[] ns(int n)
	{
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while(p < n && !(isSpaceChar(b))){
			buf[p++] = (char)b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}
	
	private static char[][] nm(int n, int m)
	{
		char[][] map = new char[n][];
		for(int i = 0;i < n;i++)map[i] = ns(m);
		return map;
	}
	
	private static int[] na(int n)
	{
		int[] a = new int[n];
		for(int i = 0;i < n;i++)a[i] = ni();
		return a;
	}
	
	private static int ni()
	{
		int num = 0, b;
		boolean minus = false;
		while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
		if(b == '-'){
			minus = true;
			b = readByte();
		}
		
		while(true){
			if(b >= '0' && b <= '9'){
				num = num * 10 + (b - '0');
			}else{
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
	
	private static long nl()
	{
		long num = 0;
		int b;
		boolean minus = false;
		while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
		if(b == '-'){
			minus = true;
			b = readByte();
		}
		
		while(true){
			if(b >= '0' && b <= '9'){
				num = num * 10 + (b - '0');
			}else{
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
	
	private static void tr(Object... o) { if(INPUT.length() != 0)System.out.println(Arrays.deepToString(o)); }
}
}