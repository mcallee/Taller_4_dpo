package InterfazGUI;

import LogicaGUI.TableroGUILogica;
import uniandes.dpoo.taller4.modelo.RegistroTop10;
import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.File;
import java.util.ArrayList;

public class PantallaJuegoGUI extends JFrame {
    private TableroGUILogica tableroGUILogica;

    public PantallaJuegoGUI() {
        setTitle("Juego de Luces");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    public void initComponents() {
        getContentPane().setBackground(new Color(238, 238, 238));

        // Panel superior
        JPanel opcionesSuperioresPanel = new JPanel();
        opcionesSuperioresPanel.setLayout(new FlowLayout());
        opcionesSuperioresPanel.setBackground(new Color(135, 206, 250)); // Azul celeste
        opcionesSuperioresPanel.setForeground(Color.WHITE);

        JLabel tamanoTableroLabel = new JLabel("Tamaño del Tablero:");
        tamanoTableroLabel.setForeground(Color.WHITE);
        opcionesSuperioresPanel.add(tamanoTableroLabel);

        String[] tamanos = {"5x5", "4x4", "3x3"};
        JComboBox<String> tamanoTableroCombo = new JComboBox<>(tamanos);
        tamanoTableroCombo.setBackground(new Color(135, 206, 250)); // Azul celeste
        tamanoTableroCombo.setForeground(Color.BLACK);
        opcionesSuperioresPanel.add(tamanoTableroCombo);

    
        ButtonGroup dificultadGroup = new ButtonGroup();

        JRadioButton facilRadioButton = new JRadioButton("Fácil");
        dificultadGroup.add(facilRadioButton);
        facilRadioButton.setForeground(Color.WHITE);
        facilRadioButton.setBackground(new Color(135, 206, 250)); // Azul celeste
        opcionesSuperioresPanel.add(facilRadioButton);

        JRadioButton mediaRadioButton = new JRadioButton("Media");
        dificultadGroup.add(mediaRadioButton);
        mediaRadioButton.setBackground(new Color(135, 206, 250)); // Azul celeste
        mediaRadioButton.setForeground(Color.WHITE);
        opcionesSuperioresPanel.add(mediaRadioButton);

        JRadioButton dificilRadioButton = new JRadioButton("Difícil");
        dificultadGroup.add(dificilRadioButton);
        dificilRadioButton.setBackground(new Color(135, 206, 250)); // Azul celeste
        dificilRadioButton.setForeground(Color.WHITE);
        opcionesSuperioresPanel.add(dificilRadioButton);
        
     

        
        // Panel de juego
        JPanel juegoPanel = new JPanel();
        juegoPanel.setLayout(new BorderLayout());
        juegoPanel.setBackground(Color.GRAY);

        int filas = 5;
        int columnas = 5;
        JPanel panelTablero = new JPanel(new GridLayout(filas, columnas));
        panelTablero.setBackground(Color.LIGHT_GRAY);

        Tablero tablero = new Tablero(filas);
        
        
        //Interfaz bonotenes derecha centro
        
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.Y_AXIS));
        botonesPanel.setBackground(new Color(220, 220, 220));

        JButton nuevoButton = new JButton("Nuevo");
        nuevoButton.setEnabled(false); // Inicialmente deshabilitado
        nuevoButton.setBackground(Color.BLUE);
        nuevoButton.setForeground(Color.WHITE);
        nuevoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        nuevoButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        botonesPanel.add(Box.createRigidArea(new Dimension(10, 10))); // Agrega un espacio de 10px
        botonesPanel.add(nuevoButton);
        botonesPanel.add(Box.createRigidArea(new Dimension(0	, 10))); // Agrega un espacio de 10px
        nuevoButton.addActionListener(e -> {
            String seleccion = (String) tamanoTableroCombo.getSelectedItem(); // Obtener la selección del JComboBox
            String[] dimensiones = seleccion.split("x");
            int filass = Integer.parseInt(dimensiones[0]); // Número de filas
            int columnass = Integer.parseInt(dimensiones[1]); // Número de columnas

            // Reiniciar el tablero con la nueva dimensión
            tableroGUILogica.reiniciarTableroConDimension(filass, columnas);
        });
        
        
        JButton reiniciarButton = new JButton("Reiniciar");
        reiniciarButton.setBackground(Color.BLUE);
        reiniciarButton.setForeground(Color.WHITE);
        reiniciarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        reiniciarButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
     // Agregamos un ActionListener al botón "Reiniciar"
        reiniciarButton.addActionListener(e -> tableroGUILogica.reiniciarTablero()); // Agregar ActionListener para reiniciar el tablero
        botonesPanel.add(reiniciarButton);
        botonesPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Agrega un espacio de 10px

