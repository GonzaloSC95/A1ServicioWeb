package com.devops.A1ServicioWeb.Backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gonzalo
 */
@Component
public class ManejadorDeStringsYFicheros {

    private String fichero = "registro.txt";

    ////CREACION DEL FICHERO "registro.txt"
    /**
     *
     * @throws IOException
     */
    public void creacionDeFichero() throws IOException {
        File file = new File(fichero);
        if (file.exists()) {
            System.out.println("\nFichero \"registro.txt\" creado: "
                    + file.createNewFile());
        } else {
            System.out.println("\nFichero \"registro.txt\" creado: "
                    + file.createNewFile());
        }
    }

    ////GUARDAR STRING EN FICHERO "registro.txt"
    /**
     *
     * @param txt
     */
    public void guardarEnfichero(String txt) {

        File file = new File(fichero);

        try (FileWriter f = new FileWriter(file, true);
                BufferedWriter buffer = new BufferedWriter(f)) {
            buffer.write(txt);
            buffer.newLine();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    ///BUSCAR EN FICHERO Y CONTAR OCURRENCIAS
    /**
     *
     * @param txt
     * @return int
     */
    public int contarEnFichero(String txt) {
        int contador = 0;
        ///////////////////////////////////////////
        try (FileReader file = new FileReader(fichero);//
                BufferedReader buffer = new BufferedReader(file);) {
            ///////LEEMOS LA PRIMERA LINEA DEL FICHERO Y LE QUITAMOS LOS ACENTOS//////////////
            String fraseSinAcentos = normalizer(buffer.readLine());
            //////LE QUITAMOS LOS ACENTOS A LA CADENA INTRODUCIDAD POR EL USUARIO///////////////
            String cadenaSinAcentos = normalizer(txt);
            //COMPROBAMOS SI LA CADENA INTRODUCIDA POR EL USUARIO TIENE O NO MAYUSCULAS///////////
            if (isMayuscula(cadenaSinAcentos) == true) {
                //SI TIENE MAYUSCULAS/////////////////////////////////////
                String fraseSinAcentosYenMayusculas = fraseSinAcentos.toUpperCase();
                String cadenaSinAcentosYenMayusculas = cadenaSinAcentos.toUpperCase();

                while (fraseSinAcentosYenMayusculas != null) {

                    if (fraseSinAcentosYenMayusculas.contains(cadenaSinAcentosYenMayusculas)) {
                        contador++;
                    }
                    System.out.println(fraseSinAcentosYenMayusculas);
                    fraseSinAcentosYenMayusculas = normalizer(buffer.readLine()).toUpperCase();
                    ////////////////////////
                }

            } else {
                //SI SOLO SON MINUSCULAS///////////////////////////////////////////
                String fraseSinAcentosYenMinusculas = fraseSinAcentos.toLowerCase();
                String cadenaSinAcentosYenMinusculas = cadenaSinAcentos.toLowerCase();
                while (fraseSinAcentosYenMinusculas != null) {

                    if (fraseSinAcentosYenMinusculas.contains(cadenaSinAcentosYenMinusculas)) {
                        contador++;
                    }
                    System.out.println(fraseSinAcentosYenMinusculas);
                    fraseSinAcentosYenMinusculas = normalizer(buffer.readLine()).toLowerCase();
                    ////////////////////////
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        System.out.println("\nCoincidencias de la string: "
                + txt + ",en el fichero \"registro.txt\" = " + contador);
        return contador;
    }

    //ELIMINACIÓN DE ACENTOS
    /**
     *
     * @param aux
     * @return String
     */
    public String normalizer(String aux) {
        String mystring = aux;
        mystring = Normalizer.normalize(mystring, Normalizer.Form.NFD);
        mystring = mystring.replaceAll("[^\\p{ASCII}]", "");
        return mystring;
    }

    //COMPROBAR MAYUSCULAS O MNUSCULAS
    /**
     *
     * @param txt
     * @return
     */
    public boolean isMayuscula(String txt) {
        ////////////////////////
        for (int i = 0; i < txt.length(); i++) {
            if (Character.isUpperCase(txt.charAt(i))) {
                return true;
            }
        }
        return false;//TRUE = MAYUSCULAS || FALSE = MINUSCULAS
    }

}
