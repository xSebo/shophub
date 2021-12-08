function toggleShop(shopId){
    const textResponse = document.getElementById("status-text");
    params = 'shopId=' + shopId;
    //Sends list to server
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", '/toggleShop', true); // true is asynchronous
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.onload = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            console.log(xhttp.responseText);
            if(textResponse.innerText === "Your site is currently LIVE, changing this will hide your site from all customers."){
                textResponse.innerText = "Your site is currently not LIVE, changing this will show your site to anyone on ShopHub.";
            } else if (textResponse.innerText === "Your site is currently not LIVE, changing this will show your site to anyone on ShopHub."){
                textResponse.innerText = "Your site is currently LIVE, changing this will hide your site from all customers.";
            } else{
                window.location.href = "/settings?tab=shop-setup";
            }
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
