function changeAccount(userId){
    let params="userId=" + userId;
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", '/changeAccount', true); // true is asynchronous
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.onload = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            console.log(xhttp.responseText);
            window.location.href="/";
        }
    };
    xhttp.onerror = function () {
        alert("There was an error. Please try again later.");
    }
    xhttp.send(params);

    return false;
}

function searchBusinessOwners(event){
    var searchString = event.target.value.toLowerCase();
    console.log(event);
    for(let row of document.getElementsByClassName("custom-record")){
        var record = row.children[0].innerText.toLowerCase() + row.children[1].innerText.toLowerCase();
        if(record.includes(searchString)){
            row.classList.remove("hidden");
        } else {
            row.classList.add("hidden");
        }
    }
}