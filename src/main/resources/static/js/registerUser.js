function validate(){
    var nameVar = document.forms["signIn"]["name"].value; //retrieves the text in each textbox in form
    var surnameVar = document.forms["signIn"]["surname"].value;
    var password = document.forms["signIn"]["password"].value;
    var valid = true;
    if (nameVar === ""){ //checks each textbox isn't null
        document.getElementById("NameTxt").style.display = "initial";
        valid = false; // ^^ if null, the hidden error message will have its display changed so it will appear
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