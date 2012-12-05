package hlic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Engine {
	Queue<Node> q =  new LinkedList<Node>();
	Engine singleton = null;
	
//	HashMap<String, V>
	
	public Engine getInstance(){
		if(singleton == null)
			return singleton = new Engine();
		else
			return singleton;
	}
	
	synchronized Node getNode(){
		return q.poll();
	}
	
	public void addChilderns(LinkedList<String> childern, int depth, String parent, boolean v){
		
	}

}
