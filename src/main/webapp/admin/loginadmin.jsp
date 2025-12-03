<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Login Admin - Agita Rural</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css">
</head>
<body>

<div class="login-box">
    <h2>Login Administrador</h2>

    <% String erro = (String) request.getAttribute("erro"); %>
    <% if (erro != null) { %>
        <div class="error"><%= erro %></div>
    <% } %>

    <form action="<%= request.getContextPath() %>/loginadmin" method="post">
		 <div class="form-row">
		     <label for="login">Login</label>
		     <input id="login" name="email" type="text" required />
		 </div>
		 <div class="form-row">
		     <label for="password">Senha</label>
		     <input id="password" name="senha" type="password" required />
		 </div>

    	<div class="actions">
        	<button class="btn" type="submit">Entrar</button>
    	</div>
	</form>

</div>

</body>
</html>
