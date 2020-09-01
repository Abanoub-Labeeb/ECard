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

<a href="card" class="btn btn-info btn-sm testAdd">Add Card</a>
<a href="card" class="btn btn-info btn-sm testEdit">Edit Card</a>
<a href="card" class="btn btn-info btn-sm testDelete">Delete Card</a>

<c:url var="logoutUrl" value="/"/>
<form action="${logoutUrl}" method="post">
    <input type="submit" value="Logout"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<br/>
<a href="${pageContext.request.contextPath}/addcard">Add Card</a>
<button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#createCardModal">Add Card</button>
<br/>

<table class="table table-hover" border="1">
	<thead>
	    <tr>
			<th style="text-align: center">Card Holder Name</th>
			<th style="text-align: center">Card Number</th>
			<th style="text-align: center">Expiry Date</th>
			<th style="text-align: center"></th>
			<th style="text-align: center"></th>
		<tr/>
    </thead>
    
    <tbody>
	    <c:forEach items="${pageCards.content}" var="card">
	    <tr style="text-align: center">
			<td>${card.cardHolderName}</td>
			<td>${card.cardNumber}</td>
			<td>${card.cardExpiryDate}</td>
			<td>
			    <a href="api/card/${card.id}" class="btn btn-info btn-sm editButton">Edit Card</a>
			</td>
			<td>
				<a href="api/card/${card.id}" class="btn btn-info btn-sm deleteButton" data-toggle="modal">Delete Card</a>
			</td>
		<tr/>
	    </c:forEach>
	    <tr style="text-align: center">
	       <td colspan="5">
	          <div class="pagination-div">
				    <c:if test = "${pageCards.hasPrevious()}">
         				<a href="cards?pageNum=${pageCards.number-1}&pageSize=${pageCards.size}">Previous</a>
                    </c:if>
				    <c:forEach begin="0" end="${numOfPages-1}" varStatus="loop" var="pageNumber">
				        <c:choose>
					        <c:when test = "${pageCards.number == pageNumber}">
					             <span class="selected">${pageNumber}</span>
					        </c:when>
	    					<c:otherwise>
	    					     <a href="cards?pageNum=${pageNumber}&pageSize=${pageCards.size}">${pageNumber}</a>
	   						</c:otherwise>
   						</c:choose>
					</c:forEach>
				    <c:if test = "${pageCards.hasNext()}">
				        <a href="cards?pageNum=${pageCards.number+1}&pageSize=${pageCards.size}">Next</a>
				    </c:if>
			  </div>
	       </td>
	    </tr>
    </tbody>
</table>


<!--Create Card Modal Popup-->
<div id="createCardModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
   <!--  <form:form action="createCard" modelAttribute="createdcard">  -->
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
                  <form:errors path="cardHolderName" cssStyle="color:red;"></form:errors>
		        </div>
		        
		        <div class="form-group">
		          <label for="cardNumber" class="col-form-label">Card Number        :</label>
		          <form:input id="cardNumber" path="cardNumber" class="form-control"/> <br/>
                  <form:errors path="cardNumber" cssStyle="color:red;"></form:errors>
		        </div>
		        
		        <div class="form-group">
		          <label for="cardExpiryDate" class="col-form-label">Expiry Date        :</label>
		          <form:input id="cardExpiryDate" path="cardExpiryDate" class="form-control"/> <br/>
                  <form:errors path="cardExpiryDate" cssStyle="color:red;"></form:errors>
		        </div>
                    
		      </div>
		      <div class="modal-footer">
		        <a href="" class="btn btn-primary createSaveButton" value="Submit">Save changes</a> 
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
	    </div>
   <!--  </form:form>  -->
  </div>
</div>

<!--Edit Card Modal Popup-->
<div id="editCardModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
 <!--  <form:form action="editCard" modelAttribute="editedcard">  -->
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
                  <form:errors path="id" cssStyle="color:red;"></form:errors>
		        </div>
      
                <div class="form-group">
		          <label for="cardHolderNameEdit" class="col-form-label">Card Holder's Name :</label>
		          <form:input id="cardHolderNameEdit" path="cardHolderName" class="form-control" disabled="true" /> <br/>
                  <form:errors path="cardHolderName" cssStyle="color:red;"></form:errors>
		        </div>
		        
		        <div class="form-group">
		          <label for="cardNumberEdit" class="col-form-label">Card Number        :</label>
		          <form:input id="cardNumberEdit" path="cardNumber" class="form-control" disabled="true" /> <br/>
                  <form:errors path="cardNumber" cssStyle="color:red;"></form:errors>
		        </div>
		        
		        <div class="form-group">
		          <label for="cardExpiryDateEdit" class="col-form-label">Expiry Date        :</label>
		          <form:input id="cardExpiryDateEdit" path="cardExpiryDate" class="form-control"/> <br/>
                  <form:errors path="cardExpiryDate" cssStyle="color:red;"></form:errors>
		        </div>
       </div>
       <div class="modal-footer">
        <a href="" class="btn btn-primary editSaveButton" value="Submit">Save changes</a> 
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
       </div>
     </div>
<!-- </form:form> -->
  </div>
</div>

<!--Delete Card Modal Popup-->
<div id="deleteCardModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
<!-- <form:form action="editCard" modelAttribute="deletededcard">  -->
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Edit Card</h4>
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
        <p>Confirm delete card?</p>
      </div>
      <div class="modal-footer">
        <a href="" class="btn btn-primary deleteSaveButton">Delete</a> 
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  <!--  </form:form>  -->
  </div>
</div>


</body>
</html>