function renderCarousel() {

    const STORYLIST = [
        {
            id: 0,
            name: "Breeder Setup in Office",
            image: "/img/story/40BreederSetupInOffice.jpg",
            description: "Setting up a tank with a plant carpet in office."
        },
        {
            id: 1,
            name: "Carpeted Tank Start",
            image: "/img/story/CarpetedTankStart.jpg",
            description: ""
        },
        {
            id: 2,
            name: "Diamond Hole Saw",
            image: "/img/story/DiamondHoleSaw.jpg",
            description: ""
        },
        {
            id: 3,
            name: "First Two Rows with Lights",
            image: "/img/story/First2RowsWithLights.jpg",
            description: ""
        },
        {
            id: 4,
            name: "First 10g Aquascape with Plants",
            image: "/img/story/first10gaquascapewithplants.jpg",
            description: ""
        },
        {
            id: 5,
            name: "First 10g Aquascape with Plants",
            image: "/img/story/first10gaquascapewithplants2.jpg",
            description: ""
        },
        {
            id: 6,
            name: "First 29g Setup",
            image: "/img/story/first29gSetup.jpg",
            description: ""
        },
        {
            id: 7,
            name: "First Guppy Fry",
            image: "/img/story/firstGuppyFry.jpg",
            description: ""
        },
        {
            id: 8,
            name: "Fish Rack Pieces",
            image: "/img/story/FishRackPieces.jpg",
            description: ""
        },
        {
            id: 9,
            name: "Fish Stand Design",
            image: "/img/story/FishStandDesign.jpg",
            description: "Original design plans for basement aquarium"
        },
        {
            id: 10,
            name: "First 10g Aquascape Hard",
            image: "/img/story/fisrt10gaquascapehard.jpg",
            description: ""
        },
        {
            id: 11,
            name: "Grandpa Working on Fish Rack",
            image: "/img/story/GrandpaWorkingOnFishRack.jpg",
            description: "My grandfather at 90 years old, a lifelong engineer and woodworker, helping me build my tank racks."
        },
        {
            id: 12,
            name: "Holes Drilled Good Design",
            image: "/img/story/HolesDrilledGoodDesign.jpg",
            description: ""
        },
        {
            id: 13,
            name: "Pleco With Juvenile Cichlids",
            image: "/img/story/PlecoWithJuvenileCichlids.jpg",
            description: ""
        },
        {
            id: 14,
            name: "Side of Fish Rack",
            image: "/img/story/SideOfFishRack.jpg",
            description: ""
        },
    ]

    var orderedList = document.createElement("ol");
    orderedList.setAttribute("class", "carousel-indicators");
    var list = document.createElement("li");
    list.setAttribute("data-target", "#homeCarousel");
    list.setAttribute("data-slide-to", STORYLIST[0].id);
    list.setAttribute("class", "active");
    orderedList.appendChild(list);
    
    var carouselInner = document.createElement("div");
    carouselInner.setAttribute("class", "carousel-inner");
    var carouselItem = document.createElement("div");
    carouselItem.setAttribute("class", "carousel-item active");
    var image = document.createElement("img");
    image.setAttribute("class", "d-block w-100");
    image.setAttribute("src", STORYLIST[0].image);
    image.setAttribute("alt", STORYLIST[0].name);
    var carouselCaption = document.createElement("div");
    carouselCaption.setAttribute("class", "carousel-caption");
    var h3 = document.createElement("h3");
    h3.textContent = STORYLIST[0].name;
    var p = document.createElement("p");
    p.setAttribute("class","d-none d-sm-block");
    p.textContent = STORYLIST[0].description;

    carouselInner.appendChild(carouselItem);
    carouselItem.appendChild(image);
    carouselItem.appendChild(carouselCaption);
    carouselCaption.appendChild(h3);
    carouselCaption.appendChild(p);

    //We will create loop here
    for( var i=1; i < STORYLIST.length; i++) {
        console.log(i);
        var list = document.createElement("li");
        list.setAttribute("data-target", "#homeCarousel");
        list.setAttribute("data-slide-to", STORYLIST[i].id);
        orderedList.appendChild(list);
        
        var carouselItem = document.createElement("div");
        carouselItem.setAttribute("class", "carousel-item");
        var image = document.createElement("img");
        image.setAttribute("class", "d-block w-100");
        image.setAttribute("src", STORYLIST[i].image);
        image.setAttribute("alt", STORYLIST[i].name);
        var carouselCaption = document.createElement("div");
        carouselCaption.setAttribute("class", "carousel-caption");
        var h3 = document.createElement("h3");
        h3.textContent = STORYLIST[i].name;
        var p = document.createElement("p");
        p.setAttribute("class","d-none d-sm-block");
        p.textContent = STORYLIST[i].description;

        carouselInner.appendChild(carouselItem);
        carouselItem.appendChild(image);
        carouselItem.appendChild(carouselCaption);
        carouselCaption.appendChild(h3);
        carouselCaption.appendChild(p);
    }

    var element = document.getElementById("homeCarousel");
    element.appendChild(orderedList);
    element.appendChild(carouselInner);







}
