function Studente(matricola, nome, cognome){
	this.matricola = matricola;
	this.nome = nome;
	this.cognome = cognome;	
}

function setCorsoDiLaurea(){
	var valore = $("#dip").val();
	
	$.ajax({
			type: "GET",
			url: "dammiCorsiDiLaurea",
			data: {dipartimento: valore},
			success: function(data){
				$("#cLaurea").removeAttr("disabled");
				$("#cLaurea").html(data);
			}
		}
	);
}

function caricaDettagliScuola(stud){
	var studente = {
			matricola : stud.matricola,
			nome : stud.nome,
			cognome : stud.cognome,						
		};	
	$.ajax({
		type: "POST",		
		url: "dettagliScuola",		
		datatype : "json",
		data: JSON.stringify(studente),
		success: function(data){
			var scuola = JSON.parse(data);
			$("#dettagliScuola").text("Nome Scuola : " + scuola.nome + " Codice meccanografico : " + scuola.codiceMeccanografico); 
		}	
	});	
}