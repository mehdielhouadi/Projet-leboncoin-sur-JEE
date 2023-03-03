<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="entities.Announcement"  %>
    <%@ page import="services.LeMauvaisCoinServices"  %>
    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Le Bon Coin</title>
	</head>
	<body>
		
		<h5>Log in :</h5>
		<form action="/LeBonCoin/show" method="post">
			<label for='txtEmail'>Email : </label>
			<input type='email' name='txtEmail'> <br/>
			
			<label for='txtPassword'>Password : </label>
			<input type='password' name='txtPassword'> <br/>

			<input type='submit' value='Go'>
		</form>
		
		<br>
		<a href='registerin'>Register in</a>
		<br><br><br>
		<a href=''>Filters</a>
		<h3>Announcements list : </h3>
		
		<table>
			<thead>
				<tr>
					<th>title</th>
					<th>price</th>
					<th>type</th>
					<th>description</th>
					<th>seller</th>
				</tr>
			</thead>
			
			<tbody>
				<% LeMauvaisCoinServices service = new LeMauvaisCoinServices(); %>
				<% for ( Announcement ann : new LeMauvaisCoinServices().getAllAnnouncement() ) {%>
				<tr>
					<td> <%= ann.getTitle() %> </td>
					<td> <%= ann.getPrice() %> </td>
					<td> <%= ann.getType() %> </td>
					<td> <%= ann.getDescription() %> </td>
					<td> <%= service.getCustomerPseudonymeById(ann.getIdCustomer()) %> </td>
					
				</tr>
				<% }%>
			</tbody>

		</table>
		
	</body>
</html>