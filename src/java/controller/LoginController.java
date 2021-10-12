package controller;
import bd.util.Banco;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Notificacao;

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
                Notificacao notificacao = new Notificacao(usuario);
                ArrayList<Notificacao> notificacoes = notificacao.getNotificacoes(Banco.getConexao());
                sessao.setAttribute("notificacoes", notificacoes);
                if(usuario.isAdmin())
                    response.sendRedirect("/usuarios");
                else
                    response.sendRedirect("/contas");
            }
            else
            {
                HttpSession sessao = request.getSession();
                sessao.invalidate();
                // String dados = "{\"url\": \"\", \"ok\": false, \"mensagem\": \"Dados incorretos\"}";
                // JsonObject json = new JsonParser().parse(dados).getAsJsonObject();
                // response.getWriter().print(json);
                response.sendRedirect("/");
            }
        } catch (Exception e) {
            response.sendRedirect("/erro");
            e.printStackTrace();
        }
    }   
}