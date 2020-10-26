<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CarpeDiem - Escola Virtual</title>
</head>
<body bgcolor="#ffffff">

<div>
      <%      
    		String redireciona = (String)request.getAttribute("redireciona");          
    		String mensagem = (String)request.getAttribute("mensagem");
    		
    		
      
  			if (redireciona == null) 
  			{ 
  			%>        
       			<jsp:include page="CarpeDiem.html"/>
 		    <%
 		    } 
  			else if (!redireciona.equals("")) 
 		    {  		    
 		    %>      
                <jsp:include page="<%=redireciona%>"/> 
            <%}%>
        
</div>

   
 
</body>
</html>