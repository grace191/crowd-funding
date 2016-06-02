$(document)
    .ready(function() {
    $('#title_input').change(function() {
        localStorage.setItem("title_input", $(
            '#title_input').val());

    });
    $("#title_input")
        .val(
        localStorage
        .getItem("title_input"));

    $('#changePlaceholder').change(function() {
        localStorage.setItem("changePlaceholder", $(
            '#changePlaceholder').val());

    });
    $("#changePlaceholder")
        .val(
        localStorage
        .getItem("changePlaceholder"));

    CKEDITOR.instances.summary
        .on(
        'change', function() {
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



    CKEDITOR.instances.description.on(

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

    .change(function() {
        var amount = $("#amountSelect").val();





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

        }

    });

    $("#amountSelect").val(localStorage
        .getItem("amountSelect"));



});