

package practicafinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class LTGraficos extends JDialog {
    //atributo array de componentes JTextField que representarán los valores
    //introducidos a través del contenedor JDialog
    private JTextField [] datos;
    //atributo entero que representa el número de valores a introducir a traves
    //del contenedor JDialog
    private int numeroValores=0;

    //MÉTODO CONSTRUCTOR
    //el parámetro frame representa el contenedor JFrame desde el que se le ha
    //llevado a cabo la instanciación, y el parámetro campos representan los
    //literales de los conceptos demandados para introducir     
    public LTGraficos(JFrame frame,String [] campos) {       
        super(frame, true);  
        setTitle("INTRODUCCIÓN DATOS");  
        
        //el número de componentes de campos (número de conceptos) representa
        //el número de valores a introducir
        numeroValores=campos.length;

        //declaración del panel de contenidos del contenedor JDialog
        Container panelContenidos=getContentPane();
        //asignación al panel de contenidos del administrador de layout
        //GridLayout estructurado en (numeroValores+1) filas y una
        //columna
        panelContenidos.setLayout(new GridLayout(numeroValores+1,1));
        
        
        //instanciación del array datos con el número de componentes igual
        //al número de valores, haciendo corresponder una componente JTextField
        //por cada valor a introducir
        datos=new JTextField[numeroValores];
        //de forma análoga se declara e instancia un array de componentes JLabel
        //para, a través de ellas, visualizar en el contenedor JDialog los
        //literales de los conceptos a introducir, estableciendose la relación
        //de una componente JLabel para cada concepto
        JLabel [] conceptos=new JLabel[numeroValores];
        //declaració de un array de contenedores JPanel, en número igual al 
        //número de valores, para introducir en cada uno de ellos, una
        //de las componentes JLabel, del array conceptos, y una de las
        //componentes JTextField del array datos
        JPanel [] paneles=new JPanel[numeroValores];

        
        int maximo=0;
        //bucle de instanciación de las componentes del array de JLabel conceptos,
        //del array de JTextField datos, del array de contenedores JPanel paneles
        //e introducción en cada uno de estos contenedores su correspondiente
        //etiqueta JLabel y componente JTextField, para acabar introduciendo
        //el contenedor JLabel en el panel de contenidos del contenedor JDialog
        for (int indice=0;indice<datos.length;indice++) {
            if (maximo<campos[indice].length()) {
                maximo=campos[indice].length();
            }
            //instanciación de los diferentes arrays 
            conceptos[indice]=new JLabel("  "+campos[indice]);
            conceptos[indice].setForeground(Color.white);
            conceptos[indice].setFont(new Font("Arial",Font.BOLD,18));
            datos[indice]=new JTextField();
            datos[indice].setText("");
            datos[indice].setHorizontalAlignment(JTextField.RIGHT);
            paneles[indice]=new JPanel();
            paneles[indice].setBackground(Color.black);
            paneles[indice].setLayout(new GridLayout());
            //introducción en el contenedor JPanel indice-ésimo de la 
            //componente JLabel indice-ésima y de la componente JTextField
            //indice-ésima
            paneles[indice].add(conceptos[indice]);
            paneles[indice].add(datos[indice]);
            //introducción del contenedor JPanel indice-ésimo en el
            //panel de contenidos del contenedor JDialog
            panelContenidos.add(paneles[indice]);
        }
        
        //declaración componente JButton salirBoton
        JButton salirBoton=new JButton("CONFIRMAR");
        //introducción de la componente JButton salirBoton en el panel
        //de contenidos del contenedor JDialog
        panelContenidos.add(salirBoton);
        //asignación e implementación del gestor de eventos asociado a la
        //componente JButton salirBoton
        salirBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    //pone a FALSE la visibilidad del contenedor JDialog
                    setVisible(false);
            }
        });
        //dimensionamiento contenedor JDialog en función del número de valores a
        //introducir
        if (numeroValores<=2) {
            setSize((maximo+5)*25,numeroValores*55);
        }
        else {
            setSize((maximo+5)*25,numeroValores*35);
        }
        //centrar ventana contenedor JDialog en el centro de la ventana de la aplicación
        //desde donde se ha instanciado
        setLocationRelativeTo(frame);
        //pone a TRUE la visibilidad del contenedor JDialog
        setVisible(true);
    }
    
    //metodo CONSTRUCTOR para la visualizacion del historial selectivo
    //el parámetro frame representa el contenedor JFrame desde el que se le ha
    //llevado a cabo la instanciación, y el parámetro campo representa el nombre
    //del jugador a buscar
    public LTGraficos(JFrame frame){
       super(frame, true);
       setTitle("input");
       
        //el número de componentes de campos (número de conceptos) representa
        //el número de valores a introducir
        numeroValores=1;    
        
        //declaración del panel de contenidos del contenedor JDialog
        Container panelContenidos=getContentPane();
        //asignacion de un gestor de Layout al panelContenidos
        panelContenidos.setLayout(new BorderLayout());
        //asignacion del fondo del panelContenidos a negro
        panelContenidos.setBackground(Color.BLACK);
        
        //declaracion de una componente JPanel
        JPanel texto=new JPanel();
        //asignacion de un gestor de Layout
        texto.setLayout(new GridLayout(3,1));
        //asignacion del color del fondo
        texto.setBackground(Color.BLACK);
        //introducir a panelContenidos
        panelContenidos.add(texto,BorderLayout.CENTER);
        
        //declaracion de una componente JPanel
        JPanel botones=new JPanel();
        //asignacion de un gestor de Layout
        botones.setLayout(new FlowLayout());
        //asignacion del color del fondo
        botones.setBackground(Color.BLACK);
        //introducir a panelContenidos
        panelContenidos.add(botones,BorderLayout.SOUTH);
        
        //declaracion de una JLabel para un icono
        JLabel icono=new JLabel(new ImageIcon("icono.png"));
        //introducir icono al panelContenidos
        panelContenidos.add(icono,BorderLayout.WEST);
        
        //declaracion de las JLabel para el texto
        //declaracion un array JLabel
        JLabel[]textos=new JLabel[2];
        //declaracion de un array de String para almacenar los Strings de los
        //mensajes
        String []mensajes={"HISTORIAL JUGADOR","INTRODUCE EL NOMBRE DEL JUGADOR"};
        //bucle de iteracion de los JLabel
        for(int indice=0;indice<textos.length;indice++){
            //instanciar JLabels
            textos[indice]=new JLabel(mensajes[indice]);
            //asignar del color del trazado
            textos[indice].setForeground(Color.YELLOW);
            //asignar la fuente
            textos[indice].setFont(new Font("Arial",Font.BOLD,15));
            //introducir los textos al panel de texto
            texto.add(textos[indice]); 
        }
        
        //declaracion de un JTextFile del numero de valores que tenemos
        //instanciacion de los datos
        datos=new JTextField[numeroValores];
        //instanciacion JTextField
        datos[0]=new JTextField();
        //asignacion del texto
        datos[0].setText("");
        //asignacion de la fuente
        datos[0].setFont(new Font("Arial",Font.BOLD,15));
        //asignacion de la alineacion para que el texto se escriba desde la 
        //izquierda
        datos[0].setHorizontalAlignment(JTextField.LEFT);
        //introduccion al panel texto
        texto.add(datos[0]);
        
        //declaracion de los botones para poder ACEPTAR y CANCELAR
        //declaracion de una componente de array de botones
        JButton[]botonesAC=new JButton[2];
        //declaracion de una componenete de array de String con los nombres de 
        //los botones
        String []nombresBotones={"Ok","Cancel"};
        //bucle tratamiento botones
        for(int i=0;i<botonesAC.length;i++){
            //instanciar los botones
            botonesAC[i]=new JButton(nombresBotones[i]);
            //gestor de evento
            botonesAC[i].addActionListener((ActionEvent evento)->{
                
            //si se pulsa el boton de Ok y hay un nombre escrito, la ventana se
            //cerrara
            if(evento.getActionCommand().equals("Ok") &&
                    !datos[0].getText().equals("")){
                this.setVisible(false);
            }
    
            //si se pulsa el botones de Cancel, la ventana se cerrara
            if(evento.getActionCommand().equals("Cancel")){
                this.setVisible(false);
            }
        });
            
            //Introduccion boton al panel de botones
            botones.add(botonesAC[i]);
        }
        
        //ultimas acciones
        //tamaño de la ventana
        this.setSize(450, 150);
        //centrar en el centro de la ventana
        setLocationRelativeTo(frame);
        //visualizar JDialog
        setVisible(true);
    }

    //Método que lleva a cabo la devolución de los datos introducidos a través
    //del contenedor JDialog
    public String [] getDatosTexto() {
        String [] datosIntroducidos=new String[numeroValores];
        for (int indice=0;indice<datosIntroducidos.length;indice++) {
            //asignamos a la componente indice del array datosIntroducidos el
            //String introducido en la componente JTextField del array datos
            
            datosIntroducidos[indice]=(datos[indice].getText());
            if (datos[indice].getText().equals("")){
                
                try{
                    throw new entradaIncorrecta("ENTRADA INCORRECTA");
                }catch(entradaIncorrecta e){ 
                    ventanaInformativa("ENTRADA INCORRECTA");
                return null;
                }
            }
        }
        return datosIntroducidos;
    }
    
    
    
    private void ventanaInformativa(String informacion) {
        UIManager manipulador=new UIManager();
        manipulador.put("Panel.background", Color.BLACK);
        manipulador.put("OptionPane.background", Color.BLACK);
        manipulador.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
        manipulador.put("OptionPane.messageForeground", Color.YELLOW);
        JOptionPane.showMessageDialog(this,informacion); 
    }
}
