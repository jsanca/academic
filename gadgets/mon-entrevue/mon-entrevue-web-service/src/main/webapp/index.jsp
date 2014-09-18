<html>
<head>
    <title>List of Api Functions</title>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>


    <script>

        function doAuthentication(authenticationForm) {

            var username;
            var password;
            var url;

            try {

                console.log("Doing authentication...");
                $('#uuid').val('');

                url = authenticationForm.attr('action');
                username = authenticationForm.find('input[name="u"]').val();
                password = authenticationForm.find('input[name="p"]').val();

                url += "?u=" + username + "&p=" + password;

                $.get(url, function (data) {

                    $('#uuid').val(data.uuid);
                }, "json");
            } catch (err) {

                console.log(err.message);
                $('#uuid').val(err.message);
            }
        } // doAuthentication.

        function stillAuthenticated(authenticationForm) {

            var uuid;
            var url;

            try {

                console.log("Doing still authenticated...");

                url = authenticationForm.attr('action');
                uuid = $('#uuid').val();

                url += "?uuid=" + uuid;

                $.get(url, function (data) {

                    alert(data.value);
                }, "json");
            } catch (err) {

                console.log(err.message);
                alert(err.message);
            }
        } // stillAuthenticated.

        function logout(authenticationForm) {

            var uuid;
            var url;

            try {

                console.log("Doing logout...");

                url = authenticationForm.attr('action');
                uuid = $('#uuid').val();

                url += "?uuid=" + uuid;

                $.get(url, function (data) {

                    alert(data.value);
                }, "json");
            } catch (err) {

                console.log(err.message);
                alert(err.message);
            }
        } // logout.

        function loadInterview(loadForm) {

            var interviewId;
            var uuid;
            var url;

            try {

                console.log("Doing load interview...");

                url = loadForm.attr('action');
                interviewId = loadForm.find('input[name="interviewId"]').val();
                uuid = $('#uuid').val();

                if (interviewId) {

                    url += "?uuid=" + uuid + "&id=" + interviewId;

                    $.get(url, function (data) {

                        alert(data.message);
                    }, "json");
                } else {

                    alert("Please add a interview id");
                }
            } catch (err) {

                console.log(err.message);
                $('#uuid').val(err.message);
            }
        } // loadInterview.

        function getInterview(loadForm) {

            var interviewId;
            var uuid;
            var url;

            try {

                console.log("Doing get interview...");

                url = loadForm.attr('action');
                interviewId = loadForm.find('input[name="interviewId"]').val();
                uuid = $('#uuid').val();

                console.log("interviewId: " + interviewId);

                if (interviewId) {

                    url += "?uuid=" + uuid + "&id=" + interviewId;

                    $.get(url, function (data) {

                        console.log(data);
                        $('#interviewResult').text(JSON.stringify(data));
                    }, "json");
                } else {

                    alert("Please add a interview id");
                }
            } catch (err) {

                console.log(err.message);
                $('#uuid').val(err.message);
            }
        } // getInterview.

    </script>
</head>
<body>
<h1>Mon Entrevue</h1>

<div id="authentication">

    <h2>Authentication</h2>

    <br/>
    <strong>
        Input / GET
    </strong>

    <br/>
    <code>
        /mon-entrevue-web-service/auth/entication.json?u={username}&p={password}
    </code>

    <br/>
    <strong>
        Output
    </strong>

    <br/>
    <code>
        {"uudi":"48a94b5f-7e79-4155-9a67-b8002ecf66ab"}
    </code>

    <br/>
    <br/>

    <strong>
        Do a test
    </strong>

    <form name="authenticationForm" id="authenticationForm" method="get"
          action="/mon-entrevue-web-service/auth/entication.json">
        user <input name="u" type="text"/> <br/>
        pass <input name="p" type="password"/> <br/>
        <a href="javascript:void(0);" onclick="doAuthentication($('#authenticationForm')); return false;">
            Do Authentication
        </a> <br/>
    </form>
