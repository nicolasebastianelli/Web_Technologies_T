import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;


public class MyErrorHandler extends DefaultHandler{
	public MyErrorHandler (){ }
	public void error (SAXParseException e) {
		System.out.println("Parsing error: "+e.getMessage());
	}
	public void warning (SAXParseException e) {
		System.out.println("Parsing problem: "+e.getMessage());
	}
	public void fatalError (SAXParseException e) {
		System.out.println("Parsing error: "+e.getMessage());
		System.out.println("Cannot continue.");
		System.exit(1);
	}
}
