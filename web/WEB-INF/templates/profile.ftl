<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
    <#include 'navbar.html'>

<div class="container" align="center">
    <h1 align="center">
        Profile
    </h1>

    <form role="form">


        <div class="card" style="width: 18rem">
            <img name="avatar" class="card-img-top" src="${user.getPicPath()}" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title" id="NickName">${user.getLogin()}</h5>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item" id="Name">${user.getName()}</li>
                <li class="list-group-item" id="Region">${user.getDescription()}</li>
            </ul>
            <div class="card-body" align="center">
                <a href="/uposts?login=${user.getLogin()}" class="card-link" id="Topics">Topics</a>
            </div>
            <#if thisUser>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">
                    <input name="button" type="button" class="btn btn-info btn-block" value="Edit" id="btn_edit">
                </li>
            </ul>
            <#if user.isAdmin()>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">
                    <a href="/tcheck"> <input name="button" type="button" class="btn btn-dark btn-block" value="Topics" id="btn_topics"></a>
                </li>
            </ul>
            </#if>
            </#if>
        </div>


    </form>
</div>
</body>
</html>