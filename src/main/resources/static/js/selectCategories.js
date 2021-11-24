let categoryID; //ID of the Tag
let listSize; //size of list of all the tags retrieved from database
let listOfCategories = ""; //this will be a string that contains each category
let count = 0; //variable used to count how many tags the user has selected

//function will change/toggle each button color when clicked and change the value
function toggle_onclick(categoryID){
    const selected = document.getElementById(categoryID);
    const number = selected.value;
    if (number === "0"){
        selected.style.backgroundColor = 'green' ;
        selected.value = 1; //value 1 means user has selected this category/tag
    } else {
        selected.style.backgroundColor = 'rgb(1,126,255)' ;
        selected.value = 0; //value 0 means user hasn't selected this category/tag
    }
}

//this function will send a list of all the selected tags to server
function submitCategories(listSize){
    for (let i = 1; i < parseInt(listSize) + 1; i++) { //loops through all of the tags/categories
        const selected = document.getElementById(i.toString()); //selects the tag at the current iteration
        const number = selected.value;
        if (number === "1"){ //if tag has been selected by user
            listOfCategories = listOfCategories + "," + i; //adds the ID to a list separated by ","
            count = count + 1; //number of tags selected + 1
        }
    }
    if (count < 3){ //checks the user has selected the correct number of categories/tags
        alert("Please select 3 or more categories")
    } else {
        params = 'listOfTagIDs='+listOfCategories;
        //Sends list to server
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
