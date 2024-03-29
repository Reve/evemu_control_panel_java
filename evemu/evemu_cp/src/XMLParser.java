import java.io.File;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {

	public static void WriteSettingsToFile() {

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("server_settings");
			doc.appendChild(rootElement);

			Element _server = doc.createElement("Server");
			_server.appendChild(doc.createTextNode(Settings.server));
			rootElement.appendChild(_server);

			Element _user = doc.createElement("User");
			_user.appendChild(doc.createTextNode(Settings.user));
			rootElement.appendChild(_user);

			Element _pass = doc.createElement("Pass");
			_pass.appendChild(doc.createTextNode(Settings.pass));
			rootElement.appendChild(_pass);

			Element _port = doc.createElement("Port");
			_port.appendChild(doc.createTextNode(Settings.port));
			rootElement.appendChild(_port);

			Element _dbName = doc.createElement("DatabaseName");
			_dbName.appendChild(doc.createTextNode(Settings.dbName));
			rootElement.appendChild(_dbName);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			String path = XMLParser.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			path = path.substring(0, path.length() - 12);
			StreamResult result = new StreamResult(new File(path + "/" + "settings.xml"));

			transformer.transform(source, result);

			System.out.println("File saved!");
			JOptionPane.showMessageDialog(null, "File saved!", "Info", JOptionPane.INFORMATION_MESSAGE);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error: " + pce.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error: " + tfe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void LoadSettingsFromFile(File file) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);

			doc.getDocumentElement().normalize();

			NodeList serverNodeLst = doc.getElementsByTagName("Server");
			Element serverElm = (Element) serverNodeLst.item(0);
			NodeList serverNm = serverElm.getChildNodes();
			Settings.server = ((Node) serverNm.item(0)).getNodeValue();

			NodeList userNodeLst = doc.getElementsByTagName("User");
			Element userElm = (Element) userNodeLst.item(0);
			NodeList userNm = userElm.getChildNodes();
			Settings.user = ((Node) userNm.item(0)).getNodeValue();

			NodeList passNodeLst = doc.getElementsByTagName("Pass");
			Element passElm = (Element) passNodeLst.item(0);
			NodeList passNm = passElm.getChildNodes();
			Settings.pass = ((Node) passNm.item(0)).getNodeValue();

			NodeList portNodeLst = doc.getElementsByTagName("Port");
			Element portElm = (Element) portNodeLst.item(0);
			NodeList portNm = portElm.getChildNodes();
			Settings.port = ((Node) portNm.item(0)).getNodeValue();

			NodeList dbNameNodeLst = doc.getElementsByTagName("DatabaseName");
			Element dbNameElm = (Element) dbNameNodeLst.item(0);
			NodeList dbNameNm = dbNameElm.getChildNodes();
			Settings.dbName = ((Node) dbNameNm.item(0)).getNodeValue();

			System.out.println("Settings loaded!");

			JOptionPane.showMessageDialog(null, "Settings loaded!", "Info", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
