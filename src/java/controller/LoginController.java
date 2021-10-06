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
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario;
        try {
            usuario = new Usuario(request.getParameter("login"), request.getParameter("senha"));
            if(usuario.validar(Banco.getConexao()))
            {
                HttpSession sessao = request.getSession(true);
                sessao.setAttribute("usuario", usuario);
                // String dados = "{\"url\": \"usuarios\", \"ok\": true, \"mensagem\": \"Login feito com sucesso\"}";
                // JsonObject json = new JsonParser().parse(dados).getAsJsonObject();
                // response.getWriter().print(json);
                ArrayList<String> notificacoes = usuario.getNotificacoes(Banco.getConexao());
                sessao.setAttribute("notificacoes", notificacoes);
                response.sendRedirect("/usuarios");
            }
            else
            {
                HttpSession sessao = request.getSession();
                sessao.invalidate();
                // String dados = "{\"url\": \"\", \"ok\": false, \"mensagem\": \"Dados incorretos\"}";
                // JsonObject json = new JsonParser().parse(dados).getAsJsonObject();
                // response.getWriter().print(json);
                response.sendRedirect("/erro");
            }
        } catch (Exception e) {
            response.sendRedirect("/erro");
            e.printStackTrace();
        }
    }   
}