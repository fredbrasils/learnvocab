/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* Show popup message */
function showMessage(data){                
                
    var messages = "";
    var clazz = "success";
    
    // get messages from server
    if(data.message !== null && data.message !== undefined){
        
        $.each(data.message, function(index, obj){
           clazz = obj.clazz; 
           messages += obj.message + " <br>";
        });                    

        // show message by effect
        $('#message').fadeIn(1,function(){                    
            $('#message').addClass('box-message').addClass(clazz);
            $('#message').append(messages);

        }).delay(5000).fadeOut(2000,function(){
            $('#message').removeClass();
            $('#message').empty();
        });

        // return true if it is a success message
        if(clazz === "success" ){
            return true;
        }
        
        return false;
    }
    
    return false;
}

function showMessageServerError(){                
                
    var messages = "Service is unavailable in this moment!";
    var clazz = "error";

    // show message by effect
    $('#message').fadeIn(1,function(){                    
        $('#message').addClass('box-message').addClass(clazz);
        $('#message').append(messages);

    }).delay(5000).fadeOut(2000,function(){
        $('#message').removeClass();
        $('#message').empty();
    });
}

/* Function register idiom  
 * 
 * @param type: 'POST' or 'GET'
 * @param queryName: data will be submited
 * @param url: Url path
 * @param success: Function will be executed in success' case
 * @param done: Function will be executed independet of result
 * @param fail: Function will be executed in fail' case
 * */
function register(type, queryName, url, success, done, fail){    
    
    // get the security header spring
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    var csrfToken = $("meta[name='_csrf']").attr("content");

    var headers = {};
    headers[csrfHeader] = csrfToken;                        

    $.ajax({
        type: type,
        data: queryName,
        url: url,
        dataType: "json",
        headers: headers,

        beforeSend: function (jqXHR, settings) {
            //chamar gif de processando
        },
        success: function(data, textStatus, jqXHR) {

                var successMessage = showMessage(data); // show the message

                if(successMessage){
                    
                    if(success !== undefined){
                        success(data); // success' function
                    }
                    
                }else{
                    if(fail !== undefined){
                        fail(data); // fail's function
                    }
                }    
                
                if(done !== undefined){
                    done(data); // done's function
                }
                
              },
              
        complete:function (jqXHR, textStatus) {
            //fecha gif de processando
        },
        
        error: function(jqXHR, textStatus, errorThrown){
            showMessageServerError();                
        }            
    });

}

/* Ajax Post request */
function registerPost(queryName, url, success, fail, done){
    register("POST", queryName, url, success, fail, done);
}

/* Ajax Get request */
function registerGet(queryName, url, success, fail, done){
    register("GET", queryName, url, success, fail, done);
}