<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
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
                <li><a href="/hotel-management-system/form.html" >Zglos pokoj</a></li>
            </ul>
          </div>
        </div>
    <div class='container' style="margin-top: 100px">
        <table class="table">
          <thead>
            <tr>
              <th>#</th>
              <th>Numer pokoju</th>
              <th>Pietro</th>
              <th>Kategoria</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach items="${history}" var="item">
            <tr>
              <td><c:out value="${item['id']}" /></td>
              <td><c:out value="${item['number']}" /></td>
              <td><c:out value="${item['floor']}" /></td>
              <td><c:out value="${item['category']}" /></td>
              <td><c:out value="${item['status']}" /></td>
            </tr>
          </c:forEach>
          </tbody>
      </table>
    </div>
    </body>
</html>
