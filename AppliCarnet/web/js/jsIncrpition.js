$("#emailVerif").on('change', function () {
    var email= $("#email").val();
    var emailVerif= $("#emailVerif").val();
    if (email!=emailVerif){
        $("#emailDiff").show();
    }else{
       $("#emailDiff").hide(); 
    }
});

$("#passwordVerif").on('change', function () {
    var email= $("#password").val();
    var emailVerif= $("#passwordVerif").val();
    if (email!=emailVerif){
        $("#passwordDiff").show();
    }else{
       $("#passwordDiff").hide(); 
    }
});
