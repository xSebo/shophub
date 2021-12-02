function toggle_tab(tab){
    Array.prototype.forEach.call(
        document.getElementsByClassName("admin-tab"),
        function (el) {
            el.classList.remove("active");
        }
    );

    Array.prototype.forEach.call(
        document.getElementsByClassName("admin-section"),
        function (el) {
            el.classList.remove("active");
        }
    );

    document.getElementById(tab+"-tab").classList.add("active");
    document.getElementById(tab+"-section").classList.add("active");
}

function toggle_sidebar() {
    document.getElementById("admin-sidebar").classList.toggle("active");
    document.getElementById("admin-sidebar-toggle").classList.toggle("active");
    document.getElementById("admin-main").classList.toggle("overlay");
}

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

function checkTab(){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const tab = urlParams.get('tab')
    if (tab != null){
        if(document.getElementById(tab+"-tab") != undefined){
            toggle_tab(tab);
        }
    }
}