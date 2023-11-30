package SeriesSpot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class APIRequest {
    public static String getApiResponse(String movieTitle) {
        try {
            // URL de la API con el título de la película proporcionado
            String apiUrl = "https://dannielarodriguez-project-mlops.onrender.com/recomendacion/" + movieTitle;

            // Crear una conexión HTTP
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar la solicitud
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "application/json");

            // Leer la respuesta de la API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Cerrar la conexión
            connection.disconnect();

            // Devolver la respuesta JSON como una cadena
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de errores: puedes lanzar una excepción personalizada o devolver un mensaje de error, según tus necesidades.
            return "Error al realizar la solicitud a la API";
        }
    }
    public static String getRecommendation(String movieTitle) {
    try {
        String apiUrl = "https://dannielarodriguez-project-mlops.onrender.com/recomendacion/" + movieTitle;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("accept", "application/json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        connection.disconnect();

        return response.toString();
    } catch (Exception e) {
        e.printStackTrace();
        return "Error al obtener la recomendación de la segunda API";
    }
}

    public static void main(String[] args) {
        // Utilizar un Scanner para obtener el título de la película
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa el título de la película: ");
        String movieTitle = scanner.nextLine();

        // Llamar al método getApiResponse con el título proporcionado
        String apiResponse = getApiResponse(movieTitle);
        System.out.println(apiResponse);
    }
}