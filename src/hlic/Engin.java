package hlic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Engin {
	Queue<Node> q =  new LinkedList<Node>();
	Engin singleton = null;
	
//	HashMap<String, V>
	
	public Engin getInstance(){
		if(singleton == null)
			return singleton = new Engin();
		else
			return singleton;
	}
	
	synchronized Node getNode(){
		return q.poll();
	}
	
	public void addChilderns(LinkedList<String> childern, int depth, String parent, FileMetaData parentData){
		
	}

}
