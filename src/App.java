import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws  Exception {

        // fazer conexão HTTP para buscar os top 250 filmes
        String url = "https://api.themoviedb.org/3/trending/movie/week?api_key=4ba5c0d434e003a484b95cb97fe9f3db";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados interessantes (titulo, imagem, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("backdrop_path"));
            System.out.println(filme.get("vote_average"));
            System.out.println();
        }
    }
}