</div>

<hr/>

<div id="stillAuthenticated">

    <h2>Still Authenticated</h2>

    <br/>
    <strong>
        Input / GET
    </strong>

    <br/>
    <code>
        /mon-entrevue-web-service/auth/still.json?uuid=48a94b5f-7e79-4155-9a67-b8002ecf66ab
    </code>

    <br/>
    <strong>
        Output
    </strong>

    <br/>
    <code>
        {"value":true}
    </code>

    <br/>
    <br/>

    <strong>
        Do a test
    </strong>

    <form name="stillAuthenticationForm" id="stillAuthenticationForm" method="get"
          action="/mon-entrevue-web-service/auth/still.json">
        UUID
        <input name="uuid" id="uuid" type="text"/>
        <br/>
        <a href="javascript:void(0);" onclick="stillAuthenticated($('#stillAuthenticationForm')); return false;">
            Still Authenticated?
        </a>
    </form>
</div>

<hr/>

<div id="logout">

    <h2>Logout</h2>

    <br/>
    <strong>
        Input / GET
    </strong>

    <br/>
    <code>
        /mon-entrevue-web-service/auth/cleanUp.json?uuid=48a94b5f-7e79-4155-9a67-b8002ecf66ab
    </code>

    <br/>
    <strong>
        Output
    </strong>

    <br/>
    <code>
        {"value":true}
    </code>

    <br/>
    <br/>

    <strong>
        Do a test
    </strong>

    <form name="cleanUpAuthenticationForm" id="cleanUpAuthenticationForm" method="get"
          action="/mon-entrevue-web-service/auth/cleanUp.json">
        <a href="javascript:void(0);" onclick="logout($('#cleanUpAuthenticationForm')); return false;">
            Logout
        </a>
    </form>
</div>

<hr/>

<div id="upload">

    <h2>Upload</h2>

    <br/>
    <strong>
        Input / POST
    </strong>

    <br/>
    <code>
        /mon-entrevue-web-service/interview/upload.json
    </code>

    <br/>
    <strong>
        Output
    </strong>

    <br/>
    <code>
        {"value":true}
    </code>

    <br/>
    <br/>

    <strong>
        Do a test
    </strong>

    <form name="uploadForm" id="uploadForm" method="post" enctype="multipart/form-data"
          action="/mon-entrevue-web-service/interview/upload.json">

        Please specify a .Q extension file to upload:<br>
        <input type="file" name="file"  id="file"/>

        <br/>
        <a href="javascript:void(0);" onclick="$('#uploadForm').submit(); return false;">
            Send
        </a>
    </form>
</div>

<hr/>

<div id="loadInterview">

    <h2>Load Interview</h2>

    <br/>
    <strong>
        Input / GET
    </strong>

    <br/>
    <code>
        /mon-entrevue-web-service/interview/load.json?uuid=d7b47cf4-40ea-4eeb-94bc-bf22c7da160e&id=entrevue-test1.q
    </code>

    <br/>
    <strong>
        Output
    </strong>

    <br/>
    <code>
        {

        "message": "Ok"

        }
    </code>

    <br/>
    <br/>

    <strong>
        Do a test
    </strong>

    <form name="loadForm" id="loadForm" method="get"
          action="/mon-entrevue-web-service/interview/load.json">

        Please specify the interview id:<br/>
        <input type="text" name="interviewId"  id="interviewId"/>

        <br/>
        <a href="javascript:void(0);" onclick="loadInterview($('#loadForm')); return false;">
            Load
        </a>
    </form>
</div>

<hr/>

