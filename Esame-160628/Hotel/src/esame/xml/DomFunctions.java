package esame.xml;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class DomFunctions {

	// FUNZIONI SEMPRE UTILI --------------------------------------------------------------

	public static String getAttributeByName(Node node, String attrName){
		return node.getAttributes().getNamedItem(attrName).getTextContent();
	}

	public static Node getNodeByName(Node root, String name){
		NodeList childnodes = root.getChildNodes();
		for(int i=0; i<childnodes.getLength(); i++){
			Node ni = childnodes.item(i);
			if(ni.getNodeName().equals(name)){
				return ni;
			}
		}
		return null;
	}

	public static String getNodeTextByNodeName(Node root, String name){
		NodeList childnodes = root.getChildNodes();
		for(int i=0; i<childnodes.getLength(); i++){
			Node ni = childnodes.item(i);
			if(ni.getNodeName().equals(name)){
				return ni.getTextContent();
			}
		}
		return null;
	}


	public static Set<Node> getNodesByName(Node root, String name){ //nodi dello stesso tipo in un rootxml
		NodeList childnodes = root.getChildNodes();
		Set<Node> ret = new HashSet<Node>();
		for(int i=0; i<childnodes.getLength(); i++){
			Node ni = childnodes.item(i);
			if(ni.getNodeName().equals(name)){
				ret.add(ni);
			}
		}
		if(!ret.isEmpty() && ret.size() > 0)
			return ret;
		else return null;
	}

	public static void printDom(Document document) throws TransformerException{
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

		Source source = new DOMSource(document);
		Result output = new StreamResult(System.out);
		transformer.transform(source, output);
	}

	// FUNZIONI RICHIESTE --------------------------------------------------------------	

	public static List<String> getSelezione(Document domDocument){
		Element element;
		String res = null;
		List<String> resuList= new ArrayList<String>();
		NodeList nodeList = domDocument.getElementsByTagName("link");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			element= (Element) node;
			if(element.hasAttribute("cliccato"))
				if(Boolean.valueOf(element.getAttribute("cliccato"))){
					{
						res =node.getParentNode().getFirstChild().getTextContent();
						resuList.add(res);
					}					            		 
				}
		}
		return resuList;
	}
}
