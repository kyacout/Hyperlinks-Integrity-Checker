package hlic;

public class FileMetaData {
	private boolean valid;
	private boolean linksVaild;
	private int numberOfOcc;
	
	
	public FileMetaData(boolean v){
		this.valid = v;
		this.linksVaild = true;
		this.numberOfOcc = 1;
	}
	
	public void increaseOcc(){
		this.numberOfOcc++;
	}
}
