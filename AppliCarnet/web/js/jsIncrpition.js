$("input[type='email']").on('change', function () {
    var email= $("#email").val();
    var emailVerif= $("#emailVerif").val();
    if (email!=emailVerif){
        $("#emailDiff").show();
    }else{
       $("#emailDiff").hide(); 
    }
});

$("input[type='password']").on('change', function () {
    var pass= $("#password").val();
    var passVerif= $("#passwordVerif").val();
    if (pass!=passVerif){
        $("#passwordDiff").show();
    }else{
       $("#passwordDiff").hide(); 
    }
});



function VerifInscription(){
    
    var email= $("#email").val();
    var emailVerif= $("#emailVerif").val();
    var pass= $("#password").val();
    var passVerif= $("#passwordVerif").val();
    if (pass!=passVerif){
        alert("Les passwords ne sont pas identique");
        return false;
    }
    if (email!=emailVerif){
        alert("Les adresses mails ne sont pas identique");
        return false; 
    }
  $("input[name='password']").val(MD5(pass));
};






