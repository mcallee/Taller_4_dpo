// Clase para la actualización del tablero en la interfaz (TableroUpdater.java)
package LogicaGUI;

import uniandes.dpoo.taller4.modelo.Tablero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableroGUILogica {
	private Image luzApagada;
    private Image luzEncendida;
    private Tablero tablero;
    private JPanel panelTablero;
    private JTextField cantidadJugadas;
    public int cont=0;
    public TableroGUILogica(JPanel panelTablero, Tablero tablero, int filas, JTextField cantidadJugadas) {
        this.panelTablero = panelTablero;
        this.cantidadJugadas = cantidadJugadas;
        this.tablero = tablero;
        panelTablero.setLayout(new GridLayout(filas, filas)); // Establecer el layout a GridLayout
        
        inicializarTablero(filas);
    }

    private void inicializarTablero(int filas) {
        try {
            luzApagada = new ImageIcon("data/luzApagada.png").getImage();
            luzEncendida = new ImageIcon("data/luzPrendida.png").getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        panelTablero.setLayout(new GridLayout(filas, filas)); // Establecer el layout a GridLayout
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < filas; j++) {
                JLabel label = new JLabel();
                if(tablero.darTablero()[i][j]==true) {
                	
                }else {
                	label.setIcon(new ImageIcon( luzEncendida ));
                }
                
                int finalI = i;
                int finalJ = j;
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tablero.jugar(finalI, finalJ);
                        actualizarTablero();
                    }
                });
                panelTablero.add(label);
            }
        }
        cont=0;
        cantidadJugadas.setText(Integer.toString(cont));
        reiniciarTablero();
        }
    public void reiniciarTablero() {
        tablero.reiniciar(); // Restablecer el tablero
        tablero.desordenar(10); // Desordenar las luces aleatoriamente con 10 jugadas
        cont=0;
        actualizarTablero(); // Actualizar la representación visual en la interfaz
        
    }

    private void actualizarTablero() {
        Component[] componentes = panelTablero.getComponents();
        for (int i = 0; i < componentes.length; i++) {
            JLabel label = (JLabel) componentes[i];
            int fila = i / tablero.darTablero()[0].length;
            int columna = i % tablero.darTablero()[0].length;
            label.setIcon(new ImageIcon(tablero.darTablero()[fila][columna] ? luzApagada : luzEncendida));
        }
        
        cantidadJugadas.setText(Integer.toString(cont));
        cont++;
        
    }
    public void reiniciarTableroConDimension(int filas, int columnas) {
        tablero = new Tablero(filas); // Reiniciar el tablero con las nuevas dimensiones
        panelTablero.removeAll(); // Eliminar los componentes anteriores
        inicializarTablero(filas); // Volver a inicializar el tablero con la nueva dimensión
        panelTablero.revalidate(); // Volver a validar el panel
        panelTablero.repaint(); // Repintar el panel
        cont=0;
    }

    // Otros métodos relacionados con la lógica del tablero en la interfaz
    // ...
}