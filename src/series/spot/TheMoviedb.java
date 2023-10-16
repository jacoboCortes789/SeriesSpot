
package series.spot;
import com.themoviedb.tmdb.Tmdb;
import com.themoviedb.tmdb.entities.Movie;
import java.io.IOException;
import java.util.Scanner;


public class TheMoviedb {


    public static void main(String[] args) throws IOException {
        // Crea la API
        Tmdb tmdb = new Tmdb("YOUR_API_KEY");

        // Crea un escáner
        Scanner scanner = new Scanner(System.in);

        // Solicita el título de la película
        System.out.print("Introduce el título de la película: ");
        String title = scanner.nextLine();

        // Realiza la solicitud
        Movie movie = tmdb.searchMovies(title).getResults().get(0);

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
    

