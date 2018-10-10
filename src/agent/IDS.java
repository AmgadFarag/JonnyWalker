package agent;

import models.MiniMap;
import models.WorldHandler;

import java.util.ArrayList;
import java.util.Stack;

import agent.structures.Operator;
import agent.structures.SearchTreeNode;
import agent.structures.State;
//result[0] b null, check for it
public class IDS extends Search {
	private SearchTreeNode root;
	private Stack<SearchTreeNode> queue;

	public IDS(SearchTreeNode root) {
		this.root = root;
		queue = new Stack<SearchTreeNode>();
		queue.add(root);
	}
	public SearchTreeNode begin(){
		return begin(0);
	}

	public SearchTreeNode begin(int depth) {
		if (queue.isEmpty())
			return null;
		Stack<SearchTreeNode> temp = new Stack<SearchTreeNode>();
		for (int i = 0; i <= depth; i++) {
			while (!queue.isEmpty()) {
				SearchTreeNode current = queue.pop();
				System.out.println(current.getDepth()+", " + current);
				if (isGoal(current))
					return current;
				temp.push(current);
			}
			while (!temp.isEmpty()) {
				Stack<SearchTreeNode> intermidate = new Stack<SearchTreeNode>();
				SearchTreeNode current = temp.pop();
				intermidate.push(current);
				if (current.getDepth() < depth) {
					ArrayList<SearchTreeNode> expansion = expandNode(current);
					//System.out.println(expansion.size());

					for (int j = expansion.size() - 1; j >= 0; j--) {
						temp.push(expansion.get(j));

					}
				}
				if (current.getDepth() == depth) {
					
					while(!intermidate.isEmpty())
					queue.push(intermidate.pop());
					
						
				}

			}
		}
		System.out.println(queue);

		return begin(++depth);

	}
//	public static void main(String[] args) {
//		State s = new State(0, 0, 1, 1, true);
//		State goal = new State(2, 2, 0, 2, true);
//		SearchTreeNode n1 = new SearchTreeNode(s, null, null, 0	, 0);
//		n1.setName("n1");
//		SearchTreeNode n2 = new SearchTreeNode(s, n1, Operator.UP, 1	, 0);
//		n2.setName("n2");
//		SearchTreeNode n3 = new SearchTreeNode(s, n1, Operator.LEFT, 1	, 0);
//		n3.setName("n3");
//		SearchTreeNode n4 = new SearchTreeNode(s, n2, Operator.UP, 2	, 0);
//		n4.setName("n4");
//		SearchTreeNode n5 = new SearchTreeNode(s, n2, Operator.LEFT, 2	, 0);
//		n5.setName("n5");
//		SearchTreeNode n6 = new SearchTreeNode(s, n4, Operator.PICKUP, 3	, 0);
//		n6.setName("n6");
//		SearchTreeNode n7 = new SearchTreeNode(s, n3, Operator.LEFT, 2	, 0);
//		n7.setName("n7");
//		SearchTreeNode n8 = new SearchTreeNode(goal, n7, Operator.KILL, 3	, 0);
//		n8.setName("n8");
//		IDS ids = new IDS(n1, null);
//		ids.begin(0);
//	}


}