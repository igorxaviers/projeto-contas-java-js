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
        <h1 class="h3 mb-0 text-gray-800">Fornecedores</h1>
    </div>
    <div class="mb-4 d-flex">
        <button class="btn btn-dark mr-3" onclick="fornecedores.mostraForm('cadastrar')">Cadastrar</button>
    </div>
    <form id="form-fornecedor" class="card p-4 my-3 d-none">
        <div class="row justify-content-end">
            <button onclick="fornecedores.fechar()" type="button" class="btn btn-danger" style="width: fit-content"><i class="fas fa-times"></i> Fechar</button>
        </div>

        <h4 class="mb-3">Dados do fornecedor</h4>
        <div class="row">
            <div class="col-6">
                <input type="hidden" value="" name="cnpj-fornecedor" id="cnpj-fornecedor">
                <div class="mb-3">
                    <label for="categoria" class="form-label">Categoria do fornecedor:</label>
                    <select class="form-control" name="categoria" id="categorias">
                        <option selected hidden disabled>-- Selecione uma categoria --</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="cnpj" class="form-label">CNPJ:</label>
                    <input type="text" class="form-control" name="cnpj" placeholder="CNPJ do fornecedor" required minlength="1" maxlength="18">
                </div>
                <div class="mb-3">
                    <label for="razao" class="form-label">Razão Social:</label>
                    <input type="text" class="form-control" name="razao" placeholder="Razão Social do fornecedor" required minlength="1" maxlength="50">
                </div>
            </div> 
            <div class="col-6">
                <div class="mb-3">
                    <label for="fantasia" class="form-label">Nome fantasia:</label>
                    <input type="text" class="form-control" name="fantasia" placeholder="Nome fantasia do fornecedor" required minlength="1" maxlength="50">
                </div>
                <div class="mb-3">
                    <label for="inscricao_estadual" class="form-label">Inscrição Estadual:</label>
                    <input type="text" class="form-control" name="inscricao_estadual" placeholder="Inscrição Estadual do fornecedor" required minlength="1" maxlength="15">
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email:</label>
                    <input type="text" class="form-control" name="email" placeholder="Email do fornecedor" required minlength="1" maxlength="50">
                </div>
            </div>
        </div>

        <h4 class="my-3">Endereço do fornecedor</h4>
        <div class="row">
            <div class="col-6">
                <div class="mb-3">
                    <label for="nome" class="form-label">CEP:</label>
                    <input type="text" class="form-control" name="cep" placeholder="CEP" required minlength="1" maxlength="8">
                </div>
                <div class="mb-3">
                    <label for="endereco" class="form-label">Endereço:</label>
                    <input type="text" class="form-control" name="endereco" placeholder="Endereço do fornecedor" required minlength="1" maxlength="50">
                </div>
            </div> 
            <div class="col-6">
                <div class="mb-3">
                    <label for="cidade" class="form-label">Cidade:</label>
                    <input type="text" class="form-control" name="cidade" placeholder="Cidade do fornecedor" required minlength="1" maxlength="50">
                </div>
                <div class="mb-3">
                    <label for="bairro" class="form-label">Bairro:</label>
                    <input type="text" class="form-control" name="bairro" placeholder="Bairro do fornecedor" required minlength="1" maxlength="50">
                </div>
            </div>  
        </div>

        <button id="bt-alterar" onclick="fornecedores.alterarFornecedor()" type="button" class="btn btn-primary w-25">Alterar</button>
        <button id="bt-cadastrar" onclick="fornecedores.cadastrarFornecedor()" type="button" class="btn btn-primary w-25">Cadastrar</button>
    </form>

    <div class="card shadow mb-4">
        <div class="card-body">
            <div class="table-responsive">
                <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                    <table class="table table-bordered dataTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
                        <thead>
                            <tr role="row">
                                <th>CNPJ</th>
                                <th>Categoria</th>
                                <th>Nome Fantasia</th>
                                <th>Razão</th>
                                <th>Inscrição Estadual</th>
                                <th>Endereço</th>
                                <th>Bairro</th>
                                <th>Email</th>
                                <th>CEP</th>
                                <th>Cidade</th>

                                <th width="100px">Editar</th>
                                <th width="100px">Excluir</th>
                            </tr>
                        </thead>
                        <tbody id="fornecedores-table"></tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../js/fornecedor/fornecedor.js"></script>

<jsp:include page="../footer.html"/>
<% } %>