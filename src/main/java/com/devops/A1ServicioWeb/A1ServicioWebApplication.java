package com.devops.A1ServicioWeb;

import com.devops.A1ServicioWeb.Backend.ManejadorDeStringsYFicheros;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Gonzalo
 */
@SpringBootApplication
public class A1ServicioWebApplication {

    //MÉTODO MAIN
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(A1ServicioWebApplication.class, args);
        try {
            new ManejadorDeStringsYFicheros().creacionDeFichero();
            System.out.println("\nPara poder ejecutar tus request, necesitas abrir el programa \"Postman\", u otro similar.");
            System.out.println("GET PATH: http://localhost:12345/\"cadena de texto a buscar\"");
            System.out.println("POST PATH: http://localhost:12345/newstring\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
