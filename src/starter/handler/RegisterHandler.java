package handler;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import model.User;
import request.JoinGameRequest;
import request.RegisterRequest;
import result.JoinGameResult;
import result.RegisterResult;
import service.JoinGameService;
import service.RegisterService;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A handler for registering a new user account.
 */
public class RegisterHandler implements Route{
    Collection<User> users=new ArrayList<User>();
    public RegisterHandler(){}
    @Override
    public Object handle(Request request, Response response) throws Exception {
        System.out.println(request.body());
        Gson gson = new Gson();
        RegisterRequest registerRequest = gson.fromJson(request.body(), RegisterRequest.class);
        RegisterService registerService = new RegisterService(registerRequest); //need to fix
        RegisterResult registerResult = registerService.register(registerRequest);
        if (registerResult.getUsername() != null && registerResult.getAuthToken() != null) {
            response.status(200);
            System.out.println("Good!");
        }
        else if (registerResult.getMessage() == "Error: bad request") {response.status(400);}
        else if (registerResult.getMessage() == "Error: already taken") {response.status(403);}
        else if (registerResult.getMessage() == "Error: description") {response.status(500);}
        return gson.toJson(registerResult);
        // Do I use my Request and Response classes still?
        // Do I invoke a service here? And if so, do I need to pass parameters to it?
        // ... like username, password, email, auth token?
        // Do I interact with DAOs here?
                // it takes the information from the request body register request
        /*
        if (RegisterResponse.message() == whatevert
            response.status(403)
            response.set Message ("")
            Error
        }
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
