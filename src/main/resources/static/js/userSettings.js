function loadNewPicture(event) {
    var preview = document.getElementById('profilePicPreview');
    document.getElementById("newPicFileName").innerText = event.target.files[0].name;
    let tmpURL = URL.createObjectURL(event.target.files[0]);
    preview.src = tmpURL
}

function saveNameEmailProfileChanges() {
    // Get the fields
    let newFirstName = document.getElementById("newFirstName").value
    let newLastName = document.getElementById("newLastName").value
    let newEmail = document.getElementById("newEmail").value
    let newProfilePicture = document.getElementById("newProfilePicture").files

    // If the profile picture is not empty (being changed)
    // Will update all fields, including the profile picture
    if (newProfilePicture.length != 0) {
        // Create a new XMLHttpRequest object
        var xhr = new XMLHttpRequest();
        // Tell it to post to the "/upload" route
        xhr.open("POST", '/upload', true);
        // Add the new file to a FormData object
        var formData = new FormData();
        formData.append("file", newProfilePicture[0]);
        // When the function returns
        xhr.onload = function() {
            // If it was successful
            if (xhr.readyState === 4 && xhr.status === 200) {
                // Set the filename as the response text
                newProfileFilename = xhr.responseText;

                // Create a params object
                let params= "newFirstName=" + newFirstName.toString() +
                    "&newLastName=" + newLastName.toString() +
                    "&newEmail=" + newEmail.toString() +
                    "&newProfilePic=" + newProfileFilename.toString();

                // Create a new XHttpRest
                var xhttp = new XMLHttpRequest()

                // Tell the page which route to call
                xhttp.open("POST", '/nameEmailProfilePicture/update', true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                // Logic for different responses
                xhttp.onload = function() {
                    // If the status is ok (request succeeded
                    if (xhttp.readyState === 4 && xhttp.status === 200) {
                        // Get the response text
                        var response = xhttp.responseText
                        // If the text is "success"
                        if (response == "success"){
                            document.getElementById("profileDetailsChangeFailure").style.display = "none";
                            document.getElementById("profileDetailsChangeSuccess").style.display = "block";
                        }else{
                            document.getElementById("profileDetailsChangeSuccess").style.display = "none";
                            document.getElementById("profileDetailsChangeFailure").style.display = "block";                        }
                        // Otherwise
                    } else {
                        console.error(xhttp.statusText);
                    }
                };
                // Send the request
                xhttp.send(params);
            } else {
            }
        };
        // Send the request
        xhr.send(formData);

    // Else (profile picture not being changed)
    } else {
        // Create a new XMLHttpRequest object
        var xhttp = new XMLHttpRequest();

        // Create a params object
        let params= "newFirstName=" + newFirstName.toString() +
            "&newLastName=" + newLastName.toString() +
            "&newEmail=" + newEmail.toString();

        // Tell the page which route to call
        xhttp.open("POST", '/nameEmailProfilePicture/update', true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        // Logic for different responses
        xhttp.onload = function() {
            // If the status is ok (request succeeded
            if (xhttp.readyState === 4) {
                if (xhttp.status === 200) {
                    // Get the response text
                    var response = xhttp.responseText
                    // If the text is "success"
                    if (response == "success"){
                        document.getElementById("profileDetailsChangeFailure").style.display = "none";
                        document.getElementById("profileDetailsChangeSuccess").style.display = "block";
                    }
                } else {
                    document.getElementById("profileDetailsChangeSuccess").style.display = "none";
                    document.getElementById("profileDetailsChangeFailure").style.display = "block";
                }
                // Otherwise
            } else {
                console.error(xhttp.statusText);
            }
        };
        // Send the request
        xhttp.send(params);
    }
}

function savePasswordChanges() {
    // Get the fields
    let oldPassword = document.getElementById("oldPassword").value
    let newPassword = document.getElementById("newPassword").value
    let newPasswordConfirm = document.getElementById("newPasswordConfirm").value

    // If the new password's don't match, alert the user
    if (newPassword !== newPasswordConfirm) {
        alert("New Passwords do not match")
    }

    // Create a new XMLHttpRequest object
    var xhttp = new XMLHttpRequest();

    // Create a params object
    let params= "oldPassword=" + oldPassword.toString() +
        "&newPassword=" + newPassword.toString() +
        "&newPasswordConfirm=" + newPasswordConfirm.toString();

    // Tell the page which route to call
    xhttp.open("POST", '/password/update', true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    // Logic for different responses
    xhttp.onload = function() {
        // If the status is ok (request succeeded
        if (xhttp.readyState === 4) {
            if (xhttp.status === 200) {
                // Get the response text
                var response = xhttp.responseText
                if (response == "success"){
                    document.getElementById("passwordChangeFailure").style.display = "none";
                    document.getElementById("passwordChangeSuccess").style.display = "block";
                }
            } else {
                document.getElementById("passwordChangeSuccess").style.display = "none";
                document.getElementById("passwordChangeFailure").style.display = "block";
            }
        } else {
            console.error(xhttp.statusText);
        }
    };
    // Send the request
    xhttp.send(params);
}