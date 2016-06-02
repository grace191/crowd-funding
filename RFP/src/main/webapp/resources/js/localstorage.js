
$(document)
.ready(
		function() {
			$('#title_input').change(
					function() {
						localStorage.setItem("title_input", $(
						'#title_input').val());

					});
			if (localStorage.getItem("title_input") !== null) {
				$("#title_input")
				.val(
						localStorage
						.getItem("title_input"));
			}




			$('#changePlaceholder').change(
					function() {
						localStorage.setItem("changePlaceholder", $(
						'#changePlaceholder').val());

					});

			if (localStorage.getItem("changePlaceholder") !== null) {
				$("#changePlaceholder")
				.val(
						localStorage
						.getItem("changePlaceholder"));
			}





			/*		$('#successInput').change(
					function() {
						localStorage.setItem("successInput", $(
						'#successInput').val());

					});
			$("#successInput")
			.val(
					localStorage
					.getItem("successInput"));*/


			CKEDITOR.instances.summary
			.on(
					'change',
					function() {
						//alert("changed");
						var data = CKEDITOR.instances.summary
						.getData();
						//console.log(data);
						localStorage
						.setItem(
								"summary",
								data);
					});

			$(CKEDITOR.instances.summary
					.setData(localStorage
							.getItem("summary")));



			CKEDITOR.instances.description

			.on(

					'change',

					function() {

						//alert("changed");

						var data = CKEDITOR.instances.description

						.getData();

						//console.log(data);

						localStorage

						.setItem(

								"description",

								data);

					});



			$(CKEDITOR.instances.description

					.setData(localStorage

							.getItem("description")));


			$("#amountSelect")

			.change(

					function() {
						var amount = $( "#amountSelect" ).val();
						/*var span = $("#successInput").html();*/
						/*console.log(span);*/




						if ($(

						"#amountSelect option:selected")

						.text() === 'Total Amount Pledged') {


							$(

							"#successInput")

							.append(

							'<span class="input-group-addon" style="padding:10px 5px 10px 5px">$</span>');

							$(

							'#changePlaceholder')

							.attr(

									"placeholder",

							"Total Amount Pledged");
							localStorage.setItem("amountSelect", amount);
							/*localStorage.setItem("successInput", span);*/

						} else {


							var spans = $('.input-group-addon');

							spans

							.hide();

							$(

							'#changePlaceholder')

							.attr(

									"placeholder",

							"Number of Participants");
							localStorage.setItem("amountSelect", amount);
							/*localStorage.setItem("successInput", span);*/

						}

					});






			if (localStorage.getItem("amountSelect") !== null) {
				$( "#amountSelect" ).val(localStorage
						.getItem("amountSelect"));
			}
			/*$("#successInput")
			.html(
					localStorage
					.getItem("successInput"));*/

		});
//build table
/*function build(attachments, data) {
	var head = '', cols = '';
	for (j = 0; j < data.length; j++) {
		var rows = '';
		for (i = 1; i < 2; i++) {

			rows += '<td class="col-md-6">' + data[j]['filename']
			+ '</td>';

		}
		var id = data[j]['fileId'];
		var link = "<c:url value='/download/attachment/"+id"'/>";
		rows += '<td class="col-md-3">'
			+ '<a href=\''+link+'\' value="download" class="btn btn-success custom-width"/>'
			+ '</td>' 
			+ '<td class="col-md-3">'
			+ '<input type="button" onClick="deleteContact(\''
			+ id + '\')" value="Delete" class="btn btn-success custom-width"/>'
			+ '</td>';
		cols += '<tr>' + rows + '</tr>';
	}

	$("#attachments").empty();
	$("#attachments").html(
			'<table class="table table-hover">'
			+ '<tbody>' + cols + '</tbody>'
			+ '</table>');
}*/

