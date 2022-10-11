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
                    alert(response.message);
                }
                console.log(response);
            },
            dataType: "json"
        }
    )
    console.log(commentContent);
    console.log(questionId);
}