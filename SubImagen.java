/*
DECLARACION DE LA CLASE SubImagen. Un objeto SubImagen deberá representar
una subdivisión en las que la imagen a acertar haya sido subdividida.

 */
package practicafinal;

import java.awt.image.BufferedImage;


public class SubImagen {
    //ATRIBUTOS DE LA CLASE SUBIMAGEN
    //declaracion de una componente BufferedImage para alamcenar la imagen con
    //las correspondientes subdivisiones
    private BufferedImage imagenSalida;


    //METODO CONTRUCTOR DE LA CLASE SubImagen
    public SubImagen(BufferedImage imagen, int X, int Y, int Z, int W){
        //instanciacion de la imagenSalida
        imagenSalida=imagen.getSubimage(X,Y, Z, W);
    }

    public BufferedImage getSubImagen(){
        return imagenSalida;
    }
     public void setSubImagen(BufferedImage image){
         imagenSalida=image;
     }
}