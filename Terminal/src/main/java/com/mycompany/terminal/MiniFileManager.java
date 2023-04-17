/*
Implementa un programa que funcione como una pequeña terminal Linux, con algunos comandos (simplificados) que permitan al usuario realizar
distintas operaciones de gestión de archivos. 

Los comandos que el usuario podrá ejecutar son:

    ● pwd: Muestra cual es la carpeta actual.
    ● cd <DIR>: Cambia la carpeta actual a ‘DIR’. Con .. cambia a la carpeta superior.
    ● ls: Muestra la lista de directorios y archivos de la carpeta actual (primero directorios, luego archivos, ambos ordenados alfabéticamente).
    ● ll: Como ls pero muestra también el tamaño y la fecha de última modificación.
    ● mkdir <DIR>: Crea el directorio ‘DIR’ en la carpeta actual.
    ● rm <FILE>: Borra ‘FILE’. Si es una carpeta, borrará primero sus archivos y luego la carpeta.
    Si tiene subcarpetas, las dejará intactas y mostrará un aviso al usuario.
    ● mv <FILE1> <FILE2>: Mueve o renombra ‘FILE1’ a ‘FILE2’.
    ● help: Muestra una breve ayuda con los comandos disponibles.
    ● exit: Termina el programa.


    Clase MiniTerminal: 
    Clase principal (con función main) que se encargará de interactuar con el usuario e interpretar los comandos (qué comando se pide, argumentos, etc.).
    Utilizará la segunda clase para realizar las operaciones de gestión de archivos. 
    Manejará todas las posibles excepciones.

    Clase MiniFileManager: 
    Tendrá los atributos y métodos que necesites, para realizar las distintas operaciones relacionadas con la gestión de archivos. 
    Necesitarás al menos un método por cada operación. 
    Se lanzará una excepción si se produce un error o la operación solicitada no es posible.

    Algunos ejemplos que podrías implementar:
    
    ● String getPWD(): Devuelve una cadena de texto con la carpeta actual.
    ● boolean changeDir(String dir): Cambia la carpeta actual a dir. Devuelve ‘true’ si fue posible.
    ● void printList(boolean info): Muestra una lista con los directorios y archivos de la carpeta actual. 
    Si info es ‘true’ mostrará también su tamaño y fecha de modificación.
    ● etc.
 */
package com.mycompany.terminal;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author isaac
 */
public class MiniFileManager {

    private File direccion;
    private boolean salida = false;

    public void pwd() {//muestra la ruta de la carpeta actual

        System.out.println("La ruta actual es " + direccion.getPath());

    }

    public void cd(String x) {//cambia la direccion en la que estamos posicionados

        File cosa = new File(x);
        setDireccion(cosa);
    }

    public void ls() {

        if (direccion.isFile() == true) {
            System.out.println("[A]" + direccion.getName());
        } else if (direccion.isDirectory() == true) {

            File[] archivos_en_directorio = direccion.listFiles();
            Arrays.sort(archivos_en_directorio);

            for (int contador = 0; contador < archivos_en_directorio.length; contador++) {

                if (archivos_en_directorio[contador].isDirectory() == true) {
                    System.out.println("[*]" + archivos_en_directorio[contador].getName());

                }

            }
            for (int contador = 0; contador < archivos_en_directorio.length; contador++) {

                if (archivos_en_directorio[contador].isFile() == true) {
                    System.out.println("[A]" + archivos_en_directorio[contador].getName());

                }

            }
        }

    }

