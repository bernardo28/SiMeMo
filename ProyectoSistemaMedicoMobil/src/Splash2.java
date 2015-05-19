// Fig. 19.2: AnimadorLogo.java
// Animaci�n de una serie de im�genes.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Splash2 extends JPanel implements ActionListener {

   private final static String NOMBRE_IMAGEN = "logo"; // nombre base de la imagen
   protected ImageIcon imagenes[];     // arreglo de im�genes

   private int imagenesTotales = 4;     // n�mero de im�genes
   private int imagenActual = 0;     // �ndice de imagen actual
   private int retrasoAnimacion = 50;  // retraso en milisegundos
   private int anchura;                // anchura de la imagen
   private int altura;               // altura de la imagen

   private Timer temporizadorAnimacion; // el objeto Timer controla la animaci�n

   // inicializar objeto AnimadorLogo cargando las im�genes
   public Splash2()
   {
      imagenes = new ImageIcon[ imagenesTotales ];

      // cargar im�genes
      for ( int cuenta = 0; cuenta < imagenes.length; ++cuenta )
         imagenes[ cuenta ] = new ImageIcon( getClass().getResource(
            "Img/" + NOMBRE_IMAGEN + cuenta + ".gif" ) );
 
      // en este ejemplo suponemos que todas las im�genes tienen la misma anchura y altura
      anchura = imagenes[ 0 ].getIconWidth();   // obtener anchura de icono
      altura = imagenes[ 0 ].getIconHeight(); // obtener altura de icono
   }

   // mostrar imagen actual 
   public void paintComponent( Graphics g )
   {
      super.paintComponent( g );

      imagenes[ imagenActual ].paintIcon( this, g, 0, 0 );
      
      // pasar a la siguiente imagen solamente si el temporizador se est� ejecutando
      if ( temporizadorAnimacion.isRunning() )  
         imagenActual = ( imagenActual + 1 ) % imagenesTotales;
   }

   // responder al evento del objeto Timer
   public void actionPerformed( ActionEvent eventoAccion )
   {
      repaint(); // repintar el animador
   }

   // iniciar o reiniciar la animaci�n
   public void iniciarAnimacion()
   {
      if ( temporizadorAnimacion == null ) {
         imagenActual = 0;  
         temporizadorAnimacion = new Timer( retrasoAnimacion, this );
         temporizadorAnimacion.start();
      }
      else // continuar desde la �ltima imagen mostrada
         if ( ! temporizadorAnimacion.isRunning() )
            temporizadorAnimacion.restart();
   }

   // detener el temporizador de la animaci�n
   public void detenerAnimacion()
   {
      temporizadorAnimacion.stop();
   }

   // devolver el tama�o m�nimo de la animaci�n
   public Dimension getMinimumSize()
   { 
      return getPreferredSize(); 
   }

   // devolver el tama�o preferido de la animaci�n
   public Dimension getPreferredSize()
   {
      return new Dimension( anchura, altura );
   }

   // ejecutar la animaci�n en un objeto JFrame
  /* public static void main( String args[] )
   {
      Splash2 animacion = new Splash2(); // crear el objeto 

      JFrame ventana = new JFrame( "Prueba de Splash" ); // configurar ventana
      ventana.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      ventana.setUndecorated(false);
      ventana.setResizable(false);
     
      Container contenedor = ventana.getContentPane();
      contenedor.add( animacion );

      ventana.pack();  // hacer la ventana lo suficientemente grande para su GUI
      ventana.setVisible( true );   // mostrar la ventana
      animacion.iniciarAnimacion();  // comenzar la animaci�n

   } // fin del m�todo main*/

} // fin de la clase A

