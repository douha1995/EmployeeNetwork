<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert new Employee</title>
<script language="JavaScript" type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script language="JavaScript" type="text/javascript">

function getFieldValue(fieldName) {
	return $('input[name=' + fieldName + ']').val().trim();
}

function validate(fields) {
	var msg = [];
	for (var field in fields) {
		var f = fields[field];
		var curValue = getFieldValue(field);
		
		var isRequired = (f.isReq != undefined);
		var isMinMaxLengthDefined = (f.minLength != undefined && f.maxLength != undefined);
		var isMinMaxValueDefined = (f.minValue != undefined && f.maxValue != undefined);
		var isRegexDefined = (f.regex != undefined && f.regexMsg != undefined);
		
		if (isRequired) {
			if (curValue.length == 0) {
				msg.push('The field ' + f.name + " cannot be empty!");
				continue;
			}
		}
		if (isMinMaxLengthDefined) {
			if (curValue.length < f.minLength) {
				msg.push('The field ' + f.name + ' has to be at least ' + f.minLength + ' characters long!');
				continue;
			}
			else if (curValue.length > f.maxLength) {
				msg.push('The field ' + f.name + ' can be at most ' + f.maxLength + ' characters long!');
				continue;
			}
		}
		if (isMinMaxValueDefined) {
			if (curValue < f.minValue) {
				msg.push('The field ' + f.name + ' cannot contain value less than ' + f.minValue);
				continue;
			}
			else if (curValue > f.maxValue) {
				msg.push('The field ' + f.name + ' cannot contain value greater than ' + f.maxValue);
				continue;
			}
		}
		if (isRegexDefined) {
			if (!curValue.match(f.regex)) {
				msg.push('The field ' + f.name + f.regexMsg);
				continue;
			}
		}
	}
	
	if (msg.length > 0)
		return 'Cannot submit data!\n\n' + msg.join('\n');
	else
		return true;
}

function validateInsertEmployee() {
	var lettersOnly = /[^\s\d\W]/i;
	var lettersOnlyMsg = ' is allowed to contain letters only!';
	var fields = {
			firstName: { isReq:true, minLength:3, maxLength:45, regex:lettersOnly, regexMsg:lettersOnlyMsg, name:'First name' }, 
			lastName: { isReq:true, minLength:3, maxLength:45, regex:lettersOnly, regexMsg:lettersOnlyMsg, name:'Last name' }, 
			middleInitial: { isReq:true, minLength:1, maxLength:1, regex:lettersOnly, regexMsg:lettersOnlyMsg, name:'Middle initial' }, 
			level: { isReq:true, minLength:3, maxLength:45, regex:lettersOnly, regexMsg:lettersOnlyMsg, name:'Level' }, 
			workforce: { isReq:true, minLength:3, maxLength:45, regex:lettersOnly, regexMsg:lettersOnlyMsg, name:'Workforce' },
			enterpriceID: { isReq:true, minLength:3, maxLength:45, regex:lettersOnly, regexMsg:lettersOnlyMsg, name:'Enterprise ID' }
			};
	
	var msg = validate(fields);
	if (msg != true) alert(msg);
	return (msg == true);
}

function validateInsertEmployeeSkills() {
	var digitsOnly = /^\d{1}$/;
	var digitsOnlyMsg = ' can contain only digits!';
	var fields = { 
			rating: { isReq:true, minLength:1, maxLength:1, regex:digitsOnly, regexMsg:digitsOnlyMsg, name:'Rating', minValue:1, maxValue:5 }
			};
	
	var msg = validate(fields);
	if (msg != true) alert(msg);
	return (msg == true);
}

function validateInsertEmployeeProjects() {
	var dateOnly = /^\d{4}-\d{2}-\d{2}$/;
	var dateOnlyMsg = ' does not contain date in format yyyy-mm-dd';
	var fields = { 
			employee_role: { isReq:true, minLength:0, maxLength:255, name:'Employee role' },
			start_date: { isReq:true, name:'Start date', regex:dateOnly, regexMsg:dateOnlyMsg },
			end_date: { isReq:true, name:'End date', regex:dateOnly, regexMsg:dateOnlyMsg }
			};
	
	var msg = validate(fields);
	
	var startDate = new Date(getFieldValue('start_date'));
	var endDate = new Date(getFieldValue('end_date'));
	if (!isNaN(startDate.getTime()) && !isNaN(endDate.getTime())) {
		if (endDate < startDate) {
			if (msg == true) {
				msg = 'Cannot submit data!\n\nStart date has to be earlier than end date!';
			}
			else {
				msg = msg + '\nStart date has to be earlier than end date!';
			}
		}
	}
	
	if (msg != true) alert(msg);
	return (msg == true);
}

