
package series.spot;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TheMoviedb_API {

    private static final String API_KEY = "TU_API_KEY_DE_TMDB";
    private static final String API_URL = "https://api.themoviedb.org/3/search/movie";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el título de la película que deseas buscar: ");
        String query = scanner.nextLine();

        try {
            // Construir la URL de la solicitud a la API de TMDb
            String apiUrl = API_URL + "?api_key=" + API_KEY + "&query=" + query;
            URL url = new URL(apiUrl);

            // Realizar la solicitud HTTP GET
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Leer la respuesta JSON
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Analizar la respuesta JSON
            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
            JsonArray results = jsonResponse.getAsJsonArray("results");

            System.out.println("Resultados de la búsqueda:");
            for (int i = 0; i < results.size(); i++) {
                JsonObject movie = results.get(i).getAsJsonObject();
                String title = movie.get("title").getAsString();
                String releaseDate = movie.get("release_date").getAsString();
                System.out.println("Título: " + title);
                System.out.println("Fecha de lanzamiento: " + releaseDate);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}



    

