package service;

import request.ClearDataRequest;
import result.ClearDataResult;
/**
 * The service for clearing data using a request and result.
 */
public class ClearDataService {
    ClearDataRequest request;
    public ClearDataService(ClearDataRequest request) {this.request=request;}
    /**
     * Clears the database. Removes all users, games, and authTokens.
     * @param request
     * @return
     */
    public ClearDataResult clear(ClearDataRequest request) {
        try {

            return new ClearDataResult(null);
        } catch (Exception e) {
            return new ClearDataResult("Error: description");
        }
    }
}
