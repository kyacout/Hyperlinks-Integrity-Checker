package hlic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Engine {
	
	static class dupInfo{
		String fileName;
		int size;
		public dupInfo(String f, int s){
			this.fileName = f;
			this.size = s;
		}
		@Override
		public int hashCode() {
			return (size + "-" + fileName).hashCode();
		}
		
	}
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

	public void addChildern(LinkedList<String> childern, Node prevNode, boolean v,int fileSize) {
		
		String parent = prevNode.getUrl();
		String grandParent = prevNode.getParent();
		int depth = prevNode.getDepth();
		if(h.size() < maxDocment && depth < maxDepth){
			for (String curr : childern) {
				q.add(new Node(depth + 1, curr, parent));
			}
	
			if (!h.containsKey(parent)) {
	
				h.put(parent, new FileMetaData(v,fileSize));
				
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
	
	static void generateReports(int currDepth) throws FileNotFoundException{
        PrintWriter firstReport = new PrintWriter(reportUrl + "\\ report1.txt");
        PrintWriter secondReport = new PrintWriter(reportUrl + "\\ report2.txt");
		HashMap<dupInfo, LinkedList<String>> duplicates = new HashMap<dupInfo, LinkedList<String>>();
		
		for (Map.Entry<String, FileMetaData> entry : h.entrySet()) {
		    String key = entry.getKey();
		    FileMetaData value = entry.getValue();
		    String [] sp;
		    sp = key.split("\\\\");
		    
		    String vaild = value.getValid() == true ? "valid": "not vaild";
		    String linksValid =  value.getLinksValid() == true ? "valid": "not vaild";
		    String fileType = sp[sp.length - 1].split(".")[1];
		    int fileSize = value.getFileSize();
		    int numberOfOcc = value.getNumOfOcc();
		    
		    dupInfo newDup = new dupInfo(sp[sp.length - 1],fileSize);
		    if(duplicates.containsKey(newDup)){
		    	duplicates.get(newDup).add(key);
		    }else{
		    	LinkedList<String> newLi = new LinkedList<String>();
		    	newLi.add(key);
		    	duplicates.put(newDup, newLi);
		    }
		    
		    firstReport.printf("%s: \n\tlink's validity: %s \n\tall-working links: %s \n\tfile type: %s \n\tnumber of occurnce: %d \n", key, vaild, linksValid, fileType, numberOfOcc);
		}
		
		firstReport.printf("==================================================================================\n");
		
		for (Map.Entry<dupInfo,  LinkedList<String>> entry : duplicates.entrySet()) {
			dupInfo key = entry.getKey();
		    LinkedList<String> value = entry.getValue();
		    
		    firstReport.printf("%s :\n", key.fileName);
		    
		    for (String curr : value) {
		    	firstReport.printf("\t%s\n", curr);
			}
		    
		}
	}
}
