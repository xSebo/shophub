var tags = [];
var query = "";
var page = 1;

var searchBar = document.getElementById("main-search");
var tagGroup = document.getElementById("search-tag-group");

function updateSearch(e){
    page = 1;
    query = searchBar.value;
    doSearch()
}

function removeTag(i){
    tags.splice(i, 1);
    page=1;
    updateUI()
    doSearch()
}

function addTag(e){
    if(e.key== "Enter"){
        if (searchBar.value != ""){
            page = 1;
            query = "";
            tags.push(searchBar.value.toLowerCase());
            searchBar.value = "";
            updateUI();
            doSearch();
        }
    }
}

function updateUI(){
    tagGroup.innerHTML = "";
    for(let [i,tag] of tags.entries()){
        tagGroup.innerHTML += `
            <div class="control mr-3">
                <div class="tags has-addons">
                    <span class="tag gradient">${tag}</span>
                    <a class="tag is-delete" onclick="removeTag(${i})"></a>
                </div>
            </div>`
    }
}

function doSearch(){
    let url = "/shop/search"
    url += "?q=" + query
    url += "&p=" + page.toString()
    for (let t of tags){
        url += "&t=" + t.toString()
    }
    fetch(url)
        .then(e=>e.json())
        .then(data=>console.log(data))
}