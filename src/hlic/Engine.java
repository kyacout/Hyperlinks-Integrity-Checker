package hlic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Engine {
	private static Queue<Node> q = new LinkedList<Node>();
	private static Engine singleton = null;
	private static HashMap<String, FileMetaData> h = new HashMap<String, FileMetaData>();
	private static int maxDepth;
	private static int maxDoc;
	
	public synchronized static Engine getInstance(String url, int maxD, int maxDo) {
		if (singleton == null){
			q.add(new Node(1, url, null));
			maxDepth = maxD;
			maxDoc = maxDo;
			return singleton = new Engine();
		}
		else
			throw new ExceptionInInitializerError();
	}

	public synchronized static Engine getInstance() {
		if (singleton == null)
			return singleton = new Engine();
		else
			return singleton;
	}

	synchronized Node getNode() {
		return q.poll();
	}

	public void addChilderns(LinkedList<String> childern, Node prevNode, boolean v) {
		
		String parent = prevNode.getUrl();
		String grandParent = prevNode.getParent();
		int depth = prevNode.getDepth();
		
		for (String curr : childern) {
			q.add(new Node(depth + 1, curr, parent));
		}

		if (!h.containsKey(parent)) {

			h.put(parent, new FileMetaData(v));
			
			if (!v && grandParent != null) {
				h.get(grandParent).setLinksValid(false);
			}
			
		} else {
			h.get(parent).increaseOcc();
		}

	}

	public boolean nodeAvailable() {
		return q.isEmpty();
	}
}
