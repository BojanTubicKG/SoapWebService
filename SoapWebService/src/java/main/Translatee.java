/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

@WebService(endpointInterface = "main.Translate")
public class Translatee implements Translate {

    @Override
    public String translate(String rec, String izvorniJezik, String trazeniJezik) {
        String prevod = "";

        if (izvorniJezik.equals("engleski") && trazeniJezik.equals("srpski")) {
            try {
                XPathFactory xPathFactory = XPathFactory.newInstance();
                XPath xPath = xPathFactory.newXPath();
                XPathExpression xPathExpression = xPath.compile("/recnik/par[engleski=\"" + rec + "\"]");

                File file = new File("C:\\Users\\User\\Documents\\NetBeansProjects\\Assingment_WebService\\src\\java\\baza.xml");
                //mora da bude apsolutna putanja ,zato sto ide preko servera!!!
                InputSource is = new InputSource(new FileInputStream(file));

                Object o = xPathExpression.evaluate(is, XPathConstants.NODE);
                Node node = (Node) o;

                if (node != null) {
                    prevod = node.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getTextContent();
                } else {
                    prevod = "Trazena rec ne postoji u bazi podataka";
                }

            } catch (XPathExpressionException ex) {
                Logger.getLogger(Translatee.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Translatee.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (izvorniJezik.equals("srpski") && trazeniJezik.equals("engleski")) {

            try {
                XPathFactory xPathFactory = XPathFactory.newInstance();
                XPath xPath = xPathFactory.newXPath();
                XPathExpression xPathExpression = xPath.compile("/recnik/par[srpski=\"" + rec + "\"]");

                File file = new File("C:\\Users\\User\\Documents\\NetBeansProjects\\Assingment_WebService\\src\\java\\baza.xml");
                //mora da bude apsolutna putanja ,zato sto ide preko servera!!!
                InputSource is = new InputSource(new FileInputStream(file));

                Object o = xPathExpression.evaluate(is, XPathConstants.NODE);
                Node node = (Node) o;

                if (node != null) {
                    prevod = node.getFirstChild().getNextSibling().getTextContent();
                } else {
                    prevod = "Trazena rec ne postoji u bazi podataka";
                }

            } catch (XPathExpressionException ex) {
                Logger.getLogger(Translatee.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Translatee.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            prevod = "Kombinacija jezika ne postoji u bazi podataka";
        }

        return prevod;
    }

}
