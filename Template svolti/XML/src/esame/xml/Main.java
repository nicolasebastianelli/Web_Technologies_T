package esame.xml;

import java.io.File;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class Main {

	public static void main(String[] args) throws Exception {

		String xmlFilename= null;

		if (args.length == 0) {
			xmlFilename = "files/hotels.xml";
		} else if (args.length == 1) {
			xmlFilename = args[0];
		} else {
			System.out.println("Usage: " + Main.class.getSimpleName() + " [xmlfile]");
			System.exit(1);
		}
		try {

			String schemaFeature = "http://apache.org/xml/features/validation/schema";
			String ignorableWhitespace = "http://apache.org/xml/features/dom/include-ignorable-whitespace";

			// DOM
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setValidating(true);
			dbf.setNamespaceAware(true);
			dbf.setFeature(schemaFeature, true);
			dbf.setFeature(ignorableWhitespace, false);

			DocumentBuilder db = dbf.newDocumentBuilder();
			ErrorHandler errorHandler = new ErrorHandler();
			db.setErrorHandler(errorHandler);

			Document dom = db.parse(new File(xmlFilename));
			dom.getDocumentElement().normalize();

			System.out.println("Risultati ricerca siti cliccati:");
			for (String string : DomFunctions.getSelezione(dom)) {
				System.out.println(string);
			}

			//SAX
			/*
				SAXParserFactory spf = SAXParserFactory.newInstance();
				spf.setValidating(true);
				spf.setNamespaceAware(true);
				SAXParser saxParser = spf.newSAXParser();

				// 2) agganciare opportuni listener al lettore XML
				XMLReader xmlReader = saxParser.getXMLReader();
				ErrorHandler errorHandler = new ErrorHandler();
			    xmlReader.setErrorHandler(errorHandler);

				SAXContentHandler handler = new SAXContentHandler();
			    xmlReader.setContentHandler(handler);


				xmlReader.setFeature(schemaFeature,true);


				xmlReader.parse(xmlFilename);

				System.out.println("SAX IgnorableWhitespace = " + handler.getIgnorableWhitespace());
				System.out.println("SAX PeopleAmount = " + handler.getPeopleAmount());
				System.out.println("SAX PeoplePreMM = " + handler.getPeoplePreMM());
				System.out.println("SAX DonTel = " + handler.getDonTel());
			 */
			FileWriter fw = new FileWriter("files/result.txt");
			fw.write("Risultati ricerca siti cliccati: \n");
			for (String string : DomFunctions.getSelezione(dom)) {
				fw.write(string+"\n");
			}
			fw.close();

			DomFunctions.printDom(dom);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
