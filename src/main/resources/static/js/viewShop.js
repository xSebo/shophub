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