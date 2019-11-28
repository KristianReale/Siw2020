function Studente(matricola, nome, cognome, dataNascita){
	this.matricola = matricola;
	this.nome = nome;
	this.cognome = cognome;
	this.dataNascita = dataNascita;
}

var studente1 = new Studente("103123", "Mario", "Rossi", new Date(1/7/2001));
var studente2 = new Studente("103", "Giorgio", "Bianchi", new Date(13/04/2001));

calcolaISEE(studente1);
calcolaISEE(studente2);

function calcolaISEE(studente){
	alert("Inizio procedura calcolo ISEE per lo studente "
			+ studente.nome + " " + studente.cognome);
	var numeroComponenti = prompt("Inserici il numero dei componenti");
	componentiReddito = new Array();
	componentiPatrimonio = new Array();
	
	var i;
	for (i = 0; i < numeroComponenti; i++){
		componentiReddito[i] = prompt("Inserici il reddito del componente " 
														+ (i + 1));
		componentiPatrimonio[i] = prompt("Inserici il patrimonio del componente " 
														+ (i + 1));
	}
	
	var redditoComplessivo = 0;
	var patrimonioComplessivo = 0;
	
	for (i = 0; i < numeroComponenti; i++){
		redditoComplessivo += parseInt(componentiReddito[i]);
		patrimonioComplessivo += parseInt(componentiPatrimonio[i]);
	}
	
	ISR = redditoComplessivo;
	ISP = patrimonioComplessivo;
	
	ISE = ISR + ISP * 20/100;
	
	scaleEquivalenza =
	{
			"1" : 1,
			"2" : 1.57,
			"3" : 2.04,
			"4" : 2.46,
			"5" : 2.85,
			
	};
	
	var SE;
	//switch (numeroComponenti){
	//case "1":
	//	SE = scaleEquivalenza["1"];
	//	break;
	//case "2":
	//	SE = scaleEquivalenza["2"];
	//	break;
	//case "3":
	//	SE = scaleEquivalenza["3"];
	//	break;
	//case "4":
	//	SE = scaleEquivalenza["4"];
	//	break;
	//case "5":
	//	SE = scaleEquivalenza["5"];
	//	break;
	//}
	
	var calcolaSe = function(numeroComponenti, scaleEquivalenza){
		if (numeroComponenti < 5){
			SE = scaleEquivalenza[numeroComponenti];
		}else{
			SE = scaleEquivalenza["5"];
		}
		return SE;
	}
	
	SE = calcolaSe(numeroComponenti, scaleEquivalenza);
	
	
	
	ISEE = ISE / SE;
	
	report = "Report ISEE Calcolato:\n";
	report += "Numero componenti nucleo familiare:" + numeroComponenti + "\n";
	report += "Reddito complessivo nucleo familiare:" + redditoComplessivo + "\n";
	report += "Patrimonio complessivo nucleo familiare:" + patrimonioComplessivo + "\n";
	report += "Valore ISE:" + ISE + "\n";
	report += "Scala di equivalenza applicata:" + SE + "\n";
	report += "Valore ISEE:" + ISEE + "\n";
	
	alert(report);
}







