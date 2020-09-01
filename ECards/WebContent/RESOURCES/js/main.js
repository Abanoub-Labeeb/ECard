/*
*
*/
$('document').ready(function() {
    
    /* Edit Card*/
    $('.editButton').on('click',function(event){
        event.preventDefault();
        
        var href = $(this).attr('href');
        $.get(href, function(card,status){
        	$('#idEdit').val(card.id);
        	$('#userNameEdit').val(card.userName);
        	$('#cardHolderNameEdit').val(card.cardHolderName);
        	$('#cardNumberEdit').val(card.cardNumber);
        	$('#cardExpiryDateEdit').val(card.cardExpiryDate);
        });
        
        $('#editCardModal').modal();
    });
    
    /* Delete Card*/
    $('.deleteButton').on('click',function(event){
        event.preventDefault();
        
        var href = $(this).attr('href');
        $.get(href, function(card,status){
        	$('#idDelete').val(card.id);
        	$('#cardHolderNameDelete').val(card.cardHolderName);
        	$('#cardNumberDelete').val(card.cardNumber);
        	$('#cardExpiryDateDelete').val(card.cardExpiryDate);
        });
        
        $('#deleteCardModal').modal();
    });
    
    /* Add Save Card*/
    $('.createSaveButton').on('click',function(event){
        event.preventDefault();
    
        
        /*Validate Before Submit*/
        var isValid = true;
        var validMonths = ["01", "02", "03", "04" , "05", "06", "07", "08", "09", "10", "11", "12"];
        
        var cardHolderName = $( "#cardHolderName" ).val();
	    var cardHolderNameLength = cardHolderName.length;
	    
	    var cardNumber = $( "#cardNumber" ).val();
	    var cardNumberLength = cardNumber.length;
	     
	    var expiryDate = $( "#cardExpiryDate" ).val();
	    var Month = expiryDate.substring(0, 2);
	    var Year  = expiryDate.substring(3, 5);
        
        if(cardHolderNameLength < 3)
        {
          $('#cardHolderNameError').remove();
          $('#cardHolderName').after('<span id="cardHolderNameError" class="error" style="color:red;">Holder name must be longer than 3 characters</span>');
          isValid = false;
        }else{
          $('#cardHolderNameError').remove();
        }
        
        if(cardNumberLength != 14)
        {
          $('#cardNumberError').remove();
          $('#cardNumber').after('<span id="cardNumberError" class="error" style="color:red;">Card Number must be 14 digits</span>');
          isValid = false;
        }else{
          $('#cardNumberError').remove();
        }
        
        if(!(validMonths.includes(Month)) || !(parseInt(Year) > 19) || !(Year.length == 2))
        {
          $('#cardExpiryDateError').remove();
          $('#cardExpiryDate').after('<span id="cardExpiryDateError" class="error" style="color:red;">Expiry Date must be on MM/YY format e.g. 03/22</span>');
          isValid = false;
        }else{
          $('#cardExpiryDateError').remove();
        }

        /*Submit*/
        if(isValid)
          $( "#createCardForm" ).submit();
        
        /*In case we are going to call rest controller*/
        /*
        var card = 
        {
	        'userName'       : "admin",
	        'cardHolderName' : $('#cardHolderNameEdit').val(),
	        'cardNumber'     : $('#cardNumberEdit').val(),
	        'cardExpiryDate' : $('#cardExpiryDateEdit').val()
        };
        
		$.ajax({
		        type: 'POST',
		        url : '/ECards/api/card',
		        data: JSON.stringify(card),
		        async: true,
		        dataType: 'json',
		        contentType: 'application/json; charset=utf-8',
		        success: function(data, textStatus, xhr) {
		            alert('success');
		            window.location.href = "/ECards/";
		           //$('#createCardModal').modal("hide");
		        },
		        error: function(xhr, textStatus, errorThrown) {
		            alert('error : ' + errorThrown);
		            window.location.href = "/ECards/";
		        }
		    });
         */
    });
    
    /* Edit Save Card*/
    $('.editSaveButton').on('click',function(event){
        event.preventDefault();
        
        /*Validate Before Submit*/
        var isValid = true;
        var validMonths = ["01", "02", "03", "04" , "05", "06", "07", "08", "09", "10", "11", "12"];
        var expiryDate = $( "#cardExpiryDateEdit" ).val();
	    var Month = expiryDate.substring(0, 2);
	    var Year  = expiryDate.substring(3, 5);

        if(!(validMonths.includes(Month)) || !(parseInt(Year) > 19) || !(Year.length == 2))
        {
          $('#cardExpiryDateEditError').remove();
          $('#cardExpiryDateEdit').after('<span id="cardExpiryDateEditError" class="error" style="color:red;">Expiry Date must be on MM/YY format e.g. 03/22</span>');
          isValid = false;
        }else{
          $('#cardExpiryDateEditError').remove();
        }

        /*Submit*/
        if(isValid)
            $( "#editCardForm" ).submit();
        
        /*In case we are going to call rest controller*/
        /*
        var card = 
        {
	        'id'             : $('#idEdit').val(),
	        'userName'       : "admin",
	        'cardHolderName' : $('#cardHolderNameEdit').val(),
	        'cardNumber'     : $('#cardNumberEdit').val(),
	        'cardExpiryDate' : $('#cardExpiryDateEdit').val()
        };
        
		$.ajax({
		        type: 'PUT',
		        url : '/ECards/api/card',
		        data: JSON.stringify(card),
		        async: true,
		        dataType: 'json',
		        contentType: 'application/json; charset=utf-8',
		        success: function(data, textStatus, xhr) {
		            window.location.href = "/ECards/";
		        },
		        error: function(xhr, textStatus, errorThrown) {
		            alert('error : ' + errorThrown);
		            window.location.href = "/ECards/";
		        }
		    });
         */
    });
    
    /* Delete Save Card*/
    $('.deleteSaveButton').on('click',function(event){
        event.preventDefault();
        
        /*Validate Before Submit*/
        
        
        /*Submit*/
        $( "#deleteCardForm" ).submit();
        
        /*In case we are going to call rest controller*/
        /*
        
        var card = 
        {
	       	'id'             : $('#idDelete').val(),
	        'userName'       : "admin",
	        'cardHolderName' : $('#cardHolderNameDelete').val(),
	        'cardNumber'     : $('#cardNumberDelete').val(),
	        'cardExpiryDate' : $('#cardExpiryDateDelete').val()
        };
        
        $.ajax({
		        type: 'DELETE',
		        url : '/ECards/api/card',
		        data: JSON.stringify(card),
		        async: true,
		        dataType: 'json',
		        contentType: 'application/json; charset=utf-8',
		        success: function(data, textStatus, xhr) {
		            alert('success');
		            window.location.href = "/ECards/";
		           //$('#createCardModal').modal("hide");
		        },
		        error: function(xhr, textStatus, errorThrown) {
		            alert('error : ' + errorThrown);
		            window.location.href = "/ECards/";
		        }
		    });
        */ 
    });    
    
});