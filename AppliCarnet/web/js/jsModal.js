/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$('#dureeSeance').timepicker({
    timeFormat: 'HH:mm:ss',
    showButtonPanel: false,
    hourGrid: 2,
    minuteGrid: 10,
    secondGrid: 10,
    hourMax: 10,
    stepSecond: 5
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

    if (typeSport == "default") {
        $("#typeDistance").hide();
    } else if (typeSport == "distance") {
        $("#typeDistance").show();
    } else {

    }
}
;

function chgParcours(dataParcours) {
    if (dataParcours != "") {
        var lesDataParcours = dataParcours.split("_-_");
        var id = lesDataParcours[0];
        var distance = lesDataParcours[1];
        var denivele = lesDataParcours[2];
        $("#nbTours").val(1);
        $("#nbTours").prop('disabled', false);

        $("#distance").val(distance);
        $("#distance").prop('disabled', true);

        $("#denivele").val(denivele);
        $("#denivele").prop('disabled', true);
    } else {
        $("#nbTours").prop('disabled', true);
        $("#distance").prop('disabled', false);
        $("#denivele").prop('disabled', false);
    }
}

function chgNbTours(nbTours) {
    var dataParcours = $("#selectParcours").val();
    var lesDataParcours = dataParcours.split("_-_");
    var id = lesDataParcours[0];
    var distance = lesDataParcours[1];
    var denivele = lesDataParcours[2];

    $("#distance").val(distance * nbTours);

    $("#denivele").val(denivele * nbTours);
}

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
        document.location.href = "SaveSeance?meteo=" + meteo + "&comment=" + comment + "&lieu=" + lieu + "&sport=" + sport + "&duree=" + duree + "&nomSeance=" + nomSeance + "&date=" + date;
    }
}