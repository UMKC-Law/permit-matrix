<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>KCMO</title>
        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="css/index.css" rel="stylesheet">
        <!-- Custom Fonts -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
        
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body id="page-top" >

        <!-- Navigation -->
        <nav class="navbar navbar-custom" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                    <i class="fa fa-bars"></i>
                    </button>
                    <a class="navbar-brand page-scroll" href="#page-top">
                        <img id='kc_logo' src='img/kc_logo.png' />
                    </a>
                    <div id='logo_text'>
                        <div>KCMO</div>
                        <div>Plumbing Project</div>
                        <div>Permitting Process</div>
                    </div>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse navbar-right navbar-main-collapse">
                    <ul class="nav navbar-nav">
                        <li>
                            <a id="about" href="#about">About</a>
                        </li>
                        <div class="dropdown">
                            <li class="dropdown-toggle" type="button" data-toggle="dropdown"><span class="caret"></span>Permits</li>
                            <ul class="dropdown-menu">
                                <li><a target="_blank" href="pdf/IB109.pdf">IB109</a></li>
                                <li><a target="_blank" href="pdf/traffic_control.pdf">Traffic Control</a></li>
                                <li><a target="_blank" href="pdf/excavation_permit.pdf">Excavation</a></li>
                            </ul>
                        </div>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>
        <div id="main_container" class="intro">
            <div class="container">
                <div class='row'>
                    <div id='main_content' class='col-lg-10 col-lg-offset-1'>
                        <div id="myCarousel" class="carousel slide carousel-fade" data-ride="carousel" data-interval="false">
                            <!-- Indicators -->
                            <ol class="carousel-indicators">
                                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                <li data-target="#myCarousel" data-slide-to="1"></li>
                                <li data-target="#myCarousel" data-slide-to="2"></li>
                                <li data-target="#myCarousel" data-slide-to="3"></li>
                                <li data-target="#myCarousel" data-slide-to="4"></li>
                                <li data-target="#myCarousel" data-slide-to="5"></li>
                                <li data-target="#myCarousel" data-slide-to="6"></li>
                            </ol>
                            <!-- Wrapper for slides -->
                            <div class="carousel-inner" role="listbox">
                                <div class="item active row">
                                    <form action="" method="POST" role="form">
                                        <legend>PERSONAL INFORMATION</legend>
                                        
                                        <div class="form-group">
                                            <label for="">First Name</label>
                                            <input type="text" class="form-control" id="fname" placeholder="First Name" value="Brett">
                                        </div>
                                        <div class="form-group">
                                            <label for="">Last Name</label>
                                            <input type="text" class="form-control" id="lname" placeholder="Last Name" value="Wolverton">
                                        </div>
                                        
                                        <div class="form-group">
                                            <label for="">Job Title</label>
                                            <input type="text" class="form-control" id="job_title" placeholder="Job Title" value="Programmer">
                                        </div>
                                        <!-- for permit applications -->
                                        <div class="form-group">
                                            <label for="">Email</label>
                                            <input type="text" class="form-control" id="personal_email" placeholder="Email" value="bawd54@mail.umkc.edu">
                                        </div>
                                        <!-- for permit and traffic applications -->
                                        <div class="form-group">
                                            <label for="">Phone</label>
                                            <input type="text" class="form-control" id="mobile_phone" placeholder="Phone" value="8162104584">
                                        </div>
                                        <div class="form-group">
                                            <label for="">Contractor License Number</label>
                                            <input type="text" class="form-control" id="contractor_num" placeholder="Contractor License Number" value="123456789">
                                        </div>
                                        <div class="form-group">
                                            <label for="">Occupational License Number</label>
                                            <input type="text" class="form-control" id="occupational_num" placeholder="Occupational License Number" value="987654321">
                                        </div>
                                        
                                        <!--    <button type="submit" class="btn btn-primary">Submit</button> -->
                                    </form>
                                </div>
                                <div class="item row">
                                    <form action="" method="POST" role="form">
                                        <legend>BUISNESS INFORMATION</legend>
                                        
                                        <div class="form-group">
                                            <label for="">Business Name</label>
                                            <input type="text" class="form-control" id="business_name" placeholder="Business Name" value="UMKC Plumbing">
                                        </div>
                                        
                                        <div class="form-group">
                                            <label for="">Phone</label>
                                            <input type="text" class="form-control" id="business_phone" placeholder="Business Phone" value="8162351000">
                                        </div>
                                        <div class="form-group">
                                            <label for="">Street Address</label>
                                            <input type="text" class="form-control" id="business_address" placeholder="Street Address" value="5100 Rockhill Rd">
                                        </div>
                                        <!-- could be a kansas company -->
                                        <div class="form-group">
                                            <label for="">City</label>
                                            <input type="text" class="form-control" id="business_city" placeholder="City" value="Kansas City">
                                        </div>
                                        <!-- could be a kansas company -->
                                        <div class="form-group">
                                            <label for="">State</label>
                                            <input type="text" class="form-control" id="business_state" placeholder="State" value="MO">
                                        </div>
                                        <div class="form-group">
                                            <label for="">Zip Code</label>
                                            <input type="text" class="form-control" id="business_zip" placeholder="Zip Code" value="64110">
                                        </div>
                                        <!-- <button type="submit" class="btn btn-primary">Submit</button> -->
                                    </form>
                                </div>
                                <div class="item row">
                                    <form action="" method="POST" role="form">
                                        <legend>PROJECT INFORMATION</legend>
                                        
                                        <div class="form-group">
                                            <label for="">Project Name</label>
                                            <input type="text" class="form-control" id="project_name" placeholder="Project Name" value="BEH Repair">
                                        </div>
                                        <div class="form-group">
                                            <label for="">Project Address</label>
                                            <input type="text" class="form-control" id="project_address" placeholder="Project Address" value="5110 Cherry Street">
                                        </div>
                                        <div class="form-group">
                                            <label for="">Project Description</label>
                                            <textarea name="" id="project_description" class="form-control" rows="3" placeholder="Project Description"></textarea>
                                        </div>
                                        
                                        <!-- <button type="submit" class="btn btn-primary">Submit</button> -->
                                    </form>
                                </div>
                                <div class="item row">
                                    <form id="property_details" action="" method="POST" role="form">
                                        <legend>PROJECT INFORMATION- Property Details</legend>
                                        <table>
                                            <tr>
                                                <td>
                                                    <div class="form-group">
                                                        <label for="">Zoning District</label>
                                                        <input type="text" class="form-control" id="zoning_district" placeholder="Zoning District" value="A">
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="form-group">
                                                        <label for="">OCC Group</label>
                                                        <input type="text" class="form-control" id="occ_group" placeholder="OCC Group" value="B">
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="form-group">
                                                        <label for="">Construction Type</label>
                                                        <input type="text" class="form-control" id="const_type" placeholder="Construction Type" value="Repair">
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="form-group">
                                                        <label for="">Structural Class</label>
                                                        <input type="text" class="form-control" id="struct_class" placeholder="Structural Class" value="C">
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="form-group">
                                                        <label for="">Site Plan Required</label>
                                                        <input type="text" class="form-control" id="site_plan" placeholder="Site Plan Required" value="NO">
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="form-group">
                                                        <label for="">Control Number</label>
                                                        <input type="text" class="form-control" id="control_num" placeholder="Control Number" value="123">
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="form-group">
                                                        <label for="">Number of Stories</label>
                                                        <input type="text" class="form-control" id="stories" placeholder="Number of Stories" value="2">
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="form-group">
                                                        <label for="">Gross Building Area</label>
                                                        <input type="text" class="form-control" id="building_area" placeholder="Gross Building Area" value="1200">
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="form-group">
                                                        <label for="">Dwelling Units</label>
                                                        <input type="text" class="form-control" id="dwelling_units" placeholder="Dwelling Units" value="5">
                                                    </div>
                                                </td>
                                            </tr>
                                            
                                            <!-- <button type="submit" class="btn btn-primary">Submit</button> -->
                                        </table>
                                    </form>
                                </div>
                                <div class="item row">
                                    <form id="other_info" action="" method="POST" role="form">
                                        <legend>PROJECT INFORMATION- Other Questions</legend>
                                        <div>
                                            <span>Does your project involve removing septic tanks and private infiltration fields?</span>
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="septic_removal" id="septic_removal" value="yes">Yes
                                                </label>
                                                <label>
                                                    <input type="radio" name="septic_removal" id="septic_removal" value="no">No
                                                </label>
                                            </div>
                                        </div>
                                        <div>
                                            <span>Do you have insurance on file with Public Works or Parks and Recreation?</span>
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="insurance" id="input" value="yes">Yes
                                                </label>
                                                <label>
                                                    <input type="radio" name="insurance" id="input" value="no">No
                                                </label>
                                            </div>
                                        </div>
                                        <div>
                                            <span>Will any portion of excavation interfere with street pavement or sidewalk?</span>
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="street_excavation" id="street_excavation" value="yes">Yes
                                                </label>
                                                <label>
                                                    <input type="radio" name="street_excavation" id="street_excavation" value="no">No
                                                </label>
                                            </div>
                                        </div>
                                        
                                        <!-- <button type="submit" class="btn btn-primary">Submit</button> -->
                                    </form>
                                    <form id="traffic_info" action="" method="POST" role="form" style="display:none;">
                                        <legend>PROJECT INFORMATION- Traffic Control</legend>
                                        <div class="form-group">
                                            <label for="">What streets will you need to have closed?  Specify which lanes of traffic will be closed.</label>
                                            <textarea name="" id="streets_closed" class="form-control" rows="2" required="required" placeholder="Please give the street address and specify which lanes will need to be closed."></textarea>
                                        </div>
                                        <div class="form-group">
                                            <div><span for="" class="control-label">When will the street(s) be closed?</span></div>
                                            <input type="text" class="col-sm-3 form-control-horizontal" id="street_close_start" placeholder="Start Date" value="11/13/15">
                                            <input type="text" class="col-sm-3 form-control-horizontal" id="street_close_end" placeholder="End Date" value="12/14/15">
                                        </div>
                                        <div class="form-group">
                                            <label for="">What sidewalks will you need to have closed?</label>
                                            <textarea name="" id="sidewalks_closed" class="form-control" rows="2" required="required" placeholder="Please give the street address and specify which lanes will need to be closed."></textarea>
                                        </div>
                                        <div class="form-group">
                                            <div><span for="" class="control-label">When will the sidewalk(s) be closed?</span></div>
                                            <input type="text" class="col-sm-3 form-control-horizontal" id="sidewalk_close_start" placeholder="Start Date" value="11/13/15">
                                            <input type="text" class="clo-sm-3 form-control-horizontal" id="sidewalk_close_end" placeholder="End Date" value="12/14/15">
                                        </div>
                                        <div class="form-group">
                                            <label for="">Please give the boundaries of your closure:</label>
                                            <textarea name="" id="boundary_closed" class="form-control" rows="2" required="required" placeholder="Please give the street address and specify which lanes will need to be closed."></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label for="">What is the traffic control permit for? (Placing cones, placing signs, directing traffic, etc..)</label>
                                            <textarea name="" id="permit_reason" class="form-control" rows="2" required="required" placeholder="Please give the street address and specify which lanes will need to be closed."></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label for="">What is your proposed detour route (only applies to full closure of traffic in any direction)?</label>
                                            <textarea name="" id="proposed_detour" class="form-control" rows="2" required="required" placeholder="Please give the street address and specify which lanes will need to be closed."></textarea>
                                        </div>
                                        <div class="form-group">
                                            <div><span for="" class="control-label">If this is a renewal of an old permit, please provide the number for your old permit:</span></div>
                                            <input type="text" class="form-control" id="old_permit_num" placeholder="Permit #" value=""123456789>
                                        </div>
                                        
                                        <!-- <button type="submit" class="btn btn-primary">Submit</button> -->
                                    </form>
                                </div>
                                
                                <div class="item row">
                                    <form action="" method="POST" role="form">
                                        <legend id='credit_info'>CREDIT CARD AUTHORIZATION</legend>
                                        <!--                                 <table class="table" style="border:none;">
                                            <tbody>
                                                <tr>
                                                    <td>                                         <div class="form-group">
                                                        <div><span for="" class="control-label">Name</span></div>
                                                        <input type="text" class="form-control" id="card_holder_name" placeholder="Card Holder Name">
                                                    </div> </td>
                                                    <td>                                         <div class="form-group">
                                                        <div><span for="" class="control-label">Account #</span></div>
                                                        <input type="text" class="form-control" id="account_num" placeholder="Account #">
                                                    </div> </td>
                                                </tr>
                                                <tr>
                                                    <td>                                        <div class="form-group">
                                                        <div><span for="" class="control-label">Expiration Date</span></div>
                                                        <input type="text" class="form-control" id="exp_date" placeholder="Expiration">
                                                    </div></td>
                                                    <td>                                            <div><span for="" class="control-label">Street Address</span></div>
                                                    <input type="text" class="form-control-horizontal" id="card_address" placeholder="Street Address"></td>
                                                </tr>
                                                <tr>
                                                    <td>                                            <div><span for="" class="control-label">City</span></div>
                                                    <input type="text" class="form-control-horizontal" id="card_city" placeholder="City"></td>
                                                    <td>                                            <div><span for="" class="control-label">State</span></div>
                                                    <input type="text" class="form-control-horizontal" id="card_state" placeholder="State"></td>
                                                    <td>                                            <div><span for="" class="control-label">Zip</span></div>
                                                    <input type="text" class="form-control-horizontal" id="card_zip" placeholder="Zip Code"></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    -->
                                        <div class="form-group">
                                            <div><span for="" class="control-label">Name</span></div>
                                            <input type="text" class="form-control" id="card_holder_name" placeholder="Card Holder Name" value="Brett Wolverton">
                                        </div>

                                        <div class="form-group">
                                            <div><span for="" class="control-label">Account #</span></div>
                                            <input type="text" class="form-control" id="account_num" placeholder="Account #" value="123456789">
                                        </div>

                                        <div class="form-group">
                                            <div><span for="" class="control-label">Expiration Date</span></div>
                                            <input type="text" class="form-control" id="exp_date" placeholder="Expiration" value="11/14/2018">
                                        </div>

                                        <div class="form-group">
                                            <div><span for="" class="control-label">Security Code</span></div>
                                            <input type="text" class="form-control" id="security_code" placeholder="Security Code" value="000">
                                        </div>

                                        <div class="form-group">
                                            <label for="">Street Address</label>
                                            <input type="text" class="form-control" id="card_address" placeholder="Street Address" value="5100 Rockhill Rd">
                                        </div>
                                        <!-- could be a kansas company -->
                                        <div class="form-group">
                                            <label for="">City</label>
                                            <input type="text" class="form-control" id="card_city" placeholder="City" value="Kansas City">
                                        </div>
                                        <!-- could be a kansas company -->
                                        <div class="form-group">
                                            <label for="">State</label>
                                            <input type="text" class="form-control" id="card_state" placeholder="State" value="MO">
                                        </div>

                                        <div class="form-group">
                                            <label for="">Zip Code</label>
                                            <input type="text" class="form-control" id="card_zip" placeholder="Zip Code" value="64110">
                                        </div>
                                            
                                        </form>
                                    </div>
                                    <div class="item row">
                                        <form id='permits'>
                                            <legend >PERMITS</legend>



                                            <div class='form-group permits'><a target="_blank" href="pdf/IB109.pdf" style='color:white;'>Permit Application</a></div>
                                            <div class='form-group permits'><a target="_blank" href="pdf/excavation_permit.pdf" style='color:white;'>Excavation Permit</a></div>
                                            <div class='form-group permits'><a target="_blank" href="pdf/traffic_control.pdf" style='color:white;'>Traffic Control Permit</a></div>
                                            <button id='form_submit' class="btn btn-primary">Summary</button>
                                            
                                        </form>
                                    </div>
                                </div>
                                <!-- Left and right controls -->
                                <a id='back' class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                    <span class="sr-only">Previous</span>
                                </a>
                                <a id="next" class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                    <span class="sr-only">Next</span>
                                </a>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Footer -->
            <footer>
                <div class="container text-center">
                </div>
            </footer>
            <!-- jQuery -->
            <script src="js/jquery.js"></script>
            <!-- Bootstrap Core JavaScript -->
            <script src="js/bootstrap.min.js"></script>
            <!-- Plugin JavaScript -->
            <script src="js/jquery.easing.min.js"></script>
            <script type="text/javascript" src="js/index.js"></script>
        </body>
    </html>