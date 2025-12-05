<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="modelos.Acao, modelos.Unidade, java.util.*" %>

<%@ include file="/componentes/barraNav.jsp" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Editar Ação</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/dashboard.css">
</head>

<body>

<%
    Acao acao = (Acao) request.getAttribute("acao");
    List<Unidade> unidades = (List<Unidade>) request.getAttribute("unidades");

    boolean encerrada = LocalDate.now().isAfter(acao.getDataFim());
%>

<section style="background:#004d26; color:white; padding:30px; text-align:center;">
    <h1>Editar Ação: <%= acao.getNome() %></h1>
    <% if (encerrada) { %>
        <p style="color:yellow;">Esta ação já foi encerrada e não pode ser editada.</p>
    <% } %>
</section>

<div class="container">

    <form action="<%= request.getContextPath() %>/admin/atualizarAcao" method="post" class="form-box">

        <input type="hidden" name="id" value="<%= acao.getId() %>">

        <!-- NOME -->
        <label>Nome da Ação:</label>
        <input type="text" name="nome" value="<%= acao.getNome() %>" <%= encerrada ? "readonly" : "" %> required>

        <!-- DESCRIÇÃO -->
        <label>Descrição / Sobre:</label>
        <textarea name="sobre" rows="3" <%= encerrada ? "readonly" : "" %> required><%= acao.getSobre() %></textarea>

        <!-- PÚBLICO ALVO -->
        <label>Público-alvo:</label>
        <input type="text" name="publicoAlvo" value="<%= acao.getPublicoAlvo() %>" <%= encerrada ? "readonly" : "" %> required>

        <!-- LOCALIZAÇÃO -->
        <h3>Localização</h3>
        <label>Nome do local:</label>
        <input type="text" name="nomeLocal" value="<%= acao.getLocalizacao().getNome() %>" <%= encerrada ? "readonly" : "" %> required>

        <label>Endereço:</label>
        <input type="text" name="endereco" value="<%= acao.getLocalizacao().getEndereco() %>" <%= encerrada ? "readonly" : "" %> required>

        <label>Ponto de referência:</label>
        <input type="text" name="pontoRef"
               value="<%= acao.getLocalizacao().getPontoDeReferencia() != null ? acao.getLocalizacao().getPontoDeReferencia() : "" %>"
               <%= encerrada ? "readonly" : "" %>>

        <!-- CONTATO -->
        <h3>Contato</h3>
        <label>Email:</label>
        <input type="email" name="email" value="<%= acao.getEmail() != null ? acao.getEmail() : "" %>" <%= encerrada ? "readonly" : "" %>>

        <label>Celular:</label>
        <input type="text" name="celular" value="<%= acao.getCelular() != null ? acao.getCelular() : "" %>" <%= encerrada ? "readonly" : "" %>>

        <label>Link externo:</label>
        <input type="text" name="linkExterno" value="<%= acao.getLinkExterno() != null ? acao.getLinkExterno() : "" %>" <%= encerrada ? "readonly" : "" %>>

        <!-- TAXA -->
        <h3>Taxa</h3>
        <div class="criar-row taxa-row">
            <label for="taxa">Possui Taxa?</label>
            <div class="checkbox-wrapper">
                <input type="checkbox" id="taxa" name="possuiTaxa" <%= acao.getTaxa() ? "checked" : "" %> <%= encerrada ? "disabled" : "" %>>
                <span>Sim</span>
            </div>
        </div>

        <label>Preço:</label>
        <input type="text" name="preco" value="<%= acao.getPreco() != null ? acao.getPreco() : "" %>" <%= encerrada ? "readonly" : "" %>>

        <!-- UNIDADE -->
        <h3>Unidade responsável</h3>
        <select name="unidadeId" <%= encerrada ? "disabled" : "" %> required>
            <% for (Unidade u : unidades) { %>
                <option value="<%= u.getId() %>"
                    <%= u.getId() == acao.getUnidade().getId() ? "selected" : "" %>>
                    <%= u.getNome() %> – <%= u.getTipo() %>
                </option>
            <% } %>
        </select>

        <!-- DATA DE INÍCIO -->
        <h3>Data de Início</h3>
        <input type="text" value="<%= acao.getDataInicio() %>" readonly>

        <% if (!encerrada) { %>
            <button class="btn" type="submit">Salvar alterações</button>
        <% } %>

    </form>

</div>

<script>
function togglePreco() {
    let cb = document.getElementById('taxa');
    let preco = document.querySelector('input[name="preco"]');
    preco.readOnly = !cb.checked;
    if (!cb.checked) preco.value = "";
}
</script>

</body>
</html>
