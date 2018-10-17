package utilities;

import java.util.ArrayList;

public class utilities {
	public static ArrayList<int[]> copyArray(ArrayList<int[]> arr){
		ArrayList<int[]> newArr = new ArrayList<int[]>();
		for(int i =0; i < arr.size(); i++){
			newArr.add(arr.get(i));
		}
		return newArr;
	}
}
