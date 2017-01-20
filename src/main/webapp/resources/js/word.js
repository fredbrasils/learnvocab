

/* the variable 'contextPath' is defined in pageTemplate.tag */

/*  Function: delete a word */
$("#wordTable").on("click","a[data-delete]", function(){

     var row = $(this);
     var id = $(this).attr("data-delete"); // get id of word

     var url = contextPath+"/word/deleteword/"+id;               
     
     var success = function(){
         // remove row
         var tr = row.closest('tr');

             tr.fadeOut(400, function(){ 
               tr.remove(); 
             });  
     };                 

     registerPost("", url, success); 

  });                      

  /* Function: edit a word */

   $("#wordTable").on("click","a[data-edit]", function(){

    // get the reference of the link <a>
    var row = $(this);

    // get the <tr> closest of the <a> and change the css
    var tr = row.closest('tr');
              tr.css("display","none");

    // get the next <tr> 
    var nexttr = tr.next('tr');
        nexttr.removeAttr("style");
          
    // get field with attribute '[data-obj]'
    nexttr.find('[data-obj]').each(function(index, element){            
        $(element).val($(element).attr("data-obj"));
    });
    
  });

  /* Function of cancel word's edition */             
  $("#wordTable").on("click","a[data-cancel]", function(){

     // get the reference of the link <a>
     var row = $(this);

     // get the <tr> closest of the <a> and change the css
     var tr = row.closest('tr');
              tr.css("display","none");

     // get the previous <tr> of the <tr> and remove the css
     tr.prev('tr').removeAttr("style");

  });

  /* Function update word */            
 $("#wordTable").on("click","a[data-update]", function(){

    // get the reference of the link <a>
    var row = $(this);
     
    // get the word's ID
    var id = row.attr("data-update");
    
    var tr = row.closest('tr');
    var queryName = "id="+id;
    
    tr.find('[name]').each(function(index, element){            
        
        if($(element).is("select") && $(element).attr("name") === "idiom"){
            queryName = queryName +"&idiom.id="+$(element).val();            
        }else{
            queryName = queryName +"&"+$(element).attr("name")+"="+$(element).val();
        }
    });
    
    var url = contextPath+"/word/updateword";                

    var success = function(data){

          // get the <tr> closest of the <a> and change the css
         var tr = row.closest('tr');
                  tr.css("display","none");

         // get the previous <tr> of the <tr> and remove the css
         tr = tr.prev('tr');
              tr.removeAttr("style");                                     

         // change the value of <td>s
         tr.find('td').each(function(index, element){

            if(index === 0){
                $(element).text(data.word.word);
            }else if(index === 1){ 
                $(element).text(data.word.idiom.name);                
            }else if(index === 2){ 
                $(element).text(data.word.priority);                
            }else if(index === 3){ // the second <td> is the word's date register
                $(element).text(data.word.dateRegister.time);
                return false;
            }
         });
     };

    registerPost(queryName, url, success);

 });

 /* Function register word  */
 function registerWord(){

    //get the form data and then serialize that
    var queryName = $("#wordForm").serialize();
    var queryNameArray = $("#wordForm").serializeArray();    
    var url = contextPath+"/word";                      

    var success = function(data){        

        var tbody = $('#wordTable > tbody:last-child');                 
        var treditcopy = tbody.find("tr[data-tr-edit]:last").clone();
        var trshowcopy = tbody.find("tr[data-tr-show]:last").clone();                                                        

        trshowcopy.find('td').each(function(index, element){

            if(index === 0){
                $(element).text(data.word.word);
            }else if(index === 1){ 
                $(element).text(data.word.idiom.name);                
            }else if(index === 2){ 
                $(element).text(data.word.priority);                
            }else if(index === 3){ // the second <td> is the word's date register
                $(element).text(data.word.dateRegister.time);
                return false;
            }
        });

        trshowcopy.find('td a[data-delete]').attr("data-delete",data.word.id);
        
        /*
        treditcopy.find('td input,td select').each(function(index, element){
                    
            if(index === 0){ 
                $(element).attr("value",data.word.word);                        
            }else if(index === 1){ 
                $(element).attr("value",data.word.idiom.id);                
            }else if(index === 2){ 
                $(element).attr("value",data.word.priority);                
            }
        });
        */
       
        treditcopy.find('[name]').each(function(index, element){            
            
            var jsonObj = data.word;
            $.each(jsonObj, function (key, data) {
                
                if(key === $(element).attr("name")){
                    
                    if(typeof data === 'object'){
                        $(element).val(data.id);
                    }else if(typeof data === 'string'){
                        $(element).val(data);
                    }
                }                
            });        
        });

        treditcopy.find('td a[data-update]').attr("data-update",data.word.id);                                

        tbody.append(trshowcopy).children(":last").hide().fadeIn(200,

            function(){
                trshowcopy.animate({opacity: '0.2'}, 500);
                trshowcopy.animate({opacity: '1'}, 500);
            }            
        );                                

        tbody.append(treditcopy); 
     };

    var done = function(){         
        $.each(queryNameArray, function(index, obj){
           
           if(index === 0){
               $( "[name*='"+obj.name+"']" ).focus();
               
           }
           $( "[name*='"+obj.name+"']" ).val("");
        });
    };

    registerPost(queryName, url, success, done);                

 };

 $("a[data-register]").click(registerWord);
