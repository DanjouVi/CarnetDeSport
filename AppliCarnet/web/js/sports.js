
function rowStyle(row, index) {
    if (index % 2 === 0) {
        return {
            classes: 'active'
        };
    }
    return {};
}



function deleteSport(nomSport, nbSeance){
    var supr = true;
    if(nbSeance!==0){
        supr = confirm("Attention les "+nbSeance+" Seances associées a ce sport seront suprimées en meme temps que le sport : "+nomSport);
    }else{
        supr = confirm("Attention vous etes sur le point de supprimer le sport : "+nomSport);
    }
    if(supr) document.location.href="delSport?nomSport="+nomSport+"&nbSeance="+nbSeance; 
}


function modifSport(nomSport,nbSeance){   
     document.location.href="modifSport?nomSport="+nomSport;
}
function saveModifSport(nomSport,nbSeances,typeSeance){
    var modif = true;
    if(nbSeances>0){
        if(typeSeance != $("#typeSport").val()){
            alert("il est imposible de modifier le type d'un sport comportant des séances");
            modif = false;
        }else{
            modif = confirm("Attention les "+nbSeances+" séances associées a ce sport seront modifiée en meme temps que le sport : "+nomSport);
        }
    }
    if(modif)
         document.location.href="saveModifSport?nomSport="+$("#inputNomSport").val()+"&typeSport="+$("#typeSport").val();
    
    
}

function sportPreDef(isPreDef) {
    var nom = "";
    if (isPreDef == "true") {
        $("#nomSportDef").show();
        $("#nomSport").hide();
        nom = $("#nomSportDef").val();
    } else {
        $("#nomSportDef").hide();
        $("#nomSport").show();
        nom = $("#nomSport").val();
    }
}


function etapeSuivante() {
    var nom = "";
    if ($("[name='SportPreDef']").val() == "true") {
        nom = $("[name='nomSportDef']").val();
    } else {
        nom = $("[name='nomSport']").val();
    }

    if (nom != "") {
        $.ajax({
            type: 'post', // it's easier to read GET request parameters
            url: 'SportExiste',
            dataType: 'JSON',
            data: {
                nomSport: nom,
                isPreDef : $("[name='SportPreDef']").val()
            },
            success: function (data) {
                if (data.existe == "false") {
                    document.location.href="Sports";
                    /*$("#formSportBis").show();
                    $("[name='nomSport']").prop('disabled', true);
                    $("[name='nomSportDef']").prop('disabled', true);
                    $("[name='SportPreDef']").prop('disabled', true);
                    $("#buttonEtapeSuivante").hide();*/
                } else {
                   alert("Vous utilisez déja ce nom de sport");
                }

            },
        });

    }else{
        alert("Un nom de sport est nécessaire");
    }

}

function initSport(){
    document.location.href="ResetSport";
}
function saveSport(){
    var typeSport = $("[name='typeSport']").val();
    document.location.href="SaveSport?typeSport="+typeSport;
}
$(document).ready(function () {
    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });

    $('.btn-calendar').height($('.btn-calendar').width());
});
