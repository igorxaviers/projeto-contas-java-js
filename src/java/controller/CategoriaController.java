package controller;
import bd.util.Banco;
import bd.util.Conexao;
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

import model.Categoria;
import model.Usuario;

@WebServlet(name = "CategoriaController", urlPatterns = {"/Categoria"})
public class CategoriaController extends HttpServlet {
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        String acao = request.getParameter("acao"); 
        if(acao != null) 
        {
            if(acao.equals("buscar"))
            {
                int id = Integer.parseInt(request.getParameter("id"));
                Categoria c = new Categoria();
                c.setId(id);
                response.getWriter().print(new Gson().toJson(c.getCategoria(Banco.getConexao())));
            }
        }
        else
        {
            ArrayList<Categoria> cList = new ArrayList<>();
            cList = new Categoria().getCategorias("", Banco.getConexao());
            response.getWriter().print(new Gson().toJson(cList));
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject OBJ = retornaJson(request);
        Categoria c;
        try {
            String acao = OBJ.getString("acao");
            switch(acao){
                case "add":
                    c = new Categoria(OBJ.getString("nome"));
                    if(c.valida())
                        if(c.salvar(Banco.getConexao()))
                            response.getWriter().print("Categoria salva com sucesso");
                        else
                            response.getWriter().print("Houve um erro ao cadastrar a categoria");
                    else
                        response.getWriter().print("Erro: Corrija os campos inválidos");
                break;
                
                case "alterar":
                    c = new Categoria(OBJ.getInt("id"), OBJ.getString("nome"));
                    if(c.valida())
                        if(c.alterar(Banco.getConexao()))
                            response.getWriter().print("Categoria alterada com sucesso");
                        else
                            response.getWriter().print("Houve um erro ao alterar a categoria");
                    else
                        response.getWriter().print("Erro: Corrija os campos inválidos");
                break;

                case "excluir":
                    c = new Categoria();
                    c.setId(OBJ.getInt("id"));
                    if(c.excluir(Banco.getConexao()))
                        response.getWriter().print("Categoria excluída com sucesso");
                    else
                        response.getWriter().print("Houve um erro ao excluir a categoria");
                break;
            }
        } catch (JSONException e1) {
            response.getWriter().print("Houve um erro ao salvar a conta");
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