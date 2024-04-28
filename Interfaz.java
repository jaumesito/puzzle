package practicafinal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.Timer;

public class Interfaz {
    //DECLARACIONES DE CLASE
    //declaracion panelVisualizacion
    private JPanel panelVisualizacion, panelStandby, panelHistorial, panelPartida, 
           panelImagenSolucion;
    //declaracion componentes JButton SIN ICONO
    private JButton nuevaPartidaBoton, clasificacionBoton, historialBoton, salirBoton;
    //declaracion componentes JButton CON ICONO
    private JButton nuevaPartidaIcono, clasificacionIcono, historialIcono, salirIcono, 
            cambiarDirectorioIcono;
    //declaracion componenetes JMenuItem
    private JMenuItem nuevaPartidaMenu, clasificacionMenu, historialMenu, salirMenu,
              cambiarDirectorioMenu;
    //declaracion de la ventana
    private JFrame ventana;
    //declaracion de un String para alamcenar el directorio
    private String directorioImagenes=new File("Imagenes/").getAbsolutePath();
    
    //declaracion Jlaber para almacenar la imagen de la UIB
    private JLabel imagenUIB;
    //componente barraProceso
    private BarraProgresionTemporal barraProgreso;
    //declaracion de las componentes que van en el panel imagenSolucion
    //componente JLabel para guaradar la imagenSolucion
    private JLabel imagenSolucion;
    //declaracion componente Jbutton 
    private JButton botonContinuar;
    //declaracion de las componentes que van en el panel panelHistorial
    private JTextArea areaVisualizacionResultados;
    
