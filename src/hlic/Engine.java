package hlic;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Engine {
	private static Queue<Node> q;
	private static Engine singleton = null;
	private static HashMap<String, FileMetaData>h ;
	private static int maxDepth;
	private static int maxDocment;
	private static boolean targetReach;
	private static String reportUrl;
	
	public synchronized static void analyzeFile(String url, String report, int maxD, int maxDoc) {
		if (singleton == null){
			q = new LinkedList<Node>();
			h = new HashMap<String, FileMetaData>();
			q.add(new Node(1, url, null));
			maxDepth = maxD;
			maxDocment = maxDoc;
			reportUrl = report;
			Thread newThread = new Thread(new makeConnection());
			newThread.run();
			singleton = new Engine();
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

	public void addChildern(LinkedList<String> childern, Node prevNode, boolean v) {
		
		String parent = prevNode.getUrl();
		String grandParent = prevNode.getParent();
		int depth = prevNode.getDepth();
		if(h.size() < maxDocment && depth < maxDepth){
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
		}else{
			targetReach = false;
			singleton = null;
			
		}

	}

	public boolean nodeAvailable() {
		return q.isEmpty();
	}
	
	public boolean isTargetReached(){
		return targetReach;
	}
	
	static void generateReports(int currDepth){
		File firstReport = new File(reportUrl + "\\ report1.txt");
		File secondReport = new File(reportUrl + "\\ report2.txt");
		
		for (HashMap.Entry<String, FileMetaData> entry : h.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    // ...
		}
	}
}
