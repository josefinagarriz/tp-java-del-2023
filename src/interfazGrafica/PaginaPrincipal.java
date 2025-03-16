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
        JPanel panelCentral = new JPanel(new BorderLayout());

        // Sección de Álbumes
        JPanel panelAlbumes = new JPanel();
        panelAlbumes.setLayout(new BoxLayout(panelAlbumes, BoxLayout.X_AXIS)); // Hace que los botones estén en fila
        panelAlbumes.setBorder(BorderFactory.createTitledBorder("TUS ALBUMES:"));
        for (int i = 1; i <= 18; i++) {
            panelAlbumes.add(new JButton(escalarJPG("/interfazGrafica/recursos/album.jpg", 80, 80)));
            panelAlbumes.add(Box.createRigidArea(new Dimension(10, 0))); // Espacio de 10px entre botones
        }
        // **Forzar tamaño para que desborde horizontalmente**
        panelAlbumes.setPreferredSize(new Dimension(18 * 90 + (18 * 10), 100)); // Se ajusta según la cantidad de álbumes

        // **Scroll horizontal**
        JScrollPane scrollAlbumes = new JScrollPane(panelAlbumes, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Contenedor para ajustar altura
        JPanel contenedorAlbumes = new JPanel(new BorderLayout());
        contenedorAlbumes.setPreferredSize(new Dimension(800, 120)); // Más angosto
        contenedorAlbumes.add(scrollAlbumes, BorderLayout.CENTER);

        panelCentral.add(contenedorAlbumes, BorderLayout.NORTH);

        // Sección de Publicaciones
        JPanel panelPublicaciones = new JPanel(new GridLayout(0, 8, 10, 10)); // Filas dinámicas, 3 columnas, con espacio
        panelPublicaciones.setBorder(BorderFactory.createTitledBorder("TUS PUBLICACIONES:"));
        for (int i = 1; i <= 10; i++) {
            panelPublicaciones.add(new JButton(escalarJPG("/interfazGrafica/recursos/imagen.jpg", 110, 110)));
            panelPublicaciones.add(new JButton(escalarJPG("/interfazGrafica/recursos/video.jpg", 110, 110)));
            panelPublicaciones.add(new JButton(escalarJPG("/interfazGrafica/recursos/audio.jpg", 110, 110)));
        }

        // Envolver en otro panel con FlowLayout para centrarlo dentro del JScrollPane
        JPanel contenedorPublicaciones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contenedorPublicaciones.add(panelPublicaciones);

        // Agregar a JScrollPane con barra de desplazamiento vertical
        JScrollPane scrollPublicaciones = new JScrollPane(contenedorPublicaciones, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Ajustar tamaño del contenedor principal
        JPanel wrapperPublicaciones = new JPanel(new BorderLayout());
        wrapperPublicaciones.setPreferredSize(new Dimension(800, 360));
        wrapperPublicaciones.add(scrollPublicaciones);
        panelCentral.add(wrapperPublicaciones, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);

        // Panel inferior con botones de filtro y búsqueda
        JPanel panelInferior = new JPanel();
        panelInferior.add(new JButton("Solo imágenes"));
        panelInferior.add(new JButton("Solo videos"));
        panelInferior.add(new JButton("Solo audios"));
        panelInferior.add(new JButton("Todas las publicaciones"));
        panelInferior.add(new JButton("Estadísticas y reportes"));

        textBuscador = new JTextField(25);
        panelInferior.add(textBuscador);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private ImageIcon escalarJPG(String archivo, int ancho, int alto) {
        ImageIcon icon = new ImageIcon(getClass().getResource(archivo));
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

}
