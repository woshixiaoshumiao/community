function postComment() {
    const questionId = $('#question_id').val();
    const commentContent = $("#comment_content").val();
    $.ajax({
            type: "POST",
            url: "/comment",
            contentType: "application/json",
            data: JSON.stringify({
                    "parentId": questionId,
                    "content": commentContent,
                    "type": 1
                }
            ),
            success: function (response) {
                if (response.code == 200) {
                    $("#comment").hide();
                }else{
                    if(response.code == 2003){
                        const isAccept = confirm(response.message);
                        if(isAccept){
                            window.open("https://github.com/login/oauth/authorize?client_id=Iv1.8e672d03e6a8f4e9&redirect_url=http://localhost:8080/callback&scope=user&state=1");
                            window.localStorage.setItem("closeable", "true");
                        }
                    }else{
                        alert(response.message);
                    }
                }
                console.log(response);
            },
            dataType: "json"
        }
    )
    console.log(commentContent);
    console.log(questionId);
}