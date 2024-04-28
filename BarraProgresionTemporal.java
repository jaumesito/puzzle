/*
CLASE BarraProgresionTemporal PARA LA GENERACIÓN DE OBJETOS JProgressBar
 */
package practicafinal;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JProgressBar;

public class BarraProgresionTemporal extends JProgressBar {
    //DECLARACIÓN ATRIBUTOS
    private int valorMinimo=0;
    private int valorMaximo=100;
    private final int ANCHO_BARRA=40;

    //declaración método metodoPrincipal
    public  BarraProgresionTemporal(int dimension) {
        super();
        //ASIGNACIÓN VALORES MÍNIMO Y MÁXIMO A LA JProgressBar barraTemporal
        setMinimum(valorMinimo);
        setMaximum(dimension);
        //ASIGNACIÓN VALOR INICIAL A LA JProgressBar barraTemporal
        setValue(0);
        //ACTIVACIÓN VISUALIZACIÓN VALOR EN LA JProgressBar barraTemporal
        setStringPainted(true);
        //DIMENSIONAMIENTO JProgressBar barraTemporal
        setPreferredSize(new Dimension(dimension,40));
        //ASIGNACIÓN COLORES DE FONDO Y TRAZADO JProgressBar barraTemporal
        setForeground(Color.RED);
        setBackground(Color.YELLOW);
    }

    //declaracion de un metodo publico de la clase BarraProgresionTemporal el 
    //cual lleva a cabo la incrementacion del porcentaje de la barraTemporal
    public void incrementar(int valor){
        //asignacion del valor a la barra de progresion
        this.setValue(this.getValue()+valor);
    }
    
    public boolean esMaximo(){
        return this.getMaximum()==valorMaximo;
    }
}


