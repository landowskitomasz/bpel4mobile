<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <link href="web-resources/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="web-resources/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
          <div class="container">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="#">System zarzadzania hotelem</a>
            </div>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="/hotel-management-system/list.html" >Historia sprzatania</a></li>
            </ul>
          </div>
        </div>
    <div class='container' style="margin-top: 100px">
        <form class="form-horizontal" method="POST" action="/hotel-management-system/startProcess.html">
        <fieldset>

        <!-- Form Name -->
        <legend>Pokój do posprzatania</legend>

        <!-- Text input-->
        <div class="form-group">
          <label class="col-md-4 control-label" for="number">Numer pokoju</label>
          <div class="col-md-4">
          <input id="number" name="number" type="text" placeholder="numer" class="form-control input-md" required="">

          </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
          <label class="col-md-4 control-label" for="floor">Pietro</label>
          <div class="col-md-4">
            <select id="floor" name="floor" class="form-control">
              <option value="1">Pierwsze</option>
              <option value="2">Drugie</option>
              <option value="3">Trzecie</option>
            </select>
          </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
          <label class="col-md-4 control-label" for="category">Kategoria</label>
          <div class="col-md-4">
            <select id="category" name="category" class="form-control">
              <option value="apartment">Apartament</option>
              <option value="standard">Standard</option>
            </select>
          </div>
        </div>

        <!-- Button -->
        <div class="form-group">
          <label class="col-md-4 control-label" for="submit"></label>
          <div class="col-md-4">
            <button id="submit" name="submit" class="btn btn-primary">Rozpocznij</button>
          </div>
        </div>

        </fieldset>
        </form>

    </div>
    </body>
</html>
