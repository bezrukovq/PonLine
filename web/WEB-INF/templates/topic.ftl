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

    <link rel="stylesheet" type="text/css" href="../../front/css/topic.css">

</head>
<body>
    <#include 'navbar.html'>

<div class="container">

    <br>

    <div class="row">
        <div class="col-md-4">
            <form class="container">
                <img src="${news.getuPicPath()}" class="closePic" width="200"
                     height="200">
            </form>
            <br>
            <form class="container">
                <a href="/profile?login=${news.getCrLogin()}" id="link" name="linkg">${news.getCrLogin()}</a>
            </form>

            <div class="detailBox">
                <div class="titleBox">
                    <label>Comments</label>
                </div>

                <div class="actionBox">
                    <ul class="commentList">
                        <#if comments?has_content>
                        <#list comments as c>
                        <li>
                            <div class="commenterImage">
                                <img src="${c.getuPicPath()}"/>
                            </div>
                            <div class="commentText">
                                <h6>${c.getCrLogin()}</h6>
                                <p class="">${c.getText()}</p> <span class="date sub-text">${c.getDate()}</span>

                            </div>
                        </li>
                        </#list>
                            </#if>
                    </ul>
                    <#if logged>
                    <form class="form-inline" method="post" role="form">
                        <div class="form-group">
                            <input name="comment" class="form-control" type="text" placeholder="Your comment" required="required"/>
                        </div>
                        <div class="form-group">
                            <input name="btn_comment" type="submit" class="btn btn-primary" value="add"
                                   id="btn_comment">
                            <br>
                        </div>
                    </form>
                    </#if>
                </div>
            </div>

        </div>


        <div class="col-md-8">
            <form class="container">
                <h5 name="title">${news.getHeader()}</h5>

            </form>

            <form class="container" ali><h6>
                ${news.getCategory()}
            </h6>

            </form>

            <form class="container">
                <a name="text">${news.getText()}</a>
                <br>
                <br>
            </form>


            <br>

        </div>
    </div>
</body>
</html>