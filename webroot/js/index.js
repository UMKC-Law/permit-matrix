/*$(function(){

	$("input:radio").change(function(){
		alert("change");
		if($("#septic_removal").val() === "yes"){
			//$("#other_info").hide();
			//$("#traffic_info").show();

			var html = "<form id='traffic_info' action='' method='POST' role='form'>"
                                    +"<legend>PROJECT INFORMATION- Traffic Control</legend>"
                                   + "<div>"
                                     +   "<span>Which lanes of which street will need to be closed?</span>"
                                       + "<div class='radio'>"
                                      +      "<label>"
                                              +  "<input type='radio' name='street_closing' id='input' value='yes'>Yes"
                                            +"</label>"
                                            "<label>"
                                               + "<input type='radio' name='street_closing/*' id='input' value='no'>No"
                                            +"</label>"
                                        +"</div>"
                                    +"</div>"
                                   +"<div>"
                                        +"<span>Do you have insurance on file with Public Works or Parks and Recreation?</span>"
                                        
                                      	+"<div class='radio'>"
                                            +"<label>"
                                                +"<input type='radio' name='insurance' id='input' value='yes'>Yes"
                                            +"</label>"
                                            +"<label>"
                                                +"<input type='radio' name='insurance' id='input' value='no'>No"
                                            +"</label>"
                                        +"</div>"
                                    +"</div>"
                                    +"<div>"
                                      +  "<span>Will any portion of excavation will interfere with street pavement or sidewalk?</span>"
                                       + "<div class='radio'>"
                                        +    "<label>"
                                         +       "<input type='radio' name='septic_removal' id='input' value='yes'>Yes"
                                          +  "</label>"
                                           + "<label>"
                                            +    "<input type='radio' name='septic_removal' id='input' value='no'>No"
                                           + "</label>"
                                        +"</div>"
                                    +"</div>"
                                    
                                    +"<!-- <button type='submit' class='btn btn-primary'>Submit</button> -->"
                                +"</form>";


                $("#other_info").html(html);
		}
	});
});*/
/*
function show_previous(){
	if($("#other_info").text() === "Farts"){
		var html = "<form action='' method='POST' role='form'><legend>PROJECT INFORMATION- Other Questions</legend>"
                                       + "<div>"
                                        +    "<span>Does your project involve removing septic tanks and private infiltration fields?</span>"
                                         +   "<div class='radio'>"
                                          +      "<label>"
                                           +         "<input type='radio' name='septic_removal' id='septic_removal' value='yes'>Yes"
                                            +    "</label>"
                                             +   "<label>"
                                              +      "<input type='radio' name='septic_removal' id='septic_removal' value='no'>No"
                                               + "</label>"
                                            +"</div>"
                                        +"</div>"
                                         + "<div>"
                                            + "<span>Do you have insurance on file with Public Works or Parks and Recreation?</span>"
                                            +"<div class='radio'>"
                                             +   "<label>"
                                              +      "<input type='radio' name='insurance' id='input' value='yes'>Yes"
                                               + "</label>"
                                                +"<label>"
                                                 +   "<input type='radio' name='insurance' id='input' value='no'>No"
                                                +"</label>"
                                            +"</div>"
                                        +"</div>"
                                        +"<div>"
                                         +   "<span>Will any portion of excavation will interfere with street pavement or sidewalk?</span>"
                                          +  "<div class='radio'>"
                                           +     "<label>"
                                            +        "<input type='radio' name='septic_removal' id='input' value='yes'>Yes"
                                             +   "</label>"
                                              +  "<label>"
                                               +     "<input type='radio' name='septic_removal' id='input' value='no'>No"
                                                +"</label>"
                                            +"</div>"
                                        +"</div>"
                                        
                                        +"<!-- <button type='submit' class='btn btn-primary'>Submit</button> -->"
                                    +"</form>";
          $("#other_info").html(html);
          $("#property_details").removeClass("active");
          $("#other_info").addClass("active");

	}
}*/


