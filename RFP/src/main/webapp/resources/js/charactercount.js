/*$(document)
		.ready(
				function() {

					var editAbstract = CKEDITOR.instances.summary;

					editAbstract.on("key", function(e) {

						var maxLength = e.editor.config.maxlength;

						e.editor.document.on("keyup", function() {
							KeyUp(e.editor, maxLength, "letterCount");
						});
						e.editor.document.on("paste", function() {
							KeyUp(e.editor, maxLength, "letterCount");
						});
						e.editor.document.on("blur", function() {
							KeyUp(e.editor, maxLength, "letterCount");
						});
					}, editAbstract.element.$);

					// function to handle the count check
					function KeyUp(editorID, maxLimit, infoID) {

						// If you want it to count all html code then just
						// remove everything from and after '.replace...'
						var text = editorID.getData().replace(
								/<("[^"]*"|'[^']*'|[^'">])*>/gi, '').replace(
								/^\s+|\s+$/g, '');
						$("#" + infoID).text(text.length);

						if (text.length > maxLimit) {
							alert("You cannot have more than " + maxLimit
									+ " characters");
							editorID.setData(text.substr(0, maxLimit));
							editor.cancel();
						} else if (text.length == maxLimit - 1) {
							alert("WARNING:\nYou are one character away from your limit.\nIf you continue you could lose any formatting");
							editor.cancel();
						}
					}

				});*/
/*<script type="text/javascript">
    $(function () {
        var myEditor = $('#myTextEditor1');
        myEditor.ckeditor({ 
        height: 200, 
        extraPlugins: 'charcount', 
        maxLength: 10, 
        toolbar: 'TinyBare', 
        toolbar_TinyBare: [
             ['Bold','Italic','Underline'],
             ['Undo','Redo'],['Cut','Copy','Paste'],
             ['NumberedList','BulletedList','Table'],['CharCount']
        ] 
        }).ckeditor().editor.on('key', function(obj) {
            if (obj.data.keyCode === 8 || obj.data.keyCode === 46) {
                return true;
            }
            if (myEditor.ckeditor().editor.document.getBody().getText().length >= 10) {
                alert('No more characters possible');
                return false;
            }

        });
    });
</script>*/