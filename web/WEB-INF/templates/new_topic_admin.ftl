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

<div class="container">
    <br>

    <div class="row">
        <div class="col-md-4" align="center">
            <form method="post">
                <label for="accept">Accept</label>
                <input id="accept" required name="two" type="radio" class="btn btn-outline-success btn-lg" value="Accept"/>
            <br>
                <label for="deny">Deny</label>
                <input id="deny" name="two" required type="radio" class="btn btn-outline-danger" value="Deny"/>
                <br>
                <input name="submit" type="submit" class="btn btn-outline-success btn-lg" value="Submit"/>
            </form>
        </div>


        <div class="col-md-8">
            <form class="container">
                <h1 name="title" align="center">${news.getHeader()}</h1>
            </form>

            <form class="container" ali>
                <h6>
                    <#list news.getTags() as tag>
                        <div align="center">${tag}</div>
                    </#list>
                </h6>
                <h6>
                    <#list news.getCategories() as tag>
                        <div align="center">${tag}</div>
                    </#list>
                </h6>

            </form>

            <form class="container">
                <a name="text">${news.getText()}</a>
                <br>
                <br>
            </form>

            <br>

            <form class="container">
                <img name="image" src="../../front/src/img_avatar.png" class="closePic" width="100"
                     height="100">
            </form>

        </div>
    </div>
</div>
</body>
</html>