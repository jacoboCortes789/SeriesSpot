package SeriesSpot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MovieInfoGUI {
    private JFrame frame;
    private JTextField titleField;
    private JTextArea resultArea;
    private JComboBox<String> movieSelectionComboBox;
    private List<MovieDetails> movieList;
    private MovieDetails selectedMovie;
    private JLabel movieImageLabel;

    public MovieInfoGUI() {
        frame = new JFrame("Series Spot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        Color background = new Color(23, 32, 42);
        Color text = Color.WHITE;  // Cambiado a texto blanco
        Font textFont = new Font("Arial", Font.PLAIN, 14);
        Color darkBackground = new Color(33, 47, 61);  // Nuevo color oscuro
        frame.getContentPane().setBackground(background);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(background);

        // Añadir una barra de búsqueda en el centro
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(background);

        // Crear un JLabel para "SeriesSpot" encima de la barra de búsqueda
        JLabel seriesSpotLabel = new JLabel("SeriesSpot");
        seriesSpotLabel.setFont(new Font("Arial", Font.BOLD, 36));
        seriesSpotLabel.setForeground(text);
        seriesSpotLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(seriesSpotLabel);

        titleField = new JTextField(20);
        titleField.setBackground(Color.GRAY);  // Fondo de la barra de búsqueda gris
        titleField.setForeground(text);
        titleField.setFont(textFont);
        searchPanel.add(titleField);

        JButton searchButton = new JButton("Buscar Película");
        searchButton.setBackground(new Color(0, 102, 204));
        searchButton.setForeground(text);
        searchButton.setFont(textFont);
        searchPanel.add(searchButton);

        centerPanel.add(searchPanel);

        // Añadir el panel al centro de la ventana
        frame.add(centerPanel, BorderLayout.CENTER);

        // Panel central para mostrar el título y la barra de búsqueda
        
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(background);

        JLabel titleLabel = new JLabel("SeriesSpot");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createVerticalStrut(20)); // Espacio entre el título y la barra de búsqueda
        centerPanel.add(searchPanel);
        centerPanel.add(Box.createVerticalGlue());


        frame.add(centerPanel, BorderLayout.CENTER);

        resultArea = new JTextArea();
        resultArea.setBackground(darkBackground);  // Establecer color oscuro
        resultArea.setForeground(text);
        resultArea.setFont(textFont);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);


        movieImageLabel = new JLabel();
        movieImageLabel.setPreferredSize(new Dimension(150, 200));

        movieList = new ArrayList<>();
        selectedMovie = null;

        searchButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String movieTitle = titleField.getText();
        TmdbApiConnector tmdbApiConnector = new TmdbApiConnector();

        // Obtener detalles de la película de TMDB
        tmdbApiConnector.searchMovie(movieTitle, resultArea, movieSelectionComboBox, movieList, movieImageLabel);

        // Obtener recomendación de la segunda API
        String recommendation = APIRequest.getRecommendation(movieTitle);

        // Mostrar la recomendación junto con los detalles de TMDB
        resultArea.append("\nRecomendación: " + recommendation + "\n");
    }
});

        frame.add(searchPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane, BorderLayout.CENTER);

        movieSelectionComboBox = new JComboBox<>();
        movieSelectionComboBox.setBackground(Color.BLACK);
        movieSelectionComboBox.setForeground(text);
        movieSelectionComboBox.setFont(textFont);

        movieSelectionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = movieSelectionComboBox.getSelectedIndex();
                if (selectedIndex >= 0) {
                    MovieDetails selectedMovie = movieList.get(selectedIndex);
                    resultArea.setText("Título: " + selectedMovie.getTitle() + "\n");
                    resultArea.append("Resumen: " + selectedMovie.getOverview() + "\n");
                    resultArea.append("Fecha de lanzamiento: " + selectedMovie.getReleaseDate() + "\n");
                    resultArea.append("Promedio de votos: " + selectedMovie.getVoteAverage() + "\n");
                    resultArea.append("Cantidad de votos: " + selectedMovie.getVoteCount() + "\n");
                    
                    TmdbApiConnector tmdbApiConnector = new TmdbApiConnector(); // Crear una instancia de TmdbApiConnector
                    tmdbApiConnector.showMoviePoster(selectedMovie, movieImageLabel);
                }
            }
        });

        frame.add(movieSelectionComboBox, BorderLayout.SOUTH);

        JPanel movieDetailsPanel = new JPanel();
        movieDetailsPanel.add(movieImageLabel);
        frame.add(movieDetailsPanel, BorderLayout.WEST);
    }

    public void display() {
        frame.setVisible(true);
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public JTextArea getResultArea() {
        return resultArea;
    }

    public JComboBox<String> getMovieSelectionComboBox() {
        return movieSelectionComboBox;
    }

    public List<MovieDetails> getMovieList() {
        return movieList;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MovieInfoGUI movieInfoGUI = new MovieInfoGUI();
                movieInfoGUI.display();
            }
        });
    }
}



