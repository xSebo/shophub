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



