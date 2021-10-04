
package controller;

import bd.util.Banco;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Categoria;
import model.Fornecedor;


@WebServlet(name = "FornecedorController", urlPatterns = {"/Fornecedor"})
public class FornecedorController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        
        String acao = request.getParameter("acao"); 
        if(acao.equals("busca"))
        {
            String cnpj = request.getParameter("cnpj");
            Fornecedor f = new Fornecedor();
            f.setCnpj(cnpj);
            response.getWriter().print(new Gson().toJson(f.getFornecedor(Banco.getConexao())));
        }
        else
        {
            ArrayList<Fornecedor> fList = new ArrayList<>();
            fList = new Fornecedor().getFornecedores("", Banco.getConexao());
            response.getWriter().print(new Gson().toJson(fList));
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        JSONObject OBJ = retornaJson(request);
        Fornecedor f;
        try {
            String acao = OBJ.getString("acao");
            switch(acao){
                case "add":
                    f = new Fornecedor(
                        OBJ.getString("cnpj"),
                        OBJ.getString("razao"), 
                        OBJ.getString("fantasia"),
                        OBJ.getString("endereco"), 
                        OBJ.getString("bairro"), 
                        OBJ.getString("email"), 
                        OBJ.getString("inscricao_estadual"), 
                        OBJ.getString("cep"), 
                        OBJ.getString("cidade"), 
                        new Categoria(OBJ.getInt("id_categoria")));
                    if(f.salvar(Banco.getConexao()))
                        response.getWriter().print("Fornecedor salvo com sucesso");
                    else
                        response.getWriter().print("Houve um erro ao salvar a categoria");
                break;
                
                case "alterar":
                    f = new Fornecedor(
                        OBJ.getString("cnpj"),
                        OBJ.getString("razao"), 
                        OBJ.getString("fantasia"),
                        OBJ.getString("endereco"), 
                        OBJ.getString("bairro"), 
                        OBJ.getString("email"), 
                        OBJ.getString("inscricao_estadual"), 
                        OBJ.getString("cep"), 
                        OBJ.getString("cidade"), 
                        new Categoria(OBJ.getInt("id_categoria")));
                    if(f.alterar(Banco.getConexao()))
                        response.getWriter().print("Fornecedor alterado com sucesso");
                    else
                        response.getWriter().print("Houve um erro ao alterar a categoria");
                break;

                case "excluir":
                    f = new Fornecedor();
                    f.setCnpj(OBJ.getString("cnpj"));
                    if(f.excluir(Banco.getConexao()))
                        response.getWriter().print("Fornecedor excluído com sucesso");
                    else
                        response.getWriter().print("Houve um erro ao excluir a fornecedor");
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