function validateInsertProject() {
	var atLeastOneChar = /[a-z]+/i;
	var atLeastOneCharMsg = ' has to contain at least one letter!';
	var fields = { 
			nameP: { isReq:true, minLength:3, maxLength:45, regex:atLeastOneChar, regexMsg:atLeastOneCharMsg, name:'Name' },
			client: { isReq:true, minLength:3, maxLength:45, regex:atLeastOneChar, regexMsg:atLeastOneCharMsg, name:'Client' }
			};
	
	var msg = validate(fields);
	if (msg != true) alert(msg);
	return (msg == true);
}

function validateInsertSkill() {
	var atLeastOneChar = /[a-z]+/i;
	var atLeastOneCharMsg = ' has to contain at least one letter!';
	var fields = { 
			nameS: { isReq:true, minLength:3, maxLength:45, regex:atLeastOneChar, regexMsg:atLeastOneCharMsg, name:'Name' }
			};
	
	var msg = validate(fields);
	if (msg != true) alert(msg);
	return (msg == true);
}
</script>

</head>
<body>
<hr>
<p>Inserting new employee</p>
<hr>

<form name="input" action="/empdb/insert/insertEmployee.htm" method="POST" onsubmit="return validateInsertEmployee()" >
<b>Insert Employees</b>
<table>
	<tr><td>First Name</td><td><input name="firstName"/></td></tr>
	<tr><td>Last Name</td><td><input name="lastName"/></td></tr>
	<tr><td>Middle Initial</td><td><input name="middleInitial"/></td></tr>
	<tr><td>Level</td><td><input name="level"/></td></tr>
	<tr><td>Work Force</td><td><input name="workforce"/></td></tr>
	<tr><td>Enterprise ID</td><td><input name="enterpriceID"/></td>
	<td><input type="submit" name="action" value="Insert employee" ></td></tr>
</table>
<hr>
</form>
<form name="input2" action="/empdb/insert/insertEmployee.htm" method="POST" onsubmit="return validateInsertEmployeeSkills()" >
<b>Insert employee's skill(s)</b>
<table>
	<tr><td>Employee</td><td>
	<select name="employee_idS">
		<c:forEach var="employee" items="${employeesList}">
			<option value="${employee.ID}">${employee.firstName} 
				${employee.middleInitial}. ${employee.lastName}</option>
		</c:forEach>
	</select>
	</td></tr>
	<tr><td>Skill</td><td>
	<select name="skill_id">
		<c:forEach var="skill" items="${skillList}">
			<option value="${skill.ID}">${skill.name}</option>
		</c:forEach>
	</select>
	</td></tr>
	<tr><td>Rating</td><td><input name="rating"/></td>
	<td><input type="submit" name="action" value="Insert employee's skill" ></td></tr>
</table>
</form>
<hr>
<form name="input3" action="/empdb/insert/insertEmployee.htm" method="POST" onsubmit="return validateInsertEmployeeProjects()" >
<b>Insert employee's project(s)</b>
<table>
	<tr><td>Employee</td><td>
	<select name="employee_idP">
		<c:forEach var="employee" items="${employeesList}">
			<option value="${employee.ID}">${employee.firstName} 
				${employee.middleInitial}. ${employee.lastName}</option>
		</c:forEach>
	</select>
	</td></tr>
	<tr><td>Project</td><td>
	<select name="project_id">
		<c:forEach var="project" items="${projectList}">
			<option value="${project.ID}">${project.name}</option>
		</c:forEach>
	</select>
	</td></tr>
	<tr><td>Employee Role</td><td><input name="employee_role"/></td></tr>
	<tr><td>Start Date</td><td><input name="start_date" value="yyyy-mm-dd"/></td></tr>
	<tr><td>End Date</td><td><input name="end_date" value="yyyy-mm-dd"/></td>
	<td><input type="submit" name="action" value="Insert employee's project" ></td></tr>
</table>
</form>
<hr>
<p>Insert new projects or skills</p>
<hr>
<b>Insert Projects</b>
<form name="input4" action="/empdb/insert/insertEmployee.htm" method="POST" onsubmit="return validateInsertProject()" >
<table>
	<tr><td>Name</td><td><input name="nameP"/></td></tr>
	<tr><td>Description</td><td><input name="descriptionP"/></td></tr>
	<tr><td>Client</td><td><input name="client"/></td>
	<td><input type="submit" name="action" value="Insert project" ></td></tr>
</table>
</form>
<hr>
<b>Insert Skills</b>
<form name="input5" action="/empdb/insert/insertEmployee.htm" method="POST" onsubmit="return validateInsertSkill()" >
<table>
	<tr><td>Name</td><td><input name="nameS"/></td></tr>
	<tr><td>Description</td><td><input name="descriptionS"/></td>
	<td><input type="submit" name="action" value="Insert skill" ></td></tr>
</table>
</form>
<hr>

</body>
</html>