$(function(){
/*
	$("input[type='radio'][name='street_excavation]").change(function(){
		if($("#street_excavation").val() === "yes"){
      alert("yes");
			$("#other_info").hide();
			$("#traffic_info").show();

		}
    else{
      alert("no");
    }
	});*/

  $('input[name="street_excavation"]').change(function(e){
    if($(this).val() === "yes"){
      $("#other_info").hide();
      $("#traffic_info").show();
    }

    $('#back').click(function(){
      $("#traffic_info").hide();
      $("#other_info").show();
    });
  });


  $("#form_submit").click(function(){
    get_summary();
    
  });

});

function get_summary() {
  var html = "";
    var html = "<legend >SUMMARY</legend><div id='summary_table'><table class='table'><thead><tr><th>Input Field</th><th>Value</th><th>Permits</th></tr></thead><tbody>";

    var formData = new FormData();

    var fname = $("#fname").val();
    var lname = $("#lname").val();
    var job_title = $("#job_title").val();
    var personal_email = $("#personal_email").val();
    var mobile_phone = $("#mobile_phone").val();
    var contractor_num = $("#contractor_num").val();
    var occupational_num = $("#occupational_num").val();

    html += "<tr> <td>" + "First Name" + "</td><td> " + fname + " </td><td>" + " Permit 1, Permit 2, Permit 3 " + "</td> </tr>";
    html += "<tr> <td>" + "Last Name" + "</td><td>" + lname + "</td><td>" + "Permit 1, Permit 2, Permit 3" + "</td> </tr>";
    html += "<tr> <td>" + "Job Title" + "</td><td>" + job_title + "</td><td>" + "Permit 1" + "</td> </tr>";
    html += "<tr> <td>" + "Personal Email" + "</td><td>" + personal_email + "</td><td>" + "Permit 2" + "</td> </tr>";
    html += "<tr> <td>" + "Mobile Phone" + "</td><td>" + mobile_phone + "</td><td>" + "Permit 1, Permit 3" + "</td> </tr>";
    html += "<tr> <td>" + "Contractor #" + "</td><td>" + contractor_num + "</td><td>" + "Permit 1" + "</td> </tr>";
    html += "<tr> <td>" + "Occupational #" + "</td><td>" + occupational_num + "</td><td>" + " Permit 1" + "</td> </tr>";

    formData.append("fname", fname);
    formData.append("lname", lname);
    formData.append("job_title", job_title);
    formData.append("personal_email", personal_email);
    formData.append("mobile_phone", mobile_phone);
    formData.append("contractor_num", contractor_num);
    formData.append("occupational_num", occupational_num);

    var business_name = $("#business_name").val();
    var business_phone = $("#business_phone").val();
    var business_address = $("#business_address").val();
    var business_city = $("#business_city").val();
    var business_state = $("#business_state").val();
    var business_zip = $("#business_zip").val();

    html += "<tr> <td>" + " Business Name " + "</td><td>" + business_name + "</td><td>" + "Permit 2, Permit 3" + "</td> </tr>";
    html += "<tr> <td>" + " Business Phone " + "</td><td>" + business_phone + "</td><td>" + "Permit 2, Permit 3" + "</td> </tr>";
    html += "<tr> <td>" + " Business Street Address " + "</td><td>" + business_address + "</td><td>" + "Permit 2, Permit 3" + "</td> </tr>";
    html += "<tr> <td>" + " Business City " + "</td><td>" + business_city + "</td><td>" + "Permit 2, Permit 3" + "</td> </tr>";
    html += "<tr> <td>" + " Business State " + "</td><td>" + business_state + "</td><td>" + "Permit 2, Permit 3" + "</td> </tr>";
    html += "<tr> <td>" + " Business Zip Code " + "</td><td>" + business_zip + "</td><td>" + "Permit 2, Permit 3" + "</td> </tr>";

    formData.append("business_name", business_name);
    formData.append("business_phone", business_phone);
    formData.append("business_address", business_address);
    formData.append("business_city", business_city);
    formData.append("business_state", business_state);
    formData.append("business_zip", business_zip);

    var project_name = $("#project_name").val();
    var project_address = $("#project_address").val();
    var project_description = $("#project_description").val(); 

    html += "<tr> <td>" + " Project Name " + "</td><td>" + project_name + "</td><td>" + "Permit 1" + "</td> </tr>";
    html += "<tr> <td>" + " Project Address" + "</td><td>" + project_address + "</td><td>" + "Permit 1, Permit 2" + "</td> </tr>";
    html += "<tr> <td>" + " Project Description " + "</td><td>" + project_description + "</td><td>" + "Permit 1, Permit 2" + "</td> </tr>";

    formData.append("project_name", project_name);
    formData.append("project_address", project_address);
    formData.append("project_description", project_description);

    var zoning_district = $("#zoning_district").val();
    var occ_group = $("#occ_group").val();
    var const_type = $("#const_type").val();
    var struct_class = $("#struct_class").val();
    var site_plan = $("#site_plan").val();
    var control_num = $("#control_num").val();
    var stories = $("#stories").val();
    var building_area = $("#building_area").val();
    var dwelling_units = $("#dwelling_units").val();

    html += "<tr> <td>" + " Project Zoning District " + "</td><td>" + zoning_district + "</td><td>" + "Permit 1" + "</td> </tr>";
    html += "<tr> <td>" + " Project OCC Group " + "</td><td>" + occ_group + "</td><td>" + "Permit 1" + "</td> </tr>";
    /*html += "<tr> <td>" + " Project Construction Type " + "</td><td>" + const_type + "</td><td>" + + "</td> </tr>";
    html += "<tr> <td>" + " Project Structural Class " + "</td><td>" + struct_class + "</td><td>" + + "</td> </tr>";
    html += "<tr> <td>" + " Project Site Plan " + "</td><td>" + site_plan + "</td><td>" + + "</td> </tr>";
    html += "<tr> <td>" + " Project Control Number " + "</td><td>" + control_num + "</td><td>" + + "</td> </tr>";
    html += "<tr> <td>" + " Project Stories " + "</td><td>" + stories + "</td><td>" + + "</td> </tr>";
    html += "<tr> <td>" + " Project Building Area " + "</td><td>" + building_area + "</td><td>" + + "</td> </tr>";
    html += "<tr> <td>" + " Project Dwelling Units " + "</td><td>" + dwelling_units + "</td><td>" + + "</td> </tr>";

    formData.append("zoning_district", zoning_district);
    formData.append("occ_group", occ_group);
    formData.append("const_type", const_type);
    formData.append("struct_class", struct_class);
    formData.append("site_plan", site_plan);
    formData.append("control_num", control_num);
    formData.append("stories", stories);
    formData.append("building_area", building_area);
    formData.append("dwelling_units", dwelling_units);

    // left off for adding only value of radio button that is checked
    var septic_removal_val = "";
    var septic_removal = $("input[type='radio'][name='septic_removal]:checked");
    if (septic_removal.length > 0) {
        septic_removal_val = septic_removal.val();
    }

    var insurance_val = "";
    var insurance = $("input[type='radio'][name='insurance]:checked");
    if (insurance.length > 0) {
        insurance_val = insurance.val();
    }

    var street_excavation_val = "";
    var street_excavation = $("input[type='radio'][name='street_excavation]:checked");
    if (street_excavation.length > 0) {
        street_excavation_val = street_excavation.val();
    }

    html += "<tr> <td>" + " Septic Removal (Yes/No) " + "</td><td>" + septic_removal_val + "</td><td>" + + "</td> </tr>";
    html += "<tr> <td>" + " Insurance (Yes/No) " + "</td><td>" + insurance_val + "</td><td>" + + "</td> </tr>";
    html += "<tr> <td>" + " Street Excavation (Yes/No) " + "</td><td>" + street_excavation_val + "</td><td>" + + "</td> </tr>";*/

  // if street_excavation_val === "yes" then include traffic control permit answers
  /*  if(street_excavation_val === "yes"){
          
    }*/

    html += "</tbody></table></div>";


    $("#permits").html(html);


   /* $.ajax({
        url: '../dal/eqs.php',
        data: formData,
        processData: false,
        contentType: false,
        type: 'POST',
        error: function(xhr, desc, e) {
            alert(xhr.responseText);
        },
        success: function(data) {
            window.location.href = "enroll_landing.php";
        }
    });
*/
}






