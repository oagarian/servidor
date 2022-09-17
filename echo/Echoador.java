//Área dos imports
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import static java.net.HttpURLConnection.HTTP_OK;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
//Inicio da classe Echoador.
public class Echoador implements HttpHandler {

    public static final String PATH = "/echo";
    //@Override irá sobescrever o HttpHandler.
    @Override
    public void handle(HttpExchange conn) throws IOException {
        //Declara os objetos de requisicao e resposta (IS e OS);
        InputStream fluxoCorpoRequisicao = conn.getRequestBody();
        OutputStream fluxoCorpoResposta = conn.getResponseBody();

        //Cria um scanner utilizando do IS RequestBody;
        Scanner scanner = new Scanner(fluxoCorpoRequisicao);
        try {
            // Pega uma linha do corpo da requisição.
            String corpoRequisicao = scanner.nextLine();

            //Realiza a separação da String em um vetor.
            String[] escrita = corpoRequisicao.split("-");
            //Variáveis de acordo com a separação.
            String word = escrita[0];
            String number = escrita[1];
            //Retirando todas as letras existentes no lado em que sera recolhido o número.
            String number_test = number.replaceAll("[^0-9]", "");
            int RealNumber = Integer.parseInt(number_test);
            //Variável auxiliar com contagem de vezes.
            String original = "Repetindo " + RealNumber + " vezes.";
            //Declarando variavel de bytes antes do laço de repetição.
            byte[] original_bytes = word.getBytes();
            //Verificação se um dado foi ou não informado.
            if(word == "" ||  RealNumber <= 0){
                word = "Não foram informados dados suficientes";
                RealNumber = 1;
            }
            //Laço de repetição.
            for(int i = 1; i <= RealNumber; i++){
                //Concatenação em linhas e declarando bytes.
                original += ("\n");
                original = original.concat(word);
                original_bytes = original.getBytes();
            }
            
            //Envio dos headers e escrita dos bites.
            conn.sendResponseHeaders(HTTP_OK, original_bytes.length);
            fluxoCorpoResposta.write(original_bytes);
        } finally {
            //Fechamento da index e do scanner.
            conn.close();
            scanner.close();
        }
    }
}
