package by.bsuir.aipos.cxfclient;

import by.bsuir.aipos.cxflib.StudentWebService;
import by.bsuir.aipos.model.StudentGroup;

import java.net.URL;
import java.util.Arrays;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;


public final class Client {

    private static final QName SERVICE_NAME
            = new QName("http://cxflib.aipos.bsuir.by/", "StudentWebService");
    private static final String URL = "http://localhost:8080/student";

    private Client() {
    }

    public static void main(String args[]) throws Exception {
        Service service = Service.create(new URL(URL), SERVICE_NAME);
        System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");

        StudentWebService studentWebService = service.getPort(StudentWebService.class);
        System.out.print(Arrays.asList(studentWebService.getAllStudent()).get(0));


    }

}
