function submit(shopId, email={"value":""}){
    var xhttp = new XMLHttpRequest();

    if(email.parentElement.children[0].classList.contains("subtitle")){
        emailValue = email.parentElement.children[0].innerHTML
        email.parentElement.parentElement.remove()

    }else{
        emailValue = email.parentElement.children[0].value
        document.getElementById("staffManagement").innerHTML+=
            `<div id="staffManagement">
            <div class="staffManagementContainer">
                <p class="subtitle is-6" style="width:50%; margin-bottom: 0">${emailValue}</p>
                <button class="button is-danger is-outlined" style="border-bottom: 1px solid black"
                        onclick="submit(${document.getElementById("shopId").value},this);">
                    <span class="icon is-small">
                        <i class="fas fa-times is-danger"></i>
                    </span>
                </button>
            </div>
            <p id="blackLine" class="subtitle is-6" style="border-bottom: 1px solid #00b89c; margin-bottom:1%; width:50%"></p>
            </div>`
    }

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
            if (response == "success"){
            }else{
            }
        } else {
            console.error(xhttp.statusText);
        }
    };
    xhttp.send(params);
}
