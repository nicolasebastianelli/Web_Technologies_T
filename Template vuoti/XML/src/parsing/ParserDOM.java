package parsing;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class ParserDOM {
	
	public static void main(String[] args) {
		
		String filename = null;
		if (args.length == 0) {
			filename = "resources/file.xml";
		} else if (args.length == 1) {
			filename = args[1];
		} else {
			System.out.println("Usage: " + ParserDOM.class.getSimpleName() + " [xmlfile]");
			System.exit(1);
		}
		
		String schemaFeature = "http://apache.org/xml/features/validation/schema";
		String ignorableWhitespace = "http://apache.org/xml/features/dom/include-ignorable-whitespace";

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setValidating(true);
			dbf.setNamespaceAware(true);
			dbf.setFeature(schemaFeature,true);
			dbf.setFeature(ignorableWhitespace, false);

			DocumentBuilder builder = dbf.newDocumentBuilder();
			builder.setErrorHandler(new ErrorHandler());

			Document document = builder.parse(new File(filename));
			document.getDocumentElement().normalize();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO
}
