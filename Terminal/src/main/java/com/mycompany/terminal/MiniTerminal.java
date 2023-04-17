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
    public static void main(String[] args) {
        // TODO code application logic here

        Scanner lector = new Scanner(System.in);

        boolean salida = false;
        int opcion = 10;

        System.out.println("Introduce la direccion inicial");
        File cosa = new File(lector.nextLine());

        MiniFileManager comandos = new MiniFileManager(cosa);//crea acceso al 

        while (comandos.isSalida() == false) {

            System.out.println("Bienvenido al terminal.Seleccione una opcion");
            System.out.println("");
            System.out.println("1. pwd ");
            System.out.println("2. cd ");
            System.out.println("3. ls ");
            System.out.println("4. ll ");
            System.out.println("5. mkdir ");
            System.out.println("6. rm ");
            System.out.println("7. mv ");
            System.out.println("8. help ");
            System.out.println("9. exit ");

            int seleccion = lector.nextInt();

            switch (seleccion) {

                case 1://pwd

                    comandos.pwd();
                    break;

                case 2://cd
                    System.out.println("Introduce la nueva direccion");
                    comandos.cd(lector.next());
                    lector.nextLine();
                    System.out.println("Se ha cambiado la posicion");
                    break;

                case 3://ls                
                    comandos.ls();
                    break;

                case 4://ll 
                    comandos.ll();
                    break;

                case 5://mkdir 

                    System.out.println("Introduce el nombre del directorio a crear");
                    comandos.mkdir(lector.next());

                    break;

                case 6://rm 
                    comandos.rm();
                    System.out.println("Se han borrado los archivos");
                    break;

                case 7://mv  
                    System.out.println("Introduce la direcciona del fichero que quieres renombrar y despues la direccion o nombre que le quieres dar");
                    comandos.mv(lector.next(), lector.next());
                    break;

                case 8://help

                    MiniFileManager.help();
                    break;

                case 9://exit
                    comandos.exit();
                    break;

            }
        }

    }

}
///Ruta para probar --> /3º_Ev/Programacion/EjFicheros/Documentos/Mis_Cosas/Fotografias
///Ruta para probar --> \3º_Ev\Programacion\EjFicheros\Documentos\Mis_Cosas\Fotografias
//FALTAN EXCEPCIONES
A
