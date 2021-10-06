<%@page import="model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
    Usuario u = (Usuario) session.getAttribute("usuario");
    if(u == null)
        response.sendRedirect("/");
    else {
%>
<jsp:include page="../topo.jsp"/>
<div class="container-fluid">
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Contas</h1>
    </div>
    <div class="mb-4 d-flex">
        <button class="btn btn-dark mr-3" onclick="contas.mostraForm('cadastrar')">Cadastrar</button>
    </div>
    <form id="form-conta" class="card p-4 my-3 d-none">
        <div class="row justify-content-end">
            <button onclick="contas.fechar()" type="button" class="btn btn-danger" style="width: fit-content"><i class="fas fa-times"></i> Fechar</button>
        </div>
        <div class="row mb-4">
            <div class="col-6">
                <input type="hidden" value="" name="id" id="id-conta">
                <input type="hidden" value="<%= u.getId() %>" name="id_usu" id="id-conta">

                <div class="row mb-3">
                    <div class="col-6">
                        <label for="">Tipo da conta:</label>
                        <select name="tipo_conta" class="form-control">
                            <option value="0">Pagar</option>
                            <option value="1">Receber</option>
                        </select>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-6">
                        <label for="">Data:</label>
                        <input type="date" name="data">
                    </div>
                    <div class="col-6">
                        <label for="">Data vencimento:</label>
                        <input type="date" name="data_vencimento">
                    </div>
                </div>
                <div class="mb-3">
                    <label for="nome" class="form-label">Descrição:</label>
                    <textarea type="text" class="form-control" name="descricao" placeholder="Descricao da conta" required minlength="1" maxlength="500" style="max-height: 250px; min-height: 150px;"></textarea>
                </div>
                <div class="mb-3 row">
                    <div class="col-6">
                        <label for="valor" class="form-label">Valor:</label>
                        <input type="number" name="valor" class="form-control" placeholder="Valor da conta">
                    </div>
                    <div class="col-6">
                        <label for="nome" class="form-label">Usuário responsável:</label>
                        <input type="text" name="usu_id_usuarios" value="<%= u.getNome() %>" class="form-control" disabled>
                    </div>
                </div>


            </div>    
        </div>
        <button id="bt-alterar" onclick="contas.alterarConta()" type="button" class="btn btn-primary w-25">Alterar</button>
        <button id="bt-cadastrar" onclick="contas.cadastrarConta()" type="button" class="btn btn-primary w-25">Cadastrar</button>
    </form>

    <div class="card shadow mb-4">
        <div class="card-body">
            <div class="table-responsive">
                <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                    <table class="table table-bordered dataTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
                        <thead>
                            <tr role="row">
                                <th>Data</th>
                                <th>Data vencimento</th>
                                <th>Responsável</th>
                                <th>Descrição</th>
                                <th>Status</th>
                                <th>Valor</th>
                                <th>Tipo</th>
                                <th width="100px">Editar</th>
                                <th width="100px">Excluir</th>
                            </tr>
                        </thead>
                        <tbody id="contas-table"></tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../js/contas/contas.js"></script>

<jsp:include page="../footer.html"/>
<% } %>  