/**
 * Created by jsanca
 */

/**
 * Just print out a message to the user
 * @param msg
 */
function message (msg) {

    alert (msg);
} // message.

/**
 * get a json response from an url via Ajax and call the successHandler when it is successfully loaded.
 *
 * successHandler only have to expect one parameter which is the actual json returned
 *
 * @param url
 * @param successHandler
 */
function getJson (url, successHandler) {

    var request = new XMLHttpRequest();
    request.open('GET', url, true);
    request.onreadystatechange = function (event) {

        console.log("event: " + event +
            " readyState: " + request.readyState +
            " status: " + request.status);
        if (4 == request.readyState) {
            if (200 == request.status) {

                if (request.responseText) {

                    successHandler(JSON.parse(request.responseText));
                }
            } else {

                message("Please check the parameters or try later")
            }
        }
    };

    request.send(null);
} // getJson

/**
 * post a json request to an url via Ajax and call the successHandler when it is successfully loaded.
 *
 * successHandler only have to expect one parameter which is the actual json returned
 *
 * @param url
 * @param jsonObject
 * @param successHandler
 */
function postJson (url, jsonObject, successHandler) {

    var request = new XMLHttpRequest();

    request.open('POST', url, true);
    request.setRequestHeader('Content-Type', 'application/json');
    request.onreadystatechange = function (event) {

        console.log("event: " + event +
            " readyState: " + request.readyState +
            " status: " + request.status);
        if (4 == request.readyState) {
            if (200 == request.status) {

                if (request.responseText) {

                    successHandler(JSON.parse(request.responseText));
                }
            } else {

                message("Please check the parameters or try later")
            }
        }
    };

    request.send(JSON.stringify(jsonObject));
} // postJson

/**
 * Set the element visible (aka display:block)
 * @param element
 */
function setVisible (element) {

    if (element) {

         element.style.display = 'block';
    }
} // setVisible



/**
 * Set the element hide (aka display:block)
 * @param element
 */
function setHide (element) {

    if (element) {

        element.style.display = 'none';
    }
} // setHide

/**
 * If it is an assigned and not blank string returns true.
 * @param aString
 * @returns {*|boolean}
 */
function isNotBlank(aString) {

    return aString && aString.trim().length > 0;
}