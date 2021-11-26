function validateSignup(){
    let form = document.forms["signUpForm"]

    let valid = true;

    let nameField = form["newUserForename"]
    let nameHelpText = document.getElementById("nameHelpText")
    if(nameField.value == ""){
        nameField.classList.remove("is-success")
        nameField.classList.add("is-danger")
        nameHelpText.innerText = "Required"
        nameHelpText.style.display = "block"
        valid = false;
    }else{
        nameField.classList.add("is-success")
        nameField.classList.remove("is-danger")
        nameHelpText.innerText = ""
        nameHelpText.style.display = "none"
    }

    let surnameField = form["newUserLastname"]
    let surnameHelpText = document.getElementById("surnameHelpText")
    if(surnameField.value == ""){
        surnameField.classList.remove("is-success")
        surnameField.classList.add("is-danger")
        surnameHelpText.innerText = "Required"
        surnameHelpText.style.display = "block"
        valid = false;
    }else{
        surnameField.classList.add("is-success")
        surnameField.classList.remove("is-danger")
        surnameHelpText.innerText = ""
        surnameHelpText.style.display = "none"
    }

    var mail_format = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\\\[\x01-\x09\x0b\x0c\x0e-\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\\])";
    const regex = new RegExp(mail_format);
    let emailField = form["newUserEmail"]
    let emailHelpText = document.getElementById("emailHelpText")
    if(emailField.value == ""){
        emailField.classList.remove("is-success")
        emailField.classList.add("is-danger")
        emailHelpText.innerText = "Required"
        emailHelpText.style.display = "block"
        valid = false;
    }else{
        if(!regex.test(emailField.value)){
            emailField.classList.remove("is-success")
            emailField.classList.add("is-danger")
            emailHelpText.innerText = "Invalid Email"
            emailHelpText.style.display = "block"
            valid = false;
        }else{
            fetch("/emailInUse?email=" + emailField.value)
                .then(result=>result.json())
                .then(data => {
                    if(data){
                        emailField.classList.remove("is-success")
                        emailField.classList.add("is-danger")
                        emailHelpText.innerText = "Email in use"
                        emailHelpText.style.display = "block"
                        valid = false;
                    }else{
                        emailField.classList.add("is-success")
                        emailField.classList.remove("is-danger")
                        emailHelpText.innerText = ""
                        emailHelpText.style.display = "none"
                    }
                })
        }
    }



    let passwordField = form["newUserPassword"]
    let passwordHelpText = document.getElementById("passwordHelpText")
    if(passwordField.value == ""){
        passwordField.classList.remove("is-success")
        passwordField.classList.add("is-danger")
        passwordHelpText.innerText = "Required"
        passwordHelpText.style.display = "block"
        valid = false;
    }else{
        passwordField.classList.add("is-success")
        passwordField.classList.remove("is-danger")
        passwordHelpText.innerText = ""
        passwordHelpText.style.display = "none"
    }

    return valid
}