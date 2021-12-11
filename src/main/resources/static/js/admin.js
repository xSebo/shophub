// noinspection DuplicatedCode
var isShopSelectOpen = {
    "profile":false,
    "shop-setup":true,
    "shop-rewards":true,
    "super-admin":false
}

function toggle_tab(tab){
    if(isShopSelectOpen[tab]){
        document.getElementById("side_shop_select").classList.remove("closed");
    }else{
        document.getElementById("side_shop_select").classList.add("closed");
    }

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

function loadFile(event){
    var preview = document.getElementById('icon_preview');
    document.getElementById("file-name").innerText = event.target.files[0].name;
    let tmpURL = URL.createObjectURL(event.target.files[0]);
    preview.src = tmpURL
    for(let item of document.getElementById('stampboardContainer').children){
        for(let child of item.children){
            if(child.nodeName == "IMG"){
                child.src = tmpURL;
            }
        }
    }
    preview.onload = function() {
        URL.revokeObjectURL(preview.src) // free memory
    }
}

function updateReward(e,index){
    let input = e.target;
    let group = Math.ceil(index/8);
    let child = index%8;

    if(e.data == input.value && input.value.length == 1){
        //Update appropriate number to icon
        let elem = document.getElementById("stampboardContainer").children[group-1].children[(child-1==-1) ? 7 : child-1];
        if(elem.nodeName != "IMG"){
            const newItem = document.createElement('i');
            newItem.classList.add("fa")
            newItem.classList.add("fa-gift")
            elem.replaceChild(newItem, elem.childNodes[0]);
        }
    }else{
        if(input.value == ""){
            //Remove icon and replace with index
            let elem = document.getElementById("stampboardContainer").children[group-1].children[(child-1==-1) ? 7 : child-1];
            if(elem.nodeName != "IMG"){
                elem.innerText = index.toString()
            }
        }
    }
}

function addRow(){
    let rows = document.getElementById("stamp_position_container");
    let index = rows.children.length + 1
    let row = `<div class="is-flex is-flex-direction-row is-align-items-center mb-2">
        <p class="mr-2" style="width: 30px;">${index} -</p>
        <span class="control stamp-position-input">
                <input type="text" class="input" placeholder="No reward" onInput="updateReward(event,${index});">
        </span>
    </div>`
    rows.innerHTML += row;

    let group = Math.ceil(index/8);
    let child = index%8;
    if(child == 1){
        let colour = document.getElementById("colour-input").value
        let elem = document.getElementById("stampboardContainer")
        let x = `<div class="stampBoard" id="${group}" style="display: none; background-color:${colour};"></div>`
        elem.innerHTML += x;
    }
    let elem = document.getElementById("stampboardContainer").children[group-1];
    elem.innerHTML += `<div class="stamp">${index}</div>`;
}

function removeRow(){
    let rows = document.getElementById("stamp_position_container");
    if(rows.children.length != 1){
        rows.lastElementChild.remove();

        let elem = document.getElementById("stampboardContainer")
        let lastBoard =elem.lastElementChild;
        lastBoard.lastElementChild.remove();
        if(lastBoard.children.length == 0){
            pageNav(-1)
            lastBoard.remove();
        }
    }
}


function saveStampboardChanges(shopId){
    document.getElementById("save-stamp-btn").classList.add("is-loading")
    document.getElementById("stampboard_change_text").style.display = "none";

    let colour = document.getElementById("colour-input").value;

    let icon = document.getElementById("icon-input").files;

    let stampBoardContainer = document.getElementById("stamp_position_container");
    let iter = 1;
    let rewards = new Map();
    for (let group of stampBoardContainer.children){
        let val =group.children[1].children[0].value;
        if(val != ""){
            rewards.set(iter ,val);
        }
        iter++;
    }
    rewards = JSON.stringify(Object.fromEntries(rewards));
    let filename = "";
    if(icon.length != 0) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", '/upload', true);
        var formData = new FormData();
        formData.append("file", icon[0]);
        xhr.onload = function() {
            console.log(xhr.responseText)
            if (xhr.readyState === 4 && xhr.status === 200) {
                filename = xhr.responseText;
                var xhttp = new XMLHttpRequest();
                let params= "colour=" + colour.toString() +
                    "&rewardMapping=" + encodeURI(rewards) +
                    "&stampboardSize=" + (iter-1).toString() +
                    "&shopId=" + shopId.toString()+
                    "&iconFilePath=" + filename.toString();
                xhttp.open("POST", '/stampboard/update', true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.onload = function() {
                    if (xhttp.readyState === 4 && xhttp.status === 200) {
                        document.getElementById("save-stamp-btn").classList.remove("is-loading")
                        document.getElementById("stampboard_change_text").style.display = "block";
                        var response = xhttp.responseText
                        if (response == "success"){
                        }else{
                        }
                    } else {
                        console.error(xhttp.statusText);
                    }
                };
                xhttp.send(params);
            } else {
            }
        };
        xhr.send(formData);
    }else{
        document.getElementById("save-stamp-btn").classList.add("is-loading")
        document.getElementById("stampboard_change_text").style.display = "none";
        var xhttp = new XMLHttpRequest();
        let params= "colour=" + colour.toString() +
            "&rewardMapping=" + encodeURI(rewards) +
            "&stampboardSize=" + (iter-1).toString() +
            "&shopId=" + shopId.toString()+
            "&iconFilePath=";
        xhttp.open("POST", '/stampboard/update', true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.onload = function() {
            if (xhttp.readyState === 4 && xhttp.status === 200) {
                document.getElementById("save-stamp-btn").classList.remove("is-loading")
                document.getElementById("stampboard_change_text").style.display = "block";
                var response = xhttp.responseText
                if (response == "success"){
                }else{
                }
            } else {
                console.error(xhttp.statusText);
            }
        };
        xhttp.send(params);
    }
}








