function validate(){
    var nameVar = document.forms["signIn"]["name"].value;
    var surnameVar = document.forms["signIn"]["surname"].value;
    var password = document.forms["signIn"]["password"].value;
    //var form = document.getElementById("signIn");
    var valid = true;
    if (nameVar === ""){
        document.getElementById("NameTxt").style.display = "initial";
        valid = false;
    }
    if (surnameVar === ""){
        document.getElementById("SurnameTxt").style.display = "initial";
        valid = false;
    }
    if (password === ""){
        document.getElementById("PasswordTxt").style.display = "initial";
        valid = false;
    }
    return valid
    // if (valid === true){
    //     alert("All fields valid, submitting form");
    //     form.submit();
    // } else {
    //     alert("");
    // }

}