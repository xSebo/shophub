var modalStage = 0

function setLoadingBar(){
    let progressAmount = (modalStage+1)*20

    if(progressAmount <= 100){

        document.getElementById("progressBar").children[0].style.width = progressAmount + "%"
    }
}

function progress(){
    if(modalStage<5){
        modalStage += 1
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
