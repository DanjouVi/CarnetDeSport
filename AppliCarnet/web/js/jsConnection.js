function VerifConnection(){
    
    var pass= $("input[name='password']").val();
    $("input[name='password']").val(MD5(pass));
};






