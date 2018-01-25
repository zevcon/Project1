package chan;
 
import javax.swing.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.Random;
 
 
public class Chan implements ActionListener
{
   JFrame ventana;
   JButton btn_iniciar, btn_boton, btn_tmp, btn_numeros;
   JTextField tf_texto, cv_texto, tesoro_texto;
   JButton btn_norte, btn_sur, btn_este, btn_oeste;
   int z, xt, yt;
   public static void main(String[] args)
   {
      new Chan();
   }
   Chan()
   {
      ventana = new JFrame("Proyecto");
      ventana.setBounds(50,50,1000,750);
      ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ventana.setLayout(null);
     
      btn_iniciar = new JButton("Iniciar");
      btn_iniciar.setBounds(50,20,100,20);
      btn_iniciar.addActionListener(this);
      ventana.add(btn_iniciar);
      // caja de texto de coordenadas del click del evento corriente.
      tf_texto = new JTextField();
      tf_texto.setBounds(200,20,50,20);
      ventana.add(tf_texto);
      // Caja de texto de # de intentos
      cv_texto = new JTextField();
      cv_texto.setBounds(300,20,50,20);
      ventana.add(cv_texto);
      // Caja de texto de las coordenadas del tesoro escondido
      tesoro_texto = new JTextField();
      tesoro_texto.setBounds(400,20,50,20);
      ventana.add(tesoro_texto);
     
      ventana.setVisible(true);
      z = 0;
   }
  
   public void actionPerformed(ActionEvent e)
   {
      //System.out.println("actionPerformed");
     if (e.getSource() == btn_iniciar)
      {
         crearBotones();
         crearNumerosF();
         crearNumerosC();
         crearPuntosCardinales();
         crear_coordenadas_tesoro();
         crearPuntosCardinales();
         // xt es a la coordenada en x (filas)
         // yt es la coordenada en y (columnas)
         xt = crear_coordenadas_tesoro();
         yt = crear_coordenadas_tesoro();
         //crear_sugenrencia_ubicacion();
      }
      else
      {
         btn_tmp = (JButton)e.getSource();
         //tf_texto.setText(btn_tmp.getText());
         tf_texto.setText(btn_tmp.getActionCommand());
         z++;
         cv_texto.setText(Integer.toString(z));
         // Compara para saber si los intentos supera la marca maxima de intentos
         if(z>10)
         {
            JOptionPane.showMessageDialog(null, "¡Algo anda mal!");
            System.exit(0);
         }
         String cxy_intento = tf_texto.getText();
         String cxy_tesoro = Integer.toString(xt) + "," + Integer.toString(yt);
         tesoro_texto.setText(cxy_tesoro);
         // Compara las coordendas del tesoro vs la del click o intento
        
         if (cxy_intento.equals(cxy_tesoro))
         {
            JOptionPane.showMessageDialog(null, "¡Excelente eres muy Inteligente!");
            System.exit(0);
         }
         else {
             String lc_sugerencia = "¡Este no es!  Vaya al " + crear_sugerencia(cxy_tesoro, cxy_intento);
             JOptionPane.showMessageDialog(null, lc_sugerencia);
         }
      }
   }
  
   public void crearBotones()
   {
      int i,j;
      for (j=0;j<10;j++)
      {   
      for (i=0;i<10;i++)
      {
         btn_boton = new JButton(String.valueOf(i));
         //btn_boton.setText("B"+String.valueOf(i));
         String etiquetaj = Integer.toString(10-j);
         String etiquetai = Integer.toString(i+1);
         //btn_boton.setText(etiquetaj+","+etiquetai);
         btn_boton.setText("");
         btn_boton.setActionCommand(String.valueOf(i+1)+","+String.valueOf(10-j));
        
         btn_boton.setBounds(145+75*i,90+50*j,75,50);
        
         btn_boton.addActionListener(this);
         ventana.add(btn_boton);
      }
      }
      ventana.repaint();
   }
  
   public void crearNumerosF()
   {
       int i,j;
       for (i=0;i<10;i++)
       {
           btn_numeros = new JButton(String.valueOf(i));
           String etiquetai = Integer.toString(i+1);
           btn_numeros.setBounds(145+75*i,650*1,75,50);
           btn_numeros.setText(etiquetai);
           //btn_numeros.setBounds(85,90+i,75,50);
           //btn_numeros.addActionListener(this);
           btn_numeros.setBackground(java.awt.Color.yellow);
           ventana.add(btn_numeros);
           ventana.repaint();
       }
   }
    public void crearNumerosC()
   {
       int i,j;
       for (i=0;i<10;i++)
       {
           btn_numeros = new JButton(String.valueOf(i));
           String etiquetai = Integer.toString(10-i);
           btn_numeros.setBounds(5,90+50*i,65,50);
           btn_numeros.setText(etiquetai);
           //btn_numeros.setBounds(85,90+i,75,50);
           btn_numeros.setBackground(java.awt.Color.BLUE);
           ventana.add(btn_numeros);
           ventana.repaint();
       }
   }
 
    public void crearPuntosCardinales()
    {
        btn_norte = new JButton("Norte");
        btn_norte.setBounds(485, 40, 65, 40);
        btn_norte.setBackground(java.awt.Color.GREEN);
        btn_sur = new JButton("Sur");
        btn_sur.setBounds(485, 600, 65, 40);
        btn_sur.setBackground(java.awt.Color.GREEN);
        btn_este = new JButton("Este");
        btn_este.setBounds(75, 325, 60, 40);
        btn_este.setBackground(java.awt.Color.GREEN);
        btn_oeste = new JButton("Oeste");
        btn_oeste.setBounds(900, 325, 75, 40);
        btn_oeste.setBackground(java.awt.Color.GREEN);
        ventana.add(btn_norte);
        ventana.add(btn_sur);
        ventana.add(btn_este);
        ventana.add(btn_oeste);
        ventana.repaint();
    }
   
    public int crear_coordenadas_tesoro()
    {
        //generar coordenada de fila i(x)
        //generar coordenada de columna j(y)
        //Se generara un numero aleatrio entre 1 y 10
        double ramdon =Math.random()*(10-1)+1;
        return (int)ramdon;
    }
   
    public String crear_sugerencia(String cxy_tesoro, String cxy_intento)
    {
        int xi = extrae_xi(cxy_intento);
        int yj = extrae_yj(cxy_intento);
        int xx = extrae_xi(cxy_tesoro);
        int yy = extrae_yj(cxy_tesoro);
        // norte o sur
        String nortesur = "";
        if (xi>xx) {
             nortesur = "Este";
        }
        else if (xi<xx) {
            nortesur = "Oeste";
        }
        else {
            nortesur = " ";
        }
        // este o oeste
        String esteooeste = "";
        if (yj>yy) {
            esteooeste = "Sur";
        }
        else if (yj<yy) {
            esteooeste = "Norte";
        }
        else {
            esteooeste = "";
        }
        return esteooeste + nortesur;
    }
   
    public int extrae_xi(String cxy)
    {
        int posicionx = cxy.indexOf(",",1);
        int largo = cxy.length();
        int cx = Integer.parseInt(cxy.substring(0, posicionx));
        return cx;
    }
    public int extrae_yj(String cxy)
    {
        int posiciony = cxy.indexOf(",",1);
        int largo = cxy.length();
        int cy = Integer.parseInt(cxy.substring(posiciony+1,largo-posiciony+1));
        return cy;
    }
   
}