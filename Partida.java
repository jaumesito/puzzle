/*
CLASE PARTIDA LA CULA AGLUTINA LOS METODOS NECESARIOS PARA LA CREACION DE UN 
OBJETO PARTIDA
*/
package practicafinal;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Partida implements Serializable{
    //DECLARACION DE LOS ATRIBUTOS DE LA CLASE PARTIDA
    //declaracion de un atributo de clase tipo int para almacenar la puntuacion
    private int puntuacion;
    //declaracion de un atributo de clase tipo String para almacenar el nombre 
    //del jugador
    private String nombreJugador;
    //declaracion de un atributo de clase tipo datatTime para almacenar la
    //fecha en la que un jugador realizar una partida
    private static final DateTimeFormatter formatoFechaHora= DateTimeFormatter.
                ofPattern("dd:MM HH:mm:ss");
    //declaracion de un atributo String para almacenar la fecha
    private String fechaHora;
    
    //declaracion del CENTINELA de la clase Partida
    private final static Partida CENTINELA=new Partida("","",-1);
    
    //METODOS CONSTRUCTORES
    public Partida(String nombre){
        nombreJugador=nombre;
    }
    
    public Partida(String nombre, String fecha, int puntos){
        nombreJugador=nombre;
        puntuacion=puntos;
        fechaHora=fecha;
    }
    
    //METODOS FUNCIONALES
    //declaracion de un metodo publico de la clase Partida acabarPartida, el cual
    //escribe el objeto partida en el fichero
    public void acabarPartida(boolean acabada, int rows, int columns){
        //obtener la fecha y hora de la partida
        fechaHora=formatoFechaHora.format(LocalDateTime.now());
        
        //verificar si la partida esta acabada
        if(acabada){
            puntuacion=rows*columns;
        }else{
            puntuacion=0;
        }
    }
    
    //declaracion del metodo publico de la clase Partida toString para poder 
    //visualizar todo objeto Partida como String
    @Override
    public String toString(){
      //declaracion de una variable String para almcacenar el resultado
      String resultado="JUGADOR: "+nombreJugador+"          -FECHA: "+fechaHora+
              " -PUNTOS: "+puntuacion;
      
      //devolver resultado
      return resultado;
    }
    
    //declaracion del metodo getCentinela
    public static Partida getCentinela(){
        return CENTINELA;
    }
    public String getNombre(){
        return nombreJugador;
    }
    
    public int setPuntuacion(int puntos){
        return puntuacion=puntos;
    }
    
    //declaracion del metodo boolean esCentinela que devuelve true cuando el
    //objeto Partida leido sea el centinela
    public boolean esCentinela(){
        return this.puntuacion==CENTINELA.puntuacion;
    }
    
}
