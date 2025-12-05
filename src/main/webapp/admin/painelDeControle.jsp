<%@ page contentType="text/html;charset=UTF-8" session="true" %>

<%
    Object adminObj = session.getAttribute("adminLogado");
    if (adminObj == null) {
        response.sendRedirect(request.getContextPath() + "/admin/loginadmin.jsp");
        return;
    }
%>

<%@ include file="/componentes/barraNav.jsp" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Painel Administrativo</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/dashboard.css">
</head>
<body>

<div class="container dashboard">

    <h2 class="section-title">Painel Administrativo</h2>

    <div class="dashboard-actions">
        <a href="<%= request.getContextPath() %>/admin/logout" class="btn-red">Sair</a>
    </div>

    <div class="cards-grid">

        <a href="<%= request.getContextPath() %>/admin/projetoListar" class="card">
            <h3>Projetos</h3>
            <p>Gerencie todos os projetos cadastrados.</p>
        </a>

        <a href="<%= request.getContextPath() %>/admin/acaoListar" class="card">
            <h3>Ações</h3>
            <p>Visualize e edite todas as ações.</p>
        </a>

        <a href="<%= request.getContextPath() %>/admin/projeto/criar" class="card card-green">
            <h3>+ Novo Projeto</h3>
            <p>Criar um novo projeto comunitário.</p>
        </a>

        <a href="<%= request.getContextPath() %>/admin/acao/criar" class="card card-green">
            <h3>+ Nova Ação</h3>
            <p>Registrar uma nova ação no sistema.</p>
        </a>
    </div>
</div>
</body>
</html>
