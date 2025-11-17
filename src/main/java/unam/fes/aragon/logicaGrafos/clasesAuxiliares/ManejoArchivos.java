package unam.fes.aragon.logicaGrafos.clasesAuxiliares;

import unam.fes.aragon.dinamicas.listasimple.ListaSimple;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ManejoArchivos {
    Scanner scanner;
    File archivo;

    public ManejoArchivos(String rutaRelativa){
        try {
          this.archivo = new File(rutaRelativa);
          this.scanner = new Scanner(archivo);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public ListaSimple<String> lecturaDatos(){
        String [] caracteresSeparados;

        while(scanner.hasNextLine()){
            String lineaLeida = scanner.nextLine();
            caracteresSeparados = lineaLeida.split(",");

        }
        return null;
    }

}
