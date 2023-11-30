
package SeriesSpot;

import java.net.URLEncoder;
import javax.swing.JTextArea;

public class RecommendationApiConnector {
    public void searchRecommendations(String movieTitle, JTextArea resultArea) {
        try {
            // Codificar el título de la película para que sea seguro para URL
            String encodedTitle = URLEncoder.encode(movieTitle, "UTF-8");

            // URL de la API de recomendación y tu clave de API
            String apiUrl = "https://dannielarodriguez-project-mlops.onrender.com/recomendacion/" + encodedTitle;

            // Obtener la respuesta de la API de recomendación
            String apiResponse = APIRequest.getApiResponse(apiUrl);

            // Mostrar la respuesta en el área de resultados
            resultArea.append("\nResultados de la API de Recomendación:\n" + apiResponse + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            resultArea.append("Error al buscar recomendaciones.");
        }
    }
}