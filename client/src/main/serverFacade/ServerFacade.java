package serverFacade;
import chess.*;
import chessStarter.ChessBoard;
import chessStarter.ChessGame;
import chessStarter.ChessPiece;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import model.AuthToken;
import request.*;
import result.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class ServerFacade {
    URI uri;
    static class ResponseObj<T>{
        public ResponseObject responseCode; public String errorMessage; public T body;
    }
    public ServerFacade(URI uri){this.uri = uri;}
    public void clearDatabase() throws Exception {
        var httpConnection = sendRequest(uri + "/db", "DELETE", null, null);
        httpConnection.getResponseCode();
    }
    public RegisterResult register(RegisterRequest req) throws Exception {
        var body = new Gson().toJson(Map.of("username", req.getUsername() ,  "password", req.getPassword(), "email", req.getEmail()));
        var httpConnection = sendRequest(uri + "/user", "POST", body, null);
        ResponseObj<AuthToken> resObj = getResponse(httpConnection, AuthToken.class);
        if(resObj.responseCode == ResponseObject.SUCCESS) return new RegisterResult(resObj.body);
        else return new RegisterResult(resObj.responseCode, resObj.errorMessage);
    }
    public LoginResult login(LoginRequest req) throws Exception {
        var body = new Gson().toJson(Map.of("username", req.getUsername(),"password", req.getPassword()));
        var httpConnection = sendRequest(uri + "/session", "POST", body, null);
        ResponseObj<AuthToken> resObj = getResponse(httpConnection, AuthToken.class);
        if(resObj.responseCode == ResponseObject.SUCCESS) return new LoginResult(resObj.body);
        else return new LoginResult(resObj.responseCode, resObj.errorMessage);
    }
    public LogoutResult logout(LogoutRequest req) throws Exception {
        var httpConnection = sendRequest(uri + "/session", "DELETE", null, req.getAuthToken());
        ResponseObj<AuthToken> resObj = getResponse(httpConnection, null);
        if(resObj.responseCode == ResponseObject.SUCCESS) return new LogoutResult(resObj.responseCode);
        else return new LogoutResult(resObj.responseCode, resObj.errorMessage);
    }
    public CreateGameResult createGame(CreateGameRequest req) throws Exception {
        var body = new Gson().toJson(Map.of("gameName", req.getGameName()));
        var httpConnection = sendRequest(uri + "/game", "POST", body, req.getAuthToken());
        ResponseObj<CreateGameResult> resObj = getResponse(httpConnection, CreateGameResult.class);
        if(resObj.responseCode == ResponseObject.SUCCESS) return resObj.body;
        else return new CreateGameResult(resObj.responseCode, resObj.errorMessage);
    }
    public ListGamesResult listGames(ListGamesRequest req) throws Exception {
        var httpConnection = sendRequest(uri + "/game", "GET", null, req.getAuthToken());
        ResponseObj<ListGamesResult> resObj = getResponse(httpConnection, ListGamesResult.class);
        if(resObj.responseCode == ResponseObject.SUCCESS) return resObj.body;
        else return new ListGamesResult(resObj.responseCode, resObj.errorMessage);
    }
    public JoinGameResult joinGame(JoinGameRequest req) throws Exception {
        var body = new Gson().toJson(Map.of("playerColor", req.getColor(), "gameID", req.getGameID()));
        var httpConnection = sendRequest(uri + "/game", "PUT", body, req.getAuthToken());
        ResponseObj<JoinGameResult> resObj = getResponse(httpConnection, JoinGameResult.class);
        if(resObj.responseCode == ResponseObject.SUCCESS) return resObj.body;
        else return new JoinGameResult(resObj.responseCode, resObj.errorMessage);
    }
    private HttpURLConnection sendRequest(String url, String method, String body, String authToken) throws URISyntaxException, IOException {
        URI uri = new URI(url);
        HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
        http.setRequestMethod(method);
        if(authToken != null) http.addRequestProperty("authorization", authToken);
        if(body != null){
            http.addRequestProperty("Content-Type", "application/json");
            writeRequest(body, http);
        } http.connect(); return http;
    }
    private void writeRequest(String body, HttpURLConnection http) throws IOException {
        if (!body.isEmpty()) {
            http.setDoOutput(true);
            try (var outputStream = http.getOutputStream()) { outputStream.write(body.getBytes()); }
        }
    }
    private <T>T readResponse(HttpURLConnection http, Class<T> cls) throws IOException {
        T responseBody = null;
        try (InputStream body = http.getInputStream()) {
            InputStreamReader inputStreamReader = new InputStreamReader(body);
            responseBody = getSerializer().fromJson(inputStreamReader, cls);
        } return responseBody;
    }
    private <T>ResponseObj<T> getResponse(HttpURLConnection http, Class<T> cls) throws IOException {
        ResponseObj<T> response = new ResponseObj<T>();
        response.responseCode = ResponseObject.fromInteger(http.getResponseCode());
        response.errorMessage = http.getResponseMessage();
        if(cls != null && http.getResponseCode() == 200) response.body = readResponse(http, cls);
        return response;
    }
    private Gson getSerializer() {
        class GameInstanceCreator implements InstanceCreator<ChessGame> {
            @Override public ChessGame createInstance(Type type) {return new ChessGameImpl();}
        }
        class BoardInstanceCreator implements InstanceCreator<ChessBoard> {
            @Override public ChessBoard createInstance(Type type) {return new ChessBoardImpl();}
        }
        class PieceInstanceCreator implements InstanceCreator<ChessPiece> {
            @Override public ChessPiece createInstance(Type type) {return new ChessPieceImpl();}
        }
        var builder = new GsonBuilder();
        builder.registerTypeAdapter(ChessGame.class, new GameInstanceCreator());
        builder.registerTypeAdapter(ChessBoard.class, new BoardInstanceCreator());
        builder.registerTypeAdapter(ChessPiece.class, new PieceInstanceCreator());
        return builder.create();
    }
}