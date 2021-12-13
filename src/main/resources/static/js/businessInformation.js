async function submitFile(inputName) {
    return new Promise(function (resolve, reject) {
        let icon = document.getElementById(inputName).files;
        if (icon.length != 0) {
            var xhttp = new XMLHttpRequest();
            xhttp.open("POST", '/upload', true);
            var formData = new FormData();
            formData.append("file", icon[0]);
            xhttp.onload = function () {
                if (xhttp.readyState === 4 && xhttp.status === 200) {
                    resolve(xhttp.responseText)
                } else {
                    console.error(xhttp.statusText);
                    reject("")
                }
            };
            xhttp.send(formData);
        }else{
            reject("")
        }
    })
}

async function submitInfo(shopId) {

    document.getElementById("saveInfoButton").classList.add("is-loading")

    let bannerName, logoName
    try{
        bannerName = await submitFile("bannerInput");
    }catch(e){
        console.log(e)
        bannerName = ""
    }
    try{
        logoName = await submitFile("logoInput");
    }catch(e){
        console.log(e)
        logoName = ""
    }
    var xhttp = new XMLHttpRequest();



    let params = "shopId=" + shopId
    params += "&shopName=" + encodeURIComponent(document.getElementById("nameInput").value)
    params += "&shopDescription=" + encodeURIComponent(document.getElementById("descriptionInput").value)
    params += "&bannerName=" + bannerName + "&logoName=" + logoName

    xhttp.open("POST", '/updateKeyInformation', true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.onload = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            var response = xhttp.responseText
            if (response == "OK") {
                document.getElementById("saveInfoButton").classList.remove("is-loading")
                document.getElementById("infoSuccess").innerHTML = "Changes Saved!"
            } else {
            }
        } else {
            console.error(xhttp.statusText);
        }
    };
    xhttp.send(params);
}

function loadImagePreview(event, previewId, fileId) {
    var preview = document.getElementById(previewId);
    document.getElementById(fileId).innerText = event.target.files[0].name;
    let tmpURL = URL.createObjectURL(event.target.files[0]);
    preview.src = tmpURL

    preview.onload = function () {
        URL.revokeObjectURL(preview.src) // free memory
    }
}