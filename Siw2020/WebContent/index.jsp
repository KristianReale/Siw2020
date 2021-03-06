<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestione Segreteria</title>
<!-- 
	<style type="text/css">
		h2 {
			color:red;
			text-shadow: 3px 1px blue;
		}
		p {
			color:green;
		}
	</style>
-->

<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<LINK rel="stylesheet" href="css/index.css" type="text/css">
</head>
<body>
<header>
	<aside>
		<!-- <a href="http://www.unical.it">-->
			<figure>
			  <img src="images/logo_unical.png" width="300"/>
			  <figcaption>Universit� della Calabria</figcaption>
			</figure>
				
		<!-- </a> -->
	</aside>
	<aside>
		<nav class="navbar">
			<ul class="nav navbar-nav">
				<li class="nav-item"><a class="dropdown-item" href="">Chi siamo</a></li></li>
				<li class="nav-item"><a class="dropdown-item" href="">Contattaci</a></li></li>
				<li class="nav-item">
					<c:if test="${utente != null}">
						Benvenuto ${utente.username};
						<a class="dropdown-item" href="login?logout=true">Logout</a>
					</c:if>
					<c:if test="${utente == null}">
						<a class="dropdown-item" href="login">Login</a>
					</c:if>					
				</li></li>
			</ul>
		</nav>
	</aside>
	<h2>Portale segreteria studenti</h2>
	<h3>Portale gestione segreteria studenti</h3>
	
</header>
<h2 class="intestazione">
	Benvenuti nel portale di gestione delle Segreterie Studenti
</h2>
<p>
	Utilizza il menu in alto per 
	<strong>
		navigare tra le <i>varie</i> sezioni della <u>pagina</u>
	</strong>
</p>
<div>
	<p>
		Ti auguriamo una felice navigazione
	</p>
</div>
<section>
	<nav class="navbar navbar-inverse">
		<ul class="nav navbar-nav">
			<li class="nav-item"><a href="#">Home</a></li>
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">Studenti<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a class="dropdown-item" href="gestioneStudenti/ottieniStudenti">Elenco Studenti</a></li>			
					<li><a class="dropdown-item" href="gestioneStudenti/iscriviStudenti">Iscrivi uno studente</a></li>				
					<li><a class="dropdown-item" href="gestioneStudenti/isee.html">Calcola ISEE</a></li>				
				</ul>
			</li>
			<li class="nav-item dropdown"><a href="#">Corsi</a></li>
			<li class="nav-item dropdown"><a href="#">Corsi di laurea</a></li>
			<li class="nav-item dropdown"><a href="#">Dipartimenti</a></li>
		</ul>
	</nav>	
</section>	
<section>
	<div class="row">
		<article class="col-xs-5 col-sm-5 col-lg-5 jumbotron">
			<h3 id="art1" class="intestazione">Dicono di noi</h3>
			<p>La segreteria � un servizio di riferimento che serve per...</p>
		</article>
		<div class="col-xs-2 col-sm-2 col-lg-2"></div>
		<article class="col-xs-5 col-sm-5 col-lg-5 jumbotron">
			<h3  id="art2"  class="intestazione">Centro ICT di Ateneo</h3>
			<p>Dalla pagine accessibile da <a href="http://www.unical.it">questo link</a></p>	
		</article>	
	</div>
<!-- 	<div class="row">
		<article>
			<h3 id="art1" class="intestazione">Dico</h3></header>
			<p>La segreteria � un servizio di riferimento che serve per...</p>
		</article>
				
		<article>
			<h3  id="art2"  class="intestazione">ICT</h3></header>
			<p>Dalla pagine accessibile da <a href="http://www.unical.it">questo link</a></p>	
		</article>	
	</div> -->
</section>
<section>	
	<article class="col-xs-5 col-sm-5 col-lg-5 jumbotron">
		<h3  id="art2"  class="intestazione">Centro ICT di Ateneo2</h3></header>
		<p>Dalla pagine accessibile da <a href="http://www.unical.it">questo link</a></p>	
	</article>
	<div class="col-xs-2 col-sm-2 col-lg-2"></div>
	<article class="col-xs-5 col-sm-5 col-lg-5 jumbotron">
		<h3  id="art2"  class="intestazione">Centro ICT di Ateneo3</h3></header>
		<p>Dalla pagine accessibile da <a href="http://www.unical.it">questo link</a></p>	
	</article>	
</section>
<footer>
	<a href="http://www.unical.it">
		Clicca qui per accedere al sito dell'unical
	</a>
</footer>

</body>
</html>