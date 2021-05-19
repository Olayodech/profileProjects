 function addNextDetailsSection(){
	allDivDetails = $("[id^='divProductDetails']")
	divDetailsCount = allDivDetails.length;
	
	html = `
		<div class="form-inline" id="divProductDetails${divDetailsCount}">
			<label class="m-3">Name:</label> <input type="text" class="form-control w-25"
				name="detailsName" maxlength="255" />
			 <label class="m-3">Value:</label> <input
				type="text" class="form-control w-25" name="detailsValue" maxlength="255" />

		</div>
	`;
	
	$("#divProductDetails").append(html);
	
	
	previousDivDetailSection = allDivDetails.last();
	previousDivDetailId = previousDivDetailSection.attr("id");
		
	htmlRemove = divextraproductimages
	`
		<a class="btn fas fa-times-circle fa-2x icon-dark" href="javascript:removeDetailSectionById('${previousDivDetailId}')" title="Remove this section">Remove</a>
	`
		
		
	previousDivDetailSection.append(htmlRemove);
	$("input[name='detailsName']").last().focus()
}

function removeDetailSectionById(id){
	$("#" + id).remove();
}