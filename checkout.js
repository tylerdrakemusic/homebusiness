function printOrderSummary() {
    sessionStorage.removeItem("windowref");
  
    var para = document.createElement("p");
    para.className="productsummary";
  
    for (let i = 0; i < sessionStorage.length; i++) {
      var element = JSON.parse(sessionStorage.getItem(sessionStorage.key(i)));
      if(element.subproduct!=null){
      var node = document.createTextNode(element.product + ' ' + element.subproduct + '...... quantity=' + element.value + '\n');
      para.appendChild(node);
      para.appendChild(document.createElement('br'));
      
      }
    }
    para.appendChild(document.createElement('br'));
    para.appendChild(document.createElement('br'));
    var dollarTotal = sessionStorage.getItem('dollarTotal');
    if(dollarTotal!=null){
      var node = document.createTextNode('Order Total = $' + dollarTotal);
      para.appendChild(node);
    }
  
    var element = document.getElementById("div1");
    element.appendChild(para);
  }

  window.onload = function checkboxShipping() {
    var shipCheckBox = document.getElementById("sameaddresscheckbox");
    shipCheckBox.onclick = function(){
      checkboxShipping();
    };
    var shipdiv = document.getElementById("shipdiv");
  
    if (shipCheckBox.checked == false){
      shipdiv.style.display = "block";
    } else {
      shipdiv.style.display = "none";
    }
    checkboxPolicy();
  }

  function checkboxPolicy(){
    var policyCheckBox = document.getElementById("policycheckbox");
    var checkoutCheckBox = document.getElementById("checkoutcheckbox");
    policyCheckBox.onclick = function (){
      checkboxPolicy();
    }
    checkoutCheckBox.onclick = function(){
      checkboxPolicy();
    }
    if(policyCheckBox.checked==true && checkoutCheckBox.checked==true){
      document.getElementById("checkout").disabled = false;
    }
    else{
      document.getElementById("checkout").disabled = true;
    }
  }