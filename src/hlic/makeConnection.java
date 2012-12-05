package hlic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Khaled
 *
 */
public class makeConnection implements Runnable {

	@Override
	public void run() {
		while(!Engine.getInstance().nodeAvailable()) {
			Node currentNode = Engine.getInstance().getNode();
			
			try {
				URL url = new URL(currentNode.getUrl());
				BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
}