    //declaracion int para el numero de filas y columnas
    private int filas, columnas;
    private Partida partida;
    private String nombreJugador;
    private String nombreJugadorBuscado;
    //declaracion de un panelImagenes
    private panelSubImagenes panelImagenes;
    private Timer cronometro; 
    private final int FACTOR_VELOCIDAD=50;
    private int velocidad;
    private boolean partidaActiva=false;
    private  BufferedImage imagenPartida;
    private JScrollPane barrasDesplazamiento;
    //metodo main
    public static void main (String argumentos[]){
        new Interfaz().metodoPrincipal();
    }
    //metodoPrincipal
    private void metodoPrincipal(){
        
        //DECLARACION DEL JFrame PRINCIPAL
        ventana=new JFrame("PRÁCTICA PROGRAMACIÓN II 2022-2023");
        
        //declaración del contenedor principal del JFrame
        Container panelContenidos=ventana.getContentPane();
        //declaracion del Layout BorderLayout ya nos permite colocar tanto 
        //paneles como componentes en las siguientes zonas:
        //NORTE, SUR, ESTE, OESTE, CENTRO
        panelContenidos.setLayout(new BorderLayout());
        
        //instanciacion del JPanel pricipal, panelVisualizacion en el cual se 
        //visualizará todo lo relacionado con las imagenes
        panelVisualizacion=new JPanel();
        //declaracion del Layout necesario para el panelVisuaizacion
        panelVisualizacion.setLayout(new CardLayout());
        panelVisualizacion.setBackground(Color.PINK);
        //añadirlo en el centro del panel de Contenidos
        panelContenidos.add(panelVisualizacion,BorderLayout.CENTER);
    
//////////////////////////////ADIMINSTRACION DE LOS DISTINTOS PANELES 
                            //DEL CARD LAYOUT
        //PANEL STANDBY
        panelStandby=new JPanel();
        //instanciacion imagenUib
        imagenUIB=new JLabel(new ImageIcon("imagenUIB.jpg"));
        panelStandby.add(imagenUIB,BorderLayout.CENTER);
        panelStandby.setBackground(Color.BLACK);
        panelVisualizacion.add(panelStandby,"PANEL IMAGEN");   
        //añadir a panelContendidos
        panelContenidos.add(panelVisualizacion, BorderLayout.CENTER);
        
        //PANEL PARTIDA
        panelPartida=new JPanel();
        //layout
        panelPartida.setLayout((new BorderLayout()));
        panelPartida.setBackground(Color.BLACK);
        panelVisualizacion.add(panelPartida,"PANEL PARTIDA");
        cronometro=new Timer(100,gestorEvento);
        //añadir a panel contenidos
        panelContenidos.add(panelVisualizacion,BorderLayout.CENTER);
        
        //PANEL HISTORIAL
        panelHistorial=new JPanel();
        panelHistorial.setBackground(Color.white);
        panelHistorial.setLayout(new FlowLayout(FlowLayout.LEFT));
        barrasDesplazamiento = new JScrollPane(panelHistorial);
        barrasDesplazamiento.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        barrasDesplazamiento.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelContenidos.add(barrasDesplazamiento);
        //textArea
        areaVisualizacionResultados=new JTextArea();
        areaVisualizacionResultados.setEditable(false);
        areaVisualizacionResultados.setForeground(Color.BLACK);
        areaVisualizacionResultados.setFont(new Font("arial",Font.BOLD,20));
        panelHistorial.add(areaVisualizacionResultados);
        panelVisualizacion.add(panelHistorial,"PANEL HISTORIAL");
        //añadir al panel de Contenidos
        panelContenidos.add(panelVisualizacion,BorderLayout.CENTER);
        
        //PANEL IMAGEN SOLUCION
        panelImagenSolucion=new JPanel();
        panelImagenSolucion.setLayout(new BorderLayout());
        //boton
        botonContinuar=new JButton("CONTINUAR");
        //back and fore negros
        botonContinuar.setBackground(Color.BLACK);
        botonContinuar.setForeground(Color.WHITE);
        botonContinuar.addActionListener(new gestorEvento());
        panelImagenSolucion.add(botonContinuar, BorderLayout.SOUTH);
        panelVisualizacion.add(panelImagenSolucion,"PANEL SOLUCION");
        //añadir al panel de Contenidos
        panelContenidos.add(panelVisualizacion,BorderLayout.CENTER);
        
        //declaracion del JPanel para los botones, panelBotones en el cual
        //estarán los botones correspondientes:
        //nuevaPartidaBoton, clasificacionBoton, historialBoton, salirBoton
        JPanel panelBotones=new JPanel();
        //declaracion del Layout GridLayout para poder poner los botones uno
        //debajo del otro
        panelBotones.setLayout(new GridLayout(4,1));
        
//////////////////////////////BOTONES SIN IMAGEN CORRESPONDIENTES
////////BOTON nuevaPartidaBoton
        nuevaPartidaBoton=new JButton("NUEVA PARTIDA");
        //añadir un FlowLayout para poder visualizar correctamente las letras 
        //del boton
        nuevaPartidaBoton.setLayout(new FlowLayout());
        //fondo del boton
        nuevaPartidaBoton.setBackground(Color.BLACK);
        //color de las letras
        nuevaPartidaBoton.setForeground(Color.WHITE);
        //administrar evento
        nuevaPartidaBoton.addActionListener(new gestorEvento());
        //añadir al panel de Botones
        panelBotones.add(nuevaPartidaBoton);
////////BOTON clasificacionBoton
        clasificacionBoton=new JButton("HISTORIAL GENERAL");
        //añadir un FlowLayout para poder visualizar correctamente las letras 
        //del boton
        clasificacionBoton.setLayout(new FlowLayout());
        //fondo del boton
        clasificacionBoton.setBackground(Color.BLACK);
        //color de las letras
        clasificacionBoton.setForeground(Color.WHITE);
        //administrar evento
        clasificacionBoton.addActionListener(new gestorEvento());
        //añadir al panel de Botones
        panelBotones.add(clasificacionBoton);
////////BOTON historialBoton
        historialBoton=new JButton("HISTORIAL SELECTIVO");
        //fondo del boton
        historialBoton.setBackground(Color.BLACK);
        //color de las letras
        historialBoton.setForeground(Color.WHITE);
        //administrar evento
        historialBoton.addActionListener(new gestorEvento());
        //añadir al panel de Botones
        panelBotones.add(historialBoton);
////////BOTON salirBoton
        salirBoton=new JButton("SALIR");
        //fondo del boton
        salirBoton.setBackground(Color.BLACK);
        //color de las letras
        salirBoton.setForeground(Color.WHITE);
        //administrar evento
        salirBoton.addActionListener(new gestorEvento());
        //añadir al panel de Botones
        panelBotones.add(salirBoton);
        //añadir panelBotones al contenedor
        panelContenidos.add(panelBotones,BorderLayout.WEST);
     
        //panel separador1 que separa el panelBotones del panelVisualizacion
        JSplitPane panelSeparador1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        //divisiones que tiene que realizar el panerSeparador1
        panelSeparador1.add(panelBotones);
        panelSeparador1.add(panelVisualizacion);
        //insertar el panelSeparador1 en el panel de Contenidos
        panelContenidos.add(panelSeparador1,BorderLayout.CENTER);
        
        //declaracion del JMenu correspondiente que contendrá las siguientes 
        //componentes JMenuItem:
        //nuevaPartidaMenu, clasificacionMenu, historialMenu, salirMenu,
        //cambiarDirectorioMenu;
        //declaracion de una componente JMenuBar
        JMenuBar barraMenu=new JMenuBar();
        //color de la barraMenu
        barraMenu.setBackground(Color.BLACK);
        barraMenu.setForeground(Color.WHITE);
        //introducir en la ventana
        ventana.setJMenuBar(barraMenu);
        //declaracion de la componente componente JMenu
        JMenu menuOpciones=new JMenu("MENU");
        //color del String "MENU"
        menuOpciones.setForeground(Color.WHITE);      
//////////////////////////////COMPONENETES NECESARIAS
        //JMenuItem nuevaPartidaMenu
        nuevaPartidaMenu=new JMenuItem("NUEVA PARTIDA");
        //añadir un FlowLayout para poder visualizar correctamente las letras 
        //del boton
        nuevaPartidaMenu.setLayout(new FlowLayout());
        //administrar evento
        nuevaPartidaMenu.addActionListener(new gestorEvento());
        //fondo del boton
        nuevaPartidaMenu.setBackground(Color.BLACK);
        //color de las letras
        nuevaPartidaMenu.setForeground(Color.WHITE);
        //añadirlo a menuOpciones
        menuOpciones.add(nuevaPartidaMenu);
        //JMenuItem clasificacionMenu
        clasificacionMenu=new JMenuItem("HISTORIAL GENERAL");
        //añadir un FlowLayout para poder visualizar correctamente las letras 
        //del boton
        clasificacionMenu.setLayout(new FlowLayout());
        //administrar evento
        clasificacionMenu.addActionListener(new gestorEvento());
        //fondo del boton
        clasificacionMenu.setBackground(Color.BLACK);
        //color de las letras
        clasificacionMenu.setForeground(Color.WHITE);
        //añadirlo a menuOpciones
        menuOpciones.add(clasificacionMenu);
        //JMenuItem historialMenu
        historialMenu=new JMenuItem("HISTORIAL SELECTIVO");
        //administrar evento
        historialMenu.addActionListener(new gestorEvento());
        //fondo del boton
        historialMenu.setBackground(Color.BLACK);
        //color de las letras
        historialMenu.setForeground(Color.WHITE);
        //añadirlo a menuOpciones
        menuOpciones.add(historialMenu);
        //JMenuItem cambiarDirectorioMenu
        cambiarDirectorioMenu=new JMenuItem("CAMBIAR DIRECTORIO");
        //añadir un FlowLayout para poder visualizar correctamente las letras 
        //del boton
        cambiarDirectorioMenu.setLayout(new FlowLayout());
        //administrar evento
        cambiarDirectorioMenu.addActionListener(new gestorEvento());
        //fondo del boton
        cambiarDirectorioMenu.setBackground(Color.BLACK);
        //color de las letras
        cambiarDirectorioMenu.setForeground(Color.WHITE);
        //añadirlo a menuOpciones
        menuOpciones.add(cambiarDirectorioMenu);
        //JMenuItem salirMenu
        salirMenu=new JMenuItem("SALIR");
        //administrar evento
        salirMenu.addActionListener(new gestorEvento());
        //fondo del boton
        salirMenu.setBackground(Color.BLACK);
        //color de las letras
        salirMenu.setForeground(Color.WHITE);
        //añadirlo a menuOpciones
        menuOpciones.add(salirMenu);
        //añadir el menu a la barra de Menu
        barraMenu.add(menuOpciones);
        
        //declaracion de una JToolBar, IconosMenu que contendrá las siguientes
        //componentes:
        //nuevaPartidaIcono, clasificacionIcono, historialIcono, salirIcono, 
        //cambiarDirectorioIcono
        JToolBar barraHerramientas=new JToolBar();
        //color de fondo
        barraHerramientas.setBackground(Color.BLACK);
        barraHerramientas.setFloatable(false);
//////////////////////////////BOTONES CON IMAGEN CORRESPONDIENTES
        //BOTON nuevaPartidaIcono
        nuevaPartidaIcono=new JButton(new ImageIcon("iconoNuevaPartida.jpg"));
        //fondo del boton
        nuevaPartidaIcono.setBackground(Color.BLACK);
        //darle un action command
        nuevaPartidaIcono.setActionCommand("NUEVA PARTIDA");
        //administrar evento
        nuevaPartidaIcono.addActionListener(new gestorEvento());
        //BOTON clasificacionIcono
        clasificacionIcono=new JButton(new ImageIcon("iconoHistorialGeneral.jpg"));
        //fondo del boton
        clasificacionIcono.setBackground(Color.BLACK);
        //darle un action command
        clasificacionIcono.setActionCommand("HISTORIAL GENERAL");
        //administrar evento
        clasificacionIcono.addActionListener(new gestorEvento());
        //BOTON clasificacionIcono
        clasificacionIcono=new JButton(new ImageIcon("iconoHistorialGeneral.jpg"));
        //fondo del boton
        clasificacionIcono.setBackground(Color.BLACK);
        //administrar evento
        clasificacionIcono.addActionListener(new gestorEvento());
        //BOTON historialIcono
        historialIcono=new JButton(new ImageIcon("iconoHistorialSelectivo.jpg"));
        //fondo del boton
        historialIcono.setBackground(Color.BLACK);
        //darle un action command
        historialIcono.setActionCommand("HISTORIAL SELECTIVO");
        //administrar evento
        historialIcono.addActionListener(new gestorEvento());
        //BOTON cambiarDirectorioIcono
        cambiarDirectorioIcono=new JButton(new ImageIcon("iconoCambiarDirectorio.jpg"));
        //fondo del boton
        cambiarDirectorioIcono.setBackground(Color.BLACK);
        //darle un action command
        cambiarDirectorioIcono.setActionCommand("CAMBIAR DIRECTORIO");
        //administrar evento
        cambiarDirectorioIcono.addActionListener(new gestorEvento());
        //BOTON salirIcono
        salirIcono=new JButton(new ImageIcon("iconoSalir.jpg"));
        //fondo del boton
        salirIcono.setBackground(Color.BLACK);
        //darle un action command
        salirIcono.setActionCommand("SALIR");
        //administrar evento
        salirIcono.addActionListener(new gestorEvento());
        //añadir las componentes a la barra de herramientas
        barraHerramientas.add(nuevaPartidaIcono);
        barraHerramientas.add(clasificacionIcono);
        barraHerramientas.add(historialIcono);
        barraHerramientas.add(cambiarDirectorioIcono);
        barraHerramientas.add(salirIcono);
        //panel separador2 que separa la barraHerraminetas del panelVisualizacion
        JSplitPane panelSeparador2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        //divisiones que tiene que realizar el panerSeparador1
        panelSeparador2.add(barraHerramientas);
        //insertar el panelSeparador1 en el panel de Contenidos
        panelContenidos.add(panelSeparador2,BorderLayout.NORTH);
                            //ULTIMAS ACCIONES
         //DECLARACION DEL TAMAÑO DE LA VENTANA 
         ventana.pack();
        //ACTIVACIÓN DEL CIERRE INTERACTIVO VENTANA DE WINDOWS EN EL CONTENEDOR 
        //JFrame ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        //CENTRADO DEL CONTENEDOR ventana EN EL CENTRO DE LA PANTALLA
        ventana.setLocationRelativeTo(null);
        //VISUALIZACIÓN CONTENEDOR JFrame ventana
        ventana.setVisible(true);
    }
    
