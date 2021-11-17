var modalStage = 0
var urlInput, urlPrefixInput = null;

function setLoadingBar(){
    let progressAmount = (modalStage+1)*20

    if(progressAmount <= 100){

        document.getElementById("progressBar").children[0].style.width = progressAmount + "%"
        document.getElementById("modal-title").innerHTML = "Register a business " + (modalStage+1).toString() + "/5"
    }
}

function progress(){
    urlInput = document.getElementById("business_register_url");
    urlPrefixInput = document.getElementById("business_register_url_prefix");

    if(modalStage<5){
        modalStage += 1

        if(modalStage == 1){
            getBusinessInfo(urlPrefixInput.value + urlInput.value);
        }
        let currentStageId = "modal_page" + modalStage.toString()
        let nextStageId = "modal_page"+ (modalStage+1).toString()

        let newHeight = (document.getElementById(nextStageId).offsetHeight)+40
        document.getElementById("modal_container").style.height = newHeight.toString()+"px"

        console.log(currentStageId)
        console.log(document.getElementById(currentStageId))
        console.log(document.getElementById(currentStageId).style.transform)
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
    let name = data.site_name;
    let url = data.url;
    let description = data.description;
    if(description !== undefined){
        description = unescape(description);
    }
    console.log(description);
    console.log(url);
    console.log(name);
}