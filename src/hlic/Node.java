package hlic;

public class Node {
	private String url;
	private int depth;
	private String parentUrl;

	public Node(int d, String u, String p) {
		this.url = u;
		this.depth = d;
		this.parentUrl = p;
	}

	public String getUrl() {
		return this.url;
	}
	
	public String getParent() {
		return this.parentUrl;
	}
	
	public int getDepth() {
		return this.depth;
	}

}
