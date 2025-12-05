<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelos.Acao" %>
<%@ page import="modelos.Unidade" %>
<%@ include file="/componentes/barraNav.jsp" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Cadastrar Novo Projeto</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/criar.css">
</head>
<body>

<div class="criar-container">

    <h2>Cadastrar Novo Projeto</h2>

    <form action="<%= request.getContextPath() %>/admin/projeto/salvar" method="post">

        <!-- Nome do Projeto -->
        <div class="criar-row">
            <label>Nome do Projeto</label>
            <input type="text" name="nome" required />
        </div>

        <!-- Sobre / Descrição -->
        <div class="criar-row">
            <label>Descrição / Sobre</label>
            <textarea name="sobre" rows="3" required></textarea>
        </div>

        <!-- Público Alvo -->
        <div class="criar-row">
            <label>Público-Alvo</label>
            <textarea name="publicoAlvo" rows="2"></textarea>
        </div>

        <!-- Contato -->
        <h3>Informações de Contato</h3>
        <div class="criar-row">
            <label>Email</label>
            <input type="email" name="emailContato" required />
        </div>

        <div class="criar-row">
            <label>Celular</label>
            <input type="text" name="celularContato" required />
        </div>

        <div class="criar-row">
            <label>Link Externo (Instagram, site, etc.)</label>
            <input type="url" name="linkExterno" placeholder="https://instagram.com/seuPerfil" />
        </div>

        <!-- Local -->
        <h3>Local do Projeto</h3>
        <div class="criar-row">
            <label>Nome do local</label>
            <input type="text" name="localNome" required />
        </div>

        <div class="criar-row">
            <label>Bairro / Cidade</label>
            <input type="text" name="localBairro" required />
        </div>

        <div class="criar-row">
            <label>Ponto de referência</label>
            <input type="text" name="localPontoReferencia" />
        </div>

        <!-- Unidade Responsável -->
        <div class="criar-row">
            <label>Unidade Responsável</label>
            <select name="unidadeId" required>
                <option value="">Selecione...</option>
                <%
                    List<Unidade> unidades = (List<Unidade>) request.getAttribute("unidades");
                    if (unidades != null) {
                        for (Unidade u : unidades) {
                %>
                            <option value="<%= u.getId() %>"><%= u.getNome() %></option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <!-- Seleção de Ações -->
        <div class="criar-row">
            <label>Ações do Projeto</label>
            <div class="acoes-list">
                <%
                    List<Acao> acoes = (List<Acao>) request.getAttribute("acoes");
                    if (acoes != null) {
                        for (Acao a : acoes) {
                %>
                            <div>
                                <input type="checkbox" name="acoesSelecionadas" value="<%= a.getId() %>" />
                                <span><%= a.getNome() %> – <%= a.getSobre() %></span>
                            </div>
                <%
                        }
                    }
                %>
            </div>
        </div>

        <button type="submit" class="btn">Salvar Projeto</button>
    </form>

</div>

</body>
</html>
