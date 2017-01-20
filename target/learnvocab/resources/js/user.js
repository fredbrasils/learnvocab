

/* the variable 'contextPath' is defined in pageTemplate.tag */

/*  Function: delete a user */
$("#userTable").on("click","a[data-delete]", function(){

    var row = $(this);
    var id = $(this).attr("data-delete"); // get id of user
     
    var url = contextPath+"/user/deleteuser/"+id;               
    
    var success = function(){
        // remove row
        var tr = row.closest('tr');

            tr.fadeOut(400, function(){ 
              tr.remove(); 
            });  
    };                 

    registerPost("", url, success); 

  });                      

  /* Function: edit a user */

   $("#userTable").on("click","a[data-edit]", function(){

        var id = $(this).attr("data-edit"); // get id of user

        var url = contextPath+"/user/edituser/"+id;

        window.location.href = url;
  });

 /* Function register user  */
 function registerUser(){

    //get the form data and then serialize that
    var queryName = $("#userForm").serialize();
    var url = contextPath+"/user/formuser";                      
    
    var success = function(data){
        window.location.href = contextPath+"/user";
     };

    registerPost(queryName, url, success);                

 };

 $("#userForm").submit(function(event){
     event.preventDefault();
     registerUser();
 });
