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
        <div class="form-group">
            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                   placeholder="search" name="search">
            <small id="search" class="form-text text-muted"></small>
            <br>
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
                <a name="text"> The main reason for going to university is to get an academic qualification, but
                    personally, I think that the social side of things and developing as a person are equally important.
                    The good thing about university life is that you are left to your own devices, unlike school, where
                    you're told what to do and how to do it. It's important to socialise and meet new friends. lt takes
                    some people longer than others. If a student has any problems, about anything, there's student
                    counselling available at all universities, where they try and help out as much as possible. I've
                    been a volunteer for our Student Counselling Scheme for some time now. I've found it very rewarding,
                    not only with helping other students through university life but also as an extra-curricular
                    activity. DATE ${item.getDate()}</a>
            </form>
        </div>
            </#list>
        </#if>
    </div>
</div>
</body>
</html>