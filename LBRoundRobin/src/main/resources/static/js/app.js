var app = (function () {
    var url = window.location.href+'results';
    function addMessage(){
        var m =document.getElementById("message").value;
        console.log(m)
        console.log("URL "+ url)
        axios.post(url,m)
            .then(res => {
                getMessages();
            })
    }
    function getMessages(){

        $("#Table > tbody").empty();
        axios.get(url).then(res=>{
            console.log(res.data)
            res.data.map(message=>{
                console.log(message)
                $("#table > tbody").append(
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