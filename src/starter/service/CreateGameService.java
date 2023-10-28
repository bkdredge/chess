package service;
import model.Game;
import request.CreateGameRequest;
import result.CreateGameResult;
/**
 * The service for creating a game using a request and result.
 */
public class CreateGameService {
    CreateGameRequest request;
    public CreateGameService(CreateGameRequest request) {this.request=request;}
    /**
     * Creates a new game.
     * @param request
     * @return
     */
    public CreateGameResult createGame(CreateGameRequest request) {
        try{
            return new CreateGameResult(request.getGameName(), null);
        } catch (Exception e) {
            //return new CreateGameResult(null, "Error: bad request"); //400
            //return new CreateGameResult(null, "Error: authorized"); //401
            return new CreateGameResult(null, "Error: description"); //500
        }
    }
}
