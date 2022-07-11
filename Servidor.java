import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;

public class Servidor {



    public static void main(String[] args) throws IOException {
        //Declarando backlog (máximo de entradas), endereço socket(port), e criando o servidor.
        int BACKLOG = 10;
        InetSocketAddress enderecoSocket = new InetSocketAddress(9999);
        HttpServer server = HttpServer.create(enderecoSocket, BACKLOG);
        server.setExecutor(Executors.newSingleThreadExecutor());

        // Registrando caminhos que devem ser atendidos.
        server.createContext(IndexHandler.PATH, new IndexHandler());
        server.createContext(Echoador.PATH, new Echoador());

        // Iniciando servidor.
        server.start();
        System.out.printf("Servidor ouvindo requisições na porta %s\n\n", server.getAddress().getPort());
    }
}