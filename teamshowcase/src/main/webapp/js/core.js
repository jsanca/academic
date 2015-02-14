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

        /*console.log("event: " + event +
            " readyState: " + request.readyState +
            " status: " + request.status);*/
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

        /*console.log("event: " + event +
            " readyState: " + request.readyState +
            " status: " + request.status);*/
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

/**
 * Try to get the nick name from the email.
 * @param email
 * @returns {*}
 */
function getNickName(email) {

    var emailArray = email.split("@");
    return (emailArray.length > 0)?emailArray[0]:email;
}

/**
 * From the nick name gets the image name (basically the nick name without . and with the png extension)
 * @param nickName
 */
function getImageName(nickName) {

    return nickName.replace(/\./g,'') + ".png";
}

/**
 * Get elements by class.
 * @param className
 * @returns {NodeList}
 */
function getElementByClass (className) {

    return document.getElementsByClassName(className);
}


/**
 * Remove all the ChildNodes from the element
 * @param element
 */
function removeAllChildNodes (element) {

    for (var i = element.childNodes.length - 1; i >= 0; --i) {

        var child =
            element.childNodes[i];
        element.removeChild(child);
    }
}

/**
 * Get the capability name from the capability/position
 * @param capabilityPosition
 */
function getCapabilityName (capabilityPosition) {

    var capabilityPositionArray =
        capabilityPosition.split("/");

    var capabilityName = capabilityPosition;

    if (capabilityPositionArray.length > 0) {

        capabilityName =
            capabilityPositionArray[0];
    }

    return capabilityName;
} // getCapabilityName.


/**
 * Remove all the spaces from the string
 * @param string
 * @returns {XML|string|void}
 */
function removeSpaces (string) {

    return string.replace(/ /g,'');
} // removeSpaces