$('.plus-btn').on('click', function (e) {
  console.log("registered");
  e.preventDefault();
  var $this = $(this);
  var $input = $this.closest('div').find('input');
  var quantity = parseInt($input.val());
  var dollars = (parseInt($this.closest('div').find('h5')[0].textContent.replace(/[^0-9\.]/g, '')));
  var productName = $this.closest('div.menuitem').find('h4')[0].textContent;
  var subProduct = "needed?";
  quantity = isNaN(quantity) ? 0 : quantity;
  quantity++;

  var cartTotal = parseInt(sessionStorage.getItem("cartTotal"));
  if (isNaN(cartTotal)) {
    cartTotal = 0;
  }
  cartTotal = cartTotal + 1;

  var dollarTotal = parseInt(sessionStorage.getItem("dollarTotal"));
  if (isNaN(dollarTotal)) {
    dollarTotal = 0;
  }
  dollarTotal = dollarTotal + dollars;

  document.getElementById('lblCartCount').textContent = cartTotal + " fish";
  document.getElementById('lblDollarCount').textContent = '$' + (dollarTotal);
  $input.val(quantity);

  var entry = {
    "productName": productName,
    "subProduct": subProduct,
    "dollars": dollars,
    "quantity": quantity
  };

  sessionStorage.setItem("cartTotal", cartTotal);
  sessionStorage.setItem("dollarTotal", dollarTotal);
  sessionStorage.setItem(subProduct, JSON.stringify(entry));

});

$('.minus-btn').on('click', function (e) {
  e.preventDefault();
  var $this = $(this);
  var $input = $this.closest('div').find('input');
  var quantity = parseInt($input.val());
  var dollars = (parseInt($this.closest('div').find('h5')[0].textContent.replace(/[^0-9\.]/g, '')));
  var productName = $this.closest('div.menuitem').find('h4')[0].textContent;
  var subProduct = "needed?";

  quantity = isNaN(quantity) ? 0 : quantity;
  if (quantity > 0) {
    quantity--;
    var cartTotal = parseInt(sessionStorage.getItem("cartTotal"));
    if (isNaN(cartTotal)) {
      cartTotal = 0;
    }
    cartTotal = cartTotal - 1;
    var dollarTotal = parseInt(sessionStorage.getItem("dollarTotal"));
    if (isNaN(dollarTotal)) {
      dollarTotal = 0;
    }
    dollarTotal = dollarTotal - dollars;
    document.getElementById('lblCartCount').textContent = cartTotal + " fish";
    document.getElementById('lblDollarCount').textContent = '$' + (dollarTotal);
    $input.val(quantity);

    var entry = {
      "productName": productName,
      "subProduct": subProduct,
      "dollars": dollars,
      "quantity": quantity
    };
    sessionStorage.setItem("cartTotal", cartTotal);
    sessionStorage.setItem("dollarTotal", dollarTotal);
    sessionStorage.setItem(subProduct, JSON.stringify(entry));
  }

});

window.onbeforeunload = function () {
  sessionStorage.setItem("windowref", window.location.href);
}

window.onload = function () {
  if (window.location.href == sessionStorage.getItem("windowref")) {
    sessionStorage.clear();
  }
  readConfiguration("http://localhost:8888/FishApplication/default")
}

function readConfiguration(url){
  console.log(url)
  fetch(url)
  .then(console.log(response))
  .then(response => response.json())
  .then(json => console.log(json.glossary.title));
}

//Code below to make button pause/play - not working
//   $(".carousel").carousel( { interval: 2000 } );

//   $("#carouselButton").click(function(){
//       if ($("#carouselButton").children("i").hasClass("fa-pause")) {
//           $(".carousel").carousel("pause");
//           $("#carouselButton").children("i").removeClass("fa-pause");
//           $("#carouselButton").children("i").addClass("fa-play");
//       } else {
//           $(".carousel").carousel("cycle");
//           $("#carouselButton").children("i").removeClass("fa-play");
//           $("#carouselButton").children("i").addClass("fa-pause"); 
//       }
// });