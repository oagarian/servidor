//Declaração de imports
import static java.net.HttpURLConnection.HTTP_OK;
import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class IndexHandler implements HttpHandler {

    public static final String PATH = "/";
    //@Override para sobescrever o HttpHandler.
    @Override
    public void handle(HttpExchange conn) throws IOException {
        //Trasnformando a mensagem de informação em bytes.
        byte[] hello = "Ecoador TABAJARA \n utilize /echo e informe os dados separando-os com -".getBytes();

        try {
            // Recebendo os Headers e status.
            conn.sendResponseHeaders(HTTP_OK, hello.length);

            // Formando os cabeçalhos.
            Headers headers = conn.getResponseHeaders();
            headers.add("Content-Type", "text/html; charset=UTF-8");
            //Transcrevendo os bytes na tela.
            try (OutputStream out = conn.getResponseBody()) {
                out.write(hello);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}