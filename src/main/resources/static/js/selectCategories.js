const categoryID = 0;
var tags = [];

function toggle_onclick(categoryID){
    const selected = document.getElementById(categoryID);
    const number = selected.value;
    let tagIndex = 0;
    if (tags.includes(categoryID)){
        selected.style.backgroundColor = 'rgb(1,126,255)';
        tags = tags.filter(tag => tag != categoryID);
    } else {
        selected.style.backgroundColor = 'green' ;
        tags.push(categoryID)
    }

}

//this function will send a list of all the selected tags to server
function submitCategories(listSize){
    let listOfCategories = "";
    if (tags.length < 3) { //checks the user has selected the correct number of categories/tags
        alert("Please select 3 or more categories");
    } else {
        for (let i = 0; i < tags.length; i++) {
            listOfCategories = listOfCategories + "," + tags[i];
        }
        listOfCategories = listOfCategories.substring(1, listOfCategories.length);
        params = 'listOfTagIDs=' + listOfCategories;
        //Sends list to server
        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", '/selectCategories', true); // true is asynchronous
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.onload = function () {
            if (xhttp.readyState === 4 && xhttp.status === 200) {
                console.log(xhttp.responseText);
                window.location.reload(true);
            } else if (xhttp.readyState === 401) {
                status.innerHTML = "Check the credentials and try again.";
            } else {
                status.innerHTML = "There was an error try again later.";
            }
        };
            xhttp.onerror = function () {
                status.innerHTML = "There was an error. Please try again later.";
            }
        xhttp.send(params);
        return false;
    }

}

function filterRewards(input){
    let added = 0;
    let rewards = document.getElementsByClassName("reward_card");
    for(let reward of rewards){
        let title = reward.getElementsByClassName("title")[0].innerText
        if(title.toLowerCase().replace(" ","").includes(input.value.toString().toLowerCase().replace(" ", ""))){
            reward.parentElement.classList.remove("hidden")
        }else{
            reward.parentElement.classList.add("hidden")
            added++;
        }
    }

    if (added == rewards.length){
        document.getElementById("filterText").style.display = "block";
    }else{
        document.getElementById("filterText").style.display = "none";
    }
}