        JButton top10Button = new JButton("Top-10");
        top10Button.setBackground(Color.BLUE);
        top10Button.setForeground(Color.WHITE);
        top10Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        top10Button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        botonesPanel.add(top10Button);
        botonesPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Agrega un espacio de 10px
        
        
        top10Button.addActionListener(e -> {
            JDialog top10Dialog = new JDialog();
            top10Dialog.setTitle("Top-10 Jugadores");
            top10Dialog.setModal(true);
            top10Dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JPanel top10Panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;

                    // Dibujar un diseño de puntaje de videojuegos con Java2D
                    int x = 20;
                    int y = 30;
                    int fontSize = 16;
                    int lineHeight = 30;

                    g2d.setFont(new Font("Arial", Font.BOLD, fontSize));

                    // Colores personalizados para los textos
                    Color[] playerColors = {
                            Color.RED,
                            Color.BLUE,
                            Color.GREEN,
                            Color.ORANGE,
                            Color.MAGENTA,
                            Color.CYAN,
                            Color.PINK,
                            Color.YELLOW,
                            Color.LIGHT_GRAY,
                            Color.DARK_GRAY
                    };

                    Top10 top10 = new Top10();
                    top10.cargarRecords(new File("data/top10.csv"));

                    int counter = 1;
                    for (RegistroTop10 registro : top10.darRegistros()) {
                        // Utiliza colores personalizados para cada registro
                        g2d.setColor(playerColors[counter - 1]);
                        g2d.drawString(counter + ". " + registro.darNombre() + " - " + registro.darPuntos(), x, y);
                        y += lineHeight;
                        counter++;
                        if (counter == 11) { // Muestra solo los 8 primeros registros
                            break;
                        }
                    }
                }

                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(300, 400);
                }
            };

            // Cambiar el color de fondo para un mejor contraste
            top10Panel.setBackground(Color.BLACK); // Cambia a un color de fondo que contraste

            JScrollPane scrollPane = new JScrollPane(top10Panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            top10Dialog.add(scrollPane);

            top10Dialog.setSize(220, 300);
            top10Dialog.setLocationRelativeTo(null);
            top10Dialog.setVisible(true);
        });
        
        JButton cambiarJugadorButton = new JButton("Cambiar Jugador");
        cambiarJugadorButton.setBackground(Color.BLUE);
        cambiarJugadorButton.setForeground(Color.WHITE);
        cambiarJugadorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cambiarJugadorButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        botonesPanel.add(cambiarJugadorButton);

        

        juegoPanel.add(panelTablero, BorderLayout.WEST);
        juegoPanel.add(botonesPanel, BorderLayout.EAST);


        // Panel inferior
        JPanel infoJugadorPanel = new JPanel();
        infoJugadorPanel.setLayout(new FlowLayout());
        infoJugadorPanel.setForeground(Color.WHITE);

        JLabel jugadasLabel = new JLabel("Jugadas: ");
        infoJugadorPanel.add(jugadasLabel);

        JTextField cantidadJugadas = new JTextField(5);
        cantidadJugadas.setEditable(false);
        cantidadJugadas.setText(Integer.toString(tablero.darJugadas()));
        infoJugadorPanel.add(cantidadJugadas);
        
        tableroGUILogica = new TableroGUILogica(panelTablero, tablero, filas,cantidadJugadas);

        
        JLabel nombreJugadorLabel = new JLabel("Nombre del Jugador: ");
        infoJugadorPanel.add(nombreJugadorLabel);
        
        JTextField nombreJugadorField = new JTextField(15);
        infoJugadorPanel.add(nombreJugadorField);

        getContentPane().add(opcionesSuperioresPanel, BorderLayout.NORTH);
        getContentPane().add(juegoPanel, BorderLayout.CENTER);
        getContentPane().add(infoJugadorPanel, BorderLayout.SOUTH);
        
     // Agregar ItemListener a los JRadioButtons
        facilRadioButton.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                nuevoButton.setEnabled(true); // Habilitar el botón "Nuevo"
            }
        });

        mediaRadioButton.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                nuevoButton.setEnabled(true); // Habilitar el botón "Nuevo"
            }
        });

        dificilRadioButton.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                nuevoButton.setEnabled(true); // Habilitar el botón "Nuevo"
            }
        });
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PantallaJuegoGUI juego = new PantallaJuegoGUI();
            juego.setVisible(true);
        });
    }
}
