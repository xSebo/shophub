function submit(shopId, email={"value":""}){
    var xhttp = new XMLHttpRequest();

    if(email.parentElement.children[0].classList.contains("subtitle")){
        emailValue = email.parentElement.children[0].innerHTML

    }else{
        emailValue = email.parentElement.children[0].value
        emailArray = []

        document.getElementsByName("staffEmail").forEach(x => emailArray.push(x.innerHTML))

        console.log(emailArray)

        if(emailArray.includes(emailValue)){
            document.getElementById("emailErrorField").innerHTML = "User already added"
            return
        }
    }

    if(emailValue == ""){
        document.getElementById("emailErrorField").innerHTML = "Field blank"
        return
    }

    document.getElementById("emailErrorField").innerHTML = ""


    let params = "shopId="+ shopId
    if(emailValue=="") {
        params += "&email=" + document.getElementById("emailAddress").value
    }else{
        params += "&email=" + emailValue;
    }
    xhttp.open("POST", '/updateStaff', true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.onload = function() {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            var response = xhttp.responseText
            if (response == "OK" || response == "USER REMOVED"){

                if(email.parentElement.children[0].classList.contains("subtitle")){
                    email.parentElement.parentElement.remove()

                }else{
                    document.getElementById("staffManagement").innerHTML+=
                        `<div id="staffManagement">
            <div class="staffManagementContainer">
                <p class="subtitle is-6 staffEmail" name="staffEmail" style="width:80%; margin-bottom: 0">${emailValue}</p>
                <button class="button is-danger is-outlined" style="border-bottom: 1px solid"
                        onclick="submit(${document.getElementById("shopId").value},this);">
                    <span class="icon is-small">
                        <i class="fas fa-times is-danger"></i>
                    </span>
                </button>
            </div>
            <p id="blackLine" class="subtitle is-6" style="margin-bottom:1%; width:50%"></p>
            </div>`
                }

            }else{
            }
        } else {
            console.error(xhttp.statusText);
        }
    };
    xhttp.send(params);
}
