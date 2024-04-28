/*
DECLARACION DE LA CLASE PartidaObjetoFicherosLectura QUE AGLUTINA LOS 
METODOS NECESARIOS PARA PODER LEER OBJETOS PARTIDA DESDE UN FICHERO
*/
package practicafinal;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class PartidaObjetoFicherosLectura {
 //ATRIBUTOS
    //declaración objeto ObjectOutputStream para establecer enlace con el fichero
    private ObjectInputStream fichero=null;
    
    
    //MÉTODOS DE LA CLASE PartidaObjetoFicheroLectura
    //declaracion del metodo constructor de la clase PartidaObjetoFicheroLectura
    //que permite establecer enlace con un fichero
    public PartidaObjetoFicherosLectura(String nombreFichero) throws FileNotFoundException, IOException {
        fichero = new ObjectInputStream(new BufferedInputStream
                (new FileInputStream(nombreFichero)));       
    }
    
    //declaracion del metodo lectura de la clase PartidaObjetoFicheroLectura que 
    //permite leer objetos lectura desde un fichero
    public Partida lectura() throws ClassNotFoundException, IOException { 
        //declaracion de un objeto Partida para poder almacenar el objeto Partida
        //leido desde el fichero 
        Partida partida;
        
        //ACCIONES       
        //lectura objeto Partida leido desde el fichero
        partida=(Partida) fichero.readObject();      
        //devolución objeto Partida leido desde el fichero
        return partida; 
    }

    
    //declaracion del metodo cerrarEnlaceFichero que permite cerrar el enlace
    //creado 
    public void cerrarEnlaceFichero() throws IOException {
        if (fichero!=null) {
            fichero.close();
        }
    }  
}
