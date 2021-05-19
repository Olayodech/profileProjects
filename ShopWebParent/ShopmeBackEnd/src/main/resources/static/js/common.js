$(document).ready(function() {
	console.log("inside log out js")
	$("#logoutLink").on("click", function(e) {
		e.preventDefault();
		document.logoutForm.submit();
		
	});
			customizeDropDown();
			validateUpdateAccount();
});

function customizeDropDown(){
        $(".navbar .dropdown").hover(function(){
            $(this).find('.dropdown-menu').first().stop(true, true).delay(250).slideDown();
        },
        function(){
            $(this).find('.dropdown-menu').first().stop(true, true).delay(100).slideUp();
        }

        )
		$(".dropdown > a").click(function(){
			location.href = this.href;
		});
}

function validateUpdateAccount(confirmPassword){
        if(confirmPassword.val() != $("#password").val()){
            confirmPassword.setCustomValidity("Passwords do not match");
        }else{
            confirmPassword.setCustomValidity("");
        }
}


function checkunique(form){
	console.log('inside checkunique');
	catId = $("#categoryId").val();
	name = $("#categoryName").val();
	alias = $("#categoriesAlias").val();
	csrfValue = $("input[name='_csrf']").val();
	
	console.log(catId + name + alias + csrfValue)
	
	url = url;
	params = {id:catId, name:name, alias: alias, _csrf:csrfValue};
	console.log('params are' + params.id + params.name + params._csrf)
	
	$.post(url, params, function(response){
		console.log('response is' + response)
			alert(response);
	}).fail(function(){
		alert('failed');
	
	});
	
		return false;
	}