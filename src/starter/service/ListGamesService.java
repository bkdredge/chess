package service;

import request.ListGamesRequest;
import result.ListGamesResult;
/**
 * The service for listing the games in the database using a request and result.
 */
public class ListGamesService {
    ListGamesRequest request;
    public ListGamesService(ListGamesRequest request) {this.request=request;}
    /**
     * Gives a list of all games.
     * @param request
     * @return
     */
    public ListGamesResult listGames(ListGamesRequest request) {
        try{
            return new ListGamesResult(null);
        } catch (Exception e) {
            return new ListGamesResult("Error: description");
            //return new ListGamesResult(null,"Error: unauthorized");
        }
    }
}
