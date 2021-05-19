var buttonload;
var countryDropdown;
var buttonAddCountry;
var buttonUpdateCountry;
var buttonDeleteCountry;
var labelCountryName;
var fieldCountryNames; 
var fieldCountryCode;

$(document).ready(function() {
	buttonload = $("#countrybuttonload");
	countryDropdown = $("#dropDownCountries");
	buttonAddCountry = $("#buttonAddCountry");
	buttonUpdateCountry = $("#buttonUpdateCountry");
	buttonDeleteCountry = $("#buttonDeleteCountry");
	labelCountryName = $("#labelCountryName");
	fieldCountryNames = $("#fieldCountryName");
	fieldCountryCode = $("#fieldCountryCode");
	
	
	buttonload.click(function() {
		loadCountrys();
	})
	
	countryDropdown.on("change", function(){
		changeFormStateToSelectedCountry();
	})
	
	buttonAddCountry.click(function(){
		if(buttonAddCountry.val() == "Add"){
			addCountry();
		}else {
		changeFormStateToNew();
		}
	});
	
	buttonUpdateCountry.click(function(){
		updateCountry();
	})
})


function updateCountry(){
	url =contextpath+ "countries/save";
	countryName = fieldCountryNames.val()
	countryCode = fieldCountryCode.val();
	
	countryId = dropcountryDropdown.val().split("-")[0];
	
	jsonData = {countryId:countryId, countryName: countryName, countryCode: countryCode};
	
	$.ajax({
		type: 'POST',
		url: url,
		beforeSend: function(xhr){
			xhr.setRequestHeader(csrfHeaderName, csrfValue);
		},
		
		data: JSON.stringify(jsonData),
		contentType: 'application/json'
	}).done(function(countryId) {
		$("#dropDownCountries option:selected").val(countryId + "-" + countryCode); 
		$("#dropDownCountries option:selected").text(countryName); 

		showToastMessage("Country has been updated")
		changeFormStateToNew();
	}).fail(function() {
		showToastMessage("ERROR: Server encountered an error");

	});
}

function addCountry(){
	url =contextpath+ "countries/save";
	countryName = fieldCountryNames.val()
	countryCode = fieldCountryCode.val();
	
	
	jsonData = {countryName: countryName, countryCode: countryCode };
	
	$.ajax({
		type: 'POST',
		url: url,
		beforeSend: function(xhr){
			xhr.setRequestHeader(csrfHeaderName, csrfValue);
		},
		
		data: JSON.stringify(jsonData),
		contentType: 'application/json'
	}).done(function(countryId) {
		selectNewlyAddedCountry(countryId, countryCode, countryName)
		showToastMessage("Country added")
	})
}

function selectNewlyAddedCountry(countryId, countryCode, countryName){
		optionValue = countryId + "-" + countryCode;
		$("<option>").val(optionValue).text(countryName).appendTo(countryDropdown);
		
		$("#dropDownCountries option[value=' " + optionValue + "']").prop("selected", true);
		fieldCountryCode.val("");
		fieldCountryNames.val("").focus()

	}



function changeFormStateToNew(){
	buttonAddCountry.val("Add");
	labelCountryName.text("Country Name:")
	buttonUpdateCountry.prop("disabled", true)
	buttonDeleteCountry.prop("disabled", true)
	
	fieldCountryNames.val("").focus();
	fieldCountryCode.val("");
}

function changeFormStateToSelectedCountry(){
	buttonAddCountry.prop("value", "New");
	buttonUpdateCountry.prop("disabled", false)
	buttonDeleteCountry.prop("disabled", false)
	
	labelCountryName.text("selected Country: ");
	 
	selectedCountryName = $("#dropDownCountries option:selected").text(); 
	fieldCountryNames.val(selectedCountryName);

	countryCode = countryDropdown.val().split("-")[1]; 
	fieldCountryCode.val(countryCode);
}


function loadCountrys() {
	url = contextpath + "countries/list";
	$.get(url, function(responseJSON) {
		countryDropdown.empty();

		$.each(responseJSON, function(index, country) {
			optionValue = country.countryId + "-" + country.countryCode;
			$("<option>").val(optionValue).text(country.countryName).appendTo(countryDropdown);
		});

	}).done(function() {
		buttonload.val("Refresh Country List")
		showToastMessage("All countries have been refreshed");
	}).fail(function() {
		showToastMessage("ERROR: Server encountered error");

	});
}

function showToastMessage(message) {
	$("#toastMessage").text(message);
	$(".toast").toast('show')
}