     //CLASE GESTOR DE EVENTOS
    private class gestorEvento implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent evento) {
            CardLayout local = (CardLayout)(panelVisualizacion.getLayout());
                {
                    switch (evento.getActionCommand()) {
                        case "SALIR":
                            System.exit(0);
                            break;
                        case "CAMBIAR DIRECTORIO":
                            if(!partidaActiva){
                                //declaracion de una componente JFileChooser
                                JFileChooser ventanaSeleccion=new JFileChooser();
                                ventanaSeleccion.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 

                                int op=ventanaSeleccion.showOpenDialog(ventana);
                                if(op==JFileChooser.APPROVE_OPTION) {
                                    //obtención del nombre de la libreria (nombre del directorio seleccionado)
                                    directorioImagenes=ventanaSeleccion.getSelectedFile().getAbsolutePath();                         
                                }
                            }else{
                                ventanaError("HAY UNA PARTIDA ACTIVA");
                            }
                            break;
                        case "NUEVA PARTIDA":
                            if(!partidaActiva){
                            try{
                            imagenPartida = imagenAleatoria();
                            imagenPartida=reescalarImagen(imagenPartida,1250,760);
                            //declaracion array de strings
                            String []datos={"NOMBRE JUGADOR", "NÚMERO DE SUBDIVIONES HORIZONTAL", "NÚMERO DE SUBDIVIONES VERTICAL"};
                            datos=new LTGraficos(ventana,datos).getDatosTexto();
                            if(datos!=null){
                                //lectura de datos
                                nombreJugador=(datos[0]);
                                filas=Integer.parseInt(datos[1]);
                                columnas=Integer.parseInt(datos[2]);
                            }
                            //controlar que filas y columnas son mayor a 0
                            if(filas<=0||columnas<=0){
                                throw new entradaIncorrecta("NUMERO DE FILA/COLUMNA INVALIDO");
                            }
                            //activar partida
                            partidaActiva=true;
                            //lectura del objeto partida
                            partida=new Partida(nombreJugador);
                            //instanciacion del objeto panelImagenes
                            panelImagenes=new panelSubImagenes(imagenPartida,filas,columnas);
                            //añadir al panel Partida
                            panelPartida.add(panelImagenes, BorderLayout.CENTER);
                            //llamar al cardLayout
                            local.show(panelVisualizacion, "PANEL PARTIDA");
                            //instanciar barraTemporal
                            barraProgreso=new BarraProgresionTemporal(panelPartida.getWidth());
                            //añadir la barra de progresion
                            panelPartida.add(barraProgreso,BorderLayout.SOUTH);
                            //velocidad barra
                            velocidad=(int)FACTOR_VELOCIDAD/(filas*columnas);
                            //si da 0
                            if(velocidad==0){
                                velocidad++;
                            }
                            //iniciar cronometro
                            cronometro.start();
                            

                            
                            }catch(IOException e){
                                System.err.println(e.toString());
                            }catch(libreriaVacia e){
                                ventanaError("LA LIBRERIA ESTA VACIA");
                            }catch(entradaIncorrecta e){
                                ventanaError("VALORES NO CORRECTOS");
                            }
                            }else{
                                ventanaError("HAY UNA PARTIDA ACTIVA");
                            }
                           
                            break;
                        case "HISTORIAL GENERAL":
                            if(!partidaActiva){
                            //declaracion objeto PartidaObjetoFicherosLectura
                            PartidaObjetoFicherosLectura fichero;
                            
                            //ACCIONES
                            try{
                                //instanciar fichero
                                fichero=new PartidaObjetoFicherosLectura("resultados.dat");
                                areaVisualizacionResultados.setText("HISTORIAL GENERAL\n");
                                try{
                                    //leer primera partida
                                    partida=fichero.lectura();
                                    //mientras no sea centinela
                                    while(!partida.esCentinela()){
                                        //asignar la partida a la JTextArea
                                        areaVisualizacionResultados.setText(areaVisualizacionResultados.getText()+"\n"+partida.toString());
                                        //lectura siguiente 
                                        partida=fichero.lectura();
                                    }
                                    local.show(panelVisualizacion, "PANEL HISTORIAL");                                    
                                }catch(ClassNotFoundException e){
                                    System.out.println("NO SE ENCUENTRA LA CLASE PARTIDA");
                                }catch(IOException e){
                                    System.out.println("ERROR LECTURA FICHERO");
                                }finally{
                                    try{
                                        //cerrar fichero
                                        fichero.cerrarEnlaceFichero();
                                    }catch(IOException e){
                                        System.out.println("ERROR EN EL CIERRE DEL FICHERO");
                                    }
                                }
                            }catch(FileNotFoundException e){
                               ventanaError("EL FICHERO NO EXISTE");
                            }catch(IOException er){
                                System.out.println("ERROR ENLACE FICHERO");
                            }
                            }else{
                                ventanaError("HAY UNA PARTIDA ACTIVA"); 
                            }
                            break;
                        case "HISTORIAL SELECTIVO":
                            if(!partidaActiva){
                            //lectura del nombre a buscar
                            nombreJugadorBuscado = new LTGraficos(ventana).getDatosTexto()[0]; 
                            //declaracion objeto PartidaObjetosFicherosLectura
                            PartidaObjetoFicherosLectura ficheroSelectivo;
                            //ACCIONES
                            try{
                                //instanciar fichero
                                ficheroSelectivo=new PartidaObjetoFicherosLectura("resultados.dat");
                                areaVisualizacionResultados.setText("HISTORIAL SELECTIVO\n");
                                try{
                                    //leer primera partida
                                    partida=ficheroSelectivo.lectura();
                                    //mientras no sea centinela
                                    while(!partida.esCentinela()){
                                        //mirar si el nombre es igual al elegido
                                        if(partida.getNombre().equals(nombreJugadorBuscado)){
                                        //asignar la partida a la JTextArea
                                            areaVisualizacionResultados.setText(areaVisualizacionResultados.getText()+"\n"+partida.toString());
                                        }
                                        //lectura siguiente 
                                        partida=ficheroSelectivo.lectura();
                                    }
                                    local.show(panelVisualizacion, "PANEL HISTORIAL");
                                }catch(ClassNotFoundException e){
                                    System.out.println("NO SE ENCUENTRA LA CLASE PARTIDA");
                                }catch(IOException e){
                                    System.out.println("ERROR LECTURA FICHERO");
                                }finally{
                                    try{
                                        //cerrar fichero
                                        ficheroSelectivo.cerrarEnlaceFichero();
                                    }catch(IOException e){
                                        System.out.println("ERROR EN EL CIERRE DEL FICHERO");
                                    }
                                }
                            }catch(FileNotFoundException e){
                               ventanaError("EL FICHERO NO EXISTE");
                            }catch(IOException er){
                                System.out.println("ERROR ENLACE FICHERO");
                            }
                            }else{
                                ventanaError("HAY UNA PARTIDA ACTIVA");             
                            }
                            break;
                            
                        case "CONTINUAR":
                            if(!partidaActiva){                                                 
                            grabarFichero();
                            local.show(panelVisualizacion, "PANEL IMAGEN");
                            panelPartida.removeAll();
                            }else{
                                ventanaError("HAY UNA PARTIDA ACTIVA");  
                            }
                            break;
                    }   
                }
        }
    }
            ActionListener gestorEvento = new ActionListener() { 
            //TRATAMIENTO EVENTO
            @Override
            public void actionPerformed(ActionEvent evento) {
                CardLayout local = (CardLayout)(panelVisualizacion.getLayout());
                //verificar que no ha llegado a maximo ni se ha completado el 
                //puzzle
                if(barraProgreso.getValue()!=barraProgreso.getMaximum()&&!panelImagenes.puzzleResuelto()){
                    //incrementar barraProgreso
                    barraProgreso.setValue(barraProgreso.getValue()+velocidad);
                }else{    
                    if(!panelImagenes.puzzleResuelto()){
                        ventanaError("NO LO HAS CONSEGUIDO -  EL TIEMPO HA TERMINADO");                        
                    }
                    //parar el cronometro
                    cronometro.stop();
                    //eliminar todo del panelImagenSolucion para poder visualizar
                    //la imagen correcta una vez se acabe la partida
                    panelImagenSolucion.removeAll();
                    //instanciar boton continuar
                    panelImagenSolucion.add(botonContinuar, BorderLayout.SOUTH);
                    //instanciar imagenSolucion
                    panelImagenSolucion.add(imagenSolucion=new JLabel(new ImageIcon(imagenPartida)), BorderLayout.CENTER);
                    panelImagenSolucion.revalidate();
                    panelImagenSolucion.repaint();
                    local.show(panelVisualizacion, "PANEL SOLUCION");
                    partidaActiva=false;    
                    partida.acabarPartida(panelImagenes.puzzleResuelto(), filas, columnas);
                }             
        }
            };
    
    //declaracion del metodo privado de la clase interfaz imagenAleatoria que 
    //en el directorio seleccionado busca una imagen aleatoria
    private BufferedImage imagenAleatoria()throws IOException, libreriaVacia{
        //declaracion variable booleana para identificar que hay imagenes
        boolean hayImagenes=false;
        //leer los archivos de la libreria selecciona
        File biblioteca=new File(directorioImagenes);
        //guardar los archivos
        String archivos[]=biblioteca.list();
        //declaracion de una variable entera para almacenar el numero aleatorio
        int numAleatorio=0;
        //declaracion de un objeto random
        Random generador=new Random();
        
        //verificacion de que la biblioteca exista y contenga al menos una imagen
        if(biblioteca==null){
            ventanaError("LA LIBRERIA ESTA VACIA");
        }
        //verificar que haya al menos una imagen
        for(int i=0;i<archivos.length;i++){
            if(archivos[i].endsWith(".jpg")||archivos[i].endsWith(".png")){
                hayImagenes=true;
            }
        }
        if(!hayImagenes){
            ventanaError("LA LIBRERIA ESTA VACIA");
        }
        //generar imagen aleatoria
        do{
            //generar numAleatorio
            numAleatorio=generador.nextInt(archivos.length);
            
        }while(!(archivos[numAleatorio].endsWith(".jpg")|| archivos[numAleatorio].endsWith(".png")));
        //devolver la imagen aleatoria seleccionada
        return ImageIO.read(new File(directorioImagenes+"/"+archivos[numAleatorio]));
    }
    
    //metodo ventanaError que muestra una ventana emergente ocurre un error
    //en la libreria
    private void ventanaError(String mensaje){
        //configuracion del JDialog
        JDialog ventanaEmergente=new JDialog(ventana,"MENSAJE",true);
        Container contentPanel=ventanaEmergente.getContentPane();
        

        //panel panelVentanaEmergente
        JPanel panelVentanaEmergente=new JPanel();
        panelVentanaEmergente.setLayout(new FlowLayout()); 
        panelVentanaEmergente.setBackground(Color.BLACK);
        contentPanel.add(panelVentanaEmergente);

        //JLabel para el icono
        JLabel icono=new JLabel(new ImageIcon("icono.png"));
        panelVentanaEmergente.add(icono);
        //JLable para el mensaje 
        JLabel texto=new JLabel(mensaje);
        texto.setFont(new Font("arial", Font.BOLD, 15));
        texto.setForeground(Color.YELLOW);
        panelVentanaEmergente.add(texto);

        //cerrarVentana
        JButton cerrarVentana=new JButton("OK");
        panelVentanaEmergente.add(cerrarVentana);
        cerrarVentana.addActionListener((ActionEvent evento) ->{
         ventanaEmergente.setVisible(false);
        });

        ventanaEmergente.setSize(500,150);
        ventanaEmergente.setLocationRelativeTo(null);
        ventanaEmergente.setVisible(true);        
    }
    //declaracion de un metodo publico para redimensionar la imagen a hacer el puzzle
    //y la imagen solucion
    public BufferedImage reescalarImagen(BufferedImage imagenOriginal, int anchura, int altura) {
    BufferedImage imagenReescalada = new BufferedImage(anchura, altura, imagenOriginal.getType());
    Graphics2D graphics2D = imagenReescalada.createGraphics();
    graphics2D.drawImage(imagenOriginal, 0, 0, anchura, altura, null);
    graphics2D.dispose();

    return imagenReescalada;
    }
    
    //declaracion del metodo grabarFichero que permite la escritura de un objeto
    //partida en el fichero de texto resultados.dat
    public void grabarFichero(){
    //DECLARACIONES
        //declaracion objeto PartidaObjetoFicherosLectura ficheroLectura para leer
        //partidas desde un fichero
        PartidaObjetoFicherosLectura ficheroLectura;
        //declaración PartidaObjetoFicheros ficheroOutTemp para utilizarlo
        //como fichero temporal
        PartidaObjetoFicherosEscritura ficheroTemp;
        //declaración objeto Partida para almacenar los leidos desde fichero
        Partida partidaLeida;
        
        //ACCIONES
        try{
            //Instanciación de ficheros
            ficheroLectura=new PartidaObjetoFicherosLectura("resultados.dat");
            ficheroTemp=new PartidaObjetoFicherosEscritura("resultadosTemporal.dat");
            try{
                //Copia de partidas en ficheroTemporal
                //Lectura primera partida
                partidaLeida=ficheroLectura.lectura();
                //Bucle de copia de partidas
                while(!partidaLeida.esCentinela()){
                    //Copia de partida en fichero temporal
                    ficheroTemp.escritura(partidaLeida);
                    //Lectura siguiente partida
                    partidaLeida=ficheroLectura.lectura();
                }
                
                //Escritura nueva partida
                ficheroTemp.escritura(partida);
            }catch(ClassNotFoundException error){
                //Visualizar aviso correspondiente
                ventanaError("NO SE HA ENCONTRADO LA CLASE PARTIDA");
           }catch(IOException error){
                //Visualizar aviso correspondiente
                ventanaError("ERROR EN LA GRABACIÓN DEL FICHERO");
            }finally{
                try{   
                    //Escribir en el fichero el centinela
                    ficheroTemp.escritura(Partida.getCentinela());
                    //cerrar enlace
                    ficheroLectura.cerrarEnlaceFichero();
                    ficheroTemp.cerrarEnlaceFichero();
                    
                    //sustituir nombre de los ficheros
                    File f1=new File("resultados.dat");
                    File f2=new File("resultadosTemporal.dat");
                    if (f1.delete()) {
                        f2.renameTo(f1);
                    }
                }catch (IOException error) {
                    ventanaError("ERROR CIERRE FICHERO");
                }
            }
        }catch(FileNotFoundException error){
           ventanaError("NO SE ENCUENTRA EL FICHERO");
        }catch(IOException error){
           ventanaError("ERROR EN EL CIERRE");
        }
    }
        
}
                    