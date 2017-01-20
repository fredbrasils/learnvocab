/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* the variable 'contextPath' is defined in pageTemplate.tag */

/*  Function: delete a idiom */
$("#idiomTable").on("click","a[data-delete]", function(){

     var row = $(this);
     var id = $(this).attr("data-delete"); // get id of idiom

     var url = contextPath+"/idiom/deleteidiom/"+id;               
     
     var success = function(){
         // remove row
         var tr = row.closest('tr');

             tr.fadeOut(400, function(){ 
               tr.remove(); 
             });  
     };                 

     registerPost("", url, success); 

  });                      

  /* Function: edit a idiom */

   $("#idiomTable").on("click","a[data-edit]", function(){

     // get the reference of the link <a>
     var row = $(this);

     // get the <tr> closest of the <a> and change the css
     var tr = row.closest('tr');
              tr.css("display","none");

     // get value of first <td>
     var value = tr.find('td:first').text();

     // get the next <tr> and remove its style
     var nexttr = tr.next('tr');
          nexttr.removeAttr("style");

     // insert the value
     var input = nexttr.find('input');
         input.val(value);
  });

  /* Function of cancel idiom's edition */             
  $("#idiomTable").on("click","a[data-cancel]", function(){

     // get the reference of the link <a>
     var row = $(this);

     // get the <tr> closest of the <a> and change the css
     var tr = row.closest('tr');
              tr.css("display","none");

     // get the previous <tr> of the <tr> and remove the css
     tr.prev('tr').removeAttr("style");

  });

  /* Function update idiom */            
 $("#idiomTable").on("click","a[data-update]", function(){

     // get the idiom's ID
     var id = $(this).attr("data-update");

     // get the reference of the link <a>
     var row = $(this);

     // get the input
     var input = row.closest('tr').find('input');

     // get the idiom's value
     var idiomValue = input.val();                               

     var url = contextPath+"/idiom/updateidiom";                
     var queryName = "id="+id+"&name="+idiomValue;

     var success = function(data){

          // get the <tr> closest of the <a> and change the css
         var tr = row.closest('tr');
                  tr.css("display","none");

         // get the previous <tr> of the <tr> and remove the css
         tr = tr.prev('tr');
              tr.removeAttr("style");                                     

         // change the value of <td>s
         tr.find('td').each(function(index, element){

             if(index === 0){ // the first <td> is the idiom's name
                 $(element).text(data.idiom.name);
             }else if(index === 1){ // the second <td> is the idiom's date register
                 $(element).text(data.idiom.dateRegister.time);
                 return false;
             }
         });
     };

    registerPost(queryName, url, success);

 });

 /* Function register idiom  */
 function registerIdiom(){

//                event.preventDefault();

     // get the idiom's name
     var idiomName = $('#idiomName').val();
     var url = contextPath+"/idiom";              
     var queryName = "name="+idiomName;                                


     var success = function(data){

         var tbody = $('#idiomTable > tbody:last-child');                 
         var treditcopy = tbody.find("tr[data-tr-edit]:last").clone();
         var trshowcopy = tbody.find("tr[data-tr-show]:last").clone();                                                        

         trshowcopy.find('td').each(function(index, element){

                     if(index === 0){ // the first <td> is the idiom's name
                         $(element).text(data.idiom.name);
                     }else if(index === 1){ // the second <td> is the idiom's date register
                         $(element).text(data.idiom.dateRegister.time);
                         return false;
                     }
                 });

         trshowcopy.find('td a[data-delete]').attr("data-delete",data.idiom.id);

         treditcopy.find('td input').each(function(index, element){

                     if(index === 0){ 
                         $(element).attr("value",data.idiom.name);
                         return false;
                     }
                 });

         treditcopy.find('td a[data-update]').attr("data-update",data.idiom.id);                                

         tbody.append(trshowcopy).children(":last").hide().fadeIn(200,

             function(){
                 trshowcopy.animate({opacity: '0.2'}, 500);
                 trshowcopy.animate({opacity: '1'}, 500);
             }            
         );                                

         tbody.append(treditcopy); 
     };

     var done = function(){
         $('#idiomName').val("");
         $('#idiomName').focus();
     };

     registerPost(queryName, url, success, done);                

 };

 $("a[data-register]").click(registerIdiom);
