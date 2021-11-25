const categoryID = 0;

function toggle_onclick(categoryID){
    const selected = document.getElementById(categoryID);
    const number = selected.value;
    if (number == "0"){
        selected.style.backgroundColor = 'green' ;
        selected.value = 1;
    } else {
        selected.style.backgroundColor = 'rgb(1,126,255)' ;
        selected.value = 0;
    }

}