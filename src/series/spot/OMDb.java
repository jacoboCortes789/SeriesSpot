
package series.spot;
import java.io.IOException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
public class OMDb {
 {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Crea un escáner
        Scanner scanner = new Scanner(System.in);

        // Solicita el título de la película
        System.out.print("Introduce el título de la película: ");
        String title = scanner.nextLine();

        // Crea la solicitud
        URL url = new URL("https://www.omdbapi.com/?t=" + title + "&apikey=YOUR_API_KEY");
        HttpRequest request = HttpRequest.newBuilder().uri(url).build();

        // Realiza la solicitud
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Procesa la respuesta
        String json = response.body();
        ObjectMapper mapper = new ObjectMapper();
        Movie movie = mapper.readValue(json, Movie.class);

        // Imprime los resultados
        if (movie != null) {
            System.out.println("Título: " + movie.getTitle());
            System.out.println("Fecha de lanzamiento: " + movie.getReleased());
            System.out.println("Calificación: " + movie.getImdbRating());
        } else {
            System.out.println("No se encontró ninguna película con el título " + title);
        }
    }

    static class Movie {
        private String title;
        private String released;
        private String imdbRating;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getReleased() {
            return released;
        }

        public void setReleased(String released) {
            this.released = released;
        }

        public String getImdbRating() {
            return imdbRating;
        }

        public void setImdbRating(String imdbRating) {
            this.imdbRating = imdbRating;
        }
    }
}

    
