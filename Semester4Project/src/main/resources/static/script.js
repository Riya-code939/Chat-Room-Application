var stompCient=null

function sendMessage()
{
    let jsonOb={                                         //initializing message as JSON object
    name:localStorage.getItem("name"),                   //will give name
    content:$("#message-value").val()                   //will give message
    }
    stompClient.send("/app/message",{},JSON.stringify(jsonOb));
}
function connect()
{
    let socket = new SockJS("/server1")
    stompClient= Stomp.over(socket)
    stompClient.connect({},function(frame){
        console.log("Connected:" +frame)

        $("#name-from").addClass('d-none')
        $('#chat-room').removeClass('d-none')

        //subscribe-->receive message
        stompClient.subscribe("/topic/return-to",function(response){
           showMessage(JSON.parse(response.body))         //converting body to JSON
        })
    })
}
function showMessage(message)
{
    $("#message-container-table").prepend(`<tr><td><b>${message.name} :</b> ${message.content}</td></tr>`)   //prepend-->message table aa jaega

}
$(document).ready((e)=>{
    $("#login").click(()=>{
        let name=$("#name-value").val()
        localStorage.setItem("name",name)                 //localStorage-->to store name to local storage
        $("#name-title").html(`Welcome, <b>${name} </b>`)    //to display name of person with welcome
        connect();
    })

    //button action for send

    $("#send-btn").click(()=>{
        sendMessage();
    })

    //action on clicking button :logout
    $("#logout").click(()=>{
        //removing name
        localStorage.removeItem("name")

        //disconnecting the server
        if(stompClient!= null)
        {
              //if object exists, then disconnect
              stompClient.disconnect();

              $("#name-from").removeClass('d-none')
              $("#chat-from").addClass('d-none')
              console.log(stompClient)


        }
    })
})