package utilities;

import java.util.ArrayList;
import java.util.Stack;

import agent.structures.SearchTreeNode;

public class utilities {
	public static ArrayList<int[]> copyArray(ArrayList<int[]> arr){
		ArrayList<int[]> newArr = new ArrayList<int[]>();
		for(int i =0; i < arr.size(); i++){
			newArr.add(arr.get(i));
		}
		return newArr;
	}

	public static Stack<SearchTreeNode> flip(Stack<SearchTreeNode> in) {
		Stack<SearchTreeNode> result = new Stack<SearchTreeNode>();
		while(!in.isEmpty())
			result.push(in.pop());
		return result;
	}
}
