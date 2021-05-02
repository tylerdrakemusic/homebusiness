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