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
    <h1 align="center">
        New Topic
    </h1>

    <br>

    <div class="row">
        <div class="col-md-6">
            <form role="form">
                <!--<div class="form-group">-->
                <!--<label for="themes">Select themes</label>-->
                <!--<select multiple class="form-control" id="themes" name="themes">-->
                <!--<option>World</option>-->
                <!--<option>Politic</option>-->
                <!--<option>Study</option>-->
                <!--<option>Space</option>-->
                <!--<option>Tim_baton</option>-->
                <!--</select>-->
                <!--</div>-->


                <!--<div class="form-group">-->
                <!--<label for="curTopics">Current themes topics</label>-->
                <!--<select multiple class="form-control" id="curTopics" name="curTopics">-->
                <!--<option>-->
                <!--<a href="vk.com/tim_baron">A new animal founded!</a>-->
                <!--</option>-->
                <!--<option>Vova is a gay!!</option>-->
                <!--<option>Timur is the boss!!</option>-->
                <!--</select>-->
                <!--</div>-->
            </form>
        </div>

        <div class="col-md-6">
            <form role="form" method="post">
                <div class="form-group">
                    <label for="title">Title</label>
                    <textarea required="required" class="form-control" id="title" rows="3" placeholder="Title" name="title">
                    </textarea>
                </div>

                <div class="form-group">
                    <label for="text">Text</label>
                    <textarea class="form-control" required="required" id="text" rows="5" placeholder="Text" name="text">
                    </textarea>
                </div>

                <#--<div class="form-group">
                    <label for="attach">Attach images</label>
                    <input type="file" class="form-control-file" id="attach" name="attach">
                </div>-->
                
                <label for="category">Pick a category</label>
                <div class="form-group" id="category">
                    <select class="form-control" name="filter" id="filter">
                        <option name="default" value="1">No Category</option>
                        <option name="politics" value="2">Politics</option>
                        <option name="nature" value="3">Nature</option>
                        <option name="celebrities" value="4">Celebrities</option>
                    </select>
                </div>
                <input type="submit" class="btn btn-primary btn-block" value="Send" id="btn_register">

            </form>
        </div>
    </div>
</div>
</body>
</html>