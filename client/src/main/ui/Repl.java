package ui;

import chess.*;
import chessStarter.ChessBoard;
import chessStarter.ChessGame;
import chessStarter.ChessPiece;
import client.State;
import request.*;
import result.*;
import serverFacade.ServerFacade;

import java.net.URI;
import java.util.HashMap;
import java.util.Scanner;

public class Repl {
    ServerFacade server;
    State state;
    String authToken;
    ChessBoard board;
    public Repl(URI uri){
        this.server = new ServerFacade(uri);
        this.state = State.LOGGED_OUT;
        this.authToken = null;
        board = new ChessBoardImpl();
        board.resetBoard();
    }

    public void run() throws Exception{
        while (true) {
            System.out.printf("[" + state + "] >>> ");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            var args = line.split(" ");
            switch(args[0]){
                case "register": register(args); break;
                case "login": login(args); break;
                case "logout": logout(); break;
                case "create": create(args); break;
                case "list": list(); break;
                case "join": join(args); break;
                case "observe": observe(args); break;
                case "quit": return;
                case "help": default: help(); break;
            }
        }
    }

    private void register(String[] args) throws Exception{
        String username = args[1];
        String password = args[2];
        String email = args[3];
        RegisterResult response = server.register(new RegisterRequest(username, password, email));
        int status = response.getCode().code;
        if(status == 200){
            state = State.LOGGED_IN;
            authToken = response.getAuthToken();
        }
        else if(status == 400 || status == 403 || status == 500){
            System.out.println(response.getMessage());
        }
    }

    private void login(String[] args) throws Exception{
        String username = args[1];
        String password = args[2];
        LoginResult response = server.login(new LoginRequest(username, password));
        int status = response.getCode().code;
        if(status == 200){
            state = State.LOGGED_IN;
            authToken = response.getAuthToken();
        }
        else if(status == 401 || status == 500){
            System.out.println(response.getMessage());
        }
    }

    private void logout() throws Exception{
        LogoutResult response = server.logout(new LogoutRequest(authToken));
        int status = response.getCode().code;
        if(status == 200){
            state = State.LOGGED_OUT;
            authToken = null;
        }
        else if(status == 401 || status == 500){
            System.out.println(response.getMessage());
        }
    }
    private void create(String[] args) throws Exception{
        CreateGameResult response = server.createGame(new CreateGameRequest(authToken, args[1]));
        int status = 200;
        if(status == 200){
            System.out.printf("gameID: " + response.getGameID() + "%n");
        }
        else if(status == 400 || status == 401 || status == 500){
            System.out.println(response.getMessage());
        }
    }

    private void list() throws Exception{
        ListGamesResult response = server.listGames(new ListGamesRequest(authToken));
        int status = 200;
        if(status == 200){
            for(HashMap<String, Object> game: response.getGames()){
                System.out.printf("gameID: " + game.get("gameID") + "%n");
                if(game.get("whiteUsername") == null){
                    System.out.printf("white player: AVAILABLE%n");
                }
                else{
                    System.out.printf("white player: " + game.get("whiteUsername") + "%n");
                }
                if(game.get("blackUsername") == null){
                    System.out.printf("black player: AVAILABLE%n");
                }
                else{
                    System.out.printf("black player: " + game.get("blackUsername") + "%n");
                }
                System.out.printf("game name: " + game.get("gameName") + "%n\n");
            }
        }
        else if(status == 401 || status == 500){
            System.out.println(response.getMessage());
        }
    }

    private void join(String[] args) throws Exception{
        //get player color
        String color = null;
        if(args[2].equals("BLACK")){
            color = "BLACK";
        }
        else if(args[2].equals("WHITE")){
            color = "WHITE";
        }
        else{
            System.out.println("No color specified. Observing game...");
            observe(args);
        }
        //get game id
        int gameID = -1;
        try{
            gameID = Integer.parseInt(args[1]);
        }
        catch(NumberFormatException e){
            System.out.println("Invalid integer input");
        }
        //make request + get response
        JoinGameResult response = server.joinGame(new JoinGameRequest(authToken, color, gameID));
        int status = 200;
        if(status == 200){
            //System.out.println("game joined");
            printBoardBlack(board);
            System.out.println();
            printBoardWhite(board);
            System.out.print("\u001b[0m");
        }
        else if(status == 400 || status == 401 || status == 403 || status == 500){
            System.out.println(response.getMessage());
        }
    }

    private void observe(String[] args) throws Exception{
        printBoardBlack(board);
        System.out.println();
        printBoardWhite(board);
        System.out.print("\u001b[0m");

/*        int gameID;
        try{
            gameID = Integer.parseInt(args[1]);
            JoinGameResponse response = server.joinGame(new JoinGameRequest(authToken, null, gameID));
            int status = response.statusCode.code;
            if(status == 200){
                printBoardBlack(board);
                System.out.println();
                printBoardWhite(board);
                System.out.print("\u001b[0m");
            }
            else if(status == 400 || status == 401 || status == 500){
                System.out.println(response.errorMessage);
            }
        }
        catch(NumberFormatException e){
            System.out.println("Invalid integer input");
        }*/
    }



