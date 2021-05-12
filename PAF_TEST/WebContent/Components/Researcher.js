$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();

});

$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateResearcherForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid-------------------------
	var type = ($("#hididSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "ResearcherAPI",
		type : type,
		data : $("#formResearcher").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onResearcherSaveComplete(response.responseText, status);
		}
	});

});

function onResearcherSaveComplete(response, status) {
	if (status == "success") {

		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {

			$("#alertSuccess").text("Researcher details successfully saved.");
			$("#alertSuccess").show();

			$("#divResearcherGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#hididSave").val("");
	$("#formResearcher")[0].reset();
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {

	$("#hididSave").val($(this).data("id"));

	$("#firstname").val($(this).closest("tr").find('td:eq(0)').text());
	$("#lastname").val($(this).closest("tr").find('td:eq(1)').text());
	$("#gender").val($(this).closest("tr").find('td:eq(2)').text());
	$("#email").val($(this).closest("tr").find('td:eq(3)').text());
	$("#password").val($(this).closest("tr").find('td:eq(4)').text());
});

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "ResearcherAPI",
		type : "DELETE",
		data : "id=" + $(this).data("id"),
		dataType : "text",
		complete : function(response, status) {
			onResearcherDeleteComplete(response.responseText, status);
		}
	});
});

function onResearcherDeleteComplete(response, status)
{ 
	if (status == "success") 
 { 
			var resultSet = JSON.parse(response); 
			if (resultSet.status.trim() == "success") 
			{ 
				$("#alertSuccess").text("Researcher details successfully deleted."); 
				$("#alertSuccess").show(); 
				
				$("#divResearcherGrid").html(resultSet.data); 
			} else if (resultSet.status.trim() == "error") 
			{ 
				$("#alertError").text(resultSet.data); 
				$("#alertError").show(); 
			} 
 	} else if (status == "error") 
 	{ 
 		$("#alertError").text("Error while deleting."); 
 		$("#alertError").show(); 
 	} else
	{ 
 		$("#alertError").text("Unknown error while deleting.."); 
 		$("#alertError").show(); 
	} 
}



// CLIENT-MODEL================================================================
function validateResearcherForm() {
	// First Name
	if ($("#firstname").val().trim() == "") {
		return "Insert First Name.";
	}
	// Last Name
	if ($("#lastname").val().trim() == "") {
		return "Insert Last Name.";
	}
	// Gender-------------------------------
	if ($("#gender").val().trim() == "") {
		return "Insert Gender.";
	}

	// Email------------------------
	if ($("#email").val().trim() == "") {
		return "Insert Email.";
	}
	// Password------------------------
	if ($("#password").val().trim() == "") {
		return "Insert Password.";
	}

	return true;
}
