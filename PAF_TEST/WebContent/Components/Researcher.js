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
	 if (status != true) 
	  { 
	  $("#alertError").text(status); 
	  $("#alertError").show(); 
	  return; 
	  }
	 
	// If valid-------------------------
	 $("#formResearcher").submit();
});


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
 $("#hididSave").val($(this).closest("tr").find('#hididUpdate').val()); 
  
 $("#firstname").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#lastname").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#gender").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#email").val($(this).closest("tr").find('td:eq(3)').text());
 $("#password").val($(this).closest("tr").find('td:eq(4)').text());
});





//CLIENT-MODEL================================================================
function validateResearcherForm() 
{ 
// First Name
if ($("#firstname").val().trim() == "") 
 { 
 return "Insert First Name."; 
 } 
// Last Name
if ($("#lastname").val().trim() == "") 
 { 
 return "Insert Last Name."; 
 } 
// Gender-------------------------------
if ($("#gender").val().trim() == "") 
 { 
 return "Insert Gender."; 
 } 

//Email------------------------
if ($("#email").val().trim() == "") 
{ 
return "Insert Email."; 
} 
// Password------------------------
if ($("#password").val().trim() == "") 
{ 
return "Insert Password."; 
} 


return true; 
}













