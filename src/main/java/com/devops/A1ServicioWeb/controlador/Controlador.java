package com.devops.A1ServicioWeb.controlador;

import com.devops.A1ServicioWeb.Backend.ManejadorDeStringsYFicheros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gonzalo
 */
@RestController
public class Controlador {

    @Autowired
    private ManejadorDeStringsYFicheros manejadorDeStrings;

    //MÉTODO QUE DEVUELVE EL NÚMERO DE OCURRENCIAS DE 
    //UNA CADENA DE TEXTO INTRODUCIDA POR EL USUARIO 
    //COMO VARIABLE EN LA CABECERA DE LA REQUEST
    //http://localhost:12345/
    /**
     *
     * @param txt
     * @return ResponseEntity
     */
    @GetMapping(path = "/{txt}", produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> getText(@PathVariable("txt") String txt) {
        System.out.println("\nPalabra consultada " + txt);
        int numeroDeOcurrencias = manejadorDeStrings.contarEnFichero(txt);
        return new ResponseEntity<>(String.valueOf(numeroDeOcurrencias), HttpStatus.OK);

    }

    //MÉTODO POR EL QUE EL USUARIO AÑADE UNA CADENA DE TEXTO
    //AL FICHERO "registro.txt" (PERSISTENCIA, DENTRO DEL BODY
    //DE LA REQUEST
    //http://localhost:12345/newstring
    /**
     *
     * @param txt
     * @return ResponseEntity
     */
    @PostMapping(path = "newstring", produces = MediaType.ALL_VALUE,
            consumes = MediaType.ALL_VALUE)
    public ResponseEntity<String> postText(@RequestBody String txt) {
        System.out.println("\nPalabra a insertar en fichero \"registro.txt\" : " + txt);
        manejadorDeStrings.guardarEnfichero(txt);
        return new ResponseEntity<>(txt, HttpStatus.OK);

    }

}
