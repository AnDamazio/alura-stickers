import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello, world");

        //fazer uma conexão HTTP e buscar os top 250 filmes

        String url = "https://imdb-api.com/en/API/Top250Movies/key";
        URI address = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        var parser = new JsonParser();
        //pegar só os dados que interessam (título, poster, classificação)
        List<Map<String, String>> movies = parser.parse(body);
        System.out.println(movies.get(0).get("fullTitle"));
        System.out.println(movies.get(0).get("image"));
        System.out.println(movies.get(0).get("imDbRating"));
        //exibir e manipular os dados
    }
}