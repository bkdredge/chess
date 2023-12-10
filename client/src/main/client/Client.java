package client;
import ui.Repl;
import java.net.URI;
public class Client {
    public static void main(String[] args) throws Exception {
        new Repl(new URI("http://localhost:8080")).run();
    }
}