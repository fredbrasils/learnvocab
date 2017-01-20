

/* the variable 'contextPath' is defined in pageTemplate.tag */

/*  Function: delete a box */
$("#boxTable").on("click","a[data-delete]", function(){

     var row = $(this);
     var id = $(this).attr("data-delete"); // get id of word

     var url = contextPath+"/box/deletebox/"+id;               
     
     var success = function(){
         // remove row
         var tr = row.closest('tr');

             tr.fadeOut(400, function(){ 
               tr.remove(); 
             });  
     };                 

     registerPost("", url, success);  

  });                      

 /* Function register box  */
 function registerBox(){

//   event.preventDefault();

     // get the box's name
     var boxName = $('#boxName').val();
     var boxDaysNumber = $('#boxDaysNumber').val();
     var url = contextPath+"/box";              
     var queryName = "name="+boxName+"&daysNumber="+boxDaysNumber;                                

     var success = function(data){

         var tbody = $('#boxTable > tbody:last-child');                 
         var trshowcopy = tbody.find("tr[data-tr-show]:last").clone();                                                        

        trshowcopy.find('td').each(function(index, element){

            if(index === 0){
                $(element).text(data.box.name);
            }else if(index === 1){ 
                $(element).text(data.box.daysNumber);                
            }else if(index === 2){ 
                $(element).text(data.box.order);                
            }else if(index === 3){ 
                $(element).text(data.box.dateRegister.time);
                return false;
            }
        });

        trshowcopy.find('td a[data-delete]').attr("data-delete",data.box.id);

        tbody.append(trshowcopy).children(":last").hide().fadeIn(200,

            function(){
                trshowcopy.animate({opacity: '0.2'}, 500);
                trshowcopy.animate({opacity: '1'}, 500);
            }            
        );                                

     };

     var done = function(){
         $('#boxName').val("");
         $('#boxDaysNumber').val("");
         $('#boxName').focus();
     };

     registerPost(queryName, url, success, done);                

 };

 $("a[data-register]").click(registerBox);
