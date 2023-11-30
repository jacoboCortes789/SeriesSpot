package SeriesSpot;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.util.List;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.net.URLEncoder;
import org.json.JSONArray;
import org.json.JSONObject;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class TmdbApiConnector {
    public void searchMovie(String movieTitle, JTextArea resultArea, JComboBox<String> movieSelectionComboBox, List<MovieDetails> movieList, JLabel movieImageLabel) {
        try {
            // Codificar el título de la película para que sea seguro para URL
            String encodedTitle = URLEncoder.encode(movieTitle, "UTF-8");

            // URL de la API de TMDb y tu clave de API
            String apiKey = "3a6a06ad4b962d931202ec9a98750489";
            String apiUrl = "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=" + encodedTitle;

            // Crear una conexión HTTP
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar la solicitud
            connection.setRequestMethod("GET");

            // Leer la respuesta de la API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Limpiar el área de resultados
            resultArea.setText("");

            // Limpiar la lista de películas y el JComboBox
            movieList.clear();
            movieSelectionComboBox.removeAllItems();

            // Analizar la respuesta JSON
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray results = jsonResponse.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject movieData = results.getJSONObject(i);

                // Extraer datos de la película
                String title = movieData.getString("title");
                String overview = movieData.getString("overview");
                String releaseDate = movieData.getString("release_date");
                double voteAverage = movieData.getDouble("vote_average");
                int voteCount = movieData.getInt("vote_count");

                // Verificar si el campo "poster_path" es nulo
                String posterPath = movieData.isNull("poster_path") ? "" : movieData.getString("poster_path");

                // Agregar el título y detalles de la película al área de resultados
                resultArea.append("Título: " + title + "\n");
                resultArea.append("Resumen: " + overview + "\n");
                resultArea.append("Fecha de lanzamiento: " + releaseDate + "\n");
                resultArea.append("Promedio de votos: " + voteAverage + "\n");
                resultArea.append("Cantidad de votos: " + voteCount + "\n\n");

                // Agregar la película a la lista
                MovieDetails movie = new MovieDetails(title, overview, releaseDate, voteAverage, voteCount, posterPath);
                movieList.add(movie);

                // Agregar el título de la película al JComboBox
                movieSelectionComboBox.addItem(title);
            }

            // Cerrar la conexión
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de errores al cargar la imagen
            // Puedes mostrar un mensaje de error en lugar de dejar el JLabel vacío
            movieImageLabel.setIcon(null);
        } catch (Exception e) {
            e.printStackTrace();
            resultArea.setText("Error al buscar la película.");
        }
    }

    public void showMoviePoster(MovieDetails movie, JLabel movieImageLabel) {
        try {
            if (movie.getPosterPath() != null && !movie.getPosterPath().isEmpty()) {
                // Construye la URL de la imagen del póster (puedes ajustar el tamaño si es necesario)
                String baseUrl = "https://image.tmdb.org/t/p/w500"; // Cambia el tamaño si es necesario
                String posterUrl = baseUrl + movie.getPosterPath();
                URL url = new URL(posterUrl);

                // Descarga la imagen desde la URL
                BufferedImage image = ImageIO.read(url);

                // Ajusta el tamaño de la imagen si es necesario
                Image scaledImage = image.getScaledInstance(150, 200, Image.SCALE_SMOOTH);

                // Actualiza el icono del JLabel con la imagen descargada
                movieImageLabel.setIcon(new ImageIcon(scaledImage));
            } else {
                // Si no hay una URL de póster disponible, muestra un mensaje o icono predeterminado
                // Puedes personalizar esto según tus necesidades
                movieImageLabel.setIcon(null);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de errores al cargar la imagen
            // Puedes mostrar un mensaje de error en lugar de dejar el JLabel vacío
            movieImageLabel.setIcon(null);
        }
    }
    public void showRecommendation(String recommendation, JTextArea resultArea) {
    resultArea.append("Recomendación: " + recommendation + "\n");
}}










