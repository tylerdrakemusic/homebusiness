function renderAccordion() {

    const FISHLIST = [
        {
            id: 0,
            name: "Yellow-Tail Acei",
            video: "/videos/fish/AfricanCichlidAcei.MOV",
            price: "13",
            type: "Lake Malawi Cichlids"
        },
        {
            id: 1,
            name: "Blue Diamond Guppy (male)",
            video: "/videos/fish/LiveBearerBlueDiamondGuppyMale.MOV",
            price: "5",
            type: "Livebearers"
        },
        
    ]

    //filteredByCategory will create an array of one of each category
    const filteredByCategory = FISHLIST.reduce((acc, current) => {
        const x = acc.find(item => item.type === current.type);
        if (!x) {
          return acc.concat([current]);
        } else {
          return acc;
        }
      }, []);

      //this for loop will theoretically make an accordion component for each category

    for( var i=1; i < filteredByCategory.length; i++) {
        var card = document.createElement("div");
        card.setAttribute("class", "card");

        var tab = document.createElement("div");
        tab.setAttribute("role", "tab");
        tab.setAttribute("class", "card-header");

        var h3 = document.createElement("h3");

        var a = document.createElement("a");
        a.setAttribute("data-toggle", "collapse");
        a.setAttribute("class", "collapsed accordion-category");
        a.setAttribute("data-target", "#sectionOne");
        a.textContent = filteredByCategory[i].type;

        section1 = document.createElement("div");
        section1.setAttribute("class", "collapse");
        section1.setAttribute("id", "sectionOne");
        section1.setAttribute("data-parent", "#accordion");

        cardbody = document.createElement("div");
        cardbody.setAttribute("class","card-body");

        card.appendChild(tab);
        tab.appendChild(h3);
        tab.appendChild(sectionOne);
        h3.appendChild(a);
        secionOne.appendChild(cardbody);

    }
    
//this appends each category to the id of #accordion
    var element = document.getElementById("accordion");
    element.appendChild(card);


}
