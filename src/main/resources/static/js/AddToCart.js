function addToCart(action, cartId, quantity) {
    // Effectue la requête AJAX à la servlet
    $.post("/panier/" + action, { cartId: cartId, productId: cartId, quantity: quantity, action: action }, function (data) {
        console.log(data);
        if (action != "add"){
            location.reload();
        }else{
            handleCartResponse(data);
        }
    }, "json");
}

function increment(cartId){
    $.post("/panier/increment", { cartId: cartId}, function (data) {
        console.log(data);
        // location.reload();
    }, "json");
}

function handleCartResponse(data) {
    // Afficher un message (peut être supprimé si non nécessaire)
    alert(data.message);
    location.reload();
}

function deleteItem(action, cartId){
    $.post("/panier/" + action, { cartId: cartId, productId: cartId, action: action, quantity: 0 }, function (data) {
        if (action != "add"){
            location.reload();
        }else{
            handleCartResponse(data);
        }
    }, "json");
}

function updateQuantity(cartId, quantity){
    $.post("/panier/updateQuantity", { cartId: cartId, productId: cartId, action: 'updateQuantity', quantity: quantity }, function (data) {
            location.reload();
    }, "json");
}