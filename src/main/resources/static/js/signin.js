async function validateLogin(){
    let form = document.forms["loginForm"]

    let valid = true;
    let helpText = document.getElementById("loginHelpText");

    //Check Email isn't empty
    let emailField = form["loginEmail"]
    if(emailField.value == ""){
        emailField.classList.add("is-danger")
        valid = false;
    }else{
        emailField.classList.remove("is-danger")
    }

    //Check password isn't empty
    let passwordField = form["loginPassword"]
    if(passwordField.value == ""){
        passwordField.classList.add("is-danger")
        valid = false;
    }else{
        passwordField.classList.remove("is-danger")
    }

    //Set help text for empty fields
    if(!valid){
        helpText.style.display = "block";
        helpText.innerText = "Fields cannot be empty";
    }else{
        helpText.style.display = "none";
    }

    if(valid){
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "/login_api", true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function () {
            if (this.readyState != 4) return;

            if (this.status == 200) {
                window.location.href="/";
            }else{
                passwordField.classList.add("is-danger")
                emailField.classList.add("is-danger")
                helpText.style.display = "block";
                helpText.innerText = "Incorrect email or password";
            }
        };
        xhr.send("loginEmail=" + emailField.value + "&loginPassword=" + passwordField.value);
    }
}