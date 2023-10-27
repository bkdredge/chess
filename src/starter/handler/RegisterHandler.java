package handler;

import com.google.gson.Gson;
import request.JoinGameRequest;
import request.RegisterRequest;
import result.JoinGameResult;
import result.RegisterResult;
import service.JoinGameService;
import service.RegisterService;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * A handler for registering a new user account.
 */
public class RegisterHandler implements Route{
    public RegisterHandler(){}
    @Override
    public Object handle(Request request, Response response) throws Exception {
        // Do I use my Request and Response classes still?
        // Do I invoke a service here? And if so, do I need to pass parameters to it?
        // ... like username, password, email, auth token?
        // Do I interact with DAOs here?
        RegisterRequest registerRequest = new Gson().fromJson(request.body(), RegisterRequest.class);

        // it takes the information from the request body register request
        /*
        if (RegisterResponse.message() == whatevert
            response.status(403)
            response.set Message ("")
            Error
        }
         */
        RegisterResult registerResult= null;
        return new Gson().toJson(registerResult);




        /*
        try
        registerreq req = gson from Json
        service > result > userDAO dao user user(req getusername...)
        authaToken = dao inserUser user
        return new RegisterRes(usergetusername, authtoken get athotken)
        else return

        ///
        if it has the msaage set this status code response


         */
    }
}
