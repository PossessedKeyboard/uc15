/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UTIL;

/**
 *
 * @author DELL
 */
public class Session {
     private static int    idUsuario;
    private static String perfil;
    private static String nomeUsuario;
 
    public static int getIdUsuario() {
        return idUsuario;
    }
 
    public static void setIdUsuario(int id) {
        idUsuario = id;
    }
 
    public static String getPerfil() {
        return perfil;
    }
 
    public static void setPerfil(String perfil) {
        Session.perfil = perfil;
    }
 
    public static String getNomeUsuario() {
        return nomeUsuario;
    }
 
    public static void setNomeUsuario(String nome) {
        Session.nomeUsuario = nome;
    }
 
    public static void encerrar() {
        idUsuario   = 0;
        perfil      = null;
        nomeUsuario = null;
    }
}
