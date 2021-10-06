
package controller;

import bd.util.Banco;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Conta;
import model.Usuario;
import org.json.JSONObject;

@WebServlet(name = "ContaServlet", urlPatterns = {"/Contas"})
public class ContaController extends HttpServlet {

    public PagarController cPagar;
    public ReceberController cReceber;
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        String acao = request.getParameter("acao"); 
        if(acao != null) 
        {
            if(acao.equals("buscar"))
            {
                int id = Integer.parseInt(request.getParameter("id"));
                Conta c = new Conta();
                c.setId(id);
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                response.getWriter().print(gson.toJson(c.getConta(Banco.getConexao())));
            }
        }
        else
        {
            ArrayList<Conta> cList = new ArrayList<>();
            cList = new Conta().getContas("",Banco.getConexao());
            response.getWriter().print(new Gson().toJson(cList));
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            Conta c = new Conta();
            HttpSession sessao = request.getSession(true);
            Usuario u = (Usuario) sessao.getAttribute("usuario");
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
            JSONObject OBJ = retornaJson(request);
            String acao = OBJ.getString("acao");

            if(c.getTipo() == 0)//PAGAR
            {
                cPagar = new PagarController();
                switch(acao){
                    case "add":
                        c = new Conta(
                            OBJ.getInt("cont_tipo"),
                            OBJ.getString("cont_desc"),
                            formato.parse(OBJ.getString("cont_data")),
                            formato.parse(OBJ.getString("cont_data_vencimento")),
                            OBJ.getDouble("cont_valor"),
                            u                
                        );
                        if(cPagar.adicionarConta(c))
                        {
                            c.addTodosAdmin(Banco.getConexao());
                            c.notificar();
                            response.getWriter().print("Conta salva com sucesso, os administradores foram notificados!");
                            //SIMULAR AS NOTIF
                        }else
                            response.getWriter().print("Houve um erro ao salvar a conta");
                    break;

                    case "alterar":
                        
                    break;

                    case "excluir":
                        c = new Conta();
                        c.setId(OBJ.getInt("cont_id"));
                        c = c.getConta(Banco.getConexao());
                        if(cPagar.excluir(c))
                            response.getWriter().print("Conta excluída com sucesso");
                        else
                           response.getWriter().print("Houve um erro ao excluir a conta");

                    break;
                }
            }
            else// RECEBER
            {
                cReceber = new ReceberController();
                switch(acao){
                    case "add":
                        c = new Conta(
                            OBJ.getInt("cont_tipo"),
                            OBJ.getString("cont_desc"),
                            formato.parse(OBJ.getString("cont_data")),
                            formato.parse(OBJ.getString("cont_data_vencimento")),
                            OBJ.getDouble("cont_valor"),
                            u                
                        );
                        if(cReceber.adicionarConta(c))
                            response.getWriter().print("Conta salva com sucesso");
                        else
                            response.getWriter().print("Houve um erro ao salvar a conta");
                    break;

                    case "alterar":

                    break;

                    case "excluir":
                        c = new Conta();
                        c.setId(OBJ.getInt("cont_id"));
                        c = c.getConta(Banco.getConexao());
                        if(cReceber.excluir(c))
                            response.getWriter().print("Conta excluída com sucesso");
                        else
                           response.getWriter().print("Houve um erro ao excluir a conta");
                    break;
                }
            }
        }
        catch(Exception e){}
        
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
