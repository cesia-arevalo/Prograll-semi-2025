package com.example.miprimeraplicacion;

import java.util.Base64;
public class utilidades {
    static String url_consulta = "http://192.168.1.5:5984/tienda en android/_design/tienda en android/_view/tienda en android";
    static String url_mto = "http://192.168.1.5:5984/agenda";
    static String user = "admin";
    static String passwd = "12345";
    static String credencialesCodificadas = Base64.getEncoder().encodeToString((user + ":" + passwd).getBytes());
    public String generarUnicoId(){
        return java.util.UUID.randomUUID().toString();
    }
}

