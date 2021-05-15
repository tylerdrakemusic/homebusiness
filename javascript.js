$('.plus-btn').on('click', function (e) {
  e.preventDefault();
  var $this = $(this);
  var $input = $this.closest('div').find('input');
  var value = parseInt($input.val());
  var dollars = (parseInt($this.closest('div').find('h4')[0].textContent.replace(/[^0-9\.]/g, '')));
  var product = $this.closest('div.menuitem').find('h4')[0].textContent;
  var subproduct = $this.closest('div').find('h4')[0].textContent;
  value = isNaN(value) ? 0 : value;
  value++;

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

  document.getElementById('lblCartCount').textContent = cartTotal;
  document.getElementById('lblDollarCount').textContent = '$' + (dollarTotal);
  $input.val(value);

  var entry = {
    "product": product,
    "subproduct": subproduct,
    "dollars": dollars,
    "value": value
  };

  sessionStorage.setItem("cartTotal", cartTotal);
  sessionStorage.setItem("dollarTotal", dollarTotal);
  sessionStorage.setItem(subproduct, JSON.stringify(entry));

});

$('.minus-btn').on('click', function (e) {
  e.preventDefault();
  var $this = $(this);
  var $input = $this.closest('div').find('input');
  var value = parseInt($input.val());
  var dollars = (parseInt($this.closest('div').find('h4')[0].textContent.replace(/[^0-9\.]/g, '')));
  var product = $this.closest('div.menuitem').find('h4')[0].textContent;
  var subproduct = $this.closest('div').find('h4')[0].textContent;

  value = isNaN(value) ? 0 : value;
  console.log(value);
  if (value > 0) {
    value--;
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
    document.getElementById('lblCartCount').textContent = cartTotal;
    document.getElementById('lblDollarCount').textContent = '$' + (dollarTotal);
    $input.val(value);

    var entry = {
      "product": product,
      "subproduct": subproduct,
      "dollars": dollars,
      "value": value
    };
    sessionStorage.setItem("cartTotal", cartTotal);
    sessionStorage.setItem("dollarTotal", dollarTotal);
    sessionStorage.setItem(subproduct, JSON.stringify(entry));
  }

});

window.onbeforeunload = function () {
  sessionStorage.setItem("windowref", window.location.href);
}

window.onload = function () {
  if (window.location.href == sessionStorage.getItem("windowref")) {
    sessionStorage.clear();
  }
}
