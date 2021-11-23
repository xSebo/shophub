var modalStage = 0
var urlInput, urlPrefixInput, nameInput, descInput = null;

function htmlDecode(input) {
    var doc = new DOMParser().parseFromString(input, "text/html");
    return doc.documentElement.textContent;
}

function setLoadingBar(){
    let progressAmount = (modalStage+1)*20

    if(progressAmount <= 100){

        document.getElementById("progressBar").children[0].style.width = progressAmount + "%"
        document.getElementById("modal-title").innerHTML = "Register a business " + (modalStage+1).toString() + "/5"
    }
}

function progress(){

    function refresh(){
        let newHeight = (document.getElementById("modal_page" + (modalStage+1).toString()).offsetHeight)+40
        document.getElementById("modal_container").style.minHeight = newHeight.toString()+"px"
    }
    urlInput = document.getElementById("business_register_url");
    urlPrefixInput = document.getElementById("business_register_url_prefix");
    nameInput = document.getElementById("business_register_name");
    descInput = document.getElementById("business_register_desc");

    if(document.getElementById("forwardButton").innerHTML == "Done"){
        document.getElementById("businessForm").submit();
    }

    if(modalStage==3) {
        document.getElementById("forwardButton").innerHTML = "Done"
    }

    switch (modalStage){
        case 0:
            let val = urlPrefixInput.value + urlInput.value
            if(val == "" ||
                !new RegExp("http(s)?:\\/\\/(www[.])?([a-zA-Z0-9$_.+!*'(),]|[-])*[.].+").test(val)){
                urlInput.classList.add("is-danger")
                document.getElementById("business_register_url_help").innerText = "Url is not valid"
                refresh()
                return
            }else{
                urlInput.classList.remove("is-danger")
                document.getElementById("business_register_url_help").innerText = ""
                refresh()
            }
            break;
        case 1:
            let valid = true;
            if(nameInput.value == ""){
                nameInput.classList.add("is-danger")
                document.getElementById("business_register_name_help").innerText = "Name cannot be empty"
                refresh()
                valid = false;
            }else{
                nameInput.classList.remove("is-danger")
                document.getElementById("business_register_name_help").innerText = ""
                refresh()
            }
            if(descInput.value == ""){
                descInput.classList.add("is-danger")
                document.getElementById("business_register_desc_help").innerText = "Description cannot be empty"
                refresh()
                valid = false;
            }else{
                descInput.classList.remove("is-danger")
                document.getElementById("business_register_desc_help").innerText = ""
                refresh()
            }
            if(!valid){return;}
            break;
    }


    if(modalStage<5){
        modalStage += 1

        if(modalStage == 1){
            nameInput.parentElement.classList.add("is-loading")
            descInput.parentElement.classList.add("is-loading")
            getBusinessInfo(urlPrefixInput.value + urlInput.value);
        }
        let currentStageId = "modal_page" + modalStage.toString()
        let nextStageId = "modal_page"+ (modalStage+1).toString()

        let newHeight = (document.getElementById(nextStageId).offsetHeight)+40
        document.getElementById("modal_container").style.minHeight = newHeight.toString()+"px"

        document.getElementById(currentStageId.toString()).style.transform = "translateX(-150%)"
        document.getElementById(nextStageId.toString()).style.transform = "translateX(0%)"
    }
    setLoadingBar()
}

function getBusinessInfo(url){
    fetch("http://localhost:8080/infoExtract?url=" + url)
        .then(response => response.json())
        .then(data => handleInfo(data));
}

function handleInfo(data){
    nameInput.parentElement.classList.remove("is-loading")
    descInput.parentElement.classList.remove("is-loading")

    let name = data.site_name;
    let url = data.url;
    let description = data.description;
    if(description !== undefined){
        description = htmlDecode(unescape(description));
    }

    if(name !== undefined){
        nameInput.value = name;
    }
    if(description !== undefined){
        descInput.value = description;
    }
}

var tags = []
function addTag(e){
    if(e.data=="," && tags.length != 20){
        let inputField = document.forms["businessForm"]["businessTagsInput"]
        let text = inputField.value.slice(0,-1);
        document.getElementById("bulmaTags").innerHTML += `
        <div>
            <div class="control mr-3 mb-2">
                <div class="tags has-addons">
                    <span class="tag gradient" style="color:white">${text}</span>
                    <a class="tag is-delete"></a>
                </div>
            </div>
        </div>
        `
        let newHeight = (document.getElementById("modal_page3").offsetHeight)+40
        document.getElementById("modal_container").style.minHeight = newHeight.toString()+"px"

        let bTag = document.getElementById("businessTags")
        if(bTag.value == "") {
            bTag.value += (text)
        }else{
             bTag.value += ("," + text);
        }

        tags.push(bTag.value);
        document.getElementById("tagCount").innerText = tags.length.toString() + "/20"
        document.getElementById("tagCount").style.display = "block";
        // document.getElementById("businessTags").value += text + ","
        inputField.value = ""
    }
}