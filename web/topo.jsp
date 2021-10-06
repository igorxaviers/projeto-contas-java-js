<%@page import="java.util.ArrayList"%>
<%@page import="model.Usuario"%>
<%@page language="java" session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
    Usuario u = (Usuario) session.getAttribute("usuario"); 
    ArrayList<String> notificacoes = (ArrayList) session.getAttribute("notificacoes"); 
%>


<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>SB Admin 2 - Dashboard</title>
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="../css/sb-admin-2.css" rel="stylesheet">
    <script src="../js/HTTPClient.js"></script>
    <script src="../js/Validate.js"></script>
    <script src="../js/ohsnap.js"></script>

</head>

<body id="page-top">
    <div id="ohsnap"></div>
    <div id="wrapper">
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">Gerenciamento de Contas<sup>2</sup></div>
            </a>
            <hr class="sidebar-divider my-0">
            <li class="nav-item active">
                <a class="nav-link" href="#">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Dashboard</span></a>
            </li>
            <hr class="sidebar-divider">
            <div class="sidebar-heading">
                Interface
            </div>
            <li class="nav-item">
                <a class="nav-link" href="/contas">
                    <i class="fas fa-fw fa-wrench"></i>
                    <span>Contas</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/categoria">
                    <i class="fas fa-fw fa-table"></i>
                    <span>Categorias</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/fornecedor">
                    <i class="fas fa-fw fa-table"></i>
                    <span>Fornecedores</span></a>
            </li>

            <% if(u.isAdmin()) { %>

            <hr class="sidebar-divider">
            <div class="sidebar-heading">
                Administrador
            </div>
            <li class="nav-item">
                <a class="nav-link" href="/usuarios">
                    <i class="fas fa-fw fa-table"></i>
                    <span>Usuários</span></a>
            </li>

            <% } %>

        </ul>

        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>
                    <ul class="navbar-nav ml-auto">
                        <div class="topbar-divider d-none d-sm-block"></div>
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-bell fa-fw"></i>
                                <span class="badge badge-danger badge-counter"><%= notificacoes.size() %></span>
                            </a>
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in "
                                aria-labelledby="alertsDropdown">
                                <h6 class="dropdown-header">
                                    Central de alertas
                                </h6>
                                <% for(String notifi: notificacoes ) { %>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-success">
                                            <i class="fas fa-money-bill-alt text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">Nova conta</div>
                                        <span class="font-weight-bold"><%= notifi %></span>
                                    </div>
                                </a>
                                <% } %>
                            </div>
                        </li>
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Bem vindo
                                    <%= u.getNome() %></span>
                                <img class="img-profile rounded-circle" src="../img/undraw_profile.svg">
                            </a>
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="/Logout" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>
                    </ul>
                </nav>