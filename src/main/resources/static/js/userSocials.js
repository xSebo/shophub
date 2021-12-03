function send(shopId){
    var xhttp = new XMLHttpRequest();

    let params = "shopId="+ shopId
    params += "&instagram=" + document.getElementById("instagram").value
    params += "&facebook=" + document.getElementById("facebook").value
    params += "&twitter=" + document.getElementById("twitter").value
    params += "&tiktok=" + document.getElementById("tiktok").value
    params += "&shopUrl=" +
        document.getElementById("shopUrlPrefix").value + document.getElementById("shopUrl").value

    xhttp.open("POST", '/updateSocials', true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.onload = function() {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            document.getElementById("submitButton").classList.remove("is-loading")
            var response = xhttp.responseText
            if (response == "OK"){
                document.getElementById("responseText").innerHTML = "Success!"
            }else{
                document.getElementById("responseText").innerHTML = "Something went wrong!"
            }

        } else {
            console.error(xhttp.statusText);
        }
    };
    document.getElementById("submitButton").classList.add("is-loading")
    xhttp.send(params);
}