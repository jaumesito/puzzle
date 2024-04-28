/*
DECLARACION DE LA CLASE PartidaObjetoFicherosEscritura QUE AGLUTINA LOS 
METODOS NECESARIOS PARA PODER ESCRIBIR OBJETOS PARTIDA EN UN FICHERO
 */
package practicafinal;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class PartidaObjetoFicherosEscritura {
    //ATRIBUTOS
    //declaración objeto ObjectOutputStream para establecer enlace con el fichero
    private ObjectOutputStream fichero=null;
    
    
    //MÉTODOS DE LA CLASE PartidaObjetoFicherosEscritura
    //declaracion del metodo constructor de la clase PartidaObjetosFicheroEscritura
    //que permite establecer enlace con un fichero
    public PartidaObjetoFicherosEscritura(String nombreFichero) throws FileNotFoundException, IOException {
        //instanciacion del objeto fichero
        fichero= new ObjectOutputStream(new BufferedOutputStream
                    (new FileOutputStream(nombreFichero)));         
    }
    

    
    //declaracion del metodo escritura que permite la escritura a nivel de 
    //objetos dentro de un fichero
    public void escritura(Partida partida) throws IOException {
        //escritura del objeto Partida en el fichero
        fichero.writeObject(partida); 
    }
    
    //declaracion del metodo cerrarEnlaceFichero que permite cerrar el enlace
    //creado 
    public void cerrarEnlaceFichero() throws IOException {     
        if (fichero!=null) {
            fichero.close();
        }
    }
}
