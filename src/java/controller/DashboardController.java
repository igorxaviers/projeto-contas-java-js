package controller;
import bd.util.Banco;
import model.Caixa;
import model.Conta;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


@WebServlet(name = "DashboardController", urlPatterns = {"/Dashboard"})
public class DashboardController extends HttpServlet {
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        Gson gson = new Gson();
        Caixa caixa = new Caixa(Banco.getConexao());
        Conta conta = new Conta();
        ArrayList<Conta> contas = conta.getContas(5, Banco.getConexao());
        int totalPagar = conta.countContas(0, Banco.getConexao());
        int totalReceber = conta.countContas(1, Banco.getConexao());
        double valorPagar = conta.somaValorContas(0, Banco.getConexao());
        double valorReceber = conta.somaValorContas(1, Banco.getConexao());

        ArrayList<Object> objs = new ArrayList<>();
        objs.add(caixa);
        objs.add(contas);
        objs.add(totalPagar);
        objs.add(totalReceber);
        objs.add(valorPagar);
        objs.add(valorReceber);


        response.getWriter().print(gson.toJson(objs));

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

   
}