    private void help(){
        if(state == State.LOGGED_OUT){
            System.out.printf("register <USERNAME> <PASSWORD> <EMAIL> - to create an account%n");
            System.out.printf("login <USERNAME> <PASSWORD> - to play chess%n");

        }
        else{
            System.out.printf("create <NAME> - a game%n");
            System.out.printf("list - games%n");
            System.out.printf("join <ID> [WHITE|BLACK|empty] - a game%n");
            System.out.printf("observe <ID> - a game%n");
            System.out.printf("logout - when you are done%n");
        }
        System.out.printf("quit - playing chess%n");
        System.out.printf("help - with possible commands%n%n");
    }

    private void printBoardBlack(ChessBoard board){
        int boardSize = 8;

        System.out.print("\u001b[100;30;1m    h  g  f  e  d  c  b  a    \u001b[0m\n");

        for(int i = 1; i <= boardSize; i++){
            System.out.print("\u001b[100;30;1m " + i + " ");
            for(int j = boardSize; j >= 1; j--){
                if((i + j) % 2 == 1){
                    System.out.print("\u001b[107m");
                }
                else{
                    System.out.print("\u001b[0m");
                }
                ChessPositionImpl position = new ChessPositionImpl(j, i);
                ChessPiece piece = board.getPiece(position);
                if(piece != null){
                    ChessGame.TeamColor color = piece.getTeamColor();
                    if(color.equals(ChessGame.TeamColor.WHITE)){
                        System.out.print("\u001b[31m");
                        switch(piece.getPieceType()){ //white
                            case KING:      System.out.print(" K "); break;
                            case QUEEN:     System.out.print(" Q "); break;
                            case ROOK:      System.out.print(" R "); break;
                            case KNIGHT:    System.out.print(" N "); break;
                            case BISHOP:    System.out.print(" B "); break;
                            case PAWN:      System.out.print(" P "); break;
                        }
                    }
                    else{
                        System.out.print("\u001b[34m");
                        switch(piece.getPieceType()){ //black
                            case KING:      System.out.print(" k "); break;
                            case QUEEN:     System.out.print(" q "); break;
                            case ROOK:      System.out.print(" r "); break;
                            case KNIGHT:    System.out.print(" n "); break;
                            case BISHOP:    System.out.print(" b "); break;
                            case PAWN:      System.out.print(" p "); break;
                        }
                    }
                }
                else{ //empty position
                    System.out.print("   ");
                }
            }
            System.out.print("\u001b[100;30;1m " + i + " ");
            System.out.print("\u001b[0m\n");
        }
        System.out.print("\u001b[100;30;1m    h  g  f  e  d  c  b  a    \u001b[0m\n");
    }



    private void printBoardWhite(ChessBoard board){
        int boardSize = 8;

        System.out.print("\u001b[100;30;1m    a  b  c  d  e  f  g  h    \u001b[0m\n");

        for(int i = boardSize; i >= 1; i--){
            System.out.print("\u001b[100;30;1m " + i + " ");
            for(int j = 1; j <= boardSize; j++){
                if((i + j) % 2 == 1){
                    System.out.print("\u001b[107m");
                }
                else{
                    System.out.print("\u001b[0m");
                }
                ChessPositionImpl position = new ChessPositionImpl(j, i);
                ChessPiece piece = board.getPiece(position);
                if(piece != null){
                    ChessGame.TeamColor color = piece.getTeamColor();
                    if(color.equals(ChessGame.TeamColor.WHITE)){
                        System.out.print("\u001b[31m");
                        switch(piece.getPieceType()){ //white
                            case KING:      System.out.print(" K "); break;
                            case QUEEN:     System.out.print(" Q "); break;
                            case ROOK:      System.out.print(" R "); break;
                            case KNIGHT:    System.out.print(" N "); break;
                            case BISHOP:    System.out.print(" B "); break;
                            case PAWN:      System.out.print(" P "); break;
                        }
                    }
                    else{
                        System.out.print("\u001b[34m");
                        switch(piece.getPieceType()){ //black
                            case KING:      System.out.print(" k "); break;
                            case QUEEN:     System.out.print(" q "); break;
                            case ROOK:      System.out.print(" r "); break;
                            case KNIGHT:    System.out.print(" n "); break;
                            case BISHOP:    System.out.print(" b "); break;
                            case PAWN:      System.out.print(" p "); break;
                        }
                    }
                }
                else{ //empty position
                    System.out.print("   ");
                }
            }
            System.out.print("\u001b[100;30;1m " + i + " ");
            System.out.print("\u001b[0m\n");
        }
        System.out.print("\u001b[100;30;1m    a  b  c  d  e  f  g  h    \u001b[0m\n");
    }
}