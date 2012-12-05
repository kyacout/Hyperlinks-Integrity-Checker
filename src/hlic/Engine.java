package hlic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Engine {
	private Queue<Node> q =  new LinkedList<Node>();
	private static Engine singleton = null;
	private HashMap<String, FileMetaData> h = new HashMap<String, FileMetaData>();
	
	static Engine getInstance(){
		if(singleton == null)
			return singleton = new Engine();
		else
			return singleton;
	}
	
	synchronized Node getNode(){
		return q.poll();
	}
	
	public void addChilderns(LinkedList<String> childern, int depth, String parent, boolean v){
		
		for (String curr : childern) {
			q.add(new Node(depth, curr, parent));
		}
		
		if(!h.containsKey(parent)){
			h.put(parent, new FileMetaData(v));
		}else{
			h.get(parent).increaseOcc();
		}
		
	}

}
