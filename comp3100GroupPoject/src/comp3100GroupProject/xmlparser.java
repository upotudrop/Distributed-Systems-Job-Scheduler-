package comp3100GroupProject;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class xmlparser {
	public static comp3100GroupProject.configFromXML XMLParse(File input) throws ParserConfigurationException, SAXException, IOException, VerifyError {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();

		Document doc = dBuilder.parse(input);

		NodeList nodeList= doc.getElementsByTagName("server");

		configFromXML config = new configFromXML(1024);

		doc.getDocumentElement().normalize();

		for(int i=0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);

			if(n.getNodeName().equals("server")) {
				Servers s = new Servers(n.getAttributes().getNamedItem("type").getNodeValue(),
						Integer.parseInt(n.getAttributes().getNamedItem("limit").getNodeValue()),
						Integer.parseInt(n.getAttributes().getNamedItem("bootupTime").getNodeValue()),
						Double.parseDouble(n.getAttributes().getNamedItem("rate").getNodeValue()),
						Integer.parseInt(n.getAttributes().getNamedItem("coreCount").getNodeValue()),
						Integer.parseInt(n.getAttributes().getNamedItem("memory").getNodeValue()),
						Integer.parseInt(n.getAttributes().getNamedItem("disk").getNodeValue()));


				configFromXML.addServer(s);

			}
		}
		return config;
	}
}
