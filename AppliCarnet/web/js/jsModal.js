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

    $("#typeDistance").hide();
    $("#typeMatch").hide();
    if (typeSport == "distance") {
        $("#typeDistance").show();
    } else if (typeSport == "match") {
        $("#typeMatch").show();
        $("#typeMatch").load("matchCtrl?action=init");
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
        $("#nbTours").val(1);
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
    var erreur = false;
    var meteo = $("#selectMeteo").val();
    var comment =$("#comment").val().split("\n").join('<br/>');
    var lieu = $("#inputLieu").val();
    var sport = $("#selectSport").val().split("_-_")[0];
    var typeSport = $("#selectSport").val().split("_-_")[2];
    var duree = $("#dureeSeance").val();
    var nomSeance = $("#inputNomSeance").val();

    if (nomSeance == "") {
        $("#inputNomSeance").css("border-color", "red");
        erreur = true;
    } else {
        $("#inputNomSeance").css("border-color", "black");
    }

    if (typeSport == "match") {
        $.ajax({
            type: 'post', // it's easier to read GET request parameters
            url: 'matchCtrl',
            dataType: 'JSON',
            data: {
                action: "verifMatch"
            },
            success: function (data) {
                if (data.length > 0) {
                    erreur = true;
                    for (var i = 0; i < data.length; i++)
                        $("#" + data[i]).css("border-color", "red");
                }
            }
        });
    }

    var url = "SaveSeance?meteo=" + meteo + "&comment=" + comment + "&lieu=" + lieu + "&sport=" + sport + "&duree=" + duree + "&nomSeance=" + nomSeance + "&date=" + date +"&type="+typeSport;
    if(typeSport =="distance"){
        var idParcours = $("#selectParcours").val().split("_-_")[0];
        if(idParcours!==""){
           var nbTours = $("#nbTours").val();
           url += "&nbTours="+nbTours+"&idParcours="+idParcours;
        }else{
           url +="&distance="+$("#distance").val()+"&denivele="+$("#denivele").val();
        }
    }

    if (!erreur)
      document.location.href = url;

}

function delSeance (idSeance){
    var url ="ModifSeance?action=del&idSeance="+idSeance;
    document.location.href = url;
}

function modifSeance(idSeance,numDay,numMois,annee){
     $("div[class='modal-content']").load("SeancesCtrler?numDay="+numDay+"&numMois="+numMois+"&annee="+annee+"&modifSeance=true&idSeance="+idSeance);
}
function addMatch() {
    $("#typeMatch").load("matchCtrl?action=addMatch");
}

function delMatch(numMatch) {
    $("#typeMatch").load("matchCtrl?action=delMatch&num=" + numMatch);
}

function addPersonne(numMatch, typePersonne) {
    $("#typeMatch").load("matchCtrl?action=addPersonne&num=" + numMatch + "&type=" + typePersonne);
}

function delPersonne(numMatch, numPersonne, typePersonne) {
    $("#typeMatch").load("matchCtrl?action=delPersonne&num=" + numMatch + "&type=" + typePersonne + "&numPers=" + numPersonne);
}

function changePersonne(numMatch, numPersonne, typePersonne, value) {
    $("#typeMatch").load("matchCtrl?action=changePersonne&num=" + numMatch + "&type=" + typePersonne + "&numPers=" + numPersonne + "&value=" + value);
}

function changeScore(numMatch, typePersonne, value) {
    $("#typeMatch").load("matchCtrl?action=changeScore&num=" + numMatch + "&type=" + typePersonne + "&value=" + value);
}