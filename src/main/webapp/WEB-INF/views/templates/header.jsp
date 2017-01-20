<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

    <!-- 
        <security:authorize access="isAnonymous()">
            <h1>Conheça nosso site!</h1>
        </security:authorize>

        <security:authorize access="isAuthenticated()">            
            <h1>Bem Vindo </h1>
            <security:authentication property="principal" var="usuario" />
            Usuário: ${usuario.username} | <a href="<c:url value="/logout" />">Logout</a>             
        </security:authorize>
    -->
        
    <!-- Navbar Top-->
    <div class="w3-top" style="z-index:4">
        <ul class="w3-navbar w3-theme-d2 w3-left-align w3-large">
            <li class="w3-hide-medium w3-hide-large w3-opennav w3-right">
              <a class="w3-padding-large w3-hover-white w3-large w3-theme-d2" href="javascript:void(0);" onclick="openNav()"><i class="fa fa-bars"></i></a>
            </li>
            <li><a href="#" class="w3-padding-large w3-theme-d4"><i class="fa fa-home w3-margin-right"></i>Logo</a></li>
            <li class="w3-hide-small"><a href="#" class="w3-padding-large w3-hover-white" title="News"><i class="fa fa-globe"></i></a></li>
            <li class="w3-hide-small"><a href="#" class="w3-padding-large w3-hover-white" title="Account Settings"><i class="fa fa-user"></i></a></li>
            <li class="w3-hide-small"><a href="#" class="w3-padding-large w3-hover-white" title="Messages"><i class="fa fa-envelope"></i></a></li>
            <li class="w3-hide-small w3-dropdown-hover">
                <a href="#" class="w3-padding-large w3-hover-white" title="Notifications"><i class="fa fa-bell"></i><span class="w3-badge w3-right w3-small w3-green">3</span></a>
                <div class="w3-dropdown-content w3-white w3-card-4">
                  <a href="#">One new friend request</a>
                  <a href="#">John Doe posted on your wall</a>
                  <a href="#">Jane likes your post</a>
                </div>
            </li>
            <li class="w3-hide-small w3-right"><a href="#" class="w3-padding-large w3-hover-white" title="My Account"><img src="/w3images/avatar2.png" class="w3-circle" style="height:25px;width:25px" alt="Avatar"></a></li>
        </ul>
    </div>
    
    <nav class="w3-sidenav w3-collapse w3-white w3-animate-left" style="z-index:3;width:300px;top:53px;" id="mySidenav"><br>
        <div class="w3-container w3-row">
          <div class="w3-col s4">
            <img src="/w3images/avatar2.png" class="w3-circle w3-margin-right" style="width:46px">
          </div>
          <div class="w3-col s8">
            <span>Welcome, <strong>Mike</strong></span><br>
            <a href="#" class="w3-hover-none w3-hover-text-red w3-show-inline-block"><i class="fa fa-envelope"></i></a>
            <a href="#" class="w3-hover-none w3-hover-text-green w3-show-inline-block"><i class="fa fa-user"></i></a>
            <a href="#" class="w3-hover-none w3-hover-text-blue w3-show-inline-block"><i class="fa fa-cog"></i></a>
          </div>
        </div>
        <hr>
        <div class="w3-container">
          <h5>Dashboard</h5>
        </div>
<!--        <a href="#" class="w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  Close Menu</a>
        <a href="#" class="w3-padding w3-blue"><i class="fa fa-users fa-fw"></i>  Overview</a>
        <a href="#" class="w3-padding"><i class="fa fa-eye fa-fw"></i>  Views</a>
        <a href="#" class="w3-padding"><i class="fa fa-users fa-fw"></i>  Traffic</a>
        <a href="#" class="w3-padding"><i class="fa fa-bullseye fa-fw"></i>  Geo</a>
        <a href="#" class="w3-padding"><i class="fa fa-diamond fa-fw"></i>  Orders</a>
        <a href="#" class="w3-padding"><i class="fa fa-bell fa-fw"></i>  News</a>
        <a href="#" class="w3-padding"><i class="fa fa-bank fa-fw"></i>  General</a>
        <a href="#" class="w3-padding"><i class="fa fa-history fa-fw"></i>  History</a>
        <a href="#" class="w3-padding"><i class="fa fa-cog fa-fw"></i>  Settings</a><br><br>-->
        <a href="#" class="w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  Close Menu</a>
        <a href="${s:mvcUrl('IC#showIdiomList').build() }" class="w3-padding"><i class="fa fa-eye fa-fw"></i>  Lista de idioma</a>
        <a href="${s:mvcUrl('WC#showWordList').build() }" class="w3-padding"><i class="fa fa-bank fa-fw"></i>  Lista de palavras</a>
        <a href="${s:mvcUrl('UC#showUserList').build() }" class="w3-padding"><i class="fa fa-users fa-fw"></i>  Lista de usuários</a>
        <a href="${s:mvcUrl('BC#showBoxList').build() }" class="w3-padding"><i class="fa fa-bullseye fa-fw"></i>  Lista de Boxes</a>      
        
    </nav>
        
    <div id="message"></div>
       
    <script>
        // Get the Sidenav
        var mySidenav = document.getElementById("mySidenav");

        // Get the DIV with overlay effect
        var overlayBg = document.getElementById("myOverlay");

        // Toggle between showing and hiding the sidenav, and add overlay effect
        function w3_open() {
            if (mySidenav.style.display === 'block') {
                mySidenav.style.display = 'none';
                overlayBg.style.display = "none";
            } else {
                mySidenav.style.display = 'block';
                overlayBg.style.display = "block";
            }
        }

        // Close the sidenav with the close button
        function w3_close() {
            mySidenav.style.display = "none";
            overlayBg.style.display = "none";
        }
        
        // Accordion
        function myFunction(id) {
            var x = document.getElementById(id);
            if (x.className.indexOf("w3-show") == -1) {
                x.className += " w3-show";
                x.previousElementSibling.className += " w3-theme-d1";
            } else {
                x.className = x.className.replace("w3-show", "");
                x.previousElementSibling.className =
                x.previousElementSibling.className.replace(" w3-theme-d1", "");
            }
        }

        // Used to toggle the menu on smaller screens when clicking on the menu button
        function openNav() {
            var x = document.getElementById("navDemo");
            if (x.className.indexOf("w3-show") == -1) {
                x.className += " w3-show";
            } else {
                x.className = x.className.replace(" w3-show", "");
            }
        }
    </script>

  
    