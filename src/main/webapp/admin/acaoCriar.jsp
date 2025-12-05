<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="modelos.Unidade" %>
<%@ page import="java.util.List" %>
<%@ include file="/componentes/barraNav.jsp" %>
<!DOCTYPE html>

<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Cadastrar Nova Ação</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/criar.css">
</head>
<body>

<div class="criar-container">

    <h2>Cadastrar Nova Ação</h2>

    <form action="<%= request.getContextPath() %>/admin/salvarAcao" method="post">

        <!-- Nome -->
        <div class="criar-row">
            <label>Nome da Ação</label>
            <input type="text" name="nome" required />
        </div>

        <!-- Sobre -->
        <div class="criar-row">
            <label>Descrição / Sobre</label>
            <textarea name="sobre" rows="3" required></textarea>
        </div>

        <!-- Público alvo -->
        <div class="criar-row">
            <label>Público-Alvo</label>
            <textarea name="publicoAlvo" rows="2"></textarea>
        </div>

        <!-- Localização -->
        <h3>Localização</h3>

        <div class="criar-row">
            <label>Logradouro</label>
            <input type="text" name="logradouro" required />
        </div>

        <div class="criar-row">
            <label>Bairro</label>
            <input type="text" name="bairro" required />
        </div>

        <div class="criar-row">
            <label>Cidade</label>
            <input type="text" name="cidade" required />
        </div>

        <div class="criar-row">
            <label>Ponto de referência</label>
            <input type="text" name="pontoReferencia" />
        </div>
        
        <div class="criar-row">
        	<label>Data de início</label>
        	<input type="datetime-local" name="dataInicio">
        </div>
        
        <div class="criar-row">
        	<label>Data de fim</label>
        	<input type="datetime-local" name="dataFim">
        </div>

        <!-- Taxa -->
        <div class="criar-row taxa-row">
            <label for="taxa">Possui Taxa?</label>
            <div class="checkbox-wrapper">
                <input type="checkbox" id="taxa" name="possuiTaxa" onclick="togglePreco()" />
                <span>Sim</span>
            </div>
        </div>

        <div class="criar-row">
            <label>Preço</label>
            <input type="text" id="preco" name="preco" disabled placeholder="R$ 0,00" />
        </div>

        <!-- Unidade -->
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

        <button type="submit" class="btn">Salvar Ação</button>

    </form>

</div>

<script>
function togglePreco() {
    let cb = document.getElementById('taxa');
    let preco = document.getElementById('preco');
    preco.disabled = !cb.checked;
    if (!cb.checked) preco.value = "";
}
</script>

</body>
</html>
