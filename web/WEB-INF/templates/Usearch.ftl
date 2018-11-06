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
<br>
<center><h4>Search for users</h4></center>
<div class="container">
    <br>
    <div class="input-group mb-3">
        <input id="q" name="search" oninput="search()" type="text" class="form-control" aria-label="Text input with dropdown button">
    </div>
    <div id="r2d2" class="row">
    </div>
</div>
<script type="application/javascript">
    var search = function () {
        var paras = document.getElementsByClassName('col-md-1.2');
        while (paras[0]) {
            paras[0].parentNode.removeChild(paras[0]);
        }
        ajax2();
    };
    var ajax2 = function () {
        $.ajax({
            'url': '/searchserv',
            type: 'post',
            dataType: 'json',
            'data': {
                'q': $("#q").val()
            },
            success: function (obj) {
                for (var i = 0; i < obj.res.length; i++) {
                    var n = obj.res[i];
                    var div = document.createElement("p");
                    div.innerHTML = "<div class=\"col-md-1.2\">\n" +
                        "                <a href=\"/profile?login="+n.nam+"\" name=\"/profile?id="+n.nam+"\">" +
                        "            <form class=\"container\">\n" +
                        "                <img src=\""+n.path+"\" class=\"closePic\" width=\"100\"\n" +
                        "                     height=\"100\">\n" +
                        "            </form>\n" +
                        "\n" +
                        "            <form class=\"container\">\n"+n.nam+
                        "            </form>\n" +"</a>\n" +
                        "            <br>\n" +
                        "        </div>" +
                        "<br>";
                    r2d2.appendChild(div);
                }
            },
            failure: function (obj) {
                alert(2);
            }
        });
    }
</script>
</body>
</html>