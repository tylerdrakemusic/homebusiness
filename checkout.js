function printOrderSummary() {
  sessionStorage.removeItem("windowref");

  var para = document.createElement("p");
  para.className = "productsummary";

  for (let i = 0; i < sessionStorage.length; i++) {
    var element = JSON.parse(sessionStorage.getItem(sessionStorage.key(i)));
    if (element.subProduct != null) {
      //console.log(element);
      var node = document.createTextNode(element.productName + ' ' + element.subProduct + '...... quantity=' + element.quantity + '\n');
      para.appendChild(node);
      para.appendChild(document.createElement('br'));

    }
  }
  para.appendChild(document.createElement('br'));
  para.appendChild(document.createElement('br'));
  var dollarTotal = sessionStorage.getItem('dollarTotal');
  if (dollarTotal != null) {
    var node = document.createTextNode('Order Total = $' + dollarTotal);
    para.appendChild(node);
  }

  var element = document.getElementById("div1");
  element.appendChild(para);
}

window.onload = function checkboxShipping() {
  var shipCheckBox = document.getElementById("sameaddresscheckbox");
  shipCheckBox.onclick = function () {
    checkboxShipping();
  };
  var shipdiv = document.getElementById("shippingdiv");

  if (shipCheckBox.checked == false) {
    shipdiv.style.display = "block";
  } else {
    shipdiv.style.display = "none";
  }
  checkboxPolicy();

  const form = document.querySelector('form');
  form.addEventListener('submit', handleSubmit);
}

function checkboxPolicy() {
  var policyCheckbox = document.getElementById("policyCheckbox");
  var checkoutCheckbox = document.getElementById("checkoutCheckbox");
  policyCheckbox.onclick = function () {
    checkboxPolicy();
  }
  checkoutCheckbox.onclick = function () {
    checkboxPolicy();
  }
  if (policyCheckbox.checked == true && checkoutCheckbox.checked == true) {
    document.getElementById("checkout").disabled = false;
  }
  else {
    document.getElementById("checkout").disabled = true;
  }
}

function handleSubmit(event) {
  event.preventDefault();
  const data = new FormData(event.target);
  const requestBody = Object.fromEntries(data.entries());
  var products = {
    products: []
  };
  for (let i = 0; i < sessionStorage.length; i++) {
    var element = JSON.parse(sessionStorage.getItem(sessionStorage.key(i)));
    if (element.hasOwnProperty('productName')) {

      products.products.push(element);
    }
  }

  requestBody.products = products.products;
  //console.log(requestBody.products);
  userAction(requestBody);
}

const userAction = async (requestBody) => {
  //console.log( requestBody.valueOf() );
  const response = await fetch('https://vt-fish-service-dev.herokuapp.com/order', {
    method: 'POST',
    body: JSON.stringify(requestBody),
    headers: {
      'Content-Type': 'application/json',
      'CorrelationId': createUUID()
    }
  });
  const responseText = await response.text();

  if (response.status === 201) {
    var parent = document.getElementById("helpinput");
    while (parent.firstChild) {
      parent.removeChild(parent.firstChild);
    }
    document.getElementById("feedback").appendChild(document.createTextNode("Thank you for your request!  Website is still under construction, your credit card will not be processed."));
  }
  else if (response.status === 400) {
    var parent = document.getElementById("helpinput");
    while (parent.firstChild) {
      parent.removeChild(parent.firstChild);
    }
    var responseJSONObject = JSON.parse(responseText);
    parent.appendChild(document.createTextNode(responseJSONObject.message));
    //console.log(responseText);
  }
  else if (response.status !== 201) {
    var parent = document.getElementById("helpinput");
    while (parent.firstChild) {
      parent.removeChild(parent.firstChild);
    }
    var responseJSONObject = JSON.parse(responseText);
    parent.appendChild(document.createTextNode(responseJSONObject.message));
    //console.log(responseText);
  }

}

function createUUID() {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
    var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
    return v.toString(16);
  });
}
