

$(document).ready(function () {
    $('.btn-calendar').height($('.btn-calendar').width());
    $('.btn-false').addClass("disabled");
    var test = MD5("test");

});

function modifCalendar(mois,year){
    if(mois ==0){
        mois = 12;
        year--;
    }
    if (mois ==13){
        mois = 1;
        year++;
    }
   document.location.href="modifDateCalendar?mois="+mois+"&year="+year;
}

function initCalendar(){
   document.location.href="htmlCalendar";
}
function dayClick(numDay,numMois,annee){
    
    $("div[class='modal-content']").load("SeancesCtrler?numDay="+numDay+"&numMois="+numMois+"&annee="+annee);
    
}

$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});

$('div[data-toggle]').hover(
        function () {
            $(this).css('cursor', 'hand');

        },
        function () {
            $(this).css('cursor', 'pointer');

        }
);


function  test() {
 
    $(this).css('cursor', 'hand');
    $(this).css('cursor', 'pointer');
    $(this).tooltip(('show'));

    
}