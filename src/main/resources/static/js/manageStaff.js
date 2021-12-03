function submit(shopId, email={"value":""}){
    var xhttp = new XMLHttpRequest();

    if(email.parentElement.children[0].classList.contains("subtitle")){
        email = email.parentElement.children[0].innerHTML
    }else{
        email = email.parentElement.children[0].value
    }

    let params = "shopId="+ shopId
    if(email=="") {
        params += "&email=" + document.getElementById("emailAddress").value
    }else{
        params += "&email=" + email;
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
