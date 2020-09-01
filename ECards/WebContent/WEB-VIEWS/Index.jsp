<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<!DOCTYPE html>
<html>
      
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/site.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="application/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Cards</title>
</head>
<body>
<div class="container" style="width: 100%;margin: 0 auto;padding: 20px;background: linear-gradient(90deg,#ff8a00,#e52e71);">
    <nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="/">Cards</a>
	    </div>
	    <ul class="nav navbar-nav">
	      <c:if test ="${!isAdmin}">
		       <li><a data-toggle="modal" data-target="#createCardModal">Add Card</a></li>
	      </c:if>
	    </ul>
	    <form class="navbar-form navbar-left" method="POST" action="cards">
	      <div class="form-group">
	        <input type="text" name="cardNum" id="cardNum" class="form-control" placeholder="Search" value="${searchNum}">
	      </div>
	      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	      <button type="submit" class="btn btn-default"><i class="glyphicon glyphicon-search"></i></button>
	      
	    </form>
	    <ul class="nav navbar-nav navbar-right">
            <li>
		        <c:url var="logoutUrl" value="/"/>
			    <form action="${logoutUrl}" method="post">
			      <input type="submit" class="glyphicon glyphicon-log-in" value="Logout"/>
			      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			    </form>
	        </li>
       </ul>
	  </div>
	</nav>
</div>

<table class="table table-hover" border="1">
	<thead>
	    <tr>
			<th style="text-align: center">Card Holder Name</th>
			<th style="text-align: center">Card Number</th>
			<th style="text-align: center">Expiry Date</th>
		  <c:if test ="${!isAdmin}">	
			<th style="text-align: center"></th>
		  </c:if>
		<tr/>
    </thead>
    
    <tbody>
	    <c:forEach items="${pageCards.content}" var="card">
	    <tr style="text-align: center">
			<td>${card.cardHolderName}</td>
			<td>${card.cardNumber}</td>
			<td>${card.cardExpiryDate}</td>
			<c:if test ="${!isAdmin}">
				<td>
				    <a href="api/card/${card.id}" class="btn btn-success btn-sm editButton" >Edit</i></a>
				    <a href="api/card/${card.id}" class="btn btn-danger btn-sm deleteButton" data-toggle="modal" >Delete</a>
				</td>
			</c:if>
			<td>
				
			</td>
		<tr/>
	    </c:forEach>
	    <tr style="text-align: center">
	       <td colspan="5">
	          <div class="pagination-div">
				  <c:if test = "${numOfPages > 1}">  
					    <c:if test = "${pageCards.hasPrevious()}">
	         				<a href="?pageNum=${pageCards.number-1}&pageSize=${pageCards.size}">Previous</a>
	                    </c:if>
	                    <c:forEach begin="0" end="${(numOfPages-1) > 0 ? (numOfPages-1) : 0 }" varStatus="loop" var="pageNumber">
					        <c:choose>
						        <c:when test = "${pageCards.number == pageNumber}">
						             <span class="selected">${pageNumber}</span>
						        </c:when>
		    					<c:otherwise>
		    					     <a href="?pageNum=${pageNumber}&pageSize=${pageCards.size}">${pageNumber}</a>
		   						</c:otherwise>
	   						</c:choose>
						</c:forEach>
					    <c:if test = "${pageCards.hasNext()}">
					        <a href="?pageNum=${pageCards.number+1}&pageSize=${pageCards.size}">Next</a>
					    </c:if>
				   </c:if> 
			  </div>
	       </td>
	    </tr>
    </tbody>
</table>

<c:if test ="${!isAdmin}">

<!--Create Card Modal Popup-->
<div id="createCardModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <form:form id="createCardForm" action="createCard" modelAttribute="createdcard">
	    <!-- Modal content-->
	    <div class="modal-content">
	          <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Create Card</h4>
		      </div>
		      <div class="modal-body">
		        
		        <div class="form-group">
		          <label for="cardHolderName" class="col-form-label">Card Holder's Name :</label>
		          <form:input id="cardHolderName" path="cardHolderName" class="form-control"/> <br/>
                </div>
		        
		        <div class="form-group">
		          <label for="cardNumber" class="col-form-label">Card Number        :</label>
		          <form:input id="cardNumber" path="cardNumber" class="form-control"/> <br/>
                </div>
		        
		        <div class="form-group">
		          <label for="cardExpiryDate" class="col-form-label">Expiry Date        :</label>
		          <form:input id="cardExpiryDate" path="cardExpiryDate" class="form-control"/> <br/>
                </div>
                    
		      </div>
		      <div class="modal-footer">
		        <a href="" class="btn btn-primary createSaveButton" value="Submit">Save changes</a> 
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
	    </div>
   </form:form>
  </div>
</div>
</c:if>

<c:if test ="${!isAdmin}">
<!--Edit Card Modal Popup-->
<div id="editCardModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
 <form:form id="editCardForm" action="editCard" modelAttribute="editedcard" method="POST">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Edit Card</h4>
      </div>
      <div class="modal-body">
                <div class="form-group" hidden="true">
		          <label for="idEdit" class="col-form-label">Card Holder's Name :</label>
		          <form:input id="idEdit" path="id" class="form-control"/> <br/>
                </div>
      
      			<div class="form-group" hidden="true">
		          <label for="userNameEdit" class="col-form-label">Card Holder's Name :</label>
		          <form:input id="userNameEdit" path="userName" class="form-control" readonly="true" /> <br/>
                </div>
      
                <div class="form-group">
		          <label for="cardHolderNameEdit" class="col-form-label">Card Holder's Name :</label>
		          <form:input id="cardHolderNameEdit" path="cardHolderName" class="form-control" readonly="true" /> <br/>
                </div>
		        
		        <div class="form-group">
		          <label for="cardNumberEdit" class="col-form-label">Card Number        :</label>
		          <form:input id="cardNumberEdit" path="cardNumber" class="form-control" readonly="true" /> <br/>
                </div>
		        
		        <div class="form-group">
		          <label for="cardExpiryDateEdit" class="col-form-label">Expiry Date        :</label>
		          <form:input id="cardExpiryDateEdit" path="cardExpiryDate" class="form-control"/> <br/>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
       </div>
       <div class="modal-footer">
        <a href="" class="btn btn-primary editSaveButton" value="Submit">Save changes</a> 
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
       </div>
     </div>
 </form:form> 
  </div>
</div>
</c:if>

<c:if test ="${!isAdmin}">
<!--Delete Card Modal Popup-->
<div id="deleteCardModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
<form:form id="deleteCardForm" action="deleteCard" modelAttribute="deletededcard" method="POST">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Delete Card</h4>
      </div>
      <div class="modal-body">
                <div class="form-group" hidden="true">
		          <form:input id="idDelete" path="id" class="form-control"/> <br/>
                </div>
      
                <div class="form-group" hidden="true">
		          <form:input id="cardHolderNameDelete" path="cardHolderName" class="form-control" /> <br/>
                </div>
		        
		        <div class="form-group" hidden="true">
		          <form:input id="cardNumberDelete" path="cardNumber" class="form-control" /> <br/>
                </div>
		        
		        <div class="form-group" hidden="true">
		          <form:input id="cardExpiryDateDelete" path="cardExpiryDate" class="form-control"/> <br/>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <p>Confirm delete card?</p>
      </div>
      <div class="modal-footer">
        <a href="" class="btn btn-primary deleteSaveButton">Delete</a> 
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
</form:form>
  </div>
</div>
</c:if>

</body>
</html>