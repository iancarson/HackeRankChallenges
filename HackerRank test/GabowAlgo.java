import java.util.*;
class GabowAlgo{
	//Number of vertices
	private int V;
	//Preorder number of counter
	private int precount;
	private int[] preorder;
	//to check if v is visited
	private boolean[] visited;
	//check strong component containing v
	private boolean [] chk;
	//to store given graph
	private List<Integer>[] graph;
	//to store all scc
	private List<List<Integer>> sccComp;
	private Stack<Integer> stack1;
	private Stack<Integer> stack2;
	//Function to get all strongly connected components
	public List<List<Integer>> getSCComponents(List<Integer>[] graph)
	{
		V = graph.length;
		this.graph = graph;
		preorder = new int[V];
		chk = new boolean[V];
		stack1 = new Stack<Integer>();
		stack2 = new Stack<Integer>();
		sccComp = new ArrayList<>();
		for(int v = 0;v < V; v++)
			if(!visited[v])
				dfs(v);
			return sccComp;
	}
	//Function dfs
	public void dfs(int v)
	{
		preorder[v] = precount++;
		visited[v] = true;
		stack1.push(v);
		stack2.push(v);
		for(int w : graph[v])
		{
			if(!visited[w])
				dfs(w);
			else if(!chk[w])
				while(preorder[stack2.peek()] > preorder[w])
					stack2.pop();
		}
		if(stack2.peek() == v)
		{
			stack2.pop();
			List<Integer> component = new ArrayList<Integer>();
			int w;
			do
			{
				w = stack1.pop();
				component.add(w);
			}
			while( w != v)
				sccComp.add(component);
		}
	}
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
        System.out.println("Gabow algorithm Test\n");
        System.out.println("Enter number of Vertices");
        /** number of vertices **/
        int V = scan.nextInt();
 
        /** make graph **/
        List<Integer>[] g = new List[V];        
        for (int i = 0; i < V; i++)
            g[i] = new ArrayList<Integer>();        
        /** accpet all edges **/
        System.out.println("\nEnter number of edges");
        int E = scan.nextInt();
        /** all edges **/
        System.out.println("Enter "+ E +" x, y coordinates");
        for (int i = 0; i < E; i++)
        {
            int x = scan.nextInt();
            int y = scan.nextInt();
            g[x].add(y);
        }    
 
        Gabow gab = new Gabow();        
        System.out.println("\nSCC : ");
        /** print all strongly connected components **/
        List<List<Integer>> scComponents = gab.getSCComponents(g);
        System.out.println(scComponents);   
	}
}