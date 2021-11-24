function favouriteBusiness(e,shopId){

    e.classList.add("active");

    var xhttp = new XMLHttpRequest();
    let params= 'userId=' + "2" + "&shopId=" + shopId
    xhttp.open("POST", '/favouriteBusiness', true);
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