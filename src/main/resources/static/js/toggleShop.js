let shopId;
function toggleShop(shopId){
    params = 'shopId=' + shopId;
    //Sends list to server
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", '/toggleShop', true); // true is asynchronous
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.onload = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            console.log(xhttp.responseText);
            alert("shop activity has been successfully changed");
            location.reload();
        } else if (xhttp.readyState === 401) {
            alert("Check the credentials and try again.");
        } else {
            alert("There was an error try again later.");
        }
    };
    xhttp.onerror = function () {
        alert("There was an error. Please try again later.");
    }
    xhttp.send(params);
    return false;
}
