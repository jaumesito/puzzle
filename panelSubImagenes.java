/*
DECLARACION DE LA CLASE panelSubImagenes que a través de ellas se instancien objetos
panelSubImagenes. Un objeto panelSubImagenes representará el contenedor donde
aparecen las diferentes subdivisiones en las que ha sido subdividida la imagen a
solucionar.
 */
package practicafinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class panelSubImagenes extends JPanel {
   private BufferedImage imagen;
    private int numFilas;
    private int numColumnas;
    private int anchoSubImagen;
    private int altoSubImagen;
    private int[][] puzzle;
    private int filaSeleccionada;
    private int columnaSeleccionada;
    private Random generador=new Random();
    private int numAleatorio;
    private boolean imagenSeleccionada=false;
    
    //metodo constructor de la clase panelSubImagenes
    //en el constructor pasamos por parametro la imagen, el numero de filas y
    //el numero de columnas para iniciar el puzzle y gestionar el evento del
    //ratón
    public panelSubImagenes(BufferedImage imagen, int numFilas, int numColumnas){
        this.imagen = imagen;
        this.numFilas = numFilas;
        this.numColumnas = numColumnas;
        this.setLayout(new GridLayout(numFilas,numColumnas));       

       
        anchoSubImagen = imagen.getWidth() / numColumnas;
        altoSubImagen = imagen.getHeight() / numFilas;
        imagen.getScaledInstance(anchoSubImagen, altoSubImagen, 0);
        
        puzzle = new int[numFilas][numColumnas];
        inicializarPuzzle();
        addMouseListener(new gestorRaton());
    }
    //metodo privado de la clase panelSubImagenes que inicia el puzzle y 
    //desordena el puzzle 
    private void inicializarPuzzle() {
    //declaracion array de int 
    int numsAleatorios[]=new int[numFilas*numColumnas];
    //declarcion boolean repetido
    boolean repetido=false;
    int contador = 1;
    for (int fila = 0; fila < numFilas; fila++) {
        for (int columna = 0; columna < numColumnas; columna++) {
            //Asignar un número único a cada subImagen(excepto el espacio en blanco)
            puzzle[fila][columna] = contador;
            contador++;
        }
    }
        puzzle[numFilas - 1][numColumnas - 1] = 0;

    //desordenar Puzzle
    //obtener indice a colocar la imagen
    for(int i=0;i<numsAleatorios.length;i++){
        do{
            repetido=false;
            numAleatorio=generador.nextInt(numFilas*numColumnas)+1;
            for(int j=0;j<i;j++){
                if (numsAleatorios[j]==numAleatorio) repetido=true;
            }
        }while(repetido);
        numsAleatorios[i]=numAleatorio;
    }
        for(int i=0;i<numsAleatorios.length;i++){
            puzzle[i/numColumnas][i%numColumnas]=numsAleatorios[i];
        }
        this.repaint();
    }

    //metodo privado de la clase panelSubImagenes que intercambia una subImagen
    //con otra pasando por parametro la fila y la columa
    private void intercambiarSubImagenes(int fila, int columna) {
        //Intercambiar las subimágenes en la matriz del puzzle
        int temp = puzzle[fila][columna];
        puzzle[fila][columna] = puzzle[filaSeleccionada][columnaSeleccionada];
        puzzle[filaSeleccionada][columnaSeleccionada] = temp;
    }
    //metodo booleano de la clase panelSubImagenes que verifica si el puzzle se 
    //ha resuelto o no
    public boolean puzzleResuelto() {
        int contador = 1;
        for (int fila = 0; fila < numFilas; fila++) {
            for (int columna = 0; columna < numColumnas; columna++) {
                   if(puzzle[fila][columna]!=contador){
                       return false;
                   }
                   contador++;
            }
        }
        return true;
    }
    
   @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (int fila = 0; fila < numFilas; fila++) {
            for (int columna = 0; columna < numColumnas; columna++) {
                int x = columna * anchoSubImagen;
                int y = fila * altoSubImagen;
                int subImagenTamaño = puzzle[fila][columna];

                if (subImagenTamaño != 0) {
                    //Calcular las coordenadas de la subimagen en la imagen original
                    int columnaImagen = (subImagenTamaño-1) % numColumnas;
                    int filaImagen = (subImagenTamaño-1) / numColumnas;
                    //instanciar la subImagen
                    SubImagen subImagen=new SubImagen(imagen,columnaImagen * anchoSubImagen, filaImagen * altoSubImagen,anchoSubImagen, altoSubImagen);
                    //Obtener la subimagen correspondiente del BufferedImage
                    BufferedImage subImagenBuffered = subImagen.getSubImagen();
                    //Dibujar la subimagen en la posición adecuada
                    g.drawImage(subImagenBuffered.getScaledInstance(anchoSubImagen, altoSubImagen,0)
                    , x, y, null);
                }
            }
        }

        //visualización malla de subdivisión de la imagen en partes
        int y = 0;
        for (int i = 0; i < numFilas; i++) {
            int x = 0;
            for (int j = 0; j < numColumnas; j++) {
                Rectangle2D.Float r = new Rectangle2D.Float(x, y, anchoSubImagen, altoSubImagen);
                g2d.draw(r);
                x += anchoSubImagen;
            }
            y += altoSubImagen;
        } 
    } 
    //Gestor de eventos de ratón
    private class gestorRaton implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent eventoRaton) {
            //NO SE HACE NADA
        }

        @Override
        public void mouseEntered(MouseEvent eventoRaton) {
            //NO SE HACE NADA
        }

        @Override
        public void mouseExited(MouseEvent eventoRaton) {
            //NO SE HACE NADA
        }

        @Override
        public void mousePressed(MouseEvent eventoRaton) {
                int fila = eventoRaton.getY() / altoSubImagen;
                int columna = eventoRaton.getX() / anchoSubImagen;
                
                if(!imagenSeleccionada){
                    //Seleccionar la primera subimagen
                    filaSeleccionada = fila;
                    columnaSeleccionada = columna;
                    imagenSeleccionada=true;
                }else{
                    //Intercambiar las subimágenes seleccionadas
                    intercambiarSubImagenes(fila, columna);
                    revalidate();
                    repaint();
                    imagenSeleccionada=false;
                    //Verificar si el puzzle está resuelto
                    if (puzzleResuelto()) {
                        JOptionPane.showMessageDialog(null, "¡¡¡ ENHORABUENA !!! LO HAS CONSEGUIDO HAS OBTENIDO "+numFilas*numColumnas+" PUNTOS");
                    }
                }
        }

        @Override
        public void mouseReleased(MouseEvent eventoRaton) {
            //NO SE HACE NADA
        }
    }
    
}
