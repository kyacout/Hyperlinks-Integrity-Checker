package hlic;

public class FileMetaData {
	private boolean valid;
	private boolean linksVaild;
	private int numberOfOcc;
	private int fileSize;
	
	
	public FileMetaData(boolean v, int fSize){
		this.valid = v;
		this.linksVaild = true;
		this.numberOfOcc = 1;
		this.fileSize = fSize;
	}
	
	public boolean getValid(){
		return this.valid;
	}
	
	public boolean getLinksValid(){
		return this.linksVaild;
	}
	
	public int getNumOfOcc(){
		return this.numberOfOcc;
	}
	
	public int getFileSize(){
		return this.fileSize;
	}
	
	public void increaseOcc(){
		this.numberOfOcc++;
	}
	
	public void setLinksValid(boolean v){
		this.linksVaild = v;
	}

}
