package hlic;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;

import javax.swing.text.BadLocationException;
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
public class makeConnection {
	public static void run() throws BadLocationException {
		while (!Engine.getInstance().nodeAvailable()) {
			Node currentNode = Engine.getInstance().getNode();

			EditorKit kit = new HTMLEditorKit();
			Document doc = kit.createDefaultDocument();

			LinkedList<String> links = new LinkedList<>();

			// The Document class does not yet
			// handle charset's properly.
			doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
			
			// Create a reader on the HTML content.
			Reader rd;
			try {
				rd = getReader(currentNode.getUrl());
				
				// Parse the HTML.
				kit.read(rd, doc, 0);
			} catch (IOException e) {
				Engine.getInstance().addChildern(links, currentNode, false);
			}

			// Iterate through the elements
			// of the HTML document.
			ElementIterator it = new ElementIterator(doc);
			javax.swing.text.Element elem;
			while ((elem = it.next()) != null) {
				MutableAttributeSet s = (MutableAttributeSet) elem
						.getAttributes().getAttribute(HTML.Tag.A);

				if (s != null) {
					System.out.println(s.getAttribute(HTML.Attribute.HREF));
				}
			}

			Engine.getInstance().addChildern(links, currentNode, true);
		}
	}

	static Reader getReader(String url) throws MalformedURLException, IOException {
		if (url.startsWith("http:")) {

			// Retrieve from Internet.
			return new InputStreamReader(new URL(url).openStream());
		} else {

			// Retrieve from file.
			return new FileReader(url);
		}
	}
}
