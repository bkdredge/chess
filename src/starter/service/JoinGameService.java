package service;

import request.JoinGameRequest;
import result.JoinGameResult;

public class JoinGameService {
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
        return null;
    }
}
