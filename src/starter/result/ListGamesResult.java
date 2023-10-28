package result;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The result of a List Games request.
 */
public class ListGamesResult extends _Result {

    /**
     * An array to store games by Map of Strings and Objects
     */
    private ArrayList<HashMap<String, Object>> games;
    public ListGamesResult() {}
    public ArrayList<HashMap<String, Object>> getGames() {
        return games;
    }
    public void setGames(ArrayList<HashMap<String, Object>> games) {
        this.games = games;
    }
}
