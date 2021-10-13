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
        <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
    </div>

        <div class="row">

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-6 col-md-6 mb-4">
                <div class="card border-left-primary shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                    Valor em caixa
                                </div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800">R$ <span id="valor-caixa" class=></span></div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-cash-register fa-2x text-gray-300"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-success shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                    TOTAL DE CONTAS A RECEBER
                                </div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800" id="total-receber">0</div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Pending Requests Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-danger shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-danger text-uppercase mb-1">
                                    TOTAL DE CONTAS A PAGAR
                                </div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800" id="total-pagar">0</div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-money-bill-alt fa-2x text-gray-300"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Content Row -->

        <div class="row">

            <!-- Area Chart -->
            <div class="col-xl-8 col-lg-7">
                <div class="card shadow mb-4 border-0">
                    <!-- Card Header - Dropdown -->
                    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                        <h6 class="m-0 font-weight-bold text-primary">Últimas contas cadastradas</h6>
                    </div>
                    <!-- Card Body -->
                    <div id="contas" >
                        <table class="table table-bordered dataTable border-0" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
                            <thead>
                                <tr role="row">
                                    <th width="100px">Vencimento</th>
                                    <th>Fornecedor</th>
                                    <th width="320px">Descrição</th>
                                    <th>Status</th>
                                    <th>Valor</th>
                                    <th>Tipo</th>
                                </tr>
                            </thead>
                            <tbody id="contas-table"></tbody>
                        </table>
                    </div>
                    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between"> 
                        <a class="btn btn-dark" href="/contas">Ver todas as contas <i class="ms-2 fas fa-arrow-right"></i></a>
                    </div>
                </div>
            </div>

            <!-- Pie Chart -->
            <div class="col-xl-4 col-lg-5">
                <div class="card shadow mb-4 border-0">
                    <!-- Card Header - Dropdown -->
                    <div
                        class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                        <h6 class="m-0 font-weight-bold text-primary">Valores a Pagar/Receber</h6>
                        <div class="dropdown no-arrow">
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="chart-pie pt-4 pb-2">
                            <canvas id="myPieChart"></canvas>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        

    </div>
</div>
<script src="../vendor/chart.js/Chart.min.js"></script>
<script src="../js/dashboard/dashboard.js"></script>
<script src="../js/demo/chart-area-demo.js"></script>
<script src="../js/demo/chart-pie-demo.js"></script>

<jsp:include page="../footer.jsp"/>
<% } %>  