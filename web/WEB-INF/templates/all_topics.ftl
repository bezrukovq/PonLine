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

<div class="container">
    <br>

    <form>
        <div class="input-group mb-3">
            <input name="search" type="text" class="form-control" aria-label="Text input with dropdown button">
            <div class="form-group">
                <select class="form-control" id="exampleFormControlSelect1">
                    <option name="default" value="null">Philter</option>
                    <option name="politics" value="politics">Politics</option>
                    <option name="nature" value="nature">Nature</option>
                    <option name="celebrities" value="celebrities">Celebrities</option>
                </select>
            </div>
        </div>
    </form>
    <div class="row">
        <#if news?has_content>
            <#list news as item>
        <div class="col-md-1.2">
            <form class="container">
                <img src="../../front/src/img_avatar.png" class="closePic" width="100"
                     height="100">
            </form>

            <form class="container">
                <a href="/profile?login=${item.getCrLogin()}" name="/profile?id=${item.getCrLogin()}">${item.getCrLogin()}</a>
            </form>

            <form class="container">
                <a href="vk.com/tim_baron" name="article_link">article link</a>
            </form>
        </div>

        <div class="col-md-2">
            <form class="container">
                <a href="/post?id=${item.getId()}"> <h5 name="title">${item.getHeader()}</h5></a>
                <h6>
                    <#list item.getTags() as tag>
                    ${tag},
                    </#list>
                </h6>
                <h6>
                    <#list item.getCategories() as tag>
                        ${tag},
                    </#list>
                </h6>
            </form>
        </div>

        <div class="col-md-8">
            <form class="container">
                <a name="title"> TITLE DATE ${item.getDate()}</a>
            </form>
        </div>
            </#list>
        </#if>
    </div>
</div>
</body>
</html>