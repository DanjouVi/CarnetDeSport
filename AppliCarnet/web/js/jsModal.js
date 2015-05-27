/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$('#dureeSeance').timepicker({
    showButtonPanel: false,
    hourGrid: 2,
    minuteGrid: 10,
    hourMax: 10
});

chgSport($("#selectSport").val());

function imageMeteo(meteo) {
    $("#imgMeteo").attr("src", "images/meteo/" + meteo + ".png");
    if (meteo != "") {
        $("#imgMeteo").show();
    } else {
        $("#imgMeteo").hide();
    }
}
;

function chgSport(dataSport) {

    var lesDataSport = dataSport.split("_-_");
    var nomSport = lesDataSport[0];
    var urlImage = lesDataSport[1];
    var typeSport = lesDataSport[2];

    $("#imgSport").attr("src", "images/" + urlImage);
    if (urlImage != "null") {
        $("#imgSport").show();
    } else {
        $("#imgSport").hide();
    }
}
;

function valSeance(date) {
    var meteo = $("#selectMeteo").val();
    var comment = $("#comment").val();
    var lieu = $("#inputLieu").val();
    var sport = $("#selectSport").val().split("_-_")[0];
    var duree = $("#dureeSeance").val();
    var nomSeance = $("#inputNomSeance").val();
    if (nomSeance == "") {
        $("#labelNomSeance").css("color", "red");
    } else {
        $("#labelNomSeance").css("color", "black");
        document.location.href="SaveSeance?meteo="+meteo+"&comment="+comment+"&lieu="+lieu+"&sport="+sport+"&duree="+duree+"&nomSeance="+nomSeance+"&date="+date;
    }
}