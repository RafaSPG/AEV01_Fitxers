import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        String directorio = "";
        int menu = 0;
        Main main = new Main();
        Boolean dirCorrecto = false;
        File directorioFile ;


        do {
            System.out.println("Introduce la ruta del directorio: ");directorio = teclado.nextLine();
            directorioFile = new File(directorio);

            if (directorioFile.exists()){

                if (directorioFile.canExecute() && directorioFile.canWrite() && directorioFile.canRead()) {

                    dirCorrecto = true;

                } else {

                    System.out.println("No tienes los suficientes permisos sobre este directorio");

                }

            } else {

                System.out.println("El directorio no existe");

            }

        } while (!dirCorrecto);




        while (menu != 6) {

        System.out.println("Elige que quieres hacer... debes introducir un numero");
        System.out.println("1 - Mostrar informacion");
        System.out.println("2 - Crear carpeta");
        System.out.println("3 - Crear fichero");
        System.out.println("4 - Eliminar");
        System.out.println("5 - Renombrar");
        System.out.println("6 - Salir");
        menu = teclado.nextInt();
        teclado.nextLine();

        switch (menu) {

            case 1:

                main.getInformacion(directorioFile);

                break;

            case 2:

                main.crearCarpeta(directorioFile);

                break;

            case 3:

                main.crearFichero(directorioFile);

                break;

            case 4:

                main.eliminar(directorioFile);

                break;

            case 5:

                if (directorioFile.isFile() && directorioFile.exists()) {

                    File nuevoFichero;
                    String nombre = "";

                    System.out.println("Introduce el nombre del nuevo fihero");
                    nombre = teclado.nextLine();

                    nombre = directorio + nombre;

                    nuevoFichero = new File(nombre);

                    main.renombrar(directorioFile, nuevoFichero);

                } else {

                    System.out.println("No existe como fichero");

                }



                break;

            case 6:

                menu = 6;

                break;

        }





        }

    }

    public void getInformacion (File dir) {

        System.out.println("---------------------------------------------------------------");

        System.out.println("Nombre: " + dir.getName());
        System.out.println();

        System.out.print("Tipo: ");
        if (dir.isFile()) System.out.println("fichero");
        if (dir.isDirectory()) System.out.println("directorio");
        System.out.println();

        System.out.println("Ruta completa: " + dir.getPath());
        System.out.println();

        long ultimaMod = dir.lastModified();
        Date fechaMod = new Date (ultimaMod);
        System.out.println("Fecha ultima modificacion: " + fechaMod);
        System.out.println();


        System.out.print("Es directorio esta oculto? ");
        if (dir.isHidden()) {
            System.out.println("El directorio/fichero esta oculto");
        } else System.out.println("El directorio/fichero no esta oculto");
        System.out.println();

        if (dir.isFile()) System.out.println("El tamaño del fichero es: " + dir.length() + " bytes");
        System.out.println();

        if (dir.isDirectory()){

            System.out.println("Els elements que conte son: ");

            File[] listaFicheros= new File[dir.listFiles().length];

            listaFicheros = dir.listFiles();

            for (int i = 0; i < listaFicheros.length;i++) {

                System.out.println(listaFicheros[i]);

            }
            System.out.println();

            System.out.println("El espacio libre es de: " + dir.getFreeSpace()/1024/1024/1024 + "GB");
            System.out.println("El espacio disponible es de: " + dir.getUsableSpace()/1024/1024/1024 + "GB");
            System.out.println("El espacio total es de: " + dir.getTotalSpace()/1024/1024/1024 + "GB");



        }


        System.out.println("---------------------------------------------------------------");

    }
    public void crearCarpeta (File dir) {

        if (dir.mkdirs()) {

            System.out.println("Direcotrio creado existosamente");

        }else {

            System.out.println("Directorio no creado");

        }

    }
    public void crearFichero (File dir) {

        try {
            if (dir.createNewFile()) {
                System.out.println("Fichero creado exitosamente");
            } else {
                System.out.println("El fichero ya existe");
            }
        } catch (IOException e) {
            System.out.println("Error al crear el fichero");
        }


    }
    public void eliminar (File dir) {

        if (dir.delete()) {
            System.out.println("Fichero eliminado exitosamente");
        } else {
            System.out.println("Error al eliminar el fichero");
        }

    }
    public void renombrar (File dir, File renombre) {

        if (dir.renameTo(renombre)) {
            System.out.println("Fichero renombrado exitosamente");
        } else {
            System.out.println("Error al renombrar el fichero");
        }

    }



}