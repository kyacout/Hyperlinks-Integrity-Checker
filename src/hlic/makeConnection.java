package hlic;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;

import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.ElementIterator;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

/**
 * @author Khaled
 * 
 */
public class makeConnection implements Runnable {

	@Override
	public void run() {
		while (!Engine.getInstance().nodeAvailable()) {
			Node currentNode = Engine.getInstance().getNode();

			EditorKit kit = new HTMLEditorKit();
			Document doc = kit.createDefaultDocument();
			
			LinkedList<String> links = new LinkedList<>();

			// The Document class does not yet
			// handle charset's properly.
			doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
			try {

				// Create a reader on the HTML content.
				Reader rd = getReader(currentNode.getUrl());

				// Parse the HTML.
				kit.read(rd, doc, 0);

				// Iterate through the elements
				// of the HTML document.
				ElementIterator it = new ElementIterator(doc);
				javax.swing.text.Element elem;
				while ((elem = it.next()) != null) {
					MutableAttributeSet s = (MutableAttributeSet) elem.getAttributes().getAttribute(HTML.Tag.A);
					String link = (String) s.getAttribute(HTML.Attribute.HREF);
					
					if (link != null) {
						links.add(link);
						System.out.println(link);
					}
				}
				
				Engine.getInstance().addChilderns(links, currentNode, true);
			} catch (Exception e) {
				Engine.getInstance().addChilderns(links, currentNode, false);
			}
		}
	}
	
	static Reader getReader(String uri) throws IOException {
		if (uri.startsWith("http:")) {

			// Retrieve from Internet.
			URLConnection conn = new URL(uri).openConnection();
			return new InputStreamReader(conn.getInputStream());
		} else {

			// Retrieve from file.
			return new FileReader(uri);
		}
	}
}
