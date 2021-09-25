package controller;
import bd.util.Conexao;
import bd.util.Banco;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuario;

@WebServlet(name = "LoginController", urlPatterns = {"/Login"})
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject OBJ = retornaJson(request);
        Usuario usuario;
        try {
            usuario = new Usuario(OBJ.getString("login"), OBJ.getString("senha"));
            if(usuario.validar(Banco.getConexao()))
            {
                String dados = "{\"url\": \"/usuarios\", \"ok\": true, \"mensagem\": \"Login feito com sucesso\"}";
                JsonObject json = new JsonParser().parse(dados).getAsJsonObject();
                response.getWriter().print(json);
            }
            else
            {
                String dados = "{'url': '', 'ok': false, 'mensagem': 'Dados inválidos'}";
                response.getWriter().print(new Gson().toJson(dados));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   

    public JSONObject retornaJson(HttpServletRequest request) {
        JSONObject OBJ;
        StringBuilder sb = new StringBuilder();
        String str;
        try {
            BufferedReader br = request.getReader();
            while( (str = br.readLine()) != null ){
                sb.append(str);
            }    
            return OBJ = new JSONObject(sb.toString());
        } catch (Exception e) {}
        return new JSONObject();
    }
}