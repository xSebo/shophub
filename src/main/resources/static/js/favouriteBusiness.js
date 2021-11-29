function favouriteBusiness(e,shopId){

    if(e.classList.contains("active")){
        e.classList.remove("active")
    }else{
        e.classList.add("active")
    }

    var xhttp = new XMLHttpRequest();
    let params= "shopId=" + shopId
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

function redirect(shopId){
    location.href = "businessDetails?shopId="+shopId;
}