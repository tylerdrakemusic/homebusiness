 $('.plus-btn').on('click', function(e) {
    e.preventDefault();
    var $this = $(this);
    var $input = $this.closest('div').find('input');
    var value = parseInt($input.val());

    value++;
    var cartTotal = parseInt(document.getElementById('lblCartCount').textContent);
    document.getElementById('lblCartCount').textContent = cartTotal+1;
    value = isNaN(value) ? 0 : value;
    $input.val(value);
    document.getElementById('total').innerHTML = value;
});

$('.minus-btn').on('click', function(e) {
    e.preventDefault();
    var $this = $(this);
    var $input = $this.closest('div').find('input');
    var value = parseInt($input.val());
 
    if(value!==0){
        value--;
        var cartTotal = parseInt(document.getElementById('lblCartCount').textContent);
        document.getElementById('lblCartCount').textContent = cartTotal-1;
    }
    value = isNaN(value) ? 0 : value;

  $input.val(value);

  document.getElementById('total').innerHTML = value;
});