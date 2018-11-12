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
    <#include 'navbar.ftl'>
<div class="container">
    <br>
<#if all>
    <form method="post">
        <div class="input-group mb-3">
            <input name="search" type="text" class="form-control" aria-label="Text input with dropdown button">
            <div class="form-group">
                <select class="form-control" name="filter" id="filter">
                    <option name="default" value="0">No Filter</option>
                    <option name="default" value="1">No Category</option>
                    <option name="politics" value="2">Politics</option>
                    <option name="nature" value="3">Nature</option>
                    <option name="celebrities" value="4">Celebrities</option>
                    <option name="nature" value="5">Army/War</option>
                    <option name="nature" value="6">Tech</option>
                </select>
            </div>
        </div>
    </form>
</#if>

    <div class="row">
        <#if news?has_content>
            <#list news as item>
        <div class="col-md-1.2">
            <form class="container">
                <img src="${item.getuPicPath()}" class="closePic" width="100"
                     height="100">
            </form>

            <form class="container">
                <a href="/profile?login=${item.getCrLogin()}" name="/profile?id=${item.getCrLogin()}">${item.getCrLogin()}</a>
            </form>
<br>
        </div>

        <div class="col-md-2">
            <form class="container">
                <a href="/post?id=${item.getId()}"> <h5 name="title">${item.getHeader()}</h5></a>
                <h6>
                    ${item.getCategory()}
                </h6>
            </form>
        </div>

        <div class="col-md-8">
            <form class="container">
                <a name="title">${item.getDate()}</a>
            </form>
        </div>
            </#list>
        </#if>
    </div>
</div>
</body>
</html>