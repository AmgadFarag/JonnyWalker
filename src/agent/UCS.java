package agent;
import java.util.PriorityQueue;
import agent.structures.Operator;
import agent.structures.SearchTreeNode;
import agent.structures.State;
public class UCS extends Search{
    private SearchTreeNode root;
	private PriorityQueue<SearchTreeNode> queue;
	public UCS(SearchTreeNode root, WorldHandler newWorld) {
		// TODO Auto-generated constructor stub
		world = newWorld;
		this.root = root;
		queue = new PriorityQueue<SearchTreeNode>();
		queue.add(root);
	}
	public SearchTreeNode begin() {

		while(true){
			if(queue.isEmpty()){
				System.out.println("no more moves -ucs");
			}
			SearchTreeNode current = queue.poll();
			System.out.println(current);
			if (isGoal(current))
					return current;
		    SearchTreeNode[] expandednodes = expandNode(current);
			for (int j = expandednodes.length - 1; j >= 0; j--) {
						queue.add(expandednodes[j]);
					}
			 
			
		}


	}



}

