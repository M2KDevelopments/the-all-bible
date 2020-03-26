/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bible;

import bible.enums.EnumBibleBook;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author MARTIN-PC
 * @version 1.0
 */
public class Bible {

    private static final String EXTENSION = ".xml";

    /**
     * This creates a bible object which references in-built bible files
     */
    public Bible() {
    }

    /**
     * This method scans for all the available version this applications
     * contains
     *
     * @return A String Array of the list of Bible versions. Return null if no
     * versions were found
     */
    public String[] getVersions() {

        System.out.println(Bible.class.getResource("resources").getFile());
        File f = new File(Bible.class.getResource("resources").getFile());
        File files[] = f.listFiles();
        String versions[] = new String[files.length];
        for (int i = 0; i < versions.length; i++) {
            File file = files[i];
            versions[i] = file.getName().replaceAll(EXTENSION, "");
        }
        return versions;
    }

    /**
     * Get all the books of the bible
     *
     * @return list of all book names
     */
    public EnumBibleBook[] getBookNames() {
        return EnumBibleBook.values();
    }

    /**
     * This method scans how many chapters are in the specified book of the
     * bible.
     *
     * @param version The version of bible written NKJV
     * @param book The book of the book i.e EnumBibleBook.GENESIS
     * @return The number chapters of the book of the bible. Returns 0 if there
     * was an error
     */
    public int getChapterCount(String version, EnumBibleBook book) {

        String path = getBibleFilePath(version);
        Node doc = getParseXMLDocumentNode(path, "bible");
        if (doc != null) {
            NodeList bookNodes = doc.getChildNodes();
            for (int i = 0; i < bookNodes.getLength(); i++) {
                if (bookNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element ele = (Element) bookNodes.item(i);
                    if (ele.getAttribute("name").toUpperCase().equals(book.getBook().toUpperCase())) {
                        return ele.getElementsByTagName("chapter").getLength();
                    }
                }
            }
        }
        return 0;
    }

    /**
     * This method scans how many verses are in the specified book and chapters
     * of the bible.
     *
     * @param version The version of bible written NKJV
     * @param book The book of the book i.e EnumBibleBook.GENESIS
     * @param chapter the chapter number
     * @return The number verses of the chapter in the book of the bible.
     * Returns 0 if there was an error
     */
    public int getVerseCount(String version, EnumBibleBook book, int chapter) {

        if (chapter > 0) {
            String path = getBibleFilePath(version);
            Node doc = getParseXMLDocumentNode(path, "bible");

            if (doc != null) {
                NodeList bookNodes = doc.getChildNodes();
                for (int i = 0; i < bookNodes.getLength(); i++) {
                    if (bookNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element ele = (Element) bookNodes.item(i);
                        if (ele.getAttribute("name").toUpperCase().equals(book.getBook().toUpperCase())) {

                            //get chapters/
                            NodeList chapterNodes = ele.getElementsByTagName("chapter");

                            for (int j = 0; j < chapterNodes.getLength(); j++) {
                                if (chapterNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                    ele = (Element) chapterNodes.item(j);

                                    if (ele.getAttribute("number").equals(String.valueOf(chapter))) {
                                        return ele.getElementsByTagName("verse").getLength();
                                    }
                                }
                            }

                        }
                    }
                }

            }

        }
        return 0;

    }

    /**
     * This method searches for the text for the verse reference specified
     *
     * @param version The version of bible written NKJV
     * @param book The book of the book i.e EnumBibleBook.GENESIS
     * @param chapter the chapter number
     * @param verse The verse number
     * @return The text of the bible for that references. Returns empty string
     * if there was an error
     */
    public String getVerse(String version, EnumBibleBook book, int chapter, int verse) {

        if (chapter > 0) {
            String path = getBibleFilePath(version);
            Node doc = getParseXMLDocumentNode(path, "bible");
            if (doc != null) {
                NodeList bookNodes = doc.getChildNodes();
                for (int i = 0; i < bookNodes.getLength(); i++) {
                    if (bookNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element ele = (Element) bookNodes.item(i);
                        if (ele.getAttribute("name").toUpperCase().equals(book.getBook().toUpperCase())) {

                            //get chapters/
                            NodeList chapterNodes = ele.getElementsByTagName("chapter");

                            for (int j = 0; j < chapterNodes.getLength(); j++) {
                                if (chapterNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                    ele = (Element) chapterNodes.item(j);

                                    if (ele.getAttribute("number").equals(String.valueOf(chapter))) {

                                        //get verses
                                        NodeList verseNodes = ele.getElementsByTagName("verse");

                                        for (int k = 0; k < verseNodes.getLength(); k++) {
                                            ele = (Element) verseNodes.item(k);

                                            if (ele.getAttribute("number").equals(String.valueOf(verse))) {
                                                return ele.getTextContent();
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }

            }

        }
        return "";

    }

    public String getVerse(String version, EnumBibleBook book, int chapter, int verseStart, int verseEnd) {
        String verses = "";
        for (int i = verseStart; i <= verseEnd; i++) {
            verses += " " + getVerse(version, book, chapter, i);
        }
        return verses;
    }

    private static Node getParseXMLDocumentNode(String filePath, String rootTag) {
        try {
            File fXmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            //get node
            NodeList nList = doc.getElementsByTagName(rootTag);
            return nList.item(0);

        } catch (IOException | ParserConfigurationException | SAXException ex) {
            System.err.println("Error XML Parsing: " + ex.getMessage());
        }
        return null;
    }

    private String getBibleFilePath(String version) {
        File f = new File(Bible.class.getResource("resources").getFile(), version + EXTENSION);
        return f.getAbsolutePath();
    }
}
