var app = (function () {
var entrada =0;
    function addMessage(){
        var m =document.getElementById("message").value;
        console.log(m)
        axios.post("/insert",m)
            .then(res => {
                $("#Table > tbody").empty();
                getMessages();
            })
    }
    function getMessages(){
        $("#Table > tbody").empty();
        axios.get("/results").then(res=>{
            res.data.map(message=>{
                console.log(message)
                $("#Table > tbody").append(
                    "<tr>" +
                    "<td>" + message.content + "</td>" +
                    "<td>" + message.date + "</td> " +
                    "</tr>"
                );
            })
        })
    }
    return {
        addMessage:addMessage,
        getMessages:getMessages
    };
})();