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
<body class="bg-secondary">
	<div class="container">
		<div class="row mt-5 mb-2">
			<div class="col-sm p-0">
				<s:form action="/filtrarExamesFuncionario.action">
					<div class="input-group">
						<span class="input-group-text"> <strong><s:text
									name="label.buscar.por" /></strong>
						</span>
						<s:select cssClass="form-select" name="filtrar.opcoesCombo"
							list="listaOpcoesCombo" headerKey="" headerValue="Escolha..."
							listKey="%{codigo}" listValueKey="%{descricao}"
							value="filtrar.opcoesCombo.codigo" />

						<s:textfield cssClass="form-control" id="nome"
							name="filtrar.valorBusca" />
						<button class="btn btn-primary" type="submit">
							<s:text name="label.pesquisar" />
						</button>
					</div>
				</s:form>
			</div>
		</div>

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
					<s:iterator value="examesFuncionario">
						<tr>
							<td>${rowid}</td>
							<td>${codFuncionario.nome}</td>
							<td>${codExame.nome}</td>
							<td>${dataRealizacao}</td>
							<td class="text-end">
								<s:url action="atualizarExamesFuncionario" var="atualizar">
									<s:param name="examesFuncionarioVo.rowid" value="rowid"></s:param>
								</s:url> 
								<a href="${atualizar}" class="btn btn-warning text-white">
									<s:text name="label.editar" />
								</a> 
							
								<s:url action="excluirExamesFuncionario" var="excluir">
									<s:param name="examesFuncionarioVo.rowid" value="rowid"></s:param>
								</s:url> 
								<a href="${excluir}" class="btn btn-danger text-white">
									<s:text name="label.excluir" />
								</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>

				<tfoot class="table-secondary">
					<tr>
						<td colspan="3">
							<s:url action="novoExames" var="novo" /> 
							<a href="${novo}" class="btn btn-success"> 
								<s:text name="label.novo" />
							</a>
							
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

		<div class="row"></div>
	</div>

	<div class="modal fade" id="confirmarExclusao"
		data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">
						<s:text name="label.modal.titulo" />
					</h5>

					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>

				<s:form action="/filtrarExames.action">
					<div class="input-group">
						<span class="input-group-text"> <strong><s:text
									name="label.buscar.por" /></strong>
						</span>
						<s:select cssClass="form-select" name="filtrar.opcoesCombo"
							list="listaOpcoesCombo" headerKey="" headerValue="Escolha..."
							listKey="%{codigo}" listValueKey="%{descricao}"
							value="filtrar.opcoesCombo.codigo" />

						<s:textfield cssClass="form-control" id="nome"
							name="filtrar.valorBusca" />
						<button class="btn btn-primary" type="submit">
							<s:text name="label.pesquisar" />
						</button>
					</div>
				</s:form>
			</div>
		</div>
	</div>

	<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>