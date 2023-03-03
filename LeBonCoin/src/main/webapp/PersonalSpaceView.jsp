<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="entities.Announcement"  %>
    <%@ page import="services.LeMauvaisCoinServices"  %>
    <%@ page import="java.util.*" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Bienvenue</title>
	</head>
	<body>
		<h2>Bienvenue :  ${pseudonyme} </h2>>
			<tbody>
				<% LeMauvaisCoinServices service = new LeMauvaisCoinServices(); %>
				<% for ( Announcement ann : (List<Announcement>) session.getAttribute("listAnnoncementConnectedCustomer") ) {%>
				<tr>
					<td> <%= ann.getTitle() %> </td>
					<td> <%= ann.getPrice() %> </td>
					<td> <%= ann.getType() %> </td>
					<td> <%= ann.getDescription() %> </td>
					<a href='/LeBonCoin/modify?idAnnouncement=<%= ann.getId() %>'>Modify</a>
					<a href='/LeBonCoin/delete?idAnnouncement=<%= ann.getId() %>'>Delete</a>
				</tr>
				<% }%>
			</tbody><br>
		
		<a href='create'>Create announcement</a>
		<a href='show'> deconnexion</a>
				
	</body>
</html>