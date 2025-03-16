package interfazGrafica;

import logica.clases.Perfil;
import excepciones.*;
import logica.Reportes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaginaPrincipal extends JFrame{

    private Perfil p;
    private JTextField textBuscador;


    public PaginaPrincipal(Perfil perfil)
    {
        p=perfil;
        inicializarComponenetes();
    }

    public void inicializarComponenetes()
    {
        setTitle("Red Social");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior con nombre, log off y ajustes
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("Red social xxxxxx"));
        panelSuperior.add(new JButton("Log off"));
        panelSuperior.add(new JButton("Ajustes"));
        add(panelSuperior, BorderLayout.NORTH);

        // Panel izquierdo con foto de perfil y datos
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.add(new JLabel(new ImageIcon(getClass().getResource("/interfazGrafica/recursos/perfil.jpg")))); // Imagen de perfil
        panelIzquierdo.add(new JLabel("Nombre:"));
        panelIzquierdo.add(new JLabel("Seguidores:"));
        panelIzquierdo.add(new JLabel("Seguidos:"));
        panelIzquierdo.add(new JLabel("Fecha ingreso:"));
        panelIzquierdo.add(new JLabel("Cant likes:"));
        panelIzquierdo.add(new JLabel("Cant publicaciones:"));
        panelIzquierdo.add(new JButton("PUBLICAR"));
        panelIzquierdo.add(new JButton("NUEVO ALBUM"));
        add(panelIzquierdo, BorderLayout.WEST);

        // Panel central con álbumes y publicaciones
        JPanel panelCentral = new JPanel(new GridLayout(2, 1));

        // Sección de Álbumes
        JPanel panelAlbumes = new JPanel(new FlowLayout());
        panelAlbumes.setBorder(BorderFactory.createTitledBorder("TUS ALBUMES:"));
        for (int i = 1; i <= 6; i++) {
            panelAlbumes.add(new JButton(new ImageIcon("album.jpg")));
        }
        JScrollPane scrollAlbumes = new JScrollPane(panelAlbumes, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelCentral.add(scrollAlbumes);

        // Sección de Publicaciones
        JPanel panelPublicaciones = new JPanel(new FlowLayout());
        panelPublicaciones.setBorder(BorderFactory.createTitledBorder("TUS PUBLICACIONES:"));
        for (int i = 1; i <= 10; i++) {
            panelPublicaciones.add(new JButton(new ImageIcon("publicacion.jpg")));
        }
        JScrollPane scrollPublicaciones = new JScrollPane(panelPublicaciones, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelCentral.add(scrollPublicaciones);

        add(panelCentral, BorderLayout.CENTER);

        // Panel inferior con botones de filtro y búsqueda
        JPanel panelInferior = new JPanel();
        panelInferior.add(new JButton("Solo imágenes"));
        panelInferior.add(new JButton("Solo videos"));
        panelInferior.add(new JButton("Solo audios"));
        panelInferior.add(new JButton("Todas las publicaciones"));
        panelInferior.add(new JButton("Estadísticas y reportes"));

        textBuscador = new JTextField(15);
        panelInferior.add(textBuscador);
        add(panelInferior, BorderLayout.SOUTH);
    }

}
