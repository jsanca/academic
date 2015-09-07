/**
 * jQuery like library
 * @author Daniel Fernandez
 */

'use strict';

(function (global) {
    var document = global.document;
    var $;

    var selfclosing = /^(abbr|br|col|img|input|link|meta|param|hr|area|embed)$/i;

    // Make sure that self-closing tags are interpreted correctly
    // Taked from: Secrets of the JavaScript Ninja, John Resig and Bear Bibeault. Chapter 14, Manipulating the DOM
    var _convert = function (html) {

        var htmlReplaced = html.replace(/(<(\w+)[^>]*?)\/>/g, function (all, front, tag) {

            var tagResult =
                selfclosing.test(tag) ? all : front + '></' + tag + '>';

            console.log(" tagResult " + tagResult +
                " all " + all +
                " front " + front +
                " tag " + tag);

            return tagResult;
        });

        console.log(" html " + html +
            " htmlReplaced " + htmlReplaced);

        return htmlReplaced;
    };

    var dQuery = function (param) {
        var div = document.createElement('div');

        // Assume that strings that start with < are HTML
        if (param.charAt(0) === '<') {
            // If param is a string of type <tag/> or <tag></tag>, create an element of type 'tag'
            // And store it as element property of the dQuery object
            div.innerHTML = _convert(param);     
            this.el = div.childNodes[0];
        }

        // Handle $(#id)
        if (param.charAt(0) === '#') {
            this.el = document.getElementById(param.replace('#', ''));
        }    
    };

    // $().html() implementation
    dQuery.prototype.html = function (html) {
        if (html) {
            this.el.innerHTML = '' + html; // Force the toString method to be executed
            return this; // Return the dQuery instance to allow chaining
        } else {
            return this.el.innerHTML;
        }
    };

    // $().text() implementation
    dQuery.prototype.text = function (txt) {
        if (txt) {
            this.el.textContent = txt;
            return this; // Return the dQuery instance to allow chaining    
        } else {
            return this.el.textContent;
        }
    };

    // Override dQuery object toString method, to return the element HTML
    // It will be called when the dQuery object is used as an String for example when using with .html(dQuery)
    dQuery.prototype.toString = function () {
        return this.el.outerHTML;
    };

    // Expose the $ function: $()
    global.$ = function (param) {
        return new dQuery(param);
    };

    $ = global.$;

})(window);