package unitTests;
import org.junit.jupiter.api.*;
import chess.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import model.AuthToken;
import request.*;
import result.*;
import serverFacade.ServerFacade;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.UUID;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ServerFacadeTests {
    URI uri = new URI("http://localhost:8080");
    ServerFacade server = new ServerFacade(uri);
    public ServerFacadeTests() throws URISyntaxException {}
    @BeforeEach public void clearDatabase() throws Exception { server.clearDatabase(); }

    @Test
    @DisplayName("Register New User")
    public void successfullyRegisterNewUser() throws Exception {
        RegisterRequest request = new RegisterRequest("Barbie", "Barbie@gmail.com", "best2023movie");
        RegisterResult response = server.register(request);
        assertNull(response.getMessage());
        assertNotNull(response.getAuthToken());
        assertEquals("Barbie", response.getUsername());
    }
    @Test
    @DisplayName("Register Existing User")
    public void alreadyTakenRegisterUser() throws Exception {
        RegisterRequest request1 = new RegisterRequest("Barbie", "Barbie@gmail.com", "best2023movie");
        RegisterResult response1 = server.register(request1);
        assertNull(response1.getMessage());
        RegisterRequest request2 = new RegisterRequest("Barbie", "Midge@gmail.com", "midgeWhat");
        RegisterResult response2 = server.register(request2);
        assertEquals(403, response2.getCode().code);
    }
    @Test
    @DisplayName("Login")
    public void successfullyLoginUser() throws Exception {
        server.register(new RegisterRequest("Barbie", "Barbie@gmail.com", "best2023movie"));
        LoginRequest request = new LoginRequest("Barbie", "best2023movie");
        LoginResult response = server.login(request);
        assertNull(response.getMessage());
        assertNotNull(response.getAuthToken());
    }
    @Test
    @DisplayName("Login Unauthorized User")
    public void unauthorizedLoginUser() throws Exception{
        LoginResult response = server.login(new LoginRequest("Midge", "Whatareyoudoinghere"));
        assertEquals(401, response.getCode().code);
        assertNull(response.getAuthToken());
    }
    @Test
    @DisplayName("Logout")
    public void successfullyLogoutUser() throws Exception{
        server.register(new RegisterRequest("Barbie", "Barbie@gmail.com", "best2023movie"));
        LoginResult response = server.login(new LoginRequest("Barbie", "best2023movie"));
        String token = response.getAuthToken();
        LogoutRequest logoutRequest = new LogoutRequest(token);
        LogoutResult logoutResponse = server.logout(logoutRequest);
        assertNull(logoutResponse.getMessage());
    }
    @Test
    @DisplayName("Logout Unauthorized User")
    public void unauthorizedLogoutUser() throws Exception{
        UUID uuid = UUID.randomUUID();
        LogoutRequest request = new LogoutRequest(uuid.toString());
        LogoutResult response = server.logout(request);
        assertEquals(401, response.getCode().code);
    }
    @Test
    @DisplayName("Create New Game")
    public void successfullyCreateNewGame() throws Exception{
        RegisterRequest request = new RegisterRequest("Oppenheimer", "Oppenheimer@gmail.com", "bomb.com");
        RegisterResult response = server.register(request);
        String token = response.getAuthToken();
        CreateGameRequest createGameRequest = new CreateGameRequest(token, "WorldWarII");
        CreateGameResult createGameResponse = server.createGame(createGameRequest);
        assertNull(createGameResponse.getMessage());
        assertNotNull(createGameResponse.getGameID());
    }
    @Test
    @DisplayName("Unauthorized To Create Game")
    public void unauthorizedCreateGame() throws Exception{
        UUID uuid = UUID.randomUUID();
        CreateGameRequest request = new CreateGameRequest(uuid.toString(), "unauthorizedGame");
        CreateGameResult response = server.createGame(request);
        assertEquals(401, response.getCode().code);
        assertEquals(0,response.getGameID());
    }
    @Test
    @DisplayName("List Games")
    public void successfullyListGames() throws Exception{
        RegisterRequest request = new RegisterRequest("Sonic", "Sonic@gmail.com", "fastestHedgehog");
        RegisterResult response = server.register(request);
        String token = response.getAuthToken();

        CreateGameRequest createGameRequest1 = new CreateGameRequest(token, "Game1");
        server.createGame(createGameRequest1);
        CreateGameRequest createGameRequest2 = new CreateGameRequest(token, "Game2");
        server.createGame(createGameRequest2);

        ListGamesRequest listGamesRequest = new ListGamesRequest(token);
        ListGamesResult listGamesResponse = server.listGames(listGamesRequest);
        assertNull(listGamesResponse.getMessage());
        assertEquals(2, listGamesResponse.getGames().size());
    }
    @Test
    @DisplayName("Unauthorized List Games")
    public void unauthorizedListGames() throws Exception{
        UUID uuid = UUID.randomUUID();
        ListGamesRequest request = new ListGamesRequest(uuid.toString());
        ListGamesResult response = server.listGames(request);
        assertEquals(401, response.getCode().code);
        assertNull(response.getGames());
    }
    @Test
    @DisplayName("Two Players Join Game")
    public void successfullyJoinGame() throws Exception{
        RegisterRequest request1 = new RegisterRequest("Sonic", "Sonic@gmail.com", "fastestHedgehog");
        RegisterResult response1 = server.register(request1);
        String token1 = response1.getAuthToken();
        RegisterRequest request2 = new RegisterRequest("Eggman", "Eggman@gmail.com", "dominateWorld");
        RegisterResult response2 = server.register(request2);
        String token2 = response2.getAuthToken();

        CreateGameResult createGameResponse = server.createGame(new CreateGameRequest(token1, "GetEmeralds"));
        Integer gameID = createGameResponse.getGameID();

        JoinGameRequest joinGameRequest1 = new JoinGameRequest(token1, "BLACK", gameID);
        JoinGameResult joinGameResponse1 = server.joinGame(joinGameRequest1);

        JoinGameRequest joinGameRequest2 = new JoinGameRequest(token2, "WHITE", gameID);
        JoinGameResult joinGameResponse2 = server.joinGame(joinGameRequest2);

        assertNull(joinGameResponse1.getMessage());
        assertNull(joinGameResponse2.getMessage());
    }
    @Test
    @DisplayName("Unauthorized To Join Game")
    public void unauthorizedJoinGame() throws Exception{
        UUID uuid = UUID.randomUUID();

        RegisterRequest request = new RegisterRequest("HomerSimpson1989", "homer@gmail.com", "lisaknowsit");
        RegisterResult response = server.register(request);
        String token = response.getAuthToken();
        CreateGameRequest createGameRequest = new CreateGameRequest(token, "DonutGame");
        CreateGameResult createGameResponse = server.createGame(createGameRequest);

        JoinGameRequest joinGameRequest = new JoinGameRequest(uuid.toString(), "WHITE", createGameResponse.getGameID());
        JoinGameResult joinGameResponse = server.joinGame(joinGameRequest);

        assertEquals(401, joinGameResponse.getCode().code);
    }
    @Test
    @DisplayName("Already Taken Join Game")
    public void alreadyTakenJoinGame() throws Exception{
        RegisterRequest request1 = new RegisterRequest("Burns", "powerplant@gmail.com", "lookatmyvest");
        RegisterResult response1 = server.register(request1);
        String token1 = response1.getAuthToken();
        RegisterRequest request2 = new RegisterRequest("Moe","moes@gmail.com", "heymidge");
        RegisterResult response2 = server.register(request2);
        String token2 = response2.getAuthToken();
        RegisterRequest request3 = new RegisterRequest("Apu", "quikemart@gmail.com", "hellopleasecomeagain");
        RegisterResult response3 = server.register(request3);
        String token3 = response3.getAuthToken();

        CreateGameResult createGameResponse = server.createGame(new CreateGameRequest(token1, "ThrivingBusiness"));
        Integer gameID = createGameResponse.getGameID();

        JoinGameRequest joinGameRequest1 = new JoinGameRequest(token1, "BLACK", gameID);
        server.joinGame(joinGameRequest1);

        JoinGameRequest joinGameRequest2 = new JoinGameRequest(token2, "WHITE", gameID);
        server.joinGame(joinGameRequest2);

        JoinGameRequest joinGameRequest3 = new JoinGameRequest(token3, "WHITE", gameID);
        JoinGameResult joinGameResponse3 = server.joinGame(joinGameRequest3);

        JoinGameRequest joinGameRequest4 = new JoinGameRequest(token3, "BLACK", gameID);
        JoinGameResult joinGameResponse4 = server.joinGame(joinGameRequest4);

        assertEquals(403, joinGameResponse3.getCode().code);
        assertEquals(403, joinGameResponse4.getCode().code);
    }
}