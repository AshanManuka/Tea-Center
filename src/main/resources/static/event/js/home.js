
$("#clickBtn").click(function(){
   callToBackEnd();
});


function callToBackEnd(){
    $.ajax({
        url: 'http://localhost:8080/hellow',
        method: 'get',
        dataType: "json",
        success: function (resp) {
            alert("awaaaa")
            console.log(resp.data);

            }
    });
}