    public void ll() {

        if (direccion.isFile() == true) {
            System.out.println("[A]" + direccion.getName());
            System.out.println("Tamaño = " + tamañoByte(direccion) + " Bytes");
            System.out.println("La fecha de la ultima modificacion es: " + ultimaModificacion(direccion));

        } else if (direccion.isDirectory() == true) {

            File[] archivos_en_directorio = direccion.listFiles();
            Arrays.sort(archivos_en_directorio);

            for (int contador = 0; contador < archivos_en_directorio.length; contador++) {

                if (archivos_en_directorio[contador].isDirectory() == true) {
                    System.out.println("[*]" + archivos_en_directorio[contador].getName());
                    System.out.println("Tamaño = " + tamañoByte(archivos_en_directorio[contador]) + " Bytes");
                    System.out.println("La fecha de la ultima modificacion es: " + ultimaModificacion(archivos_en_directorio[contador]));

                }

            }
            for (int contador = 0; contador < archivos_en_directorio.length; contador++) {

                if (archivos_en_directorio[contador].isFile() == true) {
                    System.out.println("[A]" + archivos_en_directorio[contador].getName());
                    System.out.println("Tamaño = " + tamañoByte(archivos_en_directorio[contador]) + " Bytes");
                    System.out.println("La fecha de la ultima modificacion es: " + ultimaModificacion(archivos_en_directorio[contador]));

                }

            }
        }
    }

    public static long tamañoByte(File x) {

        long tamaño = x.length();
        return tamaño;
    }

    public static Date ultimaModificacion(File x) {//muestra la fecha de la ultima modificacion

        long milisegundos = x.lastModified();
        Date tiempo = new Date(milisegundos);
        return tiempo;
    }

    public void mkdir(String x) {
        String aux = direccion.getPath() + "/" + x;

        File carpeta = new File(aux);
        carpeta.mkdir();
        System.out.println("Carpeta creada con exito");

    }

    public boolean rm() {
        if (direccion.isFile() == true) {
            direccion.delete();
            return true;

        } else if (direccion.isDirectory() == true) {

            File[] archivos_en_directorio = direccion.listFiles();
            Arrays.sort(archivos_en_directorio);

            for (int contador = 0; contador < archivos_en_directorio.length; contador++) {

                if (archivos_en_directorio[contador].isDirectory() == true) {
                    String ruta = direccion + "/" + archivos_en_directorio[contador].getName();
                    System.out.println(ruta);
                    File paraBorrar = new File(ruta);
                    paraBorrar.delete();

                }

            }
            for (int contador = 0; contador < archivos_en_directorio.length; contador++) {

                if (archivos_en_directorio[contador].isFile() == true) {

                    String ruta = direccion + "/" + archivos_en_directorio[contador].getName();
                    System.out.println(ruta);
                    File paraBorrar = new File(ruta);
                    paraBorrar.delete();

                }

            }

            return true;

        } else {
            return false;
        }

    }

    public void mv(String x, String y) throws Exception {//poner como boolean

        File origen = new File(x);
        File destino = new File(y);
        origen.renameTo(destino);
        System.out.println("Se ha realizado el cambio");

    }

    public static void help() {
        System.out.println("El comando pwd te permite ver cual es la carpeta actual");
        System.out.println("El comando cd permite cambiar la capeta en la que estamos posicionados por otra diferente");
        System.out.println("El comando ls nos muestra la lista de directorios y archivos de la carpeta actual");
        System.out.println("El comando ll nos muestra lo mismo que ls + el tamaño y la fecha de la ultima modificacion");
        System.out.println("El comando mkdir crea el directorio en la carpeta actual");
        System.out.println("El comando rm borra el fichero,si es una carpeta borrara los ficheros de dentro,ademas si tiene subcarpetas avisara al usuario");
        System.out.println("El comando mv mueve o renombra ");
    }

    public void exit() {
        System.out.println("Se ha seleccionado salir");
        setSalida(true);

    }

    public File getDireccion() {
        return direccion;
    }

    public void setDireccion(File direccion) {
        this.direccion = direccion;
    }

    public boolean isSalida() {
        return salida;
    }

    public void setSalida(boolean salida) {
        this.salida = salida;
    }

    public MiniFileManager(File direccion) {
        this.direccion = direccion;
    }

}
