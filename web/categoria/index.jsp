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
        <h1 class="h3 mb-0 text-gray-800"><i class="fas fa-list"></i> Categorias</h1>
    </div>
    <p class=" mt-2 mb-5">Gerenciamento das categorias de fornecedores.</p>

    <div class="mb-4 d-flex">
        <button class="btn btn-dark mr-3 px-4 bt-cad" onclick="categorias.mostraForm('cadastrar')">
            <i class="fas fa-plus me-2"></i> Nova categoria
        </button>
    </div>
    <form id="form-categoria" class="card p-4 border-0 shadow  my-3 d-none">
        <div class="row justify-content-end">
            <button onclick="categorias.fechar()" type="button" class="btn btn-danger" style="width: fit-content"><i class="fas fa-times"></i> Fechar</button>
        </div>
        <div class="row">
            <div class="col-6">
                <input type="hidden" value="" name="id" id="id-categoria">
                <div class="mb-3">
                    <label for="nome" class="form-label">Nome:</label>
                    <input type="text" class="form-control" name="nome" placeholder="Nome da categoria" required minlength="1" maxlength="50">
                </div>
            </div>    
        </div>
        <button id="bt-alterar" onclick="categorias.alterarCategoria()" type="button" class="btn btn-primary w-25">Alterar</button>
        <button id="bt-cadastrar" onclick="categorias.cadastrarCategoria()" type="button" class="btn btn-primary w-25">Cadastrar</button>
    </form>

    <div class="card shadow border-0 mb-4">
        <div class="card-body">
            <div class="table-responsive">
                <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                    <table class="table table-bordered dataTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
                        <thead>
                            <tr role="row">
                                <th>Nome</th>
                                <th width="150px">Ações</th>
                            </tr>
                        </thead>
                        <tbody id="categorias-table"></tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../js/categoria/categoria.js"></script>

<jsp:include page="../footer.jsp"/>
<% } %>  