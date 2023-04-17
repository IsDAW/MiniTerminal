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
import java.util.Scanner;

/**
 *
 * @author isaac
 */
public class MiniTerminal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        Scanner lector = new Scanner(System.in);

        boolean salida = false;
        int opcion = 10;
        boolean rutaOk = false;

        System.out.println("Introduce la direccion inicial");
        while (rutaOk == false) {//fuerza a poner una ruta valida
            File cosa = new File(lector.nextLine());
            if (cosa.exists() == true) {
                System.out.println("RUTA CORRECTA");
                System.out.println("----------------------------------------------------------------------");
                MiniFileManager comandos = new MiniFileManager(cosa);//crea acceso a la clase
                rutaOk = true;

                while (comandos.isSalida() == false) {
                    System.out.println("Bienvenido al terminal.Introduce uno de los siguientes comandos :");
                    System.out.println("");
                    System.out.println("pwd");
                    System.out.println("cd <dir>");
                    System.out.println("ls");
                    System.out.println("ll");
                    System.out.println("mkdir <dir>");
                    System.out.println("rm <file>");
                    System.out.println("mv <file1> <file2>");
                    System.out.println("help");
                    System.out.println("exit");
                    System.out.println("----------------------------------------------------------------------");
                    String seleccion = lector.next();
                    String opcion1 = "";
                    String opcion2 = "";

                    if ("cd".equals(seleccion) || "mkdir".equals(seleccion) || "rm".equals(seleccion)) {
                        opcion1 = lector.next();
                    } else if ("mv".equals(seleccion)) {
                        opcion1 = lector.next();
                        opcion2 = lector.next();
                    }

                    switch (seleccion) {
                        case "pwd"://pwd
                            comandos.pwd();
                            System.out.println("----------------------------------------------------------------------");
                            break;
                        case "cd"://cd
                            System.out.println("¿Se ha cambiado la posicion?: " + comandos.cd(opcion1));
                            System.out.println("----------------------------------------------------------------------");
                            break;
                        case "ls"://ls
                            comandos.ls();
                            System.out.println("----------------------------------------------------------------------");
                            break;
                        case "ll"://ll
                            comandos.ll();
                            System.out.println("----------------------------------------------------------------------");
                            break;
                        case "mkdir"://mkdir
                            System.out.println("¿Se ha creado la carpeta?: " + comandos.mkdir(opcion1));
                            System.out.println("----------------------------------------------------------------------");
                            break;
                        case "rm"://rm
                            System.out.println("¿Se han borrado los archivos posibles?: " + comandos.rm(opcion1));
                            System.out.println("----------------------------------------------------------------------");
                            break;
                        case "mv"://mv
                            System.out.println("¿Se ha movido o renombrado?: " + comandos.mv(opcion1, opcion2));
                            System.out.println("----------------------------------------------------------------------");
                            break;
                        case "help"://help
                            MiniFileManager.help();
                            System.out.println("----------------------------------------------------------------------");
                            break;
                        case "exit"://exit
                            comandos.exit();
                            System.out.println("");
                            break;
                        default://se muestra cuando se ponga otra opcion diferente a las anteriores mencionadas
                            System.out.println("Introduce una opcion valida");
                            System.out.println("----------------------------------------------------------------------");
                            break;
                    }//fin switch
                }

            } else if (cosa.exists() == false) {
                System.out.println("RUTA INCORRECTA");
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Introduce otra ruta");
            }
        }

    }

}
