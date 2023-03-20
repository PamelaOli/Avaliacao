<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><s:text name="label.titulo.pagina.consulta" /></title>
<link rel='stylesheet'
	href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
</head>
<body>
	<div class="row">
		<table class="table table-light table-striped align-middle">
			<thead>
				<tr>
					<th><s:text name="label.id" /></th>
					<th><s:text name="label.funcionario" /></th>
					<th><s:text name="label.exame" /></th>
					<th><s:text name="label.dtExame" /></th>
					<th class="text-end mt-5"><s:text name="label.acao" /></th>
					</tr>
			</thead>

			<tbody>
				<s:iterator value="relatorio">
					<tr>
						<td>${rowid}</td>
						<td>${codFuncionario.nome}</td>
						<td>${codExame.nome}</td>
						<td>${dataRealizacao}</td>
					</tr>
					</s:iterator>
				</tbody>

				<tfoot class="table-secondary">
					<tr>
						<td colspan="3">
							<s:url action="todosExames" var="exame" /> 
							<a href="${exame}" class="btn btn-dark"> 
								<s:text name="label.tipo.exame" />
							</a>
				
							<s:url action="todosFuncionarios" var="funcionario" /> 
							<a href="${funcionario}" class="btn btn-dark"> 
								<s:text name="label.funcionario" />
							</a>
							
							<s:url action="mostrarRelatorio" var="relatorio" /> 
							<a href="${relatorio}" class="btn btn-dark"> 
								<s:text name="label.relatorio" />
							</a>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>

</body>
</html>