package parsing;

import javax.xml.parsers.*;
import org.xml.sax.XMLReader;

public class ParserSAX {
	
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
		
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setValidating(true);
			spf.setNamespaceAware(true);
			SAXParser saxParser = spf.newSAXParser();
			
			XMLReader xmlReader = saxParser.getXMLReader();
			xmlReader.setErrorHandler(new ErrorHandler());
		    SAXContentHandler handler = new SAXContentHandler();
		    xmlReader.setContentHandler(handler);
		    
		    xmlReader.setFeature(schemaFeature,true);
		    xmlReader.parse(filename);
		    
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
