//package storage;
//
//import org.w3c.dom.*;
//import org.xml.sax.SAXException;
//
//import business.components.Item;
//import business.simulation.Grid;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//
//public class XMLFile {
//
//	public static void saveAsXML(ArrayList<Item> itemList,String path,Grid grid) {
//
//		try {
//			DocumentBuilderFactory documentF = DocumentBuilderFactory.newInstance();
//			DocumentBuilder documentB = documentF.newDocumentBuilder();
//			Document document = documentB.newDocument();
//
//			
//			Element root = document.createElement("Simulation");
//			document.appendChild(root);
//			
//			if(grid == null) {
//				System.out.println("Sie m�ssen mindestens eine Stra�e gesetzt haben.");
//			}
//			
//			Element gridlayout = document.createElement("grid");
//			
//			Element x = document.createElement("x");
//			x.appendChild(document.createTextNode(String.valueOf(grid.getX())));
//			gridlayout.appendChild(x);
//			
//			Element y = document.createElement("y");
//			y.appendChild(document.createTextNode(String.valueOf(grid.getY())));
//			gridlayout.appendChild(y);
//			
//			root.appendChild(gridlayout);
//			
//			
//			
//			for(Item i : itemList){
//				Element element = document.createElement("element");
//				root.appendChild(element);
//				
//				Element xpos = document.createElement("x");
//                xpos.appendChild(document.createTextNode(String.valueOf(i.getPositionX())));
//                element.appendChild(xpos);
//
//                Element ypos = document.createElement("y");
//                ypos.appendChild(document.createTextNode(String.valueOf(i.getPositionY())));
//                element.appendChild(ypos);
//			}
//			
//			
//			
//			TransformerFactory transformerFactory = TransformerFactory.newInstance();
//			Transformer transformer = transformerFactory.newTransformer();
//			DOMSource domSource = new DOMSource(document);
//			StreamResult streamResult = new StreamResult(new File(path));
//			transformer.transform(domSource, streamResult);
//			System.out.println("Done creating XML File");
//
//		} catch (ParserConfigurationException pce) {
//			pce.printStackTrace();
//		} catch (TransformerException tfe) {
//			tfe.printStackTrace();
//		}
//	}
//
//	public static ArrayList<Item> loadFromXML(String path) {
//		ArrayList<Item> itemList = new ArrayList<>();
//		try {
//			File xmlFile = new File(path);
//			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//			Document document = documentBuilder.parse(xmlFile);
//			document.getDocumentElement().normalize();
//			NodeList nodeList = document.getElementsByTagName("element");
//			System.out.println(nodeList.getLength());
//			for (int i = 0; i < nodeList.getLength(); i++) {
//                Node node = nodeList.item(i);
//                if (node.getNodeType() == Node.ELEMENT_NODE) {
//                    Element element = (Element) node;
//                  //  Item item = new Item(Integer.parseInt(element.getElementsByTagName("x").item(0).getTextContent()), Integer.parseInt(element.getElementsByTagName("y").item(0).getTextContent()));
//                   // itemList.add(item);
//                }
//            }
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return itemList;
//	}
//
//}
