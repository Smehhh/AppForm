<%@page import="credit.entities.ClientRequest"%>
<%@page import="credit.analyst.Analyst"%>
<%@page import="credit.storage.Storage"%>
<%@page import="credit.storage.CookieStorage"%>
<%
    ClientRequest client = (ClientRequest)request.getAttribute("clientRequest");
    String[] errors = (String[])request.getAttribute("errors");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>ЗАЯВЛЕНИЕ КРЕДИТОПОЛУЧАТЕЛЯ</h3>
        <form method="post">
            <b>Фамилия</b> <input type="text" name="surname" value="<%= client != null ? client.getClient().getSurname() : "" %>"><br><br>
            <b>Имя</b> <input type="text" name="forename" value="<%= client != null ? client.getClient().getForename() : "" %>"><br><br>
            <b>Отчество</b> <input type="text" name="patronym" value="<%= client != null ? client.getClient().getPatronym() : "" %>"><br><br>
            <b>Электронная почта</b> <input type="text" name="mail" value="<%= client != null ? client.getClient().getMail() : "" %>"><br><br>
            <b>Возраст</b> <input type="text" name="age" placeholder="полных лет" value="<%= client != null ? client.getClient().getAge() : "" %>"><br><br>
            <b>Номер телефона</b> <input type="text" name="number" value="<%= client != null ? client.getClient().getNumber() : "" %>"><br><br>
            <b>Выберите срок кредита, дн</b>
            <input type="number" name="termInDays" min="1" max="3653" value="<%= client != null ? client.getTermInDays(): "" %>"><br/><br/>
            <b>Выберите сумму кредита</b><br><br>
            <input type="number" name="amount" min="500" max="10000000" step="100"
                   placeholder="в рублях" required="required" value="<%= client != null ? client.getAmount() : "" %>"><br><br>
            <b>Ваш годовой доход</b><br>
            <input type="number" min="0" name="income" required="required" value="<%= client != null ? client.getClient().getIncome() : "" %>"><br><br>
            <input type="submit" />
        </form>
        <ul>            
            <% for(String error : errors) { %>
            <li class="error"><%= error %></p>
            <% } %>
        </ul>
    </body>
</html>
