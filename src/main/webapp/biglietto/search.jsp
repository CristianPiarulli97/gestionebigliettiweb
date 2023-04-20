<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="it.prova.gestionebigliettiweb.model.Biglietto"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ricerca articolo</title>
</head>
<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Cerca articolo</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Inserisci dati da ricercare</h5> 
				    </div>
				    <div class='card-body'>
		
		
		
	 	<form method="post" action="ExecuteSearchBigliettoServlet" class="row g-3" novalidate="novalidate">
							
							
								<div class="col-md-6">
									<label for="provenienza" class="form-label">Provenienza <span class="text-danger">*</span></label>
									<input type="text" name="provenienza" id="provenienza" class="form-control" placeholder="Inserire la provenienza">
								</div>
								
								<div class="col-md-6">
									<label for="destinazione" class="form-label">Destinazione <span class="text-danger">*</span></label>
									<input type="text" name="destinazione" id="destinazione" class="form-control" placeholder="Inserire la destinazione">
								</div>
							
								<div class="col-md-6">
									<label for="prezzo" class="form-label">Prezzo <span class="text-danger">*</span></label>
									<input type="number" class="form-control" name="prezzo" id="prezzo" placeholder="Inserire prezzo">
								</div>
								
								<div class="col-md-3">
									<label for="data" class="form-label">Data<span class="text-danger">*</span></label>
									<input class="form-control"  name="data" id="data" type="date" placeholder="dd/MM/yy" title="formato : gg/mm/aaaa"/>
								</div>
								
								
							<div class="col-12">
								
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
							</div>
		
						</form>
								    			
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	
<body>

</body>
</html>