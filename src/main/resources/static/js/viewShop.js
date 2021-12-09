var currentPage = 1;


function pageNav(direction){
    //this checks that current page will not go to 0 if "direction" = -1 / left
    if (direction === -1){ //"-1" = left
        if (currentPage - 1 !== 0){
            let Page = document.getElementById(currentPage);//hides current page
            Page.style.display = "none";
            currentPage = currentPage -1;
            Page = document.getElementById(currentPage);//displays new page
            Page.style.display = "flex";
        }
    } else{ //right
        let nextNumber = currentPage +1;
        try {
            let Page = document.getElementById(nextNumber);
            Page.style.display = "flex";
            Page = document.getElementById(currentPage);//displays new page
            Page.style.display = "none";
            currentPage = currentPage +1;
        }
        catch(err){

        }

    }
}


function changeUserStampPos(increment, shopId){
    if (increment === "subtract"){ //take away a stamp position from user
        params = 'direction=subtract';
    } else if(increment === "add"){ //add a stamp to user
        params = 'direction=add';
    } else {
        return null;
    }
    params = params + '&shopId=' + shopId;
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", '/changeUserPos', true); // true is asynchronous
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.onload = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            console.log(xhttp.responseText);
            window.location.reload(true);
        }
    }
    xhttp.onerror = function () {
        alert("There was an error. Database has not been updated.");
    }
    xhttp.send(params);
    return false;
}

function claimReward(rewardId){
    params = 'rewardId=' + rewardId;
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", '/reedeemReward', true); // true is asynchronous
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.onload = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            console.log(xhttp.responseText);
            var modal = document.getElementById("rewardModal");
            var code = document.getElementById("reward-code");
            code.innerText = xhttp.responseText;
            modal.classList.add("is-active");
        }
    }
    xhttp.onerror = function () {
        alert("There was an error. Database has not been updated.");
    }
    xhttp.send(params);
    return false;
}


function closeModal(){
    window.location.reload(true);
}

