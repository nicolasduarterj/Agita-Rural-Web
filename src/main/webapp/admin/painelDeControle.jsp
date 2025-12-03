<%@ page contentType="text/html;charset=UTF-8" session="true" %>

<%
    Object adminObj = session.getAttribute("adminLogado");
    if (adminObj == null) {
        response.sendRedirect(request.getContextPath() + "/loginadmin.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Dashboard Admin</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>

<%@ include file="/componentes/barraNav.jsp" %>

<div class="container">
    <h2 class="section-title">Painel Administrativo</h2>

    <div style="margin-bottom:20px;">
        <a class="btn" href="<%= request.getContextPath() %>/logoutadmin">Sair</a>
    </div>

    <div>
        <p>Selecione a lista que deseja ver:</p>

        <a href="javascript:void(0)" class="btn"
           onclick="document.getElementById('box').innerText='Projetos (a implementar)';">
            Projetos
        </a>

        <a href="javascript:void(0)" class="btn"
           onclick="document.getElementById('box').innerText='Ações (a implementar)';">
            Ações
        </a>

        <a href="<%= request.getContextPath() %>/admin/projetoMod.jsp" class="btn">+ Novo Projeto</a>
        <a href="<%= request.getContextPath() %>/admin/acaoMod.jsp" class="btn">+ Nova Ação</a>
    </div>

    <div id="box"
         style="margin-top:20px; padding:16px; background:#fff;
                border-radius:8px; box-shadow:0 1px 6px rgba(0,0,0,0.08);">
        futura implementação
    </div>
</div>

</body>
</html>
