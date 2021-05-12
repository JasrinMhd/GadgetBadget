  <%-- <%

	//Initialize--------------------------------------------
	session.setAttribute("statusMsg", "");
	System.out.println("Trying to process....");

	//Save---------------------------------
	if (request.getParameter("firstname") != null) 
	{
		
		Researcher Obj = new Researcher();
		String stsMsg = "";
		
		//Insert--------------------------
		if (request.getParameter("hididSave") == "") {
			
			stsMsg = Obj.insertResearcher(request.getParameter("firstname"), 
								request.getParameter("lastname"),
								request.getParameter("gender"), 
								request.getParameter("email"), 
								request.getParameter("password"));
			
		} else
		//Update----------------------
		{
			
			stsMsg = Obj.updateResearcher(request.getParameter("hididSave"), 
					request.getParameter("firstname"),
					request.getParameter("lastname"),
					request.getParameter("gender"),
					request.getParameter("email"),
					request.getParameter("password"));
		}
		session.setAttribute("statusMsg", stsMsg);
	}

	//Delete-----------------------------
	if (request.getParameter("hididDelete") != null) {
		
		Researcher Obj = new Researcher();
		
		String stsMsg = Obj.deleteResearcher(request.getParameter("hididDelete"));
		session.setAttribute("statusMsg", stsMsg);
	}
%> --%>  






 <%@page import="com.Researcher" %>
 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Researcher Details</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Researcher.js"></script>
</head>
<body>

	
	<div class="container">
		<div class="row">
			<div class="col-8">
				<h1>Researcher Details</h1>
				
				<form id="formResearcher" name="formResearcher">

					First Name: <input id="firstname" name="firstname" type="text"
						class="form-control form-control-sm">
						
					 <br> Last Name:
						<input id="lastname" name="lastname" type="text"
						class="form-control form-control-sm">
						
					<br> Gender: 
						 <input id="gender" name="gender" type="text"
						class="form-control form-control-sm"> 
						
					<br> Email:
					 	<input id="email" name="email" type="text"
						class="form-control form-control-sm">
						
					 <br> Password:
						<input id="password" name="password" type="text"
						class="form-control form-control-sm">
						
						 <br> 
						 <input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary">
						 <input type="hidden"
						id="hididSave" name="hididSave" value="">


				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divResearcherGrid">
			 	  <%
					Researcher Obj = new Researcher();
					out.print(Obj.readResearcher());
				%> 
				</div>  
			</div>
		</div>



	</div>

</body>
</html>