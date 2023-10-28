package service;

import request.JoinGameRequest;
import result.JoinGameResult;
/**
 * The service for joining a game using a request and result.
 */
public class JoinGameService {
    JoinGameRequest request;
    public JoinGameService(JoinGameRequest request) {this.request=request;}
    /**
     * 	Verifies that the specified game exists,
     * 	and, if a color is specified,
     * 	adds the caller as the requested color to the game.
     * 	If no color is specified, the user is joined as an observer.
     * 	This request is idempotent.
     * @param request
     * @return
     */
    public JoinGameResult joinGame(JoinGameRequest request) {
        try {
            // request header for user
            return new JoinGameResult(request.getPlayerColor(),request.getGameID(),null);
        } catch (Exception e) {
            //return new JoinGameResult(null,0,"Error: bad request"); //400
            //return new JoinGameResult(null,0,"Error: unauthorized"); //401
            //return new JoinGameResult(null,0,"Error: already taken"); //403
            return new JoinGameResult(null,0,"Error: description"); //500
        }
    }
}
