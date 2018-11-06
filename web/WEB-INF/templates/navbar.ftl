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
<nav class="navbar navbar-default navbar-fixed-top" style="
height: 50px;
background: #e7e7e7">
    <div class="container">
        <button name="main" onclick="document.location='/news'" type="button" class="btn btn-default navbar-btn" style="background: #e7e7e7">Main</button>
        <button name="new_topic" onclick="document.location='/newpost'" type="button" class="btn btn-default navbar-btn" style="background: #e7e7e7">Create
            topic
        </button>
        <button name="main" onclick="document.location='/usearch'" type="button" class="btn btn-default navbar-btn" style="background: #e7e7e7">Search Users</button>

        <#if logged>
        <button name="main" onclick="document.location='/profile?login=${login}'" type="button" class="btn btn-default navbar-btn" style="background: #e7e7e7">Profile</button>
        <#else>
        <button name="main" onclick="document.location='/login'" type="button" class="btn btn-default navbar-btn" style="background: #e7e7e7">Log In</button>
        </#if>
    </div>
</nav>
</body>
</html>