<div id="getInterview">

    <h2>Get Interview</h2>

    <br/>
    <strong>
        Input / GET
    </strong>

    <br/>
    <code>
        /mon-entrevue-web-service/interview/get.json
    </code>

    <br/>
    <strong>
        Output
    </strong>

    <br/>
    <code>
        {"value":true}
    </code>

    <br/>
    <br/>

    <strong>
        Do a test
    </strong>

    <form name="getInterviewForm" id="getInterviewForm" method="get"
          action="/mon-entrevue-web-service/interview/get.json">

        Please specify the interview id:<br/>
        <input type="text" name="interviewId" />

        <br/>
        <a href="javascript:void(0);" onclick="getInterview($('#getInterviewForm')); return false;">
            Get Interview
        </a>

        <br/>
        <textarea id="interviewResult" cols="100" rows="20">
              RESULT HERE
        </textarea>
    </form>
</div>

<hr/>

<div id="getQuestionTypes">

    <h2>Get Question Types</h2>

    <br/>
    <strong>
        Input / GET
    </strong>

    <br/>
    <code>
        /mon-entrevue-web-service/interview/getQuestionTypes.json?uuid=6969f2a5-1f53-478c-869f-46587cf93643
    </code>

    <br/>
    <strong>
        Output
    </strong>

    <br/>
    <code>
        [

        "beginner",
        "expert",
        "free"

        ]
    </code>

    <br/>
    <br/>

</div>

<hr/>

<div id="findQuestionsByType">

    <h2>Find Questions By Type</h2>

    <br/>
    <strong>
        Input / GET
    </strong>

    <br/>
    <code>
        /mon-entrevue-web-service/interview/findQuestionsByType.json?uuid=6969f2a5-1f53-478c-869f-46587cf93643&questionType=beginner
    </code>

    <br/>
    <strong>
        Output
    </strong>

    <br/>
    <code>
    <pre>
        [

            {
                "nameId": "1",
                "text": "Question 1",
                "note": "An really easy question",
                "type": "beginner",
                "choices": [
                    {
                        "text": "Choice 1",
                        "suggestedScore": 4,
                        "valid": true,
                        "note": "This is a right choice"
                    },
                    {
                        "text": "Choice 2",
                        "suggestedScore": 0,
                        "valid": false,
                        "note": "This is a wrong choice"
                    },
                    {
                        "text": "Choice 3",
                        "suggestedScore": 4,
                        "valid": true,
                        "note": "This is a right choice"
                    },
                    {
                        "text": "Choice 4",
                        "suggestedScore": 0,
                        "valid": false,
                        "note": null
                    },
                    {
                        "text": "Choice 5",
                        "suggestedScore": 0,
                        "valid": false,
                        "note": null
                    }
                ]
            },
            {
                "nameId": "2",
                "text": "Question 2",
                "note": "Another really easy question",
                "type": "beginner",
                "choices": [
                    {
                        "text": "Choice 1",
                        "suggestedScore": 2,
                        "valid": true,
                        "note": "This is a right choice"
                    },
                    {
                        "text": "Choice 2",
                        "suggestedScore": 0,
                        "valid": false,
                        "note": "This is a wrong choice"
                    },
                    {
                        "text": "Choice 3",
                        "suggestedScore": 2,
                        "valid": true,
                        "note": "This is a right choice"
                    },
                    {
                        "text": "Choice 4",
                        "suggestedScore": 0,
                        "valid": false,
                        "note": null
                    }
                ]
            }

        ]
    </pre>
    </code>

    <br/>
    <br/>

</div>

<hr/>

<div id="findQuestionsById">

    <h2>Find Questions By Id</h2>

    <br/>
    <strong>
        Input / GET
    </strong>

    <br/>
    <code>
        /mon-entrevue-web-service/interview/findQuestionById.json?uuid=6969f2a5-1f53-478c-869f-46587cf93643&id=1
    </code>

    <br/>
    <strong>
        Output
    </strong>

    <br/>
    <code>
    <pre>
        {

            "nameId": "1",
            "text": "Question 1",
            "note": "An really easy question",
            "type": "beginner",
            "choices": [
                {
                    "text": "Choice 1",
                    "suggestedScore": 4,
                    "valid": true,
                    "note": "This is a right choice"
                },
                {
                    "text": "Choice 2",
                    "suggestedScore": 0,
                    "valid": false,
                    "note": "This is a wrong choice"
                },
                {
                    "text": "Choice 3",
                    "suggestedScore": 4,
                    "valid": true,
                    "note": "This is a right choice"
                },
                {
                    "text": "Choice 4",
                    "suggestedScore": 0,
                    "valid": false,
                    "note": null
                },
                {
                    "text": "Choice 5",
                    "suggestedScore": 0,
                    "valid": false,
                    "note": null
                }
            ]

        }
    </pre>
    </code>

    <br/>
    <br/>

