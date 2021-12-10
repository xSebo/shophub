var tags = [];
var query = "";
var page = 1;

var searchBar = document.getElementById("main-search");
var tagGroup = document.getElementById("search-tag-group");

function updateSearch(e){
    page = 1;
    query = searchBar.value;
    doSearch(false)
}

function removeTag(i){
    tags.splice(i, 1);
    page=1;
    updateUI()
    doSearch(false)
}

function addTag(e){
    if(e.key== "Enter"){
        if (searchBar.value != ""){
            page = 1;
            query = "";
            tags.push(searchBar.value.toLowerCase());
            searchBar.value = "";
            updateUI();
            doSearch(false);
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

function doSearch(fromNextPageBtn){
    let url = "/shop/search"
    url += "?q=" + query
    url += "&p=" + page.toString()
    for (let t of tags){
        url += "&t=" + t.toString()
    }
    fetch(url)
        .then(e=>e.json())
        .then(data=>{
            if(!fromNextPageBtn){
                document.getElementById("business_card_container").innerHTML = "";
            }
            if(data["shops"].length == 0){
                document.getElementById("searchText").style.display="block";
            }else{
                document.getElementById("searchText").style.display="none";
            }
            for(let shop of data["shops"]){
                addShop(shop);
            }
            if(data["hasNextPage"] == true){
                document.getElementById("loadMoreBtn").style.display = "flex";
            }else{
                document.getElementById("loadMoreBtn").setAttribute('style', 'display:none!important');
            }
        });
}

function addShop(shopInfo){
    let img_path = shopInfo["banner"]
    let title = shopInfo["name"]
    let reward_text = shopInfo["next_reward_name"] + " at " + shopInfo["next_reward_pos"] + " stamps"
    let reward_amount = shopInfo["reward_count"]
    let shopId = shopInfo["id"]

    document.getElementById("business_card_container").innerHTML +=`
<div class="business_container box" style="position:relative;">
    <div class="image" style="background-image:url(${img_path});"></div>
    <div class="favouriteStar starContainer" onclick="favouriteBusiness(this,${shopId});">
        <span class="icon favouriteStar">
            <i class="far fa-star"></i>
        </span>
        <span class="icon favouriteStar">
            <i class="fas fa-star"></i>
        </span>
    </div>
    <div class="content">
        <h1 class="title is-4 mb-1">${title}</h1>
        <p class="mb-1">${reward_text}</p>
        <div class="is-full-width" style="display:flex;justify-content: space-between;align-items: center">
            <div class="level-left">
                <span class="icon is-small is-left ml-1 mr-1">
                    <i class="fas fa-gift"></i>
                </span>
                <p>${reward_amount}</p>
            </div>
            <div class="level-right">
                <button class="button is-rounded gradient" onclick="redirect(${shopId})">
                    View Shop
                    <span class="icon is-small is-left ml-1">
                        <i class="fas fa-arrow-right"></i>
                    </span>
                </button>
            </div>
        </div>
    </div>
</div>`
}

function loadNextPage(){
    page++;
    doSearch(true);
}