package controller;
import bd.util.Conexao;
import bd.util.Banco;

import com.google.gson.Gson;
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
import model.Usuario;

@WebServlet(name = "UsuarioController", urlPatterns = {"/Usuario"})
public class UsuarioController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        String acao = request.getParameter("acao"); 
        if(acao.equals("busca"))
        {
            int id = Integer.parseInt(request.getParameter("id"));
            Usuario u = new Usuario();
            u.setId(id);
            response.getWriter().print(new Gson().toJson(u.getUsuario(Banco.getConexao())));
        }
        else
        {
            ArrayList<Usuario> uList = new ArrayList<>();
            uList = new Usuario().getUsuarios("", Banco.getConexao());
            response.getWriter().print(new Gson().toJson(uList));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject OBJ = retornaJson(request);
        Usuario u;
        try {
            String acao = OBJ.getString("acao");
            switch(acao){
                case "add":
                    u = new Usuario(OBJ.getString("nome"), OBJ.getString("login"), OBJ.getString("senha"), OBJ.getBoolean("admin"), OBJ.getBoolean("ativo"));
                    if(u.salvar(Banco.getConexao()))
                        response.getWriter().print("Usuário salvo com sucesso");
                    else
                        response.getWriter().print("Houve um erro ao salvar o usuário");
                break;
                
                case "alterar":
                    u = new Usuario(OBJ.getString("nome"), OBJ.getString("login"), OBJ.getString("senha"), OBJ.getBoolean("admin"), OBJ.getBoolean("ativo"));
                    u.setId(OBJ.getInt("id"));
                    if(u.alterar(Banco.getConexao()))
                        response.getWriter().print("Usuário alterado com sucesso");
                    else
                        response.getWriter().print("Houve um erro ao alterar o usuário");
                break;

                case "excluir":
                    u = new Usuario();
                    u.setId(OBJ.getInt("id"));
                    if(u.excluir(Banco.getConexao()))
                        response.getWriter().print("Usuário excluído com sucesso");
                    else
                        response.getWriter().print("Houve um erro ao excluir o usuário");
                break;
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
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