import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class DataSender {
    public static void main(String[] args) {
        try {
            // Define the URL of the server endpoint
            URI uri = new URI("http://localhost:8080/api/endpoint"); // Corrected the URL

            // Create the HttpURLConnection object
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");

            // Set headers
            connection.setRequestProperty("Content-Type", "application/json");

            // Enable output and disable caching
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            // Define the data you want to send (e.g., as a JSON string)
            String jsonData = "{\"key1\": \"value1\", \"key2\": \"value2\"}";

            // Convert data to bytes
            byte[] postData = jsonData.getBytes(StandardCharsets.UTF_8);

            // Get the output stream of the connection and write data to it
            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(postData);
            }

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response code: " + responseCode);

            // Read and print the response from the server
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                StringBuilder response = new StringBuilder();
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    response.append(new String(buffer, 0, bytesRead));
                }
                System.out.println("Response from server: " + response.toString());
            } else {
                System.out.println("Failed to receive response from server.");
            }

            // Disconnect the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
