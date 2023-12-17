function getCsrf() {
    return document.querySelector("#_csrf_token").value;
}


function addToCart(productId) {
    // Effectue la requête AJAX à la servlet
    _post('/panier/add', { productId: productId}, data => {
        console.log(data)
        location.reload()
    })
}

function incrementCart(cartId){
    _post('/panier/increment', { cartId: cartId}, data => {
        console.log(data)
        location.reload()
    })
}

function decrementCart(cartId){
    _post('/panier/decrement', { cartId: cartId}, data => {
        console.log(data)
        location.reload()
    })
}

function deleteCart(cartId){
    _post('/panier/delete', { cartId: cartId}, data => {
        console.log(data)
        location.reload()
    })
}

function updateCart(cartId, quantity){
    _post('/panier/updateCart',  { cartId: cartId, quantity: quantity }, data => {
        console.log(data)
        location.reload()
    })
}

function _post(url, data, success) {
    $.ajax({
        url: url,
        type: 'POST',
        data: data,
        // beforeSend: function(xhr) {
        //     xhr.setRequestHeader('X-CSRF-TOKEN', getCsrf());
        // },
        success: success
    })
}



function handleCartResponse(data) {
    // Afficher un message (peut être supprimé si non nécessaire)
    alert(data.message);
    location.reload();
}
