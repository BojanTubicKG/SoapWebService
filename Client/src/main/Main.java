package main;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class Main {

    public static void main(String[] args) throws MalformedURLException {

        Scanner scn = new Scanner(System.in);
        System.out.println("UNESITE REC ZA PREVOD :");
        String rec = scn.nextLine();
        System.out.println("\nUnesite izvorni jezik :");
        String izvorniJezik = scn.nextLine();
        System.out.println("\nUnesite ciljani jezik :");
        String ciljaniJezik = scn.nextLine();

        URL url = new URL("http://localhost:8080/Assingment_WebService/TranslateeService?wsdl");
        QName qName = new QName("http://main/", "TranslateeService");
        Service service = Service.create(url, qName);
        QName port = new QName("http://main/", "TranslateePort");

        Translate t = service.getPort(port, Translate.class);

        System.out.println("\nPREVOD :");
        System.out.println(t.translate(rec, izvorniJezik, ciljaniJezik));
    }

}
