let categoryID;
let listSize; //size of list of all the tags retrieved from database
let listOfCategories = ""; //this will be a string that contains each category
let count = 0;

function toggle_onclick(categoryID){
    const selected = document.getElementById(categoryID);
    const number = selected.value;
    if (number === "0"){
        selected.style.backgroundColor = 'green' ;
        selected.value = 1;
    } else {
        selected.style.backgroundColor = 'rgb(1,126,255)' ;
        selected.value = 0;
    }
}

function submitCategories(listSize){
    let selectedCategoriesID = [];
    for (let i = 1; i < parseInt(listSize) + 1; i++) {
        const selected = document.getElementById(i.toString());
        const number = selected.value;
        if (number === "1"){
            listOfCategories = listOfCategories + "," + i;
            count = count + 1;
        }
    }
    if (count < 3){
        alert("Please select 3 or more categories")
    } else {
        alert("sending categories to server")
        params = 'listOfTagIDs='+listOfCategories;

        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", '/selectCategories', true); // true is asynchronous
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.onload = function() {
            if (xhttp.readyState === 4 && xhttp.status === 200) {
                console.log(xhttp.responseText);
            } else if (xhttp.readyState === 401){
                status.innerHTML = "Check the credentials and try again."
            } else {
                status.innerHTML = "There was an error try again later."
            }
        };
        xhttp.onerror = function() {
            status.innerHTML = "There was an error. Please try again later."
        }
        xhttp.send(params);
        return false;
    }

}
