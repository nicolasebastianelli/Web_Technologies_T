import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;


public class Parser  extends DefaultHandler{
	// Flag per ricordare dove siamo:
	private boolean Indestinatario=false, Inmittente=false, Indata=false, Inoggetto=false,Insaluto=false,Inparagrafo=false,Inchiusura=false,Infirma=false;
	// Attributi per i valori da visualizzare
	private String destinatario, mittente, data, oggetto,saluto,chiusura,firma,paragrafo;
	private int i=0;
	private String[] body = new String[50] ;
	public void startElement (String namespaceURI,
			String localName, String rawName, Attributes atts)
	{
		if (rawName.equals("mittente"))
			Inmittente = true;
		if (rawName.equals("destinatario"))
			Indestinatario = true;
		if (rawName.equals("data"))
			Indata = true;
		if (rawName.equals("oggetto"))
			Inoggetto = true;
		if (rawName.equals("saluto"))
			Insaluto = true;
		if (rawName.equals("paragrafo"))
			Inparagrafo = true;
		if (rawName.equals("chiusura"))
			Inchiusura = true;
		if (rawName.equals("firma"))
			Infirma = true;
		if (rawName.equals("corpo")){
			body =new String[50];
			i=0;
		}
	} 

	public void characters (char ch[], int start,
			int length)
	{
		if (Inmittente) destinatario =
				new String(ch, start, length);
		if (Indestinatario) mittente =
				new String(ch, start, length);
		if (Indata) data =
				new String(ch, start, length);
		if (Inoggetto) oggetto =
				new String(ch, start, length);
		if (Insaluto) saluto =
				new String(ch, start, length);			 
		if (Inparagrafo) paragrafo =
				new String(ch, start, length);
		if (Inchiusura) chiusura =
				new String(ch, start, length);
		if (Infirma) firma =
				new String(ch, start, length);
	}

	public void endElement(String namespaceURI, String
			localName, String qName)
	{
		if (qName.equals("lettera")){
			System.out.println("from: "+destinatario + " to: " +
					mittente + " at "+data+"\n"+oggetto+"\n"+saluto);
			for(int j=0;j<i;j++){
				System.out.println(body[j]);
			}
			System.out.println(chiusura+"\n"+firma+"\n\n");
		}
		//Aggiorna i flag di contesto
		if (qName.equals("mittente"))
			Inmittente = false;
		if (qName.equals("destinatario"))
			Indestinatario = false;
		if (qName.equals("data"))
			Indata = false;
		if (qName.equals("oggetto"))
			Inoggetto = false;
		if (qName.equals("saluto"))
			Insaluto = false;
		if (qName.equals("paragrafo")){
			Inparagrafo = false;
			body[i]=paragrafo;
			i++;
		}
		if (qName.equals("chiusura"))
			Inchiusura = false;
		if (qName.equals("firma"))
			Infirma = false;
	}

	public static void main (String args[]) throws Exception
	{
		// Usa SAXParserFactory per istanziare un XMLReader
		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setNamespaceAware(true);
		spf.setValidating(true);
		try
		{
			SAXParser saxParser = spf.newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();
			ContentHandler handler = new Parser();
			xmlReader.setContentHandler(handler);
			xmlReader.setFeature("http://apache.org/xml/features/validation/schema",true);
			MyErrorHandler errorHandler = new MyErrorHandler();
			xmlReader.setErrorHandler(errorHandler);
			for (int i=0; i<args.length; i++)
				xmlReader.parse(args[i]);
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
			System.exit(1);
		};
	}
}
