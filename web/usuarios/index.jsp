<%@page import="model.Usuario"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
    Usuario u = (Usuario) session.getAttribute("usuario");
    if(u == null)
        response.sendRedirect("/");
    else {
%>
<jsp:include page="../topo.jsp"/>
<div class="container-fluid">
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800"><i class="fas fa-users"></i> Usuários do sistema</h1>
    </div>
    <p class=" mt-2 mb-5">Gerenciamento dos usuários e administradores do sistema.</p>

    <div class="mb-4 d-flex">
        <button class="btn btn-dark mr-3 px-4 bt-cad" onclick="usuarios.mostraForm('cadastrar')">
            <i class="fas fa-plus me-2"></i> Novo usuário
        </button>
    </div>
    <form id="form-usuario" class="card p-4 border-0 shadow  my-3 d-none">
        <div class="row justify-content-end">
            <button onclick="usuarios.fechar()" type="button" class="btn btn-danger" style="width: fit-content"><i class="fas fa-times"></i> Fechar</button>
        </div>
        <div class="row col-md-8 col-12 px-0 mb-4">
            <div class="col-6">
                <input type="hidden" value="" name="id" id="id-usuario">
                <div class="mb-3">
                    <label for="nome" class="form-label">Nome:</label>
                    <input type="text" class="form-control" name="nome" placeholder="name@example.com" required>
                </div>
                <div class="mb-3">
                    <label for="login" class="form-label">Login:</label>
                    <input type="text" class="form-control" name="login" placeholder="Seu login aqui" required>
                </div>
                <div class="mb-3">
                    <label for="senha" class="form-label">Senha:</label>
                    <input type="password" class="form-control" name="senha" placeholder="*********" required>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3">
                    <label for="admin" class="form-label">Admin:</label>
                    <select class="form-select" name="admin" required>
                        <option selected hidden disabled>Selecione uma opção</option>
                        <option value="true">Sim</option>
                        <option value="false">Não</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="ativo" class="form-label">Ativo:</label>
                    <select class="form-select" name="ativo" required>
                        <option selected hidden disabled>Selecione uma opção</option>
                        <option value="true">Sim</option>
                        <option value="false">Não</option>
                    </select>
                </div>
            </div>
        </div>
        <button id="bt-alterar" onclick="usuarios.alterarUsuario()" type="button" class="btn btn-primary w-25">Alterar</button>
        <button id="bt-cadastrar" onclick="usuarios.cadastrarUsuario()" type="button" class="btn btn-primary w-25">Cadastrar</button>
    </form>

    <div class="card shadow border-0 mb-4">
        <div class="card-body">
            <div class="table-responsive">
                <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                    <table class="table table-bordered dataTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
                        <thead>
                            <tr role="row">
                                <th>Nome</th>
                                <th>Login</th>
                                <th width="100px">Administrador</th>
                                <th width="100px">Ativo</th>
                                <th width="150px">Ações</th>
                            </tr>
                        </thead>
                        <tbody id="usuarios-table"></tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../js/usuario/usuarios.js"></script>

<jsp:include page="../footer.jsp"/>

<% } %>