</div>

<hr/>

<div id="search">

    <h2>Search</h2>

    <br/>
    <strong>
        Input / GET
    </strong>

    <br/>
    <code>
        /mon-entrevue-web-service/interview/search.json?uuid=6969f2a5-1f53-478c-869f-46587cf93643&q=just
    </code>

    <br/>
    <strong>
        Output
    </strong>

    <br/>
    <code>
    <pre>
        [
            {
                "nameId": "4",
                "text": "Just a question",
                "note": null,
                "type": "free",
                "choices": null
            }
        ]
    </pre>
    </code>

    <br/>
    <br/>

</div>


<hr/>

<div id="findNextQuestion">

    <h2>Find Next Question</h2>
    <p>
        It works as an iterator, anytime you call it you will get a new question following a logic order.
    </p>
    <br/>
    <strong>
        Input / GET
    </strong>

    <br/>
    <code>
        /mon-entrevue-web-service/interview/findNextQuestion.json?uuid=6969f2a5-1f53-478c-869f-46587cf93643
    </code>

    <br/>
    <strong>
        Output
    </strong>

    <br/>
    <code>
    <pre>
        {

            "nameId": "1",
            "text": "Question 1",
            "note": "An really easy question",
            "type": "beginner",
            "choices": [
                {
                    "text": "Choice 1",
                    "suggestedScore": 4,
                    "valid": true,
                    "note": "This is a right choice"
                },
                {
                    "text": "Choice 2",
                    "suggestedScore": 0,
                    "valid": false,
                    "note": "This is a wrong choice"
                },
                {
                    "text": "Choice 3",
                    "suggestedScore": 4,
                    "valid": true,
                    "note": "This is a right choice"
                },
                {
                    "text": "Choice 4",
                    "suggestedScore": 0,
                    "valid": false,
                    "note": null
                },
                {
                    "text": "Choice 5",
                    "suggestedScore": 0,
                    "valid": false,
                    "note": null
                }
            ]

        }
    </pre>
    </code>

    <br/>
    <br/>

</div>


<hr/>

<div id="findRandomQuestion">

    <h2>Find Random Question</h2>
    <p>
       Get a random question from the pool
    </p>
    <br/>
    <strong>
        Input / GET
    </strong>

    <br/>
    <code>
        /mon-entrevue-web-service/interview/findRandomQuestion.json?uuid=6969f2a5-1f53-478c-869f-46587cf93643
    </code>

    <br/>
    <strong>
        Output
    </strong>

    <br/>
    <code>
    <pre>
        {

            "nameId": "1",
            "text": "Question 1",
            "note": "An really easy question",
            "type": "beginner",
            "choices": [
                {
                    "text": "Choice 1",
                    "suggestedScore": 4,
                    "valid": true,
                    "note": "This is a right choice"
                },
                {
                    "text": "Choice 2",
                    "suggestedScore": 0,
                    "valid": false,
                    "note": "This is a wrong choice"
                },
                {
                    "text": "Choice 3",
                    "suggestedScore": 4,
                    "valid": true,
                    "note": "This is a right choice"
                },
                {
                    "text": "Choice 4",
                    "suggestedScore": 0,
                    "valid": false,
                    "note": null
                },
                {
                    "text": "Choice 5",
                    "suggestedScore": 0,
                    "valid": false,
                    "note": null
                }
            ]

        }
    </pre>
    </code>

    <br/>
    <br/>

</div>


</body>
</html>
