
package series.spot;

import com.themoviedb.api.TmdbApi;
import com.themoviedb.api.model.Movie;
import java.io.IOException;
import java.util.Scanner;


public class TMDb {
    public static void main(String[] args) throws IOException {
        // Crea la API
        TmdbApi api = new TmdbApi("YOUR_API_KEY");

        // Crea un escáner
        Scanner scanner = new Scanner(System.in);

        // Solicita el título de la película
        System.out.print("Introduce el título de la película: ");
        String title = scanner.nextLine();

        // Realiza la solicitud
        Movie movie = api.searchMovies(title).getResults().get(0);

        // Imprime los resultados
        if (movie != null) {
            System.out.println("Título: " + movie.getTitle());
            System.out.println("Fecha de lanzamiento: " + movie.getReleaseDate());
            System.out.println("Calificación: " + movie.getVoteAverage());
        } else {
            System.out.println("No se encontró ninguna película con el título " + title);
        }
    }
}

    
